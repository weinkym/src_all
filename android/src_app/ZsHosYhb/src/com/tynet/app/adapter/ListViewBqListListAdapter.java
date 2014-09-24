package com.tynet.app.adapter;

import java.util.ArrayList;
import java.util.List;


import com.tynet.app.R;


import com.tynet.app.bean.Bq;
import com.tynet.app.bean.PatList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * 
  * @Description: 
  * @author bijy
  * @date 2013-8-14 上午10:52:00
 */
public class ListViewBqListListAdapter extends BaseAdapter {
	
	private Context 					context;//运行上下文
	private List<Bq>					listItems;//数据集合
	private LayoutInflater 				listContainer;//视图容器
	private int 						itemViewResource;//自定义项视图源
	static class ListItemView{				//自定义控件集合  
		    
		    public TextView name;
		  


	 }  

	/**
	 * 实例化Adapter
	 * @param context
	 * @param data
	 * @param resource
	 */
	public ListViewBqListListAdapter(Context context, List<Bq> data,int resource) {
		this.context = context;			
		this.listContainer = LayoutInflater.from(context);	//创建视图容器并设置上下文
		this.itemViewResource = resource;
		this.listItems = data;
	
	}
	
	public int getCount() {
		return listItems.size();
	}

	public Object getItem(int arg0) {
		return listItems.get(arg0);
	}

	public long getItemId(int arg0) {
		return arg0;
	}
	
	/**
	 * ListView Item设置
	 */
	public View getView(final int position, View convertView, ViewGroup parent) {
		//Log.d("method", "getView");
		
		//自定义视图
		ListItemView  listItemView = null;
		
		if (convertView == null) {
			//获取list_item布局文件的视图
			convertView = listContainer.inflate(this.itemViewResource, parent,false);
			
			listItemView = new ListItemView();
			//获取控件对象
			
			listItemView.name =  (TextView)convertView.findViewById(R.id.bq_tv);
			
			
			//设置控件集到convertView
			convertView.setTag(listItemView);
		}else {
			listItemView = (ListItemView)convertView.getTag();
		}	

		//设置文字和图片
		Bq patlist = listItems.get(position);
		

       
		listItemView.name.setText(patlist.getBqmc());
	
		//listItemView.scoreBtn ;
		return convertView;
		
	}
	

}