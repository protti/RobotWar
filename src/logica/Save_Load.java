package logica;

import java.io.*;
import java.util.ArrayList;

public class Save_Load {
	public static void save() throws FileNotFoundException, IOException
	{
		File file = new File("robot.dat");
		ArrayList<Robot> r0 = Scenario.getTeamZero();
		ArrayList<Robot> r1 = Scenario.getTeamUno();
		ArrayList<BancoRifornimento> banchi = Scenario.getBanchi();
		
		if(file.exists()) file.delete(); 
		
		ObjectOutputStream fo = new ObjectOutputStream(new FileOutputStream(file));
		fo.writeObject(r0);
		fo.writeObject(r1);
		fo.writeObject(banchi);
		fo.close();
	}
	
	public static void load() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		File file = new File("robot.dat");
		if(file.exists())
		{
			ObjectInputStream fi = new ObjectInputStream(new FileInputStream(file));
			Object obj;
			ArrayList<Robot> r0 = (ArrayList<Robot>) fi.readObject();
			ArrayList<Robot> r1 = (ArrayList<Robot>) fi.readObject();
			//ArrayList<Ostacolo> ost = (ArrayList<Ostacolo>) fi.readObject();
			ArrayList<BancoRifornimento> banchi = (ArrayList<BancoRifornimento>) fi.readObject();
			
			for(Robot zero: r0)
			{
				
				Scenario.addRobotUno(zero);
			}
			for(Robot uno: r1)
			{
				Scenario.addRobotDue(uno);
			}
			
			for(BancoRifornimento br: banchi)
			{
				Scenario.addBanco(br);
			}
			fi.close();
		}
	}
	
	public static void remove()
	{
		File file = new File("robot.dat");
		if(file.exists())
		{
			file.delete();
		}
	}
}
