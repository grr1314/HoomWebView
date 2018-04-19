package hoomsun.com.lc.hoomwebview.ui;

import android.widget.ProgressBar;

import com.tencent.smtt.sdk.WebView;

/**
 * Created by hoomsun on 2018/4/19.
 */

public interface ProgressInterface {
    public void setProgressBar(ProgressBar progressBar);
    public void progress(WebView view,int newProgress);
    public ProgressBar getProgressBar();
    public void finish();
}
