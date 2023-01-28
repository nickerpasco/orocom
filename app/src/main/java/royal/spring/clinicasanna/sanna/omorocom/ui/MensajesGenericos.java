package royal.spring.clinicasanna.sanna.omorocom.ui;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import royal.spring.clinicasanna.R;

public class MensajesGenericos {

    public static Runnable ans_true = null;
    public static Runnable ans_true2 = null;
    public static Runnable ans_false = null;
    public static String TiempoCalculado ="";
    public static String TipoTiempo ="";



    public static void ShowMessageConfirmDosBotones(String TituloMensaje, String MensajeCUerpo, final Activity activity, Runnable bProcedure,Runnable aProcedure,String TextButtonSi,String TextButtonNo) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.item_question, viewGroup, false);

        ans_true = aProcedure;
        ans_false= bProcedure;


        Button BtnAceptar = (Button) dialogView.findViewById(R.id.BtnAceptar);
        ImageView BtnCerrar = (ImageView) dialogView.findViewById(R.id.BtnCerrar);
        Button BtnNo = (Button) dialogView.findViewById(R.id.BtnNO);
        TextView TxtTitle = (TextView) dialogView.findViewById(R.id.TxtNameCliente);
        TextView TxtMesaagee = (TextView) dialogView.findViewById(R.id.message);

        TxtTitle.setText(TituloMensaje);
        TxtMesaagee.setText(MensajeCUerpo);


        BtnAceptar.setText(TextButtonSi);

        if(TextButtonNo.equals("NO_OCULTAR_X")){
            BtnNo.setText("NO");
            BtnCerrar.setVisibility(View.INVISIBLE);
        }else{
            BtnNo.setText(TextButtonNo);
        }



        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(dialogView);


        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);


        BtnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans_true.run();
                dialog.dismiss();
            }
        });

        BtnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        BtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans_false.run();
                dialog.dismiss();
            }
        });



    }

    public static void SHowMensajesGenericosConAccion(String type, String titulo, String menssage, Context context,Runnable accionboton) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pruebas);
        ans_true = accionboton;


        ImageView ImgBoton = dialog.findViewById(R.id.ImgBoton);
        TextView title = dialog.findViewById(R.id.TxtNameCliente);
        TextView message = dialog.findViewById(R.id.message);
        Button read_btn = dialog.findViewById(R.id.read_btn);

        if(type.equals("Error")){
            ImgBoton.setImageResource(R.drawable.ic_error);
            title.setTextColor(Color.parseColor("#E11C1B"));
            title.setText(titulo);
            message.setText(menssage);
            read_btn.setBackgroundColor(Color.parseColor("#E11C1B"));
        }else if(type.equals("Success")){
            ImgBoton.setImageResource(R.drawable.ic_succes);
            title.setTextColor(Color.parseColor("#18830B"));
            title.setText(titulo);
            message.setText(menssage);
            read_btn.setBackgroundColor(Color.parseColor("#18830B"));
        }else if(type.equals("Info")){
            ImgBoton.setImageResource(R.drawable.ic_info);
            title.setTextColor(Color.parseColor("#D28C00"));
            title.setText(titulo);
            message.setText(menssage);
            read_btn.setBackgroundColor(Color.parseColor("#D28C00"));
        }

        // #E11C1B --> Rojo
        // #18830B --> Verde
        // #D28C00 --> Info

        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans_true.run();
                dialog.dismiss();
            }
        });



        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    public static void SHowMensajesGenericos(String type, String titulo, String menssage, Context context) {

        try {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.pruebas);

            ImageView ImgBoton = dialog.findViewById(R.id.ImgBoton);
            TextView title = dialog.findViewById(R.id.TxtNameCliente);
            TextView message = dialog.findViewById(R.id.message);
            Button read_btn = dialog.findViewById(R.id.read_btn);

            if(type.equals("Error")){
                ImgBoton.setImageResource(R.drawable.ic_error);
                title.setTextColor(Color.parseColor("#E11C1B"));
                title.setText(titulo);
                message.setText(menssage);
                read_btn.setBackgroundColor(Color.parseColor("#E11C1B"));
            }else if(type.equals("Success")){
                ImgBoton.setImageResource(R.drawable.ic_succes);
                title.setTextColor(Color.parseColor("#18830B"));
                title.setText(titulo);
                message.setText(menssage);
                read_btn.setBackgroundColor(Color.parseColor("#18830B"));
            }else if(type.equals("Info")){
                ImgBoton.setImageResource(R.drawable.ic_info);
                title.setTextColor(Color.parseColor("#D28C00"));
                title.setText(titulo);
                message.setText(menssage);
                read_btn.setBackgroundColor(Color.parseColor("#D28C00"));
            }

            // #E11C1B --> Rojo
            // #18830B --> Verde
            // #D28C00 --> Info

            read_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });



            dialog.show();
            dialog.setCancelable(false);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }catch (Exception e){
            Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
        }




    }

}