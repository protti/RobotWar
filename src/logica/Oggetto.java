package logica;
import java.io.Serializable;
import java.util.Random;

/**
 * 
 * Classe per la generazione casuale dell'oggetto sullo scenario
 * 
 *
 */

public class Oggetto implements Cloneable,Serializable{

	private Posizione locazione;
	Oggetto()
	{
		Random pos = new Random();
		locazione = new Posizione();
		int riga,colonna;
		do
		{
			riga = pos.nextInt(25);
			colonna = pos.nextInt(25);
		}
		while(!Ambiente.ostacolo(riga, colonna));
		locazione.setPosition(riga, colonna);
	}
	
	public Posizione getPos()
	{
		return this.locazione;
	}
	
	public void setPosition(Posizione nuova)
	{
		locazione.setPosition(nuova.getRiga(), nuova.getColonna());
	}
	
	public Oggetto clone()
	{
		try
		{
			Oggetto clonato = (Oggetto)super.clone();
			clonato.locazione = locazione.clone();
			return clonato;
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
	
	public String toString()
	{
		return ("" + getClass().getName() + " [Riga:" + this.locazione.getRiga()+" Colonna:" + this.locazione.getColonna() + "]");           
	}
	
	public boolean equals(Object oggetto)
	{
		if(!(oggetto != null)) return false;
		if(this.getClass() != oggetto.getClass()) return false;
		Oggetto copia = (Oggetto) oggetto;
		return (copia.locazione.getRiga() == this.locazione.getRiga() && copia.locazione.getColonna() == this.locazione.getColonna());
	}
	
	
	
	
	
}
