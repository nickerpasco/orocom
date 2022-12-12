package royal.spring.clinicasanna.sanna.sanna;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MetodosService {

    @GET(ApiEndPoint.get_persona)
    Call<List<ReponsePersona>> getMaestros();

    //@GET(ApiEndPoint.get_persona+"dni?numero={dni}")
    //Call<List<ReponsePersona>> getMaestros(@Path("dni") String dni);

}
