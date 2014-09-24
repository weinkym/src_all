package com.tynet.app.adapter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tynet.app.R;
import com.tynet.app.adapter.ListViewPatListListAdapter.ListItemView;
import com.tynet.app.bean.GbPat;
import com.tynet.app.bean.JcBChao;
import com.tynet.app.common.StringUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewJcBChaoAdapter extends BaseAdapter{
	private Context 					context;//运行上下文
	private List<JcBChao>					listItems;//数据集合
	private LayoutInflater 				listContainer;//视图容器
	private int 						itemViewResource;//自定义项视图源
	static class ListItemView{				//自定义控件集合  
		    
		    public TextView pro_name;
		    public TextView hospital_name;
		    public TextView gather_time;
		    public TextView report_time;
	 } 
	public ListViewJcBChaoAdapter(Context context, List<JcBChao> data,int resource) {
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
			listItemView.pro_name =  (TextView)convertView.findViewById(R.id.pro_name);
			listItemView.hospital_name = (TextView)convertView.findViewById(R.id.hospital_name);
			listItemView.gather_time = (TextView)convertView.findViewById(R.id.gather_time);
			listItemView.report_time = (TextView)convertView.findViewById(R.id.report_time);
			//设置控件集到convertView
			convertView.setTag(listItemView);
		}else {
			listItemView = (ListItemView)convertView.getTag();
		}	

		//设置文字和图片
		JcBChao itemList = listItems.get(position);       
		String jcxm = itemList.getJcxm();
		jcxm = replaceBlank(jcxm);//有空格、换行
//		System.out.println("TTTTTTTTTT<6666>" + jcxm);
		listItemView.pro_name.setText(jcxm);
//		listItemView.hospital_name.setText(patlist.getBah());
		listItemView.gather_time.setText("");
		listItemView.report_time.setText(StringUtils.toDate2(itemList.getBgsj()));
		return convertView;
	}
	
	public static String replaceBlank(String str) {  
        String dest = "";  
        if (str!=null) {  
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
                Matcher m = p.matcher(str);  
                dest = m.replaceAll("");  
        }  
        return dest;  
}
}
