package com.tynet.app.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tynet.app.ui.SideBar.OnTouchingLetterChangedListener;
import com.tynet.app.api.*;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.soft.task.GenericTask;
import com.soft.task.TaskAdapter;
import com.soft.task.TaskFeedback;
import com.soft.task.TaskListener;
import com.soft.task.TaskParams;
import com.soft.task.TaskResult;
import com.tynet.app.BookPlatApplication;
import com.tynet.app.R;
import com.tynet.app.adapter.ListViewGeRenAdapter;
import com.tynet.app.adapter.ListViewKeShiAdapter;
//import com.tynet.app.adapter.ListViewTongXunLuAdapter;
import com.tynet.app.bean.TongXunLu;
import com.tynet.app.bean.TongXunLuList;

//import com.tynet.app.ui.TongXunLuListActivity.InitTask;

public class TongXunLuListActivity extends Activity {
	@ViewInject(R.id.btn_keshi)
	Button m_btn_keshi;
	@ViewInject(R.id.btn_geren)
	Button m_btn_geren;

	@ViewInject(R.id.lv_tongxunlu)
	ListView m_listView;

	@ViewInject(R.id.sidrbar)
	SideBar m_sideBar;

	@ViewInject(R.id.et_search)
	EditText m_editText;

	private final String KESHI = "0";
	private final String GEREN = "1";
	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private TongXunLuList m_obj;
	private List<TongXunLu> m_itemList_keshi = new ArrayList<TongXunLu>();
	private List<TongXunLu> m_itemList_geren = new ArrayList<TongXunLu>();
	private ListViewKeShiAdapter m_adapter_keshi;
	private ListViewGeRenAdapter m_adapter_geren;
	private String m_groupType;
	private boolean m_inited_keshi = false;
	private boolean m_inited_geren = false;
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_tongxunlu);
		ViewUtils.inject(this);

		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();
		
		m_editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				System.out.println("onTextChanged");
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		// 设置右侧触摸监听
		m_sideBar
				.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

					@Override
					public void onTouchingLetterChanged(String s) {
						// TODO Auto-generated method stub
						if (m_groupType.equals(KESHI)) {
							return;
						}
						int position = m_adapter_geren.getPositionForSection(s
								.charAt(0));
						if (position != -1) {
							m_listView.setSelection(position);
						}
					}
				});
		m_sideBar.setVisibility(View.GONE);
		m_groupType = GEREN;
		appContext = (BookPlatApplication) getApplication();
		m_adapter_keshi = new ListViewKeShiAdapter(this, m_itemList_keshi);
		m_adapter_geren = new ListViewGeRenAdapter(this, m_itemList_geren);
		m_groupType = KESHI;
		m_listView.setAdapter(m_adapter_keshi);
		// m_groupType = GEREN;
		// m_listView.setAdapter(m_adapter_geren);
		m_listView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						if (m_groupType.equals(GEREN)) {
							TongXunLu item = (TongXunLu) m_itemList_geren
									.get(position);
							goInfo(item);
						}
					}
				});
		init();
	}

	private void init() {
		TaskParams params = new TaskParams();
		params.put("groupType", m_groupType);
		if (mInitTask != null
				&& mInitTask.getStatus() == GenericTask.Status.RUNNING) {
			return;
		} else {
			mInitTask = new InitTask();
			mInitTask.setListener(mInitTaskListener);
			mInitTask.execute(params);
		}
	}

	private void updateView(String type) {
		if (type.equals(m_groupType)) {
			return;
		}
		final Resources res = this.getResources();
		m_groupType = type;
		if (type.equals(KESHI)) {
			m_btn_keshi.setTextColor(res.getColor(R.color.white));
			m_btn_geren.setTextColor(res.getColor(R.color.black));

			m_btn_keshi.setBackgroundDrawable(res
					.getDrawable(R.drawable.btn_left_dg));
			m_btn_geren.setBackgroundDrawable(res
					.getDrawable(R.drawable.btn_right_g));
			m_listView.setAdapter(m_adapter_keshi);
			m_sideBar.setVisibility(View.GONE);
			if (m_inited_keshi) {
				m_adapter_keshi.notifyDataSetChanged();
			} else {
				init();
			}
		} else {
			m_btn_keshi.setTextColor(res.getColor(R.color.black));
			m_btn_geren.setTextColor(res.getColor(R.color.white));

			m_btn_keshi.setBackgroundDrawable(res
					.getDrawable(R.drawable.btn_left_g));
			m_btn_geren.setBackgroundDrawable(res
					.getDrawable(R.drawable.btn_right_dg));
			m_listView.setAdapter(m_adapter_geren);
			m_sideBar.setVisibility(View.VISIBLE);
			if (m_inited_geren) {
				m_adapter_geren.notifyDataSetChanged();
			} else {
				init();
			}
		}
	}

	private class InitTask extends GenericTask {

		private String msg = getString(R.string.activity_init_failure);

		public String getMsg() {
			return msg;
		}

		@Override
		protected TaskResult _doInBackground(TaskParams... params) {
			TaskParams param = params[0];
			JSONObject paramJSON = new JSONObject();
			try {
				paramJSON.put("jxzyyToken", appContext.getLoginInfo());
				paramJSON.put("hosId", getString(R.string.msg_hosId));
				try {
					paramJSON.put("groupType", param.getString("groupType"));

				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				m_obj = appContext.getTongXunLuList(paramJSON);
				if (m_obj.isSuccess()) {
					if (m_groupType.contentEquals(KESHI)) {
						m_itemList_keshi.clear();
						m_itemList_keshi.addAll(m_obj.getJclbList());
					} else {
						m_itemList_geren.clear();
						m_itemList_geren.addAll(m_obj.getJclbList());
					}
					return TaskResult.OK;
				} else {
					msg = m_obj.getMessage();
					return TaskResult.FAILED;
				}
			} catch (JSONException e) {
				msg = "异常";
				e.printStackTrace();
				return TaskResult.FAILED;
			}
		}
	}

	private TaskListener mInitTaskListener = new TaskAdapter() {

		@Override
		public void onPreExecute(GenericTask task) {
			onInitBegin();
		}

		@Override
		public void onProgressUpdate(GenericTask task, Object param) {

		}

		@Override
		public void onPostExecute(GenericTask task, TaskResult result) {
			if (result == TaskResult.OK) {
				onInitSuccess();
			} else {
				onInitFailure(((InitTask) task).getMsg());
			}
		}

		private void onInitBegin() {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					TongXunLuListActivity.this).start(
					getString(R.string.activity_init));
		}

		private void onInitSuccess() {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					TongXunLuListActivity.this).success();
			if (m_groupType.contentEquals(KESHI)) {
				m_inited_keshi = true;
				m_adapter_keshi.notifyDataSetChanged();

			} else {
				m_inited_geren = true;
				m_adapter_geren.notifyDataSetChanged();
			}
		}

		private void onInitFailure(String reason) {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					TongXunLuListActivity.this).failed(reason);
		}

		@Override
		public String getName() {
			return "Jybgactivity";
		}
	};

	private void filterData(String filterStr) {
		if (m_groupType.equals(GEREN)) {
			filterDataGeRen(filterStr);
		} else {
			filterDataKeShi(filterStr);
		}
	}

	private void filterDataGeRen(String filterStr) {
		List<TongXunLu> filterDateList = new ArrayList<TongXunLu>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = m_itemList_geren;
		} else {
			filterDateList.clear();
			for (TongXunLu sortModel : m_itemList_geren) {
				String name = sortModel.getUserName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}
		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		m_adapter_geren.updateListView(filterDateList);
	}

	private void filterDataKeShi(String filterStr) {
		List<TongXunLu> filterDateList = new ArrayList<TongXunLu>();

		System.out.println("TTTTT<336>");
		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = m_itemList_keshi;
		} else {
			filterDateList.clear();
			for (TongXunLu sortModel : m_itemList_keshi) {
				String name = sortModel.getUserName();
				System.out.println("TTTTT<336>");
				System.out.println(name);
				int index = name.indexOf(filterStr.toString());
				System.out.println("index = "+index);

				String selling = characterParser.getSelling(name);
				System.out.println("selling = "+selling);

				boolean ok = selling.startsWith(filterStr.toString());
				System.out.println("ok = "+ok);

				if (index != -1
						|| ok) {
					filterDateList.add(sortModel);
				}
			}
		}
		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		m_adapter_keshi.updateListView(filterDateList);
	}

	private void goInfo(TongXunLu item) {
		// contactId
		Intent i = new Intent(this, GeRenInfoActivity.class);
		// TongXunLu item = (TongXunLu)m_itemList_geren.get(position);
		i.putExtra("contactId", item.getContactId());
		startActivity(i);
	}

	@OnClick(R.id.btn_head_back)
	public void goBack(View v) {
		finish();
	}

	@OnClick(R.id.btn_keshi)
	public void onKeshi(View v) {
		updateView(KESHI);
	}

	@OnClick(R.id.btn_geren)
	public void onGeRen(View v) {
		updateView(GEREN);
	}

}
