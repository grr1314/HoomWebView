package hoomsun.com.lc.hoomwebview.ui;

import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import hoomsun.com.lc.hoomwebview.data.post.BasePostModel;

/**
 * User Bean
 * Created by hoomsun on 2018/4/16.
 */

public class User extends BasePostModel{
    private String userName;
    private String age;

    public User(String userName, String age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        try {
            return "userName=" + URLEncoder.encode(userName,"UTF-8") + "&" +
                    "age=" + URLEncoder.encode(age,"UTF-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return super.toString();
    }
}
