package hoomsun.com.lc.hoomwebview.data.factory;


import com.google.gson.Gson;

/**
 * Created by hoomsun on 2018/4/11.
 */

public class DefalultData extends BaseDataConvertFactroy<bean> {
    ConvertInterface listener;
    Gson gson;

    public DefalultData(Gson gson, ConvertInterface listener) {
        this.listener = listener;
        this.gson = gson;
    }

    public static ConvertInterface.ConvertFactory create(ConvertInterface<bean> listener) {
        if (listener == null) {
            return create();
        }
        return new DefalultData(new Gson(), listener);
    }

    /**
     * 默认处理，没有返回数据
     * @return
     */
    static ConvertInterface.ConvertFactory create() {
        return new DefalultData(new Gson(), new ConvertInterface<bean>() {
            @Override
            public void doActionInner(bean bean) {
                return ;
            }
        });
    }

    @Override
    public void doParse(String body) {
        doAction(gson.fromJson(body, bean.class));
    }

    @Override
    public void doAction(bean t) {
        listener.doActionInner(t);
    }
}
