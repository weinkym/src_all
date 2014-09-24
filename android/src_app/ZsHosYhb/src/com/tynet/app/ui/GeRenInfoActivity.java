package com.tynet.app.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.soft.task.GenericTask;
import com.soft.task.TaskAdapter;
import com.soft.task.TaskFeedback;
import com.soft.task.TaskListener;
import com.soft.task.TaskParams;
import com.soft.task.TaskResult;
import com.tynet.app.BookPlatApplication;
import com.tynet.app.R;
import com.tynet.app.bean.TongXunLuInfo;
import com.tynet.app.common.StringUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class GeRenInfoActivity extends Activity{
	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private TongXunLuInfo m_obj;
	private String contactId;
	
//	@ViewInject(R.id.tv_head_title)
//	TextView headTitle;
	@ViewInject(R.id.btn_head_back)
	Button backBtn;
	
	@ViewInject(R.id.ib_shouji)
	ImageButton m_ib_shouji;
//	FrameLayout frameLayout;
	
	@ViewInject(R.id.tv_xingming)
	TextView tv_xingming;
	@ViewInject(R.id.tv_shouji)
	TextView tv_shouji;
	@ViewInject(R.id.tv_zhaidian)
	TextView tv_zhaidian;
	@ViewInject(R.id.tv_xiaolingtong)
	TextView tv_xiaolingtong;
	@ViewInject(R.id.tv_xunihao)
	TextView tv_xvnihao;
	@ViewInject(R.id.tv_qq)
	TextView tv_qq;
	@ViewInject(R.id.tv_youxiang)
	TextView tv_youxiang;
	@ViewInject(R.id.tv_keshi)
	TextView tv_keshi;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_geren_info);
		ViewUtils.inject(this);
//		frameLayout.setBackgroundColor(this.getResources().getColor(R.color.head_bg_green));
		Intent i = getIntent();
		contactId = i.getStringExtra("contactId");
		appContext = (BookPlatApplication) getApplication();
//		headTitle.setText(R.string.jcxq);
		init(contactId);// 待传入
	}	
	private void init(String contactId) {
		TaskParams params = new TaskParams();
		params.put("contactId", contactId);
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
		tv_xingming.setText(m_obj.getUserName());;
		tv_shouji.setText(m_obj.getMobile());
		tv_zhaidian.setText(m_obj.getHomePhone());
		tv_xvnihao.setText(m_obj.getShortMobile());
		tv_qq.setText(m_obj.getQq());
		tv_youxiang.setText(m_obj.getEmail());
		tv_keshi.setText(m_obj.getDeptName());

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
					paramJSON.put("contactId", param.getString("contactId"));
					System.out.println("T<T004>");
				} catch (HttpException e) {
					// TODO Auto-generated catch block
					System.out.println("T<T005>");
					e.printStackTrace();
				}
				System.out.println("T<T013>");
				m_obj = appContext.getTongXunLuInfo(paramJSON);
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
					GeRenInfoActivity.this).start(
					getString(R.string.activity_init));
		}

		private void onInitSuccess() {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					GeRenInfoActivity.this).success();
//			m_adapter.notifyDataSetChanged();
			updateView();
		}

		private void onInitFailure(String reason) {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					GeRenInfoActivity.this).failed(reason);
		}

		@Override
		public String getName() {
			return "Jybgactivity";
		}
	};
	
	private void callPhone(String inputStr)
	{
		if (inputStr.trim().length() != 0) {
			Intent phoneIntent = new Intent("android.intent.action.CALL",
					Uri.parse("tel:" + inputStr));
			// 启动
			startActivity(phoneIntent);
		}
		// 否则Toast提示一下
		else {
			Toast.makeText(GeRenInfoActivity.this, "不能输入为空", Toast.LENGTH_LONG)
					.show();
		}
	}
	
	private void callMessage(String inputStr)
	{
		if (inputStr.trim().length() != 0) {
			Intent intent = new Intent();
			 
			//系统默认的action，用来打开默认的短信界面
			 intent.setAction(Intent.ACTION_SENDTO);
			 
			//需要发短息的号码
			 intent.setData(Uri.parse("smsto:"+inputStr));
			 startActivity(intent);
		}
		// 否则Toast提示一下
		else {
			Toast.makeText(GeRenInfoActivity.this, "不能输入为空", Toast.LENGTH_LONG)
					.show();
		}
	}
	@OnClick(R.id.btn_head_back)
	public void goBack(View v){
		finish();
	}
	
	@OnClick(R.id.ib_shouji)
	public void goCallShouji(View v) {
		String inputStr = tv_shouji.getText().toString();
		callPhone(inputStr);
	}
	@OnClick(R.id.ib_zhaidian)
	public void goCallZhaiDian(View v) {
		String inputStr = tv_zhaidian.getText().toString();
		callPhone(inputStr);
	}
	@OnClick(R.id.ib_xiaolingtong)
	public void goCallXiaoLingTong(View v) {
		String inputStr = tv_xiaolingtong.getText().toString();
		callPhone(inputStr);
	}
	@OnClick(R.id.ib_xvnihao)
	public void goCallXvNiHao(View v) {
		String inputStr = tv_xvnihao.getText().toString();
		callPhone(inputStr);
	}
	//短信================================================
	@OnClick(R.id.ib_dx_shouji)
	public void goSendShouji(View v) {
		String inputStr = tv_shouji.getText().toString();
		callMessage(inputStr);
	}
	@OnClick(R.id.ib_dx_xiaolingtong)
	public void goSendXiaoLingTong(View v) {
		String inputStr = tv_xiaolingtong.getText().toString();
		callMessage(inputStr);
	}
	@OnClick(R.id.ib_dx_xvnihao)
	public void goSendXvNiHao(View v) {
		String inputStr = tv_xvnihao.getText().toString();
		callMessage(inputStr);
	}
}
