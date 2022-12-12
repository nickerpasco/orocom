package royal.spring.clinicasanna.sanna.sanna.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import royal.spring.clinicasanna.sanna.sanna.DBHelper;
import royal.spring.clinicasanna.sanna.sanna.PacienteAdapter;
import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.sanna.RegistroPacienteActivity;
import royal.spring.clinicasanna.sanna.sanna.VerticalSpaceItemDecoration;
import royal.spring.clinicasanna.sanna.sanna.clases.Paciente;

public class ListadoPcientes extends AppCompatActivity {
    TextView BtnRegistro;

    ArrayList<Paciente> listapaciente;
    RecyclerView RecyclerPedidos;
    PacienteAdapter adapter;
    //private SwipeRefreshLayout swipeContainer;
    ImageView pin_icon, back;
    TextView address_selected_textview;
    Button btnMapa;
    DBHelper dbHelper;

    TextView Daonloaad;
    //WritableWorkbook workbook;
    ImageView BtnExcel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_pcientes);



        BtnRegistro = (TextView) findViewById(R.id.BtnRegistro);

        back = (ImageView) findViewById(R.id.back);

        listapaciente = new ArrayList<>();
        RecyclerPedidos = (RecyclerView) findViewById(R.id.RecyclerPedidosCab);

        //swipeContainer = (SwipeRefreshLayout)findViewById( R.id.Refreshcars );

        RecyclerPedidos.setLayoutManager(new LinearLayoutManager(this));
        RecyclerPedidos.addItemDecoration(new VerticalSpaceItemDecoration(20));

        dbHelper = new DBHelper(this);


        BtnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_tv);
                BtnRegistro.startAnimation(animFadein);
                startActivity(new Intent(ListadoPcientes.this, RegistroPacienteActivity.class));

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_tv);
                back.startAnimation(animFadein);


                finish();

            }
        });

        try {
            CargarFunciones();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        try {
            CargarFunciones();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        super.onStart();
    }


    private void CargarFunciones() throws SQLException {


        List<Paciente> lis = (ArrayList<Paciente>) dbHelper.getAll(Paciente.class);

        listapaciente = (ArrayList<Paciente>) lis;


        adapter = new PacienteAdapter(listapaciente, this,this);
        RecyclerPedidos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}