package royal.spring.clinicasanna.sanna.sanna;

import androidx.appcompat.app.AppCompatActivity;

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

public class CambiarContraseniaActivity extends AppCompatActivity {
    EditText TxtNombresPNuevo,TxtAPaternoPNuevo,TxtAPaternsssoPNuevo;
    ImageView BtnAbrirModal,atrasPrioridad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasenia);

        BtnAbrirModal = (ImageView) findViewById(R.id.BtnPacGuardar);
        atrasPrioridad = (ImageView) findViewById(R.id.atrasPrioridad);

        TxtNombresPNuevo = findViewById(R.id.TxtNombresPNuevo);
        TxtAPaternoPNuevo = findViewById(R.id.TxtAPaternoPNuevo);
        TxtAPaternsssoPNuevo = findViewById(R.id.TxtAPaternsssoPNuevo);


        BtnAbrirModal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Validar();
                } catch (SQLException e) {
                    e.printStackTrace();
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

    private void Validar() throws SQLException {

        DBHelper dbHelper = null;
        dbHelper = new DBHelper(this); // INSTANCIAR PAPUS
        String usuario = TxtNombresPNuevo.getText().toString();
        String pass = TxtAPaternoPNuevo.getText().toString();
        String passnew = TxtAPaternsssoPNuevo.getText().toString();
        if(usuario.equals("")){
            Toast.makeText(CambiarContraseniaActivity.this, "Ingrese Usuario", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.equals("")){
            Toast.makeText(CambiarContraseniaActivity.this, "Ingrese Contraseña", Toast.LENGTH_SHORT).show();
            return;
        }if(passnew.equals("")){
            Toast.makeText(CambiarContraseniaActivity.this, "Ingrese Contraseña Nueva", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Usuario> lis = (ArrayList<Usuario>) dbHelper.getAll(Usuario.class);

        if (ValidarUsuario(lis) !=null){
            // enviar Clave

            Usuario ca = ValidarUsuario(lis);
            // Toast.makeText(CambiarContraseniaActivity.this, "Tu clave es : " +ca.getContrasenia() , Toast.LENGTH_SHORT).show();

            Usuario paciente = new Usuario();
            paciente = ca;
            paciente.setContrasenia(passnew);
            dbHelper.update(paciente);
            Toast.makeText(this, "La contraseña se cambió correctamente...", Toast.LENGTH_SHORT).show();
            finish();


        }else{
            Toast.makeText(CambiarContraseniaActivity.this, "Usuario No encontrado", Toast.LENGTH_SHORT).show();
        }


    }


    private Usuario ValidarUsuario(List<Usuario> lis) {

        String usuario = TxtNombresPNuevo.getText().toString();
        String pass = TxtAPaternoPNuevo.getText().toString();


        for (Usuario item : lis){

            if(item.getUsuario().equals(usuario) && item.getContrasenia().equals(pass)){

                return item;
            }

        }

        return null;

    }


}