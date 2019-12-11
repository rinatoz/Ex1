package Ex1;
import java.util.Comparator;

public class Monom implements function
{
	/**
	 * 
	 */


	/**
	 * 
	 */

	public static final Monom ZERO = new Monom(0,0);
	
	public static final Monom MINUS1 = new Monom(-1,0);
	
	public static final double EPSILON = 0.0000001;
	
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	
	public static Comparator<Monom> getComp() {return _Comp;}
	
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public double get_coefficient() {
		return this._coefficient;
	}
	
	public int get_power() {
		return this._power;
	}

	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	
	public boolean isZero() {return this.get_coefficient() == 0;}
	
	public Monom(String s)
	{
		int k=0;
		String doub="";
		double d=0;
		if (s=="x")          
		{
			this._coefficient=1;
			this._power=1;
		}
		else
		if (s=="-x")          
		{
			this._coefficient=-1;
			this._power=1;
		}
		else
		{
		    if (s.charAt(0)=='x')
			{
				this._coefficient=1;
			}
		    else
		    {
		while (k<s.length()&&s.charAt(k)!='x') 
		{
		  doub=doub+s.charAt(k);
		  k++;
		}
        if (isDigit(doub)==false)   
        {
        	throw new RuntimeException ("uncorrect monom");
        }
        if (doub.charAt(0)=='-')
        {
        	if (doub.substring(1).isEmpty())
        	{
        		doub="-1";
        	}
        	 d=Double.parseDouble(doub.substring(1));
        	 d=-1*d;
        }
        else if (doub.charAt(0)=='+')
        {
        	if (doub.substring(1).isEmpty())
        	{
        		doub="1";
        	}
        	 d=Double.parseDouble(doub.substring(1));
        	 d=1*d;
        }
        else
        {
		 d=Double.parseDouble(doub); 
        }
		this._coefficient=d;
		 }
		if (k==s.length()) 
		{
			this._power=0;
		}
		else if (k==s.length()-1)  
		{
			this._power=1;
		}
		else if (s.charAt(k+1)!='^'||(s.charAt(k+1)=='^'&&k+1==s.length()-1)) 
		{
			 throw new RuntimeException ("uncorrect monom");
		}
		else 
		{
			String inti="";
			for (int j=k+2;j<s.length();j++)
			{
				inti=inti +s.charAt(j);
			}
			if (isDigit(inti)==false)
			{
				throw new RuntimeException ("uncorrect monom");
			}
			this._power=Integer.parseInt(inti);
		}
		}	
	}
	
	public void add(Monom m)
	{
		if (m.isZero()==false&&this.isZero()==false)
		{
	       if (m._power==this._power)
	          {
		     this._coefficient=this._coefficient+m._coefficient;
		          if (this._coefficient==0)
		         {
		    	 this._power=0;
		         }
	          }
	          else
	          {
		        throw new RuntimeException("the _power must be equal!");
	          }
		}
	}
	
	public void multipy(Monom d)
	{
		 if (d.isZero()||this.isZero())
		 {
			 this._coefficient=0;
			 this._power=0;
		 }
		 else
		 {
			 this._coefficient=this._coefficient*d._coefficient;
			 this._power=this._power+d._power;
		 }
	}
	
	public String toString() 
	{
		String ans = "";
		if (this._power==0)
		{
			ans="" +this._coefficient;
		}
		else if(this._power==1)
		{
			ans=this._coefficient+"x";
		}
		else if (this._coefficient==0)
		{
			ans="0.0";
		}
		else if (this._coefficient==1)
		{
			ans="x^"+this._power;
		}
		else
		{
		ans=this._coefficient+"x^"+this._power;
		}
		
		return ans;
	}

	private static boolean isDigit (String st)     //check if String is a correct Monom.
	{
		int counter=0;
		if (st.charAt(0)=='-'||st.charAt(0)=='+')
		{
			counter++;
		}
		for (int i=0;i<st.length();i++)
		{
			if (st.charAt(i)=='.')
			{
				counter++;
				if (i==0)
					return false;
				if (i==st.length()-1)
					return false;
			}	
			if (st.charAt(i)>='0'&&st.charAt(i)<='9')
			{
				counter++;
			}
		}
		if (counter==st.length())
		{
		return true;
		}
		return false;
	}

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	
	private boolean equalsTEMP (Monom m)
	{
		if(Math.round(this._coefficient)==0&&Math.round(m._coefficient)==0)
			return true;
		if (Math.round(this._coefficient)!=Math.round(m._coefficient)|this._power!=m._power)
			return false;
		return true;
	}
	
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	
	private double _coefficient; 
	
	private int _power;
	
	
	public function initFromString(String s)
	{
		Monom m=new Monom (s);
		function f=new Monom(m);
		return f;
	}
	
	public function copy()
	{
		function f=this.copy();
		return f;
	}
	public boolean equals(Object obj)
	{
		if (obj instanceof Monom)
		{
			Monom m=new Monom (obj.toString());
			return equalsTEMP(m);
		}
		else if (obj instanceof Polynom)
		{
			if (obj.toString().equals(this.toString()))
				return true;
		}
		else
		{
			ComplexFunction comp=new ComplexFunction (obj.toString());
			if (comp.op.toString().equals("Plus"))
			{
			    if (comp.left instanceof Polynom && !(comp.right instanceof ComplexFunction))
				  {
				 Polynom p=(Polynom) comp.left;
			     Polynom p1=(Polynom) comp.right;
				 p.add(p1);
				 if(this.toString().equals(p.toString()))
					     return true;  
				 }
				      
			   }
			if (comp.op.toString().equals("Times"))
			{
			    if (comp.left instanceof Polynom && !(comp.right instanceof ComplexFunction))
				  {
				 Polynom p=(Polynom) comp.left;
			     Polynom p1=(Polynom) comp.right;
				 p.multiply(p1);
				 if(this.toString().equals(p.toString()))
					     return true;  
				 }
				      
			   }
			if (comp.op.toString().equals("None"))
			{
			    if (comp.left instanceof Polynom)
				  {
				 if(this.toString().equals(comp.left.toString()))
					     return true;  
				 }
				      
			   }
		   }
		
		return false;
	   }
		

	
}

	

