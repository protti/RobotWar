package grafica;
import logica.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class RobotLavoratoreGrafico extends JFrame{
	
	RobotLavoratore lavo;
	Robot richiedente;
	JButton button1;
	JButton button2;
	JPanel panel = null;
	
	public RobotLavoratoreGrafico(RobotAttaccante riceve,Robot round)
	{
		
		lavo = (RobotLavoratore)round;
		if(lavo.getTaskEnergy() != null || lavo.getTaskArmy() != null)
		{
			panel = new JPanel();
			ImageIcon img2 = new ImageIcon("C:/Users/jeson/Desktop/WorkspaceJava/INTERFACCIA_GRAFICA/images/kit.jpg");
			button2 = new JButton(img2);
			ActionListener l = new Listener(riceve);
			button2.addActionListener(l);
			panel.add(button2);
			this.add(panel);
		}
		
	}
	public void openRL() {
		if(panel != null)
		{
			this.setVisible(true);
			this.setSize(200, 200);
		}
	}
	class Listener implements ActionListener{
		private RobotAttaccante robot;
		public Listener(RobotAttaccante robot){
			this.robot = robot;
		}
		public void actionPerformed(ActionEvent e){
			lavo.getAction(robot);
			setVisible(false);
		}
	}
}
