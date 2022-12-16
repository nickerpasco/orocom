package royal.spring.clinicasanna.sanna.omorocom.ui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse {



        @SerializedName("Busqueda")
        @Expose
        private String busqueda;
        @SerializedName("Telefono")
        @Expose
        private String telefono;
        @SerializedName("UnidadNegocioAsignada")
        @Expose
        private String unidadNegocioAsignada;
        @SerializedName("Documento")
        @Expose
        private String documento;
        @SerializedName("FechaNacimiento")
        @Expose
        private Object fechaNacimiento;
        @SerializedName("CompaniaSocio")
        @Expose
        private String companiaSocio;
        @SerializedName("Sucursal")
        @Expose
        private String sucursal;
        @SerializedName("Persona")
        @Expose
        private Integer persona;
        @SerializedName("IdVehiculo")
        @Expose
        private Integer idVehiculo;
        @SerializedName("Usuario")
        @Expose
        private String usuario;
        @SerializedName("Clave")
        @Expose
        private String clave;
        @SerializedName("Nombre")

        @Expose
        private String nombre;
        @SerializedName("UsuarioPerfil")

        private String Direccion;
        @SerializedName("Direccion")

        private int DireccionDespacho;
        @SerializedName("DireccionDespacho")

        @Expose
        private String usuarioPerfil;
        @SerializedName("Estado")
        @Expose
        private String estado;
        @SerializedName("Token")
        @Expose
        private String token;

        @SerializedName("RutaDescripcion")
        @Expose
        private String RutaDescripcion;

        @SerializedName("RutaDespacho")
        @Expose
        private String RutaDespacho;

        @SerializedName("PlacaNumero")
        @Expose
        private String PlacaNumero;

        private String RutaDespachoDescripcion;

        private String FechaInicio;
        private String FechaFin;
        private String GuiaNumero;
        private String SerieNumero;
        private String Año;
        private String Mes;
        private String AñoPeriodo2;
        private String MesPeriodo2;
        private String Planta;
        private String PrimerNombre;
        public String TransportistaDescripcion ;
        public int IdTransportista ;
        public int IdRuta ;
        public int LineaVehiculo ;
        public String VehiculoDescripcion ;
        public String Brevete;
        public String TipoDocumentoConductor ;
        public String DocumentoCondutor ;
        public String EstablecimientoCodigo ;
        public String AfectoIGVFlag ;


        public String DocumentoTransportista ;
        public String NombreTransportista ;
        public String DireccionTransportista ;
        public String FlagTipoTransporte ;
        public String UltimaPlaca ;
        public String PlacaOriginalLogin ;


        public String SucursalCoordenadas ;
        public String PlantaCoordenadas ;
        public String TransportistaPlacaCoordenadas ;
        public int IdRutaCoordenadas ;


        public String NombreParaDocumento ;
        public String DireccionParaDocumento ;
        public String TelefonoParaDocumento ;
        public String DepartamentParaDocumento ;
        public String ProvinciaParaDocumento ;
        public String DistritoParaDocumento ;
        public String DocumentoParaDocumento ;
        public String Modalidad ;

        public String AlmacenCodigo ;


        public int PersonaParaDocumento ;
        public int IdConductorFt ;

    public List<ModelError> lstErrores = new ArrayList<>();

        public String getUltimaPlaca() {
            return UltimaPlaca;
        }

        public void setUltimaPlaca(String ultimaPlaca) {
            UltimaPlaca = ultimaPlaca;
        }

        public int getIdConductorFt() {
            return IdConductorFt;
        }

        public void setIdConductorFt(int idConductorFt) {
            IdConductorFt = idConductorFt;
        }

        public String getPlantaCoordenadas() {
            return PlantaCoordenadas;
        }

        public void setPlantaCoordenadas(String plantaCoordenadas) {
            PlantaCoordenadas = plantaCoordenadas;
        }

    public List<ModelError> getLstErrores() {
        return lstErrores;
    }

    public void setLstErrores(List<ModelError> lstErrores) {
        this.lstErrores = lstErrores;
    }

    public String getSucursalCoordenadas() {
            return SucursalCoordenadas;
        }

        public void setSucursalCoordenadas(String sucursalCoordenadas) {
            SucursalCoordenadas = sucursalCoordenadas;
        }

        public String getTransportistaPlacaCoordenadas() {
            return TransportistaPlacaCoordenadas;
        }

        public void setTransportistaPlacaCoordenadas(String transportistaPlacaCoordenadas) {
            TransportistaPlacaCoordenadas = transportistaPlacaCoordenadas;
        }

        public int getIdRutaCoordenadas() {
            return IdRutaCoordenadas;
        }

        public void setIdRutaCoordenadas(int idRutaCoordenadas) {
            IdRutaCoordenadas = idRutaCoordenadas;
        }

        public int getPersonaParaDocumento() {
            return PersonaParaDocumento;
        }

        public String getPlacaOriginalLogin() {
            return PlacaOriginalLogin;
        }

        public void setPlacaOriginalLogin(String placaOriginalLogin) {
            PlacaOriginalLogin = placaOriginalLogin;
        }

        public void setPersonaParaDocumento(int personaParaDocumento) {
            PersonaParaDocumento = personaParaDocumento;
        }


        public String getRutaDespachoDescripcion() {
            return RutaDespachoDescripcion;
        }

        public void setRutaDespachoDescripcion(String rutaDespachoDescripcion) {
            RutaDespachoDescripcion = rutaDespachoDescripcion;
        }

        public String getModalidad() {
            return Modalidad;
        }

        public void setModalidad(String modalidad) {
            Modalidad = modalidad;
        }



        public String getEstablecimientoCodigo() {
            return EstablecimientoCodigo;
        }

        public String getAfectoIGVFlag() {
            return AfectoIGVFlag;
        }

        public void setAfectoIGVFlag(String afectoIGVFlag) {
            AfectoIGVFlag = afectoIGVFlag;
        }

        public void setEstablecimientoCodigo(String establecimientoCodigo) {
            EstablecimientoCodigo = establecimientoCodigo;
        }

        public String getDocumentoParaDocumento() {
            return DocumentoParaDocumento;
        }

        public void setDocumentoParaDocumento(String documentoParaDocumento) {
            DocumentoParaDocumento = documentoParaDocumento;
        }

        public int getLineaVehiculo() {
            return LineaVehiculo;
        }

        public void setLineaVehiculo(int lineaVehiculo) {
            LineaVehiculo = lineaVehiculo;
        }

        public String getDocumentoTransportista() {
            return DocumentoTransportista;
        }

        public void setDocumentoTransportista(String documentoTransportista) {
            DocumentoTransportista = documentoTransportista;
        }

        public String getNombreParaDocumento() {
            return NombreParaDocumento;
        }

        public void setNombreParaDocumento(String nombreParaDocumento) {
            NombreParaDocumento = nombreParaDocumento;
        }

        public String getFlagTipoTransporte() {
            return FlagTipoTransporte;
        }

        public void setFlagTipoTransporte(String flagTipoTransporte) {
            FlagTipoTransporte = flagTipoTransporte;
        }

        public String getDireccionParaDocumento() {
            return DireccionParaDocumento;
        }

        public void setDireccionParaDocumento(String direccionParaDocumento) {
            DireccionParaDocumento = direccionParaDocumento;
        }

        public String getTelefonoParaDocumento() {
            return TelefonoParaDocumento;
        }

        public void setTelefonoParaDocumento(String telefonoParaDocumento) {
            TelefonoParaDocumento = telefonoParaDocumento;
        }

        public String getDepartamentParaDocumento() {
            return DepartamentParaDocumento;
        }

        public void setDepartamentParaDocumento(String departamentParaDocumento) {
            DepartamentParaDocumento = departamentParaDocumento;
        }

        public String getProvinciaParaDocumento() {
            return ProvinciaParaDocumento;
        }

        public void setProvinciaParaDocumento(String provinciaParaDocumento) {
            ProvinciaParaDocumento = provinciaParaDocumento;
        }

        public String getDistritoParaDocumento() {
            return DistritoParaDocumento;
        }

        public void setDistritoParaDocumento(String distritoParaDocumento) {
            DistritoParaDocumento = distritoParaDocumento;
        }

        public String getNombreTransportista() {
            return NombreTransportista;
        }

        public void setNombreTransportista(String nombreTransportista) {
            NombreTransportista = nombreTransportista;
        }

        public int getIdRuta() {
            return IdRuta;
        }

        public void setIdRuta(int idRuta) {
            IdRuta = idRuta;
        }

        public String getDireccionTransportista() {
            return DireccionTransportista;
        }

        public void setDireccionTransportista(String direccionTransportista) {
            DireccionTransportista = direccionTransportista;
        }

        public String getTransportistaDescripcion() {
            return TransportistaDescripcion;
        }

        public String getDocumentoCondutor() {
            return DocumentoCondutor;
        }

        public void setDocumentoCondutor(String documentoCondutor) {
            DocumentoCondutor = documentoCondutor;
        }

        public void setTransportistaDescripcion(String transportistaDescripcion) {
            TransportistaDescripcion = transportistaDescripcion;
        }

        public int getIdTransportista() {
            return IdTransportista;
        }

        public void setIdTransportista(int idTransportista) {
            IdTransportista = idTransportista;
        }

        public String getVehiculoDescripcion() {
            return VehiculoDescripcion;
        }

        public void setVehiculoDescripcion(String vehiculoDescripcion) {
            VehiculoDescripcion = vehiculoDescripcion;
        }

        public String getBrevete() {
            return Brevete;
        }

        public void setBrevete(String brevete) {
            Brevete = brevete;
        }

        public String getTipoDocumentoConductor() {
            return TipoDocumentoConductor;
        }

        public void setTipoDocumentoConductor(String tipoDocumentoConductor) {
            TipoDocumentoConductor = tipoDocumentoConductor;
        }

        public String getDireccion() {
            return Direccion;
        }

        public void setDireccion(String direccion) {
            Direccion = direccion;
        }

        public int getDireccionDespacho() {
            return DireccionDespacho;
        }

        public void setDireccionDespacho(int direccionDespacho) {
            DireccionDespacho = direccionDespacho;
        }

        public String getPrimerNombre() {
            return PrimerNombre;
        }

        public void setPrimerNombre(String primerNombre) {
            PrimerNombre = primerNombre;
        }



        public String getPlanta() {
            return Planta;
        }

        public void setPlanta(String planta) {
            Planta = planta;
        }



        public String getAñoPeriodo2() {
            return AñoPeriodo2;
        }

        public void setAñoPeriodo2(String añoPeriodo2) {
            AñoPeriodo2 = añoPeriodo2;
        }

        public String getMesPeriodo2() {
            return MesPeriodo2;
        }

        public void setMesPeriodo2(String mesPeriodo2) {
            MesPeriodo2 = mesPeriodo2;
        }

        public String getAño() {
            return Año;
        }

        public void setAño(String año) {
            Año = año;
        }

        public String getMes() {
            return Mes;
        }

        public void setMes(String mes) {
            Mes = mes;
        }

        public String getGuiaNumero() {
            return GuiaNumero;
        }

        public void setGuiaNumero(String guiaNumero) {
            GuiaNumero = guiaNumero;
        }

        public String getSerieNumero() {
            return SerieNumero;
        }

        public void setSerieNumero(String serieNumero) {
            SerieNumero = serieNumero;
        }

        public String getFechaInicio() {
            return FechaInicio;
        }

        public void setFechaInicio(String fechaInicio) {
            FechaInicio = fechaInicio;
        }

        public String getFechaFin() {
            return FechaFin;
        }

        public void setFechaFin(String fechaFin) {
            FechaFin = fechaFin;
        }

        public Integer getIdVehiculo() {
            return idVehiculo;
        }

        public void setIdVehiculo(Integer idVehiculo) {
            this.idVehiculo = idVehiculo;
        }

        public String getRutaDescripcion() {
            return RutaDescripcion;
        }

        public void setRutaDescripcion(String rutaDescripcion) {
            RutaDescripcion = rutaDescripcion;
        }

        public String getRutaDespacho() {
            return RutaDespacho;
        }

        public void setRutaDespacho(String rutaDespacho) {
            RutaDespacho = rutaDespacho;
        }

        public String getPlacaNumero() {
            return PlacaNumero;
        }

        public void setPlacaNumero(String placaNumero) {
            PlacaNumero = placaNumero;
        }

        public String getToken() {
            return token;
        }




        public String getBusqueda() {
            return busqueda;
        }

        public void setBusqueda(String busqueda) {
            this.busqueda = busqueda;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getUnidadNegocioAsignada() {
            return unidadNegocioAsignada;
        }

        public void setUnidadNegocioAsignada(String unidadNegocioAsignada) {
            this.unidadNegocioAsignada = unidadNegocioAsignada;
        }

        public String getDocumento() {
            return documento;
        }

        public void setDocumento(String documento) {
            this.documento = documento;
        }

        public Object getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(Object fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }


        public String getAlmacenCodigo() {
            return AlmacenCodigo;
        }

        public void setAlmacenCodigo(String almacenCodigo) {
            AlmacenCodigo = almacenCodigo;
        }

        public String getCompaniaSocio() {
            return companiaSocio;
        }

        public void setCompaniaSocio(String companiaSocio) {
            this.companiaSocio = companiaSocio;
        }

        public String getSucursal() {
            return sucursal;
        }

        public void setSucursal(String sucursal) {
            this.sucursal = sucursal;
        }

        public Integer getPersona() {
            return persona;
        }

        public void setPersona(Integer persona) {
            this.persona = persona;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getClave() {
            return clave;
        }

        public void setClave(String clave) {
            this.clave = clave;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getUsuarioPerfil() {
            return usuarioPerfil;
        }

        public void setUsuarioPerfil(String usuarioPerfil) {
            this.usuarioPerfil = usuarioPerfil;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public LoginResponse() {
        }




    }
