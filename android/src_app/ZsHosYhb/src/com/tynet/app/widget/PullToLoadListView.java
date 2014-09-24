/*package com.tynet.app.widget;

import java.text.SimpleDateFormat;
import java.util.Timer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PullToLoadListView extends LinearLayout implements OnScrollOverListener{
	private static final String TAG = "PullDownView";       
	private static final int START_PULL_DEVIATION = 50; // 移动误差     
	private static final int AUTO_INCREMENTAL = 10;     // 自增量，用于回弹       
	private static final int WHAT_DID_LOAD_DATA = 1;    // Handler what 数据加载完毕     
	private static final int WHAT_ON_REFRESH = 2;       // Handler what 刷新中     
	private static final int WHAT_DID_REFRESH = 3;      // Handler what 已经刷新完     
	private static final int WHAT_SET_HEADER_HEIGHT = 4;// Handler what 设置高度     
	private static final int WHAT_DID_MORE = 5;         // Handler what 已经获取完更多   
	
	private static final int DEFAULT_HEADER_VIEW_HEIGHT = 105;  // 头部文件原本的高度       
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");       
	private View mHeaderView;     
	private LayoutParams mHeaderViewParams;     
	private TextView mHeaderViewDateView;     
	private TextView mHeaderTextView;     
	private ImageView mHeaderArrowView;     
	private View mHeaderLoadingView;     
	private View mFooterView;     
	private TextView mFooterTextView;     
	private View mFooterLoadingView;  
	private ScrollOverListView mListView;  
	
	private OnPullDownListener mOnPullDownListener;     
	private RotateAnimation mRotateOTo180Animation;     
	private RotateAnimation mRotate180To0Animation;
	
	private int mHeaderIncremental; // 增量     
	private float mMotionDownLastY; // 按下时候的Y轴坐标       
	private boolean mIsDown;            // 是否按下     
	private boolean mIsRefreshing;      // 是否下拉刷新中     
	private boolean mIsFetchMoreing;    // 是否获取更多中     
	private boolean mIsPullUpDone;      // 是否回推完成     
	private boolean mEnableAutoFetchMore;   // 是否允许自动获取更多       
	// 头部文件的状态     
	private static final int HEADER_VIEW_STATE_IDLE = 0;            // 空闲     
	private static final int HEADER_VIEW_STATE_NOT_OVER_HEIGHT = 1; // 没有超过默认高度     
	private static final int HEADER_VIEW_STATE_OVER_HEIGHT = 2;     // 超过默认高度     
	private int mHeaderViewState = HEADER_VIEW_STATE_IDLE; 
	public PullToLoadListView(Context context,AttributeSet attrs) {         
		super(context, attrs);         
		initHeaderViewAndFooterViewAndListView(context);     
		}
	public PullToLoadListView(Context context) {         
		super(context);         
		initHeaderViewAndFooterViewAndListView(context);      
	
		// TODO Auto-generated constructor stub
	}

     public boolean onListViewBottomAndPullUp(int delta) {         
    	 if(!mEnableAutoFetchMore || mIsFetchMoreing) 
    		 return false;         
    	 // 数量充满屏幕才触发         
    	 if(isFillScreenItem()){             
    		 mIsFetchMoreing = true;             
    		 mFooterTextView.setText("加载更多中...");             
    		 mFooterLoadingView.setVisibility(View.VISIBLE);             
    		 mOnPullDownListener.onMore();             
    		 return true;         
    		 }         
    	 return false;     
    	 } 
     
     public boolean onMotionDown(MotionEvent ev) {         
    	 mIsDown = true;         
    	 mIsPullUpDone = false;         
    	 mMotionDownLastY = ev.getRawY();         
    	 return false;     
    	 }
     
     public boolean onMotionMove(MotionEvent ev, int delta) { 
    	//当头部文件回推消失的时候，不允许滚动
    	 if(mIsPullUpDone) return true;   
    	// 如果开始按下到滑动距离不超过误差值，则不滑动 
    	 final int absMotionY = (int) Math.abs(ev.getRawY() - mMotionDownLastY); 
    	 if(absMotionY < START_PULL_DEVIATION) return true; 
    	 final int absDelta = Math.abs(delta); 
    	 final int i = (int) Math.ceil((double)absDelta / 2); 
    	 
    	// onTopDown在顶部，并上回推和onTopUp相对         
    	 if(mHeaderViewParams.height > 0 && delta < 0){             
    		 mHeaderIncremental -= i;             
    		 if(mHeaderIncremental > 0){                 
    			 setHeaderHeight(mHeaderIncremental);                 
    			 checkHeaderViewState();             
    			 }else{                 
    				 mHeaderViewState = HEADER_VIEW_STATE_IDLE;                 
    				 mHeaderIncremental = 0;                 
    				 setHeaderHeight(mHeaderIncremental);                 
    				 mIsPullUpDone = true;
    			 }             
    		 return true;
    	 }         
    	 return false; 
    }
         public boolean onMotionUp(MotionEvent ev) {         
        	 mIsDown = false;         // 避免和点击事件冲突 
        	 if(mHeaderViewParams.height > 0){ 
        		// 判断头文件拉动的距离与设定的高度，小了就隐藏，多了就固定高度            
             int x = mHeaderIncremental - DEFAULT_HEADER_VIEW_HEIGHT;             
             Timer timer = new Timer(true);
             if(x < 0){                
            	 timer.scheduleAtFixedRate(new HideHeaderViewTask(), 0, 10);             
            	 }else{                 
            		 timer.scheduleAtFixedRate(new ShowHeaderViewTask(), 0, 10);             
            		 }
             return true;         
             }         
        	 return false;
        	 }
         }


*/