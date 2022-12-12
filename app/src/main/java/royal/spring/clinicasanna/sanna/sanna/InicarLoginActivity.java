package royal.spring.clinicasanna.sanna.sanna;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.omorocom.Menu_Principal_Padre;
import royal.spring.clinicasanna.sanna.sanna.clases.Usuario;
import royal.spring.clinicasanna.sanna.sanna.ui.MainActivity;
import royal.spring.clinicasanna.sanna.sanna.ui.NotificaionActivity;

public class InicarLoginActivity extends AppCompatActivity {

    Button button;
    TextView TvRegitro,textView4,textView52;
    EditText editText,editText2;
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


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

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




                if(usuario.equals("admin") && pass.equals("admin")){
                    startActivity(new Intent(InicarLoginActivity.this, Menu_Principal_Padre.class));
                }else{
                    Toast.makeText(InicarLoginActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                    return;
                }






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