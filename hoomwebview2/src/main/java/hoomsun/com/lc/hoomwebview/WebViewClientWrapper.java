package hoomsun.com.lc.hoomwebview;

import hoomsun.com.lc.hoomwebview.jsbridge.BridgeWebView;
import hoomsun.com.lc.hoomwebview.jsbridge.BridgeWebViewClient;

/**
 * Created by hoomsun on 2018/4/12.
 */

public class WebViewClientWrapper extends BridgeWebViewClient {
    public WebViewClientWrapper(BridgeWebView webView) {
        super(webView);
    }
}
