package myMath;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PolynomTestJunit {

	double eps=0.00001;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	
    @Test	
    public void PolynomZeroTest() {
		
		Polynom p=new Polynom();
		boolean ans=true;
		for (int i=0;i<p._pol.size();i++)
		{
			if (p._pol.get(i).get_coefficient()!=0)
				ans=false;	
		}
		assertEquals(true,ans);
	}
 	
	@Test 
 	public void addPTest() {

	//1:
	Polynom p1 = new Polynom("4x^2+3x^3");
	Polynom p2 = new Polynom("2x^2+3x");
	Polynom expected = new Polynom("3x^3+6x^2+3x");
	p1.add(p2);
	assertEquals(p1.tostring(), expected.tostring());
    //2:
	 p1 = new Polynom("-7x^2+x-1");
	 p2 = new Polynom("6x^2");
	 expected = new Polynom("-x^2+x-1");
	 p1.add(p2);
	 assertEquals(p1.tostring(), expected.tostring());
	 //3:
     p1 = new Polynom("-7x^2-5");
	 p2 = new Polynom("0");
	 expected = new Polynom("-7x^2-5");
	 p1.add(p2);
	 assertEquals(p1.tostring(), expected.tostring());
	 //4:
     p1 = new Polynom("-7x^2+5x");
	 p2 = new Polynom("7x^2-3x+5");
	 expected = new Polynom("2x+5");
	 p1.add(p2);
	 assertEquals(p1.tostring(), expected.tostring());
	
}
 	
	@Test
    public void addMTest() {

	//1:
	Polynom p1 = new Polynom("4x^2+5x");
	Monom m = new Monom("2x^2");
	Polynom expected = new Polynom("6x^2+5x");
	p1.add(m);
	assertEquals(p1.tostring(), expected.tostring());
	//2:
	p1 = new Polynom("4x^2-7x");
	m = new Monom("5");
	expected = new Polynom("4x^2-7x+5");
	p1.add(m);
	assertEquals(p1.tostring(), expected.tostring());
	//3:
	p1 = new Polynom("4x^2-7x");
	m = new Monom("7x");
	expected = new Polynom("4x^2");
	p1.add(m);
	assertEquals(p1.tostring(), expected.tostring());
	
}
  
	@Test  
 	public void Ftest ()
 	{
    	//1:
 		Polynom p=new Polynom ("5x^3+3x^2+7x");
 		double expected=15;
 		assertEquals(expected,p.f(1),eps);
 		//2:
 		p=new Polynom ("0");
 	    expected=0;
 		assertEquals(expected,p.f(6.5),eps);
 		//3:
 		p=new Polynom ("6x-13");
 		expected=-4;
 		assertEquals(expected,p.f(1.5),eps);
 		
 	}
 	
	@Test
    public void SubPTest() {

	//1:
	Polynom p1 = new Polynom("4x^2");
	Polynom p2 = new Polynom("2x^2");
	Polynom expected = new Polynom("2x^2");
	p1.substract(p2);
	assertEquals(p1.tostring(), expected.tostring());
   //2:
	 p1 = new Polynom("-7x^2");
	 p2 = new Polynom("5x^2");
	 expected = new Polynom("-12x^2");
	 p1.substract(p2);
	 assertEquals(p1.tostring(), expected.tostring());
	 //3:
    p1 = new Polynom("-7x^2");
	 p2 = new Polynom("0");
	 expected = new Polynom("-7x^2");
	 p1.substract(p2);
	 assertEquals(p1.tostring(), expected.tostring());
	 //4:
    p1 = new Polynom("-7x^2+5x");
	 p2 = new Polynom("-7x^2-3x+5");
	 expected = new Polynom("8x-5");
	 p1.substract(p2);
	 assertEquals(p1.tostring(), expected.tostring());
	
}	
 
	@Test
 	public void MultiplyTest()
 	{
 		//1:
 		Polynom p1 = new Polynom("x+2");
 		Polynom p2 = new Polynom("1+x");
 		Polynom expected = new Polynom("x^2+3x+2");
 		p1.multiply(p2);
 		assertEquals(p1.tostring(), expected.tostring());
 	    //2:
 		 p1 = new Polynom("x^2+1");
 		 p2 = new Polynom("x");
 		 expected = new Polynom("x^3+x");
 		 p1.multiply(p2);
 		 assertEquals(p1.tostring(), expected.tostring());
 		 //3:
 	     p1 = new Polynom("x^2+x+1");
 		 p2 = new Polynom("x-3");
 		 expected = new Polynom("x^3-2x^2-2x-3");
 		 p1.multiply(p2);
 		 assertEquals(p1.tostring(), expected.tostring());
 		 //4:
 	     p1 = new Polynom("-1x");
 		 p2 = new Polynom("-x");
 		 expected = new Polynom("x^2");
 		 p1.multiply(p2);
 		 assertEquals(p1.tostring(), expected.tostring());
 		 //5:
 		p1 = new Polynom("x+3");
		Monom m1 = new Monom("-x");
		 expected = new Polynom("-x^2-3x");
		 p1.multiply(p2);
		 assertEquals(p1.tostring(), expected.tostring());
		 //6:
		 p1 = new Polynom("x^2+5x-2");
		 m1 = new Monom("-x");
	    expected = new Polynom("-x^3-5x^2+2x");
		 p1.multiply(p2);
		 assertEquals(p1.tostring(), expected.tostring());
 		
 	}

	@Test
 	public void testEquals()
    {
	   //1:
    	Polynom p1=new Polynom("x^2");
    	Polynom p2=new Polynom ("1x^2");
    	boolean ans=p1.equals(p2);
    	assertTrue(ans);
    	//2:
    	p1=new Polynom("x^2+1");
    	p2=new Polynom("1+x^2");
    	ans=p1.equals(p2);
    	assertTrue(ans);
    	//3:
    	p1=new Polynom("x^2+0");
    	p2=new Polynom("x^2");
    	ans=p1.equals(p2);
    	assertTrue(ans);
    	//4:
    	p1=new Polynom("-x^2+x^3-0");
    	p2=new Polynom("x^3-x^2");
    	ans=p1.equals(p2);
    	assertTrue(ans);
    }

   @Test
   public void isZerotest()
    {
     Polynom p1=new Polynom("0");
     boolean ans=p1.isZero();
     assertTrue(ans);
    }
 
   @Test
   public void copyTest()
   {
	   Polynom p1=new Polynom("x^3-5x+7");
	   Polynom_able p2=p1.copy();;   
	   Polynom expected=new Polynom("x^3-5x+7");
		Iterator<Monom> it=p2.iteretor();
		for (int i=0;i<expected._pol.size();i++)
		{
			Monom m=it.next();
			assertEquals(expected._pol.get(i).get_power(),m.get_power());
			assertEquals(expected._pol.get(i).get_coefficient(),m.get_coefficient(),eps);
		}
   }
   
   @Test 
   public void TestDerivative()
   {
	   //1:
	Polynom p1=new Polynom("5");
	Polynom expected=new Polynom("0");
	Polynom_able p2=p1.derivative();
	Iterator<Monom> it=p2.iteretor();
	for (int i=0;i<expected._pol.size();i++)
	{
		Monom m=it.next();
		assertEquals(expected._pol.get(i).get_power(),m.get_power());
		assertEquals(expected._pol.get(i).get_coefficient(),m.get_coefficient(),eps);
	}
	//2:
	p1=new Polynom("x^2+5x-7");
	expected=new Polynom("2x+5");
	p2=p1.derivative();
	it=p2.iteretor();
	for (int i=0;i<expected._pol.size();i++)
	{
		Monom m=it.next();
		assertEquals(expected._pol.get(i).get_power(),m.get_power());
		assertEquals(expected._pol.get(i).get_coefficient(),m.get_coefficient(),eps);
	}
	
   }

   @Test
   public void TestArea()
   {
	   //1:
	   Polynom p1=new Polynom("3x^2");
	   double expected=26;
	   assertTrue(p1.area(1,3,eps)> expected-0.2);
	   assertTrue(p1.area(1,3,eps)< expected+0.2);
	   //2:
	  p1= new Polynom("2x+1");
	  expected=2;
	  assertTrue(p1.area(0,1,eps)> expected-0.2);
	   assertTrue(p1.area(0,1,eps)< expected+0.2);
	   
   }
   
   @Test
   public void TestRoot()
   {
	   //1:
	   Polynom p1=new Polynom("3x-3");
	   double expected=1;
	   assertTrue(p1.root(-3,4,eps)> expected-0.2);
	   assertTrue(p1.root(-3,4,eps)< expected+0.2);
	   //2:
	  p1= new Polynom("x^2-16");
	  expected=4;
	  assertTrue(p1.root(1,55,eps)> expected-0.2);
	   assertTrue(p1.root(1,55,eps)< expected+0.2);
	   
   }
   
   @Test
   public void TestToString ()
   {
	   Polynom Expected=new Polynom("x^2-5x+0.5");
	   Polynom Actual=new Polynom(Expected.tostring());
	   for (int i=0;i<Expected._pol.size();i++)
	   {
		   assertEquals( Expected._pol.get(i).get_power(),Actual._pol.get(i).get_power());
	       assertEquals(Expected._pol.get(i).get_coefficient(),Actual._pol.get(i).get_coefficient(),eps);
	   }
   }
   
   @Test
   public void iteretor() {
	  Polynom p=new Polynom ("x^2-5x+7");
       Iterator<Monom> it = p.iteretor();
       int i = 0;

       while (it.hasNext()) {

           Monom monom =  it.next();
           Monom expected = p._pol.get(i);
           i++;

           switch (i) {
               case 0:
                   assertTrue(monom.equals(expected));
                   break;
               case 1:
                   assertTrue(monom.equals(expected));
                   break;
               case 2:
                   assertTrue(monom.equals(expected));
                   break;
               default:
                   break;
           }
       }
   }
   
   @Test
	public void validInputsTest() {
		//#1:
		Polynom p1 = new Polynom("18+5");
		assertEquals(p1.tostring(),"23.0");

		//#2:
		p1 = new Polynom("-1x^1");
		assertEquals(p1.tostring(),"-1.0x");

		//#3:
		p1 = new Polynom("5x^2-3x");
		assertEquals(p1.tostring(), "5.0x^2-3.0x");

	}
   
	@Test
	public void failedInputsTest() {
	
			//#1:
			try {
		    Polynom	p1 = new Polynom("H+56");
			p1 = new Polynom("2x^-4");
			p1 = new Polynom("@%");
			p1 = new Polynom("2x^0.4");
			}
			catch(RuntimeException e)
			{
				System.out.println("the message of error was shown");
			
	        }
       }
	
}
