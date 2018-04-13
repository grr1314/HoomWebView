package hoomsun.com.lc.hoomwebview;

/**
 * 被代理类
 * Created by hoomsun on 2018/4/2.
 */

public class tencent implements ILawsuit {
    @Override
    public void submit() {
        System.out.println("tencent 和政府打官司");
    }

    @Override
    public void burden() {
        System.out.println("tencent 进行举证");
    }

    @Override
    public void defend() {
        System.out.println("tencent 开始辩护");
    }

    @Override
    public void finish() {
        System.out.println("tencent 结束");
    }
}
