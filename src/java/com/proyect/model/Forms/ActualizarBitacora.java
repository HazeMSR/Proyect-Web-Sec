package com.proyect.model.Forms;

import com.proyect.model.DAO.BitacorasDAO;
import com.proyect.model.DAO.ProyectosDAO;
import com.proyect.model.DAO.UsuariosDAO;
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
@RequestMapping("ModificarBitacora.htm")
public class ActualizarBitacora {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request) throws SQLException{
        ModelAndView mav = new ModelAndView();
        
        //Obtenemos los datos de la bitacora
        Bitacoras bitacora = new Bitacoras();
        Bitacoras bitacorAux = new Bitacoras();
        BitacorasDAO daoB = new BitacorasDAO();
        bitacora.setId(Integer.parseInt(request.getParameter("id")));
        bitacorAux = daoB.Read(bitacora);
        
        ProyectosDAO dao = new ProyectosDAO();
        Proyectos proyect = new Proyectos();
        proyect.setId(bitacorAux.getProyecto_id());
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("sesion", sesion.getAttribute("sesion"));
        sesion.setAttribute("proyect", dao.Read(proyect));
        
        mav.setViewName("User/ActualizarBitacoras");
        mav.addObject("datos", bitacorAux);
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("bitacoras") Bitacoras b, BindingResult result, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
      ModelAndView mav = new ModelAndView();
  
      BitacorasDAO dao = new BitacorasDAO();

      //Obtenemos los datos del usuario
       HttpSession sesion = request.getSession();
       sesion.setAttribute("sesion", sesion.getAttribute("sesion"));
       Usuarios user = new Usuarios();
       user = (Usuarios)sesion.getAttribute("sesion");
       
  //     String img = request.getParameter("imgen");
       
      //-------------Validaciones
        String cap = request.getParameter("captcha");
        String resCap = request.getParameter("resCaptcha");
        boolean c = validarEmail(user.getEmail());
        boolean w = validarWebSite(user.getWebsite());
        boolean n = validar(b.getNombre());
        boolean a = validar(b.getContenido());
        boolean m = validar(b.getM2());
        
      //------------------------
      
//      b.setImagen(img);
  
      if (w == true && c == true && n==true && a==true && m==true) {
          if (b.getNombre().contains("drop database") || b.getContenido().contains("drop database") ||
                  b.getM2().contains("drop database") || b.getImagen().contains("drop database")) {
                mav.setViewName("User/ActualizarBitacoras");
                mav.addObject("error", "Error, se ha encontrado un ataque!");
                mav.addObject("datos", b);
                return mav;
          }
            if (cap.equals(resCap)) {
                dao.Update(b);
                response.sendRedirect("inicioUser.htm");
            }
                mav.setViewName("User/ActualizarBitacoras");
                mav.addObject("error", "Captcha incorrecto, intentalo de nuevo!");
                mav.addObject("datos", b);
                return mav;
        }
        mav.setViewName("User/ActualizarBitacoras");
        mav.addObject("error", "Alguno de los campos no son validos, el uso de caracteres especiales esta prohibido para todos los campos, intentalo de nuevo!");
        mav.addObject("datos", b);
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
