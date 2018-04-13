package hoomsun.com.lc.hoomwebview;

/**
 * Created by hoomsun on 2018/4/2.
 */

public class hoomsun implements ILawsuit {
    @Override
    public void submit() {
        System.out.println("hoomsun 和政府打官司");
    }

    @Override
    public void burden() {
        System.out.println("hoomsun 进行举证");
    }

    @Override
    public void defend() {
        System.out.println("hoomsun 开始辩护");
    }

    @Override
    public void finish() {
        System.out.println("hoomsun 结束");
    }
}
