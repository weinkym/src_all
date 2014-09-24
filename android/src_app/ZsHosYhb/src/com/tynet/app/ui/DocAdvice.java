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
import com.tynet.app.adapter.ListViewYzlbListListAdapter;
import com.tynet.app.bean.GbPat;
import com.tynet.app.bean.PatList;
import com.tynet.app.bean.YzList;
import com.tynet.app.bean.Yzlb;
import com.tynet.app.bean.Yzxq;




public class DocAdvice extends Activity {
	
	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private YzList yzList;
	private List<Yzlb> yzlbList = new ArrayList<Yzlb>();
	private ListViewYzlbListListAdapter listViewYzlbListListAdapter;
	private String patientNo;
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.yizhu_list)
	ListView lvYizhu;
	@ViewInject(R.id.btn_more)
	Button btnMore;
	
	
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.layout_yizhu_list);
	ViewUtils.inject(this);
	Intent i = getIntent();
	patientNo = i.getStringExtra("patientNo");
	appContext = (BookPlatApplication) getApplication();
	
    headTitle.setText(getString(R.string.long_yizhi));
	init();
	
}
  private void init(){
		
		btnMore.setVisibility(View.VISIBLE);
        TaskParams params = new TaskParams();
        params.put("patientNo",patientNo);
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
					paramJSON.put("statusFlag", "0");
					try {
						paramJSON.put("patientNo", param.getString("patientNo"));
					} catch (HttpException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					yzList = appContext.getYzList(paramJSON);
					if(yzList.isSuccess()){
						yzlbList.clear();
						yzlbList.addAll(yzList.getYzlbList());
						 return TaskResult.OK;
					}else{
						msg = yzList.getMessage();
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
	        TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, DocAdvice.this).start(getString(R.string.activity_init));
	        listViewYzlbListListAdapter = new ListViewYzlbListListAdapter(this,yzlbList,R.layout.layout_yizhu_item);
	    	lvYizhu.setAdapter(listViewYzlbListListAdapter);
	        lvYizhu.setOnItemClickListener(new OnItemClickListener() {

	    		@Override
	         	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	    				long arg3) {
	    			Yzlb yzlb = (Yzlb)listViewYzlbListListAdapter.getItem(arg2);
	    			Intent i = new Intent(DocAdvice.this,DocAdviceInfo.class);
	    			i.putExtra("orderNo", yzlb.getOrderNo());

	    			startActivity(i);
	    			
	    		}
	    	});
	    }
            
	    private void onInitSuccess() {
	        TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, DocAdvice.this).success();
	        listViewYzlbListListAdapter.notifyDataSetChanged();

	       
	        
	    }
	    
	    private void onInitFailure(String reason) {
	        TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, DocAdvice.this).failed(reason);
	    }
	
	    @OnClick(R.id.btn_more)
	    public void changeToLs(View v){
			Intent i = new Intent(DocAdvice.this,LsDocAdvice.class);
			i.putExtra("patientNo", patientNo);
			startActivity(i);
	    	
	    	
	    }

	    
	@OnClick(R.id.btn_head_back)
	public void goBack(View v){
		finish();
	}
}