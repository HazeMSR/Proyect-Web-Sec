package com.proyect.model.Entity;

import java.io.Serializable;

/**
 *
 * @author jllpz
 */
public class Proyectos implements Serializable {

    private Integer id;
    private String nombre;
    private String arqui;
    private String tipoObra;
    private String municipio;
    private String ubicacion;
    private String propietario;
    private String contenido;
    private String imagen;
    private String created;

    public String getTipoObra() {
        return tipoObra;
    }

    public void setTipoObra(String tipoObra) {
        this.tipoObra = tipoObra;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Proyectos() {
    }

    public Proyectos(Integer id) {
        this.id = id;
    }

    public Proyectos(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArqui() {
        return arqui;
    }

    public void setArqui(String arqui) {
        this.arqui = arqui;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

        @Override
       public String toString()
       {
           StringBuilder sb= new StringBuilder();
           
           sb.append("Id: ").append(getId());
           sb.append("Nombre: ").append(getNombre());
           sb.append("Arqui: ").append(getArqui());
           sb.append("Contenido: ").append(getContenido());
           sb.append("imagen: ").append(getImagen());
           sb.append("created: ").append(getCreated());
           
           return sb.toString();
       }
    
}
