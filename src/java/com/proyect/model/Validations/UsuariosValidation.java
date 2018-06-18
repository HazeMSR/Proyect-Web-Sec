package com.proyect.model.Validations;

import com.proyect.model.Entity.Usuarios;
import java.util.Date;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 *
 * @author jllpz
 */
/*public class UsuariosValidation implements Validator{
    @Override
    public boolean supports(Class<?> type) {
        return Usuarios.class.isAssignableFrom(type);
    }
  @Override
    public void validate(Object o, Errors errors) {
        Usuarios user = (Usuarios)o;
        ValidationUtils.rejectIfEmpty(errors, "nombre", "required.nombre", "El campo Nombre es obligatorio");
        ValidationUtils.rejectIfEmpty(errors, "email", "required.email", "El campo Nombre es obligatorio");
    }
}*/
public class UsuariosValidation{
         //Validar WebSite
    private static boolean validarWebSite(String WebSite)
    { 
    String regex = "^http:\\/\\/(.*\\.(net|com|org))\\/(.*)\\/$"; //Web site
//    String regex = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$"; //mail
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

    public static void main(String[] args) {
        System.out.println(validar("<script>"));
        System.out.println(validar("algo' or '1'= '1"));   
        System.out.println(validar("drop database;"));   
        System.out.println(validar("Hola como estás bb"));   
        System.out.println(validar("hola como estas bb"));   
        System.out.println(validar("abcdefg"));   
        System.out.println(validar("    "));   
    }
}
