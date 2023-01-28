package royal.spring.clinicasanna.sanna.omorocom;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.omorocom.ui.AsistenciaDiariaMarcas;
import royal.spring.clinicasanna.sanna.omorocom.ui.LoginResponse;
import royal.spring.clinicasanna.sanna.omorocom.ui.MensajesGenericos;
import royal.spring.clinicasanna.sanna.omorocom.ui.ModelError;
import royal.spring.clinicasanna.sanna.omorocom.ui.ProgressBarGenerico;
import royal.spring.clinicasanna.sanna.omorocom.ui.UsuarioService;
import royal.spring.clinicasanna.sanna.omorocom.utils.FuncionesPrincipales;
import royal.spring.clinicasanna.sanna.omorocom.utils.GpsTracker;
import royal.spring.clinicasanna.sanna.sanna.DBHelper;
import royal.spring.clinicasanna.sanna.sanna.InicarLoginActivity;

public class AsistenciaActivity extends AppCompatActivity implements Runnable {

    String hora, minutos, segundos;
    Thread login;
    EditText TxtFechaHora,TxtObservaciones ;
    MaterialSpinner CboTipoAsistencia ;
    ImageView BtnRegresar ;
    ImageView BtnCheck ;
    UsuarioService usuarioService;

    private GpsTracker gpsTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TxtObservaciones = findViewById(R.id.TxtObservacion);
        TxtFechaHora = findViewById(R.id.TxtFechaHora);
        CboTipoAsistencia = findViewById(R.id.CboTipoAsistencia);
        BtnRegresar = findViewById(R.id.BtnRegresar);
        BtnCheck = findViewById(R.id.BtnCheck);

