package hoomsun.com.lc.hoomwebview.download;

/**
 * Created by hoomsun on 2018/4/10.
 */

public interface FileDownManagerListener {
    void start();
    void downloaging(int progress);
    void complate();
}
