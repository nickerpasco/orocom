package royal.spring.clinicasanna.sanna.omorocom.utils;

public class FuncionesPrincipales {
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
