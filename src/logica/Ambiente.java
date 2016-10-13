package logica;

import java.io.Serializable;

public class Ambiente implements Serializable{

	private static boolean[][] campo = new boolean[25][25];

	/* 0 il robot si può muovere, 1 no*/

	/**
	 * Metodo per controllare se l'ostacolo puo essere inserito o meno all'interno dello scenario
	 * 
	 * @param riga
	 * @param colonna
	 * @return true per la posizione corretta, false altrimenti
	 * */
	
	
	public static boolean ostacolo(int riga,int colonna)
	{
		if(!checkPosition(riga,colonna) && !checkPosition(riga,colonna + 1) && !checkPosition(riga,colonna - 1) && !checkPosition(riga + 1,colonna) && !checkPosition(riga - 1,colonna))
			return true;
		else
			return false;
	}
	
	/***
	 * Setta a true una posizione all'interno dello scenario
	 * 
	 * @param riga
	 * @param colonna
	 */
	public static void setPos(int riga,int colonna)
	{

		campo[riga][colonna] = true;

	}

	/***
	 * Resetta la posizione all'interno dello scenario
	 * @param riga
	 * @param colonna
	 */
	
	public static void resetPos(int riga,int colonna)
	{
		campo[riga][colonna] = false;
	}

	/**
	 * Controlla se la posizione del robot può essere instanziata o meno
	 * 
	 * 
	 * @param riga
	 * @param colonna
	 * @return true se la posizione è corretta, false altrimenti
	 */
	
	public static boolean checkPosition(int riga,int colonna)
	{
		try
		{
			return campo[riga][colonna];
		}catch(ArrayIndexOutOfBoundsException e)
		{
			return true;
		}
	}


}
