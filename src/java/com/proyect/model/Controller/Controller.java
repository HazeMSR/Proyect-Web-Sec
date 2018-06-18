package com.proyect.model.Controller;

import com.proyect.model.DAO.BitacorasDAO;
import com.proyect.model.DAO.ProyectosDAO;
import com.proyect.model.DAO.UsuariosDAO;
import com.proyect.model.Entity.Bitacoras;
import com.proyect.model.Entity.Proyectos;
import com.proyect.model.Entity.Usuarios;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jllpz
 */
public class Controller {

    @RequestMapping("inicio.htm")
    public ModelAndView inicio(HttpServletRequest request){
        HttpSession sesion = request.getSession();
        sesion.getAttribute("sesion");
        sesion.invalidate();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Inicio");
        return mav;
    }
 
    
    //---------------------------------User
    @RequestMapping("inicioUser.htm")
    public ModelAndView user(HttpServletRequest request) throws SQLException{
        HttpSession sesion = request.getSession();
        sesion.setAttribute("sesion", sesion.getAttribute("sesion"));
        Usuarios user = new Usuarios();
        UsuariosDAO dao = new UsuariosDAO();
        user = (Usuarios)sesion.getAttribute("sesion");
        ModelAndView mav = new ModelAndView();
        
        mav.addObject("listaDatos", dao.Login(user));
        mav.setViewName("User/InicioUser");
        return mav;
    }

    @RequestMapping("listaProyectos.htm")
    public ModelAndView lista(HttpServletRequest request) throws SQLException{
        HttpSession sesion = request.getSession();
        sesion.setAttribute("sesion", sesion.getAttribute("sesion"));
        
        ProyectosDAO dao = new ProyectosDAO();
        ModelAndView mav = new ModelAndView();
        mav.addObject("datos", dao.ReadAll());
        mav.setViewName("User/ListaProyectos");
        return mav;
    }

    @RequestMapping("listaBitacoras.htm")
    public ModelAndView listaB(HttpServletRequest request) throws SQLException{
        HttpSession sesion = request.getSession();
        sesion.setAttribute("sesion", sesion.getAttribute("sesion"));
        Usuarios user = new Usuarios();
        user = (Usuarios)sesion.getAttribute("sesion");
        
        BitacorasDAO dao = new BitacorasDAO();
        Bitacoras bitacora = new Bitacoras();
        bitacora.setUsuario_id(user.getId());
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("datos", dao.ReadUser(bitacora));
        mav.setViewName("User/ListaBitacoras");
        return mav;
    }

    @RequestMapping("eliminar.htm")
    public ModelAndView eliminar(HttpServletRequest request) throws SQLException{
        ModelAndView mav = new ModelAndView();
        
        Bitacoras bi = new Bitacoras();
        bi.setId(Integer.parseInt(request.getParameter("id")));

        BitacorasDAO dao = new BitacorasDAO();
        dao.Delete(bi);
        
        mav.addObject("datos", dao.ReadAll());
        mav.setViewName("User/ListaBitacoras");
        return mav;
    }
    
    @RequestMapping("Eliminar.htm")
    public ModelAndView Eliminar(HttpServletRequest request) throws SQLException{
        ModelAndView mav = new ModelAndView();
        
        Proyectos bi = new Proyectos();
        bi.setId(Integer.parseInt(request.getParameter("id")));

        ProyectosDAO dao = new ProyectosDAO();
        dao.Delete(bi);
        
        mav.addObject("datos", dao.ReadAll());
        mav.setViewName("User/ListaProyectos");
        return mav;
    }


}
