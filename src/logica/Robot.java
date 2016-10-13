package logica;

import java.io.Serializable;
import java.util.Random;


/**
 * 
 * Concetto astratto di robottino, da esso ne derivano i due robottini, Lavoratore ed Attaccante.
 * 
 * 
 * 
 */

public abstract class Robot implements Common,Serializable {

	private double carburante;
	private Posizione position;
	private int forza;
	private int vita;
	private int team;
	
	
	
	
	public Robot(int team)
	{
		Random pos = new Random();
		this.position = new Posizione();
		this.carburante = 100;
		this.vita = 100;
		while(!position.setPosition(pos.nextInt(25),pos.nextInt(25)));
		forza = pos.nextInt(80) + 1;
		this.team = team;
		if(team == 0) Scenario.addRobotUno(this);
		if(team == 1) Scenario.addRobotDue(this);
	}
	
	/**
	 * Metodo atto a far perdere la vita al robot, esso puo lanciare un eccezione di tipo controllato chiamata 
	 * CrtiticalStatusException in caso di vita inferiore a 0.
	 * 
	 */
	public void lostLife(int lost) throws CriticalStatusException
	{
		this.vita -= lost;
		if(this.vita <= 25)
			throw new CriticalStatusException();
	};
	/**
	 * Esso da la vita al robottino, passandogli la quantità necessaria
	 * @param take
	 */
	public void takeLife(int take)
	{
		if(take + this.vita > 100)
			this.vita = 100;
		else
			this.vita += take;
	};
	/**
	 * Metodo per la perdita del carburante
	 * @param lost
	 * @throws InsufficientEnergyException
	 */
	public void lostFuel(double lost) throws InsufficientEnergyException
	{
		this.carburante -= lost;
		if(this.carburante <= 0) throw new InsufficientEnergyException();
	};
	/**
	 * Metodo atto al rifornimento del robottino
	 * @param take
	 */
	public void takeFuel(double take)
	{
		if(take + this.carburante > 100)
			this.carburante = 100;
		else
			this.carburante += take;
	};
	/**
	 * Andiamo a settare la posizione del robottino
	 * 
	 * @param riga
	 * @param colonna
	 */
	public void setPos(int riga,int colonna)
	{
		
		if(!Ambiente.checkPosition(riga, colonna))
		{
			Ambiente.resetPos(position.getRiga(),position.getColonna());
			position.setPosition(riga,colonna);
		}
		
	}
	

	
	public Posizione getPos()
	{
		return this.position;
	}
	public int getTeam(){
		return team;
	}
	
	
	public void sposta(Ostacolo ostacolo)
	{
		Posizione appoggio = ostacolo.getPos();
		
		if(this.forza >= ostacolo.getPeso())
		{
			int row = ostacolo.getPos().getRiga();
			int col = ostacolo.getPos().getColonna();
			if(position.getRiga() == row)
			{
				row = position.getRiga();
				if(position.getColonna() == col - 1)
				{
					col = col + 1;
				}
				if(position.getColonna() == col + 1)
				{
					col = col - 1;
				}
			}
			if(position.getColonna() == col)
			{
				col = position.getColonna();
				if(position.getRiga() == row - 1)
				{
					row = row + 1;
				}
				if(position.getRiga() == row + 1)
				{
					row = row - 1;
				}
			}
			ostacolo.sposta(row,col);
		}
	}
	
	public int getLife()
	{
		return this.vita;
	}
	double getFuel()
	{
		return this.carburante;
	}
	
	public Robot clone()
	{
		try
		{
			Robot clonato = (Robot)super.clone();
			clonato.position = position.clone();
			return clonato;
		}catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
	
	public String toString()
	{
		return (getClass().getName() + "[Riga:" + this.position.getRiga() + " Colonna:" + this.position.getColonna() + " Carburante: " + this.carburante  + " Forza:" + this.forza + " Vita:" + this.vita +" Team:" + this.vita + "]");
	}
	
	public boolean equals(Object confronto)
	{
		if(confronto == null) return true;
		if(this.getClass() != confronto.getClass()) return false;
		Robot confront = (Robot) confronto;
		return(confront.carburante == this.carburante && confront.forza == this.forza && confront.position.getRiga() == this.position.getRiga() && confront.position.getColonna() == this.position.getColonna() && confront.team == this.team && this.vita == confront.vita);
	}
	
	
	
	
	/**
	 * Metodo astratto in dipende dal robottino l'azione da eseguire
	 */
	
	public void getAction(){};
	
	
}
