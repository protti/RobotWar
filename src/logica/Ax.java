package logica;
public class Ax extends Weapon implements Cloneable {

	private int trajectory;
	private int damage;

	/**
	 * Instanzia l'ascia che poi verra utilizzata dal robottino.
	 */

	public Ax() {
		trajectory = 1;
		damage = 15;
	}

	/**
	 * Numero massimo di caselle attraversabili con un unico colpo.
	 */

	public int getTrajectory() {
		return trajectory;
	}

	/**
	 * Danno massimo che il colpo può infliggere
	 */

	public int getDamage() {
		return damage;
	}
	
	public String toString()
	{
		return super.toString() + "[damage= " +damage+ ",trajectory= "+trajectory+"]";
	}
	public boolean equals(Object oggetto){
		if(super.equals(oggetto) == false) return false;
		Ax other =(Ax) oggetto;
		return (other.damage == this.damage) && (other.trajectory == this.trajectory);
	}
	public Ax clone(){
		Ax cloned = (Ax) super.clone();
		return cloned;
	}
	
	
}
