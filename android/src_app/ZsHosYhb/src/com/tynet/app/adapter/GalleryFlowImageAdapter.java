package com.tynet.app.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView.ScaleType;

/**
 * 
  * @Description: 联系人Adapter类
  * @author bijy
  * @date 2013-8-14 上午10:52:00
 */
public class GalleryFlowImageAdapter extends BaseAdapter {
	
	private Context 					context;//运行上下文

	private ArrayList<Drawable> imgList;
	
    private static class ViewHolder
	{
		ImageView imageView;
	}

	/**
	 * 实例化Adapter
	 * @param context
	 * @param data
	 * @param resource
	 */
	public GalleryFlowImageAdapter(Context context,ArrayList<Drawable> imgList) {
		this.context = context;
		this.imgList = imgList;

	}
	
	public int getCount() {
	    return Integer.MAX_VALUE;
	}

	public Object getItem(int position) {

	    return imgList.get(position%imgList.size());
	}

	public long getItemId(int position) {
		 return position % imgList.size();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null)
		{
			viewHolder = new ViewHolder();
			ImageView imageView = new ImageView(context);
		    imageView.setAdjustViewBounds(true);
		    imageView.setScaleType(ScaleType.FIT_XY);
		    imageView.setLayoutParams(new Gallery.LayoutParams(
			    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		    convertView = imageView;
		    viewHolder.imageView = (ImageView)convertView; 
		    convertView.setTag(viewHolder);
			
		}
		else
		{
			viewHolder = (ViewHolder)convertView.getTag();
		}
	    viewHolder.imageView.setImageDrawable(imgList.get(position%imgList.size()));
	    
	    return convertView;
	}
}