package royal.spring.clinicasanna.sanna.omorocom;

import royal.spring.clinicasanna.sanna.omorocom.ui.UsuarioService;
import royal.spring.clinicasanna.sanna.sanna.RetrofitClient;

public class APIUtils {

    private APIUtils(){
    };
    public static final String API_URL = "http://104.211.62.150/";
    public static final String PATH_SERVIDOR = "SPRING_RestServer_OlanoRenzo"; //
    public static final String IP_APP = "http://104.211.62.150";



    public static UsuarioService getUsuarioService(){
        return RetrofitClient.getClient(API_URL).create(UsuarioService.class);
    }

}
