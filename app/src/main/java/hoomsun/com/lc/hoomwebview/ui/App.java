package hoomsun.com.lc.hoomwebview.ui;

import android.app.Application;

import com.antfortune.freeline.FreelineCore;

import hoomsun.com.lc.hoomwebview.HoomWeb;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FreelineCore.init(this);
        HoomWeb.init(getApplicationContext());
    }

}
