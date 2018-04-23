package hoomsun.com.lc.hoomwebview;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.smtt.sdk.WebView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hoomsun.com.lc.hoomwebview.data.factory.ConvertInterface;
import hoomsun.com.lc.hoomwebview.data.post.BasePostModel;
import hoomsun.com.lc.hoomwebview.jsbridge.BridgeHandler;
import hoomsun.com.lc.hoomwebview.jsbridge.CallBackFunction;
import hoomsun.com.lc.hoomwebview.listener.WebChromeListener;
import hoomsun.com.lc.hoomwebview.tbs.TbsReaderViewActivity;
import hoomsun.com.lc.hoomwebview.tbs.TbsReaderViewWapper;
import hoomsun.com.lc.hoomwebview.ui.DefaultProgress;
import hoomsun.com.lc.hoomwebview.util.EncodingUtils;
import hoomsun.com.lc.hoomwebview.util.PathUtil;
import hoomsun.com.lc.hoomwebview.util.StringUtils;

/**
 * Created by hoomsun on 2018/4/8.
 */

public class HoomWebBuilder {
    private static final String TAG = HoomWebBuilder.class.getSimpleName();
    //负责创建WebView对象
    private WebViewCreator webViewCreator;
    private HoomWebView hoomWebView;
    private List<String> handlerNames = new ArrayList<>();
    private Activity activity;
    private Fragment fragment;
    private ConvertInterface.ConvertFactory convertFactory;
    private List<JSCallBack> convertFactoryList = new ArrayList<>();
    private WebChromeClientWrapper webChromeClientWrapper;
    private BasePostModel basePostModel;
    private HoomWebSettings hoomWebSettings;
    private DefaultProgress defaultProgress;
    private WebChromeListener webChromeListener;
    private ViewGroup webViewParentLayout;
    private boolean isShowFile;
    private View oldView;
    private TbsReaderViewWapper tbsReaderViewWapper;

    public HoomWebBuilder(HoomBuilder hoomBuilder) {
        activity = hoomBuilder.activity;
        defaultProgress = new DefaultProgress(activity);
        //创建HoomWebView
        if (hoomBuilder.webViewCreator == null) {
            webViewCreator = new DefaultWebViewCreator(hoomBuilder.activity, hoomBuilder.viewGroup, defaultProgress);
        } else {
            webViewCreator = hoomBuilder.webViewCreator;
        }
        webViewCreator.create();
        //获取HoomWebView对象
        hoomWebView = (HoomWebView) webViewCreator.getWebView();
        hoomWebView.getX5WebViewExtension().setScrollBarFadingEnabled(false);//不显示ScrollBar
        webViewParentLayout = webViewCreator.getWebParentLayout();
    }

    public static final class HoomBuilder {
        Activity activity;
        Fragment fragment;
        HoomWebView hoomWebView;
        ViewGroup viewGroup;
        ViewGroup.LayoutParams layoutParams;
        WebViewCreator webViewCreator;

        public HoomBuilder(Activity activity) {
            this.activity = activity;
        }

        public HoomBuilder(Fragment fragment) {
            this.fragment = fragment;
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

        public HoomBuilder setWebViewCreator(WebViewCreator webViewCreator) {
            this.webViewCreator = webViewCreator;
            return this;
        }

        /**
         * 构建HoomWeb对象
         *
         * @return
         */
        public HoomWebBuilder createHoomWebView() {
            return new HoomWebBuilder(this);
        }

    }

    /**
     * 构建一个HoomBuilder对象
     *
     * @param activity
     * @return
     */
    public static HoomBuilder with(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("activity can not be null .");
        }
        return new HoomBuilder(activity);
    }

