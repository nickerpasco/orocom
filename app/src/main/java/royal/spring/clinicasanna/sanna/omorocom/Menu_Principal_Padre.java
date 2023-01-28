package royal.spring.clinicasanna.sanna.omorocom;

import android.Manifest;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.databinding.ActivityMenuPrincipalPadreBinding;
import royal.spring.clinicasanna.sanna.omorocom.services_workers.InternetService;
import royal.spring.clinicasanna.sanna.omorocom.services_workers.MyService;
import royal.spring.clinicasanna.sanna.omorocom.services_workers.Restarter;
import royal.spring.clinicasanna.sanna.omorocom.ui.LoginResponse;
import royal.spring.clinicasanna.sanna.omorocom.ui.MensajesGenericos;
import royal.spring.clinicasanna.sanna.omorocom.ui.ProgressBarGenerico;
import royal.spring.clinicasanna.sanna.omorocom.ui.UsuarioService;
import royal.spring.clinicasanna.sanna.omorocom.utils.FuncionesPrincipales;
import royal.spring.clinicasanna.sanna.sanna.Adaptadores.AsistenciaAdapter;
import royal.spring.clinicasanna.sanna.sanna.InicarLoginActivity;
import royal.spring.clinicasanna.sanna.sanna.SplashActivity;
import royal.spring.clinicasanna.sanna.sanna.clases.Model_Asistencia;
import royal.spring.clinicasanna.sanna.sanna.clases.Usuario;

public class Menu_Principal_Padre extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuPrincipalPadreBinding binding;


    Intent mServiceIntent;
    private InternetService mYourService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuPrincipalPadreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar(binding.appBarMenuPrincipalPadre.toolbar);
        binding.appBarMenuPrincipalPadre.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "No tiene tiene correos por mostrar..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });






        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        View headerView = navigationView.getHeaderView(0);
        TextView TxtUsuario = (TextView) headerView.findViewById(R.id.TxtUserName);
        TextView TxtNombresNav = (TextView) headerView.findViewById(R.id.textView);

        LoginResponse data = FuncionesPrincipales.getDataLogin(this);

        String nombres = FuncionesPrincipales.parseMayuscula(FuncionesPrincipales.parseTrimMinuscula(data.getUsuario()));


        TxtUsuario.setText(data.getUsuario());
        TxtNombresNav.setText(nombres);




        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_principal_padre);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.nav_home) {
                    //  Intent newIntent = new Intent(Menu_Principal_Padre.this, SplashActivity.class);
//                    startActivity(newIntent);
                }
                if (id == R.id.asistencia_closs) {
                    Intent newIntent = new Intent(Menu_Principal_Padre.this, AsistenciaActivity.class);
                    startActivity(newIntent);
                }

                if (id == R.id.BtnCerrarSesion) {
                    MensajesGenericos.ShowMessageConfirmDosBotones("¿Desea Cerrar Sesión?","Para continuar, dale en ACEPTAR", Menu_Principal_Padre.this,bproc(),sIiMPRI(),"ACEPTAR","NO");
                }
                return true;
            }


        });


        IniciarServicio();

    }




    public Runnable bproc() {
        return new Runnable() {
            public void run() {


            }
        };
    }
    public Runnable sIiMPRI() {
        return new Runnable() {
            public void run() {

                CerrarCesion();
            }
        };
    }

    private void CerrarCesion() {

        SharedPreferences preferences = getSharedPreferences("PrefeM", Context.MODE_PRIVATE);
        preferences.edit().clear().apply();


        mYourService = new InternetService();
        mServiceIntent = new Intent(this, mYourService.getClass());
        if (isMyServiceRunning(mYourService.getClass())) {

            stopService(mServiceIntent);

            mYourService.stoptimertask();
        }




        startActivity(new Intent(Menu_Principal_Padre.this, InicarLoginActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

        finish();

    }

    @Override
    protected void onDestroy() {
        //stopService(mServiceIntent);
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, Restarter.class);
        this.sendBroadcast(broadcastIntent);
        super.onDestroy();
    }


    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("Service status", "Running");
                return true;
            }
        }
        Log.i("Service status", "Not running");
        return false;
    }
    private void IniciarServicio() {

        mYourService = new InternetService();
        mServiceIntent = new Intent(this, mYourService.getClass());
        if (!isMyServiceRunning(mYourService.getClass())) {
            startService(mServiceIntent);
        }
    }

    /*
    @Override
    protected void onStop() {
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        super.onStop();
    }

     */




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu__principal__padre, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_principal_padre);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}