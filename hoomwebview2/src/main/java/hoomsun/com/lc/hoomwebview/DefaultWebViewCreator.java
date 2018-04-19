package hoomsun.com.lc.hoomwebview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebView;

import hoomsun.com.lc.hoomwebview.ui.DefaultProgress;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class DefaultWebViewCreator implements WebViewCreator {

    private HoomWebView hoomWebView;
    private ViewGroup.LayoutParams layoutParams;
    private Activity activity;
    private ViewGroup parentLayout;
    private boolean isNeedDefaultProgress;
    private DefaultProgress defaultProgress;

    public DefaultWebViewCreator(Activity activity, ViewGroup parentLayout, DefaultProgress defaultProgress) {
        this(null, activity, parentLayout, defaultProgress);
    }

    public DefaultWebViewCreator(HoomWebView hoomWebView, Activity activity, ViewGroup parentLayout, DefaultProgress defaultProgress) {
        this(hoomWebView, null, activity, parentLayout, defaultProgress);
    }

    public DefaultWebViewCreator(HoomWebView hoomWebView, ViewGroup.LayoutParams layoutParams, Activity activity, ViewGroup parentLayout, DefaultProgress defaultProgress) {
        this.hoomWebView = hoomWebView;
        if (layoutParams == null) {
            layoutParams = getDefaultLayoutParams(layoutParams);
        }
        this.layoutParams = layoutParams;
        this.activity = activity;
        if (parentLayout == null) {
            throw new NullPointerException("parentLayout can not be null .");
        }
        this.parentLayout = parentLayout;
        isNeedDefaultProgress = true;
        this.defaultProgress = defaultProgress;
    }

    private ViewGroup.LayoutParams getDefaultLayoutParams(ViewGroup.LayoutParams layoutParams) {
        layoutParams = new FrameLayout.LayoutParams(-1, -1);
        return layoutParams;
    }


    @Override
    public WebViewCreator create() {
        //创建webview实例
        createWebView();
        return this;
    }

    private void createWebView() {
        if (hoomWebView == null) {
            hoomWebView = new HoomWebView(activity);
        }
        addView();
    }

    private void addView() {
        RelativeLayout frameLayout = new RelativeLayout(activity);
        frameLayout.addView(hoomWebView, layoutParams);
        RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(-1, 10);
        if (isNeedDefaultProgress) {
            ProgressBar progressBar = defaultProgress.getProgressBar();
            frameLayout.addView(progressBar, mLayoutParams);
        }
        parentLayout.addView(frameLayout);
    }

    @Override
    public WebView getWebView() {
        return hoomWebView;
    }

    @Override
    public FrameLayout getWebParentLayout() {
        return null;
    }
}
