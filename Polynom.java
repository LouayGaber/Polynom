package myMath;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	
	


	public Polynom() {
		this.poly=new ArrayList<Monom>();

	}

	public Polynom(String s) {//Polynom string constructor , splitting the string into sub string and put every sub string in array, then every substring could be an monom . then we move to monom class functions .
		if(s.length()==0)//checking if the string is empty
			throw  new RuntimeException("Error : polynom cann't be Empty ");
		String s2="";
		s=s.replaceAll(" ", "");
		s=s.replaceAll("-", "+-");
		String[] sub=s.split("\\+");

		for (int i = 0; i < sub.length; i++) {
			if(sub[i].equals(s2))
				continue;
			Monom m=new Monom(sub[i]);
			poly.add(m);
			Collections.sort(poly,new Monom_Comperator());
		}



	}

	public Polynom(Monom m) {//Polynom constructor that create a polynom with a only one monom 
		Polynom p=new Polynom();
		p.poly=this.poly;
		if(p.poly.isEmpty()) {
			p.poly.add(m);
		}else
			p.poly.add(m);
		Collections.sort(poly,new Monom_Comperator());

	}

	@Override
	public double f(double x) {//here we return the f(x).so we move using iterator on the polynom , and every monom we do an f(x) by monom f(x) fun. 
		Iterator<Monom> it=this.iteretor();
		double res=0;
		while(it.hasNext()) {
			Monom m=it.next();
			res+=m.f(x);


		}
		return res;//the result of the sum of monoms(f(x))

	}

	@Override
	public void add(Polynom_able p1) {//this will add an polynom to our polynom , by moving on the giving polynom and add every monom to our polynom then we sort it . 
		Polynom_able pCopy = new Polynom();
		pCopy = this.copy() ;
		Iterator<Monom> addIt = p1.iteretor() ;
		Monom mon1;
		while(addIt.hasNext())
		{
			mon1=addIt.next();
			pCopy.add(mon1);
		}
		Polynom pcopy2=(Polynom)pCopy;
		this.poly=pcopy2.poly;
		Collections.sort(poly,new Monom_Comperator());		

	}
	public ArrayList<Monom> getPoly(){
		return this.poly;
	}








	@Override
	public void add(Monom m1) {//this fun will add an monom to our polynom , so we move on our polynom using iterator and check if we have a monom with the same power of our giving monom so we can merge it .
		Polynom p=new Polynom();
		p.poly=this.poly;
		Iterator<Monom> it=p.iteretor();
		boolean flag=false;
		while(it.hasNext()) {
			Monom m0=it.next();
			if(m1.get_power()==m0.get_power()) {
				double newco=m0.get_coefficient()+m1.get_coefficient();
				m0.set_coefficient(newco);
				flag=true;
			}


		}
		if(flag==false)
			p.poly.add(m1);


	}



	@Override
	public void substract(Polynom_able p1) {//this will substract an giving monom from our polynom by the multply fun. by multiplying the giving polynom with (-1.0) and adding it to our polynom .
		Polynom p=new Polynom();
		p=(Polynom)p1;
		Polynom m = new Polynom();
		m.add(new Monom(-1,0));
		p1.multiply(m);
		this.add(p1);

	}



	@Override

	public void multiply(Polynom_able p1) {//this will multply our poly with giving poly by moving with iterator and for loop . one can move on the giving poly and the other loop for our poly and multply every monom by the other poly
		Polynom pother=new Polynom();
		pother=(Polynom)p1;
		Collections.sort(pother.poly,new Monom_Comperator());
		Polynom p3=new Polynom();
		Iterator<Monom> it=pother.iteretor();
		Monom m;
		while(it.hasNext()) {
			m=it.next(); 
			for (int i = 0; i <this.poly.size(); i++) {
				Monom mu=new Monom(m.multiply(this.poly.get(i)));
				p3.add(mu);

			}

		}
		this.poly=p3.poly;


	}


	@Override
	public boolean equals(Polynom_able p1) {//here we can check if the giving polynom is equal to our polynom by using 2 iterators and checking every monom if equal to the other one .
		Iterator<Monom> p1it=p1.iteretor();
		Iterator<Monom> pit=this.poly.iterator();
		while(p1it.hasNext()) {
			Monom m1=p1it.next();
			Monom m2=pit.next();
			if((m1.get_power()!=m2.get_power())||m1.get_coefficient()!=m2.get_coefficient())
				return false;
		}
		return true;


	}


	@Override
	public boolean isZero() {//this will check if our poly is zero by moving using iteratoz on or monom and check if our monom coefficient isn't zero then return true if nothing found .
		Iterator<Monom> it=this.poly.iterator();
		while(it.hasNext()) {
			Monom m=it.next();
			if(m.get_coefficient()!=0)
				return false;

		}
		return true;
	}



	@Override
	public double root(double x0, double x1, double eps) //root function will give us root of our poly by entring 2 points and eps . so we will check on every iteration the mid and take the negative side then returning the mid when the mid became < from eps 
	{
		
		if(this.f(x0)*this.f(x1)>=0) {
			System.out.println("You have not assumed"+" right a and b ");
			return -1;
			
		}
		double c=x0;
		while((x1-x0)>=eps) {
			c=(x1+x0)/2;
			 if(this.f(c)==0.0)
				 break;
			 else if (this.f(c)*this.f(x0)<0)
				 x1=c;
			 else
				 x0=c;
		}
			
			return c;
		}
		/*if(x1<x0) {
			double tmp=x0;
			x0=x1;
			x1=tmp;
		}
		double xleft=x0;
		double xright=x1;
		double fxleft=this.f(xleft);
		double fxright=this.f(xright);
		double interval=xright-xleft;
		double xmid,fxmid;
		if(fxright*fxleft>0) {
			System.out.println("have to be a negetive number to use root function!!!");
			return -1;
		}
		xmid=(xleft+xright)/2;
		fxmid=this.f(xmid);
		while(interval>eps) {
			xmid=xmid/2;
			if(fxleft*fxmid>0) {
				xleft=xmid;
			}
			xright=xmid;
		}
		return xmid;
*/
	




	@Override
	public Polynom_able copy() {// this will copy our poly by moving on our poly using iterator and adding it to a new polynom . 
		Polynom_able p=new Polynom();
		Iterator<Monom> it=this.poly.iterator();
		while(it.hasNext()){
			Monom m=it.next();
			p.add(m);
		}
		return p;

	}

	@Override
	public Polynom_able derivative() {//this will derivative the poly by moving on every monom in the poly and derivate it using the monom func 
		Polynom p=new Polynom();
		Iterator<Monom> it=this.poly.iterator();
		while(it.hasNext()) {
			Monom m=it.next();
			m.Derivative();
			p.add(m);

		}
		return p;


	}
	 /* Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		double k;
		double area = 0;
		double xleft=x0;
		double xright=x1;
		for(double j = xleft; j<xright; j=j+eps) {
			for(int i = 0; i<this.poly.size(); i++) {
				double y = j+(eps/2);
				k = this.poly.get(i).f(y); 
				area = area + (k*eps); 
			}
		}
		return area; 

		
	/*	double xleft=x0;
		double xright=x1;
		double sum=0;
		double interval=xright-xleft;
		double fxright=this.f(xright);
		double fxleft=this.f(xleft);
		double  res=(int)((interval)*(fxright-fxleft))/eps;
		double deltax=(interval)/res;
		for(int i=0;i<Math.abs(res);i++){
			sum+=this.f(xleft)*deltax;
			xleft=+deltax;
		}
		return 	Math.abs(sum) ;
		*/
	}

	@Override
	public Iterator<Monom> iteretor() {
		return poly.iterator();
	}

	// ********** add your code below ***********
	private   ArrayList<Monom> poly=new ArrayList<Monom>();
	Iterator<Monom> polyit=poly.iterator();




	public String toString() {//this will print our poly 
		for (int i = 0; i <poly.size(); i++) {
			if(poly.get(i).get_coefficient()==0)
				poly.remove(i);

		}
		String res=null;
		Polynom p=new Polynom();
		p.poly=this.poly;
		boolean f=true;
		if(p.isZero())
			return "0";
		Iterator<Monom> it=p.iteretor();
		while(it.hasNext()) {
			Monom m=it.next();
			if(res==null) {
				res=""+m.toString();
			}
			else res+="+"+m.toString();
		}
		return res;
	}
	public static void main(String[] args) {
		double eps=Double.MIN_VALUE;
		Polynom p1=new Polynom("x^3+2x^1+2");
		double res=p1.root(-10, 10,eps);
		System.out.println(res);
		
	}



}
