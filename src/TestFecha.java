import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import org.junit.Before;
import org.junit.Test;


public class TestFecha {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEsBisiesto() 
	{
		Fecha primerBisiesto = new Fecha(1, 1, 9996);
		Fecha noBisiesto = new Fecha(1, 1, 1);
		Fecha segundoBisiesto = new Fecha(1, 1, 4);
		Fecha tercerBisiesto = new Fecha(1, 1, 400);
		Fecha segundoNoBisiesto = new Fecha(1, 1, 100);
		
		assertTrue("El anyo 9966 es bisiesto", primerBisiesto.esBisiesto());
		assertFalse("El anyo 1 no es bisiesto",noBisiesto.esBisiesto());
		assertTrue("El anyo 4 es bisiesto", segundoBisiesto.esBisiesto());
		assertTrue("El anyo 400 es bisiesto", tercerBisiesto.esBisiesto());
		assertFalse("El anyo 100 no es bisiesto",segundoNoBisiesto.esBisiesto());
	}
	
	@Test
	public void testEsFechaValida()
	{
		Fecha valida = new Fecha(1, 1, 1);
		Fecha segundaValida = new Fecha(31, 10, 9999);
		Fecha bisiestaValida = new Fecha(29, 2, 9996);

		assertTrue("La fecha 01/01/0001 es valida", valida.esFechaValida());
		assertTrue("La fecha 31/10/9999 es valida", segundaValida.esFechaValida());
		assertTrue("La fecha 29/02/9996 es valida", bisiestaValida.esFechaValida());
	}
	
