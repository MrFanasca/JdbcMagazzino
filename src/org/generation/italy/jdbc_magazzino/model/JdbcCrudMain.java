/**
 * Classe per operazioni CRUD (Create/Read/Update/Delete) sul database, invio di comandi SQL
 * 
 * @author Angelo Pasquarelli
 *
 */
package org.generation.italy.jdbc_magazzino.model;

import java.sql.Connection;														//classe istanziata per mantenere il riferimento alla connessione stabilita verso il database 
import java.sql.DriverManager;													//classe di riferimento per l'uso del JDBC driver installato come dipendenza nel pom.xml di Maven										
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;													//imposta classe SqlException per gestione errori nella comunicazione col database
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.jdbc_magazzino.model.dao.ClienteDao;
import org.generation.italy.jdbc_magazzino.model.entity.Cliente;


public class JdbcCrudMain {

	
	public static void main(String[] args) {									//metodo di accesso all'applicazione da parte della Java Virtual Machine (vedi slide architettura Java)
		
		
		// Connessione al database
		String databaseName = "magazzino";										//nome del database a cui connettersi
		String dbmsUserName = "root";											//nome utente configurato nel dbms
		String dbmsPassword = "";												//password utente configurato nel dbms
		String jdbcUrl = "jdbc:mariadb://localhost:3306/" + databaseName;		
		
		try {																	//prova ad eseguire le istruzioni interne al blocco try-catch

			
			/*************************************************************************************/
			/*				        	  CONNESSIONE AL DATABASE								 */
			/*************************************************************************************/
			
			Connection jdbcConnectionToDatabase = 								//esegue la connessione al dbms con riferimento al database, se fallisce genera eccezzione SQLException (effettuare il debugging per verificarlo)
					DriverManager.getConnection(jdbcUrl
											  , dbmsUserName
											  , dbmsPassword);
			
			System.out.println("Connessione al database magazzino riuscita!");	//visualizza messaggio per avvenuta connessione al database
			
			ClienteDao clienteDao = new ClienteDao(jdbcConnectionToDatabase);	// Istanzionazione della classe ClienteDao
			
			
			/*************************************************************************************/
			/* 	 ESECUZIONE DELLA QUERY DI SELECT CON CONDIZIONE WHERE SU CHIAVE PRIMARIA (=)    */
			/*************************************************************************************/
			
			String parametroCodiceFiscale = "BRNGRD0123456789";					//imposta il valore del parametro codice fiscale
			
		    Cliente cliente = clienteDao.loadClienteByPrimaryKey(parametroCodiceFiscale);            
		    
		    if (cliente != null) {
		    	System.out.println("Dati del cliente letto=> " + cliente.toString());
		    }
		    else {
		    	System.out.println("Il cliente ricercato non è presente!!!");
		    }
		    
		    
		    
		    /*************************************************************************************/
			/* 	           ESECUZIONE DELLA QUERY DI SELECT CON CONDIZIONE WHERE                 */
		    /*************************************************************************************/
		    
		    String parametroNominativo = "Persichetti";
		    
		    Cliente clienteNominativo = clienteDao.loadClienteByNominativo(parametroNominativo);
		    
		    if (clienteNominativo != null) {
		    	System.out.println("Dati del cliente letto=> " + clienteNominativo.toString());
		    }
		    else {
		    	System.out.println("Il cliente ricercato non è presente!!!");
		    }
		    
		    
		    /*************************************************************************************/
			/* 	        ESECUZIONE DELLA QUERY DI SELECT CON CONDIZIONE WHERE CON LIKE 	         */
		    /*************************************************************************************/
		    
		    String parametroNominativoLike = "%Pers%";							//imposta il valore del parametro codice fiscale
		    
	        List<Cliente> elencoClienti = clienteDao.loadClienteByNominativoLike(parametroNominativoLike);            
		    
		    if (elencoClienti.size() != 0) {									//clienti trovati? 
		    	System.out.println("Dati dei clienti trovati=> \n " + elencoClienti.toString());
	    																		//visualizza il contenuto dell'elenco 
		    }
		    else {
		    	System.out.println("Nessun cliente trovato!!!");
		    																	//visualizza il messaggio per elenco vuoto 
		    }

		    /*************************************************************************************/
			/* 	                 ESECUZIONE DELLA QUERY DI INSERT CLIENTE 	                     */
		    /*************************************************************************************/
		    
		    Cliente clienteToInsert = new Cliente ("GVNFRN0123456789", "Giovanni Furino");
		    
		    clienteDao.addCliente(clienteToInsert);
		    
		    System.out.println("Cliente inserito=> " + clienteToInsert.toString());
		    
		    
		} catch (SQLException e) {												//errore di tipo classe SQLException
			// TODO Auto-generated catch block
			// e.printStackTrace();												//stampa la pila (stack) degli errori, dal più recente al meno recente
			System.out.println(e.getMessage()); 								//stampa lo sepcifico messaggio di errore
			// throw new JdbcMagazzinoException();
		}
		
//		} catch (NullPointerException e1) {
//		System.out.println(e1.getMessage());
//		}

		
	}
	
	
}
