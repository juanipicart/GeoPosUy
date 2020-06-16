package com.dao;

import javax.ejb.Remote;

import com.exceptions.NoSeRealizoOperacionException;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface LoginDao {

	boolean validarLogueo(String username, String password)
			throws ProblemasNivelSQLException, NoSeRealizoOperacionException;

}
