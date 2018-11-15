package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomTest {

	@Test
	void testPolynomString() {
		Polynom p=new Polynom("5x^1+-3x^2");
		assertEquals("5.0x+-3.0x^2",p.toString());
	}

	@Test
	void testPolynomMonom() {
		Monom m=new Monom(2, 3);
		Polynom p=new Polynom(m);
		assertEquals("2.0x^3",p.toString());
	}

	@Test
	void testF() {
		Polynom p=new Polynom("2x^1+3x^2+-4x^2");
		int x=1;
		double d=p.f(x);
		assertEquals(1.0,d);
	}

	@Test
	void testAddPolynom_able() {
		Polynom p=new Polynom("-x^7+x^5");
		Polynom_able p2=new Polynom("-3x^1+5x^7");
		p.add(p2);
		assertEquals("-3.0x+1.0x^5+4.0x^7",p.toString());
	}

	@Test
	void testAddMonom() {
		Polynom p=new Polynom("2x^4+4x^2");
		Monom m=new Monom(-3,2);
		p.add(m);
		assertEquals("1.0x^2+2.0x^4",p.toString());
	}

	@Test
	void testSubstract() {
		Polynom p=new Polynom("-x^2+-x^4");
		Polynom_able p2=new Polynom("-3x^2+x^1");
		p.substract(p2);
		assertEquals("-1.0x+2.0x^2+-1.0x^4",p.toString());
	}

	@Test
	void testMultiply() {
		Polynom p=new Polynom("-x^2+-x^4");
		Polynom_able p2=new Polynom("-3x^2+x^1");
		p.multiply(p2);
		assertEquals("-1.0x^3+-1.0x^5+3.0x^4+3.0x^6",p.toString());
	}

	@Test
	void testEqualsPolynom_able() {
		Polynom p=new Polynom("3.0x^2+-x^5");
		Polynom_able p2=new Polynom("2x^4+x^5");
		assertTrue(!p.equals(p2),"the polynoms arent equals ");
	}

	@Test
	void testIsZero() {
		Polynom p=new Polynom();
		assertTrue(p.isZero(),"Should be true ");
	}

	@Test
	void testRoot() {
		double eps=Double.MIN_VALUE;
		Polynom p1=new Polynom("5x^2+2x^1");
		double res=p1.root(0.5, 1.5, eps);
		assertEquals("Close to ",res);
	}

	@Test
	void testCopy() {
		Polynom p=new Polynom("5x^2+2x^1");
		Polynom_able p2=p.copy();
		assertEquals("2.0x+5.0x^2",p2.toString());
		
	}

	@Test
	void testDerivative() {
		Polynom p=new Polynom("3x^2+7x^5");
		p.derivative();
		assertEquals("6.0x+35.0x^4",p.toString());
	}

	@Test
	void testArea() {
		double eps = 0.00001;
		Polynom p1=new Polynom("2.0x+-3.0x^2+1.0x^3");
		double res=p1.area(0,1, eps);
		assertEquals("close to 0.25",res);

	}

	@Test
	void testToString() {
		Polynom p=new Polynom("-3x^4+5x^2+-x^4");
		assertEquals("5.0x^2+-3.0x^4+-1.0x^4",p.toString());
	}

}
