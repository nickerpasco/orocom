package royal.spring.clinicasanna.sanna.omorocom.ui;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import royal.spring.clinicasanna.sanna.omorocom.ApiEndPoint;

public interface UsuarioService {

    @POST(ApiEndPoint.ENDPOINT_LOGIN)
    Call<LoginResponse> SendSesionLogin(@Body LoginResponse user);

    @POST(ApiEndPoint.ENDPOINT_ASISTENCIA)
    Call<AsistenciaDiariaMarcas> SendAsistencia(@Body AsistenciaDiariaMarcas user);
}
