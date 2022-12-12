package royal.spring.clinicasanna.sanna.sanna;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Locale;

import royal.spring.clinicasanna.R;

public class BuscarDniActivity extends AppCompatActivity {

    ImageView BtnPacGuardar;
    EditText TxtNroDocumento;

    private TextToSpeech mTTS;
    private EditText mEditText;
    private SeekBar mSeekBarPitch;
    private SeekBar mSeekBarSpeed;
    //private Button mButtonSpeak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_dni);

        BtnPacGuardar =(ImageView)findViewById(R.id.BtnPacGuardar);
        //TxtNroDocumento =(EditText)findViewById(R.id.TxtNroDocumento);
        mEditText =(EditText)findViewById(R.id.TxtNroDocumento);
        mSeekBarPitch = findViewById(R.id.seek_bar_pitch);
        mSeekBarSpeed = findViewById(R.id.seek_bar_speed);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {

                    Locale loc = new Locale ("spa", "ESP");
                    int result = mTTS.setLanguage(loc);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                     //   mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        BtnPacGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = mEditText.getText().toString();
                if (dni.length()==0){
                    Toast.makeText(BuscarDniActivity.this, "Ingrese Documento..", Toast.LENGTH_SHORT).show();
                    return;
                }
                GetPersona(dni);

            }
        });
    }

    private void GetPersona(String dni) {

        ProgressDialog mPDialog;
        mPDialog = new ProgressDialog(this);
        mPDialog.setMessage("Buscando...");
        mPDialog.setIndeterminate(true);
        mPDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mPDialog.setCancelable(false);
        mPDialog.show();
        String url = "https://api.apis.net.pe//v1/dni?numero="+dni;
        RequestQueue queue = Volley.newRequestQueue(this);

        //-12.0571092,-76.9660208
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject json = new JSONObject(response);
                    String Nombres = json.getString("nombre");
                    String numero =  String.format("%08d", (int)(Math.random()*10+1));
                    showCustomDialog(Nombres,numero);
                    speak(Nombres);
                    mPDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                    mPDialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("ERROR_MAPA", error.getMessage());

                mPDialog.dismiss();
            }
        });

        queue.add(stringRequest);

    }


    private void showCustomDialog(String nombres,String Numero) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_pok, viewGroup, false);

        Button DetaOr = (Button) dialogView.findViewById(R.id.BtnOkMessage);
        TextView TvTituloMessage = (TextView) dialogView.findViewById(R.id.TvTituloMessage);
        TextView TvDetalleMessage = (TextView) dialogView.findViewById(R.id.TvDetalleMessage);

        TvTituloMessage.setText("¡Hola  "+nombres+ " !");
        TvDetalleMessage.setText("Tu Número de Atención es : " +Numero);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        DetaOr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {


                    alertDialog.dismiss();

                } catch (Exception e) {
                    Log.i("ERROR_ABRIR_MENSAJE", "" + e);
                }

            }
        });


        //Now we need an AlertDialog.Builder object

    }


    private void speak(String text) {
        //String text = mEditText.getText().toString();

        String mensaje = "Hola   " +text + ". Es su turno de atención. ";

        float pitch = (float) mSeekBarPitch.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) mSeekBarSpeed.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(mensaje, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }
}