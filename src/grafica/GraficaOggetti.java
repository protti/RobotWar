package grafica;
import logica.*;
import java.util.Random;

import javax.swing.*;

public class GraficaOggetti {
	
	private Robot round;
	
	public GraficaOggetti(Robot round){
		this.round = round;
	}
	private void change(Robot round)
	{
		this.round = round;
	}
	public Robot getRobot()
	{
		return this.round;
	}
public boolean changeRobot(int squadra){
		
		Random valore = new Random();
		
		if(squadra == 0)
		{
			if(Scenario.getTeamUno().size() != 0)
			{
				this.change(Scenario.getTeamUno().get(valore.nextInt(Scenario.getTeamUno().size())));
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if(Scenario.getTeamZero().size() != 0)
			{
				this.change(Scenario.getTeamZero().get(valore.nextInt(Scenario.getTeamZero().size())));
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	public void getAction(BancoRifornimento banco){
		BancoRifornimentoGrafico bancog = new BancoRifornimentoGrafico(banco,round);
		bancog.openBR();
	}
	public void getAction(RobotAttaccante action){
		
		RobotLavoratoreGrafico lavoratore;
		
		if(round instanceof RobotLavoratore)
		{
			lavoratore = new RobotLavoratoreGrafico(action,round);
			lavoratore.openRL();
		}
	}
	
	public void getAction(Robot action) throws InsufficientWeaponEnergy
	{
		RobotAttaccante attaccante;
		if(round instanceof RobotAttaccante)
		{
			attaccante = (RobotAttaccante) round;
			attaccante.getAction(action);
		}
	}
	
	
	
	
}
