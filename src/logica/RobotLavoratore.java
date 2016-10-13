package logica;

/**
 * 
 * 
 * Classe per l'instanziazione del Robottino lavoratore
 *
 */

public class RobotLavoratore extends Robot{

	private Rifornimento taskEnergy;
	private Weapon taskArmy;
	
	public RobotLavoratore(int team)
	{
		super(team);
		taskArmy = null;
		taskEnergy = null;
	}

	/**
	 * Metodo generico in quanto scegliendo il tipo si puo eseguire azioni diverse
	 * 
	 * @param sfidante
	 * @throws InsufficientWeaponEnergy
	 */
	public void getAction(Robot richiesta)
	{
		if(richiesta.getTeam() != this.getTeam()) return;
		if(taskArmy != null && (richiesta instanceof RobotAttaccante))
		{
			RobotAttaccante temp = (RobotAttaccante)richiesta;
			temp.changeWeapon(taskArmy);
			taskArmy = null;
		}
		if(taskEnergy != null)
		{
			richiesta.takeLife(taskEnergy.getRifornimento());
			taskEnergy = null;
		}
	}
	
	public void setTaskArmy(Weapon arma)
	{
		taskArmy = arma;
	}
	/*Per il robot attaccante il max rifornimento che puo dare è 40, per se stesso invece è 100*/
	public void setTaskEnergy(Rifornimento vita)
	{
		taskEnergy = new Rifornimento(vita.getRifornimento() - 60);
	}
	
	public Rifornimento getTaskEnergy(){
		return taskEnergy;
	}
	public Weapon getTaskArmy(){
		return taskArmy;
	}
	public RobotLavoratore clone()
	{
		RobotLavoratore clonato = (RobotLavoratore)super.clone();
		if(taskArmy != null)
			clonato.taskArmy = taskArmy.clone();
		if(taskEnergy != null)
			clonato.taskEnergy = taskEnergy.clone();
		return clonato;
	}
	
	public String toString()
	{
		if(taskArmy != null && taskEnergy != null)
			return(super.toString() + "[ Tasca Rifornimento: true" + " Tasca Arma:" + taskArmy.toString()+ "]");
		else if(taskArmy == null && taskEnergy != null)
			return(super.toString() + "[ Tasca Rifornimento: false" + " Tasca Arma:" + taskArmy.toString()+ "]");
		else if(taskArmy != null && taskEnergy == null)
			return(super.toString() + "[ Tasca Rifornimento: true" + " Tasca Arma: false]");
		else
			return(super.toString() + "[ Tasca Rifornimento: false" + " Tasca Arma: false]");

	}
	public boolean equals(Object confront)
	{
		if(super.equals(confront))
		{
			RobotLavoratore confronto = (RobotLavoratore) confront;
			return(confronto.taskArmy == this.taskArmy && confronto.taskEnergy == this.taskEnergy);
		}
		return false;
	}
}
