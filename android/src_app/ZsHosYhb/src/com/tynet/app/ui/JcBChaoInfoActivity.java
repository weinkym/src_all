package com.tynet.app.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soft.task.GenericTask;
import com.soft.task.TaskAdapter;
import com.soft.task.TaskFeedback;
import com.soft.task.TaskListener;
import com.soft.task.TaskParams;
import com.soft.task.TaskResult;
import com.tynet.app.BookPlatApplication;
import com.tynet.app.R;
import com.tynet.app.bean.JcBChaoInfo;
import com.tynet.app.common.StringUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class JcBChaoInfoActivity extends Activity{
	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private JcBChaoInfo m_obj;
	private String lsh;
	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.btn_head_back)
	Button backBtn;
	@ViewInject(R.id.fl_titlebar)
	FrameLayout frameLayout;
	
	@ViewInject(R.id.bc_zyh)
	TextView bc_zyh;
	@ViewInject(R.id.bc_name)
	TextView bc_name;
	@ViewInject(R.id.bc_sex)
	TextView bc_sex;
	@ViewInject(R.id.bc_nl)
	TextView bc_nl;
	@ViewInject(R.id.bc_sqks)
	TextView bc_sqks;
	@ViewInject(R.id.bc_bgsj)
	TextView bc_bgsj;
	@ViewInject(R.id.bc_bbys)
	TextView bc_bbys;
	@ViewInject(R.id.bc_jcxm)
	TextView bc_jcxm;
	@ViewInject(R.id.bc_lsh)
	TextView bc_lsh;
	@ViewInject(R.id.bc_bed)
	TextView bc_bed;
	@ViewInject(R.id.bc_sqys)
	TextView bc_sqys;
	@ViewInject(R.id.bc_jcsj)
	TextView bc_jcsj;
	@ViewInject(R.id.bc_zd)
	TextView bc_zd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_jc_bchao_info);
		ViewUtils.inject(this);
		frameLayout.setBackgroundColor(this.getResources().getColor(R.color.head_bg_green));
		Intent i = getIntent();
		lsh = i.getStringExtra("lsh");
		appContext = (BookPlatApplication) getApplication();
		headTitle.setText(R.string.jcxq);
		init(lsh);// 待传入
	}	
	private void init(String lsh) {
		TaskParams params = new TaskParams();
		params.put("lsh", lsh);
		if (mInitTask != null
				&& mInitTask.getStatus() == GenericTask.Status.RUNNING) {
			return;
		} else {
			mInitTask = new InitTask();
			mInitTask.setListener(mInitTaskListener);
			mInitTask.execute(params);
		}
	}
	private void updateView()
	{
		bc_zyh.setText(m_obj.getZyh());
		bc_name.setText(m_obj.getName());
		bc_sex.setText(m_obj.getSex());
		bc_nl.setText(m_obj.getNl());
		bc_sqks.setText(m_obj.getSqks());
		bc_bgsj.setText(StringUtils.toDate2(m_obj.getBgsj()));
		bc_bbys.setText(m_obj.getBbys());
		bc_jcxm.setText(m_obj.getJcxm());		
		bc_lsh.setText(m_obj.getLsh());
		bc_bed.setText(m_obj.getBed());
		bc_sqys.setText(m_obj.getSqys());
		bc_jcsj.setText(m_obj.getJcsj());
		bc_zd.setText(m_obj.getZd());
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
				System.out.println("T<T010>");
				paramJSON.put("jxzyyToken", appContext.getLoginInfo());
				System.out.println("T<T011>");;
				paramJSON.put("hosId", getString(R.string.msg_hosId));
				System.out.println("T<T012>");
				try {
					System.out.println("T<T003>");
					paramJSON.put("lsh", param.getString("lsh"));
					System.out.println("T<T004>");
				} catch (HttpException e) {
					// TODO Auto-generated catch block
					System.out.println("T<T005>");
					e.printStackTrace();
				}
				System.out.println("T<T013>");
				m_obj = appContext.getJcBChaoInfo(paramJSON);
				System.out.println("T<T006>");
				if (m_obj.isSuccess()) {
					System.out.println("T<T007>");
//					m_itemList.clear();
//					m_itemList.addAll(m_obj.getJclbList());
					return TaskResult.OK;
				} else {
					msg = m_obj.getMessage();
					System.out.println("T<T008>");
					return TaskResult.FAILED;
				}
			} catch (JSONException e) {
				System.out.println("T<T009>");
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
					JcBChaoInfoActivity.this).start(
					getString(R.string.activity_init));
		}

		private void onInitSuccess() {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcBChaoInfoActivity.this).success();
//			m_adapter.notifyDataSetChanged();
			updateView();
		}

		private void onInitFailure(String reason) {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcBChaoInfoActivity.this).failed(reason);
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
