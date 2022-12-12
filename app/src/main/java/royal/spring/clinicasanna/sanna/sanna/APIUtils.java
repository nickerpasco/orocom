package royal.spring.clinicasanna.sanna.sanna;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "https://api.apis.net.pe/";





    public static MetodosService getPedidoServices(){
        return RetrofitClient.getClient(API_URL).create(MetodosService.class);
    }



}
