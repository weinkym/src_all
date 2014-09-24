package com.tynet.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Properties;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.util.LogUtils;
import com.tynet.app.api.ApiClient;
import com.tynet.app.bean.Base;
import com.tynet.app.bean.BqList;
import com.tynet.app.bean.EmployeesService;
import com.tynet.app.bean.JcBChaoInfo;
import com.tynet.app.bean.JcBChaoList;
import com.tynet.app.bean.JcBingLiInfo;
import com.tynet.app.bean.JcBingLiList;
import com.tynet.app.bean.JcFangSheInfo;
import com.tynet.app.bean.JcFangSheList;
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




import com.tynet.app.common.MethodsCompat;
import com.tynet.app.common.StringUtils;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.CacheManager;

public class BookPlatApplication extends Application {
    public static Resources res;
    public static String token = "";
	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;
	
	public static final int PAGE_SIZE = 20;//默认分页大小
	private static final int CACHE_TIME = 60*60000;//缓存失效时间
	
	private boolean login = false;	//登录状态
	private long loginUid = 0;	//登录用户的id
	private Hashtable<String, Object> memCacheRegion = new Hashtable<String, Object>();
	
	private boolean isClientStart;// 客户端连接是否启动
	private NotificationManager mNotificationManager;
	
	private String saveImagePath;//保存图片路径
	
    public boolean m_bKeyRight = true;
    //public BMapManager mBMapManager = null;

    public static final String strKey = "fW8gkrzQq52h0brdjzNNkQc5";
    
    
    @Override
    public void onCreate() {
    	// TODO Auto-generated method stub
    	super.onCreate();
    }
    	
	/**
	 * 检测网络是否可用
	 * @return
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * 获取当前网络类型
	 * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
	 */
	public int getNetworkType() {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}		
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			String extraInfo = networkInfo.getExtraInfo();
			if(!StringUtils.isEmpty(extraInfo)){
				if (extraInfo.toLowerCase().equals("cmnet")) {
					netType = NETTYPE_CMNET;
				} else {
					netType = NETTYPE_CMWAP;
				}
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = NETTYPE_WIFI;
		}
		return netType;
	}
	
	/**
	 * 判断当前版本是否兼容目标版本的方法
	 * @param VersionCode
	 * @return
	 */
	public static boolean isMethodsCompat(int VersionCode) {
		int currentVersion = android.os.Build.VERSION.SDK_INT;
		return currentVersion >= VersionCode;
	}
	
	/**
	 * 获取App安装包信息
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try { 
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {    
			e.printStackTrace(System.err);
		} 
		if(info == null) info = new PackageInfo();
		return info;
	}
	
	/**
	 * 获取App唯一标识
	 * @return
	 */
	public String getAppId() {
		String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
		if(StringUtils.isEmpty(uniqueID)){
			uniqueID = UUID.randomUUID().toString();
			setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
		}
		return uniqueID;
	}
	
	/**
	 * 用户是否登录
	 * @return
	 */
	public boolean isLogin() {
		if(StringUtils.toLong(getProperty("user.patientId"))!=0l){
			login=true;
			this.token = getProperty("user.token");
		}
		return login;
	}
	
	/**
	 * 获取登录用户id
	 * @return
	 */
	public long getLoginUid() {
		return this.loginUid;
	}
	
	/**
	 * 用户注销
	 */
	public void Logout() {
		//ApiClient.cleanCookie();
		this.cleanCookie();
		this.cleanLoginInfo();
		this.login = false;
		this.loginUid = 0;
	}
	

	
	/**
	 * 初始化用户登录信息
	 */
	public void initLoginInfo() {
//		TPatient loginUser = getLoginInfo();
//		if(loginUser!=null && loginUser.getPatientId()>0 ){
//			this.loginUid = loginUser.getPatientId();
//			this.login = true;
//		}else{
//			this.Logout();
//		}
	}
	
	
	
	public void changeSearchType(String value){
		setProperty("searchType", value);
	}
	
	public String getSearhType(){
		String searchType = getProperty("searchType");
		return searchType==null?"0":searchType;
	}
	
	/**
	 * 保存登录信息
	 * @param username
	 * @param pwd
	 */
	public void saveLoginInfo(final String token) {
		this.login = true;
		setProperties(new Properties(){{
			setProperty("token",token);
		}});	
	}
	
	/**
	 * 清除登录信息
	 */
	public void cleanLoginInfo() {

		removeProperty("token");
	}
	
	/**
	 * 获取登录信息
	 * @return
	 */
	public String getLoginInfo() {		

		return getProperty("token");
		//return "a0c37d30-38f6-4f1c-866e-2892a6d6f975";
	}
	
	
	/**
	 * 用户登录验证
	 * @param account
	 * @param pwd
	 * @return
	 * @throws JSONException 
	 * @throws AppException
	 */
