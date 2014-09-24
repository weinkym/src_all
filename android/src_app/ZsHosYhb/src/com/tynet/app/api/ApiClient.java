package com.tynet.app.api;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.tynet.app.AppException;
import com.tynet.app.BookPlatApplication;
import com.tynet.app.bean.Base;
import com.tynet.app.bean.BqList;
import com.tynet.app.bean.EmployeesService;
import com.tynet.app.bean.JcBChaoInfo;
import com.tynet.app.bean.JcBChaoList;
import com.tynet.app.bean.JcBingLiInfo;
import com.tynet.app.bean.JcBingLiList;
import com.tynet.app.bean.JcFangSheInfo;
import com.tynet.app.bean.JcFangSheList;
import com.tynet.app.bean.JcNeiJing;
import com.tynet.app.bean.JcNeiJingInfo;
import com.tynet.app.bean.JcNeiJingList;
import com.tynet.app.bean.JcXinDianInfo;
import com.tynet.app.bean.JcXinDianList;
import com.tynet.app.bean.JybgInfoList;
import com.tynet.app.bean.JybgList;
import com.tynet.app.bean.LsYzList;
import com.tynet.app.bean.LsYzxq;
import com.tynet.app.bean.PatList;
import com.tynet.app.bean.TongXunLuInfo;
import com.tynet.app.bean.TongXunLuList;
import com.tynet.app.bean.YzList;
import com.tynet.app.bean.Yzxq;
import com.tynet.app.common.DataExchangeUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ApiClient {

	public static final String UTF_8 = "UTF-8";
	public static final String DESC = "descend";
	public static final String ASC = "ascend";
	
	private final static int TIMEOUT_CONNECTION = 20000;
	private final static int TIMEOUT_SOCKET = 20000;
	private final static int RETRY_TIME = 3;

	private static String appCookie;
	private static String appUserAgent;

	public static void cleanCookie() {
		appCookie = "";
	}
	
	private static String getCookie(BookPlatApplication appContext) {
		if(appCookie == null || appCookie == "") {
			appCookie = appContext.getProperty("cookie");
		}
		return appCookie;
	}
	
	private static String getUserAgent(BookPlatApplication appContext) {
		if(appUserAgent == null || appUserAgent == "") {
			StringBuilder ua = new StringBuilder("HomeComing.NET");
			ua.append('/'+appContext.getPackageInfo().versionName+'_'+appContext.getPackageInfo().versionCode);//App版本
			ua.append("/Android");//手机系统平台
			ua.append("/"+android.os.Build.VERSION.RELEASE);//手机系统版本
			ua.append("/"+android.os.Build.MODEL); //手机型号
			ua.append("/"+appContext.getAppId());//客户端唯一标识
			appUserAgent = ua.toString();
		}
		return appUserAgent;
	}
	
	/**
	 * 检查版本更新
	 * @param url
	 * @return
	 * @throws JSONException 
	 */
/*	public static Update checkVersion(AppContext appContext) throws AppException {
		try{
			return Update.parse(http_get(appContext, URLs.UPDATE_VERSION));		
		}catch(Exception e){
			if(e instanceof AppException)
				throw (AppException)e;
			throw AppException.network(e);
		}
	}*/
	
	
	public static EmployeesService loginCheck(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return EmployeesService.parse(DataExchangeUtil.getDataByMT2("employeesService", "Login", parmJSON));
	}
	
	public static Base sendLoginCode(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return Base.parse(DataExchangeUtil.getDataByMT2("employeesService", "SendLoginCode", parmJSON));
	}
	
	public static Base loginCode(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return Base.parse(DataExchangeUtil.getDataByMT2("employeesService", "ChkLoginCode", parmJSON));
	}
	
	public static BqList getBqList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return BqList.parse(DataExchangeUtil.getDataByMT2("employeesService", "QueryBq", parmJSON));
	}
	
	public static PatList getPatList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return PatList.parse(DataExchangeUtil.getDataByMT2("employeesService", "QueryPat", parmJSON));
	}
	
	public static YzList getYzList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return YzList.parse(DataExchangeUtil.getDataByMT2("employeesService", "QueryCqyz", parmJSON));
	}
	
	public static Yzxq getYzxq(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return Yzxq.parse(DataExchangeUtil.getDataByMT2("employeesService", "CqyzInfo", parmJSON));
	}
	
	public static LsYzList getLsYzList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return LsYzList.parse(DataExchangeUtil.getDataByMT2("employeesService", "QueryLsyz", parmJSON));
	}
	
	public static LsYzxq getLsYzxq(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return LsYzxq.parse(DataExchangeUtil.getDataByMT2("employeesService", "LsyzInfo", parmJSON));
	}
	
//	public static YzList getYzList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
//		return YzList.parse(DataExchangeUtil.getDataByMT2("employeesService", "QueryLsyz", parmJSON));
//	}
	//==========================20140813getJcBChaoList
	public static JybgList getJybgList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JybgList.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyJyList", parmJSON));
	}
	public static JcBChaoList getJcBChaoList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JcBChaoList.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyBcList", parmJSON));
	}
	public static JcFangSheList getJcFangSheList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JcFangSheList.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyFsList", parmJSON));
	}
	public static JcNeiJingList getJcNeiJingList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JcNeiJingList.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyNjList", parmJSON));
	}
	public static JcXinDianList getJcXinDianList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JcXinDianList.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyXdList", parmJSON));
	}
	public static JcBingLiList getJcBingLiList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JcBingLiList.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyBlList", parmJSON));
	}
	//===通讯录列表
	public static TongXunLuList getTongXunLuList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return TongXunLuList.parse(DataExchangeUtil.getDataByMT2("employeesService", "Contactlist", parmJSON));
	}
	//===通讯录成员详细
	public static TongXunLuInfo getTongXunLuInfo(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return TongXunLuInfo.parse(DataExchangeUtil.getDataByMT2("employeesService", "ContactInfo", parmJSON));
	}
	
	//INFOListJ
	public static JybgInfoList getJybgInfoList(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JybgInfoList.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyJyInfo", parmJSON));
	}
	//=============INFO
	public static JcBChaoInfo getJcBChaoInfo(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JcBChaoInfo.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyBcInfo", parmJSON));
	}
	public static JcFangSheInfo getJcFangSheInfo(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JcFangSheInfo.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyFsInfo", parmJSON));
	}
	public static JcNeiJingInfo getJcNeiJingInfo(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JcNeiJingInfo.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyNjInfo", parmJSON));
	}
	public static JcXinDianInfo getJcXinDianInfo(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JcXinDianInfo.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyXdInfo", parmJSON));
	}
	public static JcBingLiInfo getJcBingLiInfo(BookPlatApplication appContext, JSONObject parmJSON) throws JSONException  {
		return JcBingLiInfo.parse(DataExchangeUtil.getDataByMT2("employeesService", "ZyBlInfo", parmJSON));
	}
}
