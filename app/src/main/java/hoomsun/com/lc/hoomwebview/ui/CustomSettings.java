package hoomsun.com.lc.hoomwebview.ui;

import android.webkit.WebView;

import hoomsun.com.lc.hoomwebview.HoomWebSettingManager;
import hoomsun.com.lc.hoomwebview.HoomWebSettings;
/**
 * Created by hoomsun on 2018/4/17.
 */

public class CustomSettings extends HoomWebSettingManager {
//    @Override
    public HoomWebSettings toSet(WebView webView) {
        return this;
    }
}
