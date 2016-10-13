package logica;
import java.util.ArrayList;
import java.lang.Math;

/**
 * 
 * Classe per l'instanziazione del robot attaccante.
 *
 */
public class RobotAttaccante extends Robot{


	private Weapon arma;

	public RobotAttaccante(int team)
	{
		super(team);
		//arma = new Ax();
		arma = new Gun();
	}
	
	/**
	 * Metodo generico in quanto scegliendo il tipo si puo eseguire azioni diverse
	 * 
	 * @param sfidante
	 * @throws InsufficientWeaponEnergy
	 */
	public void getAction(Common sfidante) throws InsufficientWeaponEnergy
	{
		if(sfidante.getTeam() == this.getTeam()) return;
		if(sfidante.getPos().getRiga() == this.getPos().getRiga() || sfidante.getPos().getColonna() == this.getPos().getColonna())
		{
			int spazio = 0;
			if( sfidante.getPos().getRiga() == this.getPos().getRiga())
				spazio = Math.abs(sfidante.getPos().getColonna() - this.getPos().getColonna());
			else
				spazio = Math.abs(sfidante.getPos().getRiga() - this.getPos().getRiga());


			if(spazio <= arma.getTrajectory() && arma.getState() > 0 && this.getFuel() > 0.02)
			{
				try
				{
					sfidante.lostLife(arma.getDamage());
				}
				catch(CriticalStatusException e)
				{}
				finally
				{
					arma.usura();

					if(sfidante.getLife() <= 0)
					{
						Ambiente.resetPos(sfidante.getPos().getRiga(),sfidante.getPos().getColonna()) ;
						Scenario.deleteFromScenario((Robot)sfidante);
					}
				}
			}
			else 
				throw new InsufficientWeaponEnergy();
		}
	}
/**
 * Metodo per il cambio dell'arma
 * @param arma
 */
	public void changeWeapon(Weapon arma)
	{	
		if(arma != null){
			this.arma = arma;
		}
	}

	Weapon getWeapon(){
		return arma;
	}

	public RobotAttaccante clone()
	{
		RobotAttaccante clonato = (RobotAttaccante)super.clone();
		clonato.arma = arma.clone();
		return clonato;
	}

	public String toString()
	{
		return(super.toString() + "[Arma:" + this.arma.toString() + "]");
	}
	public boolean equals(Object confront)
	{
		if(super.equals(confront))
		{
			RobotAttaccante confronto = (RobotAttaccante)confront;
			return(confronto.arma == this.arma);
		}
		return false;
	}
}
