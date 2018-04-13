package hoomsun.com.lc.hoomwebview;

/**
 * 个人想模仿ActivityManagerService一样，这里定义一点WebView的常用方法
 * Created by hoomsun on 2018/4/3.
 */

public interface IWebView {

    void loadUrl(String url);

    void reload();

    void postUrl(String url,byte[] param);

    void stopLoading();

    void loadDataWithBaseURL(String baseUrl, String data,
                             String mimeType, String encoding, String historyUrl);
}