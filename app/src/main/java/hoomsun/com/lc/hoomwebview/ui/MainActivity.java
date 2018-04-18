package hoomsun.com.lc.hoomwebview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import hoomsun.com.lc.hoomwebview.HoomWebBuilder;
import hoomsun.com.lc.hoomwebview.R;

public class MainActivity extends AppCompatActivity {
    HoomWebBuilder hoomWebBuilder;
    LinearLayout content;
    public static String H5_BASE_URL = "https://m.hoomxb.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = findViewById(R.id.content);
        hoomWebBuilder = HoomWebBuilder.with(this)
                .setWebViewParentLayout(content, null)
                .createHoomWebView()
                .setWebSetting(new CustomSettings())
                .setPostModel(new User("serviceName","12","dddsss",new bean("name","21"),"ss","sgin"))
                .build();
//        hoomWebBuilder.go(" http://www.beijing.gov.cn/zhuanti/ggfw/htsfwbxzzt/shxfl/fw/P020150720516332194302.doc");
//        hoomWebBuilder.go("https://www.hoomxb.com/upload/pdf/payday-loan.pdf");
//        hoomWebBuilder.go("http://x5.tencent.com/tbs/sdk.html");
        hoomWebBuilder.postUrl("file:///android_asset/demo.html");

        //注意registerHandlers和registerHandler会相互覆盖
        List<HoomWebBuilder.JSCallBack> jsCallBacks=new ArrayList<>();
        jsCallBacks.add(new EscrowJsCallBacks());
        hoomWebBuilder.registerHandlers(jsCallBacks);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
