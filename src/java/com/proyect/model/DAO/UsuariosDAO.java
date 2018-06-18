package com.proyect.model.DAO;

import com.proyect.model.Entity.Bitacoras;
import com.proyect.model.Entity.Proyectos;
import com.proyect.model.Entity.Usuarios;
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
public class UsuariosDAO {
    private static final String SQL_INSERT = "INSERT INTO usuarios (id, nombre, apellidos, email, pass, website) values (?,?,?,?,?,?)";
    
    private static final String SQL_UPDATE = "UPDATE usuarios set id=?, nombre=?, apellidos=?, email=?, pass=?, website=? where id=?";
    
    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id=?";
    
    private static final String SQL_SELECT = "SELECT * FROM usuarios WHERE id=?";
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM usuarios";
    
    private static final String LOGIN = "SELECT * FROM usuarios WHERE email=? AND pass=?";
    
    
    private Connection conexion = null;

    public List Login(Usuarios usuario) throws SQLException
    {
        com.mysql.jdbc.PreparedStatement ps = null;
        getConexion();
        ResultSet rs = null;
        try
        {
            ps = (com.mysql.jdbc.PreparedStatement) conexion.prepareStatement(LOGIN);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPass());
      
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
/*    public static void main(String[] args) throws SQLException {
        Usuarios u = new Usuarios();
        u.setEmail("email@mail.com");
        u.setPass("password");
        System.out.println(Login(u));
    }
  */  

    public void create(Usuarios usuario) throws SQLException
    {
        PreparedStatement ps = null;
        getConexion();
        try
        {
            ps = (PreparedStatement) conexion.prepareStatement(SQL_INSERT);
            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellidos());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getPass());
            ps.setString(6, usuario.getWebsite());
                   ps.executeUpdate();
        }
        finally
        {
            cerrar(ps);
            cerrar(conexion);            
            
        }
        
    }
    
    
    public void Update(Usuarios usuario) throws SQLException
    {
        com.mysql.jdbc.PreparedStatement ps = null;
        getConexion();
        try
        {
            ps = (com.mysql.jdbc.PreparedStatement)conexion.prepareStatement(SQL_UPDATE);
            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellidos());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getPass());
            ps.setString(6, usuario.getWebsite());
            ps.executeUpdate();
        }
        finally
        {
            cerrar(ps);
            cerrar(conexion);                        
        }
    }


    public void Delete(Usuarios usuario) throws SQLException
    {
        com.mysql.jdbc.PreparedStatement ps = null;
        getConexion();
        try
        {
            ps = (com.mysql.jdbc.PreparedStatement) conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getId());
            ps.executeUpdate();
        }
        finally
        {
            cerrar(ps);
            cerrar(conexion);            
        }
    }

    public Usuarios read(Usuarios usuario) throws SQLException
    {
        com.mysql.jdbc.PreparedStatement ps = null;
        getConexion();
        ResultSet rs = null;
        try
        {
            ps = (com.mysql.jdbc.PreparedStatement) conexion.prepareStatement(LOGIN);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPass());
            rs = ps.executeQuery();
            
            List resultado = getResultado(rs);
                
                if(resultado.size()>0)
                {
                    return (Usuarios)resultado.get(0);
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

    
        public Usuarios Read(Usuarios usuario) throws SQLException
    {
        com.mysql.jdbc.PreparedStatement ps = null;
        getConexion();
        ResultSet rs = null;
        try
        {
            ps = (com.mysql.jdbc.PreparedStatement) conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, usuario.getId());
            rs = ps.executeQuery();
            
            List resultado = getResultado(rs);
                
                if(resultado.size()>0)
                {
                    return (Usuarios)resultado.get(0);
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
        
        
        
    private static List getResultado(ResultSet rs) throws SQLException 
    {
        List results = new ArrayList();
        while(rs.next())
        {
        Usuarios usuario = new Usuarios();
            usuario.setId(rs.getInt("id"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellidos(rs.getString("apellidos"));
            usuario.setEmail(rs.getString("email"));
            usuario.setPass(rs.getString("pass"));
            usuario.setWebsite(rs.getString("website"));
        results.add(usuario);    
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
