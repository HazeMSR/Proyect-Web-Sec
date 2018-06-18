package com.proyect.model.DAO;

import com.proyect.model.Entity.Bitacoras;
import java.sql.Connection;
import java.sql.Date;
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
public class BitacorasDAO {
    private static final String SQL_INSERT = "INSERT INTO bitacoras (id, nombre, usuario_id, m2, contenido, imagen, created, proyecto_id) values (?,?,?,?,?,?,?,?)";
    
    private static final String SQL_UPDATE = "UPDATE bitacoras set id=?, nombre=?, m2=?, contenido=?, imagen=?, created=? where id=?";
    
    private static final String SQL_DELETE = "DELETE FROM bitacoras WHERE id=?";
    
    private static final String SQL_SELECT = "SELECT * FROM bitacoras WHERE id=?";
    
    private static final String SQL_SELECT_USER = "SELECT * FROM bitacoras WHERE usuario_id=?";
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM bitacoras";
    
    
    private Connection conexion = null;


    public void create(Bitacoras b) throws SQLException
    {
        PreparedStatement ps = null;
        getConexion();
        try
        {
            ps = (PreparedStatement) conexion.prepareStatement(SQL_INSERT);
            ps.setInt(1, b.getId());
            ps.setString(2, b.getNombre());
            ps.setInt(3, b.getUsuario_id());
            ps.setString(4, b.getM2());
            ps.setString(5, b.getContenido());
            ps.setString(6, b.getImagen());     //Aquí va la imagen, no sé como manejarla
            ps.setDate(7, (Date)b.getCreated());     
            ps.setInt(8, b.getProyecto_id());
            ps.executeUpdate();
        }
        finally
        {
            cerrar(ps);
            cerrar(conexion);            
            
        }
        
    }
    
    
        public void Update(Bitacoras b) throws SQLException
    {
        com.mysql.jdbc.PreparedStatement ps = null;
        getConexion();
        try
        {
            ps = (com.mysql.jdbc.PreparedStatement)conexion.prepareStatement(SQL_UPDATE);
            ps.setInt(1, b.getId());
            ps.setString(2, b.getNombre());
            ps.setString(3, b.getM2());
            ps.setString(4, b.getContenido());
            ps.setString(5, b.getImagen());     
            ps.setDate(6, (Date)b.getCreated());     
            ps.setInt(7, b.getId());
            ps.executeUpdate();
        }
        finally
        {
            cerrar(ps);
            cerrar(conexion);                        
        }
    }


    public void Delete(Bitacoras p) throws SQLException
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
    
        public Bitacoras Read(Bitacoras p) throws SQLException
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
                    return (Bitacoras)resultado.get(0);
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
        public List ReadUser(Bitacoras p) throws SQLException
    {
        com.mysql.jdbc.PreparedStatement ps = null;
        getConexion();
        ResultSet rs = null;
        try
        {
            ps = (com.mysql.jdbc.PreparedStatement) conexion.prepareStatement(SQL_SELECT_USER);
            ps.setInt(1, p.getUsuario_id());
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
        Bitacoras bitacora = new Bitacoras();
        
        bitacora.setId(rs.getInt("id"));
        bitacora.setNombre(rs.getString("nombre"));
        bitacora.setUsuario_id(rs.getInt("usuario_id"));
        bitacora.setM2(rs.getString("m2"));
        bitacora.setContenido(rs.getString("contenido"));
        bitacora.setImagen(rs.getString("imagen"));
        bitacora.setCreated(rs.getDate("created"));
        bitacora.setProyecto_id(rs.getInt("proyecto_id"));
        results.add(bitacora);    
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
