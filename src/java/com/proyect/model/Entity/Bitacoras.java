package com.proyect.model.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jllpz
 */
public class Bitacoras implements Serializable {

    private Integer id;
    private String nombre;
    private int usuario_id;
    private String m2;
    private String contenido;
    private String imagen;
    private Date created;
    private int proyecto_id;
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Bitacoras() {
    }

    public Bitacoras(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
        this.m2 = m2;
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

    public int getProyecto_id() {
        return proyecto_id;
    }

    public void setProyecto_id(int proyectos) {
        this.proyecto_id = proyectos;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuarios) {
        this.usuario_id = usuarios;
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
        if (!(object instanceof Bitacoras)) {
            return false;
        }
        Bitacoras other = (Bitacoras) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

        @Override
       public String toString()
       {
           StringBuilder sb= new StringBuilder();
           
           sb.append("Id:").append(getId());
           sb.append("Nombre:").append(getNombre());
           sb.append("UsuarioId:").append(getUsuario_id());
           sb.append("m2:").append(getM2());
           sb.append("Contenido:").append(getContenido());
           sb.append("imagen:").append(getImagen());
           sb.append("Created:").append(getCreated());
           sb.append("ProyectoId:").append(getProyecto_id());
           
           return sb.toString();
       }
    
}
