package hoomsun.com.lc.hoomwebview;


import com.tencent.smtt.sdk.WebSettings;

/**
 * Created by hoomsun on 2018/4/17.
 */

public interface HoomWebSettings <T extends WebSettings>{
    HoomWebSettings toSetTbsWebSettings(HoomWebView hoomWebView);
    T getWebSettings();
}
