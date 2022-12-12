package royal.spring.clinicasanna.sanna.sanna.clases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Usuario")
public class Usuario {

    @DatabaseField(columnName = "IdUsuario",generatedId = true)
    private int IdUsuario;
    @DatabaseField(columnName = "ApellidosNombres")
    private String ApellidosNombres;
    @DatabaseField(columnName = "Usuario")
    private String Usuario;
    @DatabaseField(columnName = "Contrasenia")
    private String Contrasenia;
    @DatabaseField(columnName = "Celular")
    private String Celular;
    @DatabaseField(columnName = "Correo")
    private String Correo;

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getApellidosNombres() {
        return ApellidosNombres;
    }

    public void setApellidosNombres(String apellidosNombres) {
        ApellidosNombres = apellidosNombres;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
