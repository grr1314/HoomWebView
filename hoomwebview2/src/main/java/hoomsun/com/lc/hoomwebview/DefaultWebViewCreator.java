package hoomsun.com.lc.hoomwebview;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.tencent.smtt.sdk.WebView;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class DefaultWebViewCreator implements WebViewCreator {

    private HoomWebView hoomWebView;
    private ViewGroup.LayoutParams layoutParams;
    private Activity activity;
    private ViewGroup parentLayout;

    public DefaultWebViewCreator(Activity activity, ViewGroup parentLayout) {
        this(null, activity, parentLayout);
    }

    public DefaultWebViewCreator(HoomWebView hoomWebView, Activity activity, ViewGroup parentLayout) {
        this(hoomWebView, null, activity, parentLayout);
    }

    public DefaultWebViewCreator(HoomWebView hoomWebView, ViewGroup.LayoutParams layoutParams, Activity activity, ViewGroup parentLayout) {
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
    }

    private ViewGroup.LayoutParams getDefaultLayoutParams(ViewGroup.LayoutParams layoutParams) {
        layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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
        parentLayout.addView(hoomWebView, layoutParams);
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
