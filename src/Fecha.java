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
	
	private double mod(double x, double y)
	{
	    double result = x % y;
	    if (result < 0)
	    {
	        result += y;
	    }
	    return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fecha fecha1 = new Fecha ( 28 , 2 , 2012) ;
		System.out.println(fecha1); // martes 28 Feb 2012
		System.out.println(fecha1.siguienteDia()); // mi´ercoles 29 Feb 2012
		System.out.println(fecha1.siguienteDia()); // jueves 1 Mar 2012
		System.out.println(fecha1.siguienteMes()); // domingo 1 Apr 2012
		System.out.println(fecha1.siguienteAnyo()); // lunes 1 Apr 2013
		Fecha fecha2 = new Fecha ( 2 , 1 , 2012) ;
		System.out.println(fecha2); // lunes 2 Ene 2012
		System.out.println(fecha2.anteriorDia()); // domingo 1 Ene 2012
		System.out.println(fecha2.anteriorDia()); // s´abado 31 Dic 2011
		System.out.println(fecha2.anteriorMes()); // mi´ercoles 30 Nov 2011
		System.out.println(fecha2.anteriorAnyo()); // martes 30 Nov 2010
		Fecha d3 = new Fecha (29 , 2 ,2012 ) ;
		System.out.println(d3.anteriorAnyo()); // lunes 28 Feb 2011
		// Fecha fecha4 = new Fecha(31, 11, 2099); // D´ıa, mes o a~no no v´alidos
		// Fecha fecha5 = new Fecha(29, 2, 2011); // D´ıa, mes o a~no no v´alidos

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
		diaSemana = (int) mod((dia + Math.floor(2.6 * mes - 0.2) + anyoY + Math.floor(anyoY / 4) + Math.floor(anyoC / 4) - anyoC * 2), 7);
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
				
		if(getDia() == getDiaMax() && getMes() == 2 && esBisiesto())
			setDia(28);
		else
			setDia(getDia());
		setMes(getMes());
		setAnyo(getAnyo() + 1);
		return this;
	}
	
	public Fecha siguienteMes()
	{
		Fecha auxFecha = new Fecha(getDia(), getMes(), getAnyo());
		
		if(getAnyo() == 9999 && getMes() == 12)
			throw new IllegalStateException("Mes fuera de rango.");				
		if(getMes() == 12)
		{
			setAnyo(getAnyo() + 1);
			setMes(1);
		}
		else
		{
			setMes(getMes() + 1);
		}
		if(auxFecha.getDia() == auxFecha.getDiaMax())
			setDia(getDiaMax());
		return this;
	}
	
	public Fecha siguienteDia()
	{
		Fecha auxFecha = new Fecha(getDia(), getMes(), getAnyo());
		
		if(getAnyo() == 9999 && getMes() == 12 && getDia() == 31)
			throw new IllegalStateException("Dia fuera de rango.");	
		if(getMes() == 12 && getDia() == 31)
			setFecha(1, 1, getAnyo() + 1);
		else if(auxFecha.getDia() == auxFecha.getDiaMax())
			setFecha(1, getMes() + 1, getAnyo());
		else
			setFecha(getDia() + 1, getMes(), getAnyo());
		return this;
	}
	
	public Fecha anteriorAnyo()
	{
		if(getAnyo() == 1)
			throw new IllegalStateException("Anyo fuera de rango.");
		
		Fecha auxFecha = new Fecha(getDia(), getMes(), getAnyo());
		setMes(getMes());
		setAnyo(getAnyo() - 1);
		
		if(auxFecha.getDia() == auxFecha.getDiaMax() && auxFecha.getMes() == 2 && auxFecha.esBisiesto())
			setDia(28);
		else
			setDia(getDia());
		return this;
	}
	public Fecha anteriorMes()
	{
		Fecha auxFecha = new Fecha(getDia(), getMes(), getAnyo());

		if(getAnyo() == 1 && getMes() == 1)
			throw new IllegalStateException("Mes fuera de rango.");				
		if(getMes() == 1)
		{
			setAnyo(getAnyo() - 1);
			setMes(12);
		}
		else
		{
			setAnyo(getAnyo());
			setMes(getMes() - 1);
		}
		if(auxFecha.getDia() == auxFecha.getDiaMax())
			setDia(getDiaMax());
		return this;
	}
	
	public Fecha anteriorDia()
	{	
		if(getAnyo() == 1 && getMes() == 1 && getDia() == 1)
			throw new IllegalStateException("Dia fuera de rango.");	
		if(getMes() == 1 && getDia() == 1)
			setFecha(31, 12, getAnyo() - 1);
		else if(getDia() == 1)
			setFecha(getDiaMax(), getMes() - 1, getAnyo());
		else
			setFecha(getDia() - 1, getMes(), getAnyo());
		return this;
	}
	
}
