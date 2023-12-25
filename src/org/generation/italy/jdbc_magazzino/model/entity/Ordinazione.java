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
	private int quantitOrdine;
	private int prezzoAquisto;
	
	 /***********************/
    // COSTRUTTORE
    /***********************/
	public Ordinazione(String codiceFiscale, String codiceProdotto, LocalDate dataOrdine, int quantitOrdine,
			int prezzoAquisto) {
		this.codiceFiscale = codiceFiscale;
		this.codiceProdotto = codiceProdotto;
		this.dataOrdine = dataOrdine;
		this.quantitOrdine = quantitOrdine;
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
		return quantitOrdine;
	}

	public int getPrezzoAquisto() {
		return prezzoAquisto;
	}
	
    /***********************************************************************/
    // METODI DERIVATI DALLA CLASSE OBJECT: toString(), equals(), hashCode()
    /***********************************************************************/  
	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale, codiceProdotto, dataOrdine, prezzoAquisto, quantitOrdine);
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
				&& prezzoAquisto == other.prezzoAquisto && quantitOrdine == other.quantitOrdine;
	}    
	
	@Override
	public String toString() {
		return "Ordinazione [codiceFiscale=" + codiceFiscale + ", codiceProdotto=" + codiceProdotto + ", dataOrdine="
				+ dataOrdine + ", quantitOrdine=" + quantitOrdine + ", prezzoAquisto=" + prezzoAquisto + "]";
	}

	
}
