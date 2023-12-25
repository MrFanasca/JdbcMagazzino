package org.generation.italy.jdbc_magazzino.model.entity;

import java.util.Objects;

/**
 * Classe entity-bean Cliente che effettua il mapping del record della tabella Cliente
 * 
 * @author Angelo Pasquarelli
 */

public class Prodotto {
    
    /***********************/
    // DEFINIZIONE ATTRIBUTI
    /***********************/
    private String codice_prodotto;
    private String descrizione;
    private int quantita_disponibile;
    private float prezzo;

    /***************/
    // COSTRUTTORI
    /***************/
    
    public Prodotto(String codice_prodotto, String descrizione, int quantita_disponibile, float prezzo) {
        this.codice_prodotto = codice_prodotto;
        this.descrizione = descrizione;
        this.quantita_disponibile = quantita_disponibile;
        this.prezzo = prezzo;
    }

    /********************/
    // GETTERS & SETTERS
    /********************/
    
    public String getCodiceProdotto() {
        return codice_prodotto;
    }

    public String getDescrizione() {
        return descrizione;
    }
    
    public int getQuantita_disponibile() {
		return quantita_disponibile;
	}

	public float getPrezzo() {
		return prezzo;
	}

	/***********************************************************************/
    // METODI DERIVATI DALLA CLASSE OBJECT: toString()
    /***********************************************************************/  
	@Override
	public int hashCode() {
		return Objects.hash(codice_prodotto, descrizione, prezzo, quantita_disponibile);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prodotto other = (Prodotto) obj;
		return Objects.equals(codice_prodotto, other.codice_prodotto) && Objects.equals(descrizione, other.descrizione)
				&& Float.floatToIntBits(prezzo) == Float.floatToIntBits(other.prezzo)
				&& quantita_disponibile == other.quantita_disponibile;
	}

    @Override
    public String toString() {
        return "\nProdotto{\n" + "\tcodice_prodotto=" + codice_prodotto + "\n\tdescrizione=" + descrizione + "\n\tquantita_disponibile=" + 
        					quantita_disponibile + "\n\tprezzo=" + prezzo + '}';
    }

}
