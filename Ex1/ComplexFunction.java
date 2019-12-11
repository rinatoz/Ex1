package Ex1;


public class ComplexFunction implements complex_function {

	function right;
    function left;
     Operation op;
     
     public ComplexFunction ()
     {
    	 this.op=Operation.None;
     	this.left=new Polynom("0");
    	this.right=new Polynom ("0");	
     }
     
     public ComplexFunction (String s,function left,function right)
     {
    	 switch (s)
    	 {
    	 case "plus": this.op=Operation.Plus; break;
    	 case "div": this.op=Operation.Divid; break;
    	 case "mul": this.op=Operation.Times; break;
    	 case "max": this.op=Operation.Max; break;
    	 case "min": this.op=Operation.Min; break;
    	 case "comp": this.op=Operation.Comp;break;
    	 case "none": this.op=Operation.None;break;
         default: throw new RuntimeException ("uncorrect Operation");
    	 }
    	 this.left=left;
    	 this.right=right;
    		if (!(this.right.toString().equals("0.0"))&&this.op.toString().equals("None"))
        		throw new RuntimeException ("complex function can't accept 2 functions with none operation");
     }
 	public ComplexFunction(function f) {
		this.left = f;
		this.right = new Polynom("0");
		this.op = Operation.None;
	}
     public ComplexFunction (Operation op,function left,function right)
     {
    	 this.op=op;
     	this.left=left;
    	this.right=right;
    	if (!(this.right.toString().equals("0.0"))&&this.op.toString().equals("None"))
    		throw new RuntimeException ("complex function can't accept 2 functions with none operation");
     }
     
     public ComplexFunction (String s)
     {
    	 try
    	 {
    	 boolean flag=false;
    	try
    	{
    		if (s.substring(0,6).equals("f(x)= "))
				s=s.substring(6);
    		else
    		if (s.substring(0,5).equals("f(x)="))
    			s=s.substring(5);

    	}
    	catch(Exception e) {}
 		try {   //first check,simple polynom at the string
			this.left = new Polynom(s);
			this.right = new Polynom("0");
			op = Operation.None;
			return;
		} 
 		catch (Exception e) {}     //check the operation at the String
 	 	    if (s.substring(0,3).equals("max"))  
 	 	    {
 	          this.op=Operation.Max;
 	          s=s.substring(3);
 	 	    }
 	 	    else if(s.substring(0,3).equals("min"))
 	 	    {
 	 		  this.op=Operation.Min;
 	 		  s=s.substring(3);
 	 	    }
 	 	    else if (s.substring(0,4).equals("plus"))
 	 	    {
 	 	    	this.op=Operation.Plus;
 	 	    	s=s.substring(4);
 	 	    }
 	 	    else if (s.substring(0,4).equals("comp"))
 	 	    {
 	 	    	this.op=Operation.Comp;
 	 	    	s=s.substring(4);
 	 	    }
 	 	    else if (s.substring(0,3).equals("mul"))
 	 	    {
 	 	    	this.op=Operation.Times;
 	 	    	s=s.substring(3);
 	 	    }
 	 	   else if (s.substring(0,3).equals("div"))
 		    {
 		    	this.op=Operation.Divid;
 		    	s=s.substring(3);
 		    }
 	 	   else if (s.substring(0,4).equals("none"))
		    {
		    	this.op=Operation.None;
		    	s=s.substring(4);
		    	 flag=true;
		    }
 	 	   else
 	 	   {
 	 		  throw new RuntimeException ("uncorrect pattern into string"); 
 	 	   }
 	   	 
			s = s.substring(1, s.length()-1); //the rest of the string
			int spaces = countspace(s);
			if(spaces==0)       //error  
			throw new RuntimeException("uncorrect pattern,miss ','");
			int i = retIndex(s);
			if(spaces==1) //stop condition of the recourstion.
			{  				
				this.left = new Polynom(s.substring(0, i));
				this.right = new Polynom(s.substring(i+1, s.length()));
				if (flag==true&&!(this.right.toString().equals("0.0")))
					throw new RuntimeException ("complex function can't accept 2 functions with none operation");
			}
			else
			{
				try {
					this.left = new Polynom(s.substring(0, i)); //if the left side until the ',' is a polynom String.
				    } 
				catch (Exception e) 
				{
					this.left = new ComplexFunction(s.substring(0, i)); ////if the left side until the ',' is a ComplexFunction String.
				}
				try
				{
					this.right = new Polynom(s.substring(i+1, s.length())); ////if the right side until the ')' is a polynom String.
				} 
				catch (Exception e) 
				{
					this.right = new ComplexFunction(s.substring(i+1, s.length())); ///////if the right side until the ')' is a ComplexFunction String.
				}
			}
			
			if ((!(this.right.toString().equals("0.0")))&&this.op.toString().equals("None")) //error check
				throw new RuntimeException ("complex function can't accept 2 functions with none operation");
    	 }
    	 catch (Exception e)
    	 {
    		 throw new RuntimeException ("ERROR! check the string and fix problems");
    	 }
     }
 
