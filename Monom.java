
package myMath;

import javax.management.RuntimeErrorException;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	
	public Monom() {
	
		this.set_coefficient(0);
		this.set_power(0);
	}

	public Monom(double a, int p){
			if(a!=0) {
				if(p>-1) {
					
					this.set_coefficient(a);
					this.set_power(p);
				}else 
					
				throw new RuntimeException("Error : Power can't be negative");
			}
			else
				
			this.set_coefficient(0);
		
		}
		


	public Monom(String s) {
		Monom m=init_from_String(s);
		this.set_coefficient(m.get_coefficient());
		this.set_power(m.get_power());
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public boolean isZero() {
		return this.get_coefficient()==0;
	}
	public static Monom init_from_String(String s) {
		s=s.toLowerCase();
		double a=0;
		int b=0;
		if(s==null)
			throw new RuntimeException("Error can not init Empty String");
		int x_ind=s.indexOf("x");
		if(x_ind==-1) {
			a=Double.parseDouble(s);
		}
		else if(x_ind==0) {
			
		a=1.0;
		b=1;
		int p_ind=s.indexOf("^");
		if(p_ind!=-1) {
			String pow=s.substring(p_ind+1,s.length());
			b=Integer.parseInt(pow);
		}
		}
		else if((x_ind==1)&&(s.charAt(x_ind-1)=='-')) {
			a=-1.0;
			b=1;
			int p_ind=s.indexOf("^");
			if(p_ind!=-1) {
				String pow=s.substring(p_ind+1,s.length());
				b=Integer.parseInt(pow);
			}
			
		}
		else
		{
			a=Double.parseDouble(s.substring(0,x_ind));
		
			b=1;
			int p_ind=s.indexOf("^");
			if(p_ind!=-1) {
				String pow=s.substring(p_ind+1,s.length());
				b=Integer.parseInt(pow);
			}
		}
		Monom m=new Monom(a, b);
		return m;
	}
	public String toString() {
		String ans="";
		if(this.isZero()) {ans="0";};
		if(this.get_power()<0)
			ans="0";
		if(this.get_coefficient()!=0) {
			ans+=this.get_coefficient();
			if(this.get_power()==1)
				ans+="x";
			if(this.get_power()>1) {
				ans+="x^"+this.get_power();
			}
			if(this.get_power()==0)
				ans+="";
			
		}
		return ans;
	}
		
		/*String ans="";
		if(this.isZero()) {ans="0";}
		else {
			ans=""+this.get_coefficient();
			if(this.get_power()>0) {
				ans+="x";
				if(this.get_power()>1) {
					ans+="^"+this.get_power();
				}

			}
		}
		return ans;
	*/	
	




	// ***************** add your code below **********************


	//****************** Private Methods and Data *****************

	public int get_power() {
		return _power;

	}
	public double get_coefficient() {
		return _coefficient;

	}
	public void set_coefficient(double a){
		this._coefficient = a;
	}
	public void set_power(int p) {
		this._power = p;
	}

	private double _coefficient; // 
	private int _power;
	@Override
	public double f(double x) {
		x = Math.pow( x , this._power );
	      x *= this._coefficient;
	      return x ; 
	}
	public void Derivative() {
		int nextpower=get_power()-1;
		set_coefficient(get_coefficient()*get_power());
		set_power(nextpower);

	}
	public void add(Monom other) {
		if(other.get_power()==_power) {
			this.set_coefficient(other.get_coefficient()+this.get_coefficient());
		} 
		else 

			throw new RuntimeException("The power is not the same");
	}

	public Monom multiply(Monom other) {
		Monom m=new Monom(_coefficient*other.get_coefficient(),_power+other.get_power());
		return m;


	}


}

