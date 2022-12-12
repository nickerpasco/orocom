package royal.spring.clinicasanna.sanna.sanna.clases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Paciente")
public class Paciente {

    @DatabaseField(columnName = "IdPaciente",generatedId = true)
    private int IdPaciente;
    @DatabaseField(columnName = "Nombres")
    private String Nombres;
    @DatabaseField(columnName = "tipoDocumento")
    private String tipoDocumento;
    @DatabaseField(columnName = "Documento")
    private String Documento;
    @DatabaseField(columnName = "Edad")
    private int Edad;
    @DatabaseField(columnName = "Sexo")
    private String Sexo;
    @DatabaseField(columnName = "Direccion")
    private String Direccion;
    @DatabaseField(columnName = "Celular")
    private String Celular;
    @DatabaseField(columnName = "Correo")
    private String Correo;
    @DatabaseField(columnName = "Seguro")
    private String Seguro;

    public int getIdPaciente() { return IdPaciente; }

    public void setIdPaciente(int idPaciente) { IdPaciente = idPaciente; }

    public String getNombres() { return Nombres; }

    public void setNombres(String nombres) { Nombres = nombres; }

    public String getDocumento() { return Documento; }

    public void setDocumento(String documento) { Documento = documento; }

    public String getTipoDocumento() { return tipoDocumento; }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public int getEdad() { return Edad; }

    public void setEdad(int Edad) { Edad = Edad; }

    public String getCelular() { return Celular; }

    public void setCelular(String celular) { Celular = celular; }

    public String getSexo() { return Sexo; }

    public void setSexo(String sexo) { Sexo = sexo; }

    public String getCorreo() { return Correo; }

    public void setCorreo(String correo) { Correo = correo; }

    public String getSeguro() { return Seguro; }

    public void setSeguro(String seguro) { Seguro = seguro; }
}
