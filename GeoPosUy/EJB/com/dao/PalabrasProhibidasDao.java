package com.dao;

import javax.ejb.Remote;

import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface PalabrasProhibidasDao {
	
	public int consPalabraProhibida(String Palabra) throws ProblemasNivelSQLException ; 

}