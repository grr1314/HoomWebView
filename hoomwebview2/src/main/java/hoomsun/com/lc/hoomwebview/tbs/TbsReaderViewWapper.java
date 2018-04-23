package hoomsun.com.lc.hoomwebview.tbs;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.FrameLayout;

import com.tencent.smtt.sdk.TbsReaderView;

import hoomsun.com.lc.hoomwebview.download.FileDownManagerListener;
import hoomsun.com.lc.hoomwebview.download.FileDownloadManager;

/**
 * Created by hoomsun on 2018/4/13.
 */

public class TbsReaderViewWapper extends FrameLayout implements TbsReaderView.ReaderCallback {
    private Context context;
    private TbsReaderView tbsReaderView;
    private FileDownloadManager fileDownloadManager;
    private String mFileName;

    public TbsReaderViewWapper(@NonNull Context context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {
        if (tbsReaderView == null) {
            tbsReaderView = new TbsReaderView(context, this);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        addView(tbsReaderView, layoutParams);
    }

    public void showFile(String url) {
        Log.e("TbsReaderViewWapper", "showFile");
        ready(url);
        fileDownloadManager.setFileDownManagerListener(new FileDownManagerListener() {
            @Override
            public void start() {
                //开始progress
                Log.e("TbsReaderViewWapper", "start");
            }

            @Override
            public void downloaging(int progress) {
                Log.e("TbsReaderViewWapper", "downloaging");
            }

            @Override
            public void complate() {
                removeAllViews();
                addView(tbsReaderView, 0);
                displayFile();
            }
        });
        if (fileDownloadManager.isLocalExist()) {
            displayFile();
        } else {
            //开始下载文件
            fileDownloadManager.startDownload(url, mFileName);
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

    private void ready(String url) {
        fileDownloadManager = FileDownloadManager.getInstance(context);
        mFileName = fileDownloadManager.parseName(url);
        fileDownloadManager.setmFileName(url);
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }

    private void TbsReaderViewStop() {
        if (tbsReaderView != null) {
            tbsReaderView.onStop();
        }
    }

    public void unregisterTbsReaderView() {
        if (fileDownloadManager != null) {
            fileDownloadManager.unrigister();
        }
        TbsReaderViewStop();
    }
}
