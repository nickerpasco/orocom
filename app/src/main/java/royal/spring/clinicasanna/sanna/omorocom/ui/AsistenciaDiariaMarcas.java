package royal.spring.clinicasanna.sanna.omorocom.ui;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "AsistenciaDiariaMarcas")
public class AsistenciaDiariaMarcas {



    @DatabaseField(columnName = "IdAsistencia",generatedId = true)
    private int IdAsistencia;
    @DatabaseField(columnName = "Empleado")
    public int Empleado ;
    @DatabaseField(columnName = "Secuencia")
    public int Secuencia ;
    @DatabaseField(columnName = "FechaMarcacion")
    public String FechaMarcacion ;
    @DatabaseField(columnName = "Comentarios")
    public String Comentarios ;
    @DatabaseField(columnName = "TipoMarcacion")
    public String TipoMarcacion ;
    @DatabaseField(columnName = "LugarMarcacion")
    public String LugarMarcacion ;
    @DatabaseField(columnName = "Latitud")
    public String Latitud ;
    @DatabaseField(columnName = "Longitud")
    public String Longitud ;
    @DatabaseField(columnName = "Estado")
    public String Estado ;
    @DatabaseField(columnName = "UsuarioCreacion")
    public String UsuarioCreacion ;
    @DatabaseField(columnName = "IpCreacion")
    public String IpCreacion ;
    @DatabaseField(columnName = "FechaCreacion")
    public String FechaCreacion;
    @DatabaseField(columnName = "FlagOffline")
    public boolean FlagOffline;

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

    public int getIdAsistencia() {
        return IdAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        IdAsistencia = idAsistencia;
    }

    public boolean isFlagOffline() {
        return FlagOffline;
    }

    public void setFlagOffline(boolean flagOffline) {
        FlagOffline = flagOffline;
    }

    public String getComentarios() {
        return Comentarios;
    }

    public void setComentarios(String comentarios) {
        Comentarios = comentarios;
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
