package com.tynet.app.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

import com.soft.task.GenericTask;
import com.soft.task.TaskAdapter;
import com.soft.task.TaskFeedback;
import com.soft.task.TaskListener;
import com.soft.task.TaskParams;
import com.soft.task.TaskResult;
import com.tynet.app.BookPlatApplication;
import com.tynet.app.R;
import com.tynet.app.adapter.ListViewJcXinDianAdapter;
import com.tynet.app.bean.JcXinDian;
import com.tynet.app.bean.JcXinDianList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class JcXinDianListActivity extends ListActivity{

	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private JcXinDianList m_obj;
	private List<JcXinDian> m_itemList = new ArrayList<JcXinDian>();
	private ListViewJcXinDianAdapter m_adapter;
	private String bah;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_list_view);
		
		Intent i = getIntent();
		bah = i.getStringExtra("bah");
//		bah = "07161171";
		appContext = (BookPlatApplication) getApplication();
		m_adapter = new ListViewJcXinDianAdapter(this, m_itemList,
				R.layout.layout_check_record_item);
		setListAdapter(m_adapter);
		init(bah);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this,JcXinDianInfoActivity.class);
		JcXinDian item = (JcXinDian)m_itemList.get(position);
		i.putExtra("lsh",item.getGuid());
		startActivity(i);
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
				m_obj = appContext.getJcXinDianList(paramJSON);
				if (m_obj.isSuccess()) {
					m_itemList.clear();
					m_itemList.addAll(m_obj.getJclbList());
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
					JcXinDianListActivity.this).start(
					getString(R.string.activity_init));
		}

		private void onInitSuccess() {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcXinDianListActivity.this).success();
			m_adapter.notifyDataSetChanged();
		}

		private void onInitFailure(String reason) {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcXinDianListActivity.this).failed(reason);
		}

		@Override
		public String getName() {
			return "Jybgactivity";
		}
	};
}