    /**
     * 构建一个HoomBuilder对象
     *
     * @param fragment
     * @return
     */
    public static HoomBuilder with(@NonNull Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("fragment can not be null .");
        }
        return new HoomBuilder(fragment);
    }

    public HoomWebBuilder addConvertFactory(ConvertInterface.ConvertFactory convertFactory) {
        this.convertFactory = convertFactory;
        return this;
    }

    public HoomWebBuilder setWebChromeClientWrapper(WebChromeClientWrapper webChromeClientWrapper) {
        this.webChromeClientWrapper = webChromeClientWrapper;
        return this;
    }

    public HoomWebBuilder setProgresssBar() {
        return this;
    }

    public HoomWebBuilder setWebChromeClientListener(WebChromeListener webChromeListener) {
        this.webChromeListener = webChromeListener;
        return this;
    }

    public HoomWebBuilder setPostModel(BasePostModel basePostModel) {
        this.basePostModel = basePostModel;
        return this;
    }

    public HoomWebBuilder setWebSetting(HoomWebSettings hoomWebSettings) {
        this.hoomWebSettings = hoomWebSettings;
        return this;
    }

    /**
     * 设置WebSetting和WebClient
     *
     * @return
     */
    public HoomWebBuilder build() {
        if (hoomWebView != null) {
            //设置WebSetting
            if (hoomWebSettings == null) {
                //使用默认的Setting
                this.hoomWebSettings = HoomWebSettingManager.getInstance();
            }
            hoomWebSettings.toSetTbsWebSettings(hoomWebView);
            //设置WebChromeClient
            if (webChromeClientWrapper == null) {
                webChromeClientWrapper = new WebChromeClientWrapper(defaultProgress, webChromeListener, isShowFile);
            }
            hoomWebView.setWebChromeClient(webChromeClientWrapper);
            //设置WebViewClient
            hoomWebView.setWebViewClient(new WebViewClientWrapper(hoomWebView) {
                @Override
                public void onPageFinished(WebView webView, String s) {
                    super.onPageFinished(webView, s);
                    if (PathUtil.analyzePath(s)) {
                        webChromeListener.getTitle("文件预览");
//                        可能是pdf文件，首先要下载文件
//                        Intent intent = new Intent(activity, TbsReaderViewActivity.class);
//                        intent.putExtra("fileUrl", s);
//                        activity.startActivity(intent);
                        //使用Fragment来展示
//
                        //使用View来展示
                        if (tbsReaderViewWapper == null) {
                            tbsReaderViewWapper = new TbsReaderViewWapper(activity);
                        }
                        tbsReaderViewWapper.showFile(s);
                        //将View添加进来
                        oldView = webViewParentLayout.getChildAt(0);
                        webViewParentLayout.removeAllViews();
                        webViewParentLayout.addView(tbsReaderViewWapper, 0);
                        isShowFile = true;
                    }
                }
            });
        }
        return this;
    }

    /**
     * 批量注册BridgeHandler
     *
     * @param factoryMap
     * @return
     */
    public HoomWebBuilder registerHandlers(List<JSCallBack> factoryMap) {
        convertFactoryList = factoryMap;
        if (handlerNames.size() > 0) {
            handlerNames.clear();
        }
        for (JSCallBack callBack : factoryMap) {
            handlerNames.add(callBack.handlerName);
        }
        //批量处理回调数据
        for (int i = 0; i < factoryMap.size(); i++) {
            registerHandlerFinal(factoryMap.get(i).handlerName, new CustomBridgeHandler(factoryMap.get(i).convertFactory));
        }
        return this;
    }

    public HoomWebBuilder registerHandler(String name) {
        if (handlerNames.size() > 0) {
            handlerNames.clear();
        }
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

    /**
     * 自定义BridgeHandler处理类，将工厂作为参数传入
     */
    private class CustomBridgeHandler implements BridgeHandler {
        private ConvertInterface.ConvertFactory convertFactory;

        public CustomBridgeHandler(ConvertInterface.ConvertFactory convertFactory) {
            this.convertFactory = convertFactory;
        }

        @Override
        public void handler(String data, CallBackFunction function) {
            convertFactory.doParse(data);
        }
    }

    /**
     * 注销JSBridge的方法
     *
     * @return
     */
    public HoomWebBuilder unregisterHandlers() {
        for (String s : handlerNames) {
            hoomWebView.unregisterHandler(s);
        }
        return this;
    }

    /**
     * 注册JSBridge的方法
     *
     * @param name
     * @param handler
     */
    private void registerHandlerFinal(String name, BridgeHandler handler) {
        hoomWebView.registerHandler(name, handler);
    }


    public void loadUrl(String url) {
        new DoUrl().loadUrl(url);
    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        new DoUrl().loadUrl(url, additionalHttpHeaders);
    }

    public void reload() {
        new DoUrl().reload();
    }

    public void postUrl(String url, byte[] param) {
        new DoUrl().postUrl(url, param);
    }

    public void postUrl(String url) {
        byte[] param = null;
        if (basePostModel != null) {
            if (!StringUtils.isEmpty(basePostModel.toString())) {
                param = EncodingUtils.getBytes(basePostModel.toString(), "UTF-8");
                new DoUrl().postUrl(url, param);
            } else {
                throw new NullPointerException("model toString() can not be null or empty String");
            }
        }

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
        public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
            hoomWebView.loadUrl(url, additionalHttpHeaders);
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

    public abstract static class JSCallBack {
        String handlerName;
        ConvertInterface.ConvertFactory convertFactory;

        public JSCallBack() {
            Map<String, ConvertInterface.ConvertFactory> map = addJSCallBack();
            if (map != null) {
                for (Map.Entry<String, ConvertInterface.ConvertFactory> entry : map.entrySet()) {
                    this.handlerName = entry.getKey();
                    this.convertFactory = entry.getValue();
                }
            }
        }
        public abstract Map<String, ConvertInterface.ConvertFactory> addJSCallBack();
    }

    public void goBack() {
        if (hoomWebViewCanGoBack()) {
            //如果说给的链接地址就是一个PDF文件，这种情况我应该直接关闭activity，如果说是在WebView内部跳转的PDF文件，应该先移除Tbs的View然后将WebView添加回来
            if (isShowFile && webViewParentLayout != null) {
                tbsReaderViewWapper.unregisterTbsReaderView();
                webViewParentLayout.removeAllViews();
                webViewParentLayout.addView(oldView, 0);
                hoomWebView.goBack();
            } else if (hoomWebView != null) {
                hoomWebView.goBack();
            }
        }
    }

    public void unregisterTbsReaderView() {
        if (isShowFile) {
            tbsReaderViewWapper.unregisterTbsReaderView();
        }
    }

    /**
     * 判断WebView是否可以返回
     *
     * @return
     */
    public boolean hoomWebViewCanGoBack() {
        return hoomWebView.canGoBack();
    }

}
