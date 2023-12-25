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
    private String codiceProdotto;
    private String descrizione;
    private int quantitaDisponibile;
    private float prezzo;

    /***************/
    // COSTRUTTORI
    /***************/
    
    public Prodotto(String codiceProdotto, String descrizione, int quantitaDisponibile, float prezzo) {
        this.codiceProdotto = codiceProdotto;
        this.descrizione = descrizione;
        this.quantitaDisponibile = quantitaDisponibile;
        this.prezzo = prezzo;
    }

    /********************/
    // GETTERS & SETTERS
    /********************/
    
    public String getCodiceProdotto() {
        return codiceProdotto;
    }

    public String getDescrizione() {
        return descrizione;
    }
    
    public int getQuantita_disponibile() {
		return quantitaDisponibile;
	}

	public float getPrezzo() {
		return prezzo;
	}

	/***********************************************************************/
    // METODI DERIVATI DALLA CLASSE OBJECT: toString()
    /***********************************************************************/  
	@Override
	public int hashCode() {
		return Objects.hash(codiceProdotto, descrizione, prezzo, quantitaDisponibile);
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
		return Objects.equals(codiceProdotto, other.codiceProdotto) && Objects.equals(descrizione, other.descrizione)
				&& Float.floatToIntBits(prezzo) == Float.floatToIntBits(other.prezzo)
				&& quantitaDisponibile == other.quantitaDisponibile;
	}

    @Override
    public String toString() {
        return "\nProdotto{\n" + "\tcodice prodotto=" + codiceProdotto + "\n\tdescrizione=" + descrizione + "\n\tquantità disponibile=" + 
        					quantitaDisponibile + "\n\tprezzo=" + prezzo + '}';
    }

}
