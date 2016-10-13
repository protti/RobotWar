package logica;

import java.util.Random;

/**
 * Classe per l'instanziazione di un Ostacolo all'interno di uno Scenario
 * 
 */




public class Ostacolo extends Oggetto implements Common{

	
	private int peso;
	private int resistenza;
	private int team;
	
	Ostacolo()
	{
		super();
		Random in = new Random();
		peso = in.nextInt(30) + 1;
		resistenza = in.nextInt(80) + 1;
		team = 2;
		Scenario.addOstacolo(this);
	}
	
	/**
	 *
	 */
	private void setPos(Posizione nuova){
		
		Ambiente.resetPos(this.getPos().getRiga(), this.getPos().getColonna());
		Ambiente.resetPos(nuova.getRiga(), nuova.getColonna());
		this.setPosition(nuova);
	}
	
	public void lostLife(int lost) throws CriticalStatusException
	{
		this.resistenza -= lost;
		if(this.resistenza <= 25)
			throw new CriticalStatusException();
	};
	
	public void sposta(int riga,int colonna)
	{
		if(Ambiente.ostacolo(riga, colonna))
		{
			setPos(new Posizione(riga,colonna));
		}
	}
	
	public int getTeam(){
		return team;
	}
	/**
	 * Restituisce il peso dell'ostacolo
	 * @return peso
	 */
	public int getPeso()
	{
		return peso;
	}
	/**
	 * Restituisce la resistenza dell'ostacolo
	 * @return resistenza
	 */
	public int getLife()
	{
		return resistenza;
	}
	
	public Ostacolo clone()
	{
		Ostacolo clonato = (Ostacolo) super.clone();
		return clonato;
	}
	
	public String toString()
	{
		return("" + super.toString()+"[Peso:"+this.peso + " Resistenza:" + this.resistenza + "]");
	}
	public boolean equals(Object oggetto)
	{
		if(super.equals(oggetto))
		{
			Ostacolo nuovo = (Ostacolo) oggetto;
			return(this.peso == nuovo.peso && this.resistenza == nuovo.resistenza && this.team == nuovo.team);
		}
		return false;
	}
	
}
