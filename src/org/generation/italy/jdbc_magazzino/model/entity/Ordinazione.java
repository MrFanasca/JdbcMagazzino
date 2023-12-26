package org.generation.italy.jdbc_magazzino.model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Ordinazione {
	

	/***********************/
    // DEFINIZIONE ATTRIBUTI
    /***********************/
	private String codiceFiscale;
	private String codiceProdotto;
	private LocalDate dataOrdine;
	private int quantitaOrdine;
	private float prezzoAquisto;
	
	 /***********************/
    // COSTRUTTORE
    /***********************/
	// costruttore usato per il metodo di INSERT
	public Ordinazione(String codiceFiscale, String codiceProdotto, int quantitaOrdine, float prezzoAquisto) {
		this.codiceFiscale = codiceFiscale;
		this.codiceProdotto = codiceProdotto;
		this.quantitaOrdine = quantitaOrdine;
		this.dataOrdine = LocalDate.now();
		this.prezzoAquisto = prezzoAquisto;
	}
	
	
	// costruttore usato per i metodi di SELECT
	public Ordinazione(String codiceFiscale, String codiceProdotto, LocalDate dataOrdine, int quantitaOrdine,
			float prezzoAquisto) {
		this.codiceFiscale = codiceFiscale;
		this.codiceProdotto = codiceProdotto;
		this.dataOrdine = dataOrdine;
		this.quantitaOrdine = quantitaOrdine;
		this.prezzoAquisto = prezzoAquisto;
	}

	/********************/
    // GETTERS & SETTERS
    /********************/
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getCodiceProdotto() {
		return codiceProdotto;
	}

	public LocalDate getDataOrdine() {
		return dataOrdine;
	}

	public int getQuantitOrdine() {
		return quantitaOrdine;
	}

	public float getPrezzoAquisto() {
		return prezzoAquisto;
	}
	
    /***********************************************************************/
    // METODI DERIVATI DALLA CLASSE OBJECT: toString(), equals(), hashCode()
    /***********************************************************************/  
	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale, codiceProdotto, dataOrdine, prezzoAquisto, quantitaOrdine);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordinazione other = (Ordinazione) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale)
				&& Objects.equals(codiceProdotto, other.codiceProdotto) && Objects.equals(dataOrdine, other.dataOrdine)
				&& prezzoAquisto == other.prezzoAquisto && quantitaOrdine == other.quantitaOrdine;
	}    
	
	@Override
	public String toString() {
		return "Ordinazione [codiceFiscale=" + codiceFiscale + ", codiceProdotto=" + codiceProdotto + ", dataOrdine="
				+ dataOrdine + ", quantitOrdine=" + quantitaOrdine + ", prezzoAquisto=" + prezzoAquisto + "]";
	}

	
}
