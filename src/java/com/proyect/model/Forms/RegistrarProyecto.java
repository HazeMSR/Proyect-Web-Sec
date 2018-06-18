package com.proyect.model.Forms;

import com.proyect.model.DAO.BitacorasDAO;
import com.proyect.model.DAO.ProyectosDAO;
import com.proyect.model.Entity.Bitacoras;
import com.proyect.model.Entity.Proyectos;
import com.proyect.model.Entity.Usuarios;
import java.io.IOException;
import java.sql.SQLException;
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
@RequestMapping("CrearBitacora.htm")
public class RegistrarProyecto {
         @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request) throws SQLException{
        ModelAndView mav = new ModelAndView();
        
       HttpSession sesion = request.getSession();
       sesion.setAttribute("sesion", sesion.getAttribute("sesion"));
        
        mav.setViewName("User/FormProyecto");
        mav.addObject("datos", new Proyectos());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("proyectos") Proyectos p, BindingResult result, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
      ModelAndView mav = new ModelAndView();
  
      ProyectosDAO dao = new ProyectosDAO();

      //Obtenemos los datos del usuario
       HttpSession sesion = request.getSession();
       sesion.setAttribute("sesion", sesion.getAttribute("sesion"));
       Usuarios user = new Usuarios();
       user = (Usuarios)sesion.getAttribute("sesion");
       
//       Date fecha = new Date(0);
       p.setId(0);
  //     b.setCreated(fecha);
      //-------------Validaciones
        String cap = request.getParameter("captcha");
        String resCap = request.getParameter("resCaptcha");
        boolean c = validarEmail(user.getEmail());
        boolean w = validarWebSite(user.getWebsite());
        boolean n = validar(p.getNombre());
        boolean con = validar(p.getContenido());
        boolean a = validar(p.getArqui());
        boolean m = validar(p.getMunicipio());
        boolean u = validar(p.getUbicacion());
        boolean prop = validar(p.getPropietario());
        boolean t = validar(p.getTipoObra());
          //------------------------

          if (w == true && c == true && n==true && a==true && m==true && con==true && u==true && prop==true && t==true ) {
          if (p.getNombre().contains("drop database") || p.getContenido().contains("drop database") ||
                  p.getArqui().contains("drop database") || p.getImagen().contains("drop database") ||
                  p.getMunicipio().contains("drop database") || p.getUbicacion().contains("drop database") ||
                  p.getPropietario().contains("drop database") || p.getTipoObra().contains("drop database")){
                mav.setViewName("User/FormProyecto");
                mav.addObject("error", "Error, se ha encontrado un ataque!");
                mav.addObject("datos", p);
                return mav;
          }
            if (cap.equals(resCap)) {
                dao.create(p);
                response.sendRedirect("inicioUser.htm");
            }
                mav.setViewName("User/FormProyecto");
                mav.addObject("error", "Captcha incorrecto, intentalo de nuevo!");
                mav.addObject("datos", p);
                return mav;
        }
        mav.setViewName("User/FormProyecto");
        mav.addObject("error", "Alguno de los campos no son validos, intentalo de nuevo!");
        mav.addObject("datos", p);
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
       //Validar demas datos
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
