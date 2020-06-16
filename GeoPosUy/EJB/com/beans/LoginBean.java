package com.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.LoginDao;
import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

/**
 * Session Bean implementation class LoginBean
 */
@Stateless
public class LoginBean implements LoginBeanRemote {

	@EJB
	LoginDao servicio;
	
    public LoginBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean verificarLogin(String username, String password) throws ProblemasNivelSQLException, NoSeRealizoOperacionException {
    	
    	boolean existe = servicio.validarLogueo(username, password);
    	
    	return existe;
    }

}
