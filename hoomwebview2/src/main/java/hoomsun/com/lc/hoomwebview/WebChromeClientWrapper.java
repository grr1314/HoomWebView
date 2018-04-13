package hoomsun.com.lc.hoomwebview;

import android.net.Uri;

import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class WebChromeClientWrapper extends WebChromeClient {

    @Override
    public void openFileChooser(ValueCallback<Uri> valueCallback, String s, String s1) {
        super.openFileChooser(valueCallback, s, s1);
    }

}
