package royal.spring.clinicasanna.sanna.sanna;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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

public class RegistroActivity extends AppCompatActivity {

    ImageView BtnGuardar,atrasPrioridad;
    ProgressDialog mPDialog;
    EditText TxtTxtApellidos,TxtUsuario,TxtContrasenia,TxtCelularPNuevo,TxtCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        BtnGuardar = (ImageView)findViewById(R.id.BtnGuardar);
        atrasPrioridad = (ImageView)findViewById(R.id.atrasPrioridad);
        TxtTxtApellidos = (EditText)findViewById(R.id.TxtNombresApellidos);
        TxtUsuario = (EditText)findViewById(R.id.TxtUsuario);
        TxtContrasenia = (EditText)findViewById(R.id.TxtContrasenia);
        TxtCelularPNuevo = (EditText)findViewById(R.id.txtPacCel);
        TxtCorreo = (EditText)findViewById(R.id.TxtCorreo);

        atrasPrioridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BtnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                EsperarTask d = new EsperarTask();
                d.setContext(RegistroActivity.this);
                d.execute();

                 */



                GuardarUsuario();
            }
        });
    }

    private void GuardarUsuario() {
        DBHelper dbHelper;
        dbHelper = new DBHelper(this); // INSTANCIAR PAPUS
        try {
            Usuario usuario = new Usuario();

            if(TxtTxtApellidos.length()==0){
                Toast.makeText(this, "Ingrese Apellido", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TxtUsuario.length()==0){
                Toast.makeText(this, "Ingrese Usuario", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TxtContrasenia.length()==0){
                Toast.makeText(this, "Ingrese Contraseña", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TxtCelularPNuevo.length()==0){
                Toast.makeText(this, "Ingrese Celular", Toast.LENGTH_SHORT).show();
                return;
            }else{

                if(TxtCelularPNuevo.length()<9){
                    Toast.makeText(this, "El celular debe tener 9 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
            if(TxtCorreo.length()==0){
                Toast.makeText(this, "Ingrese Correo", Toast.LENGTH_SHORT).show();
                return;
            }else{
                boolean ok =  repeatedString(TxtCorreo.getText().toString(),"@",-1);
                if(ok){
                    Toast.makeText(this, "El correo tiene dos a más arrobas", Toast.LENGTH_SHORT).show();
                    return;
                }


                boolean valid = isValidEmailAddress(TxtCorreo.getText().toString());

                if(!valid){
                    Toast.makeText(this, "El correo no tiene el formato correcto", Toast.LENGTH_SHORT).show();
                    return;
                }


            }



            dbHelper = new DBHelper(this); // INSTANCIAR PAPUS
            try {

                List<Usuario> lis = (ArrayList<Usuario>) dbHelper.getAll(Usuario.class);

                if (Acceder(lis)){


                    Toast.makeText(this, "El usuario : " + TxtUsuario.getText().toString() + " Ya existe en la BD..", Toast.LENGTH_SHORT).show();

                    return;

                }else{
                    Toast.makeText(this, "Usuario No encontrado", Toast.LENGTH_SHORT).show();
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



            usuario.setApellidosNombres(TxtTxtApellidos.getText().toString());
            usuario.setCelular(TxtCelularPNuevo.getText().toString());
            usuario.setContrasenia(TxtContrasenia.getText().toString());
            usuario.setCorreo(TxtCorreo.getText().toString());
            usuario.setUsuario(TxtUsuario.getText().toString());
            dbHelper.create(usuario);
            List<Usuario> lis = (ArrayList<Usuario>) dbHelper.getAll(Usuario.class);
            Toast.makeText(this, "Usuario : " + TxtUsuario.getText().toString()  +" Registrado ", Toast.LENGTH_SHORT).show();
            finish();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }




    private boolean Acceder(List<Usuario> lis) {

        String usuario = TxtUsuario.getText().toString();



        for (Usuario item : lis){

            if(item.getUsuario().equals(usuario)){



                return true;
            }

        }

        return false;

    }

    public static boolean repeatedString(String str, String repeat, int lastIndex) {
        int next = str.indexOf(repeat, lastIndex+repeat.length());

        if(next == -1) return false;
        else if(next-lastIndex == repeat.length()) return true;
        else return repeatedString(str, repeat, next);
    }

    private boolean ValidarCarates(String cadena) {


        int contador = 0;
        char caracter = 0;

        while (cadena.length() != 0) { // mientras la cadena tenga algún carácter la recorremos
            int contadorAux = 0;
            for (int j = 0; j < cadena.length(); j++) { // recorremos la cadena para contar los caracteres del indice cero
                if (cadena.charAt(0) == cadena.charAt(j)) {
                    contadorAux++;
                }
            }

            if (contadorAux > contador) { // si el contador del nuevo caracter es mayor al que ya estaba guardado, lo cambiamos
                contador = contadorAux;
                caracter = cadena.charAt(0);
            }

            // borramos los carácteres contados para ahorrar entrar mas veces para contarlos otra vez
            cadena = cadena.replaceAll(cadena.charAt(0) + "", "");
        }

        if(contador>1){
            return true;
        }else{
            return false;
        }
    }


    private class EsperarTask extends AsyncTask<Void,Void,Void>
    {

        private Context mContext;
        void setContext(Activity context) {
            mContext = context;
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPDialog = new ProgressDialog(mContext);
                    mPDialog.setMessage("Guardando...");
                    mPDialog.setIndeterminate(true);
                    mPDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mPDialog.setCancelable(false);
                    mPDialog.show();
                }
            });
        }

        @Override
        protected Void doInBackground(Void... voids) {
            GuardarUsuario();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {


            try{


                if (mPDialog != null)
                    mPDialog.dismiss();
                Toast.makeText(RegistroActivity.this, "Se guardó correctamente..", Toast.LENGTH_SHORT).show();
            }catch(Exception e){

                e.printStackTrace();

            }
        }
    }
}