//	public TPatient loginVerify(JSONObject paramJSON) throws JSONException{
//		return ApiClient.loginCheck(this, paramJSON);
//	}
	
	public EmployeesService loginVerify(JSONObject paramJSON) throws JSONException{
		return ApiClient.loginCheck(this, paramJSON);
	}
	
	public Base sendLoginCode(JSONObject paramJSON) throws JSONException{
		return ApiClient.sendLoginCode(this, paramJSON);
	}
	
	public Base chkLoginCode(JSONObject paramJSON) throws JSONException{
		return ApiClient.loginCode(this, paramJSON);
	}
	
	public BqList getBqList(JSONObject paramJSON) throws JSONException{
		BqList hos=null;
		String key = "bqList"+"_"+paramJSON.getString("hosId");
		if(isNetworkConnected() && !isReadDataCache(key)) {
			hos = ApiClient.getBqList(this, paramJSON);
			if(hos != null ){
				hos.setCacheKey(key);
				saveObject(hos, key);
			}		
		} else {
			hos = (BqList)readObject(key);
			if(hos == null)
				hos = new BqList();
		}
		return hos;
	}
	
	public PatList getPatList(JSONObject paramJSON) throws JSONException{
		return ApiClient.getPatList(this, paramJSON);
	}
	
	public YzList getYzList(JSONObject paramJSON) throws JSONException{
		return ApiClient.getYzList(this, paramJSON);
	}
	
	public Yzxq getYzxq(JSONObject paramJSON) throws JSONException{
		return ApiClient.getYzxq(this, paramJSON);
	}
	
	public LsYzList getLsYzList(JSONObject paramJSON) throws JSONException{
		return ApiClient.getLsYzList(this, paramJSON);
	}
	
	public LsYzxq getLsYzxq(JSONObject paramJSON) throws JSONException{
		return ApiClient.getLsYzxq(this, paramJSON);
	}
	//=======================20140813getJybgList
	public JybgList getJybgList(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJybgList(this, paramJSON);
	}
	public JcBChaoList getJcBChaoList(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJcBChaoList(this, paramJSON);
	}
	public JcFangSheList getJcFangSheList(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJcFangSheList(this, paramJSON);
	}	
	public JcNeiJingList getJcNeiJingList(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJcNeiJingList(this, paramJSON);
	}
	public JcXinDianList getJcXinDianList(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJcXinDianList(this, paramJSON);
	}
	
	public JcBingLiList getJcBingLiList(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJcBingLiList(this, paramJSON);
	}
	
	//===通讯录列表
	public TongXunLuList getTongXunLuList(JSONObject paramJSON) throws JSONException{
		return ApiClient.getTongXunLuList(this, paramJSON);
	}
	//通讯录成员详细
	public TongXunLuInfo getTongXunLuInfo(JSONObject paramJSON) throws JSONException{
		return ApiClient.getTongXunLuInfo(this, paramJSON);
	}
	
	//====INFOList
	public JybgInfoList getJybgInfoList(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJybgInfoList(this, paramJSON);
	}
	//====INFO
	public JcBChaoInfo getJcBChaoInfo(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJcBChaoInfo(this, paramJSON);
	}
	public JcFangSheInfo getJcFangSheInfo(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJcFangSheInfo(this, paramJSON);
	}	
	public JcNeiJingInfo getJcNeiJingInfo(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJcNeiJingInfo(this, paramJSON);
	}
	
	public JcXinDianInfo getJcXinDianInfo(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJcXinDianInfo(this, paramJSON);
	}
	
	public JcBingLiInfo getJcBingLiInfo(JSONObject paramJSON) throws JSONException{
		return ApiClient.getJcBingLiInfo(this, paramJSON);
	}
	/**
	 * 是否加载显示文章图片
	 * @return
	 */
	public boolean isLoadImage()
	{
		String perf_loadimage = getProperty(AppConfig.CONF_LOAD_IMAGE);
		//默认是加载的
		if(StringUtils.isEmpty(perf_loadimage))
			return true;
		else
			return StringUtils.toBool(perf_loadimage);
	}
	
	/**
	 * 设置是否加载文章图片
	 * @param b
	 */
	public void setConfigLoadimage(boolean b)
	{
		setProperty(AppConfig.CONF_LOAD_IMAGE, String.valueOf(b));
	}
	
	/**
	 * 是否发出提示音
	 * @return
	 */
	public boolean isVoice()
	{
		String perf_voice = getProperty(AppConfig.CONF_VOICE);
		//默认是开启提示声音
		if(StringUtils.isEmpty(perf_voice))
			return true;
		else
			return StringUtils.toBool(perf_voice);
	}
	
	/**
	 * 设置是否发出提示音
	 * @param b
	 */
	public void setConfigVoice(boolean b)
	{
		setProperty(AppConfig.CONF_VOICE, String.valueOf(b));
	}
	
	/**
	 * 是否启动检查更新
	 * @return
	 */
	public boolean isCheckUp()
	{
		String perf_checkup = getProperty(AppConfig.CONF_CHECKUP);
		//默认是开启
		if(StringUtils.isEmpty(perf_checkup))
			return true;
		else
			return StringUtils.toBool(perf_checkup);
	}
	
	/**
	 * 设置启动检查更新
	 * @param b
	 */
	public void setConfigCheckUp(boolean b)
	{
		setProperty(AppConfig.CONF_CHECKUP, String.valueOf(b));
	}
	
	/**
	 * 是否左右滑动
	 * @return
	 */
	public boolean isScroll()
	{
		String perf_scroll = getProperty(AppConfig.CONF_SCROLL);
		//默认是关闭左右滑动
		if(StringUtils.isEmpty(perf_scroll))
			return false;
		else
			return StringUtils.toBool(perf_scroll);
	}
	
	/**
	 * 设置是否左右滑动
	 * @param b
	 */
	public void setConfigScroll(boolean b)
	{
		setProperty(AppConfig.CONF_SCROLL, String.valueOf(b));
	}
	
	/**
	 * 是否Https登录
	 * @return
	 */
	public boolean isHttpsLogin()
	{
		String perf_httpslogin = getProperty(AppConfig.CONF_HTTPS_LOGIN);
		//默认是http
		if(StringUtils.isEmpty(perf_httpslogin))
			return false;
		else
			return StringUtils.toBool(perf_httpslogin);
	}
	
	/**
	 * 设置是是否Https登录
	 * @param b
	 */
	public void setConfigHttpsLogin(boolean b)
	{
		setProperty(AppConfig.CONF_HTTPS_LOGIN, String.valueOf(b));
	}
	
	/**
	 * 清除保存的缓存
	 */
	public void cleanCookie()
	{
		removeProperty(AppConfig.CONF_COOKIE);
	}
	
	/**
	 * 判断缓存数据是否可读
	 * @param cachefile
	 * @return
	 */
	private boolean isReadDataCache(String cachefile)
	{
		return readObject(cachefile) != null;
	}
	
	/**
	 * 判断缓存是否存在
	 * @param cachefile
	 * @return
	 */
	private boolean isExistDataCache(String cachefile)
	{
		boolean exist = false;
		File data = getFileStreamPath(cachefile);
		if(data.exists())
			exist = true;
		return exist;
	}
	
	/**
	 * 判断缓存是否失效
	 * @param cachefile
	 * @return
	 */
	public boolean isCacheDataFailure(String cachefile)
	{
		boolean failure = false;
		File data = getFileStreamPath(cachefile);
		if(data.exists() && (System.currentTimeMillis() - data.lastModified()) > CACHE_TIME)
			failure = true;
		else if(!data.exists())
			failure = true;
		return failure;
	}
	
	/**
	 * 清除app缓存
	 */
	public void clearAppCache()
	{
		//清除webview缓存
		File file = CacheManager.getCacheFileBaseDir();  
		
		if (file != null && file.exists() && file.isDirectory()) {  
		    for (File item : file.listFiles()) {  
		    	item.delete();  
		    }  
		    file.delete();  
		}  		  
		deleteDatabase("webview.db");  
		deleteDatabase("webview.db-shm");  
		deleteDatabase("webview.db-wal");  
		deleteDatabase("webviewCache.db");  
		deleteDatabase("webviewCache.db-shm");  
		deleteDatabase("webviewCache.db-wal");  
		//清除数据缓存
		clearCacheFolder(getFilesDir(),System.currentTimeMillis());
		clearCacheFolder(getCacheDir(),System.currentTimeMillis());
		//2.2版本才有将应用缓存转移到sd卡的功能
		if(isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)){
			clearCacheFolder(MethodsCompat.getExternalCacheDir(this),System.currentTimeMillis());
		}
		//清除编辑器保存的临时内容
		Properties props = getProperties();
		for(Object key : props.keySet()) {
			String _key = key.toString();
			if(_key.startsWith("temp"))
				removeProperty(_key);
		}
	}	
	
	/**
	 * 清除缓存目录
	 * @param dir 目录
	 * @param numDays 当前系统时间
	 * @return
	 */
	private int clearCacheFolder(File dir, long curTime) {          
	    int deletedFiles = 0;         
	    if (dir!= null && dir.isDirectory()) {             
	        try {                
	            for (File child:dir.listFiles()) {    
	                if (child.isDirectory()) {              
	                    deletedFiles += clearCacheFolder(child, curTime);          
	                }  
	                if (child.lastModified() < curTime) {     
	                    if (child.delete()) {                   
	                        deletedFiles++;           
	                    }    
	                }    
	            }             
	        } catch(Exception e) {       
	            e.printStackTrace();    
	        }     
	    }       
	    return deletedFiles;     
	}
	
	/**
	 * 将对象保存到内存缓存中
	 * @param key
	 * @param value
	 */
	public void setMemCache(String key, Object value) {
		memCacheRegion.put(key, value);
	}
	
	/**
	 * 从内存缓存中获取对象
	 * @param key
	 * @return
	 */
	public Object getMemCache(String key){
		return memCacheRegion.get(key);
	}
	
	/**
	 * 保存磁盘缓存
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public void setDiskCache(String key, String value) throws IOException {
		FileOutputStream fos = null;
		try{
			fos = openFileOutput("cache_"+key+".data", Context.MODE_PRIVATE);
			fos.write(value.getBytes());
			fos.flush();
		}finally{
			try {
				fos.close();
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 获取磁盘缓存数据
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getDiskCache(String key) throws IOException {
		FileInputStream fis = null;
		try{
			fis = openFileInput("cache_"+key+".data");
			byte[] datas = new byte[fis.available()];
			fis.read(datas);
			return new String(datas);
		}finally{
			try {
				fis.close();
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 保存对象
	 * @param ser
	 * @param file
	 * @throws IOException
	 */
	public boolean saveObject(Serializable ser, String file) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try{
			fos = openFileOutput(file, MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ser);
			oos.flush();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			try {
				oos.close();
			} catch (Exception e) {}
			try {
				fos.close();
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 读取对象
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Serializable readObject(String file){
		if(!isExistDataCache(file))
			return null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
			fis = openFileInput(file);
			ois = new ObjectInputStream(fis);
			return (Serializable)ois.readObject();
		}catch(FileNotFoundException e){
		}catch(Exception e){
			e.printStackTrace();
			//反序列化失败 - 删除缓存文件
			if(e instanceof InvalidClassException){
				File data = getFileStreamPath(file);
				data.delete();
			}
		}finally{
			try {
				ois.close();
			} catch (Exception e) {}
			try {
				fis.close();
			} catch (Exception e) {}
		}
		return null;
	}

	public boolean containsProperty(String key){
		Properties props = getProperties();
		 return props.containsKey(key);
	}
	
	public void setProperties(Properties ps){
		AppConfig.getAppConfig(this).set(ps);
	}

	public Properties getProperties(){
		return AppConfig.getAppConfig(this).get();
	}
	
	public void setProperty(String key,String value){
		AppConfig.getAppConfig(this).set(key, value);
	}
	
	public String getProperty(String key){
		return AppConfig.getAppConfig(this).get(key);
	}
	public void removeProperty(String...key){
		AppConfig.getAppConfig(this).remove(key);
	}

	/**
	 * 获取内存中保存图片的路径
	 * @return
	 */
	public String getSaveImagePath() {
		return saveImagePath;
	}
	/**
	 * 设置内存中保存图片的路径
	 * @return
	 */
	public void setSaveImagePath(String saveImagePath) {
		this.saveImagePath = saveImagePath;
	}
	
}
