package com.tynet.app.ui;



import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;










import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.soft.task.GenericTask;
import com.soft.task.TaskAdapter;
import com.soft.task.TaskFeedback;
import com.soft.task.TaskListener;
import com.soft.task.TaskParams;
import com.soft.task.TaskResult;
import com.tynet.app.BookPlatApplication;
import com.tynet.app.R;
import com.tynet.app.adapter.ListViewBqListListAdapter;
import com.tynet.app.adapter.ListViewPatListListAdapter;
import com.tynet.app.bean.Bq;
import com.tynet.app.bean.BqList;


public class MyDialog extends Activity {

	private BookPlatApplication appContext;
	private GenericTask mInitTask;
	private List<Bq> bqs = new ArrayList<Bq>();
	private ListViewBqListListAdapter adapter;
	@ViewInject(R.id.gv_gridview)
	GridView gv;
	
 @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.layout_yygh_search_top_left_dialog);
	ViewUtils.inject(this);
	appContext = (BookPlatApplication) getApplication();
	adapter = new ListViewBqListListAdapter(this, bqs, R.layout.layout_bq_item);
	gv.setAdapter(adapter);
	gv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent i = new Intent();
			i.putExtra("bqdm", bqs.get(arg2).getBqdm());
			i.putExtra("bqmc", bqs.get(arg2).getBqmc());
			setResult(0, i);
			finish();
		}
	});
	init();
}
 
 private void init(){
	 if (mInitTask != null && mInitTask.getStatus() == GenericTask.Status.RUNNING) {
         return;
     } else {
         mInitTask = new InitTask();
         mInitTask.setListener(mInitTaskListener);
         mInitTask.execute();
     }
 }
 
 private class InitTask extends GenericTask {

     private String msg = getString(R.string.activity_init_failure);

     public String getMsg() {
         return msg;
     }

     @Override
     protected TaskResult _doInBackground(TaskParams... params) {
         //TaskParams param = params[0];
         JSONObject paramJSON = new JSONObject();
         try {
				paramJSON.put("jxzyyToken", appContext.getLoginInfo());
				paramJSON.put("hosId", getString(R.string.msg_hosId));
				BqList bqList = appContext.getBqList(paramJSON);
				if(bqList.isSuccess()){
					bqs.addAll(bqList.getBqList());
					return TaskResult.OK;
				}else{
					msg = bqList.getMessage();
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
         return "MyDialog";
     }
 };

 private void onInitBegin() {
     TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, MyDialog.this).start(getString(R.string.activity_init));
 }
     
 private void onInitSuccess() {
     TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, MyDialog.this).success();
     adapter.notifyDataSetChanged();
 }
 
 private void onInitFailure(String reason) {
     TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE, MyDialog.this).failed(reason);
 }
 
 public boolean onTouchEvent(MotionEvent paramMotionEvent)
 {
   finish();
   return true;
 }

}
	

  

