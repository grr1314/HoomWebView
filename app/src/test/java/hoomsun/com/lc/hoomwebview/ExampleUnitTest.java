package hoomsun.com.lc.hoomwebview;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    /**
     * 静态代理，其中tencent和hoomsun是被代理类，Lawyer是代理类。
     * ILawsuit是抽象主题类，其中代理类和被代理类都要实现抽象主题类
     */
    public void hoomsunLawsuit()
    {
        ILawsuit hoomsun=new hoomsun();
        ILawsuit lawyerHoomsun=new Lawyer(hoomsun);
        ILawsuit tencent=new tencent();
        ILawsuit lawyerTencent=new Lawyer(tencent);
        //交给律师来打官司
        lawyerHoomsun.submit();
        lawyerHoomsun.burden();
        lawyerHoomsun.defend();
        lawyerHoomsun.finish();
        lawyerTencent.submit();
        lawyerTencent.burden();
        lawyerTencent.defend();
        lawyerTencent.finish();
    }
    @Test
    public void lawSuitProxy()
    {
        ILawsuit hoomsun=new hoomsun();
        //构造一个动态代理
        DynamicProxy dynamicProxy=new DynamicProxy(hoomsun);
        //获取被代理类hoomsun的ClassLoader
        ClassLoader loader=hoomsun.getClass().getClassLoader();
        //动态构造一个代理者
        ILawsuit lawyer= (ILawsuit) Proxy.newProxyInstance(loader,new Class[]{ILawsuit.class},dynamicProxy);

        lawyer.submit();
        lawyer.burden();
        lawyer.defend();
        lawyer.finish();
    }
}
