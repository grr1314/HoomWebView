package hoomsun.com.lc.hoomwebview;

/**
 * Created by hoomsun on 2018/4/2.
 */

public class Lawyer implements ILawsuit {
    private ILawsuit mLawsuit;

    public Lawyer(ILawsuit mLawsuit) {
        this.mLawsuit = mLawsuit;
    }

    @Override
    public void submit() {
        mLawsuit.submit();
    }

    @Override
    public void burden() {
        mLawsuit.burden();
    }

    @Override
    public void defend() {
        mLawsuit.defend();
    }

    @Override
    public void finish() {
        mLawsuit.finish();
    }
}
