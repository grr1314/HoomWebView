package hoomsun.com.lc.hoomwebview;


import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class HoomWebSettingManager implements HoomWebSettings{
    private WebSettings mWebSettings;
    @Override
    public HoomWebSettings toSet(WebView webView) {
        setting(webView);
        return this;
    }
    public static HoomWebSettingManager getInstance() {
        return new HoomWebSettingManager();
    }
    private void setting(WebView webView) {
        mWebSettings=webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        //打开 WebView 的 LBS 功能，这样 JS 的 geolocation 对象才可以使用
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setGeolocationDatabasePath("");
        mWebSettings.setDatabaseEnabled(true);
    }

    @Override
    public com.tencent.smtt.sdk.WebSettings getWebSettings() {
        return mWebSettings;
    }
}
