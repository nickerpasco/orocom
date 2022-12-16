package royal.spring.clinicasanna.sanna.omorocom.ui;

import android.app.ProgressDialog;
import android.content.Context;

import royal.spring.clinicasanna.R;

public class ProgressBarGenerico {
    private static ProgressDialog mPDialog;


    public static void LoadProgress(Context context){
        //progressDialog.setCancelable(false);

        mPDialog = new ProgressDialog(context);
        mPDialog = new ProgressDialog(context);
        mPDialog.setMessage("Procesando...");
        mPDialog.setIndeterminate(true);
        mPDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mPDialog.setCancelable(false);
        mPDialog.show();

 /*
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Procesando...");
        progressDialog.setIndeterminate(true);
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show(); */
    }

    public static void HideProgreess(){

        if (mPDialog != null)
            mPDialog.dismiss();


    }

}
