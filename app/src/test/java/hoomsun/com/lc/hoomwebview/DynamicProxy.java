package hoomsun.com.lc.hoomwebview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hoomsun on 2018/4/2.
 */

public class DynamicProxy implements InvocationHandler {
  private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=method.invoke(object,args);
        return result;
    }
}
