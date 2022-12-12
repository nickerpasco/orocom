package royal.spring.clinicasanna.sanna.sanna.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import royal.spring.clinicasanna.R;

public class NotificaionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaion);
        Button notifyBtn = findViewById(R.id.notifyBtn);
        notifyBtn.setOnClickListener(v -> {

            createNotification(
                    getResources().getString(R.string.notification_title),
                    getResources().getString(R.string.notification_content),
                    getResources().getString(R.string.notification_channel)
            );

        });

        Iniciar();

    }

    /**
     * Create Notification
     * Param
     * 1. title
     * 2. content
     * 3. channelId
     * 4.priorty
     * 5. notificationId
     */


    public void Iniciar() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Esto se ejecuta en segundo plano una única vez
                while (true) {
                    // Pero usamos un truco y hacemos un ciclo infinito
                    try {
                        // En él, hacemos que el hilo duerma
                        Thread.sleep(10000);
                        // Y después realizamos las operaciones


                        NotificaionActivity.this.runOnUiThread(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            public void run() {
                                //
                                try {
                                    Toast.makeText(NotificaionActivity.this, "Iniciar Motor", Toast.LENGTH_SHORT).show();
                                    createNotification(
                                            getResources().getString(R.string.notification_title),
                                            getResources().getString(R.string.notification_content),
                                            getResources().getString(R.string.notification_channel)
                                    );

                                    // getPedidosOffline(pedidoService);
                                } catch (Exception e) {
                                    Log.i("ERRORSITO", e.getMessage());
                                }
                                //  startActivity(new Intent(MenuPrincipal.this, NewPedidoActivity.class));
                            }
                        });

                        // Así, se da la impresión de que se ejecuta cada cierto tiempo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        // Creamos un hilo y le pasamos el runnable
        Thread hilo = new Thread(runnable);
        hilo.start();

    }


    private void createNotification(String title, String content,
                                    String channedId) {


        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), channedId)
                        .setSmallIcon(R.drawable.ic_notifications_active)
                        .setAutoCancel(true)
                        .setLights(Color.BLUE, 500, 500)
                        .setVibrate(new long[]{500, 500, 500})
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);



        // Since android Oreo notification channel is needed.
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(NotificaionActivity.this);



        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channedId,
                    channedId,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);

        }

        String imageUrl = "https://raw.githubusercontent.com/TutorialsBuzz/cdn/main/android.jpg";

        Glide.with(getApplicationContext())
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        //largeIcon
                        notificationBuilder.setLargeIcon(resource);
                        //Big Picture
                        notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(resource));

                        Notification notification = notificationBuilder.build();
                        notificationBuilder.setOngoing(true);
                        notificationManager.notify(NotificationID.getID(), notification);




                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                    }
                });
    }

    static class NotificationID {
        private final static AtomicInteger c = new AtomicInteger(100);

        public static int getID() {
            return c.incrementAndGet();
        }
    }

}