package logica;

import java.io.Serializable;
/**
 * Classe per il rifornimento dei robottini sullo scenario
 * 
 *
 */

public class Rifornimento implements Cloneable,Serializable{
	
	private int Energia;
	
	
	Rifornimento()
	{
		Energia = 100;
	}
	Rifornimento(int valore)
	{
		Energia = valore;
	}
	
	/**
	 * Resistuisce la quantità di benzina da fornire all'robottino
	 * @return vita
	 */
	
	public int getRifornimento()
	{
		return this.Energia;
	}
	
	public Rifornimento clone()
	{
		try
		{
			Rifornimento clonato = (Rifornimento) super.clone();
			return clonato;
		}catch(CloneNotSupportedException e)
		{
			return null;
		}
		
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Rifornimento)) return false;
		Rifornimento anObj = (Rifornimento) obj;
		return anObj.Energia == Energia;
	}
	
	public String toString()
	{
		return getClass().getName() + "Energia: "+ Energia + "]";
	}
	

}
