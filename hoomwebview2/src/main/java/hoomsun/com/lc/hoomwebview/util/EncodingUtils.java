package hoomsun.com.lc.hoomwebview.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import hoomsun.com.lc.hoomwebview.data.post.BasePostModel;

/**
 * Created by hoomsun on 2018/4/16.
 */

public class EncodingUtils {
    /**
     * String 转Byte
     * @param data
     * @param encoding
     * @return
     */
    public static byte[] getBytes(String data,String encoding)
    {
        byte[] bytes=null;
        try {
            bytes=data.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * byte转String
     * @param bytes
     * @param encoding
     * @return
     */
    public static String getString(byte[] bytes,String encoding)
    {
        try {
            return new String(bytes,encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static byte[] getPostData(BasePostModel basePostModel)
    {
        byte[] param=null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            JSONObject jsonObject = new JSONObject(basePostModel.toString());
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                if (!StringUtils.isEmpty(jsonObject.getString(key)))
                {
                    stringBuilder.append(key + "=")
                            .append(jsonObject.getString(key))
                            .append("&");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        param= getBytes(stringBuilder.toString().substring(0,stringBuilder.toString().length()-1),"UTF-8");
        return param;
    }

}
