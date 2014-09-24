package com.tynet.app.ui;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tynet.app.R;
import com.soft.task.GenericTask;
import com.soft.task.TaskAdapter;
import com.soft.task.TaskFeedback;
import com.soft.task.TaskListener;
import com.soft.task.TaskParams;
import com.soft.task.TaskResult;
import com.tynet.app.BookPlatApplication;
import com.tynet.app.bean.Base;
import com.tynet.app.bean.EmployeesService;
import com.tynet.app.common.StringUtils;
import com.tynet.app.common.UIHelper;


public class LoginYzm extends Activity {
	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.et_checknum)
	EditText etCheckNum;
	@ViewInject(R.id.btn_head_back)
	Button headBack;

	private GenericTask mLoginTask;

	private GenericTask mSendLoginTask;
	
	private BookPlatApplication appContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_login1);
		ViewUtils.inject(this);
		appContext = (BookPlatApplication) getApplication();
	
		init();
	}
	
	private void init(){
		headTitle.setText(getString(R.string.title_login));
		headBack.setVisibility(View.GONE);
	        if (mSendLoginTask != null && mSendLoginTask.getStatus() == GenericTask.Status.RUNNING) {
	            return;
	        } else {
	            mSendLoginTask = new SendLoginTask();
	            mSendLoginTask.setListener(mSendLoginTaskListener);
	            mSendLoginTask.execute();			
	        }		
	}
	
	 private class SendLoginTask extends GenericTask {
	        {
	            msg = "";
	        }

	        public SendLoginTask() {

	        }

	        @Override
	        protected TaskResult _doInBackground(TaskParams... params) {
	           // TaskParams param = params[0];
	            JSONObject paramJSON = new JSONObject();
	                
						try {
							paramJSON.put("jxzyyToken", appContext.getLoginInfo());
							Base e = appContext.sendLoginCode(paramJSON);
							if(e.isSuccess()){
								msg = e.getMessage();
								return TaskResult.OK;
							}else{
								msg = e.getMessage();
								return TaskResult.FAILED;
							}
						} catch (JSONException e) {
							msg = "异常";
							e.printStackTrace();
							return TaskResult.FAILED;
						}
					
	        }
	        
	    }
	 
	    private TaskListener mSendLoginTaskListener = new TaskAdapter() {

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
	                onInitSuccess(task.getMsg());
	            } else {
	                onInitFailure(task.getMsg());
	            }
	        }

	        @Override
	        public String getName() {
	            return "Login";
	        }
	    };
	    
	    private void onInitBegin() {
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, LoginYzm.this).start("验证码发送中...");
	    }
	    
	    private void onInitSuccess(String success) {
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, LoginYzm.this).success(success);
	       
	    }

	    private void onInitFailure(String reason) {
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, LoginYzm.this).failed(reason);
	       
	    }
	
	
	@OnClick(R.id.btn_resent)
	public void reSent(View v){
		init();
	}
	

	
	@OnClick(R.id.btn_makesue)
	public void loginCheck(View v){
		String checkNum = etCheckNum.getText().toString();
		//判断输入
		if(StringUtils.isEmpty(checkNum)){
			UIHelper.ToastMessage(v.getContext(), getString(R.string.msg_checknum_null));
			return;
		
		}
        TaskParams params = new TaskParams();
        params.put("captcha", checkNum);


   
        if (mLoginTask != null && mLoginTask.getStatus() == GenericTask.Status.RUNNING) {
            return;
        } else {
            mLoginTask = new LoginTask();
            mLoginTask.setListener(mLoginTaskListener);
            mLoginTask.execute(params);			
        }		
	}
	
	
	
	 private class LoginTask extends GenericTask {
	        {
	            msg = "";
	        }

	        public LoginTask() {

	        }

	        @Override
	        protected TaskResult _doInBackground(TaskParams... params) {
	            TaskParams param = params[0];
	            JSONObject paramJSON = new JSONObject();
	                try {
						try {
							paramJSON.put("captcha", param.getString("captcha"));
							paramJSON.put("jxzyyToken", appContext.getLoginInfo());
							Base e = appContext.chkLoginCode(paramJSON);
							if(e.isSuccess()){
								return TaskResult.OK;
							}else{
								msg = e.getMessage();
								return TaskResult.FAILED;
							}
						} catch (JSONException e) {
							msg = "异常";
							e.printStackTrace();
							return TaskResult.FAILED;
						}
					} catch (HttpException e) {
						msg = "异常";
						e.printStackTrace();
						return TaskResult.FAILED;
					}
	                
	                
	            
	        }
	    }
	    
	    private void enableLogin() {
	    	etCheckNum.setEnabled(true);


	    }

	    private void disableLogin() {
	    	etCheckNum.setEnabled(false);

	    }

	    private void onLoginBegin() {
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, LoginYzm.this).start("验证中...");
	        disableLogin();
	    }
	    
	    private TaskListener mLoginTaskListener = new TaskAdapter() {

	        @Override
	        public void onPreExecute(GenericTask task) {
	            onLoginBegin();
	        }

	        @Override
	        public void onProgressUpdate(GenericTask task, Object param) {
	           
	        }

	        @Override
	        public void onPostExecute(GenericTask task, TaskResult result) {
	            if (result == TaskResult.OK) {
	                onLoginSuccess(task.getMsg());
	            } else {
	                onLoginFailure(task.getMsg());
	            }
	        }

	        @Override
	        public String getName() {
	            return "Login";
	        }
	    };
	    
	    private void onLoginSuccess(String success) {
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, LoginYzm.this).success();
	        startActivity(new Intent(this,Main.class));
	        finish();
	    }

	    private void onLoginFailure(String reason) {
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, LoginYzm.this).failed(reason);
	        enableLogin();
	    }
}
