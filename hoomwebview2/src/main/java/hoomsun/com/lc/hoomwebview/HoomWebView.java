package hoomsun.com.lc.hoomwebview;

import android.content.Context;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.WebView;

import hoomsun.com.lc.hoomwebview.jsbridge.BridgeWebView;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class HoomWebView extends BridgeWebView {
    public HoomWebView(Context context) {
        super(context);
    }

    public HoomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HoomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
