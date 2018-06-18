package com.proyect.model.Forms;

import com.proyect.model.DAO.UsuariosDAO;
import com.proyect.model.Entity.Usuarios;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jllpz
 */
@Controller
@RequestMapping("login.htm")
public class Login {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        mav.addObject("datos", new Usuarios());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("usuarios") Usuarios u, BindingResult result, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws SQLException {
            ModelAndView mav = new ModelAndView();
            //Aquí se debe realizar la consulta en la base de datos, mediante UsuarioDAO, debido a que ya se tienen los datos.
            //Se debe usar una sentencia if, en caso de que no se encuentre el usuario
            
            UsuariosDAO dao = new UsuariosDAO();
            List res = dao.Login(u); //Obtenemos el tamaño de la query, tambien los datos en una lista
            
            String cap = request.getParameter("cap");
            String resCap = request.getParameter("resCaptcha");
            
            boolean r = validarEmail(u.getEmail());
            
if (r == true) {
    if (u.getPass().contains("drop database") || u.getPass().contains("<script>") || u.getPass().contains(";")
            || u.getPass().contains("'") || u.getPass().contains("=")) {
        mav.setViewName("login");
        mav.addObject("error", "Error, Se ha encontrado algun ataque!");
        mav.addObject("datos", new Usuarios());
        return mav;        
    }
        
        if (cap.equals(resCap)) {
            if (res != null) {
                HttpSession sesion = request.getSession(); //Enviamos la sesion, se envia todo el objeto de usuarios
                sesion.setAttribute("sesion", dao.read(u));
                mav.setViewName("User/InicioUser");
                mav.addObject("listaDatos", res);
                //mav.addObject("cap", resCap);
                return mav;
            }
            mav.setViewName("login");
            mav.addObject("error", "Usuario no encontrado, intentalo de nuevo! o registrate");
            mav.addObject("datos", new Usuarios());
            return mav;

        }
            //Se debe checar
            mav.setViewName("login");
            mav.addObject("error", "Captcha incorrecto, intentalo de nuevo!");
            mav.addObject("datos", new Usuarios());
            return mav;
    }
    
        mav.setViewName("login");
        mav.addObject("error", "Correo incorrecto, intentalo de nuevo!");
        mav.addObject("datos", new Usuarios());
        return mav;
    }
    
    
    //Validar Email
    private boolean validarEmail(String email)
    { 
    String regex = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$";
        Pattern patron = Pattern.compile(regex);
        if(!patron.matcher(email).matches())
        { 
            return false;
        }else
        { 
            return true;
        }
    }

}
