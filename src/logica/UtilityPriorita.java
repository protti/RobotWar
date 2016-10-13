package logica;

import java.util.ArrayList;

public class UtilityPriorita {

	/*
	 * Data una posizione, restituisce il banco di rifornimento piu vicino alla
	 * posizione passatogli
	 * 
	 * @param Posizione
	 * @return BancoRifornimento piu vicino
	 */
	static BancoRifornimento findNearBank(Posizione pos) {
		ArrayList<BancoRifornimento> banchi = Scenario.getBanchi();
		BancoRifornimento min = banchi.get(0);
		int minimo = Math.abs(min.getPos().getRiga() - pos.getRiga())
				+ Math.abs(min.getPos().getColonna() - pos.getColonna());
		for (int i = 1; i < banchi.size(); i++) {
			int somma = Math.abs(banchi.get(i).getPos().getRiga() - pos.getRiga())
					+ Math.abs(banchi.get(i).getPos().getColonna() - pos.getColonna());
			if (somma < minimo) {
				minimo = somma;
				min = banchi.get(i);
			}
		}
		return min;
	}
	
	/**
	 *	Trova il robottino piu vicino per poterlo attaccare dal robottino interattivo (Applicabile solo
	 *	dal robottuno attaccante)
	 * @param Robot Pos
	 * @return Robot piu vicino
	 */
	static Robot findNearRobot(Posizione pos) {
		ArrayList<Robot> teamZero = Scenario.getTeamZero();
		
			Robot min = teamZero.get(0);
		int minimo = Math.abs(min.getPos().getRiga() - pos.getRiga())
				+ Math.abs(min.getPos().getColonna() - pos.getColonna());
		for (int i = 1; i < teamZero.size(); i++) {
			int somma = Math.abs(teamZero.get(i).getPos().getRiga() - pos.getRiga())
					+ Math.abs(teamZero.get(i).getPos().getColonna() - pos.getColonna());
			if (somma < minimo) {
				minimo = somma;
				min = teamZero.get(i);
			}
		}
		return min;
	}
	/**
	 *	Trova il robottino piu vicino per poterlo curare dal robottino interattivo (Applicabile solo
	 *	dal robottino lavoratore)
	 * @param Robot Pos
	 * @return Robot piu vicino
	 */
	static Robot findPriorityLife() {
		ArrayList<Robot> robots = Scenario.getTeamUno(); // Si prendono dallo
															// scenario i robot
															// a rischio
		Robot min = robots.get(0); // impostiamo il Robot a rischio come il
									// primo Robot dello scenario
		for (int i = 1; i < robots.size(); i++) {
			if (min.getLife() > robots.get(i).getLife() && robots.get(i).getLife() <= 40) // Il
																							// Robot
																							// a
																							// rischio
																							// dovrà
																							// avere
																							// vita
																							// minore
																							// del
																							// Robot
																							// min
																							// e
																							// vita
																							// minore
																							// a
																							// 40
			{
				min = robots.get(i);
			}
		}
		if (min.getLife() <= 40)
			return min; // Il robot min all'inizio viene instanziato a
						// robots.get(0) se questo ha vita minima ed è minore o
						// uguale a 40 allora verrà restituito(come succede per
						// gli altri Robot)
		else
			return null; // Altrimenti nessun Robot dovrà essere restituito
	}

	// Viene usato per trovare il Robot con l'arma più usurata (Quindi il Robot
	// restituito sarà Attaccante)
	/**
	 *	Trova il robottino attaccante piu vicino per poterlo riparare dal robottino interattivo (Applicabile solo
	 *	dal robottino lavoratore)
	 * @param Robot Pos
	 * @return Robot piu vicino
	 */
	
	static RobotAttaccante findPriorityUsura() {
		ArrayList<Robot> robots = Scenario.getTeamUno();
		RobotAttaccante min = null; // Il caso più facile è quando nessun Robot
									// ha l'arma usurata
		for (int i = 0; i < robots.size(); i++) {
			if (robots.get(i) instanceof RobotAttaccante) // Dato che solo il
															// RobotAttaccante
															// ha un' arma
															// allora questo
															// deve essere stato
															// instanziato come
															// RobotAttaccante
			{
				RobotAttaccante temp = (RobotAttaccante) robots.get(i); // Siamo
																		// sicuri
																		// a
																		// questo
																		// punto
																		// che
																		// il
																		// Robot
																		// è
																		// attaccante,
																		// ma
																		// questo
																		// di
																		// defualt
																		// un
																		// Robot
																		// generico,
																		// quindi
																		// usiamo
																		// un
																		// RobotAttaccante
																		// di
																		// appoggio
																		// e
																		// facciamo
																		// un
																		// cast
																		// a
																		// robots.get(i)
				if (min == null || min.getWeapon().getState() > temp.getWeapon().getState())
					min = temp;
			}
		}
		if (min != null) {
			if (min.getWeapon().getState() != 10)
				return min;
			else
				return null;
		} else
			return null;
	}

}

// usa
// Priorita -----> UtilityPriorità