     public String toString()
     {
 		String s=toStringREC();
         s=s.replaceAll("Times","mul");
         s=s.replaceAll("Divid","div");
         s=s.replace('M','m');
         s=s.replace('C','c');
         s=s.replace('N', 'n');
         s=s.replace('P','p');
          return s;

 	 }

    public void plus(function f1)
	{

   if (!(f1.toString().equals("0.0")))
   {
       if (this.left.toString().equals("0.0") && this.op.toString().equals("None")) 
       {
    	   this.left=f1;
       }
       else
       if (this.right.toString().equals("0.0") && this.op.toString().equals("None"))
    	   this.right=f1;
       else
       {
    	ComplexFunction comp=new ComplexFunction (this.op,this.left,this.right);
   		this.left=comp;
   		this.right=f1;
       }
    	   
  	    this.op=Operation.Plus; 
   }
	}

	/** Multiply this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1)
	{

		    if(!(f1.toString().equals("1.0"))) //the number "1" have no influence at multiply
		   {
		       if (this.left.toString().equals("0.0") && this.op.toString().equals("None")) 
		       {
		    	   this.left=f1;
		       }
		       else
		       if (this.right.toString().equals("0.0") && this.op.toString().equals("None"))
		    	   this.right=f1;
		       else
		       {
		    	ComplexFunction comp=new ComplexFunction (this.op,this.left,this.right);
		   		this.left=comp;
		   		this.right=f1;
		       }
		    	   
		  	    this.op=Operation.Times; 
		   }
		  
	}
	/** Divides this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
	public void div(function f1)
	{
		   if ((f1.toString().equals("0.0")))
		   {
			   throw new RuntimeException ("Error!!! cant divid by zero"); 
		   }
		   else if(!(f1.toString().equals("1.0")))
		   {
		       if (this.left.toString().equals("0.0") && this.op.toString().equals("None")) 
		       {
		    	   this.left=this.right;
		    	   this.right=f1;
		       }
		       else
		       if (this.right.toString().equals("0.0") && this.op.toString().equals("None"))
		    	   this.right=f1;
		       else
		       {
		    	ComplexFunction comp=new ComplexFunction (this.op,this.left,this.right);
		   		this.left=comp;
		   		this.right=f1;
		       }
		    	   
		  	    this.op=Operation.Divid; 
		   }
		  
	    
	}
	/** Computes the maximum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1)
	{
		 if (this.left.toString().equals("0.0") && this.op.toString().equals("None")) 
	       {
	    	   this.left=f1;
	       }
	       else
	       if (this.right.toString().equals("0.0") && this.op.toString().equals("None"))
	    	   this.right=f1;
	       else
	       {
	    	ComplexFunction comp=new ComplexFunction (this.op,this.left,this.right);
	   		this.left=comp;
	   		this.right=f1;
	       }
	    	   
	  	    this.op=Operation.Max;
	}
	/** Computes the minimum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1)
	{
		 if (this.left.toString().equals("0.0") && this.op.toString().equals("None")) 
	       {
	    	   this.left=f1;
	       }
	       else
	       if (this.right.toString().equals("0.0") && this.op.toString().equals("None"))
	    	   this.right=f1;
	       else
	       {
	    	ComplexFunction comp=new ComplexFunction (this.op,this.left,this.right);
	   		this.left=comp;
	   		this.right=f1;
	       }
	    	   
	  	    this.op=Operation.Min;
	}
	/**
	 * This method wrap the f1 complex_function with this function: this.f(f1(x))
	 * @param f1 complex function
	 */
	public void comp(function f1)
	{
		if (this.left==null)
			throw new RuntimeException ("this function must be exist");
		
		 if (this.left.toString().equals("0.0") && this.op.toString().equals("None")) 
	       {
	    	   this.left=this.right;
	    	   this.right=f1;
	       }
	       else if (this.right.toString().equals("0.0") && this.op.toString().equals("None"))
	    	   this.right=f1;
	       else
	       {
	    	ComplexFunction comp=new ComplexFunction (this.op,this.left,this.right);
	   		this.left=comp;
	   		this.right=f1;
	       }
	    	   
	  	    this.op=Operation.Comp;
	}
	/** returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function left()
	{
		if (this.left==null)
			throw new RuntimeException ("this Complex function have a function which has null pointer) ");
		return this.left;
	}
	/** returns the right side of the complex function - this side might not exists (aka equals null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function right()
	{
		if (this.right==null)
			throw new RuntimeException ("this Complex function have a function which has null pointer) ");
		return this.right;
	}
	/**
	 * The complex_function oparation: plus, mul, div, max, min, comp
	 * @return
	 */
	public Operation getOp()
	{
		return this.op;
	}