        usuarioService = APIUtils.getUsuarioService();
        BtnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_tv);
                BtnRegresar.startAnimation(animFadein);
                finish();

            }
        });

        BtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_tv);
                BtnCheck.startAnimation(animFadein);
                //SaveASistencia();

                GuardarAsistentenciaAync d = new GuardarAsistentenciaAync();
                d.setContext(AsistenciaActivity.this);
                d.execute();


            }
        });

        try {
            LoadTipoASistencia();


            //mostrarHora();

            Thread myThread = null;

            Runnable runnable = new CountDownRunner();
            myThread= new Thread(runnable);
            myThread.start();
        }catch (Exception e){
            Log.i("ERROR_ALCARGAR",""+e.getMessage());
        }


    }
    public Runnable AceptarBotonMensaje() {
        return new Runnable() {
            public void run() {
                finish();
            }
        };
    }


    private class GuardarAsistentenciaAync extends AsyncTask<Void, Void, Void> {

        private Context mContext;

        void setContext(Activity context) {
            mContext = context;
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    ProgressBarGenerico.LoadProgress(AsistenciaActivity.this);


                }
            });
        }


        @Override
        protected Void doInBackground(Void... voids) {

            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {


                       SaveASistencia();


                }
            });


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {

            ProgressBarGenerico.HideProgreess();

        }
    }


    private void SaveASistencia() {

        // Toast.makeText(this, "Validando usuario en línea..", Toast.LENGTH_SHORT).show();


        LoginResponse  user = new LoginResponse();


        String fechaHoy = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String fechaHoraMarcacion = fechaHoy+ " " +hora + ":" + minutos + ":" + segundos;


        AsistenciaDiariaMarcas request = new AsistenciaDiariaMarcas();
        gpsTracker = new GpsTracker(this);
        if (gpsTracker.canGetLocation()) {

            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            request.setLatitud(""+latitude);
            request.setLongitud(""+longitude);


            String cityName = "";
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(gpsTracker.getLatitude(),
                        gpsTracker.getLongitude(), 1);
                if (addresses.size() > 0)
                    System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();

                Gson gson = new Gson();
                String json = gson.toJson(addresses);
                Log.i("DATAAA_MAPA",json);

                //Toast.makeText(AsistenciaActivity.this, cityName, Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }

            request.setLugarMarcacion(cityName);
        }


         LoginResponse data = FuncionesPrincipales.getDataLogin(this);

        request.setEmpleado(data.getPersona());
        request.setTipoMarcacion(FuncionesPrincipales.getTipoMarcacion(CboTipoAsistencia.getText().toString()));
        //request.setLugarMarcacion("Juan de arona 728");
        request.setEstado("A");
        request.setUsuarioCreacion(data.getUsuario());
        //request.setIpCreacion("192.168.1.1");
        request.setFechaMarcacion(fechaHoraMarcacion);
        request.setComentarios(TxtObservaciones.getText().toString());

        Gson gson = new Gson();
        String json = gson.toJson(request);

        Log.i("JSONENVIO",json);

        boolean validaInternet = FuncionesPrincipales.getValidarInternet(this);
        if(validaInternet==false){


            DBHelper dbHelper;
            dbHelper = new DBHelper(this);

            try {

                request.setFlagOffline(true);
                dbHelper.create(request);
                ProgressBarGenerico.HideProgreess();
                MensajesGenericos.SHowMensajesGenericosConAccion("Info", "¡Atención!", "Se tuvo un inconveniente con la conexión de internet, la asistencia se guardó de forma local, se volverá a intentar automáticamente cuando se conecte a intenet..", AsistenciaActivity.this, AceptarBotonMensaje());


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return;
        }

        Call<AsistenciaDiariaMarcas> call = usuarioService.SendAsistencia(request);
        call.enqueue(new Callback<AsistenciaDiariaMarcas>() {
            @Override
            public void onResponse(Call<AsistenciaDiariaMarcas> call, Response<AsistenciaDiariaMarcas> response) {
                if (response.isSuccessful()) {
                    ProgressBarGenerico.HideProgreess();

                    if (response.body().getLstErrores().size() == 0) {

                        //MensajesGenericos.SHowMensajesGenericos("Success", "¡Éxito!", "La asistencia se registró correctamente..", AsistenciaActivity.this);
                        MensajesGenericos.SHowMensajesGenericosConAccion("Success", "¡Éxito!", "La asistencia se registró correctamente..", AsistenciaActivity.this, AceptarBotonMensaje());

                    } else {

                        ModelError error  = response.body().getLstErrores().get(0);
                        MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", error.getMensajeError(), AsistenciaActivity.this);

                    }


                }else{
                    ProgressBarGenerico.HideProgreess();

                    if(response.code()==404){
                        MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", "Verifique la configuración del servidor, Código 404", AsistenciaActivity.this);

                    }

                    if(response.code()==500){
                        MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", "Verifique la configuración del servidor, Código 500", AsistenciaActivity.this);

                    }


                }
            }

            @Override
            public void onFailure(Call<AsistenciaDiariaMarcas> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
                MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", t.getMessage(), AsistenciaActivity.this);

                ProgressBarGenerico.HideProgreess();

            }
        });
    }


    public void doWork() {
        runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void run() {
                try{
                    EditText txtCurrentTime= (EditText)findViewById(R.id.TxtFechaHora);

                    Calendar calendario = new GregorianCalendar();
                    Date horaactual = new Date();
                    calendario.setTime(horaactual);
                    hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
                    minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
                    segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);


                    String curTime = fechaActual() + " - " +  hora + ":" + minutos + ":" + segundos;
                    txtCurrentTime.setText(curTime);
                }catch (Exception e) {}
            }
        });
    }

    class CountDownRunner implements Runnable{
        // @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }catch(Exception e){
                }
            }
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private String fechaActual() {

        Date fecha = new Date();
        SimpleDateFormat formatoFecha = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        }

        return formatoFecha.format(fecha);
    }

    private void mostrarHora() {



        login = new Thread(this);
        login.start();
    }

    public void Hora() {
        Calendar calendario = new GregorianCalendar();
        Date horaactual = new Date();
        calendario.setTime(horaactual);
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    private void LoadTipoASistencia(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("Seleccione");
        arrayList.add("Ingreso");
        arrayList.add("Almuerzo");
        arrayList.add("Salida");

        ArrayAdapter<String> adapterSpTipoDocPNuevo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        adapterSpTipoDocPNuevo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        CboTipoAsistencia.setItems(arrayList);
    }


    @Override
    protected void onDestroy() {


        super.onDestroy();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        Thread current = Thread.currentThread();

        if(current != null){
            while (current == login) {
                Hora();
                TxtFechaHora.setText(" - "+   hora + ":" + minutos + ":" + segundos);
            }
        }


    }
}