package royal.spring.clinicasanna.sanna.sanna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.omorocom.APIUtils;
import royal.spring.clinicasanna.sanna.omorocom.Menu_Principal_Padre;
import royal.spring.clinicasanna.sanna.omorocom.ui.LoginResponse;
import royal.spring.clinicasanna.sanna.omorocom.ui.MensajesGenericos;
import royal.spring.clinicasanna.sanna.omorocom.ui.ModelError;
import royal.spring.clinicasanna.sanna.omorocom.ui.ProgressBarGenerico;
import royal.spring.clinicasanna.sanna.omorocom.ui.UsuarioService;
import royal.spring.clinicasanna.sanna.sanna.clases.Usuario;
import royal.spring.clinicasanna.sanna.sanna.ui.MainActivity;
import royal.spring.clinicasanna.sanna.sanna.ui.NotificaionActivity;

public class InicarLoginActivity extends AppCompatActivity {

    Button button;
    TextView TvRegitro,textView4,textView52;
    EditText editText,editText2;

    UsuarioService usuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicar_login);

        button = (Button)findViewById(R.id.button);
        TvRegitro = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView52 = findViewById(R.id.textView52);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        usuarioService = APIUtils.getUsuarioService();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        ActivarGPS();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_tv);
                button.startAnimation(animFadein);



                String usuario = editText.getText().toString();
                String pass = editText2.getText().toString();
                if(usuario.equals("")){
                    Toast.makeText(InicarLoginActivity.this, "Ingrese Usuario", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.equals("")){
                    Toast.makeText(InicarLoginActivity.this, "Ingrese Contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

               // ValidarUsuario();



            Ingresar();






            }
        });

        TvRegitro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_tv);
                TvRegitro.startAnimation(animFadein);
                startActivity(new Intent(InicarLoginActivity.this, NotificaionActivity.class));
            }
        });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_tv);
                textView4.startAnimation(animFadein);
                startActivity(new Intent(InicarLoginActivity.this,RecuperarContraseniaActivity.class));
            }
        });

        textView52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_tv);
                textView52.startAnimation(animFadein);
                startActivity(new Intent(InicarLoginActivity.this,CambiarContraseniaActivity.class));
                //Toast.makeText(InicarLoginActivity.this, "En Mantenimiento...", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public Runnable ActivarGPS() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        return new Runnable() {
            public void run() {
                //ImprimirComprobate();
                // showSettingsAlert();

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                InicarLoginActivity.this.startActivity(intent);


            }
        };
    }

    private void Ingresar() {

       // Toast.makeText(this, "Validando usuario en línea..", Toast.LENGTH_SHORT).show();
        ProgressBarGenerico.LoadProgress(this);

        String usuario = editText.getText().toString();
        String pass = editText2.getText().toString();
       LoginResponse request = new LoginResponse();
        request.setUsuario(usuario);
        request.setClave(pass);

        Gson gson = new Gson();
        String json = gson.toJson(request);

        Call<LoginResponse> call = usuarioService.SendSesionLogin(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    ProgressBarGenerico.HideProgreess();

                    if (response.body().getLstErrores().size() == 0) {

                        APIUtils.Nombnre = response.body().getBusqueda().trim();
                        APIUtils.Usuario = response.body().getUsuario().trim();
                        APIUtils.PrimerNombre = response.body().getPrimerNombre().trim();
                        APIUtils.Persona = response.body().getPersona();
                        startActivity(new Intent(InicarLoginActivity.this, Menu_Principal_Padre.class));


                    } else {

                        ModelError error  = response.body().getLstErrores().get(0);
                        MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", error.getMensajeError(), InicarLoginActivity.this);

                    }


                }else{
                    ProgressBarGenerico.HideProgreess();

                    if(response.code()==404){
                        MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", "Verifique la configuración del servidor, Código 404", InicarLoginActivity.this);

                    }

                    if(response.code()==500){
                        MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", "Verifique la configuración del servidor, Código 500", InicarLoginActivity.this);

                    }


                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
                MensajesGenericos.SHowMensajesGenericos("Error", "¡Error!", t.getMessage(), InicarLoginActivity.this);

                ProgressBarGenerico.HideProgreess();

            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        //finish();
    }

    private void ValidarUsuario() {



        DBHelper dbHelper;
        dbHelper = new DBHelper(this); // INSTANCIAR PAPUS
        try {

            List<Usuario> lis = (ArrayList<Usuario>) dbHelper.getAll(Usuario.class);

            if (Acceder(lis)){


                startActivity(new Intent(InicarLoginActivity.this, Menu_Principal_Padre.class));

            }else{
                Toast.makeText(this, "Usuario No encontrado", Toast.LENGTH_SHORT).show();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private boolean Acceder(List<Usuario> lis) {

        String usuario = editText.getText().toString();
        String pass = editText2.getText().toString();


        for (Usuario item : lis){

            if(item.getUsuario().equals(usuario) && item.getContrasenia().equals(pass)){


                SharedPreferences preferences = getSharedPreferences("PrefeM", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = preferences.edit();
                prefsEditor.putString("Usuario", item.getUsuario());
                prefsEditor.commit();


                return true;
            }

        }

        return false;

    }
}