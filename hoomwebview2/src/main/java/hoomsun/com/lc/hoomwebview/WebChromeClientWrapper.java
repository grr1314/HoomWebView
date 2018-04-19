package hoomsun.com.lc.hoomwebview;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import hoomsun.com.lc.hoomwebview.ui.DefaultProgress;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class WebChromeClientWrapper extends WebChromeClient {
    DefaultProgress defaultProgress;

    public WebChromeClientWrapper(DefaultProgress defaultProgress) {
        this.defaultProgress = defaultProgress;
    }

    @Override
    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        defaultProgress.progress(webView, i);
        if (i==100)
        {
            defaultProgress.finish();
        }
    }

}
