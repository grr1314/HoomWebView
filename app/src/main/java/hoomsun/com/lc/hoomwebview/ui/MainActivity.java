package hoomsun.com.lc.hoomwebview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import hoomsun.com.lc.hoomwebview.HoomWeb;
import hoomsun.com.lc.hoomwebview.R;
import hoomsun.com.lc.hoomwebview.data.factory.ConvertInterface;
import hoomsun.com.lc.hoomwebview.data.factory.DefalultData;
import hoomsun.com.lc.hoomwebview.data.factory.bean;

public class MainActivity extends AppCompatActivity {
    HoomWeb hoomWeb;
    LinearLayout content;
    public static String H5_BASE_URL = "https://m.hoomxb.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = findViewById(R.id.content);
        hoomWeb= HoomWeb.with(this)
                .setWebViewParentLayout(content, null)
                .createHoomWebView()
                .setWebSetting(new CustomSettings())
                .setPostModel(new User("serviceName","12","dddsss",new bean("name","21"),"ss","sgin"))
                .registerHandler("submitFromWeb")
                .addConvertFactory(DefalultData.create(new ConvertInterface<bean>() {
                    @Override
                    public void doActionInner(bean bean) {
                        //做数据处理
                    }
                }))
                .build();
//        hoomWeb.go(" http://www.beijing.gov.cn/zhuanti/ggfw/htsfwbxzzt/shxfl/fw/P020150720516332194302.doc");
//        hoomWeb.go("https://www.hoomxb.com/upload/pdf/payday-loan.pdf");
//        hoomWeb.go("http://x5.tencent.com/tbs/sdk.html");
        hoomWeb.postUrl("file:///android_asset/demo.html");
        List<HoomWeb.JSCallBack> jsCallBacks=new ArrayList<>();
        //base setting
        jsCallBacks.add(new HoomWeb.JSCallBack("ss",DefalultData.create(new ConvertInterface<bean>(){
            @Override
            public void doActionInner(bean bean) {

            }
        })));

        hoomWeb.registerHandlers(jsCallBacks);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
