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
	 * @param codProdotto codice del prodotto da ricercare
	 * @return oggetto di tipo Prodotto
	 * @throws SQLException
	 */
	
	public Prodotto loadProdottoByPrimaryKey (String codiceProdotto) throws SQLException {
		
		String selectFromProdottoByCodiceProdotto = 							//imposta il testo del comando SQL da eseguire
				"SELECT codice_prodotto, descrizione, quantita_disponibile, prezzo "
			  + "  FROM prodotto                                                   "
			  + " WHERE codice_prodotto = ?                                        ";
		
		PreparedStatement preparedStatement =									//predispone JDBC per l'invio al database del comando SQL 
				this.jdbcConnectionToDatabase.prepareStatement(selectFromProdottoByCodiceProdotto);
		
		preparedStatement.setString(1, codiceProdotto);							//imposta il valore del parametro di ricerca codice prodotto (parametro String)
		
		ResultSet rsSelect =													
				preparedStatement.executeQuery();								//esegue la query di SELECT e si predisone a leggere i risutlati presenti in memoria nel DBMS
		
		Prodotto prodotto = null;												//istanza dell'entity-bean di tipo classe Prodotto
		
		if (rsSelect.next())	{												//fino a che ci sono risutalti da leggere
			
			String codProdotto = rsSelect.getString("codice_prodotto");			//lettura del valore del campo 'codice_prodotto'
			if (rsSelect.wasNull())	{
				codProdotto = "";
			}
			
			String descrizione = rsSelect.getString("descrizione");				//lettura del valore del campo 'descrizione'
			if (rsSelect.wasNull())	{
				descrizione = "";
			}
																				//lettura del valore del campo 'quantita_disponibile'
			Integer quantitaDisponibile = rsSelect.getInt("quantita_disponibile");
			if (rsSelect.wasNull())	{											
				quantitaDisponibile = 0;
			}
			
			Float prezzo = rsSelect.getFloat("prezzo");							//lettura del valore del campo 'prezzo'
			if (rsSelect.wasNull())	{
				prezzo = 0f;
			}
																				//istanzia un oggetto di tipo classe Prodotto inizializzandolo con i valori letti dal record
			prodotto = new Prodotto(codProdotto, descrizione, quantitaDisponibile, prezzo);
		}																		
		
		return prodotto;
	}
	
	/**
	   // SELECT codice_prodotto, descrizione, quantita_disponibile, prezzo
	   //   FROM prodotto
	   //  WHERE codice_prodotto LIKE ?
	 * @param codiceProdotto codice del prodotto da ricercare
	 * @return elenco prodotti trovati
	 * @throws SQLException
	 */
	public List <Prodotto> loadProdottoByCodiceProdottoLike (String codiceProdotto) throws SQLException {
		
		String selectFromProdottoByProdotto = 									//imposta il testo del comando SQL da eseguire
				" SELECT codice_prodotto, descrizione, quantita_disponibile, prezzo "
				+ " FROM prodotto                                                   "
				+ "WHERE codice_prodotto LIKE ?                                     ";
		
		PreparedStatement preparedStatement =									//predispone JDBC per l'invio al database del comando SQL 
				this.jdbcConnectionToDatabase.prepareStatement(selectFromProdottoByProdotto);
		
		preparedStatement.setString(1, codiceProdotto);							//imposta il valore del parametro di ricerca codice prodotto (parametro String)
		
		ResultSet rsSelect =
				preparedStatement.executeQuery();								//esegue la query di SELECT e si predisone a leggere i risutlati presenti in memoria nel DBMS
		
		List <Prodotto> elencoProdotti = new ArrayList <>();					//istanza dell'elenco di entity-bean di tipo classe Prodotto
		
		while (rsSelect.next())	{												//fino a che ci sono risutalti da leggere
			
			String codProdotto = rsSelect.getString("codice_prodotto");			//lettura del valore del campo 'codice_prodotto'
			if (rsSelect.wasNull())	{
				codProdotto = "";
			}
			
			String descrizione = rsSelect.getString("descrizione");				//lettura del valore del campo 'descrizione'
			if (rsSelect.wasNull())	{
				descrizione = "";
			}
																				//lettura del valore del campo 'quantita_disponibile'
			Integer quantitaDisponibile = rsSelect.getInt("quantita_disponibile");
			if (rsSelect.wasNull())	{											
				quantitaDisponibile = 0;
			}
			
			Float prezzo = rsSelect.getFloat("prezzo");							//lettura del valore del campo 'prezzo'
			if (rsSelect.wasNull())	{
				prezzo = 0f;
			}
																				//istanzia un oggetto di tipo classe Cliente inizializzandolo con i valori letti dal record
			Prodotto prodottoLetto = new Prodotto(codiceProdotto, descrizione, quantitaDisponibile, prezzo);
			elencoProdotti.add(prodottoLetto);									//aggiunge all'elenco l'oggetto istanziato
		}
			
		return elencoProdotti;
	}
	
	// add => INSERT
	/**
	 // INSERT INTO prodotto (codice_prodotto, descrizione, quantita_disponibile, prezzo)
	 //      VALUES (?, ?, ?, ?)
	 * @param prodotto oggetto di tipo Prodotto da inserire
	 * @throws SQLException
	 */
	public void addProdotto (Prodotto prodotto) throws SQLException {
		
		String insertProdotto =													//imposta il testo del comando SQL da eseguire
				"  INSERT INTO prodotto (codice_prodotto, descrizione, quantita_disponibile, prezzo)"
				+ "     VALUES (?, ?, ?, ?)                                                         ";
		
		PreparedStatement preparedStatementinsertProdotto =						//predispone JDBC per l'invio al database del comando SQL
				this.jdbcConnectionToDatabase.prepareStatement(insertProdotto);
		
		preparedStatementinsertProdotto.setString(1, prodotto.getCodiceProdotto());		//imposta il valore del parametro di inserimento 'codice_prodotto' 
		preparedStatementinsertProdotto.setString(2, prodotto.getDescrizione());		//imposta il valore del parametro di inserimento 'descrizione'
		preparedStatementinsertProdotto.setInt(3, prodotto.getQuantita_disponibile());	//imposta il valore del parametro di inserimento 'quantita_disponibile'
		preparedStatementinsertProdotto.setFloat(4, prodotto.getPrezzo());				//imposta il valore del parametro di inserimento 'prezzo'
		
		preparedStatementinsertProdotto.executeUpdate();								//esegue la query di INSERT
	}
	
}
