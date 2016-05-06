package com.serveza.lepet.serveza.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

import com.serveza.lepet.serveza.Activity.HomeActivity;
import com.serveza.lepet.serveza.Activity.LoginActivity;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Event;
import com.serveza.lepet.serveza.Classes.Data.EventList;
import com.serveza.lepet.serveza.Classes.LocalDatas.KeyValue;
import com.serveza.lepet.serveza.Classes.Network.Network;
import com.serveza.lepet.serveza.R;

import java.util.concurrent.Callable;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ServezaNotificationService extends Service {

    private Core core;
    private Context context;
    private EventList eventList;

    public ServezaNotificationService() {
        core = null;
        eventList = new EventList();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    private void CreateNotification(String Title, String Content) {
        android.support.v4.app.NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.home_icon)
                        .setContentTitle(Title)
                        .setContentText(Content);
        Intent resultIntent = new Intent(this, LoginActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(LoginActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int mId = 1;
        mNotificationManager.notify(mId, mBuilder.build());
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            core = (Core) intent.getSerializableExtra("Core");
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            core.network.GetUserEvent(context, core, new Callable<Integer>() {
                                @Override
                                public Integer call() throws Exception {
                                    return ReadNotifOnApp();
                                }
                            });
                            Thread.sleep(60000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        } catch (Exception ex) {
            core = null;
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {

                          Network.GetUserEventUpdate(context, KeyValue.getValue(context, "token", ""), new Callable<Integer>() {
                              @Override
                              public Integer call() throws Exception {
                                  return ReadNotif();
                              }
                          }, eventList);
                            Thread.sleep(60000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();

        }
        context = this;


        return super.onStartCommand(intent, flags, startId);
    }

    private int ReadNotif() {
        for (Event event : eventList.getList()) {
            CreateNotification(event.getName(), event.getDescription());
        }
        return 0;
    }

    private int ReadNotifOnApp() {
        for (Event event : core.userEventList.getList()) {
            CreateNotification(event.getName(), event.getDescription());
        }
        return 0;
    }

}
