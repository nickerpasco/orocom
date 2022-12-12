package royal.spring.clinicasanna.sanna.sanna;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.sanna.clases.FuncionesVitales;
import royal.spring.clinicasanna.sanna.sanna.clases.Paciente;
import royal.spring.clinicasanna.sanna.sanna.ui.ListadoPcientes;

public class RegistroFuncionesVitalesActivity extends AppCompatActivity {
    ImageView btnRegistroFV, btnAtras, btnBuscaPaciente;
    EditText edPaciente, edSaturacion, edTemperatura, edPeso, edTalla, edComentario;
    TextInputEditText txtImc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_funciones_vitales);
        edPaciente = findViewById(R.id.edPaciente);
        edSaturacion = findViewById(R.id.edSaturacion);
        edTemperatura = findViewById(R.id.edTemperatura);
        edPeso = findViewById(R.id.edPeso);
        edTalla = findViewById(R.id.edTalla);
        edComentario = findViewById(R.id.edComentario);
        txtImc = findViewById(R.id.tiEdIMC);
        btnRegistroFV = findViewById(R.id.BtnRegistroFV);
        btnAtras = findViewById(R.id.btnAtrasFV);
        btnBuscaPaciente = findViewById(R.id.btnBuscaPaciente);

        VerDatos();
        edPeso.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                IMC();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        edTalla.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                IMC();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btnRegistroFV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                if(edPaciente.length()==0){
                    Toast.makeText(RegistroFuncionesVitalesActivity.this, "Tiene que seleccionar un paciente", Toast.LENGTH_SHORT).show();
                    return;
                }
                GuardarFuncionV();

            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnBuscaPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_tv);
                btnBuscaPaciente.startAnimation(animFadein);
                startActivity(new Intent(RegistroFuncionesVitalesActivity.this, ListadoPcientes.class));
            }
        });

    }

    private void IMC() {
        if (edPeso.length() != 0 && edTalla.length() != 0) {
            double p = Double.parseDouble(edPeso.getText().toString());
            double t = Double.parseDouble(edTalla.getText().toString());
            double imc = p / (Math.pow(t, 2));
            Log.w("IMC: ", String.valueOf(imc));
            DecimalFormat formato = new DecimalFormat("#0.00");
            txtImc.setText(formato.format(imc));
        } else {
            txtImc.setText("");
        }
    }

    private void VerDatos(){
        DBHelper dbHelper;
        dbHelper = new DBHelper(this);

        FuncionesVitales fV = new FuncionesVitales();
        try {
            Bundle id = getIntent().getExtras();
            if(id != null){
                fV = dbHelper.getById(FuncionesVitales.class,id.getString("ID_fv"));
                edPaciente.setText(fV.getPaciente());
                edSaturacion.setText(String.valueOf(fV.getSaturacion()));
                edTemperatura.setText(String.valueOf(fV.getTemperatura()));
                edTalla.setText(String.valueOf(fV.getTalla()));
                edPeso.setText(String.valueOf(fV.getPeso()));
                edComentario.setText(fV.getComentario());
                IMC();
                //Toast.makeText(this, fV.getPaciente(), Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void GuardarFuncionV() {
        if (edPaciente.length() !=0 && edSaturacion.length() != 0 && edTemperatura.length() != 0
                && edPeso.length() != 0 && edTalla.length() != 0 && txtImc.length() != 0 && edComentario.length() != 0) {

            DBHelper dbHelper;
            dbHelper = new DBHelper(this);

            try {

                Gson gson = new Gson();
                SharedPreferences preferences = getSharedPreferences( "PrefeM", Context.MODE_PRIVATE );
                String json = preferences.getString("ModelConfiguracion", "");
                Paciente ob = gson.fromJson(json, Paciente.class);

                if(ob==null){
                    return;
                }



                FuncionesVitales funcionesV = new FuncionesVitales();
                funcionesV.setPaciente(edPaciente.getText().toString());
                funcionesV.setDireccion(ob.getDireccion());
                funcionesV.setSaturacion(Double.parseDouble(edSaturacion.getText().toString()));
                funcionesV.setTemperatura(Double.parseDouble(edTemperatura.getText().toString()));
                funcionesV.setPeso(Double.parseDouble(edPeso.getText().toString()));
                funcionesV.setTalla(Double.parseDouble(edTalla.getText().toString()));
//                funcionesV.setIMC(Double.parseDouble(txtImc.getText().toString()));
                funcionesV.setComentario(edComentario.getText().toString());
                funcionesV.setEstado("ATENDIDO");
                funcionesV.setFecha(formatearFecha(new java.util.Date()));
                dbHelper.create(funcionesV);
                Toast.makeText(this, "Datos Registrados", Toast.LENGTH_SHORT).show();
               finish();
            } catch ( SQLException throwables) {
                throwables.printStackTrace();
            }

        }else {Toast.makeText(this, "Complete todos los espacios", Toast.LENGTH_SHORT).show();}
    }

    public static String formatearFecha(Date date) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy

        String strDate = sdfDate.format(date);
        return strDate;
    }


    @Override
    protected void onStart() {
        super.onStart();

        Gson gson = new Gson();
        SharedPreferences preferences = getSharedPreferences( "PrefeM", Context.MODE_PRIVATE );
        String json = preferences.getString("ModelConfiguracion", "");
        Paciente ob = gson.fromJson(json, Paciente.class);

        if(ob==null){
            return;
        }
        edPaciente.setText(ob.getNombres());
    }
}