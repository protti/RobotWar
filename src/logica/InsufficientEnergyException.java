package logica;
public class InsufficientEnergyException extends Exception{
	/**
	 * Eccezione controllata in caso di carburante non sufficiente
	 * 
	 */
	public InsufficientEnergyException()
	{
		super("Carburante insufficiente");
	}
}