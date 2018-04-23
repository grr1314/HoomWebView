package hoomsun.com.lc.hoomwebview;

import android.util.Log;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import hoomsun.com.lc.hoomwebview.listener.WebChromeListener;
import hoomsun.com.lc.hoomwebview.ui.DefaultProgress;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class WebChromeClientWrapper extends WebChromeClient {
    DefaultProgress defaultProgress;
    int oldProgress;
    WebChromeListener webChromeListener;
    private boolean isShowFile;

    public WebChromeClientWrapper(DefaultProgress defaultProgress, WebChromeListener webChromeListener, boolean isShowFile) {
        this.defaultProgress = defaultProgress;
        this.webChromeListener = webChromeListener;
        this.isShowFile = isShowFile;
    }

    public WebChromeClientWrapper(DefaultProgress defaultProgress) {
        this.defaultProgress = defaultProgress;
    }

    @Override
    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        if (i > oldProgress) {
            defaultProgress.progress(webView, i);
            oldProgress = i;
        }
        if (i == 100) {
            defaultProgress.finish();
            oldProgress = 0;
        }
    }

    @Override
    public void onReceivedTitle(WebView webView, String s) {
        super.onReceivedTitle(webView, s);
        if (webChromeListener != null) {
                webChromeListener.getTitle(s);
        }


    }

}
