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
import com.tynet.app.bean.EmployeesService;
import com.tynet.app.common.StringUtils;
import com.tynet.app.common.UIHelper;



public class Login extends Activity {
	
//	@ViewInject(R.id.tv_head_title)
//	TextView headTitle;
	@ViewInject(R.id.et_username)
	EditText etWorkCard;
	@ViewInject(R.id.et_password)
	EditText etPwd;
//	@ViewInject(R.id.btn_head_back)
//	Button headBack;
	private GenericTask mLoginTask;
	
	private BookPlatApplication appContext;
	
	private EmployeesService employeesService;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_login);
		ViewUtils.inject(this);
		appContext = (BookPlatApplication) getApplication();
		
		init();
	}
	
	private void init(){
//		headTitle.setText(getString(R.string.title_login));
//		headBack.setVisibility(View.GONE);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@OnClick(R.id.btn_makesue)
	public void loginCheck(View v){
		System.out.println("loginCheck(View v)");
		String workcard = etWorkCard.getText().toString();
		String password = etPwd.getText().toString();
		//判断输入
		if(StringUtils.isEmpty(workcard)){
			UIHelper.ToastMessage(v.getContext(), getString(R.string.msg_worker_number_null));
			return;
		}
		if(StringUtils.isEmpty(password)){
			UIHelper.ToastMessage(v.getContext(), getString(R.string.msg_password_null));
			return;
		}
        TaskParams params = new TaskParams();
        params.put("workcard", workcard);
        params.put("password", password);
   
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
							paramJSON.put("zgid", param.getString("workcard"));
							paramJSON.put("pwd", param.getString("password"));
							paramJSON.put("hosId", getString(R.string.msg_hosId));
							employeesService = appContext.loginVerify(paramJSON);
							if(employeesService.isSuccess()){
								return TaskResult.OK;
							}else{
								msg = employeesService.getMessage();
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
	    	etWorkCard.setEnabled(true);
	    	etPwd.setEnabled(true);

	    }

	    private void disableLogin() {
	    	etWorkCard.setEnabled(false);
	    	etPwd.setEnabled(false);
	    }

	    private void onLoginBegin() {
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, Login.this).start("登录服务器...");
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
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, Login.this).success();
	    	appContext.saveLoginInfo(employeesService.getJxzyyToken());
			startActivity(new Intent(Login.this,LoginYzm.class));
			finish();
	    }

	    private void onLoginFailure(String reason) {
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, Login.this).failed(reason);
	        enableLogin();
	    }
}
