package royal.spring.clinicasanna.sanna.omorocom.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.text.format.DateUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

import royal.spring.clinicasanna.sanna.omorocom.ui.LoginResponse;

public class FuncionesPrincipales {

    public static boolean getValidarInternet(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }



    public static LoginResponse getDataLogin(Context mcontext) {

        Gson gson = new Gson();
        SharedPreferences preferences = mcontext.getSharedPreferences("PrefeM", Context.MODE_PRIVATE);
        String json = preferences.getString("LoginResponse", "");
        LoginResponse ob = gson.fromJson(json, LoginResponse.class);

        Log.i("ObjetoLogin", json);

        return ob;

    }

    public static String FormatDDMMYY(Date date) {

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String format = formatter.format(date);

            return format;
        } catch (Exception e) {
            return "";
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Date ConvertStringDDMMYYToDate(String fechaDirigidaApp) {


        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Date date = formatter.parse(fechaDirigidaApp);

            return date;

        } catch (Exception e) {
            return null;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getHoraPMandAMFromStringtoDate(String fechaDirigidaApp) {


        try {
            Date d = ConvertStringDDMMYYToDate(fechaDirigidaApp);

            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");

            String dateString = formatter.format(d).toUpperCase();


            return dateString;

        } catch (Exception e) {
            return null;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getFechaDescripcion(String FechaDDMMYYHORA) {

        // FechaDDMMYYHORA = "04/04/2022 13:25:41";

        if (FechaDDMMYYHORA == null) {
            return "";
        }

        if (FechaDDMMYYHORA == "") {
            return "";
        }

        if (FechaDDMMYYHORA.equals("")) {
            return "";
        }


        String Hoy = FuncionesPrincipales.FormatDDMMYY(new Date()).substring(0, 10);
        String FechaPedido = FechaDDMMYYHORA.substring(0, 10);

        if (FuncionesPrincipales.isTodayString(Hoy, FechaPedido)) {
            return "Hoy : " + FuncionesPrincipales.getHoraPMandAMFromStringtoDate(FechaDDMMYYHORA);
        }

        if (FuncionesPrincipales.isYesterday(FechaDDMMYYHORA)) {
            return "Ayer : " + FuncionesPrincipales.getHoraPMandAMFromStringtoDate(FechaDDMMYYHORA);
        }

        if (FuncionesPrincipales.isTomorrow(FechaDDMMYYHORA)) {
            return "Mañana : " + FuncionesPrincipales.getHoraPMandAMFromStringtoDate(FechaDDMMYYHORA);
        }


        return FuncionesPrincipales.getDateWhith24Horas(FechaDDMMYYHORA);


    }
    public static String parseTrimMinuscula(String valor) {
        if (valor != null && valor != "") {
            return valor.trim().toLowerCase();
        }
        return "";
    }

    public static String parseMayuscula(String cadena) {

        char[] caracteres = cadena.toCharArray();
        for (int i = 0; i < cadena.length() - 2; i++) {

            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',') {
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            }
        }

        if (cadena == null || cadena == "") {

        } else {
            char[] arr = new String(caracteres).toCharArray();
            arr[0] = Character.toUpperCase(arr[0]);
            return new String(arr);
        }


        return new String(caracteres);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDateWhith24Horas(String fechaDirigidaApp) {


        try {
            Date d = ConvertStringDDMMYYToDate(fechaDirigidaApp);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");

            String dateString = formatter.format(d).toUpperCase().trim();


            return dateString;

        } catch (Exception e) {
            return null;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isYesterday(String date) {

        Date d = FuncionesPrincipales.ConvertStringDDMMYYToDate(date);

        return DateUtils.isToday(d.getTime() + DateUtils.DAY_IN_MILLIS);
    }


    public static boolean isTodayString(String date1, String date2) {

        if (date1.equals(date2)) {
            return true;
        } else {
            return false;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isTomorrow(String date) {

        Date d = FuncionesPrincipales.ConvertStringDDMMYYToDate(date);

        return DateUtils.isToday(d.getTime() - DateUtils.DAY_IN_MILLIS);
    }

    public static String getTipoMarcacion(String cboseleccionado) {

        if (cboseleccionado.equals("Ingreso")){
            return  "IN";
        }

        if (cboseleccionado.equals("Almuerzo")){
            return  "AL";
        }


        if (cboseleccionado.equals("Salida")){
            return  "SA";
        }


        return "XX";

    }



}
