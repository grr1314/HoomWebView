package hoomsun.com.lc.hoomwebview;


import android.widget.ProgressBar;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import hoomsun.com.lc.hoomwebview.base.BaseWebChromeClient;
import hoomsun.com.lc.hoomwebview.jsbridge.WebViewDrawFinishListener;
import hoomsun.com.lc.hoomwebview.listener.WebChromeListener;
import hoomsun.com.lc.hoomwebview.ui.DefaultProgress;
import hoomsun.com.lc.hoomwebview.ui.ProgressInterface;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class WebChromeClientWrapper extends BaseWebChromeClient implements WebViewDrawFinishListener{
    ProgressInterface progressInterface;
    DefaultProgress defaultProgress;
    ProgressBar progressBar;
    int oldProgress;
    WebChromeListener webChromeListener;
    private boolean isShowFile;
    HoomWebView hoomWebView;

    public WebChromeClientWrapper(DefaultProgress defaultProgress, WebChromeListener webChromeListener, boolean isShowFile, HoomWebView hoomWebView) {
        this.defaultProgress = defaultProgress;
        this.webChromeListener = webChromeListener;
        this.isShowFile = isShowFile;
        this.hoomWebView = hoomWebView;
    }

//    public WebChromeClientWrapper(DefaultProgress defaultProgress, WebChromeListener webChromeListener, boolean isShowFile) {
//        this.defaultProgress = defaultProgress;
//        this.webChromeListener = webChromeListener;
//        this.isShowFile = isShowFile;
//
//    }
//
//    public WebChromeClientWrapper(DefaultProgress defaultProgress) {
//        this.defaultProgress = defaultProgress;
//    }

    @Override
    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        if (i > oldProgress) {
            progressInterface.progress(webView, i);
            oldProgress = i;
        }
        if (i == 100) {
            progressInterface.finish();
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

    @Override
    public void onViewDrawPreView() {
        if (webChromeListener != null)
        webChromeListener.onViewDrawPreView();
    }

    @Override
    public void onViewDrawFinish() {
        if (webChromeListener != null)
            webChromeListener.onViewDrawFinish(hoomWebView);
    }

    @Override
    public void setProgressBar(ProgressInterface progressBar) {
        this.progressInterface=progressBar;
    }

    @Override
    public void setWebChromeListener(WebChromeListener webChromeListener) {
        this.webChromeListener=webChromeListener;
    }
}
