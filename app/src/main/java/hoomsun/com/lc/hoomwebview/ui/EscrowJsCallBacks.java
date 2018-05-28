package hoomsun.com.lc.hoomwebview.ui;

import java.util.HashMap;
import java.util.Map;

import hoomsun.com.lc.hoomwebview.HoomWebBuilder;
import hoomsun.com.lc.hoomwebview.data.factory.ConvertInterface;

/**
 * Created by hoomsun on 2018/4/17.
 */

public class EscrowJsCallBacks extends HoomWebBuilder.JSCallBack {
    String handlerName="submitFromWeb";
    @Override
    public Map<String, ConvertInterface.ConvertFactory> addJSCallBack() {
        return (Map<String, ConvertInterface.ConvertFactory>) new HashMap<>().put(handlerName,EscrowJsCallBackFactory.create(new ConvertInterface<User>() {
            @Override
            public void timeOut() {

            }

            @Override
            public void doActionInner(User user) {

            }
        }));
    }
}
