package com.tynet.app.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.soft.task.GenericTask;
import com.tynet.app.BookPlatApplication;
import com.tynet.app.R;

import android.app.Activity;
import android.app.ActivityGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.content.Intent;

public class JcbgListActivity extends ActivityGroup {
	private GenericTask mInitTask;
	private BookPlatApplication appContext;
	private String bah;
	
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	@ViewInject(R.id.btn_more)
	Button btnMore;
	
	@ViewInject(R.id.fl_titlebar)
	FrameLayout frameLayout;
	
	private TabHost m_tabHost = null; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_jcbg_list);
		ViewUtils.inject(this);
		
		btnMore.setVisibility(View.VISIBLE);
		frameLayout.setBackgroundColor(this.getResources().getColor(R.color.head_bg_green));
		headTitle.setText(R.string.jcbgjl);
		
		Intent i = getIntent();
		bah = i.getStringExtra("bah");
		System.out.println("bah = " + bah);
		
		m_tabHost = (TabHost) findViewById(R.id.tabhost);
		m_tabHost.setup(); // Call setup() before adding tabs if loading TabHost
							// using findViewById().
		m_tabHost.setup(this.getLocalActivityManager());  
		
		View view_bingli = (View) LayoutInflater.from(this).inflate(R.layout.tabmini, null);  
		TextView text_bingli = (TextView) view_bingli.findViewById(R.id.tab_label);  
		text_bingli.setText(R.string.bingli);		
		Intent intent_bingli = new Intent();
		intent_bingli.putExtra("bah",bah);
		intent_bingli.setClass(this, JcBingLiListActivity.class);		
		m_tabHost.addTab(m_tabHost.newTabSpec("bingli").setIndicator(view_bingli)				
				.setContent(intent_bingli));
		//=========================================================
		View view_fangshe = (View) LayoutInflater.from(this).inflate(R.layout.tabmini, null);  
		TextView text_fangshe = (TextView) view_fangshe.findViewById(R.id.tab_label);  
		text_fangshe.setText(R.string.fangshe);		
		Intent intent_fangshe = new Intent();
		intent_fangshe.putExtra("bah",bah);
		intent_fangshe.setClass(this, JcFangSheListActivity.class);		
		m_tabHost.addTab(m_tabHost.newTabSpec("fangshe").setIndicator(view_fangshe)
				.setContent(intent_fangshe));
		
		View view_bchao = (View) LayoutInflater.from(this).inflate(R.layout.tabmini, null);  
		TextView text_bchao = (TextView) view_bchao.findViewById(R.id.tab_label);  
		text_bchao.setText(R.string.bchao);		
		Intent intent_bchao = new Intent();
		intent_bchao.putExtra("bah",bah);
		intent_bchao.setClass(this, JcBChaoListActivity.class);		
		m_tabHost.addTab(m_tabHost.newTabSpec("bchao").setIndicator(view_bchao)
				.setContent(intent_bchao));
		
		View view_neijing = (View) LayoutInflater.from(this).inflate(R.layout.tabmini, null);  
		TextView text_neijing = (TextView) view_neijing.findViewById(R.id.tab_label);  
		text_neijing.setText(R.string.neijing);		
		Intent intent_neijing = new Intent();
		intent_neijing.putExtra("bah",bah);
		intent_neijing.setClass(this, JcNeiJingListActivity.class);		
		m_tabHost.addTab(m_tabHost.newTabSpec("neijing").setIndicator(view_neijing)
				.setContent(intent_neijing));
		
		View view_xindian = (View) LayoutInflater.from(this).inflate(R.layout.tabmini, null);  
		TextView text_xindian = (TextView) view_xindian.findViewById(R.id.tab_label);  
		text_xindian.setText(R.string.xindian);		
		Intent intent_xindian = new Intent();
		intent_xindian.putExtra("bah",bah);
		intent_xindian.setClass(this, JcXinDianListActivity.class);		
		m_tabHost.addTab(m_tabHost.newTabSpec("xindian").setIndicator(view_xindian)
				.setContent(intent_xindian));
		
//		m_tabHost.setCurrentTabByTag("fangshe");
		m_tabHost.setOnTabChangedListener(new OnTabChangedListener()); 
		m_tabHost.setCurrentTab(0);
		updateTab(m_tabHost);//
}
	
	@OnClick(R.id.btn_more)
    public void startJybg(View v){
    	Intent i = new Intent();
    	i.setClass(this, JybgListActivity.class);
    	i.putExtra("bah",bah);
    	startActivity(i);
    }
	
	class OnTabChangedListener implements OnTabChangeListener{ 
		  
		  
        @Override 
        public void onTabChanged(String tabId) { 
            m_tabHost.setCurrentTabByTag(tabId); 
            updateTab(m_tabHost); 
        } 
    }
	private void updateTab(final TabHost tabHost) { 
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) { 
            View view = tabHost.getTabWidget().getChildAt(i); 
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tab_label); 
//            tv.setTextSize(16); 
            if (tabHost.getCurrentTab() == i) {//选中  
//                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.category_current));//选中后的背景  
                tv.setTextColor(this.getResources().getColorStateList( 
                        R.color.text_green)); 
            }
            else
            {//不选中  
//                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.category_bg));//非选择的背景  
                tv.setTextColor(this.getResources().getColorStateList( 
                        R.color.default_text)); 
            } 
        } 
    } 
	@OnClick(R.id.btn_head_back)
	public void goBack(View v){
		finish();
	}
}
