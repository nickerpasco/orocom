package royal.spring.clinicasanna.sanna.sanna;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Formatter;

import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.sanna.clases.Paciente;

public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.ViewHolderVentaDelDia> {

    ArrayList<Paciente> listaVentasdeldia;
    Context mContext;
    Activity activity;

    public PacienteAdapter(ArrayList<Paciente> listaVentasdeldia, Context mContext,Activity activity) {

        this.listaVentasdeldia = listaVentasdeldia;
        this.mContext = mContext;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PacienteAdapter.ViewHolderVentaDelDia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pac_list, null, false);
        return new PacienteAdapter.ViewHolderVentaDelDia(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PacienteAdapter.ViewHolderVentaDelDia holder, int position) {
        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        final DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);
        final PacienteAdapter.ViewHolderVentaDelDia viewHolder = (PacienteAdapter.ViewHolderVentaDelDia) holder;
        final Paciente item = listaVentasdeldia.get(position);
        // holder.TxtNombrePedido.setText(item.getIdVenta());


        holder.textView.setText(item.getNombres());
        holder.TxtFecha.setText("Celular : "+item.getCelular());
        holder.txtdireccionPedido.setText("Dir : " +item.getDireccion());
        holder.txtEstadoPedido.setText(item.getSeguro());
        holder.NroDco.setText("Documento : " +item.getDocumento());
        holder.TxtMontoTotal.setText("Nro Paciente :" + String.format("%s", new Formatter().format("%09d", item.getIdPaciente())));
        holder.linearPacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                Intent intent = new Intent(mContext,RegistroPacienteActivity.class);
                intent.putExtra("ID_pac",item.getIdPaciente()+"");
                mContext.startActivity(intent);

                 */


                SharedPreferences preferences = mContext.getSharedPreferences("PrefeM", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = preferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(item);
                prefsEditor.putString("ModelConfiguracion", json);
                prefsEditor.commit();



                activity.finish();



            }
        });

    }



    @Override
    public int getItemCount() {
        return listaVentasdeldia.size();
    }

    public class ViewHolderVentaDelDia extends RecyclerView.ViewHolder {
        TextView txtNombrellegada, txtdireccionPedido, NroDco, txtEstadoPedido, TxtMontoTotal, TxtFecha;
        TextView tiempo, textView;
        LinearLayout linearPacientes;

        public ViewHolderVentaDelDia(View itemView) {
            super(itemView);
            // TxtNombrePedido = itemView.findViewById(R.id.shopping_cart_item_name_text_view);

            linearPacientes = itemView.findViewById(R.id.LinearPacientes);
            txtdireccionPedido = itemView.findViewById(R.id.txtdireccionPedido);
            txtEstadoPedido = itemView.findViewById(R.id.txtEstadoPedido);
            TxtFecha = itemView.findViewById(R.id.TxtFecha);
            NroDco = itemView.findViewById(R.id.NroDco);
            TxtMontoTotal = itemView.findViewById(R.id.TxtMontoTotal);
            tiempo = itemView.findViewById(R.id.tiempo);
            textView = itemView.findViewById(R.id.TxtTest);
        }
    }
}
