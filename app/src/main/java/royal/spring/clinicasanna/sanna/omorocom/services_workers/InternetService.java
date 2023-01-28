package royal.spring.clinicasanna.sanna.omorocom.services_workers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.omorocom.APIUtils;
import royal.spring.clinicasanna.sanna.omorocom.AsistenciaActivity;
import royal.spring.clinicasanna.sanna.omorocom.ui.AsistenciaDiariaMarcas;
import royal.spring.clinicasanna.sanna.omorocom.ui.LoginResponse;
import royal.spring.clinicasanna.sanna.omorocom.ui.MensajesGenericos;
import royal.spring.clinicasanna.sanna.omorocom.ui.ModelError;
import royal.spring.clinicasanna.sanna.omorocom.ui.ProgressBarGenerico;
import royal.spring.clinicasanna.sanna.omorocom.ui.UsuarioService;
import royal.spring.clinicasanna.sanna.omorocom.utils.FuncionesPrincipales;
import royal.spring.clinicasanna.sanna.omorocom.utils.GpsTracker;
import royal.spring.clinicasanna.sanna.sanna.DBHelper;

public class InternetService extends Service {

    public int counter=0;
    private Handler mHandler; // to display Toast message

    DBHelper dbHelper;
    UsuarioService usuarioService;


    public InternetService() {
    }

    static class NotificationID {
        private final static AtomicInteger c = new AtomicInteger(100);

        public static int getID() {
            return c.incrementAndGet();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());

        mHandler = new Handler(Looper.getMainLooper());
    }




    @RequiresApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground()
    {
        String NOTIFICATION_CHANNEL_ID = "example.permanence";
        String channelName = "Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }


    private void SaveASistencia(AsistenciaDiariaMarcas request,UsuarioService usuarioService) {



        Call<AsistenciaDiariaMarcas> call = usuarioService.SendAsistencia(request);
        call.enqueue(new Callback<AsistenciaDiariaMarcas>() {
            @Override
            public void onResponse(Call<AsistenciaDiariaMarcas> call, Response<AsistenciaDiariaMarcas> response) {
                if (response.isSuccessful()) {
                    ProgressBarGenerico.HideProgreess();

                    if (response.body().getLstErrores().size() == 0) {

                        Toast.makeText(getApplicationContext(), "Se subió un registro a la base de datos", Toast.LENGTH_SHORT).show();

                        try {
                            dbHelper.deleteAsitencia(AsistenciaDiariaMarcas.class,request.getIdAsistencia());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                        //MensajesGenericos.SHowMensajesGenericos("Success", "¡Éxito!", "La asistencia se registró correctamente..", AsistenciaActivity.this);
                     //   MensajesGenericos.SHowMensajesGenericosConAccion("Success", "¡Éxito!", "La asistencia se registró correctamente..", AsistenciaActivity.this, AceptarBotonMensaje());

                    } else {

                      //  ModelError error  = response.body().getLstErrores().get(0);
                      //  MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", error.getMensajeError(), AsistenciaActivity.this);

                    }


                }else{
                   // ProgressBarGenerico.HideProgreess();

                    if(response.code()==404){
                        //MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", "Verifique la configuración del servidor, Código 404", AsistenciaActivity.this);

                    }

                    if(response.code()==500){
                      //  MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", "Verifique la configuración del servidor, Código 500", AsistenciaActivity.this);

                    }


                }
            }

            @Override
            public void onFailure(Call<AsistenciaDiariaMarcas> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
             //   MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", t.getMessage(), AsistenciaActivity.this);

               // ProgressBarGenerico.HideProgreess();

            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startTimer();
        //startSignalR();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stoptimertask();

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, Restarter.class);
        this.sendBroadcast(broadcastIntent);
    }


    private Timer timer;
    private TimerTask timerTask;
    public void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                Log.i("Count", "=========  "+ (counter++));


                boolean validarInternet = FuncionesPrincipales.getValidarInternet(getApplicationContext());
                if (validarInternet) {


                    dbHelper = new DBHelper(getApplicationContext());
                    try {
                        long valida = dbHelper.getCountTable(AsistenciaDiariaMarcas.class, "AsistenciaDiariaMarcas");

                        if(valida>0){


                            Log.i("valida_datos", "Cantidad Registros " + valida);

                            Map<String, Object> params = new HashMap<>();
                            params.put("FlagOffline", true);

                            //params.put("FlagCobranza", "S");
                            List<AsistenciaDiariaMarcas> lis = (ArrayList<AsistenciaDiariaMarcas>) dbHelper.query(AsistenciaDiariaMarcas.class, params);

                            usuarioService = APIUtils.getUsuarioService();

                            for (AsistenciaDiariaMarcas item : lis){

                                SaveASistencia(item,usuarioService);

                            }
                        }



                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }else{
                    Log.i("ESTADO_SOCKET", "SE CORTÓ INTER");
                }
            }
        };
        timer.schedule(timerTask, 5000, 5000); //
    }

    public void stoptimertask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    private void createNotification(String title, String content,
                                    String channedId) {

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channedId)
                        .setSmallIcon(R.drawable.ic_app_royal)
                        .setAutoCancel(true)
                        .setLights(Color.BLUE, 500, 500)
                        .setVibrate(new long[]{500, 500, 500})
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentTitle(title)

                        .setContentText(content)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);


        // Since android Oreo notification channel is needed.
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channedId,
                    channedId,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);

        }

        String imageUrl = "https://raw.githubusercontent.com/TutorialsBuzz/cdn/main/android.jpg";

        Glide.with(this)
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


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
