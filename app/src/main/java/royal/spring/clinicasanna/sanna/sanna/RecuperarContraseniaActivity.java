package royal.spring.clinicasanna.sanna.sanna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.sanna.clases.Usuario;

public class RecuperarContraseniaActivity extends AppCompatActivity {

    EditText editText,editText2;
    ImageView BtnAbrirModal,atrasPrioridad;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasenia);
        BtnAbrirModal = (ImageView) findViewById(R.id.BtnPacGuardar);
        atrasPrioridad = (ImageView) findViewById(R.id.atrasPrioridad);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);


        BtnAbrirModal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper;
                dbHelper = new DBHelper(RecuperarContraseniaActivity.this); // INSTANCIAR PAPUS
                try {


                    String usuario = editText.getText().toString();
                    String pass = editText2.getText().toString();
                    if(usuario.equals("")){
                        Toast.makeText(RecuperarContraseniaActivity.this, "Ingrese Usuario", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(pass.equals("")){
                        Toast.makeText(RecuperarContraseniaActivity.this, "Ingrese Celular", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(pass.length()<9){
                        Toast.makeText(RecuperarContraseniaActivity.this, "El celular debe tener 9 caracteres", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<Usuario> lis = (ArrayList<Usuario>) dbHelper.getAll(Usuario.class);

                    if (ValidarUsuario(lis) !=null){
                        // enviar Clave

                        Usuario ca = ValidarUsuario(lis);
                        Toast.makeText(RecuperarContraseniaActivity.this, "Tu clave es : " +ca.getContrasenia() , Toast.LENGTH_SHORT).show();
                        createNotification(ca.getContrasenia());
                    }else{
                        Toast.makeText(RecuperarContraseniaActivity.this, "Usuario No encontrado", Toast.LENGTH_SHORT).show();
                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        atrasPrioridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void createNotification(String clave){

        Intent intent = new Intent( this, RecuperarContraseniaActivity.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        //Uri soundUri = RingtoneManager.getDefaultUri( R.raw.ic_avisom );

        //PendingIntent pendingIntent = PendingIntent.getActivity( this, 0, intent, PendingIntent.FLAG_ONE_SHOT );
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, RecuperarContraseniaActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        String mensaje =  "Tu clave es : " + clave;


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.sanna);
        builder.setContentTitle("¡Recuperación de Contraseña!");
        builder.setContentText(mensaje);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //builder.setDefaults(R.raw.ic_avisom);
        //builder.setSound(soundUri);
        builder.setAutoCancel(true);
        //builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setContentIntent(contentIntent);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }



    private Usuario ValidarUsuario(List<Usuario> lis) {

        String usuario = editText.getText().toString();
        String pass = editText2.getText().toString();


        for (Usuario item : lis){

            if(item.getUsuario().equals(usuario) && item.getCelular().equals(pass)){

                return item;
            }

        }

        return null;

    }
}