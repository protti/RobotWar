package grafica;
import logica.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class PropretyRobot {
	public static void drawLife(ArrayList<Robot> teamZ,ArrayList<Robot> teamU,JFrame fram)
	{
		class MListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					Save_Load.save();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		JFrame frame = fram;
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel l1 = new JLabel();
		JButton b = new JButton("SAVE");
		b.addActionListener(new MListener());
		panel1.setLayout(new GridLayout(2,5));
		
		for(int i = 0; i < teamZ.size();i++)
		{
			l1 = new JLabel();
			l1.setText("" + teamZ.get(i).getLife());
			panel1.add(l1);
		}
		for(int i = 0; i < teamU.size();i++)
		{
			l1 = new JLabel();
			l1.setText("" + teamU.get(i).getLife());
			panel1.add(l1);
		}
		
		panel2.add(b);
		panel.add(panel1);
		panel.add(panel2);
		frame.add(panel);
	}
	
	
}
