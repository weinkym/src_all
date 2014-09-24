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



public class Notice extends Activity {
	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.btn_head_back)
	Button headBack;
	private GenericTask mLoginTask;
	
	private BookPlatApplication appContext;
	
	private EmployeesService employeesService;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_notice);
		ViewUtils.inject(this);
		appContext = (BookPlatApplication) getApplication();
		
		init();
	}
	
	private void init(){
		headTitle.setText(getString(R.string.post_notice));
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@OnClick(R.id.btn_head_back)
	public void goBack(View v){
		finish();
	}
	
	
}
