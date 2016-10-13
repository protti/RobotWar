package grafica;
import logica.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class BancoRifornimentoGrafico extends JFrame{

	BancoRifornimento banco;
	Robot richiedente;
	JButton button1;
	JButton button2;
	JButton button3;
	JPanel panel;

	public BancoRifornimentoGrafico(BancoRifornimento b,Robot round) {
		banco = b;
		ActionListener l0 = new BRListener(0,round);
		ActionListener l1 = new BRListener(1,round);
		ActionListener l2 = new BRListener(2,round);
		ImageIcon img0 = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/ascia.jpg");
		ImageIcon img1 = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/pistola.jpg");
		ImageIcon img2 = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/kit.jpg");
		button1 = new JButton(img0);
		button2 = new JButton(img1);
		button3 = new JButton(img2);
		button1.addActionListener(l0);
		button2.addActionListener(l1);
		button3.addActionListener(l2);
		panel = new JPanel();
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		this.add(panel);
	}

	public void openBR() {
		this.setSize(200, 200);
		this.setVisible(true);
	}

	class BRListener implements ActionListener {
		public BRListener(int id, Robot round) {
			this.id = id;
			richiedente = round;
		}

		public void actionPerformed(ActionEvent e) {
			RobotLavoratore rl = null;
			RobotAttaccante ra = null ;
			if (richiedente instanceof RobotLavoratore) 
				rl = (RobotLavoratore) richiedente;
			 else
				ra = (RobotAttaccante) richiedente;
			
			if (id == 0) {
				if (rl != null)
					rl.setTaskArmy(banco.getAx());
				else
					ra.changeWeapon(banco.getAx());
			}
			if (id == 1) {
				if (rl != null)
					rl.setTaskArmy(banco.getGun());
				else
					ra.changeWeapon(banco.getGun());
			}
			if (id == 2) {
				if (rl != null)
					rl.setTaskEnergy(banco.getRifornimento());
				else
					ra.takeLife(banco.getRifornimento().getRifornimento());
			}
			setSize(200, 200);
			setVisible(false);

		}

		private int id;
	}
}
