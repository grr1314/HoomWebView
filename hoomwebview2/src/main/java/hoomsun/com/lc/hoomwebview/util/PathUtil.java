package hoomsun.com.lc.hoomwebview.util;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hoomsun on 2018/4/9.
 */

public class PathUtil {
    private static final String TAG=PathUtil.class.getSimpleName();
    public static boolean analyzePath(String url)
    {
        if (judgeEmpty(url))
        {
            Log.e(TAG,"url can not be null or empty string ");
        }
        String regEx="\\w*\\.(pdf|doc|xlex|...)";
        Pattern pattern=Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(url);
        if (matcher.find())
        {
            return true;
        }
        else
        {
            return false;
        }
//        String[] arrays =url.split("/.");
//        Log.e(TAG,arrays[arrays.length-1]);
//        if (arrays[arrays.length-1].equals("html"))
//        {
//            Log.e(TAG,"aa");
//            //正常的h5链接地址
//            return true;
//        }
//        else
//        {
//            Log.e(TAG,"bb");
//            return false;
//        }
    }
//\\w*\\.[mp4|rmvb|flv|mpeg|avi|...]

    public static boolean judgeEmpty(String url)
    {
if (url!=null&&!url.equals(""))
{
    return false;
}
return true;
    }
}
