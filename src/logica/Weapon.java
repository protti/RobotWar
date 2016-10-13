package logica;
import java.io.Serializable;
public abstract class Weapon implements Cloneable,Serializable{
	/*Delle armi conosciamo soltanto lo stato iniziale
	 * la traiettoria e il danno che può fare non è conosciuto 
	 */
	public Weapon(){
		state = 10;
	}
	public int getState(){
		return state;
	}
	//Ad ogni attacco l'arma si usura... Quando lo stato arriva a 0 l'arma viene distrutta
	public void usura(){   
		state = state - 1;
	}
	
	public String toString(){
		return getClass().getName() + "[state: "+state+"]";
	}
	public boolean equals(Object oggetto){
		if(oggetto == null) return false;
		if(!(oggetto instanceof Weapon)) return false;
		Weapon other = (Weapon) oggetto;
		return other.state == this.state;
	}
	
	public Weapon clone()
	{
		try
		{
			Weapon clonata = (Weapon) super.clone();
			return clonata;
		}catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
	public abstract int getTrajectory();
	public abstract int getDamage();
	private int state;
}
