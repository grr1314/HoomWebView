package hoomsun.com.lc.hoomwebview.ui;

import hoomsun.com.lc.hoomwebview.data.factory.BaseDataConvertFactroy;
import hoomsun.com.lc.hoomwebview.data.factory.ConvertInterface;

/**
 * Created by hoomsun on 2018/4/17.
 */

public class EscrowJsCallBackFactory extends BaseDataConvertFactroy<User> {
    ConvertInterface listener;

    public EscrowJsCallBackFactory(ConvertInterface listener) {
        this.listener = listener;
    }

    public static ConvertInterface.ConvertFactory create(ConvertInterface<User> listener) {
        if (listener == null) {
            return create();
        }
        return new EscrowJsCallBackFactory(listener);
    }

    /**
     * 默认处理，没有返回数据
     * @return
     */
    private static ConvertInterface.ConvertFactory create() {
        return new EscrowJsCallBackFactory(new ConvertInterface<User>() {
            @Override
            public void doActionInner(User bean) {
                return ;
            }

            @Override
            public void timeOut() {

            }
        });
    }

    @Override
    public void doParse(String body) {
        doAction(getBaseGson().fromJson(body, User.class));
    }

    @Override
    public void doAction(User t) {
        listener.doActionInner(t);
    }

    @Override
    public void isTimeOut() {
        listener.timeOut();
    }
}
