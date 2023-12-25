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
	
	// load => SELECT
	/**
	   // SELECT codice_prodotto, descrizione, quantita_disponibile, prezzo
	   //   FROM prodotto
	   //  WHERE codice_prodotto = ?
	 * @param codProdotto
	 * @return
	 * @throws SQLException
	 */
	
	public Prodotto loadProdottoByPrimaryKey (String codiceProdotto)	throws SQLException {
		
		String selectFromProdottoByCodiceFiscale = "SELECT codice_prodotto, descrizione, quantita_disponibile, prezzo "
												 + "  FROM prodotto                                                   "
												 + " WHERE codice_prodotto = ?                                        ";
		
		PreparedStatement preparedStatement =
				this.jdbcConnectionToDatabase.prepareStatement(selectFromProdottoByCodiceFiscale);
		
		preparedStatement.setString(1, codiceProdotto);
		
		ResultSet rsSelect =
				preparedStatement.executeQuery();
		
		Prodotto prodotto = null;
		
		if (rsSelect.next())	{
			
			String codProdotto = rsSelect.getString("codice_prodotto");
			if (rsSelect.wasNull())	{
				codProdotto = "";
			}
			
			String descrizione = rsSelect.getString("descrizione");
			if (rsSelect.wasNull())	{
				descrizione = "";
			}
			
			Integer quantitaDisponibile = rsSelect.getInt("quantita_disponibile");
			if (rsSelect.wasNull())	{
				quantitaDisponibile = 0;
			}
			
			Float prezzo = rsSelect.getFloat("prezzo");
			if (rsSelect.wasNull())	{
				prezzo = 0f;
			}
			
			prodotto = new Prodotto(codProdotto, descrizione, quantitaDisponibile, prezzo);
		}
		
		return prodotto;
	}
	
}
