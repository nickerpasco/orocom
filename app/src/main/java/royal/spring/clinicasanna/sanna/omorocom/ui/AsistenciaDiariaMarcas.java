package royal.spring.clinicasanna.sanna.omorocom.ui;

import java.util.ArrayList;
import java.util.List;

public class AsistenciaDiariaMarcas {

    public int Empleado ;
    public int Secuencia ;
    public String FechaMarcacion ;
    public String TipoMarcacion ;
    public String LugarMarcacion ;
    public String Latitud ;
    public String Longitud ;
    public String Estado ;
    public String UsuarioCreacion ;
    public String IpCreacion ;
    public String FechaCreacion;

    public List<ModelError> lstErrores = new ArrayList<>();


    public List<ModelError> getLstErrores() {
        return lstErrores;
    }

    public void setLstErrores(List<ModelError> lstErrores) {
        this.lstErrores = lstErrores;
    }

    public int getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(int empleado) {
        Empleado = empleado;
    }

    public int getSecuencia() {
        return Secuencia;
    }

    public void setSecuencia(int secuencia) {
        Secuencia = secuencia;
    }

    public String getFechaMarcacion() {
        return FechaMarcacion;
    }

    public void setFechaMarcacion(String fechaMarcacion) {
        FechaMarcacion = fechaMarcacion;
    }

    public String getTipoMarcacion() {
        return TipoMarcacion;
    }

    public void setTipoMarcacion(String tipoMarcacion) {
        TipoMarcacion = tipoMarcacion;
    }

    public String getLugarMarcacion() {
        return LugarMarcacion;
    }

    public void setLugarMarcacion(String lugarMarcacion) {
        LugarMarcacion = lugarMarcacion;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getUsuarioCreacion() {
        return UsuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        UsuarioCreacion = usuarioCreacion;
    }

    public String getIpCreacion() {
        return IpCreacion;
    }

    public void setIpCreacion(String ipCreacion) {
        IpCreacion = ipCreacion;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }
}
