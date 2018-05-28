package hoomsun.com.lc.hoomwebview.base;


import com.tencent.smtt.sdk.WebChromeClient;

import hoomsun.com.lc.hoomwebview.listener.WebChromeListener;
import hoomsun.com.lc.hoomwebview.ui.ProgressInterface;

/**
 * Created by hoomsun on 2018/5/21.
 */

public abstract class BaseWebChromeClient extends WebChromeClient {
    public abstract void setProgressBar(ProgressInterface progressBar);
    public abstract void setWebChromeListener(WebChromeListener webChromeListener);

}
