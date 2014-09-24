package com.tynet.app.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.soft.task.GenericTask;
import com.soft.task.TaskAdapter;
import com.soft.task.TaskFeedback;
import com.soft.task.TaskListener;
import com.soft.task.TaskParams;
import com.soft.task.TaskResult;
import com.tynet.app.BookPlatApplication;
import com.tynet.app.R;
import com.tynet.app.adapter.ListViewPatListListAdapter;
import com.tynet.app.bean.Base;
import com.tynet.app.bean.GbPat;
import com.tynet.app.bean.LsYzxq;
import com.tynet.app.bean.PatList;
import com.tynet.app.bean.Yzxq;
import com.tynet.app.common.StringUtils;





public class LsDocAdviceInfo extends Activity {
	

	private BookPlatApplication appContext;

	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.start_time)
	TextView startTime;
	@ViewInject(R.id.doctor_advice_detail)
	TextView doctorAdviceDetail;
	@ViewInject(R.id.use_way)
	TextView useWay;
	@ViewInject(R.id.frequency)
	TextView frequency;
	@ViewInject(R.id.doc_kaidan)
	TextView docKaidan;
	@ViewInject(R.id.doctor_advice_type)
	TextView doctorAdviceType;
	@ViewInject(R.id.doctor_advice_state)
	TextView doctorAdviceState;
	@ViewInject(R.id.doctor_advice_description)
	TextView doctorAdviceDescription;
	@ViewInject(R.id.time_stop)
	TextView stopTime;
	@ViewInject(R.id.doctor_stop)
	TextView stopDoctor;
	@ViewInject(R.id.time_stop_execute)
	TextView stopExecuteTime;
	@ViewInject(R.id.stop_operator)
	TextView stopOperator;
	@ViewInject(R.id.skin_test_result)
	TextView skinTestResult;
	@ViewInject(R.id.doctor_advice_name)
	TextView doctorAdviceName;
	@ViewInject(R.id.medicine_type)
	TextView medicineType;
	@ViewInject(R.id.operator)
	TextView operator;
	@ViewInject(R.id.proofreader)
	TextView proofReader;
	
	@ViewInject(R.id.btn_head_back)
	Button headBack;
	
	private LsYzxq yzxq;
	private String orderNo;
	private GenericTask mAdviceInfoTask;


	
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.layout_doctor_advice_info);
	ViewUtils.inject(this);
	
	appContext = (BookPlatApplication) getApplication();

    Intent i = getIntent();
    orderNo = i.getStringExtra("orderNo");

	init();
	
}
  private void init(){
		
	  headTitle.setText(getString(R.string.short_yizhu_info));
		
        if (mAdviceInfoTask != null && mAdviceInfoTask.getStatus() == GenericTask.Status.RUNNING) {
            return;
        } else {
        	mAdviceInfoTask = new AdviceInfoTask();
        	mAdviceInfoTask.setListener(mAdviceInfoTaskListener);
        	mAdviceInfoTask.execute();			
        }		
}

	 private class AdviceInfoTask extends GenericTask {
	        {
	            msg = "";
	        }

	        public AdviceInfoTask() {

	        }

	        @Override
	        protected TaskResult _doInBackground(TaskParams... params) {
	           // TaskParams param = params[0];
	            JSONObject paramJSON = new JSONObject();
	                
						try {
							paramJSON.put("jxzyyToken", appContext.getLoginInfo());
							paramJSON.put("hosId", getString(R.string.msg_hosId));
							paramJSON.put("orderNo", orderNo);
							
							yzxq = appContext.getLsYzxq(paramJSON);
							if(yzxq.isSuccess()){
								msg = yzxq.getMessage();
								return TaskResult.OK;
							}else{
								msg = yzxq.getMessage();
								return TaskResult.FAILED;
							}
						} catch (JSONException e) {
							msg = "异常";
							e.printStackTrace();
							return TaskResult.FAILED;
						}
					
	        }
	        
	    }
	 
	    private TaskListener mAdviceInfoTaskListener = new TaskAdapter() {

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
	                onInitFailure(task.getMsg());
	            }
	        }

	        @Override
	        public String getName() {
	            return "LsDocAdviceInfo";
	        }
	    };
	    
	    private void onInitBegin() {
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, LsDocAdviceInfo.this).start("正在获取医嘱详情...");
	    }
	    
	    private void onInitSuccess() {
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, LsDocAdviceInfo.this).success();
	    	startTime.setText(StringUtils.toDate2(yzxq.getStartTime()));
	    	doctorAdviceDetail.setText(yzxq.getYznr());
	    	useWay.setText(yzxq.getSupplyName());
	    	frequency.setText(yzxq.getFrequCode());
	    	docKaidan.setText(yzxq.getPhysicianName());
	    	doctorAdviceType.setText(yzxq.getYzlx());
	    	doctorAdviceState.setText(yzxq.getStatusFlag());
	    	doctorAdviceDescription.setText(yzxq.getInstruction());
	    	stopTime.setText(StringUtils.toDate2(yzxq.getEndTime()));
	    	stopDoctor.setText(yzxq.getTzys());
	    	stopExecuteTime.setText(StringUtils.toDate2(yzxq.getStatusTime()));
	    	stopOperator.setText(yzxq.getTzhdr());
	    	skinTestResult.setText(yzxq.getPsjg());
	    	doctorAdviceName.setText(yzxq.getOrderName());
	    	medicineType.setText(yzxq.getYpgg());
	    	operator.setText(yzxq.getZxr());
	    	proofReader.setText(yzxq.getHdr());
	    }

	    private void onInitFailure(String reason) {
	    	TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, LsDocAdviceInfo.this).failed(reason);
	       
	    }
	
  
	    
	@OnClick(R.id.btn_head_back)
	public void goBack(View v){
		finish();
	}
}