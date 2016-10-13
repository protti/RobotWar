package logica;
/**
 * 
 * 
 * Classe per l'instanziazione di una posizione nello scenario
 * essa viene usata da qualsiasi oggetto che vi è sullo scenario
 * 
 */

import java.io.Serializable;

public class Posizione implements Cloneable,Serializable {

	private int riga;
	private int colonna;

	Posizione()/*Istanziamo solo il campo statico*/
	{
		riga = -1;
		colonna = -1;
	};
	
	public Posizione(int riga,int colonna){
		this.riga = riga;
		this.colonna = colonna;
		if(!Ambiente.checkPosition(riga, colonna))
			Ambiente.setPos(riga, colonna);
	}
	/**
	 * Restituisce la riga dell'oggetto
	 * @return riga
	 */
	public int getRiga()
	{
		return riga;
	}
	/**
	 * Restituisce la colonna dell'oggetto
	 * @return colonna
	 */
	public int getColonna()
	{
		return colonna;
	}
	/**
	 * 
	 * Inserendo riga e colonna verrà settata, se è possibile, la posizione
	 * sullo scenario
	 * 
	 * @param riga
	 * @param colonna
	 * 
	 */

	boolean setPosition(int riga,int colonna)/*Prima posizione libera*/
	{
		if(this.riga == -1 && this.colonna == -1) //Impostiamo prima -1 in quanto cosi riusciamo a capire quando
												  //Settare o resettare la posizione degli oggetti
		{
			if(!Ambiente.checkPosition(riga,colonna))
			{
				Ambiente.setPos(riga, colonna);
				this.riga = riga;
				this.colonna = colonna;
				return true;
			}
			else
				return false;
		}
		else
		{
			if(!Ambiente.checkPosition(riga,colonna))
			{
				Ambiente.resetPos(this.getRiga(), this.getColonna());
				Ambiente.setPos(riga, colonna);
				this.riga = riga;
				this.colonna = colonna;
				return true;
			}
			else
				return false;
		}
	}
	
	public Posizione clone()
	{
		try
		{
			Posizione nuova = (Posizione) super.clone();
			return nuova;
		}catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
	

	public String toString()
	{
		return getClass().getName() + "[riga: " + riga + ",colonna: " + colonna + "]";
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Posizione)) return false;
		Posizione anObj = (Posizione) obj;
		return anObj.riga == riga && anObj.colonna == colonna;
	}




}
