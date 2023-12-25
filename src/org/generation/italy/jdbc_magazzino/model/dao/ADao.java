package org.generation.italy.jdbc_magazzino.model.dao;

import java.sql.Connection;
/**
 * Classe gderivata dalla generalizzazione dell'attributo jdbcConnectionToDatabase
 */
public abstract class ADao {
	
	protected Connection jdbcConnectionToDatabase;								// attributo di riferimento per la connessione al database
	
	
	public ADao(Connection jdbcConnectionToDatabase) {
		this.jdbcConnectionToDatabase = jdbcConnectionToDatabase;
	}

}
