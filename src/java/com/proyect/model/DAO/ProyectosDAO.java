package com.proyect.model.DAO;

import com.proyect.model.Entity.Bitacoras;
import com.proyect.model.Entity.Proyectos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jllpz
 */
public class ProyectosDAO {
    private static final String SQL_INSERT = "INSERT INTO proyectos (id, nombre, arqui, tipoObra, municipio, ubicacion, propietario, contenido, imagen, created) values (?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_UPDATE = "UPDATE proyectos set id=?, nombre=?, arqui=?, tipoObra=?, municipio=?, ubicacion=?, propietario=?, contenido=?, imagen=?, created=? where id=?";
    
    private static final String SQL_DELETE = "DELETE FROM proyectos WHERE id=?";
    
    private static final String SQL_SELECT = "SELECT * FROM proyectos WHERE id=?";
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM proyectos";
    
    
    private Connection conexion = null;


    public void create(Proyectos p) throws SQLException
    {
        PreparedStatement ps = null;
        getConexion();
        try
        {
            ps = (PreparedStatement) conexion.prepareStatement(SQL_INSERT);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getArqui());
            ps.setString(4, p.getTipoObra());
            ps.setString(5, p.getMunicipio());
            ps.setString(6, p.getUbicacion());
            ps.setString(7, p.getPropietario());
            ps.setString(8, p.getContenido());
            ps.setString(9, p.getImagen());     
            ps.setString(10, p.getCreated());     
            ps.executeUpdate();
        }
        finally
        {
            cerrar(ps);
            cerrar(conexion);            
            
        }
        
    }
    
    
    public void Update(Proyectos p) throws SQLException
    {
        com.mysql.jdbc.PreparedStatement ps = null;
        getConexion();
        try
        {
            ps = (com.mysql.jdbc.PreparedStatement)conexion.prepareStatement(SQL_UPDATE);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getArqui());
            ps.setString(4, p.getTipoObra());
            ps.setString(5, p.getMunicipio());
            ps.setString(6, p.getUbicacion());
            ps.setString(7, p.getPropietario());
            ps.setString(8, p.getContenido());
            ps.setString(9, p.getImagen());     
            ps.setString(10, p.getCreated());               
            ps.executeUpdate();
        }
        finally
        {
            cerrar(ps);
            cerrar(conexion);                        
        }
    }


    public void Delete(Proyectos p) throws SQLException
    {
        com.mysql.jdbc.PreparedStatement ps = null;
        getConexion();
        try
        {
            ps = (com.mysql.jdbc.PreparedStatement) conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
        }
        finally
        {
            cerrar(ps);
            cerrar(conexion);            
        }
    }
    
        public Proyectos Read(Proyectos p) throws SQLException
    {
        com.mysql.jdbc.PreparedStatement ps = null;
        getConexion();
        ResultSet rs = null;
        try
        {
            ps = (com.mysql.jdbc.PreparedStatement) conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, p.getId());
            rs = ps.executeQuery();
            
            List resultado = getResultado(rs);
                
                if(resultado.size()>0)
                {
                    return (Proyectos)resultado.get(0);
                }
                else
                {
                    return null;
                }
        }
        finally
        {
            cerrar(rs);
            cerrar(ps);
            cerrar(conexion);                    }
    }

        public List ReadAll() throws SQLException
    {
        com.mysql.jdbc.PreparedStatement ps = null;
        getConexion();
        ResultSet rs = null;
        try
        {
            ps = (com.mysql.jdbc.PreparedStatement) conexion.prepareStatement(SQL_SELECT_ALL);
            
            rs = ps.executeQuery();
            
            List resultado = getResultado(rs);
                
                if(resultado.size()>0)
                {
                    return resultado;
                }
                else
                {
                    return null;
                }
        }
        finally
        {
            cerrar(rs);
            cerrar(ps);
            cerrar(conexion);                       
        }
    }

    
    
    
    
    
    
        private void getConexion()
    {
        String usuario = "root";
        String clave = "n0m3l0";
        String urlBD = "jdbc:mysql://localhost:3306/constructora";
        String mySqlDriver = "com.mysql.jdbc.Driver";
        try
        {
            Class.forName(mySqlDriver);
            conexion = DriverManager.getConnection(urlBD, usuario, clave);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
        
        
        
    private List getResultado(ResultSet rs) throws SQLException 
    {
        List results = new ArrayList();
        while(rs.next())
        {
        Proyectos proyecto = new Proyectos();
            proyecto.setId(rs.getInt("id"));
            proyecto.setNombre(rs.getString("nombre"));
            proyecto.setArqui(rs.getString("arqui"));
            proyecto.setTipoObra(rs.getString("tipoObra"));
            proyecto.setMunicipio(rs.getString("municipio"));
            proyecto.setUbicacion(rs.getString("ubicacion"));
            proyecto.setPropietario(rs.getString("propietario"));
            proyecto.setContenido(rs.getString("contenido"));
            proyecto.setImagen(rs.getString("imagen"));
            proyecto.setCreated(rs.getString("created"));
        results.add(proyecto);    
        }
     return results;   
    }

    private void cerrar(ResultSet rs)
    {
        if(rs!=null)
        {
            try
            {
                rs.close();
            }catch (SQLException e){
                
            }
        }
    }
    
    private void cerrar (Connection conexion)
    {
        if(conexion!=null)
        {
            try
            {
                conexion.close();
            }catch (SQLException e){
                
            }
        }
    }
    
    private void cerrar(PreparedStatement ps) throws SQLException
    {
        if(ps!=null)
        {
            try
            {
                ps.close();
            }catch (SQLException e){
                
            }
        }
    }

    
}
