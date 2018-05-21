package hoomsun.com.lc.hoomwebview.listener;


import hoomsun.com.lc.hoomwebview.HoomWebView;

/**
 * Created by hoomsun on 2018/4/20.
 */

public interface WebChromeListener {
    public void getTitle(String title);
    public void onViewDrawPreView();
    public void onViewDrawFinish(HoomWebView hoomWebView);
}
