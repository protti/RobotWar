package logica;

import java.util.ArrayList;
import java.util.Random;

/*
 * 
 * Classe per la generazione dell'inteligenza artificiale del robottino non
 * controllato. Si basa su tre livelli di priorità in base al tipo di robot
 * scelto.
 * 
 * 
 *
 */

public class Priorita {

	public static ArrayList<Posizione> intelligence(Robot nostro) throws InsufficientWeaponEnergy {
		RobotAttaccante valore = null;
		RobotLavoratore temp = null;
		ArrayList<Posizione> walking = null;
		if (nostro instanceof RobotAttaccante)
			valore = (RobotAttaccante) nostro;
		else
			temp = (RobotLavoratore) nostro;
		if (nostro.getLife() < 25) {
			BancoRifornimento banco = UtilityPriorita.findNearBank(nostro.getPos());
			walking = Movimento.move(nostro, banco.getPos());
			if (nostro.getFuel() >= 0) {
				nostro.takeLife(100);
			}

		}

		else if (nostro instanceof RobotAttaccante && valore.getWeapon().getState() != 0) {
			Robot bot = UtilityPriorita.findNearRobot(nostro.getPos());
			walking = Movimento.move(nostro, bot.getPos());
			if (nostro.getFuel() >= 0 && walking.size() < valore.getWeapon().getTrajectory()) {
				valore.getAction(bot);
			}
		} else if (nostro instanceof RobotLavoratore && (temp.getTaskArmy() != null || temp.getTaskEnergy() != null)) {
			Robot rescueLife = UtilityPriorita.findPriorityLife();
			RobotAttaccante rescueUsura = UtilityPriorita.findPriorityUsura();
			if (rescueLife != null && temp.getTaskArmy() != null) {

				walking = Movimento.move(temp, rescueLife.getPos());
				if (temp.getFuel() > 0)
					temp.getAction(rescueLife);
			}
			if (rescueUsura != null && temp.getTaskEnergy() != null) {
				walking = Movimento.move(temp, rescueUsura.getPos());
				if (temp.getFuel() > 0)
					temp.getAction(rescueUsura);
			}
		} else if (nostro instanceof RobotLavoratore) {
			BancoRifornimento banco = UtilityPriorita.findNearBank(temp.getPos());
			walking = Movimento.move(temp, banco.getPos());
			Random caso = new Random();
			if (caso.nextInt(2) == 0)
				temp.setTaskArmy(new Ax());
			else
				temp.setTaskArmy(new Gun());

			temp.setTaskEnergy(new Rifornimento());
		} else if (nostro instanceof RobotAttaccante) {
			BancoRifornimento banco = UtilityPriorita.findNearBank(nostro.getPos());
			walking = Movimento.move(nostro, banco.getPos());
			Random caso = new Random();
			if (caso.nextInt(2) == 0)
				valore.changeWeapon(new Ax());
			else
				valore.changeWeapon(new Gun());

		}
		return walking;

	}

}
