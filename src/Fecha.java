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
	/**
	 * Calculo de el dia de la semana con el algoritmo de Gauss revisado.
	 * @return
	 */
	public int getDiaDeSemana()
	{
		double mes, dia, anyo, anyoY, anyoC;
		int diaSemana;
		String stringAnyo;
		String []stringAnyoSeparada;
		
		dia = getDia();
		if(getMes() == 1 || getMes() == 2)
			anyo = getAnyo() - 1;
		else
			anyo = getAnyo();
		mes = ((getMes() + 9) % 12) + 1;
		stringAnyo = Double.toString(anyo);
		stringAnyoSeparada = stringAnyo.split("(?<=\\G..)");
		anyoC = Double.parseDouble(stringAnyoSeparada[0]);
		anyoY = Double.parseDouble(stringAnyoSeparada[1]);
		diaSemana = (int) ((dia + (2.6 * mes - 0.2) + anyoY + (anyoY / 4) + (anyoC / 4) - anyoC*2) % 7);
		return diaSemana;
	}
	public void setFecha(int dia, int mes, int anyo)
	{
		Fecha test = new Fecha(dia, mes, anyo);
		if(!test.esFechaValida())
			throw new IllegalArgumentException();
		this.dia = dia;
		this.mes = mes;
		this.anyo = anyo;
	}
}
