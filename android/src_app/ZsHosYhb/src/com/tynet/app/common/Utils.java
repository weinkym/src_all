package com.tynet.app.common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.lidroid.xutils.util.LogUtils;
import com.soft.jni.JniClient;
import com.tynet.app.BookPlatApplication;

public class Utils {

    private static final String TAG = "Utils";
    
    private static Properties props = new Properties();

    public static String getStrFormJSON(JSONObject obj, String key) {
        String ret = "";
        try {
            ret = obj.has(key) ? obj.getString(key) : "";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static JSONObject GetCallJSONString(String service, String method, JSONObject args) throws JSONException {
        JSONObject json = new JSONObject();
        json.put(DataExchangeConst.SERVICE, service);
        json.put(DataExchangeConst.METHOD, method);
        json.put(DataExchangeConst.ARGS, args);
        json.put(DataExchangeConst.VERSION, "2.0");
        
        if(method != "CheckUpdate"){
        	int iDataLen = 0;
        	if((args!=null && args.toString()!=null) || args.toString().length() > 0){
        		iDataLen = args.toString().length();
        	}
        	
        	Log.d("util", String.valueOf(iDataLen));
        	json.put(DataExchangeConst.APPKEY, JniClient.encrypt(service, method, iDataLen));
        }
        
        return json;
    }

    public static String GetCodeJSONString(String code, int id) {
        JSONObject json = new JSONObject();
        try {
            json.put(DataExchangeConst.CODE, code);
            json.put(DataExchangeConst.MESSAGE, BookPlatApplication.res.getString(id));
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }

    public static String Filter(String str, String mode) {
        mode = mode.toLowerCase();

        if (mode == "html") {
            str = str.replaceAll("\r\n", "\n");
            str = str.replaceAll("'", "&#39;");
            str = str.replaceAll("\"", "&#34;");
            str = str.replaceAll("<", "&#60;");
            str = str.replaceAll(">", "&#62;");
            str = str.replaceAll("\n", "<br />");
        } else if (mode == "nohtml") {
            str = str.replaceAll("<[^>]*>", "");
        } else if (mode == "sql1") {
            str = str.replaceAll("'", "");
            str = str.replaceAll(";", "");
        } else if (mode == "htmltojs") {
            str = str.replaceAll("\r\n", "\n");
            str = str.replaceAll("\\", "\\\\");
            str = str.replaceAll("'", "\\\'");
            str = str.replaceAll("\"", "\\\"");
            str = str.replaceAll("/", "\\/");
            str = str.replaceAll("\n", "<br />");
            str = str.replaceAll(" ", "&nbsp;");
        } else {
            str = str.replaceAll("'", "''");
            str = str.replaceAll(";", "；");
        }
        return str;
    }

    public static String getMessage(String key) {
        return props.getProperty(key);
    }

    public static Bitmap getPicFromBytes(byte[] bytes,

    BitmapFactory.Options opts) {

        if (bytes != null)

            if (opts != null)

                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,

                opts);

            else

                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        return null;

    }

    public static byte[] getBytesFromInputStream(InputStream is, int bufsiz) throws IOException {

        int total = 0;

        byte[] bytes = new byte[4096];

        ByteBuffer bb = ByteBuffer.allocate(bufsiz);

        while (true) {

            int read = is.read(bytes);

            if (read == -1)

                break;

            bb.put(bytes, 0, read);

            total += read;

        }

        byte[] content = new byte[total];

        bb.flip();

        bb.get(content, 0, total);

        return content;

    }

    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo("com.soft", 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return verCode;
    }

    public static String getCnDateStr(String dateStr) {
        if (dateStr == null || dateStr.length() < 8) {
            return dateStr;
        } else {
            return dateStr.substring(0, 4) + "年" + dateStr.substring(4, 6) + "月" + dateStr.substring(6, 8) + "日";
        }
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}