	@Override
	public double f(double x)
	{
	  if (this.op==Operation.None)
		{
			return this.left.f(x);
		}
	else if (this.op==Operation.Plus)
	     {
		    return this.left.f(x)+this.right.f(x);
	     } 
	else if (this.op==Operation.Times)
	     {
		    return this.left.f(x)*this.right.f(x);
	     }
	else if (this.op==Operation.Comp)  //f(g(x))
	    {
		    double temp1=this.right.f(x);
		    return this.left.f(temp1);
	    }
    else if (this.op==Operation.Divid)
	    {
		  if (this.right.f(x)==0)
		  {
			throw new RuntimeException ("ERROR! impossible to divid with zero");
		  }
	      else
			return this.left.f(x)/this.right.f(x);
	    }
    else if (this.op==Operation.Max)
	    {
		    return Math.max(this.left.f(x),this.right.f(x));
	    }
	else if (this.op==Operation.Min)
	   {
		    return Math.min(this.left.f(x),this.right.f(x));
	   }
	else if (this.op==Operation.Error)
	{
		throw new RuntimeException ("the operation is ERROR,cant solve this function as x=" +x);
	}
	       return 0;
	  }

	@Override
	public function initFromString(String s)
	{
        function comp=new ComplexFunction (s);
        return comp;	
	}

	@Override
	public function copy() 
	{
		function f=new ComplexFunction (this.toString());
		return f;
	}
	
    private int retIndex(String s) {   //for the String constructor.
		int final_= 0;
		int Opencount = 0;
		int Closecount = 0;
		if(s.charAt(0) =='m'||s.charAt(0)== 'p'||s.charAt(0)== 'd'||s.charAt(0) == 'c'||s.charAt(0) == 'n') {
			while(s.charAt(0)!='(') {
				s = s.substring(1);
				final_++;
			}
			Opencount = 1;
			s = s.substring(1);
			final_++;
			while(Opencount!=Closecount) {
				if(s.charAt(0)=='(')
					Opencount++;
				if(s.charAt(0)==')')
					Closecount++;
				s = s.substring(1);
				final_++;
			}

		}
		else {
			while(s.charAt(0)!=',') {
				s = s.substring(1);
				final_++;
			}
		}
		return final_;
	}

    private int countspace(String s)    //for the String constructor.
    {
        int count = 0;

        for(int i=0; i < s.length(); i++)
        {    if(s.charAt(i) == ',')
                count++;
        }

        return count;
    }

    private String toStringREC() {
		return op.toString()+"("+this.left.toString()+","+this.right.toString()+")";
	}

	public boolean equals(Object obj) {
		
		if(obj instanceof ComplexFunction) {
			ComplexFunction f = (ComplexFunction)obj;
			if (this.left.equals(f.left) && this.right.equals(f.right) && op.toString().equals(f.op.toString()))
                 return true;
			
			if (this.left.equals(f.right) && this.right.equals(f.left) && op.toString().equals(f.op.toString()))
			{
				if( op.toString().equals("Plus")||op.toString().equals("Times"))
					return true;
			}
			
			if (!(this.left instanceof ComplexFunction) && !(this.right instanceof ComplexFunction))
			{
				if (!(f.left instanceof ComplexFunction) && !(f.right instanceof ComplexFunction))
				{
                  if (this.op.toString().equals("Plus"))
                  {
					Polynom p=new Polynom (this.left.toString());
					Polynom p1=new Polynom (this.right.toString());
					p.add(p1);
					Polynom p2=new Polynom (f.left.toString());
					Polynom p3=new Polynom (f.right.toString());
					p2.add(p3);
					if (p.toString().equals(p2.toString()))
						return true;
                  }
                  if (this.op.toString().equals("Times"))
                  {
					Polynom p=new Polynom (this.left.toString());
					Polynom p1=new Polynom (this.right.toString());
					p.multiply(p1);
					Polynom p2=new Polynom (f.left.toString());
					Polynom p3=new Polynom (f.right.toString());
					p2.multiply(p3);
					if (p.toString().equals(p2.toString()))
						return true;
                  }
				}
			}
		} 
		else if (obj instanceof Polynom || obj instanceof Monom)
		{
			Polynom objP=new Polynom (obj.toString());
			if (!(this.left instanceof ComplexFunction) && !(this.right instanceof ComplexFunction))
			{
				  if (this.op.toString().equals("Plus"))
                  {
					Polynom p=new Polynom (this.left.toString());
					Polynom p1=new Polynom (this.right.toString());
					p.add(p1);
					if (p.toString().equals(objP.toString()))
						return true;
                  }
                  if (this.op.toString().equals("Times"))
                  {
					Polynom p=new Polynom (this.left.toString());
					Polynom p1=new Polynom (this.right.toString());
					p.multiply(p1);
					if (p.toString().equals(objP.toString()))
						return true;
                  }
                  if (this.op.toString().equals("None"))
                  {
					Polynom p=new Polynom (this.left.toString());
					if (p.toString().equals(objP.toString()))
						return true;
                  }

			}
		}
		return false;
	}
}
