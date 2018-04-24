package hoomsun.com.lc.hoomwebview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hoomsun.com.lc.hoomwebview.HoomWebBuilder;
import hoomsun.com.lc.hoomwebview.HoomWebView;
import hoomsun.com.lc.hoomwebview.R;
import hoomsun.com.lc.hoomwebview.listener.WebChromeListener;

public class MainActivity extends AppCompatActivity {
    HoomWebBuilder hoomWebBuilder;
    LinearLayout content;
    TextView titleTextView;
    ImageView back;
    ImageView close;
    public static String H5_BASE_URL = "https://m.hoomxb.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = findViewById(R.id.content);
        titleTextView = findViewById(R.id.title);
        back = findViewById(R.id.back);
        close = findViewById(R.id.close);
        hoomWebBuilder = HoomWebBuilder.with(this)
                .setWebViewParentLayout(content, null)
                .createHoomWebView()
//                .setWebSetting(new CustomSettings())
                .setPostModel(new User("name", "21"))
                .setWebChromeClientListener(new WebChromeListener() {
                    @Override
                    public void getTitle(String title) {
                        if (title.length() > 15) {
                            title = title.substring(0, 14) + "...";
                        }
                        titleTextView.setText(title);
                    }

                    @Override
                    public void refreshWebView(HoomWebView webView, String url, boolean isReload) {

                    }

                })
                .build();
//        hoomWebBuilder.loadUrl(" http://www.beijing.gov.cn/zhuanti/ggfw/htsfwbxzzt/shxfl/fw/P020150720516332194302.doc");
        hoomWebBuilder.loadUrl("https://www.hoomxb.com/upload/pdf/payday-loan.pdf");
//        hoomWebBuilder.loadUrl("http://x5.tencent.com/tbs/sdk.html");
//        hoomWebBuilder.postUrl("http://x5.tencent.com/tbs/sdk.html");
//        hoomWebBuilder.loadUrl("http://www.jd.com/");

        if (hoomWebBuilder.hoomWebViewCanGoBack()) {
            close.setVisibility(View.VISIBLE);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hoomWebBuilder.hoomWebViewCanGoBack()) {
                    hoomWebBuilder.goBack();
                } else {
                    finish();
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //注意registerHandlers和registerHandler会相互覆盖
//        List<HoomWebBuilder.JSCallBack> jsCallBacks=new ArrayList<>();
//        jsCallBacks.add(new EscrowJsCallBacks());
//        hoomWebBuilder.registerHandlers(jsCallBacks);
    }

    @Override
    protected void onDestroy() {
        Log.e("onDestroy","onDestroy");
        super.onDestroy();
        if (hoomWebBuilder != null) {
            hoomWebBuilder.unregisterTbsReaderView();
        }
    }
}
