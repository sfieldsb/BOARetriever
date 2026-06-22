package com.boaretriever.model;

public class BOA {
    private String NOrden;
    private String DOCN;
    private String FechaPublicacion;
    private String Numeroboletin;
    private String Seccion;
    private String Subseccion;
    private String Fechadisposicion;
    private String Rango;
    private String Emisor;
    private String Titulo;
    private String UriEli;
    private String Texto;
    private String CodigoMateria;
    private String UrlPdf;
    private String UrlBCOM;

    public BOA() {
    }

    public BOA(String NOrden, String DOCN, String FechaPublicacion, String Numeroboletin, String Seccion,
               String Subseccion, String Fechadisposicion, String Rango, String Emisor, String Titulo,
               String UriEli, String Texto, String CodigoMateria, String UrlPdf, String UrlBCOM) {
        this.NOrden = NOrden;
        this.DOCN = DOCN;
        this.FechaPublicacion = FechaPublicacion;
        this.Numeroboletin = Numeroboletin;
        this.Seccion = Seccion;
        this.Subseccion = Subseccion;
        this.Fechadisposicion = Fechadisposicion;
        this.Rango = Rango;
        this.Emisor = Emisor;
        this.Titulo = Titulo;
        this.UriEli = UriEli;
        this.Texto = Texto;
        this.CodigoMateria = CodigoMateria;
        this.UrlPdf = UrlPdf;
        this.UrlBCOM = UrlBCOM;
    }

    public String getNOrden() {
        return NOrden;
    }

    public void setNOrden(String NOrden) {
        this.NOrden = NOrden;
    }

    public String getDOCN() {
        return DOCN;
    }

    public void setDOCN(String DOCN) {
        this.DOCN = DOCN;
    }

    public String getFechaPublicacion() {
        return FechaPublicacion;
    }

    public void setFechaPublicacion(String FechaPublicacion) {
        this.FechaPublicacion = FechaPublicacion;
    }

    public String getNumeroboletin() {
        return Numeroboletin;
    }

    public void setNumeroboletin(String Numeroboletin) {
        this.Numeroboletin = Numeroboletin;
    }

    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String Seccion) {
        this.Seccion = Seccion;
    }

    public String getSubseccion() {
        return Subseccion;
    }

    public void setSubseccion(String Subseccion) {
        this.Subseccion = Subseccion;
    }

    public String getFechadisposicion() {
        return Fechadisposicion;
    }

    public void setFechadisposicion(String Fechadisposicion) {
        this.Fechadisposicion = Fechadisposicion;
    }

    public String getRango() {
        return Rango;
    }

    public void setRango(String Rango) {
        this.Rango = Rango;
    }

    public String getEmisor() {
        return Emisor;
    }

    public void setEmisor(String Emisor) {
        this.Emisor = Emisor;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getUriEli() {
        return UriEli;
    }

    public void setUriEli(String UriEli) {
        this.UriEli = UriEli;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String Texto) {
        this.Texto = Texto;
    }

    public String getCodigoMateria() {
        return CodigoMateria;
    }

    public void setCodigoMateria(String CodigoMateria) {
        this.CodigoMateria = CodigoMateria;
    }

    public String getUrlPdf() {
        return UrlPdf;
    }

    public void setUrlPdf(String UrlPdf) {
        this.UrlPdf = UrlPdf;
    }

    public String getUrlBCOM() {
        return UrlBCOM;
    }

    public void setUrlBCOM(String UrlBCOM) {
        this.UrlBCOM = UrlBCOM;
    }
}
