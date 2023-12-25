package org.generation.italy.jdbc_magazzino.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.jdbc_magazzino.model.entity.Prodotto;

/**
 * Classe delegata alle operazioni CRUD sulla tabella 'prodotto'
 */
public class ProdottoDao extends ADao{	
	
	public ProdottoDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
	}
	
}
