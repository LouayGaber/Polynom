package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTest {

	@Test
	void testMonom() {
		Monom m=new Monom();
		assertEquals("0",m.toString());
	}

	@Test
	void testMonomDoubleInt() {
		Monom m=new Monom(7,3);
		assertTrue(m.get_power()==3&&m.get_coefficient()==7.0);
	}

	@Test
	void testMonomString() {
		Monom m=new Monom("0.1x^7");
		
		assertEquals(m.get_coefficient() ,0.1);
		assertEquals(m.get_power(),7);
		
	}

	
	@Test
	void testIsZero() {
		Monom m=new Monom("5.0x^2");
		assertTrue(!m.isZero(),"Should not be zero");
	}

	@Test
	void testToString() {
		Monom m=new Monom("-x^3");
		assertEquals("-1.0x^3",m.toString());
	}

	@Test
	void testF() {
		Monom m=new Monom("4x^3");
		assertEquals(32.0,m.f(2));
	}

	@Test
	void testDerivative() {
		Monom m=new Monom("3x^8");
		m.Derivative();
		assertEquals("24.0x^7",m.toString());
		
	}

	@Test
	void testAdd() {
		Monom m=new Monom(2,5);
		Monom m2=new Monom(-2,5);
		m.add(m2);
		assertEquals("0",m.toString());
	}

	@Test
	void testMultiply() {
		Monom m1=new Monom("-4x^3");
		Monom m2=new Monom("5x^4");
		assertEquals("-20.0x^7",m1.multiply(m2).toString());
	}

}
