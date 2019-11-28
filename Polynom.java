package myMath;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;

//import exe0.Polynom;

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

	public ArrayList<Monom> _pol;
	
	public Polynom() 
	{
		Monom m= new Monom ("0");
		this._pol=new ArrayList<Monom>();
		this._pol.add(m);
	}

	public Polynom(String s)
	{
		Monom mon=new Monom("0");
		String st="";
		this._pol=new ArrayList<Monom>();
	
		for (int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='-'&&i==0)
			{
				st="-";
			}
			else
			if (s.charAt(i)!='+'&&s.charAt(i)!='-')
			{
				st=st+s.charAt(i);
			}
			else if (s.charAt(i)=='+')
			{
				mon=new Monom(st);
				st="";
				this._pol.add(mon);
			}
			else if (s.charAt(i)=='-')
			{
				mon=new Monom(st);
				st="-";
				this._pol.add(mon);
			}
		}
		mon=new Monom(st);
		this._pol.add(mon);
		polycorrect(this);
		Polysort();	
	}
	
	@Override
	public double f(double x)
	{
		double sum=0;
		if (this._pol==null)
		{
			throw new RuntimeException ("polynom isnt exist");
		}
		
	     for (int i=0;i<this._pol.size();i++)
	     { 
			sum=sum+this._pol.get(i).f(x);
		  }
		
		return sum;
	}

	@Override
	public void add(Polynom_able p1)
	{
		Iterator<Monom> iterator = p1.iteretor();
		int power;
		double coef;
		Monom m;
		while (iterator.hasNext())
		{

			m=iterator.next();
			power=m.get_power();
			coef=m.get_coefficient();
            this.add(new Monom(coef,power));	
		}		
	
	}

	@Override
	public void add(Monom m1)
	{
       polycorrect(this);
		boolean flag=false;
		int power=m1.get_power();
		double coef=m1.get_coefficient();
       for (int i=0;i<this._pol.size();i++)
       {
    	   if (power==this._pol.get(i).get_power())
    	   {
    		   flag=true;
    		   coef=coef+this._pol.get(i).get_coefficient();
    		   if (coef!=0)
    		   {
    		   this._pol.set(i,new Monom (coef,power));
    		   }
    		   else
    		   {
    			   this._pol.set(i,new Monom (0,0));
    		   }
    	   }
       }
       if (flag==false)
       {
    	   this._pol.add(m1);
       }

       polycorrect(this);
       Polysort();
	}

	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> iterator = p1.iteretor();
		int power;
		double coef;
		Monom m;
		while (iterator.hasNext())
		{

			m=iterator.next();
			power=m.get_power();
			coef=m.get_coefficient();
            this.add(new Monom(-coef,power));	
		}
		polycorrect(this);
		Polysort();	
	}

	@Override
	public void multiply(Polynom_able p1) 
	{
		  Iterator<Monom> iterator=p1.iteretor();
		  Monom m,m1;
		  Polynom p=new Polynom();
		  int power,Npower;
		  double coef,Ncoef;
		  while (iterator.hasNext())
		  {
			  m=iterator.next();
			 power=m.get_power(); 
			 coef=m.get_coefficient();
		     for (int i=0;i<this._pol.size();i++)
		     {
		    	 Npower=power+this._pol.get(i).get_power();
		    	 Ncoef=coef*this._pol.get(i).get_coefficient();
		    	 m1=new Monom(Ncoef,Npower);
		    	 p._pol.add(m1);
		     }
		  }
		  this._pol=p._pol;
		  if (p._pol.isEmpty()==false&&p._pol.get(0).get_coefficient()==0)
		  {
		  this._pol.remove(0);
		  }
		  polycorrect(this);
		  Polysort();	
	}

	private void polycorrect (Polynom p1)
	{
		Polysort();
		Polynom p=new Polynom();
		p._pol.remove(0);
		int power;
		double Ncoef=0;
		int counter=0;
		
        for (int i=0;i<this._pol.size();i++)
        {
        	power=this._pol.get(i).get_power();
        	Ncoef=this._pol.get(i).get_coefficient();
        	for (int j=i+1;j<this._pol.size();j++)
        	{
        		if(power==this._pol.get(j).get_power())
        		{
        			Ncoef=Ncoef+this._pol.get(j).get_coefficient();
        			this._pol.set(j,new Monom("0"));
        		} 		
        	}
        	if (Ncoef!=0)
        	{
        	p._pol.add(new Monom(Ncoef,power));
        	}
        }
        this._pol=p._pol;
		if (this._pol.isEmpty())
		{
			this._pol.add(new Monom("0"));
		}
       
		Polysort();	
}
		
	@Override
	public boolean equals(Polynom_able p1)
	{
		Iterator<Monom> iterator=p1.iteretor();
		int i=0;
		if (iterator.hasNext()==false||this._pol.isEmpty())
			return false;
	      while (iterator.hasNext())
	      {
	    	  if(i==this._pol.size())
	    		  return false;
				if (this._pol.get(i).equals(iterator.next())==false)
				{
					return false;
				}
				i++;
		   }
	      if (this._pol.size()!=i)
	    	  return false;
			
	      return true;
	}

	@Override
	public boolean isZero() 
	{
	      boolean flag=true;
	    	if (this._pol==null)
	  	{
	  		throw new RuntimeException ("polynom isnt exist");
	  	}
	        for (int i=0;i<this._pol.size();i++)
	        {
	      	  if (this._pol.get(i).get_coefficient()!=0)
	      	  {
	      		  flag=false;
	      	  }
	        }
	        return flag;
	}

	@Override
	public double root(double x0, double x1, double eps)
	{
		double t=(x0+x1)/2;
		if(f(x0)*f(x1)>0)	{
			throw new RuntimeException("The function root must have one positive value and one negative value.");
		}
		while(Math.abs(f(t))>eps) {
			if(f(t)>0) {
				if(f(x0)>0)x0=t;
				else x1=t;
			}
			else {
				if(f(x1)<0)x1=t;
				else x0=t;
			}
			t=(x0+x1)/2;
		}
		return t;
	}

	@Override
	public Polynom_able copy()
	{
		Polynom p=new Polynom();
		
	       for (int i=0;i<this._pol.size();i++)
	       {
	    	  p._pol.add(this._pol.get(i));
	       }
	       p._pol.remove(0);
	       return p;
	}

	@Override
	public Polynom_able derivative() 
	{
		Polynom p=new Polynom();
		p._pol=this._pol;
		int power=0;
		double coef;
		if (this._pol.size()==1&&this._pol.get(0).get_power()==0)
		{
			p=new Polynom("0");return p;
		}
		for (int i=0;i<this._pol.size();i++)
		{
		  if (this._pol.get(i).get_power()!=0)
		  {
	      power=this._pol.get(i).get_power()-1; 
		  }

	      coef=this._pol.get(i).get_coefficient()*this._pol.get(i).get_power();
          if (coef!=0)
          {
	      p._pol.set(i,new Monom(coef,power));
          }
          else if (i!=0)
          {
        	  p._pol.remove(i);
          }
		}
		
		
		return p;
	}
		
	@Override
    public double area(double x0, double x1, double eps)
	{
        double sum=0;   
        double h,x=x0;;
        for (double i=x0;i<=x1;i+=eps)
        {
        	h=f(i);
        	sum=sum+h*eps;     	
        }
        
        return sum;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return _pol.iterator();
	}
	
	@Override
	public void multiply(Monom m1)
	{
		int power,Npower;
		double coef,Ncoef;
		Polynom p=new Polynom();
		power=m1.get_power();
		coef=m1.get_coefficient();
		for (int i = 0; i <this._pol.size(); i++)
		{
		Ncoef=coef*this._pol.get(i).get_coefficient();
		Npower=power+this._pol.get(i).get_power();
	    p._pol.add(new Monom (Ncoef,Npower));
		}
		p._pol.remove(0);
		this._pol=p._pol;
		Polysort();	
			
	}
	
	public String tostring ()
	{
		String a="";
		for (int i=0;i<this._pol.size();i++)
		{
			if (i!=this._pol.size()-1&&this._pol.get(i+1).get_coefficient()<0)
				a=a+""+this._pol.get(i).toString()+"";
			else if (i==this._pol.size()-1)
				a=a+""+this._pol.get(i).toString()+"";
			else
			a=a+""+this._pol.get(i).toString()+"+";
		}
		return a;
	}

	private void Polysort ()
	{
		Collections.sort(this._pol, new Monom_Comperator()); 
	}
}
