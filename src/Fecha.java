/**
 * 
 */

/**
 * @author eebritos
 *
 */
public class Fecha {

	/**
	 * 
	 */
	
	private int dia;
	private int mes;
	private int anyo;
	
	public enum diasDeLaSemana
	{
		DOMINGO, LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO;
	}
	
	public Fecha() {
		setDia(1);
		setMes(1);
		setAnyo(1);
	}
	
	/**
	 * Constructor con parametros
	 * @param dia
	 * @param mes
	 * @param anyo
	 */
	public Fecha(int dia, int mes, int anyo)
	{
		setDia(dia);
		setMes(mes);
		setAnyo(anyo);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int getDia()
	{
		return dia;
	}
	
	public void setDia(int dia)
	{
		this.dia = dia; 
	}
	
	public int getMes()
	{
		return mes;
	}
	
	public void setMes(int mes)
	{
		this.mes = mes;
	}
	
	public int getAnyo()
	{
		return anyo;
	}
	
	public void setAnyo(int anyo)
	{
		this.anyo = anyo;
	}
	
	public boolean esBisiesto()
	{
		boolean bisiesto = ((getAnyo() % 4 == 0) && (getAnyo() % 100 != 0) || (getAnyo() % 400 == 0));
		return bisiesto;
	}
	
	public boolean esFechaValida()
	{
		if(getAnyo() < 1 || getAnyo() > 9999)
			return false;
		if(getMes() > 0 && getMes() < 8)
		{
			if((getMes() == 2 && !esBisiesto() && getDia() > 28) || (getMes() == 2 && esBisiesto() && getDia() > 29))
				return false;
			else if(((getMes() % 2 == 0) && getDia() > 30) || ((getMes() % 2 != 0) && getDia() > 31))
				return false;
		}
		else if(getMes() <= 12)
		{
			if(((getMes() % 2 == 0) && getDia() > 31) || ((getMes() % 2 != 0) && getDia() > 30))
				return false;
		}
		return true;
	}
	public int getDiaDeSemana()
	{
		return 0;
	}
}
