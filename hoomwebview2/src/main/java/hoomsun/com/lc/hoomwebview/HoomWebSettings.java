package hoomsun.com.lc.hoomwebview;


import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by hoomsun on 2018/4/17.
 */

public interface HoomWebSettings <T extends WebSettings>{
    HoomWebSettings toSet(WebView webView);

    T getWebSettings();
}
