package logica;

public interface Common extends Cloneable{

	/**
	 * Restituisce la posizione dell'oggetto richiedente
	 * @return Posizione
	 */
	public Posizione getPos();
	
	/**
	 * Toglie la vita a coloro che possono essere attaccati, e decrementa del parametro passato in input
	 * , in caso di vita minore di 25 viene lanciata un eccezione di tipo CriticalStatusException
	 * 
	 * @param lost
	 * @throws CriticalStatusException
	 */
	
	public void lostLife(int lost) throws CriticalStatusException;
	/**
	 * Restituisce il team del robot, in caso di ostacolo ritorna team 2.
	 * 
	 * @return valore del team in cui opera
	 */
	
	public int getLife();
	
	public int getTeam();
}
