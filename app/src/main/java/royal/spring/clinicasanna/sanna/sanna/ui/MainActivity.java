package royal.spring.clinicasanna.sanna.sanna.ui;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;*/
import royal.spring.clinicasanna.sanna.sanna.DBHelper;
import royal.spring.clinicasanna.sanna.sanna.FuncionesAdapter;
import royal.spring.clinicasanna.sanna.sanna.InicarLoginActivity;
import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.sanna.RegistroFuncionesVitalesActivity;
import royal.spring.clinicasanna.sanna.sanna.VerticalSpaceItemDecoration;
import royal.spring.clinicasanna.sanna.sanna.clases.FuncionesVitales;

//import android.support.v4.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    TextView BtnRegistro;

    ArrayList<FuncionesVitales> listaPedidos;
    RecyclerView RecyclerPedidos;
    FuncionesAdapter adapter;
    //private SwipeRefreshLayout swipeContainer;
    ImageView pin_icon, back;
    TextView address_selected_textview;
    Button btnMapa;
    DBHelper dbHelper;

    TextView Daonloaad;
    /*WritableWorkbook workbook;*/
    ImageView BtnExcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    private void crearCvs() throws SQLException {

        StringBuilder csvContenido = new StringBuilder();
        List<FuncionesVitales> listaFv = (ArrayList<FuncionesVitales>) dbHelper.getAll(FuncionesVitales.class);
        csvContenido.append("ID,Paciente,% Saturacion,Temperatura,Peso,Talla,IMC,Comentario,Fecha\n");
        for (FuncionesVitales fv : listaFv) {
            csvContenido.append(fv.getIdFuncionVital()).append(",").append(fv.getPaciente()).append(",").append(fv.getSaturacion()).append(",")
                    .append(fv.getTemperatura()).append(",").append(fv.getPeso()).append(",").append(fv.getTalla()).append(",")
                    .append(fv.getIMC()).append(",").append(fv.getComentario()).append(",").append(fv.getFecha()).append("\n");
        }
        String csv = csvContenido.toString();

        Log.w("ENVIO", csv);
        File file = null;
        File dir = new File(getFilesDir(), "csv");
        dir.mkdirs();
        file = new File(dir, "ClinicaSanna_" + fecha() + ".csv");
        FileOutputStream out = null;
        Uri u1 = FileProvider.getUriForFile(this, "royal.spring.clinicasanna.fileprovider", file);

        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            out.write(csv.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "BD Clinica Sanna");
        sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        sendIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        sendIntent.putExtra(Intent.EXTRA_STREAM, u1);
        sendIntent.setType("text/plain");
        try {
            startActivity(Intent.createChooser(sendIntent, "Enviar CSV a:"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No se pudo completar el proceso de envío.", Toast.LENGTH_SHORT).show();
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

/*
        List<FuncionesVitales> lis = (ArrayList<FuncionesVitales>) dbHelper.getAll(FuncionesVitales.class);

        listaPedidos = (ArrayList<FuncionesVitales>) lis;


        adapter = new FuncionesAdapter(listaPedidos, this);
        RecyclerPedidos.setAdapter(adapter);
        adapter.notifyDataSetChanged();

 */
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
