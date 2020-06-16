package com.beans;

import javax.ejb.Remote;

import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface LoginBeanRemote {

	boolean verificarLogin(String username, String password) throws ProblemasNivelSQLException, NoSeRealizoOperacionException;

}
