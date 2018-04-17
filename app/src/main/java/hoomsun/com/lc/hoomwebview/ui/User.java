package hoomsun.com.lc.hoomwebview.ui;

import com.google.gson.Gson;

import hoomsun.com.lc.hoomwebview.data.post.BasePostModel;

/**
 * User Bean
 * Created by hoomsun on 2018/4/16.
 */

public class User extends BasePostModel{
    private String userName;
    private String age;

    public User(String serviceName, String platformNo, String userData, Object model, String keySerial, String sign, String userName, String age) {
        super(serviceName, platformNo, userData, model, keySerial, sign);
        this.userName = userName;
        this.age = age;
    }

    public User(String serviceName, String platformNo, String userData, Object model, String keySerial, String sign) {
        super(serviceName, platformNo, userData, model, keySerial, sign);
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
        return new Gson().toJson(this);
    }
}
