package Ex1Testing;
import static org.junit.Assert.*;
import org.junit.Test;

import Ex1.Monom;

public class MonomTestJunit {
     double eps =0.0001;
	@Test
	public void testGetpower ()
	{
		Monom m=new Monom (1,1);
		assertEquals(1,m.get_power());

		 m=new Monom (0,0);
		 assertEquals(0,m.get_power());
	 
	}
	
	@Test
	public void testGetcoef()
	{
		Monom m=new Monom (1,1);
		assertEquals(1,m.get_coefficient(),eps);
	}

	@Test
	public void testStringMonomUNCORRECT()
	{
	   try
	   {
		Monom m=new Monom ("EX1");
		m=new Monom ("2x^");
		m=new Monom ("x^0.4");
		m=new Monom ("$%$%#");
		m=new Monom (".");
		m=new Monom ("5x5");
	   }
	   catch (Exception E)
	   {
		System.out.println("***test string constructor***");
		System.out.println("catch the String which uncorrect at the constructor");
	   }
	   
	}
	
	@Test
public void testStringMonomCORRECT()
	{
		Monom m=new Monom ("0x^0");
		Monom m1=new Monom (0,0);
		assertEquals(m1.get_coefficient(),m.get_coefficient(),eps);
		assertEquals(m1.get_power(),m.get_power(),eps);
		m=new Monom ("2.5x");
		m1=new Monom (2.5,1);
		assertEquals(m1.get_coefficient(),m.get_coefficient(),eps);
		assertEquals(m1.get_power(),m.get_power(),eps);
		m=new Monom ("1x^1");
		 m1=new Monom (1,1);
		assertEquals(m1.get_coefficient(),m.get_coefficient(),eps);
		assertEquals(m1.get_power(),m.get_power(),eps);
		m=new Monom ("-1x^0");
		 m1=new Monom (-1,0);
		assertEquals(m1.get_coefficient(),m.get_coefficient(),eps);
		assertEquals(m1.get_power(),m.get_power(),eps);
		m=new Monom ("5x^0");
		 m1=new Monom (5,0);
		assertEquals(m1.get_coefficient(),m.get_coefficient(),eps);
		assertEquals(m1.get_power(),m.get_power(),eps);
	}
	
	@Test	
public void testMonomMonom()
	{
		Monom m= new Monom (3,2);
		Monom m1= new Monom (m);
		assertEquals(m.get_coefficient(),m1.get_coefficient(),eps);
		assertEquals(m.get_power(),m1.get_power()); 	

	    m= new Monom (0,0);
	    m1= new Monom (m);
		assertEquals(m.get_coefficient(),m1.get_coefficient(),eps);
		assertEquals(m.get_power(),m1.get_power()); 
		
		  m= new Monom ("-x");
		  m1= new Monom (m);
	      assertEquals(m.get_coefficient(),m1.get_coefficient(),eps);
		  assertEquals(m.get_power(),m1.get_power());
	}

	@Test
	public void testF()
	{
		Monom m= new Monom (3,2);
		double x=1;
		double ans=3;
		double ans1=m.f(x);
		assertEquals(ans,ans1,eps);	
		
		 m= new Monom (5,0);
		 x=1;
		 ans=5;
		 ans1=m.f(x);
		assertEquals(ans,ans1,eps);	
		
		 m= new Monom (3,3);
		 x=0;
		 ans=0;
		 ans1=m.f(x);
		assertEquals(ans,ans1,eps);	
	}
	
	@Test
	public void testAddMonom()
	{
		Monom m= new Monom (3,2);
		Monom m1= new Monom (4,2);
		Monom ans =new Monom (7,2);
        m.add(m1);
        assertEquals( m.get_power(),ans.get_power());
        assertEquals(ans.get_coefficient(),m.get_coefficient(),eps);
        
        m= new Monom (6,0);
		m1= new Monom (4,0);
	    ans =new Monom (10,0);
        m.add(m1);
        assertEquals( m.get_power(),ans.get_power());
        assertEquals(ans.get_coefficient(),m.get_coefficient(),eps);
        
        try
        {
        m= new Monom (6,5);
		m1= new Monom (4,2);
        m.add(m1);
        }
        catch (Exception E)
        {
        System.out.println("***test add-monom***");
        System.out.println("impossible to add beteen 2 monoms with diffrent power, and get monom");	
        }
	}
	
	@Test
	public void testMultiplyMonom()
	{
		Monom m= new Monom (3,2);
		Monom m1= new Monom (4,2);
		Monom ans =new Monom (12,4);
        m.multipy(m1);
        assertEquals( m.get_power(),ans.get_power());
        assertEquals(ans.get_coefficient(),m.get_coefficient(),eps);
        
    	 m= new Monom (1,5);
		 m1= new Monom (0,2);
		 ans =new Monom (0,0);
        m.multipy(m1);
        assertEquals( m.get_power(),ans.get_power());
        assertEquals(ans.get_coefficient(),m.get_coefficient(),eps);
        
        m= new Monom (1,4);
		m1= new Monom (1,0);
		ans =new Monom (1,4);
       m.multipy(m1);
       assertEquals( m.get_power(),ans.get_power());
       assertEquals(ans.get_coefficient(),m.get_coefficient(),eps);
	}
	
	@Test
	public void testDerivative() {
		Monom m=new Monom (2,3);
		Monom m1=new Monom (6,2);
		Monom m2=m.derivative();
        assertEquals( m1.get_power(),m2.get_power());
        assertEquals(m1.get_coefficient(),m2.get_coefficient(),eps);
        
    	m=new Monom (1,0);
	    m1=new Monom (0,0);
		m2=m.derivative();
        assertEquals( m1.get_power(),m2.get_power());
        assertEquals(m1.get_coefficient(),m2.get_coefficient(),eps);
        
    	m=new Monom (2.5,2);
	    m1=new Monom (5,1);
		m2=m.derivative();
        assertEquals( m1.get_power(),m2.get_power());
        assertEquals(m1.get_coefficient(),m2.get_coefficient(),eps);
        
    	m=new Monom (3.333,1);
	    m1=new Monom (3.333,0);
		m2=m.derivative();
        assertEquals( m1.get_power(),m2.get_power());
        assertEquals(m1.get_coefficient(),m2.get_coefficient(),eps);
	}

	@Test
	public void testIs_zero() {
		Monom m = new Monom(0,2);
		boolean ans = m.isZero();
		assertEquals(true, ans);

		m=new Monom (1,1);
		ans = m.isZero();
		assertEquals(false, ans);

		m=new Monom (5,0);
		m=m.derivative();
		ans = m.isZero();
		assertEquals(true, ans);
	}
	
	@Test
	public void testToString ()
	{
		Monom m=new Monom (-1.5,3);
		Monom m1=new Monom (m.toString());
        assertEquals( m.get_power(),m1.get_power());
        assertEquals(m.get_coefficient(),m1.get_coefficient(),eps);
        
        m=new Monom (1,0);
		m1=new Monom (m.toString());
        assertEquals( m.get_power(),m1.get_power());
        assertEquals(m.get_coefficient(),m1.get_coefficient(),eps);
        
        m=new Monom (0,1);
      	m1=new Monom (m.toString());
        assertEquals( m.get_power(),m1.get_power());
        assertEquals(m.get_coefficient(),m1.get_coefficient(),eps);
	}
	
	
	
}
