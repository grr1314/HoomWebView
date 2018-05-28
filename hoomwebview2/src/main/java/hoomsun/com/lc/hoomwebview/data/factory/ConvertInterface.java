package hoomsun.com.lc.hoomwebview.data.factory;

/**
 * Created by hoomsun on 2018/4/11.
 */

public interface ConvertInterface<T> {
    abstract class ConvertFactory<F> {
        //创建一个工厂的对象
//        abstract ConvertFactory create();
        //做数据解析
        public abstract void doParse(String body);

        public abstract void doAction(F t);
        public abstract void isTimeOut();
    }

    void doActionInner(T t);

    void timeOut();
}
