package logica;

public class BancoRifornimento extends Oggetto {

	/* Kit per carburante */
	/**/

	/**
	 * Crea un nuovo oggetto di tipo Banco di rifornumento, e lo inserisce
	 * all'interno dello scenario
	 */

	public BancoRifornimento() {
		super();
		Scenario.addBanco(this);
	}

	/**
	 * Da al robot richiedente una nuova pistola
	 * 
	 * @return Gun
	 */
	public Gun getGun() {
		return new Gun();
	}

	/**
	 * Da al robot richiedente una nuova ascia
	 * 
	 * @return Ax
	 */
	public Ax getAx() {
		return new Ax();
	}

	/**
	 * Da al robot lavoratore un nuovo rifornimento
	 * 
	 * @return Rifornimento
	 */
	public Rifornimento getRifornimento() {
		return new Rifornimento();
	}
	
	public BancoRifornimento clone()
	{
		BancoRifornimento clonato = (BancoRifornimento) super.clone();
		return clonato;
	}
	
	public String toString()
	{
		return("" + super.toString());
	}
	
	public boolean equals(Object oggetto)
	{
		return (super.equals(oggetto));
	}
	
	
	
}
