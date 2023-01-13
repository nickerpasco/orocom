package royal.spring.clinicasanna.sanna.sanna.Adaptadores;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Callback;
import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.omorocom.utils.FuncionesPrincipales;
import royal.spring.clinicasanna.sanna.sanna.clases.FuncionesVitales;
import royal.spring.clinicasanna.sanna.sanna.clases.Model_Asistencia;

public class AsistenciaAdapter extends RecyclerView.Adapter<AsistenciaAdapter.MyHolder> {
    ArrayList<Model_Asistencia> lista;
    Context contexto;

    public AsistenciaAdapter(ArrayList<Model_Asistencia> lista, Context contexto) {
        this.lista = lista;
        this.contexto = contexto;
    }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_asistencia, parent, false);
        return new MyHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final Model_Asistencia item = lista.get(position);

//        Toast.makeText(contexto, ""+item.getCompaniasocio(), Toast.LENGTH_SHORT).show();


       String nombres = FuncionesPrincipales.parseMayuscula(FuncionesPrincipales.parseTrimMinuscula(item.getNombrecompleto()));

        holder.txtnombres.setText(nombres );
        holder.txtlugar.setText(item.getLugarMarcacion());


        String fechaita = FuncionesPrincipales.getFechaDescripcion(item.getFechaMarcacionString());

        holder.txtfechamarcacion.setText(fechaita);

        String tipo = "";


        if (item.getTipoMarcacion().equals("IN")){
            tipo = "Ingreso";
        }

        if (item.getTipoMarcacion().equals("SA")){
            tipo = "Salida";
        }

        if (item.getTipoMarcacion().equals("AL")){
            tipo = "Almuerzo";
        }

        if (item.getTipoMarcacion().equals("XX")){
            tipo = "Sin Información";
        }

        holder.txttipomarcacion.setText(tipo);
        /*
        holder.txtPaciente.setText((1+position)+". "+ funcionesV.getPaciente());
        holder.txtComentario.setText(funcionesV.getComentario());
        holder.txtPeso.setText(String.valueOf(funcionesV.getPeso()));
        holder.txtTalla.setText(String.valueOf(funcionesV.getTalla()));
        holder.txtTemperatura.setText(String.valueOf(funcionesV.getTemperatura()));
        holder.txtSaturacion.setText(String.valueOf(funcionesV.getSaturacion()));
        holder.txtIMC.setText(String.valueOf(funcionesV.getIMC()));
        holder.txtComentario.setSelected(true);

         */

       // Toast.makeText(contexto, ""+lista.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView txtnombres, txtlugar, txtfechamarcacion, txttipomarcacion;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            txtnombres = itemView.findViewById(R.id.text);
            txttipomarcacion = itemView.findViewById(R.id.txttipoasistencia);
            txtfechamarcacion = itemView.findViewById(R.id.txtFecha);
            txtlugar = itemView.findViewById(R.id.txtLugarMarcacion);



        }
    }
}
