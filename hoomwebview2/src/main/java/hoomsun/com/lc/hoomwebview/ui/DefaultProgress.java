package hoomsun.com.lc.hoomwebview.ui;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

import com.tencent.smtt.sdk.WebView;

import hoomsun.com.lc.hoomwebview.R;

/**
 * Created by hoomsun on 2018/4/19.
 */

public class DefaultProgress implements ProgressInterface {
    Activity activity;
    ProgressBar progressBar;

    public DefaultProgress(Activity activity) {
        this.activity = activity;
        progressBar = new ProgressBar(activity, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setBackgroundResource(R.drawable.default_progressbar_bg);
    }

    @Override
    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void progress(WebView view, int newProgress) {
        if (progressBar.getVisibility()==View.GONE)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        progressBar.setProgress(newProgress);
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void finish() {
        progressBar.setVisibility(View.GONE);
    }
}
