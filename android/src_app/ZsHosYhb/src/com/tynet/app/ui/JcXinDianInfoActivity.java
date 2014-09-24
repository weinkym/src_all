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
import com.tynet.app.bean.JcXinDianInfo;
import com.tynet.app.common.StringUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class JcXinDianInfoActivity extends Activity{
	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private JcXinDianInfo m_obj;
	private String lsh;
	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.btn_head_back)
	Button backBtn;
	@ViewInject(R.id.xd_zyh)
	TextView xd_zyh;
	@ViewInject(R.id.xd_name)
	TextView xd_name;
	@ViewInject(R.id.xd_sex)
	TextView xd_sex;
	@ViewInject(R.id.xd_age)
	TextView xd_age;
	@ViewInject(R.id.xd_sfzh)
	TextView xd_sfzh;
	@ViewInject(R.id.xd_bed)
	TextView xd_bed;
	@ViewInject(R.id.xd_lb)
	TextView xd_lb;
	@ViewInject(R.id.xd_bq)
	TextView xd_bq;
	@ViewInject(R.id.xd_kdks)
	TextView xd_kdks;
	@ViewInject(R.id.xd_jcys)
	TextView xd_jcys;
	@ViewInject(R.id.xd_jcrq)
	TextView xd_jcrq;
	@ViewInject(R.id.xd_bgzt)
	TextView xd_bgzt;
	@ViewInject(R.id.xd_guid)
	TextView xd_guid;
	@ViewInject(R.id.xd_jcdl)
	TextView xd_jcdl;
	@ViewInject(R.id.xd_jcxm)
	TextView xd_jcxm;
	@ViewInject(R.id.xd_sqdh)
	TextView xd_sqdh;
	@ViewInject(R.id.xd_zd)
	TextView xd_zd;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_jc_xindian_info);
		ViewUtils.inject(this);
		
		Intent i = getIntent();
		lsh = i.getStringExtra("lsh");
		System.out.println("lsh<>" + lsh);


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
		xd_zyh.setText(m_obj.getZyh());
		xd_name.setText(m_obj.getName());
		xd_sex.setText(m_obj.getSex());
		xd_age.setText(m_obj.getAge());
		xd_sfzh.setText(m_obj.getSfzh());
		xd_bed.setText(m_obj.getBed());
		xd_lb.setText(m_obj.getLb());
		xd_bq.setText(m_obj.getBq());
		xd_kdks.setText(m_obj.getKdks());
		xd_jcys.setText(m_obj.getJcys());
		xd_jcrq.setText(m_obj.getJcrq());
		xd_bgzt.setText(m_obj.getBgzt());
		xd_guid.setText(m_obj.getGuid());
		xd_jcdl.setText(m_obj.getJcdl());
		xd_jcxm.setText(m_obj.getJcxm());
		xd_sqdh.setText(m_obj.getSqdh());
		xd_zd.setText(m_obj.getZd());
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
				m_obj = appContext.getJcXinDianInfo(paramJSON);
				if (m_obj.isSuccess()) {
//					m_itemList.clear();
//					m_itemList.addAll(m_obj.getJclbList());
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
					JcXinDianInfoActivity.this).start(
					getString(R.string.activity_init));
		}

		private void onInitSuccess() {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcXinDianInfoActivity.this).success();
//			m_adapter.notifyDataSetChanged();
			updateView();
		}

		private void onInitFailure(String reason) {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcXinDianInfoActivity.this).failed(reason);
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
