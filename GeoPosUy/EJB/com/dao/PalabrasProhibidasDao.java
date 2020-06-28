package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import com.clases.codigueras.PalabraProhibida;
import com.exceptions.ProblemasNivelSQLException;

@Remote
public interface PalabrasProhibidasDao {
	
	public int consPalabraProhibida(String Palabra) throws ProblemasNivelSQLException ; 

}