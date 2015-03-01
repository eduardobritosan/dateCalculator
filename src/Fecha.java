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
	
	private enum diasDeLaSemana
	{
		Domingo, Lunes, Martes, Miercoles, Jueves, Viernes, Sabado;
	}
	
	private enum meses
	{
		enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre;
	}
	
	public Fecha() {
		setMes(1);
		setAnyo(1);
		setDia(1);
	}
	
	/**
	 * Constructor con parametros
	 * @param dia
	 * @param mes
	 * @param anyo
	 */
	public Fecha(int dia, int mes, int anyo)
	{
		setMes(mes);
		setAnyo(anyo);
		setDia(dia);
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
		if(dia < 1 || dia > getDiaMax())
			throw new IllegalArgumentException("Dia no valido");
		this.dia = dia; 
	}
	
	public int getMes()
	{
		return mes;
	}
	
	public void setMes(int mes)
	{
		if(mes > 12 || mes < 1)
			throw new IllegalArgumentException("Mes no valido.");
		this.mes = mes;
	}
	
	public int getAnyo()
	{
		return anyo;
	}
	
	public void setAnyo(int anyo)
	{
		if (anyo > 9999 || anyo < 1) 
			throw new IllegalArgumentException("Anyo no valido.");
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
		diaSemana = (int) ((dia + Math.floor(2.6 * mes - 0.2) + anyoY + Math.floor(anyoY / 4) + Math.floor(anyoC / 4) - anyoC*2) % 7);
		return diaSemana;
	}
	
	public void setFecha(int dia, int mes, int anyo)
	{
		Fecha test = new Fecha(dia, mes, anyo);
		if(!test.esFechaValida())
			throw new IllegalArgumentException("Dia, mes o anyo no valido.");
		this.dia = dia;
		this.mes = mes;
		this.anyo = anyo;
	}
	
	public int getDiaMax()
	{
		if(getMes() > 0 && getMes() < 8)
		{
			if(getMes() == 2 && esBisiesto())
				return 29;
			else if(getMes() == 2)
				return 28;
			else if(getMes() % 2 == 0)
				return 30;
			else
				return 31;
		}
		else if(getMes() <= 12)
		{
			if(getMes() % 2 == 0)
				return 31;
		}
		return 30;
	}
	public String toString()
	{
		String fechaString = (diasDeLaSemana.values()[getDiaDeSemana()]+ ", " + getDia() + " de " 
						      + meses.values()[getMes()-1] + " de " + getAnyo() + ".");
		return fechaString;
	}
	
	public Fecha siguienteAnyo()
	{
		if(getAnyo() == 9999)
			throw new IllegalStateException("Anyo fuera de rango.");
		
		Fecha resultado = new Fecha();
		
		if(getDia() == getDiaMax() && getMes() == 2 && esBisiesto())
			resultado.setDia(28);
		else
			resultado.setDia(getDia());
		resultado.setMes(getMes());
		resultado.setAnyo(getAnyo() + 1);
		return resultado;
	}
	
	public Fecha siguienteMes()
	{
		Fecha resultado = new Fecha();

		if(getAnyo() == 9999 && getMes() == 12)
			throw new IllegalStateException("Mes fuera de rango.");				
		if(getMes() == 12)
		{
			resultado.setAnyo(getAnyo() + 1);
			resultado.setMes(1);
		}
		else
		{
			resultado.setAnyo(getAnyo());
			resultado.setMes(getMes() + 1);
		}
		if(getDia() == getDiaMax())
			resultado.setDia(resultado.getDiaMax());
		return resultado;
	}
	
	public Fecha siguienteDia()
	{
		Fecha resultado = new Fecha();
		
		if(getAnyo() == 9999 && getMes() == 12 && getDia() == 31)
			throw new IllegalStateException("Dia fuera de rango.");	
		if(getMes() == 12 && getDia() == 31)
			resultado.setFecha(1, 1, getAnyo() + 1);
		else if(getDia() == getDiaMax())
			resultado.setFecha(1, getMes() + 1, getAnyo());
		else
			resultado.setFecha(getDia() + 1, getMes(), getAnyo());
		return resultado;
	}
	
	public Fecha anteriorAnyo()
	{
		if(getAnyo() == 1)
			throw new IllegalStateException("Anyo fuera de rango.");
		
		Fecha resultado = new Fecha();
		resultado.setMes(getMes());
		resultado.setAnyo(getAnyo() - 1);
		
		if(getDia() == getDiaMax() && getMes() == 2 && esBisiesto())
			resultado.setDia(28);
		else
			resultado.setDia(getDia());
		return resultado;
	}
	public Fecha anteriorMes()
	{
		Fecha resultado = new Fecha();

		if(getAnyo() == 1 && getMes() == 1)
			throw new IllegalStateException("Mes fuera de rango.");				
		if(getMes() == 1)
		{
			resultado.setAnyo(getAnyo() - 1);
			resultado.setMes(12);
		}
		else
		{
			resultado.setAnyo(getAnyo());
			resultado.setMes(getMes() - 1);
		}
		if(getDia() == getDiaMax())
			resultado.setDia(resultado.getDiaMax());
		return resultado;
	}
	
	public Fecha anteriorDia()
	{
		Fecha resultado = new Fecha();
		
		if(getAnyo() == 1 && getMes() == 1 && getDia() == 1)
			throw new IllegalStateException("Dia fuera de rango.");	
		if(getMes() == 1 && getDia() == 1)
			resultado.setFecha(31, 12, getAnyo() - 1);
		else if(getDia() == 1)
			resultado.setFecha(getDiaMax(), getMes() - 1, getAnyo());
		else
			resultado.setFecha(getDia() - 1, getMes(), getAnyo());
		return resultado;
	}
	
}
