package grafica;
import logica.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class OstacoloGrafico extends JFrame{
	
	JPanel panel;
	JButton b1,b2;
	Ostacolo ost;
	RobotAttaccante ra;
	Posizione pOst;
	public OstacoloGrafico(RobotAttaccante ra,Ostacolo ostacolo)
	{
		panel = new JPanel();
		ost = ostacolo;
		this.ra = ra;
		b1 = new JButton("SPOSTA");
		b2 = new JButton("DISTRUGGI");
		ActionListener l1 = new List1();
		ActionListener l2 = new List2();
		b1.addActionListener(l1);
		b2.addActionListener(l2);
		panel.add(b1);
		panel.add(b2);
		this.add(panel);
		this.setSize(400, 400);
		this.setVisible(true);
		pOst = new Posizione(ost.getPos().getRiga(),ost.getPos().getColonna());
	}
	
	public boolean isChangePos()
	{
		return !ost.getPos().equals(pOst);
	}
	
	class List2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try {
				ra.getAction(ost);
				setVisible(false);
			} catch (InsufficientWeaponEnergy e1) {
				// TODO Auto-generated catch block
				JTextField tf = new JTextField(20);
				tf.setText(e1.getMessage());
				panel.add(tf);
			}
		}
	}
	
	class List1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			ra.sposta(ost);
			setVisible(false);
		}
	}
	
	
}
