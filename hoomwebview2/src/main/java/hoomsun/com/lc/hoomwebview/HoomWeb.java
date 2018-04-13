package hoomsun.com.lc.hoomwebview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ViewGroup;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hoomsun.com.lc.hoomwebview.data.factory.ConvertInterface;
import hoomsun.com.lc.hoomwebview.jsbridge.BridgeHandler;
import hoomsun.com.lc.hoomwebview.jsbridge.CallBackFunction;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class HoomWeb {

    private static final String TAG = HoomWeb.class.getSimpleName();
    //负责创建WebView对象
    private WebViewCreator webViewCreator;
    private HoomWebView hoomWebView;
    private List<String> handlerNames = new ArrayList<>();
    private Activity activity;
    private Fragment fragment;
    private ConvertInterface.ConvertFactory convertFactory;

    public HoomWeb(HoomBuilder hoomBuilder) {
        //创建HoomWebView
        webViewCreator = new DefaultWebViewCreator(hoomBuilder.activity, hoomBuilder.viewGroup);
        webViewCreator.create();
        //获取HoomWebView对象
        hoomWebView = (HoomWebView) webViewCreator.getWebView();
        activity = hoomBuilder.activity;

    }

    public static final class HoomBuilder {
        Activity activity;
        HoomWebView hoomWebView;
        ViewGroup viewGroup;
        ViewGroup.LayoutParams layoutParams;

        public HoomBuilder(Activity activity) {
            this.activity = activity;
        }

        /**
         * 设置WebView的父布局，以及设置布局参数
         *
         * @param viewGroup
         * @param layoutParams
         * @return
         */
        public HoomBuilder setWebViewParentLayout(ViewGroup viewGroup, ViewGroup.LayoutParams layoutParams) {
            this.viewGroup = viewGroup;
            this.layoutParams = layoutParams;
            return this;
        }

        /**
         * 构建HoomWeb对象
         *
         * @return
         */
        public HoomWeb createHoomWebView() {
            return new HoomWeb(this);
        }

    }

    public static HoomBuilder with(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("activity can not be null .");
        }
        return new HoomBuilder(activity);
    }

    public HoomWeb addConvertFactory(ConvertInterface.ConvertFactory convertFactory) {
        this.convertFactory = convertFactory;
        return this;
    }

    /**
     * 设置WebSetting和WebClient
     *
     * @return
     */
    public HoomWeb ready() {
        if (hoomWebView != null) {
            WebSettings webSettings = hoomWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            //打开 WebView 的 LBS 功能，这样 JS 的 geolocation 对象才可以使用
            webSettings.setGeolocationEnabled(true);
            webSettings.setGeolocationDatabasePath("");
            webSettings.setDatabaseEnabled(true);

            hoomWebView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView webView, int i) {
                    super.onProgressChanged(webView, i);
                }

                @Override
                public void onReceivedTitle(WebView webView, String s) {
                    super.onReceivedTitle(webView, s);
                }

            });
            hoomWebView.setWebViewClient(new WebViewClientWrapper(hoomWebView) {
                @Override
                public void onPageFinished(WebView webView, String s) {
                    super.onPageFinished(webView, s);
                    webView.clearCache(true);
//                    //如果是pdf文件
//                    if (PathUtil.analyzePath(s)) {
//                        //可能是pdf文件，首先要下载文件
//                        Intent intent = new Intent(activity, TbsReaderViewActivity.class);
//                        intent.putExtra("fileUrl", s);
//                        activity.startActivity(intent);
//                    }
                }
            });
        }
        return this;
    }

    public HoomWeb registerHandlers(Map<String, BridgeHandler> handlers) {
        Iterator it = handlers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            handlerNames.add(key.toString());
            registerHandlerFinal(key.toString(), (BridgeHandler) value);
        }
        return this;
    }

    public HoomWeb registerHandler(String name) {
        handlerNames.add(name);
        registerHandlerFinal(name, handler);
        return this;
    }

    /**
     * 默认的BridgeHandler处理类
     */
    private BridgeHandler handler = new BridgeHandler() {
        @Override
        public void handler(String data, CallBackFunction function) {
            if (convertFactory != null) {
                convertFactory.doParse(data);
            }
        }
    };

    public HoomWeb unregisterHandlers() {
        for (String s : handlerNames) {
            hoomWebView.unregisterHandler(s);
        }
        return this;
    }

    private void registerHandlerFinal(String name, BridgeHandler handler) {
        hoomWebView.registerHandler(name, handler);
    }


    public void go(String url) {
        new DoUrl().loadUrl(url);
    }

    public void reload() {
        new DoUrl().reload();
    }

    public void postUrl(String url, byte[] param) {
        new DoUrl().postUrl(url, param);
    }

    public void stopLoading() {
        new DoUrl().stopLoading();
    }

    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
        new DoUrl().loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }


    /**
     * HoomWebView的代理类
     */
    private class DoUrl implements IWebView {

        @Override
        public void loadUrl(String url) {
            hoomWebView.loadUrl(url);
        }

        @Override
        public void reload() {
            hoomWebView.reload();
        }

        @Override
        public void postUrl(String url, byte[] param) {
            hoomWebView.postUrl(url, param);
        }

        @Override
        public void stopLoading() {
            hoomWebView.stopLoading();
        }

        @Override
        public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
            hoomWebView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
        }
    }


}
