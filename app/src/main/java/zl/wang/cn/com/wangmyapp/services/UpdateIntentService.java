package zl.wang.cn.com.wangmyapp.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.utils.FileUtil;
import zl.wang.cn.com.wangmyapp.utils.UriUtil;

/**
 * Created by
 */
public class UpdateIntentService extends IntentService {
    public static final String ACTION_UPDATE = "zl.wang.cn.com.wangmyapp.action.UPDATE";
    private NotificationManager mNotificationManager;
    private RemoteViews mRemoteViews;
    private Notification mNotification;
    private Handler mUpdateHandler;

    public UpdateIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            switch (action) {
                case UpdateIntentService.ACTION_UPDATE:
                    handleActionUpdate(intent);
                    break;
                default:
                    break;
            }
        }
    }


    private void handleActionUpdate(Intent intent) {
        getUpdateHandler();
        beforeUpdateMessage();
        File file = updateIo(intent);
        finishUpdateMessage(file);
    }

    private void getUpdateHandler() {
        mUpdateHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.arg1) {
                    case 0:
                        createNotification();
                        //start
                        break;
                    case 1:
                        updateNotification(msg);
                        //updateingMessage
                        break;
                    case 2:
                        installApk(msg);
                        //finish
                    case 3:
                        //error
                        installApk(msg);
                        break;
                }
                return true;
            }
        });
    }


    private File updateIo(Intent intent) {
        File updateFile = FileUtil.getDiskCacheDir(getApplicationContext(), intent.getStringExtra("name") + System.currentTimeMillis() + ".apk");
        try {
            URL url = new URL(intent.getStringExtra("downUrl"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept-Encoding", "identity");
            conn.connect();
            int length = conn.getContentLength();
            InputStream inputStream = conn.getInputStream();
            FileOutputStream fos = new FileOutputStream(updateFile, true);
            int oldProgress = 0;
            byte buf[] = new byte[1024 * 8];
            int currentLength = 0;
            while (true) {
                int num = inputStream.read(buf);
                currentLength += num;
                // 计算进度条位置
                int progress = (int) ((currentLength / (float) length) * 100);
                if (progress > oldProgress) {
                    updatingMessage(progress);
                    oldProgress = progress;
                }
                if (num <= 0) {
                    break;
                }
                fos.write(buf, 0, num);
                fos.flush();
            }
            fos.flush();
            fos.close();
            inputStream.close();
        } catch (Exception e) {
            Log.i("updateException", e.toString());
            return null;
        }
        return updateFile;
    }


    private void beforeUpdateMessage() {
        Message message = mUpdateHandler.obtainMessage();
        message.arg1 = 0;
        mUpdateHandler.sendMessage(message);
    }

    private void updatingMessage(int progress) {
        Message message = mUpdateHandler.obtainMessage();
        message.arg1 = 1;
        message.obj = progress;
        mUpdateHandler.sendMessage(message);
    }

    private void finishUpdateMessage(File file) {
        Message message = mUpdateHandler.obtainMessage();
        message.arg1 = 2;
        message.obj = file;
        mUpdateHandler.sendMessage(message);
    }

    public void createNotification() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,"a");
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setTicker("开始下载");
        mRemoteViews = new RemoteViews(getPackageName(), R.layout.notification_update);
        mRemoteViews.setProgressBar(R.id.notificationProgress, 100, 0, false);
        mBuilder.setCustomContentView(mRemoteViews);
        mNotification = mBuilder.build();
        mNotification.flags = Notification.FLAG_NO_CLEAR;
        mNotificationManager.notify(0, mNotification);
    }

    private void updateNotification(Message msg) {
        Integer aFloat = (Integer) msg.obj;
        mRemoteViews.setProgressBar(R.id.notificationProgress, 100, aFloat, false);
        mRemoteViews.setTextViewText(R.id.notification_note_tv, "正在下载  " + aFloat + "%");
        mNotificationManager.notify(0, mNotification);
    }

    // 下载完成后打开安装apk界面
    public void installApk(Message msg) {
        File file = (File) msg.obj;
        if (file == null || file.length() == 0) {
            mRemoteViews.setTextViewText(R.id.notification_note_tv, "下载失败  ");
            mNotification.flags = Notification.FLAG_AUTO_CANCEL;
            mNotificationManager.notify(0, mNotification);
            return;
        }
        mRemoteViews.setProgressBar(R.id.notificationProgress, 100, 100, false);
        mRemoteViews.setTextViewText(R.id.notification_note_tv, "下载完毕  ");
        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent openFile = getFileIntent(file);
        mNotification.contentIntent = PendingIntent.getActivity(this, 0, openFile, 0);
        mNotificationManager.notify(0, mNotification);
        startActivity(openFile);
    }

    public Intent getFileIntent(File file) {
        Uri uri = UriUtil.getUriForFile(getBaseContext(), file);
        String type = getMIMEType(file);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, type);
        return intent;
    }

    public String getMIMEType(File file) {
        String type = null;
        String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
        if (suffix.equals("apk")) {
            type = "application/vnd.android.package-archive";
        } else {
            // /*如果无法直接打开，就跳出软件列表给用户选择 */
            type = "*/*";
        }
        return type;
    }



}
