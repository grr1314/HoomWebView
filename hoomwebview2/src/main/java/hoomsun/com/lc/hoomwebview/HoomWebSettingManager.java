package hoomsun.com.lc.hoomwebview;


/**
 * Created by hoomsun on 2018/4/8.
 */

public class HoomWebSettingManager implements HoomWebSettings{
    private com.tencent.smtt.sdk.WebSettings mWebSettings;
    public static HoomWebSettingManager getInstance() {
        return new HoomWebSettingManager();
    }
    private void setting(HoomWebView webView) {
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
    public HoomWebSettings toSetTbsWebSettings(HoomWebView webView) {
        setting(webView);
        return this;
    }

    @Override
    public com.tencent.smtt.sdk.WebSettings getWebSettings() {
        return mWebSettings;
    }
}
