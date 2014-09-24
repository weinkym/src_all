package com.tynet.app.ui;

import java.util.ArrayList;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


import com.tynet.app.BookPlatApplication;
import com.tynet.app.R;
import com.tynet.app.adapter.GalleryFlowImageAdapter;
import com.tynet.app.widget.GalleryFlow;





import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

@ContentView(R.layout.layout_main)
public class Main extends Activity {
	@ViewInject(R.id.tv_head_title)
	TextView headTitle;
	private BookPlatApplication appContext;// 全局Context
	public ArrayList<Drawable> imgList = new ArrayList<Drawable>();
	private int preSelImgIndex = 0;
	@ViewInject(R.id.galleryFlow)
	GalleryFlow galleryFlow;
	@ViewInject(R.id.viewGroup)
	LinearLayout viewGroup;
	@ViewInject(R.id.btn_head_back)
	Button headBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		appContext = (BookPlatApplication) getApplication();
        //是否显示登录信息
        init();
	}
	private void init(){
		headTitle.setText(getString(R.string.app_name));
		headBack.setVisibility(View.GONE);
		//滚动广告
		//初始化图片list
		InitImgList();
		//初始化小圆点
		InitFocusIndicatorContainer();
		
		galleryFlow.setAdapter(new GalleryFlowImageAdapter(this,imgList));
		galleryFlow.setFocusable(true);
		
		galleryFlow.setOnItemSelectedListener(new OnItemSelectedListener() {

		    public void onItemSelected(AdapterView<?> arg0, View arg1,
			    int selIndex, long arg3) {
				if (galleryFlow.getSelectedItemPosition() == 0) {// 实现后退功能
					galleryFlow.setSelection(imgList.size());
				}
			//修改上一次选中项的背景
		    	selIndex = selIndex % imgList.size();
		    	
			ImageView preSelImg = (ImageView) viewGroup
			.findViewById(preSelImgIndex);
		preSelImg.setImageDrawable(Main.this
			.getResources().getDrawable(R.drawable.page_indicator));
			//修改当前选中项的背景
			ImageView curSelImg = (ImageView) viewGroup
				.findViewById(selIndex);
			curSelImg
				.setImageDrawable(Main.this
					.getResources().getDrawable(
						R.drawable.page_indicator_focused));
			preSelImgIndex = selIndex;
		    }

		    public void onNothingSelected(AdapterView<?> arg0) {
		    }
		});
		
	}
	
	@OnClick(R.id.hos_patient_lay)
	public void patient(View v){
		startActivity(new Intent(this,Patient.class));
	}
	
	@OnClick(R.id.post_notice_lay)
	public void notice(View v){
		startActivity(new Intent(this,Notice.class));
	}
	
	@OnClick(R.id.doc_notice_lay)
	public void docNotice(View v){
		startActivity(new Intent(this,DocAdvice.class));
	}
	
	@OnClick(R.id.address_list_lay)
	public void doTongXunLu(View v){
		startActivity(new Intent(this,TongXunLuListActivity.class));
	}
	
    private void InitFocusIndicatorContainer() {
	for (int i = 0; i < imgList.size(); i++) {
	    ImageView localImageView = new ImageView(this);
	    localImageView.setId(i);
	    ImageView.ScaleType localScaleType = ImageView.ScaleType.FIT_XY;
	    localImageView.setScaleType(localScaleType);
	    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(
		    24, 24);
	    localImageView.setLayoutParams(localLayoutParams);
	    localImageView.setPadding(5, 5, 5, 5);
	    localImageView.setImageResource(R.drawable.page_indicator);
	    this.viewGroup.addView(localImageView);
	}
    }
	
    private void InitImgList() {
	// 加载图片数据（本demo仅获取本地资源，实际应用中，可异步加载网络数据）
	imgList.add(this.getResources().getDrawable(R.drawable.change));
	imgList.add(this.getResources().getDrawable(R.drawable.change));
	imgList.add(this.getResources().getDrawable(R.drawable.change));
	imgList.add(this.getResources().getDrawable(R.drawable.change));
	imgList.add(this.getResources().getDrawable(R.drawable.change));
	imgList.add(this.getResources().getDrawable(R.drawable.change));
    }
    
   
}
