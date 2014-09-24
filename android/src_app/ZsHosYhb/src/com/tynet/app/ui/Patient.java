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
import com.tynet.app.bean.GbPat;
import com.tynet.app.bean.PatList;





public class Patient extends Activity {
	
	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private PatList patList;
	private List<GbPat> gbPatList = new ArrayList<GbPat>();
	private ListViewPatListListAdapter listViewPatListListAdapter;
	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.patient_list)
	ListView lvPat;
	@ViewInject(R.id.btn_more)
	Button btnMore;
	
	
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.layout_patient_list);
	ViewUtils.inject(this);
	
	appContext = (BookPlatApplication) getApplication();
    listViewPatListListAdapter = new ListViewPatListListAdapter(this,gbPatList,R.layout.layout_patient_item);
    lvPat.setAdapter(listViewPatListListAdapter);
    lvPat.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent i = new Intent(Patient.this,PatientInfo.class);
			i.putExtra("patient", (GbPat)gbPatList.get(arg2));
			startActivity(i);
		}
	});
    headTitle.setText(getString(R.string.pat_list)+"_一病区");
	init("17");
	
}
  private void init(String bqdm){
		
		btnMore.setVisibility(View.VISIBLE);
        TaskParams params = new TaskParams();
        params.put("bqdm", bqdm);
      if (mInitTask != null && mInitTask.getStatus() == GenericTask.Status.RUNNING) {
          return;
      } else {
          mInitTask = new InitTask();
          mInitTask.setListener(mInitTaskListener);
          mInitTask.execute(params);
      }
	
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
						paramJSON.put("bqdm", param.getString("bqdm"));
					} catch (HttpException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					patList = appContext.getPatList(paramJSON);
					if(patList.isSuccess()){
						gbPatList.clear();
						gbPatList.addAll(patList.getGbPatList());
						 return TaskResult.OK;
					}else{
						msg = patList.getMessage();
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

	        @Override
	        public String getName() {
	            return "Patient";
	        }
	    };

	    private void onInitBegin() {
	        TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, Patient.this).start(getString(R.string.activity_init));
	    }
            
	    private void onInitSuccess() {
	        TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, Patient.this).success();
	        listViewPatListListAdapter.notifyDataSetChanged();
	       
	        
	    }
	    
	    private void onInitFailure(String reason) {
	        TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, Patient.this).failed(reason);
	    }
	
	    @OnClick(R.id.btn_more)
	    public void onTopLeftDeptAndDoc(View v){
	    	startActivityForResult(new Intent(Patient.this,MyDialog.class), 0);
	    }
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	String bqdm = data.getStringExtra("bqdm");
	    	String bqmc = data.getStringExtra("bqmc");
	    	headTitle.setText(getString(R.string.pat_list)+"_"+bqmc);
	    	init(bqdm);
	    }
	    

	    
	@OnClick(R.id.btn_head_back)
	public void goBack(View v){
		finish();
	}
}