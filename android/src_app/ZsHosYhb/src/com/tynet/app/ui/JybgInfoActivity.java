package com.tynet.app.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.soft.task.GenericTask;
import com.soft.task.TaskAdapter;
import com.soft.task.TaskFeedback;
import com.soft.task.TaskListener;
import com.soft.task.TaskParams;
import com.soft.task.TaskResult;
import com.tynet.app.BookPlatApplication;
import com.tynet.app.R;
import com.tynet.app.bean.GbPat;
import com.tynet.app.bean.Jybg;
import com.tynet.app.bean.JybgInfo;
import com.tynet.app.bean.JybgInfoList;
import com.tynet.app.common.StringUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class JybgInfoActivity extends Activity{
	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private JybgInfoList m_obj;
	private Jybg m_jybg;
	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.btn_head_back)
	Button backBtn;
	@ViewInject(R.id.fl_titlebar)
	FrameLayout frameLayout;
	
	@ViewInject(R.id.jybg_bgsj)
	TextView jybg_bgsj;
	@ViewInject(R.id.jybg_cjsj)
	TextView jybg_cjsj;
	@ViewInject(R.id.jybg_dh)
	TextView jybg_dh;
	@ViewInject(R.id.jybg_jyxm)
	TextView jybg_jyxm;
	@ViewInject(R.id.jybg_ks)
	TextView jybg_ks;
	@ViewInject(R.id.jybg_xb)
	TextView jybg_xb;
	@ViewInject(R.id.jybg_xm)
	TextView jybg_xm;
	@ViewInject(R.id.jybg_yb)
	TextView jybg_yb;
//==============================
	@ViewInject(R.id.jybg_tb_layout)
	TableLayout m_tbLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_jybg_info);
		ViewUtils.inject(this);
		frameLayout.setBackgroundColor(this.getResources().getColor(R.color.head_bg_green));
//		TableLayout tbLayout= (TableLayout)findViewById(R.id.TableLayout01);
		m_tbLayout.setStretchAllColumns(true);
		Intent i = getIntent();
		m_jybg =  (Jybg)i.getSerializableExtra("jybg");
    	
		jybg_bgsj.setText(getDateString(m_jybg.getChecktime()));//格式有问题
		jybg_cjsj.setText(getDateString(m_jybg.getExecutetime()));//格式有问题
		jybg_dh.setText(m_jybg.getSqh());//数据不确定
		jybg_jyxm.setText(m_jybg.getExaminaim());
//		jybg_ks.setText(m_jybg.getChecktime());//无数据
//		jybg_xb.setText(m_jybg.getChecktime());//无数据
		jybg_xm.setText(m_jybg.getPatientname());
		jybg_yb.setText(m_jybg.getSampletype());//数据不确定
		
		appContext = (BookPlatApplication) getApplication();
		headTitle.setText(R.string.jcxq);
		init(m_jybg.getSqh(),m_jybg.getYbh());
	}	
	private void init(String sql,String csh) {		
		TaskParams params = new TaskParams();
		params.put("sql", sql);
		params.put("csh", csh);
		if (mInitTask != null
				&& mInitTask.getStatus() == GenericTask.Status.RUNNING) {
			return;
		} else {
			mInitTask = new InitTask();
			mInitTask.setListener(mInitTaskListener);
			mInitTask.execute(params);
		}
	}
	public String getDateString(String time)
	{
    	SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
    	return format.format(StringUtils.toDate(time));
	}
	private void updateView()
	{
		m_tbLayout.removeAllViews();
		List<JybgInfo> infoList = m_obj.getJybgInfoList();
		System.out.println(infoList.size());
		
		for(int row = 0; row < infoList.size();++row)
		{
			JybgInfo info = (JybgInfo)infoList.get(row);
			addRow(info);
		}
	}
	private void addRow(JybgInfo info)
	{
		TableRow tbRow= new TableRow(this);
		TextView tv1 = new TextView(this);
		tv1.setText(info.getChinesename());
		Resources res = getResources();
		
		tv1.setTextColor(res.getColor(R.color.black));
		tbRow.addView(tv1);
		
		TextView tv2 = new TextView(this);
		tv2.setText(info.getTestresult());
		tv2.setTextColor(res.getColor(R.color.black));
		tbRow.addView(tv2);
		
		TextView tv3 = new TextView(this);
		tv3.setText(info.getRange());
		tv3.setTextColor(res.getColor(R.color.black));
		tbRow.addView(tv3);
		
		TextView tv4 = new TextView(this);
		tv4.setText(info.getUnit());
		tv4.setTextColor(res.getColor(R.color.black));
		tbRow.addView(tv4);
		
		
		String wjs = info.getUnit();
		String text = info.getUnit();
		if(wjs.equals("H"))
		{
			text = "偏高";
		}
		else if(wjs.equals("L"))
		{
			text = "偏低";
		}
		else
		{
			text = "正常";
		}
		TextView tv5 = new TextView(this);
		tv5.setText(text);
		tv5.setTextColor(res.getColor(R.color.black));
		tbRow.addView(tv5);
		m_tbLayout.addView(tbRow);
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
					paramJSON.put("sql", param.getString("sql"));
					paramJSON.put("csh", param.getString("csh"));
				} catch (HttpException e) {
					// TODO Auto-generated catch block
					System.out.println("T<T005>");
					e.printStackTrace();
				}
				m_obj = appContext.getJybgInfoList(paramJSON);
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
					JybgInfoActivity.this).start(
					getString(R.string.activity_init));
		}

		private void onInitSuccess() {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JybgInfoActivity.this).success();
//			m_adapter.notifyDataSetChanged();
			updateView();
		}

		private void onInitFailure(String reason) {
			TaskFeedback.getInstance(TaskFeedback.DIALOG_MODE,
					JybgInfoActivity.this).failed(reason);
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
