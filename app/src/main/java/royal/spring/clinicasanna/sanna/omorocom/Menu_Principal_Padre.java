package royal.spring.clinicasanna.sanna.omorocom;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.databinding.ActivityMenuPrincipalPadreBinding;
import royal.spring.clinicasanna.sanna.sanna.SplashActivity;

public class Menu_Principal_Padre extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuPrincipalPadreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuPrincipalPadreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        TxtUsuario.setText(APIUtils.Usuario);
        TxtNombresNav.setText(APIUtils.Nombnre);


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
                return true;
            }


        });


    }



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