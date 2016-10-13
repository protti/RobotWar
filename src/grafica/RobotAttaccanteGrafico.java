package grafica;
import logica.*;

import javax.swing.*;

public class RobotAttaccanteGrafico extends JFrame {

	JLabel messaggio;
	JPanel pannello;
	
	RobotAttaccanteGrafico(String mex)
	{
			messaggio = new JLabel(mex);
			pannello = new JPanel();
			pannello.add(messaggio);
			this.add(pannello);
	}
	
	

}
