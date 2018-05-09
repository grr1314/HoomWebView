package hoomsun.com.lc.hoomwebview.listener;

import hoomsun.com.lc.hoomwebview.HoomWebView;

/**
 * Created by hoomsun on 2018/5/8.
 */

public interface WebClientListener {
    public void refreshWebView(HoomWebView webView, String url, boolean isReload);
}
