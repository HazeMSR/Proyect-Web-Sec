package com.proyect.model.Forms;

import com.proyect.model.DAO.UsuariosDAO;
import com.proyect.model.Entity.Usuarios;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@RequestMapping("registro.htm")
public class RegistrarUser {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Registro");
        mav.addObject("datos", new Usuarios());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("usuarios") Usuarios u, BindingResult result, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
      ModelAndView mav = new ModelAndView();
  
      UsuariosDAO dao = new UsuariosDAO();
      u.setId(0);
      
      
      //-------------Validaciones
        String cap = request.getParameter("captcha");
        String resCap = request.getParameter("resCaptcha");
        String pass = request.getParameter("passw");
        boolean c = validarEmail(u.getEmail());
        boolean w = validarWebSite(u.getWebsite());
        boolean n = validar(u.getNombre());
        boolean a = validar(u.getApellidos());
        boolean p = validar(u.getPass());
      //------------------------
      
      
    if (u.getPass().equals(pass)) {
        if (w == true && c == true && n==true && a==true && p==true) {
            if (u.getNombre().contains("drop database") || u.getApellidos().contains("drop database") || u.getPass().contains("drop database")) {
                mav.setViewName("Registro");
                mav.addObject("error", "Error, se ha detectado un ataque!");
                mav.addObject("datos", u);
                return mav;
            }
                
            if (cap.equals(resCap)) {
                dao.create(u);
                response.sendRedirect("login.htm");
            }
                mav.setViewName("Registro");
                mav.addObject("error", "Captcha incorrecto, intentalo de nuevo!");
                mav.addObject("datos", u);
                return mav;
        }
        mav.setViewName("Registro");
        mav.addObject("error", "Algunos de los campos no son validos, el uso de caracteres especiales esta prohibido para todos los campos, intentalo de nuevo!");
        mav.addObject("datos", u);
        return mav;
        }
      mav.setViewName("Registro");
      mav.addObject("error", "Passwords incorrectos");
      mav.addObject("datos", u);
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


     //Validar WebSite
    private boolean validarWebSite(String WebSite)
    { 
    String regex = "^http:\\/\\/(.*\\.(net|com|org))\\/(.*)\\/$";
        Pattern patron = Pattern.compile(regex);
        if(!patron.matcher(WebSite).matches())
        { 
            return false;
        }else
        { 
            return true;
        }
    }
    
    
    private static boolean validar(String validar){
    String regex = "^(([A-Za-z]\\w+)\\s([A-Za-z]\\w+).*)|([A-Za-z]\\w+)";    
    Pattern patron = Pattern.compile(regex);
        if(!patron.matcher(validar).matches())
        { 
            return false;
        }else
        { 
            return true;
        }
        
    }
}
