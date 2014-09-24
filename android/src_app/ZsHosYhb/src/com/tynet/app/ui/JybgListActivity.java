package com.tynet.app.ui;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.soft.task.GenericTask;
import com.soft.task.TaskAdapter;
import com.soft.task.TaskFeedback;
import com.soft.task.TaskListener;
import com.soft.task.TaskParams;
import com.soft.task.TaskResult;
import com.tynet.app.BookPlatApplication;
import com.tynet.app.R;
import com.tynet.app.adapter.ListViewJybgAdapter;
import com.tynet.app.bean.Jybg;
import com.tynet.app.bean.JybgList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

public class JybgListActivity extends Activity {

	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private JybgList jybgList;
	private List<Jybg> gbJybgList = new ArrayList<Jybg>();
	private ListViewJybgAdapter m_adapter;
	private String bah;

	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.jy_record_list)
	ListView lvRecord;
	@ViewInject(R.id.btn_head_back)
	Button btnBack;
	@ViewInject(R.id.fl_titlebar)
	FrameLayout frameLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_jy_report_list);
		ViewUtils.inject(this);
		frameLayout.setBackgroundColor(this.getResources().getColor(R.color.head_bg_green));

		Intent i = getIntent();
		bah = i.getStringExtra("bah");
		appContext = (BookPlatApplication) getApplication();
		m_adapter = new ListViewJybgAdapter(this, gbJybgList,
				R.layout.layout_check_record_item);
		lvRecord.setAdapter(m_adapter);
		lvRecord.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO
				 Intent i = new Intent(JybgListActivity.this,JybgInfoActivity.class);
				 i.putExtra("jybg", (Jybg)gbJybgList.get(arg2));
				 startActivity(i);
			}
		});
		headTitle.setText(R.string.jybgjl);
		init(bah);// 待传入
	}

	private void init(String bah) {
		TaskParams params = new TaskParams();
		params.put("bah", bah);
		if (mInitTask != null
				&& mInitTask.getStatus() == GenericTask.Status.RUNNING) {
			return;
		} else {
			mInitTask = new InitTask();
			mInitTask.setListener(mInitTaskListener);
			mInitTask.execute(params);
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
					paramJSON.put("bah", param.getString("bah"));

				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jybgList = appContext.getJybgList(paramJSON);
				if (jybgList.isSuccess()) {
					gbJybgList.clear();
					gbJybgList.addAll(jybgList.getJylbList());
					return TaskResult.OK;
				} else {
					msg = jybgList.getMessage();
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
					JybgListActivity.this).start(
					getString(R.string.activity_init));
		}

		private void onInitSuccess() {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JybgListActivity.this).success();
			m_adapter.notifyDataSetChanged();
		}

		private void onInitFailure(String reason) {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JybgListActivity.this).failed(reason);
		}

		@Override
		public String getName() {
			return "Jybgactivity";
		}
	};
	@OnClick(R.id.btn_head_back)
	public void goBack(View v){
		finish();
	}
}
