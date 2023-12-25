package org.generation.italy.jdbc_magazzino.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.jdbc_magazzino.model.entity.Cliente;

/**
 * Classe delegata alle operazioni CRUD sulla tabella 'cliente'
 */
public class ClienteDao extends ADao{
	
	public ClienteDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
	}

	// load => SELECT	
	/**
	 * // "   SELECT codice_fiscale, nominativo "
	   // + "   FROM cliente                    "
       // + "  WHERE cliente.codice_fiscale = ? ";
	 * @param codiceFiscale codice fiscale del cliente
	 * @return oggetto di tipo classe Cliente
	 * @throws SQLException
	 */
	
	public Cliente loadClienteByPrimaryKey (String codiceFiscale) throws SQLException {
		
			String selectFromClienteByCodiceFiscale =							//imposta il testo del comando SQL da eseguire
					" SELECT codice_fiscale, nominativo "
				  + "   FROM cliente                    "
				  + "  WHERE cliente.codice_fiscale = ? ";
    
			PreparedStatement preparedStatement =								//predispone JDBC per l'invio al database del comando SQL  
					this.jdbcConnectionToDatabase.prepareStatement(selectFromClienteByCodiceFiscale);
			
			preparedStatement.setString(1, codiceFiscale);						//imposta il valore del parametro di ricerca codice fiscale (parametro String)
    
			ResultSet rsSelect
				= preparedStatement.executeQuery();								//esegue la query di SELECT e si predisone a leggere i risutlati presenti in memoria nel DBMS

			Cliente cliente= null;												//istanza dell'entity-bean di tipo classe Cliente
    
			if (rsSelect.next()) {												//fino a che ci sono risutalti da leggere

				String codFiscale = rsSelect.getString("codice_fiscale");		//lettura del valore del campo codice_fiscale
				if (rsSelect.wasNull()) {
					codFiscale = "";
				}

				String nominativo = rsSelect.getString("nominativo");			//lettura del valore del campo 'nominativo'
                	
				if (rsSelect.wasNull()) {
					nominativo = "";
				}

				cliente = new Cliente(codFiscale, nominativo);					//istanzia un oggetto di tipo classe Cliente inizializzandolo con i valori letti dal record
			}
			
			return cliente;
	}

	/**
	 * // "   SELECT codice_fiscale, nominativo "
	   // + "   FROM cliente                    "
       // + "  WHERE cliente.nominativo = ? ";
	 * @param nominativo nome e cognome del cliente
	 * @return oggetto di tipo classe Cliente
	 * @throws SQLException
	 */
	
	public Cliente loadClienteByNominativo (String nominativoRicercato) throws SQLException {
		
			String selectFromClienteByCodiceFiscale =							//imposta il testo del comando SQL da eseguire
					" SELECT codice_fiscale, nominativo "
				  + "   FROM cliente                    "
				  + "  WHERE cliente.nominativo = ? ";

			// String parametroCodiceFiscale = "Persichetti";					//imposta il valore del parametro codice fiscale
    
			PreparedStatement preparedStatement =								//predispone JDBC per l'invio al database del comando SQL  
					this.jdbcConnectionToDatabase.prepareStatement(selectFromClienteByCodiceFiscale);
			
			preparedStatement.setString(1, nominativoRicercato);				//imposta il valore del parametro di ricerca codice fiscale (parametro String)
    
			ResultSet rsSelect
				= preparedStatement.executeQuery();								//esegue la query di SELECT e si predisone a leggere i risutlati presenti in memoria nel DBMS

			Cliente cliente= null;												//istanza dell'entity-bean di tipo classe Cliente
    
			if (rsSelect.next()) {												//fino a che ci sono risutalti da leggere

				String codFiscale = rsSelect.getString("codice_fiscale");		//lettura del valore del campo codice_fiscale
				if (rsSelect.wasNull()) {
					codFiscale = "";
				}

				String nominativo = rsSelect.getString("nominativo");			//lettura del valore del campo 'nominativo'
                	
				if (rsSelect.wasNull()) {
					nominativo = "";
				}

				cliente = new Cliente(codFiscale, nominativo);					//istanzia un oggetto di tipo classe Cliente inizializzandolo con i valori letti dal record
			}
			
			return cliente;
	}
	
	/**
	 * // "   SELECT codice_fiscale, nominativo "
       // + "   FROM cliente                    "
	   // + "  WHERE cliente.nominativo LIKE ? 	";
	 * @param nominativoLike nominativo sul codice del prodotto
	 * @return elenco clienti trovati
	 * @throws SQLException
	 */
	
	public List <Cliente> loadClienteByNominativoLike (String nominativoLike) throws SQLException {
		 
			String selectFromClienteByNominativo =								//imposta il testo del comando SQL da eseguire
	    			" SELECT codice_fiscale, nominativo "
	              + "   FROM cliente                    "
	              + "  WHERE cliente.nominativo LIKE ? 	";

		    // String parametroNominativo = "%Pers%";							//imposta il valore del parametro codice fiscale
		    
	        PreparedStatement preparedStatementLike =							//predispone JDBC per l'invio al database del comando SQL  
	        		this.jdbcConnectionToDatabase.prepareStatement(selectFromClienteByNominativo);
	        
	        preparedStatementLike.setString(1, nominativoLike);					//imposta il valore del parametro di ricerca codice fiscale (parametro String)
	        
	        ResultSet rsSelectLike
	            = preparedStatementLike.executeQuery();							//esegue la query di SELECT e si predisone a leggere i risutlati presenti in memoria nel DBMS
	
	        List<Cliente> elencoClienti = new ArrayList<>();					//istanza dell'elenco di entity-bean di tipo classe Cliente
	        
		    while (rsSelectLike.next()) {										//fino a che ci sono risutalti da leggere
		
		        String codFiscale = rsSelectLike.getString("codice_fiscale");	//lettura del valore del campo codice_fiscale
		        if (rsSelectLike.wasNull()) {
		            codFiscale = "";
		        }
		
		        String nominativo												//lettura del valore del campo 'nominativo'
		                = rsSelectLike.getString("nominativo");
		        if (rsSelectLike.wasNull()) {
		            nominativo = "";
		        }
		
		        Cliente clienteLetto = new Cliente(codFiscale, nominativo);		//istanzia un oggetto di tipo classe Cliente inizializzandolo con i valori letti dal record
		        elencoClienti.add(clienteLetto);								//aggiunge all'elenco l'oggetto istanziato
		    }
		    
		    return elencoClienti;
	}
	
	// add => INSERT	
	/**
	 * //   "INSERT INTO cliente (codice_fiscale, nominativo)"
	   // + "     VALUES (?, ?)                              ";
	 * @param cliente oggetto della classe Cliente da inserire
	 * @throws SQLException
	 */
	
	public void addCliente (Cliente cliente)	throws SQLException {
		
		String insertCliente = 													//imposta il testo del comando SQL da eseguire
				 "INSERT INTO cliente (codice_fiscale, nominativo)"
			   + "     VALUES (?, ?)                              ";
		
		PreparedStatement preparedStatementInsertCliente = 						//predispone JDBC per l'invio al database del comando SQL
				this.jdbcConnectionToDatabase.prepareStatement(insertCliente);
		
		preparedStatementInsertCliente.setString(1, cliente.getCodiceFiscale());
		preparedStatementInsertCliente.setString(2, cliente.getNominativo());
		
		preparedStatementInsertCliente.executeUpdate();
		
	}

}
