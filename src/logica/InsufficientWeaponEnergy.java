package logica;

public class InsufficientWeaponEnergy extends Exception{
/**
 * Eccezione controllata in caso di impossibilit� nello sparo
 */
	
	InsufficientWeaponEnergy()
	{
		super("Impossibile Attaccare");
	}
	
	
}
