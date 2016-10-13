package logica;
import java.util.ArrayList;

public class Movimento {
	/**
	 * Genera il movimento di qualsiasi Robot (interattivo o automatizzato)
	 *  e lo rende quindi equiprobabile
	 */
	
	/*
	 * 
	 * @param partenza inteso come il Robot che dovrà muoversi
	 * (partenza.getFuel() > 0)
	 * @param arrivo inteso come la posizione che "partenza" dovrà raggiungere
	 * (arrivo.getRiga() >= 0, arrivo.getRiga() < 25,arrivo.getColonna() >= 0, arrivo.getColonna() < 25)
	 * @return walking ArrayList di Posizioni che il robot deve attraversare
	 */
	public static ArrayList<Posizione> move(Robot partenza,Posizione arrivo){
		ArrayList<Posizione> walking = new ArrayList<Posizione>();
		Posizione iniziale = new Posizione(partenza.getPos().getRiga(),partenza.getPos().getColonna());
		
		if(partenza.getPos().getRiga() == arrivo.getRiga() && partenza.getPos().getColonna() == arrivo.getColonna()) 
			return walking;
		
		Posizione start = partenza.getPos();
		//Ambiente.resetPos(start.getRiga(), start.getColonna());
		
		//Ambiente.resetPos(arrivo.getRiga(), arrivo.getColonna());
		boolean control = Ambiente.checkPosition(arrivo.getRiga(), arrivo.getColonna());
		if(control == true)
			Ambiente.resetPos(arrivo.getRiga(), arrivo.getColonna());
		
		
		while((start.getRiga() != arrivo.getRiga()) || (start.getColonna() != arrivo.getColonna())){
			
			if(start.getRiga() < arrivo.getRiga())//Nel caso in cui riga arrivo sta sopra
			{
				while(start.getRiga() != arrivo.getRiga())
				{	
					try{
						partenza.lostFuel(0.04); //Media tra la distanza minima percorribile fratto quella massima (cioè 25/125)
					}
					catch(InsufficientEnergyException e){
						break;
					}
					if(!Ambiente.checkPosition(start.getRiga()+1, start.getColonna()))
					{
						start.setPosition(start.getRiga() + 1, start.getColonna());
					}
					else if(start.getColonna() < arrivo.getColonna())
					{
						start.setPosition(start.getRiga(), start.getColonna() + 1);
					}
					else if(start.getColonna() > arrivo.getColonna())
					{
						start.setPosition(start.getRiga(), start.getColonna() - 1);
					}
					else
						start.setPosition(start.getRiga(), start.getColonna() + 1);
					
					walking.add(new Posizione(start.getRiga(),start.getColonna()));

				}
			}
			else if(start.getRiga() > arrivo.getRiga())//Nel caso in cui riga arrivo sta sotto
			{
				while(start.getRiga() != arrivo.getRiga())
				{
					try{
						partenza.lostFuel(0.04); //Media tra la distanza minima percorribile fratto quella massima (cioè 25/125)
					}
					catch(InsufficientEnergyException e){
						break;
					}
					if(!Ambiente.checkPosition(start.getRiga()- 1, start.getColonna()))
					{
						start.setPosition(start.getRiga() - 1, start.getColonna());
					}
					else if(start.getColonna() < arrivo.getColonna())
					{
						start.setPosition(start.getRiga(), start.getColonna() + 1);
					}
					else if(start.getColonna() > arrivo.getColonna())
					{
						start.setPosition(start.getRiga(), start.getColonna() - 1);
					}
					else
						start.setPosition(start.getRiga(), start.getColonna() + 1);
					walking.add(new Posizione(start.getRiga(),start.getColonna()));
				}
			}
			else if(start.getColonna() < arrivo.getColonna()) //Nel caso in cui la colonna arrivo sta avanti
			{
				while(start.getColonna() != arrivo.getColonna())
				{
					try{
						partenza.lostFuel(0.04); //Media tra la distanza minima percorribile fratto quella massima (cioè 25/125)
					}
					catch(InsufficientEnergyException e){
						break;
					}
					if(!Ambiente.checkPosition(start.getRiga(), start.getColonna() + 1))
					{
						start.setPosition(start.getRiga(), start.getColonna()+ 1);
					}
					else if(start.getRiga() < arrivo.getRiga())
					{
						start.setPosition(start.getRiga()+ 1, start.getColonna());
					}
					else if(start.getRiga() > arrivo.getRiga())
					{
						start.setPosition(start.getRiga()- 1, start.getColonna());
					}
					else
						start.setPosition(start.getRiga() + 1, start.getColonna());
					walking.add(new Posizione(start.getRiga(),start.getColonna()));
				}
			}
			else if(start.getColonna() > arrivo.getColonna()) //Nel caso in cui la colonna arrivo sta dietro
			{
				while(start.getColonna() != arrivo.getColonna())
				{
					try{
						partenza.lostFuel(0.04); //Media tra la distanza minima percorribile fratto quella massima (cioè 25/125)
					}
					catch(InsufficientEnergyException e){
						break;
					}
					if(!Ambiente.checkPosition(start.getRiga(), start.getColonna() - 1))
					{
						start.setPosition(start.getRiga(), start.getColonna()- 1);
					}
					else if(start.getRiga() < arrivo.getRiga())
					{
						start.setPosition(start.getRiga()+ 1, start.getColonna());
					}
					else if(start.getRiga() > arrivo.getRiga())
					{
						start.setPosition(start.getRiga()- 1, start.getColonna());
					}
					else
						start.setPosition(start.getRiga() + 1, start.getColonna());
					
					walking.add(new Posizione(start.getRiga(),start.getColonna()));
				}
			}
			if(partenza.getFuel() <= 0) break; 
		}
		
		if(control == true)
		{
			if(walking.size() != 0)
				walking.remove(walking.size() - 1);
			
			if(walking.size() != 0)
				partenza.setPos(walking.get(walking.size() - 1).getRiga(),walking.get(walking.size() - 1).getColonna());
			else 
				partenza.setPos(iniziale.getRiga(),iniziale.getColonna());
			Ambiente.setPos(arrivo.getRiga(), arrivo.getColonna());
		}
		/*walking.remove(arrivo);
		Ambiente.setPos(arrivo.getRiga(), arrivo.getColonna());*/
		
		return walking;
	}
}
