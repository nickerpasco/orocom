package royal.spring.clinicasanna.sanna.sanna.clases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "FuncionesVitales")
public class FuncionesVitales {

    @DatabaseField(columnName = "IdFuncionVital",generatedId = true)
    private int IdFuncionVital;
    @DatabaseField(columnName = "Campo")
    private int Campo;
    @DatabaseField(columnName = "Saturacion")
    private double Saturacion;
    @DatabaseField(columnName = "Temperatura")
    private double Temperatura;
    @DatabaseField(columnName = "Peso")
    private double Peso;
    @DatabaseField(columnName = "Talla")
    private double Talla;
    @DatabaseField(columnName = "Comentario")
    private String Comentario;
    @DatabaseField(columnName = "Paciente")
    private String Paciente;
    @DatabaseField(columnName = "IMC")
    private double IMC;
    @DatabaseField(columnName = "Fecha")
    private String Fecha;
    @DatabaseField(columnName = "Estado")
    private String Estado;
    @DatabaseField(columnName = "Medico")
    private String Medico;
    @DatabaseField(columnName = "Direccion")
    private String Direccion;


    public String getFecha() {
        return Fecha;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getMedico() {
        return Medico;
    }

    public void setMedico(String medico) {
        Medico = medico;
    }

    public int getIdFuncionVital() {
        return IdFuncionVital;
    }

    public void setIdFuncionVital(int idFuncionVital) {
        IdFuncionVital = idFuncionVital;
    }

    public int getCampo() {
        return Campo;
    }

    public String getPaciente() {
        return Paciente;
    }

    public void setPaciente(String paciente) {
        Paciente = paciente;
    }

    public void setCampo(int campo) {
        Campo = campo;
    }

    public double getSaturacion() {
        return Saturacion;
    }

    public void setSaturacion(double saturacion) {
        Saturacion = saturacion;
    }

    public double getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(double temperatura) {
        Temperatura = temperatura;
    }

    public double getPeso() {
        return Peso;
    }

    public void setPeso(double peso) {
        Peso = peso;
    }

    public double getTalla() {
        return Talla;
    }

    public void setTalla(double talla) {
        Talla = talla;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }
}
