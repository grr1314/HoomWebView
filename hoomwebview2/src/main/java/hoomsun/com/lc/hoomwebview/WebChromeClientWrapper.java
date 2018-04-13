package hoomsun.com.lc.hoomwebview;

import android.net.Uri;

import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class WebChromeClientWrapper extends WebChromeClient {

    /**
     * 打开文件选择
     * @param valueCallback
     * @param s
     * @param s1
     */
    @Override
    public void openFileChooser(ValueCallback<Uri> valueCallback, String s, String s1) {
        super.openFileChooser(valueCallback, s, s1);
    }

    @Override
    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
    }

}
