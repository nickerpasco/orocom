package royal.spring.clinicasanna.sanna.omorocom;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
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

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import royal.spring.clinicasanna.R;

public class AsistenciaActivity extends AppCompatActivity implements Runnable {

    String hora, minutos, segundos;
    Thread login;
    EditText TxtFechaHora ;
    MaterialSpinner CboTipoAsistencia ;
    ImageView BtnRegresar ;
    ImageView BtnCheck ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TxtFechaHora = findViewById(R.id.TxtFechaHora);
        CboTipoAsistencia = findViewById(R.id.CboTipoAsistencia);
        BtnRegresar = findViewById(R.id.BtnRegresar);
        BtnCheck = findViewById(R.id.BtnCheck);

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