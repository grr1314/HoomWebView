package hoomsun.com.lc.hoomwebview;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.tencent.smtt.sdk.WebView;

/**
 * Created by hoomsun on 2018/4/8.
 */

public interface WebViewCreator {
    WebViewCreator create();

    WebView getWebView();

    ViewGroup getWebParentLayout();
}