	@Test
	public void testGetDiaDeSemana()
	{
		Fecha martes = new Fecha(1, 2, 2011);
		Fecha miercolesBisiesto = new Fecha(29, 02, 2012);
		Fecha primerDia = new Fecha(1, 1, 1);
		Fecha ultimoDia = new Fecha(31, 12, 9999);
		
		assertThat("El 01/02/2011 es martes", martes.getDiaDeSemana(), is(2));
		assertThat("El 01/02/2011 no es miercoles", martes.getDiaDeSemana(), is(not(3)));
		assertThat("El 29/02/2012 es bisiesto", miercolesBisiesto.esBisiesto(), is(true));
		assertThat("El 29/02/2012 de un anyo bisiesto es miercoles", miercolesBisiesto.getDiaDeSemana(), is(3));
		assertThat("El 01/01/0001 es lunes", primerDia.getDiaDeSemana(), is(1));
		assertThat("El 31/12/9999 es domingo", ultimoDia.getDiaDeSemana(), is(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetFechaExcepcion()
	{
		Fecha erronea = new Fecha();
		erronea.setFecha(0, 0, 0000);
	}
	
	@Test
	public void testSetFecha()
	{
		Fecha correcta = new Fecha();
		correcta.setFecha(1, 11, 2012);
		
		assertThat("El dia debe ser 1", correcta.getDia(), is(1));
		assertThat("El mes debe ser 11", correcta.getMes(), is(11));
		assertThat("El anyo debe ser 2012", correcta.getAnyo(), is(2012));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetAnyo()
	{
		Fecha setterTest = new Fecha();
		setterTest.setAnyo(99999);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetMes()
	{
		Fecha setterTest = new Fecha();
		setterTest.setMes(-2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetDia()
	{
		Fecha setterDia = new Fecha();
		setterDia.setDia(-1);
	}
	
	@Test
	public void testDiaMax()
	{
		Fecha diaMaxFebreroBisiesto = new Fecha(1, 02, 2012);
		Fecha diaMaxOctubre = new Fecha(1, 10, 2012);
		Fecha diaMaxFebrero = new Fecha(1, 02, 2013);
		Fecha diaMaxSeptiembre = new Fecha(1, 9, 2012);
		
		assertThat(diaMaxFebreroBisiesto.getDiaMax(), is(29));
		assertThat(diaMaxFebrero.getDiaMax(), is(28));
		assertThat(diaMaxOctubre.getDiaMax(), is(31));
		assertThat(diaMaxSeptiembre.getDiaMax(), is(30));
	}
	
	@Test
	public void testToString()
	{
		Fecha testString = new Fecha(1, 2, 2011);
		assertThat(testString.toString(), is("Martes, 1 de febrero de 2011."));
	}
	
	@Test
	public void testSiguienteAnyo()
	{
		Fecha testAnyoBisiesto = new Fecha(29, 2, 2012);
		Fecha testAnyo = new Fecha(27, 9, 2014);
		
		assertThat("La fecha deberia ser Jueves, 28 de febrero de 2013.", 
				    testAnyoBisiesto.siguienteAnyo().toString(), is("Jueves, 28 de febrero de 2013."));
		assertThat("La fecha deberia ser Domingo, 27 de septiembre de 2015.", 
			        testAnyo.siguienteAnyo().toString(), is("Domingo, 27 de septiembre de 2015."));
	}
	
	@Test(expected = IllegalStateException.class)
	public void testSiguienteAnyoException()
	{
		Fecha testAnyoErroneo = new Fecha(1, 1, 9999);
		assertThat(testAnyoErroneo.siguienteAnyo().toString(), is(""));
	}
	
	@Test
	public void testSiguienteMes()
	{
		Fecha mesSiguienteBisiesto = new Fecha(31, 1, 2012);
		Fecha mesSiguienteNoBisiesto = new Fecha(31, 1, 2013);
		Fecha mesSiguienteFinDeAnyo = new Fecha(31, 12, 2013);
		Fecha mesSiguienteTreinta = new Fecha(31, 10, 2014);
		
		assertThat("La fecha deberia ser Miercoles, 29 de febrero de 2012.", 
					mesSiguienteBisiesto.siguienteMes().toString(),is("Miercoles, 29 de febrero de 2012."));
		assertThat("La fecha deberia ser Jueves, 28 de febrero de 2013.",
				    mesSiguienteNoBisiesto.siguienteMes().toString(), is("Jueves, 28 de febrero de 2013."));
		assertThat("La fecha deberia ser Viernes, 31 de enero de 2014.", 
				    mesSiguienteFinDeAnyo.siguienteMes().toString(), is("Viernes, 31 de enero de 2014."));
		assertThat("La fecha deberia ser Domingo, 30 de noviembre de 2014.", 
				    mesSiguienteTreinta.siguienteMes().toString(), is("Domingo, 30 de noviembre de 2014."));
	}
	
	@Test(expected = IllegalStateException.class)
	public void testSiguienteMesException()
	{
		Fecha mesSiguienteException = new Fecha(31, 12, 9999);
		
		assertThat(mesSiguienteException.siguienteMes().toString(), is(""));
	}
	
	@Test(expected = IllegalStateException.class)
	public void testSiguienteDiaException()
	{
		Fecha diaSiguienteException = new Fecha(31, 12, 9999);
		
		assertThat(diaSiguienteException.siguienteDia().toString(), is(""));
	}
	
	@Test
	public void testSiguienteDia()
	{
		Fecha anyoNuevo = new Fecha(31, 12, 2013);
		Fecha mesNuevo = new Fecha(30, 11, 2013);
		Fecha diaNuevo = new Fecha(1, 1, 2013);
		
		assertThat("La fecha deberia ser Miercoles, 1 de enero de 2014.",
				    anyoNuevo.siguienteDia().toString(), is("Miercoles, 1 de enero de 2014."));
		assertThat("La fecha deberia ser Lunes, 1 de diciembre de 2013.",
			    mesNuevo.siguienteDia().toString(), is("Lunes, 1 de diciembre de 2013."));
		assertThat("La fecha deberia ser Miercoles, 2 de enero de 2013.",
			    diaNuevo.siguienteDia().toString(), is("Miercoles, 2 de enero de 2013."));
	}
}
