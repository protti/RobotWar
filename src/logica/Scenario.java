package logica;

import java.util.ArrayList;
/**
 * Classe che permette la visualizzazione e l'aggiunta o meno dei robottini sullo Scenario
 * 
 * 
 * @author jeson
 *
 */

public class Scenario {
	
	private static ArrayList<Robot> teamZero = new ArrayList<Robot>();
	private static ArrayList<Robot> teamUno = new ArrayList<Robot>();
	private static ArrayList<BancoRifornimento> banchi = new ArrayList<BancoRifornimento>();
	private static ArrayList<Ostacolo> ostacoli = new ArrayList<Ostacolo>();
	
	/*
	 * Aggiunge all'arraylist dello scenario un nuovo robottino della squadra dell'utente
	 * 
	 * @param nuovoRobottino
	 */
	static void addRobotUno(Robot nuovo)
	{
		teamZero.add(nuovo);
		Ambiente.setPos(nuovo.getPos().getRiga(), nuovo.getPos().getColonna());
	}
	/**
	 * Aggiunge all'arraylist dello scenario un nuovo robottino della squadra del PC
	 * 
	 * @param nuovoRobottino
	 */
	static void addRobotDue(Robot nuovo)
	{
		teamUno.add(nuovo);
		Ambiente.setPos(nuovo.getPos().getRiga(), nuovo.getPos().getColonna());
	}
	/**
	 * Aggiunge un nuovo banco di rifornimento allo scenario
	 * @param nuovoBancoRifornimento
	 */
	static void addBanco(BancoRifornimento nuovo)
	{
		banchi.add(nuovo);
		Ambiente.setPos(nuovo.getPos().getRiga(), nuovo.getPos().getColonna());
	}
	/**
	 * Aggiunge un nuovo ostacolo allo sceneario
	 * @param nuovoOstacolo
	 */
	static void addOstacolo(Ostacolo nuovo)
	{
		ostacoli.add(nuovo);
		Ambiente.setPos(nuovo.getPos().getRiga(), nuovo.getPos().getColonna());
	}
	
	public static ArrayList<Robot> getTeamZero()
	{
		return teamZero;
	}
	public static ArrayList<Robot> getTeamUno()
	{
		return teamUno;
	}
	public static ArrayList<BancoRifornimento> getBanchi()
	{
		return banchi;
	}
	public static ArrayList<Ostacolo> getOstacoli()
	{
		return ostacoli;
	}
	static void deleteFromScenario(Robot lost){
		
		ArrayList<Robot> team;
		if(lost.getTeam() == 1)
			team = teamUno;
		else
			team = teamZero;
		
			for(int i = 0; i < team.size(); i++)
			{
				if(team.get(i) == lost)
				{
					team.remove(i);
					break;
				}
			}		
	}
}
