package logica;

public class InsufficientWeaponEnergy extends Exception{
/**
 * Eccezione controllata in caso di impossibilità nello sparo
 */
	
	InsufficientWeaponEnergy()
	{
		super("Impossibile Attaccare");
	}
	
	
}
