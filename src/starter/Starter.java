package starter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import grafica.*;
import logica.*;

public class Starter {
	
	
	public static void main(String[] args) {
		
		 
		 JFrame frame = new JFrame();
		 final JFrame bottoni = new JFrame();
		
	
		JButton bottone1 = new JButton("Nuova Partita");
		JButton bottone2 = new JButton("Carica");
		
		class listenerNew implements ActionListener
		{
			GrigliaML nuova; 
			public void actionPerformed(ActionEvent e) 
			{			
				RobotAttaccante attac00 = new RobotAttaccante(0);
				RobotAttaccante attac01 = new RobotAttaccante(0);
				RobotAttaccante attac02 = new RobotAttaccante(0);
				RobotLavoratore lavor00 = new RobotLavoratore(0);
				RobotLavoratore lavor01 = new RobotLavoratore(0);
				
				RobotAttaccante attac10 = new RobotAttaccante(1);
				RobotAttaccante attac12 = new RobotAttaccante(1);
				RobotAttaccante attac13 = new RobotAttaccante(1);
				RobotLavoratore lavor10 = new RobotLavoratore(1);
				RobotLavoratore lavor11 = new RobotLavoratore(1);
				
				BancoRifornimento banco1 = new BancoRifornimento();
				BancoRifornimento banco2 = new BancoRifornimento();
				BancoRifornimento banco3 = new BancoRifornimento();
				nuova = new GrigliaML(25,25,attac00);
				frame.add(nuova);
				frame.setSize(400,400);
				frame.setVisible(true);
				bottoni.setVisible(false);
				
			}	
		}
		class listenerLoad implements ActionListener 		
		{
			GrigliaML nuova; 
			public void actionPerformed(ActionEvent e) 
			{			
				try {
					Save_Load.load();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				nuova = new GrigliaML(25,25,Scenario.getTeamZero().get(0));
				frame.add(nuova);
				frame.setSize(400,400);
				frame.setVisible(true);
				bottoni.setVisible(false);
			}	
		}
		
		bottone1.addActionListener(new listenerNew());
		bottone2.addActionListener(new listenerLoad());
		JPanel pannello = new JPanel();
		pannello.add(bottone1);
		pannello.add(bottone2);
		
		bottoni.add(pannello);
		
		bottoni.setSize(400, 400);
		bottoni.setVisible(true);
		
		
		
		
		
		
		
	}
	
}
