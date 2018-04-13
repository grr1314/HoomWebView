package hoomsun.com.lc.hoomwebview.download;

import android.app.DownloadManager;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.Timer;

import static android.content.Context.DOWNLOAD_SERVICE;


/**
 * Created by hoomsun on 2018/4/9.
 */

public class FileDownloadManager {
    private static final String TAG = FileDownloadManager.class.getSimpleName();
    private DownloadManager downloadManager;
    private Context context;
    private static FileDownloadManager instance;
    Timer timer;
    private long downId;
    private DownloadObserver mDownloadObserver;
    private String mFileName;
    private FileDownManagerListener fileDownManagerListener;

    private FileDownloadManager(Context context) {
        downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
        this.context = context.getApplicationContext();
        timer = new Timer();
    }

    public static FileDownloadManager getInstance(Context context) {
        if (instance == null) {
            instance = new FileDownloadManager(context);
        }
        return instance;
    }

    public FileDownManagerListener getFileDownManagerListener() {
        return fileDownManagerListener;
    }

    public void setFileDownManagerListener(FileDownManagerListener fileDownManagerListener) {
        this.fileDownManagerListener = fileDownManagerListener;
    }

    class MyTask extends java.util.TimerTask {
        long downId;
        private int count;

        public MyTask(long downId) {
            this.downId = downId;
        }

        public void run() {
            if (instance.getDownloadStatus(downId) == DownloadManager.STATUS_SUCCESSFUL) {
                handler.sendEmptyMessage(1);
                timer.cancel();
            }
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    fileDownManagerListener.complate();
                }
                break;
            }
            return true;
        }
    });

    public void startDownload(String url,String mFileName) {
        if (downloadManager != null) {
            this.mFileName=mFileName;
            mDownloadObserver = new DownloadObserver(new Handler());
            context.getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"), true, mDownloadObserver);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, mFileName);
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
            timer.schedule(new MyTask(downId), 1000, 1000);
            downId = downloadManager.enqueue(request);
        }
    }
    public void setmFileName(String url)
    {
        this.mFileName=parseName(url);
    }
    public boolean isLocalExist() {
        return getLocalFile().exists();
    }

    public File getLocalFile() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), mFileName);
    }

    public String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public String parseName(String url) {
        String fileName = null;
        try {
            fileName = url.substring(url.lastIndexOf("/") + 1);
        } finally {
            if (TextUtils.isEmpty(fileName)) {
                fileName = String.valueOf(System.currentTimeMillis());
            }
        }
        return fileName;
    }

    /**
     * 获取文件保存的路径
     *
     * @param downloadId an ID for the download, unique across the system.
     *                   This ID is used to make future calls related to this download.
     * @return file path
     * @see FileDownloadManager#getDownloadUri(long)
     */
    public String getDownloadPath(long downloadId) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor c = downloadManager.query(query);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    return c.getString(c.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI));
                }
            } finally {
                c.close();
            }
        }
        return null;
    }

    /**
     * 获取保存文件的地址
     *
     * @param downloadId an ID for the download, unique across the system.
     *                   This ID is used to make future calls related to this download.
     * @see FileDownloadManager#getDownloadPath(long)
     */
    public Uri getDownloadUri(long downloadId) {
        return downloadManager.getUriForDownloadedFile(downloadId);
    }

    public DownloadManager getDownloadManager() {
        return downloadManager;
    }


    /**
     * 获取下载状态
     *
     * @param downloadId an ID for the download, unique across the system.
     *                   This ID is used to make future calls related to this download.
     * @return int
     * @see DownloadManager#STATUS_PENDING
     * @see DownloadManager#STATUS_PAUSED
     * @see DownloadManager#STATUS_RUNNING
     * @see DownloadManager#STATUS_SUCCESSFUL
     * @see DownloadManager#STATUS_FAILED
     */
    public int getDownloadStatus(long downloadId) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor c = downloadManager.query(query);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    return c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                }
            } finally {
                c.close();
            }
        }
        return -1;
    }

    /**
     * 移除下载任务
     *
     * @param id
     */
    public void taskRemove(long id) {
        if (downloadManager != null) {
            downloadManager.remove(id);
            //暂时只考虑一个下载任务
            timer.cancel();
        }
    }

    private class DownloadObserver extends ContentObserver {

        private DownloadObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            Log.i("downloadUpdate: ", "onChange(boolean selfChange, Uri uri)");
            queryDownloadStatus();
        }
    }

    private void queryDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downId);
        Cursor cursor = null;
        try {
            cursor = downloadManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                //已经下载的字节数
                int currentBytes = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                //总需下载的字节数
                int totalBytes = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                //状态所在的列索引
                int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                if (DownloadManager.STATUS_SUCCESSFUL == status) {
                    Log.e(TAG,"queryDownloadStatus");
                    fileDownManagerListener.complate();
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
