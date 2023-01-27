package royal.spring.clinicasanna.sanna.omorocom.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import royal.spring.clinicasanna.databinding.FragmentHomeBinding;
import royal.spring.clinicasanna.sanna.omorocom.APIUtils;
import royal.spring.clinicasanna.sanna.omorocom.Menu_Principal_Padre;
import royal.spring.clinicasanna.sanna.omorocom.ui.LoginResponse;
import royal.spring.clinicasanna.sanna.omorocom.ui.ProgressBarGenerico;
import royal.spring.clinicasanna.sanna.omorocom.ui.UsuarioService;
import royal.spring.clinicasanna.sanna.omorocom.utils.FuncionesPrincipales;
import royal.spring.clinicasanna.sanna.sanna.Adaptadores.AsistenciaAdapter;
import royal.spring.clinicasanna.sanna.sanna.clases.Model_Asistencia;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    UsuarioService usuarioService;
    AsistenciaAdapter adapter;
    RecyclerView Recycler;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        Recycler = binding.recyclerAsistencia;


        LoginResponse data = FuncionesPrincipales.getDataLogin(getActivity());


        String nombres = FuncionesPrincipales.parseMayuscula(FuncionesPrincipales.parseTrimMinuscula(data.getPrimerNombre()));


        textView.setText("Hola "+ nombres);



        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        usuarioService = APIUtils.getUsuarioService();
        ListarAsitencia(usuarioService);
    }

    public void ListarAsitencia(UsuarioService usuarioService) {


        ProgressBarGenerico.LoadProgress(getActivity());
        LoginResponse usuario = new LoginResponse();


        Gson gson = new Gson();

        LoginResponse data = FuncionesPrincipales.getDataLogin(getActivity());


        usuario.setPersona(data.getPersona());
        String json = gson.toJson(usuario);



        Call<List<Model_Asistencia>> call = usuarioService.getAsistencia(usuario);
        call.enqueue(new Callback<List<Model_Asistencia>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Model_Asistencia>> call, Response<List<Model_Asistencia>> response) {
                if (response.isSuccessful()) {

                    ArrayList<Model_Asistencia> lst = (ArrayList<Model_Asistencia>) response.body();






                    adapter = new AsistenciaAdapter(lst,getActivity());
                    Recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                    Recycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    ProgressBarGenerico.HideProgreess();


                } else {
                    ProgressBarGenerico.HideProgreess();
                }
            }

            @Override
            public void onFailure(Call<List<Model_Asistencia>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
                ProgressBarGenerico.HideProgreess();
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}