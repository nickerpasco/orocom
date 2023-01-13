package royal.spring.clinicasanna.sanna.omorocom.ui;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import royal.spring.clinicasanna.sanna.omorocom.ApiEndPoint;
import royal.spring.clinicasanna.sanna.sanna.clases.Model_Asistencia;

public interface UsuarioService {

    @POST(ApiEndPoint.ENDPOINT_LOGIN)
    Call<LoginResponse> SendSesionLogin(@Body LoginResponse user);

    @POST(ApiEndPoint.ENDPOINT_ASISTENCIA)
    Call<AsistenciaDiariaMarcas> SendAsistencia(@Body AsistenciaDiariaMarcas user);

    @POST(ApiEndPoint.ENDPOINT_LISTAR_ASISTENCIA)
    Call<List<Model_Asistencia>> getAsistencia(@Body LoginResponse user);
}
