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
import com.tynet.app.bean.JcBingLiInfo;
import com.tynet.app.common.StringUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class JcBingLiInfoActivity extends Activity{
	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private JcBingLiInfo m_obj;
	private String lsh;
	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.btn_head_back)
	Button backBtn;
	
	@ViewInject(R.id.bl_zyh)
	TextView bl_zyh;
	@ViewInject(R.id.bl_bed)
	TextView bl_bed;
	@ViewInject(R.id.bl_name)
	TextView bl_name;
	@ViewInject(R.id.bl_sex)
	TextView bl_sex;
	@ViewInject(R.id.bl_nl)
	TextView bl_nl;
	@ViewInject(R.id.bl_hy)
	TextView bl_hy;
	@ViewInject(R.id.bl_bq)
	TextView bl_bq;
	@ViewInject(R.id.bl_bgrq)
	TextView bl_bgrq;
	@ViewInject(R.id.bl_blh)
	TextView bl_blh;
	@ViewInject(R.id.bl_bglb)
	TextView bl_bglb;
	@ViewInject(R.id.bl_kdks)
	TextView bl_kdks;
	@ViewInject(R.id.bl_kdys)
	TextView bl_kdys;
	@ViewInject(R.id.bl_kdrq)
	TextView bl_kdrq;
	@ViewInject(R.id.bl_bbmc)
	TextView bl_bbmc;
	@ViewInject(R.id.bl_lczd)
	TextView bl_lczd;
	@ViewInject(R.id.bl_blzd)
	TextView bl_blzd;
	@ViewInject(R.id.bl_bgys)
	TextView bl_bgys;
	@ViewInject(R.id.bl_shys)
	TextView bl_shys;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_jc_bingli_info);
		ViewUtils.inject(this);
		
		Intent i = getIntent();
		lsh = i.getStringExtra("lsh");
		System.out.println("bah<>" + lsh);


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
		bl_zyh.setText(m_obj.getZyh());
		bl_bed.setText(m_obj.getBed());
		bl_name.setText(m_obj.getName());
		bl_sex.setText(m_obj.getSex());
		bl_nl.setText(m_obj.getNl());
		bl_hy.setText(m_obj.getHy());
		bl_bq.setText(m_obj.getBq());
		bl_bgrq.setText(m_obj.getBgrq());
		bl_blh.setText(m_obj.getBlh());
		bl_bglb.setText(m_obj.getBglb());
		bl_kdks.setText(m_obj.getKdks());
		bl_kdys.setText(m_obj.getKdys());
		bl_kdrq.setText(m_obj.getKdrq());
		bl_bbmc.setText(m_obj.getBbmc());
		bl_lczd.setText(m_obj.getLczd());
		bl_blzd.setText(m_obj.getBlzd());
		bl_bgys.setText(m_obj.getBgys());
		bl_shys.setText(m_obj.getShys());


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
				m_obj = appContext.getJcBingLiInfo(paramJSON);
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
					JcBingLiInfoActivity.this).start(
					getString(R.string.activity_init));
		}

		private void onInitSuccess() {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcBingLiInfoActivity.this).success();
//			m_adapter.notifyDataSetChanged();
			updateView();
		}

		private void onInitFailure(String reason) {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JcBingLiInfoActivity.this).failed(reason);
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
