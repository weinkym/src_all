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
import com.tynet.app.bean.JcFangSheInfo;
import com.tynet.app.common.StringUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class JcFangSheInfoActivity extends Activity{
	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private JcFangSheInfo m_obj;
	private String lsh;
	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.btn_head_back)
	Button backBtn;
	
	@ViewInject(R.id.fs_clinicId)
	TextView fs_clinicId;
	@ViewInject(R.id.fs_bedNo)
	TextView fs_bedNo;
	@ViewInject(R.id.fs_name)
	TextView fs_name;
	@ViewInject(R.id.fs_gender)
	TextView fs_gender;
	@ViewInject(R.id.fs_age)
	TextView fs_age;
	@ViewInject(R.id.fs_deptName)
	TextView fs_deptName;
	@ViewInject(R.id.fs_approvepeople)
	TextView fs_approvepeople;
	@ViewInject(R.id.fs_registerTime)
	TextView fs_registerTime;
	@ViewInject(R.id.fs_approvedate)
	TextView fs_approvedate;
	@ViewInject(R.id.fs_examItemName)
	TextView fs_examItemName;
	@ViewInject(R.id.fs_examname)
	TextView fs_examname;
	@ViewInject(R.id.fs_examdesc)
	TextView fs_examdesc;
	@ViewInject(R.id.fs_examdiagnosis)
	TextView fs_examdiagnosis;
	@ViewInject(R.id.fs_checkNumber)
	TextView fs_checkNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_jc_fangshe_info);
		ViewUtils.inject(this);		
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
		fs_clinicId.setText(m_obj.getClinicId());
		fs_bedNo.setText(m_obj.getBedNo());
		fs_name.setText(m_obj.getName());
		fs_gender.setText(m_obj.getGender());
		fs_age.setText(m_obj.getAge());
		fs_deptName.setText(m_obj.getDeptName());
		fs_approvepeople.setText(m_obj.getApprovepeople());
		fs_registerTime.setText(StringUtils.toDate2(m_obj.getRegisterTime()));
		fs_approvedate.setText(StringUtils.toDate2(m_obj.getApprovedate()));
		fs_examItemName.setText(m_obj.getExamItemName());
		fs_examname.setText(m_obj.getExamname());
		fs_examdesc.setText(m_obj.getExamdesc());
		fs_examdiagnosis.setText(m_obj.getExamdiagnosis());
		fs_checkNumber.setText(m_obj.getCheckNumber());

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
					paramJSON.put("lsh", param.getString("lsh"));
				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				m_obj = appContext.getJcFangSheInfo(paramJSON);
				if (m_obj.isSuccess()) {
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
					JcFangSheInfoActivity.this).start(
					getString(R.string.activity_init));
		}

		private void onInitSuccess() {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcFangSheInfoActivity.this).success();
//			m_adapter.notifyDataSetChanged();
			updateView();
		}

		private void onInitFailure(String reason) {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcFangSheInfoActivity.this).failed(reason);
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
