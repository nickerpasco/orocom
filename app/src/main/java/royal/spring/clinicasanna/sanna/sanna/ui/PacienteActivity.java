package royal.spring.clinicasanna.sanna.sanna.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import jxl.write.WritableWorkbook;
import royal.spring.clinicasanna.sanna.sanna.DBHelper;
import royal.spring.clinicasanna.sanna.sanna.PacienteAdapter;
import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.sanna.RegistroPacienteActivity;
import royal.spring.clinicasanna.sanna.sanna.VerticalSpaceItemDecoration;
import royal.spring.clinicasanna.sanna.sanna.clases.Paciente;

//import android.support.v4.widget.SwipeRefreshLayout;

public class PacienteActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_pacientes);

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
                startActivity(new Intent(PacienteActivity.this, RegistroPacienteActivity.class));

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

    @Override
    protected void onStop() {
        super.onStop();
        //  finish();
    }

    /*
        void createExcelSheet() {
            //File futureStudioIconFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
            String csvFile = "ExcelsheetName.xls";
            java.io.File futureStudioIconFile = new java.io.File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + "/" + csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            try {
                workbook = Workbook.createWorkbook(futureStudioIconFile, wbSettings);
                createFirstSheet();
    //            createSecondSheet();
                //closing cursor
                workbook.write();
                workbook.close();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                File file = new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        + "/" + csvFile);
                Uri path = FileProvider.getUriForFile(getApplicationContext(), "com.something.racecalculator.fileprovide", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(path, "application/vnd.ms-excel");
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void createFirstSheet() {
            try {
                List<Bean> listdata = new ArrayList<>();

                listdata.add(new Bean("mr", "firstName1", "middleName1", "lastName1"));
                listdata.add(new Bean("mr", "firstName1", "middleName1", "lastName1"));
                listdata.add(new Bean("mr", "firstName1", "middleName1", "lastName1"));
                //Excel sheet name. 0 (number)represents first sheet
                WritableSheet sheet = workbook.createSheet("sheet1", 0);
                // column and row title
                sheet.addCell(new Label(0, 0, "NameInitial"));
                sheet.addCell(new Label(1, 0, "firstName"));
                sheet.addCell(new Label(2, 0, "middleName"));
                sheet.addCell(new Label(3, 0, "lastName"));

                for (int i = 0; i < listdata.size(); i++) {
                    sheet.addCell(new Label(0, i + 1, listdata.get(i).getInitial()));
                    sheet.addCell(new Label(1, i + 1, listdata.get(i).getFirstName()));
                    sheet.addCell(new Label(2, i + 1, listdata.get(i).getMiddleName()));
                    sheet.addCell(new Label(3, i + 1, listdata.get(i).getLastName()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            return false; //I have tried here true also
        }
        return super.onKeyDown(keyCode, event);
    }

    private String fecha() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(c);
    }
}
