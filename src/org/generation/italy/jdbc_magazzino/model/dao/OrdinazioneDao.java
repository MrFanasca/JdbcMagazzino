package org.generation.italy.jdbc_magazzino.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.jdbc_magazzino.model.entity.Ordinazione;

public class OrdinazioneDao extends ADao{

	public OrdinazioneDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
	}
	
	// load => SELECT
	/**
	 // SELECT codice_fiscale, codice_prodotto, data_ordine, quantit_ordine, prezzo_aquisto
	 //   FROM ordinazione
	 //  WHERE codice_fiscale = ?
	 * @param codFiscale chiave ricerca ordinazioni 
	 * @return elenco ordinazioni trovate
	 * @throws SQLException
	 */
	public List <Ordinazione> loadOrdinazioneByCodiceFiscale (String codFiscale) throws SQLException {
		
		String selectFromOrdinazioneByCodiceFiscale =							//imposta il testo del comando SQL da eseguire
				"  SELECT codice_fiscale, codice_prodotto, data_ordine, quantit_ordine, prezzo_acquisto "
				+ "  FROM ordinazione                                                                  "
				+ " WHERE codice_fiscale = ?                                                           ";
		
		PreparedStatement preparedStatement = 									//predispone JDBC per l'invio al database del comando SQL 
				this.jdbcConnectionToDatabase.prepareStatement(selectFromOrdinazioneByCodiceFiscale);
		
		preparedStatement.setString(1, codFiscale);								//imposta il valore del parametro di ricerca codice prodotto (parametro String)
		
		ResultSet rsSelect =
				preparedStatement.executeQuery();								//esegue la query di SELECT e si predisone a leggere i risutlati presenti in memoria nel DBMS
		
		List <Ordinazione> ordinazioniCodiceFiscale = new ArrayList<>();		//istanza dell'elenco di entity-bean di tipo classe Ordinazione
		
		while (rsSelect.next())	{												//fino a che ci sono risutalti da leggere
			
			String codiceFiscale = rsSelect.getString("codice_fiscale");		//lettura del valore del campo 'codice_fiscale'
			if (rsSelect.wasNull())	{
				codiceFiscale = "";
			}
			
			String codiceProdotto = rsSelect.getString("codice_prodotto");		//lettura del valore del campo 'codice_prodotto'
			if (rsSelect.wasNull())	{
				codiceProdotto = "";
			}
			
			LocalDate dataOrdine = rsSelect.getDate("data_ordine").toLocalDate();
			if (rsSelect.wasNull())	{											//lettura del valore del campo 'data_ordine'
				dataOrdine = null;
			}
			
			Integer quantitaOrdine = rsSelect.getInt("quantita_ordine");		//lettura del valore del campo 'quantita_ordine'
			if (rsSelect.wasNull())	{
				quantitaOrdine = 0;
			}
			
			Float prezzoAcquisto = rsSelect.getFloat("prezzo_acquisto");		//lettura del valore del campo 'prezzo_acquisto'
			if (rsSelect.wasNull())	{
				prezzoAcquisto = 0f;
			}
			
			Ordinazione ordinazioniLette = 										//istanzia un oggetto di tipo classe Ordinazione inizializzandolo con i valori letti dal record
					new Ordinazione(codiceFiscale, codiceProdotto, dataOrdine, quantitaOrdine, prezzoAcquisto);
			
			ordinazioniCodiceFiscale.add(ordinazioniLette);						//aggiunge all'elenco l'oggetto istanziato
		}
		
		return ordinazioniCodiceFiscale;
	}
	
	/**
	 // SELECT codice_fiscale, codice_prodotto, data_ordine, quantit_ordine, prezzo_aquisto
	 //   FROM ordinazione
	 //  WHERE codice_prodotto = ?
	 * @param codProdotto chiave ricerca ordinazioni 
	 * @return elenco ordinazioni trovate
	 * @throws SQLException
	 */
	public List <Ordinazione> loadOrdinazioneByCodiceProdotto (String codProdotto) throws SQLException {
		
		String selectFromOrdinazioneByCodiceProgresso =							//imposta il testo del comando SQL da eseguire
				" SELECT codice_fiscale, codice_prodotto, data_ordine, quantit_ordine, prezzo_aquisto "
				+ " FROM ordinazione                                                                  "
				+ "WHERE codice_prodotto = ?                                                          ";
		
		PreparedStatement preparedStatement =									//predispone JDBC per l'invio al database del comando SQL
				this.jdbcConnectionToDatabase.prepareStatement(selectFromOrdinazioneByCodiceProgresso);
		
		preparedStatement.setString(1, codProdotto);							//imposta il valore del parametro di ricerca codice prodotto (parametro String)
		
		ResultSet rsSelect =
				preparedStatement.executeQuery();								//esegue la query di SELECT e si predisone a leggere i risutlati presenti in memoria nel DBMS
		
		List <Ordinazione> ordinazioniCodiceProdotto = new ArrayList<>();		//istanza dell'elenco di entity-bean di tipo classe Ordinazione
		
		while (rsSelect.next())	{												//fino a che ci sono risutalti da leggere
			
			String codiceFiscale = rsSelect.getString("codice_fiscale");		//lettura del valore del campo 'codice_fiscale'
			if (rsSelect.wasNull())	{
				codiceFiscale = "";
			}
			
			String codiceProdotto = rsSelect.getString("codice_prodotto");		//lettura del valore del campo 'codice_prodotto'
			if (rsSelect.wasNull())	{
				codiceProdotto = "";
			}
			
			LocalDate dataOrdine = rsSelect.getDate("data_ordine").toLocalDate();
			if (rsSelect.wasNull())	{											//lettura del valore del campo 'data_ordine'
				dataOrdine = LocalDate.of(0, 0, 0);
			}
			
			Integer quantitaOrdine = rsSelect.getInt("quantita_ordine");		//lettura del valore del campo 'quantita_ordine'
			if (rsSelect.wasNull())	{
				quantitaOrdine = 0;
			}
			
			Float prezzoAcquisto = rsSelect.getFloat("prezzo_acquisto");		//lettura del valore del campo 'prezzo_acquisto'
			if (rsSelect.wasNull())	{
				prezzoAcquisto = 0.0f;
			}
			
			Ordinazione ordinazioniLette = 										//istanzia un oggetto di tipo classe Ordinazione inizializzandolo con i valori letti dal record
					new Ordinazione(codiceFiscale, codiceProdotto, dataOrdine, quantitaOrdine, prezzoAcquisto);
			
			ordinazioniCodiceProdotto.add(ordinazioniLette);					//aggiunge all'elenco l'oggetto istanziato
		}
		
		return ordinazioniCodiceProdotto;
	}
	
	// add => INSERT
	/**
	 // INSERT INTO ordinazione (codice_fiscale, codice_prodotto, data_ordine, quantit_ordine, prezzo_aquisto)
	 //      VALUES (?, ?, ?, ?, ?)
	 * @param ordinazione oggetto di tipo Ordinazione da inserire
	 * @throws SQLException
	 */
	public void addOrdinazione (Ordinazione ordinazione) throws SQLException {
		
		String InsertOrdinazione =												//imposta il testo del comando SQL da eseguire
				"INSERT INTO ordinazione (codice_fiscale, codice_prodotto, quantit_ordine, prezzo_aquisto) "
				+ "   VALUES (?, ?, ?, ?)                                                                  ";
		
		PreparedStatement preparedStatementInsertOrdinazione = 					//predispone JDBC per l'invio al database del comando SQL
				this.jdbcConnectionToDatabase.prepareStatement(InsertOrdinazione);
		
		preparedStatementInsertOrdinazione.setString(1, ordinazione.getCodiceFiscale());		//imposta il valore del parametro di inserimento 'codice_fiscale' 
		preparedStatementInsertOrdinazione.setString(2, ordinazione.getCodiceProdotto());		//imposta il valore del parametro di inserimento 'codice_prodotto'
		preparedStatementInsertOrdinazione.setInt(3, ordinazione.getQuantitOrdine());			//imposta il valore del parametro di inserimento 'quantita_ordine' 
		preparedStatementInsertOrdinazione.setFloat(4, ordinazione.getPrezzoAquisto());			//imposta il valore del parametro di inserimento 'prezzo_acquisto' 
		
		preparedStatementInsertOrdinazione.executeUpdate();						//esegue la query di INSERT
	}
}
