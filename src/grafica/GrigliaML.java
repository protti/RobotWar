package grafica;

import logica.*;
import logica.Robot;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class GrigliaML extends JPanel {
	private JFrame frame;

	private class Cella extends JPanel {
		private JLabel label;
		private ImageIcon img;

		public Cella(ImageIcon imgr) {
			img = imgr;
			label = new JLabel("", img, JLabel.HEIGHT);
			this.add(label);
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		}

		public void switchColoreRobot(ImageIcon img) {
			imgr = img;
			label.setIcon(imgr);
		}

		public void switchColorePrato() {

			img = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/prato.jpg");
			label.setIcon(img);
		}
	}

	Posizioni nuova;
	Posizioni vecchia;
	Robot richiedente;

	private class Clicker extends MouseAdapter {
		private int x;
		private int y;
		JFrame frame = new JFrame();

		public void mouseClicked(MouseEvent me) {

			int flag = 0;
			nuova = new Posizioni(x, y);
			ArrayList<BancoRifornimento> banchi = Scenario.getBanchi();
			for (BancoRifornimento c : banchi) {
				if (c.getPos().getRiga() == x && c.getPos().getColonna() == y) {
					graffa.getAction(c);
					flag = 1;
					break;
				}
			}
			if (flag != 1) {
				ArrayList<Robot> attaccanti0 = Scenario.getTeamZero();
				for (Robot c : attaccanti0) {
					if (c.getPos().getRiga() == x && c.getPos().getColonna() == y && c instanceof RobotAttaccante) {
						graffa.getAction((RobotAttaccante) c);
						flag = 1;
						break;
					}
				}
			}
			if (flag != 1) {
				ArrayList<Robot> robot;
				if (graffa.getRobot().getTeam() == 1)
					robot = Scenario.getTeamZero();
				else
					robot = Scenario.getTeamUno();

				for (Robot c : robot) {
					if (c.getPos().getRiga() == x && c.getPos().getColonna() == y) {
						try {
							graffa.getAction(c);
						} catch (InsufficientWeaponEnergy e) {
								e.getMessage();
						}
						flag = 1;
						break;
					}
				}
			}

			ImageIcon img;
			if (graffa.getRobot() instanceof RobotAttaccante)
				img = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/robot.jpg");
			else
				img = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/robotl.jpg");

			int m = coloraDecolora((Cella) me.getSource(), img);
			boolean b = graffa.changeRobot(graffa.getRobot().getTeam());

			if (b == false) {
				JLabel label = new JLabel("Hai Vinto!");
				removeAll();
				add(label);
			}
			if (graffa.getRobot() instanceof RobotAttaccante)
				img = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/robotr.jpg");
			else
				img = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/robotrl.jpg");

			startPrio(m, img);

			boolean c = graffa.changeRobot(graffa.getRobot().getTeam());
			if (c == false) {
				JPanel nuovo = new JPanel();
				JLabel label = new JLabel("Hai Perso!");
				removeAll();
				nuovo.add(label);
				add(nuovo);
				frame.add(nuovo);
				frame.setSize(200, 200);
				frame.setVisible(false);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			celle[graffa.getRobot().getPos().getRiga()][graffa.getRobot().getPos().getColonna()]
					.setBackground(Color.blue);
			this.frame = new JFrame();
			this.frame.setSize(100, 100);
			PropretyRobot.drawLife(Scenario.getTeamZero(), Scenario.getTeamUno(), this.frame);
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.frame.setVisible(true);

		}

		public void setCoor(int xi, int yi) {
			x = xi;
			y = yi;
		}

	}

	// Metodo Privato Interno
	private void startPrio(int m, ImageIcon img) {
		ImageIcon imgr = img;
		ArrayList<Posizione> walking = null;
		Posizioni vecchia = new Posizioni(graffa.getRobot().getPos().getRiga(),
				graffa.getRobot().getPos().getColonna());
		try {
			walking = Priorita.intelligence(graffa.getRobot());
		} catch (InsufficientWeaponEnergy e) {
			e.getMessage();
			} finally {
			class TListener implements ActionListener {
				public TListener(ArrayList<Posizione> walking, ImageIcon img) {
					this.walking = walking;
					b = false;
				}

				public void actionPerformed(ActionEvent e) {
					if (b == false && walking != null) {
						if (walking.size() > 0) {
							celle[vecchia.getX()][vecchia.getY()].switchColorePrato();
							changeImmagine(walking, imgr);

							b = true;
						}
					}
				}

				private ArrayList<Posizione> walking;
				private boolean b;
			}
			ActionListener l = new TListener(walking, imgr);
			Timer time2 = new Timer(500 * m + 1000, l);
			time2.start();
		}
	}

	private Clicker clicker;
	private Cella[][] celle;

	ArrayList<BancoRifornimento> banco = Scenario.getBanchi();
	ArrayList<Ostacolo> osta = Scenario.getOstacoli();
	ArrayList<Robot> robo0 = Scenario.getTeamZero();
	ArrayList<Robot> robo1 = Scenario.getTeamUno();
	ImageIcon imgr;
	GraficaOggetti graffa;
	PropretyRobot life;

	public GrigliaML(int rows, int cols, Robot start) {
		setLayout(new GridLayout(rows, cols));
		celle = new Cella[rows][cols];
		clicker = new Clicker();
		graffa = new GraficaOggetti(start);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				if (appart(i, j) == 1)
					imgr = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/banco.jpg");
				else if (appart(i, j) == 2)
					imgr = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/robot.jpg");
				else if (appart(i, j) == 3)
					imgr = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/robotr.jpg");
				else if (appart(i, j) == 4)
					imgr = new ImageIcon(
							"C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/ostacolo.jpg");
				else if (appart(i, j) == 6)
					imgr = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/robotl.jpg");
				else if (appart(i, j) == 7)
					imgr = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/robotrl.jpg");

				else
					imgr = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/prato.jpg");
				clicker = new Clicker();
				clicker.setCoor(i, j);
				Cella c = new Cella(imgr);
				c.addMouseListener(clicker);
				add(c);
				celle[i][j] = c;

			}
		}
		frame = new JFrame();
	}

	// Metodo per controllare l'appartenenza
	private int appart(int x, int y) {
		for (BancoRifornimento c : banco)
			if (c.getPos().getRiga() == x && c.getPos().getColonna() == y)
				return 1;
		for (Robot c : robo0) {
			if (c.getPos().getRiga() == x && c.getPos().getColonna() == y && (c instanceof RobotLavoratore))
				return 6;
			if (c.getPos().getRiga() == x && c.getPos().getColonna() == y && (c instanceof RobotAttaccante))
				return 2;
		}
		for (Robot c : robo1) {
			if (c.getPos().getRiga() == x && c.getPos().getColonna() == y && (c instanceof RobotLavoratore))
				return 7;
			if (c.getPos().getRiga() == x && c.getPos().getColonna() == y && (c instanceof RobotAttaccante))
				return 3;
		}
		for (Ostacolo c : osta)
			if (c.getPos().getRiga() == x && c.getPos().getColonna() == y)
				return 4;
		return 5;

	}

	private int coloraDecolora(Cella c, ImageIcon img) {
		vecchia = new Posizioni(graffa.getRobot().getPos().getRiga(), graffa.getRobot().getPos().getColonna());
		Posizione arrivo = null;
		if (Ambiente.checkPosition(nuova.getX(), nuova.getY()) == false) {
			arrivo = new Posizione(nuova.getX(), nuova.getY());
			Ambiente.resetPos(arrivo.getRiga(), arrivo.getColonna());
		} else {
			arrivo = new Posizione(nuova.getX(), nuova.getY());
		}

		ArrayList<Posizione> cammino = Movimento.move(graffa.getRobot(), arrivo);
		changeImmagine(cammino, img);

		return cammino.size();

	}

	class MyListener implements ActionListener {
		public MyListener(ArrayList<Posizione> cammino, ImageIcon img) {
			this.i = 0;
			image = img;
			this.cammino = cammino;
			if (cammino.size() != 0) {
				celle[vecchia.getX()][vecchia.getY()].switchColorePrato();
				celle[vecchia.getX()][vecchia.getY()].setBackground(Color.white);
				if (cammino.size() != 1)
					celle[cammino.get(0).getRiga()][cammino.get(0).getColonna()].switchColoreRobot(image);

			}
		}

		public void actionPerformed(ActionEvent e) {
			if (i < cammino.size() - 1) {
				celle[cammino.get(i).getRiga()][cammino.get(i).getColonna()].switchColorePrato();
				celle[cammino.get(i + 1).getRiga()][cammino.get(i + 1).getColonna()].switchColoreRobot(image);
				i++;
			}
		}

		private ImageIcon image;
		private int i;
		private ArrayList<Posizione> cammino;
	}

	private void changeImmagine(ArrayList<Posizione> cammino, ImageIcon img) {
		ActionListener listener = new MyListener(cammino, img);
		Timer time = new Timer(500, listener);
		time.start();
	}

}

class Posizioni {
	private int x;
	private int y;

	Posizioni(int x, int y) {
		this.x = x;
		this.y = y;
		;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
