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
import com.tynet.app.common.StringUtils;




public class PatientInfo extends Activity {
	

	private BookPlatApplication appContext;

	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.patient_name)
	TextView patientName;
	@ViewInject(R.id.patient_sex)
	TextView patientSex;
	@ViewInject(R.id.patient_age)
	TextView patientAge;
	@ViewInject(R.id.patient_bed)
	TextView patientBed;
	@ViewInject(R.id.patient_card)
	TextView patientCard;
	@ViewInject(R.id.patient_home_phone)
	TextView patientHomePhone;
	@ViewInject(R.id.patient_phone)
	TextView patientPhone;
	@ViewInject(R.id.patient_work_address)
	TextView patientWorkAddress;
	@ViewInject(R.id.patient_address)
	TextView patientAddress;
	@ViewInject(R.id.patient_prot)
	TextView patientProt;
	@ViewInject(R.id.patient_type)
	TextView patientType;
	@ViewInject(R.id.patient_ry_date)
	TextView patientRyDate;
	@ViewInject(R.id.patient_ry_dept)
	TextView patientRyDept;
	@ViewInject(R.id.patient_cur_dept)
	TextView patientCurDept;
	@ViewInject(R.id.patient_bedCode)
	TextView patientBedCode;
	@ViewInject(R.id.patient_diagName)
	TextView patientDiagName;
	@ViewInject(R.id.patient_bq)
	TextView patientBq;
	private GbPat pat;

	
	
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.layout_patient_info);
	ViewUtils.inject(this);
	
	appContext = (BookPlatApplication) getApplication();

    Intent i = getIntent();
    pat =  (GbPat)i.getSerializableExtra("patient");
	init();
	
}
  private void init(){
		
	  headTitle.setText(getString(R.string.pat_info));
	  patientName.setText(pat.getName());
	  patientAddress.setText(pat.getHomeAddress());
	  patientAge.setText(pat.getNl());
	  patientBed.setText(pat.getBah());
	  patientBedCode.setText(pat.getCurrBed());
	  patientBq.setText(pat.getBqmc());
	  patientCard.setText(pat.getSocialId());
	  patientCurDept.setText(pat.getCurrKsmc());
	  patientDiagName.setText(pat.getDiagName());
	  patientHomePhone.setText(pat.getHomeTel());
	  patientPhone.setText(pat.getRelationTel());
	  patientProt.setText(pat.getBrxz());
	  patientRyDate.setText(StringUtils.toDate2(pat.getAdmissDate()));
	  patientRyDept.setText(pat.getAdmissKsName());
	  patientSex.setText(pat.getSex());
	  patientType.setText(pat.getBrlb());
	  patientWorkAddress.setText(pat.getEmployer());
	}	
	 
  @OnClick(R.id.btn_ckyz)
  public void chakanyizhu(View v){
		Intent i = new Intent(PatientInfo.this,DocAdvice.class);
		i.putExtra("patientNo",pat.getPatientNo());
		startActivity(i);
  }
  
  @OnClick(R.id.btn_zybg)
  public void zhuyuanbaogao(View v){
		Intent i = new Intent(PatientInfo.this,JcbgListActivity.class);
		i.putExtra("bah",pat.getBah());
		startActivity(i);
  }
	    
	@OnClick(R.id.btn_head_back)
	public void goBack(View v){
		finish();
	}
}