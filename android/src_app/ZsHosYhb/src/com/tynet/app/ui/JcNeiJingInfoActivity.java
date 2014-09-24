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
import com.tynet.app.bean.JcNeiJingInfo;
import com.tynet.app.common.StringUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class JcNeiJingInfoActivity extends Activity{
	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private JcNeiJingInfo m_obj;
	private String lsh;
	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.btn_head_back)
	Button backBtn;
	@ViewInject(R.id.nj_zyh)
	TextView nj_zyh;
	@ViewInject(R.id.nj_name)
	TextView nj_name;
	@ViewInject(R.id.nj_sex)
	TextView nj_sex;
	@ViewInject(R.id.nj_nl)
	TextView nj_nl;
	@ViewInject(R.id.nj_sqks)
	TextView nj_sqks;
	@ViewInject(R.id.nj_sqys)
	TextView nj_sqys;
	@ViewInject(R.id.nj_jcrq)
	TextView nj_jcrq;
	@ViewInject(R.id.nj_jcxm)
	TextView nj_jcxm;
	@ViewInject(R.id.nj_yqmc)
	TextView nj_yqmc;
	@ViewInject(R.id.nj_zdms)
	TextView nj_zdms;
	@ViewInject(R.id.nj_njzd)
	TextView nj_njzd;
	@ViewInject(R.id.nj_hj)
	TextView nj_hj;
	@ViewInject(R.id.nj_jcys)
	TextView nj_jcys;
	@ViewInject(R.id.nj_ysjy)
	TextView nj_ysjy;
	@ViewInject(R.id.nj_lsh)
	TextView nj_lsh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_jc_neijing_info);
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
		nj_zyh.setText(m_obj.getZyh());
		nj_name.setText(m_obj.getName());
		nj_sex.setText(m_obj.getSex());
		nj_nl.setText(m_obj.getNl());
		nj_sqks.setText(m_obj.getSqks());
		nj_sqys.setText(m_obj.getSqys());
		nj_jcrq.setText(StringUtils.toDate2(m_obj.getJcrq()));
		nj_jcxm.setText(m_obj.getJcxm());
		nj_yqmc.setText(m_obj.getYqmc());
		nj_zdms.setText(m_obj.getZdms());
		nj_njzd.setText(m_obj.getNjzd());
		nj_hj.setText(m_obj.getHj());
		nj_jcys.setText(m_obj.getJcys());
		nj_ysjy.setText(m_obj.getYsjy());
		nj_lsh.setText(m_obj.getLsh());

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
				m_obj = appContext.getJcNeiJingInfo(paramJSON);
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
					JcNeiJingInfoActivity.this).start(
					getString(R.string.activity_init));
		}

		private void onInitSuccess() {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcNeiJingInfoActivity.this).success();
//			m_adapter.notifyDataSetChanged();
			updateView();
		}

		private void onInitFailure(String reason) {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcNeiJingInfoActivity.this).failed(reason);
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
