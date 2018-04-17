package hoomsun.com.lc.hoomwebview.data.factory;

import com.google.gson.Gson;

/**
 * Created by hoomsun on 2018/4/11.
 */

public class bean {
    private String userName;
    private String age;
    public bean() {
    }
    public bean(String userName, String age) {
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
        return new Gson().toJson(this);
    }
}
