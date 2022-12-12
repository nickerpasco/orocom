package royal.spring.clinicasanna.sanna.sanna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import royal.spring.clinicasanna.R;
import royal.spring.clinicasanna.sanna.sanna.ui.MainActivity;

public class SplashActivity extends AppCompatActivity {
    Animation topAnim ;
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);


        ImageView ImgSplash = (ImageView)findViewById(R.id.ImgSplash);
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        ImgSplash.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {





                SharedPreferences preferences = getSharedPreferences("PrefeM", Context.MODE_PRIVATE);
                String usuario = preferences.getString("Usuario", "");

                if(usuario ==null || usuario.equals("")){
                    startActivity(new Intent(SplashActivity.this,InicarLoginActivity.class));
                }else{
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }


            }
        },2000);





    }
}