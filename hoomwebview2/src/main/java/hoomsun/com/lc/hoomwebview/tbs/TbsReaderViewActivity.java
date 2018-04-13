package hoomsun.com.lc.hoomwebview.tbs;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.smtt.sdk.TbsReaderView;

import hoomsun.com.lc.hoomwebview.R;
import hoomsun.com.lc.hoomwebview.download.FileDownManagerListener;
import hoomsun.com.lc.hoomwebview.download.FileDownloadManager;

/**
 * Created by hoomsun on 2018/4/9.
 */

public class TbsReaderViewActivity extends AppCompatActivity implements TbsReaderView.ReaderCallback {
    TbsReaderView tbsReaderView;
    LinearLayout tbs_readerview_content;
    TextView test;
    FileDownloadManager fileDownloadManager;
    String url;
    String mFileName="";
    private DownloadManager mDownloadManager;
    private long mRequestId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tbs_readerview_activity);
        tbsReaderView = new TbsReaderView(this, this);
        tbs_readerview_content = findViewById(R.id.tbs_readerview_content);
        tbs_readerview_content.addView(tbsReaderView, 0);
        Intent b = getIntent();
        url = b.getStringExtra("fileUrl");
        fileDownloadManager = FileDownloadManager.getInstance(this);
        mFileName = fileDownloadManager.parseName(url);
        fileDownloadManager.setmFileName(url);
        test = new TextView(this);
        test.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        test.setText("");
        tbs_readerview_content.addView(test);
        fileDownloadManager.setFileDownManagerListener(new FileDownManagerListener() {
            @Override
            public void start() {
                //开始progress
                test.setText("开始下载");
            }

            @Override
            public void downloaging(int progress) {

            }

            @Override
            public void complate() {
                tbs_readerview_content.removeAllViews();
                tbs_readerview_content.addView(tbsReaderView, 0);
                displayFile();
            }
        });
        if (fileDownloadManager.isLocalExist()) {
            displayFile();
        } else {
            //开始下载文件
            fileDownloadManager.startDownload(url,mFileName);
        }
    }

    private void displayFile() {
        Bundle bundle = new Bundle();
        bundle.putString("filePath", fileDownloadManager.getLocalFile().getPath());
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
        boolean result = tbsReaderView.preOpen(fileDownloadManager.parseFormat(mFileName), false);
        if (result) {
            tbsReaderView.openFile(bundle);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileDownloadManager.taskRemove(mRequestId);
        tbsReaderView.onStop();
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }
}
