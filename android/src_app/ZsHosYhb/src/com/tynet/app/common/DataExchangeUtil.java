package com.tynet.app.common;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import android.util.Log;
import com.tynet.app.R;
import com.tynet.app.BookPlatApplication;

public class DataExchangeUtil {
   private static final String TAG = "DataExchangeUtil";
   /**
    * 测试库
    */
   private final static String APP_KEY = "y66qaFutaGAN5K0vqJiF5wZVYSUSHAsBrBBYdLgnS1WbOBKlSGs79O2XAU2o 50qaufDfQ67E+P0JT0Z7i0vndmVOgGO0t8Y6";
   /**
    * 正式库
    */
   //private final static String APP_KEY = "HbJwvNM/mdHje3PIok+YRSFsw+ijdmzDWVvqQCZShXaJA5f9tSPI/j9zA1AT EUuvr2OV1sE+97ezwRMRPphcnBwzPxCg8jcd";
   
   //private final static String APP_KEY = "kE7OZ8Nj50yEAAZz7z9wqwmztxK3aMBVIba2TSvqBPmZc2OlAB3kem/YpHb4 catjIEqqINgTS2y5fndXMyYf+Q==";
   
   
    private final static int TIME_OUT =20000;

    public static JSONObject getDataByMT2(String service, String method, JSONObject data) {    	
        try {
            return getData("MT2", Utils.GetCallJSONString(service, method, data).toString(), null);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static JSONObject getDataByMT(String service, String method, JSONObject data) {

        try {
            return getDataMT("MT", Utils.GetCallJSONString(service, method, data).toString(), null);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getDataByPC2(String service, String method, JSONObject data) {
        try {
            return getData("PC2", Utils.GetCallJSONString(service, method, data).toString(), BookPlatApplication.token);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    
	private static JSONObject getData(String methodName, String jsonstr, String token) {
        Map<String, String> props = new LinkedHashMap<String, String>();

        try {
			jsonstr = DESUtil.encryptDES(jsonstr);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        props.put("jsonstr", jsonstr);
        
        if (token != null && token.length() > 0) {
            props.put("token", token);
        }
        JSONObject json = null;

        try {
            json = new JSONObject(getSoapVer11Data("http://ws.tynet.com/", methodName, props, token));
            if("00001".equals(json.getString("code"))){

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        json = json != null ? json : new JSONObject();


        return json;
    }
    
    private static JSONObject getDataMT(String methodName, String jsonstr, String token) {
    	Map<String, String> props = new LinkedHashMap<String, String>();
        props.put("jsonstr", jsonstr);
        if (token != null && token.length() > 0) {
            props.put("token", token);
        }
        JSONObject json = null;

        try {
            json = new JSONObject(getSoapVer11Data("http://ws.tynet.com/", methodName, props, token));
            if("00001".equals(json.getString("code"))){

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        json = json != null ? json : new JSONObject();


        return json;
    }

    public static String getSoapVer11Data(String namespace, String methodName, Map<String, String> props, String token) {

        return getSoapData(SoapEnvelope.VER11, namespace, methodName, props, token);

    }

    public static String getSoapData(int ver, String namespace, String methodName, Map<String, String> props, String token) {

        String jsonStr = "";

        // String SOAP_ACTION =
        // "http://114.80.213.24:8085/hyt/WatcherImplPort?wsdl";
        // 创建SoapObject对象，并指定WebService的命名空间和调用的方法名
        SoapObject request = new SoapObject(namespace, methodName);

        // 第2步：设置WebService方法的参数
        for (Object key : props.keySet()) {
        	Log.d(TAG, "add param ：" + key);
            request.addProperty(key.toString(), props.get(key.toString()));

        }

        // 创建SoapSerializationEnvelope对象，并指定WebService的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(ver);

//        if (token != null && token.length() > 0) {
//
//            Element[] header = new Element[1];
//            header[0] = new Element().createElement(namespace, "token");
//            header[0].addChild(Node.TEXT, token);
//            envelope.headerOut = header;
//
//        }

        // 设置bodyOut属性
        envelope.bodyOut = request;
         
        String SOAP_ACTION = null;
		try {
			SOAP_ACTION = new String(DESUtil.decryptDES(APP_KEY));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        // 创建HttpTransportSE对象，并指定WSDL文档的URL
        MyAndroidHttpTransport ht = new MyAndroidHttpTransport(SOAP_ACTION, TIME_OUT);
        ht.debug = true;
        Log.i(TAG, "客户端请求：" + request);
        try {
            ht.call(SOAP_ACTION, envelope);
            if (envelope.getResponse() != null) {
                jsonStr = envelope.getResponse().toString();
            }
        } catch (Exception e) {
            Log.e(TAG, "连接服务器超时" + SOAP_ACTION);
            e.printStackTrace();
           // jsonStr = Utils.GetCodeJSONString("0001", R.string.code_0001);
        }

        Log.i(TAG, "服务端返回：" + jsonStr);
        return jsonStr;
    }
}
