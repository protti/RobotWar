package logica;
/**
 *  
 * Classe atta alla costruzione dell'arma
 */
public class Gun extends Weapon{	
	private int damage;
	private int trajectory;

	public Gun(){
		super();
		damage = 10;
		trajectory = 5;
	}
	/**
	 * Restituisce il danno dell'arma 
	 */
	public int getDamage(){
		return damage;
	}
	/**
	 * Restituisce il numero di passo che puo effettuare il colpo
	 */
	public int getTrajectory(){
		return trajectory;
	}
	
	public String toString()
	{
		return super.toString() + "[damage: " + damage + ",trajectory: " + trajectory +"]";
	}
	
	public boolean equals(Object obj)
	{
		if(super.equals(obj) == false) return false;
		Gun anObj = (Gun) obj;
		return anObj.damage == damage && anObj.trajectory == trajectory;
	}
	
	public Gun clone()
	{
		Gun cloned = (Gun) super.clone();
		return cloned;
	}
	
	
	
}
