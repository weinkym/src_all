package com.tynet.app;
import com.tynet.app.common.StringUtils;
import com.tynet.app.ui.*;///TTTTTTTTTTTT测试

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.tynet.app.ui.Login;
import com.tynet.app.ui.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * 应用程序启动类：显示欢迎界面并跳转到主界面
 * @author 
 * @version 1.0
 * @created 
 */
public class AppStart extends Activity {
	

	boolean isLaucher;
	boolean isFinish=false;
	public HashMap<Integer, String[]> map = new HashMap<Integer,String[]>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.layout_start, null);
		setContentView(view);
		
		
		//渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
		aa.setDuration(3000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationStart(Animation animation) {}
			
		});
		
		//兼容低版本cookie（1.5版本以下，包括1.5.0,1.5.1）
/*		AppContext appContext = (AppContext)getApplication();
		String cookie = appContext.getProperty("cookie");
		if(StringUtils.isEmpty(cookie)) {
			String cookie_name = appContext.getProperty("cookie_name");
			String cookie_value = appContext.getProperty("cookie_value");
			if(!StringUtils.isEmpty(cookie_name) && !StringUtils.isEmpty(cookie_value)) {
				cookie = cookie_name + "=" + cookie_value;
				appContext.setProperty("cookie", cookie);
				appContext.removeProperty("cookie_domain","cookie_name","cookie_value","cookie_version","cookie_path");
			}
		}*/
    }
    
  
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
    	// TODO Auto-generated method stub\
    	if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
    		if (event.getAction() == KeyEvent.ACTION_DOWN
    				&& event.getRepeatCount() == 0) {
    			isFinish=true;
    			finish();
    			return true;
    		}
    	}
    	return super.dispatchKeyEvent(event);
    }
    
    /**
     * 跳转到...
     */
    private void redirectTo(){        
        Intent intent = new Intent(this, Login.class);
//    	Intent intent = new Intent(this, JcBingLiListActivity.class);///TTTTTTTTTTTT测试
//    	Intent intent = new Intent(this, GeRenInfoActivity.class);///TTTTTTTTTTTT测试
    	startActivity(intent);        
        finish();
    }
}
