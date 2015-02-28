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
		Fecha primerBisiesto = new Fecha(1,1,9996);
		Fecha noBisiesto = new Fecha(1,1,1);
		Fecha segundoBisiesto = new Fecha(1,1,4);
		Fecha tercerBisiesto = new Fecha(1,1,400);
		Fecha segundoNoBisiesto = new Fecha(1,1,100);
		
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
		Fecha segundaNoValida = new Fecha(31, 9, 9999);
		Fecha segundaValida = new Fecha(31, 10, 9999);
		Fecha bisiestaValida = new Fecha(29, 2, 9996);
		Fecha bisiestaInvalida = new Fecha(29, 2, 9995);

		assertTrue("La fecha 01/01/0001 es valida", valida.esFechaValida());
		assertFalse("La fecha 31/09/9999 es invalida", segundaNoValida.esFechaValida());
		assertTrue("La fecha 31/10/9999 es valida", segundaValida.esFechaValida());
		assertFalse("La fecha 29/02/9995 es invalida", bisiestaInvalida.esFechaValida());
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
		erronea.setFecha(0,0,0000);
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
}
