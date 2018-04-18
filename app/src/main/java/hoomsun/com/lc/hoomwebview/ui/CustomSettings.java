package hoomsun.com.lc.hoomwebview.ui;

import hoomsun.com.lc.hoomwebview.HoomWebSettingManager;
import hoomsun.com.lc.hoomwebview.HoomWebSettings;
import hoomsun.com.lc.hoomwebview.HoomWebView;

/**
 * Created by hoomsun on 2018/4/17.
 */

public class CustomSettings extends HoomWebSettingManager {
    @Override
    public HoomWebSettings toSetTbsWebSettings(HoomWebView webView) {
        //自定义WebSettings
        return this;
    }
}
