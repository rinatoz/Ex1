package Ex1Testing;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Ex1.Monom;

public class MonomTestJunit {
     double eps =0.00001;
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
	public void testGetpower ()
	{
		Monom m=new Monom (1,1);
		assertEquals(1,m.get_power());
		if(m.get_power() != 1) 
			fail("uncorrect implemented");
	}
	
	@Test
	public void testGetcoef()
	{
		Monom m=new Monom (1,1);
		assertEquals(1,m.get_coefficient(),eps);
		if(m.get_coefficient() != 1) 
			fail("uncorrect implemented");
	}

	@Test
	public void testStringMonom()
	{
		Monom m= new Monom ("3x^2");
		Monom m1= new Monom (3,2);
		assertEquals(m.get_coefficient(),m1.get_coefficient(),eps);
		assertEquals(m.get_power(),m1.get_power()); 	
		if(m.get_coefficient() !=  m1.get_coefficient())
			fail("the String constructor is not working ,the coefficient values arent equal");
		else if(m.get_power() != m1.get_power()) 
			fail("the String constructor is not working ,the power values arent equal");
	}
	
	@Test
	public void testMonomMonom()
	{
		Monom m= new Monom (3,2);
		Monom m1= new Monom (m);
		assertEquals(m.get_coefficient(),m1.get_coefficient(),eps);
		assertEquals(m.get_power(),m1.get_power()); 	
		if(m.get_coefficient() !=  m1.get_coefficient())
			fail("the copy constructor is not working , the coefficient values arent the same");
		else if(m.get_power() != m1.get_power()) 
			fail("the copy constructor is not working , the power values arent the same");
	}

	@Test
	public void testF()
	{
		Monom m= new Monom (3,2);
		double x=1;
		double ans=3;
		double ans1=m.f(x);
		if (ans!=ans1)
			fail("this function uncorrect-check the action that put some x at the Monom");
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
		if(m.get_coefficient() !=  ans.get_coefficient())
			fail("the coefficient values arent equal, check the action at the function which add between the coeffiecnts");
		else if(m.get_power() != ans.get_power()) 
			fail("the String constructor is not working ,check if the function keep the power without changing");
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
		if(m.get_coefficient() !=  ans.get_coefficient())
			fail("the coefficient values arent equal, check the action at the function which multipy between the coeffiecnts");
		else if(m.get_power() != ans.get_power()) 
			fail("the String constructor is not working ,check the action at the function which add between the powers");   
	}
	
	@Test
	public void testDerivative() {
		Monom m=new Monom (2,3);
		Monom m1=new Monom (6,2);
		Monom m2=m.derivative();
        assertEquals( m1.get_power(),m2.get_power());
         assertEquals(m1.get_coefficient(),m2.get_coefficient(),eps);
		if(m1.get_coefficient() != m2.get_coefficient())
			fail("the Derivative function is not working ,the coefficient value of actual_Monom isn't the same as excepted_coefficient ");
		if(m1.get_power() != m2.get_power())
			fail("the Derivative function is not working ,the power value of actual_Monom isn't the same as excepted_power");
	}

	@Test
	public void testIs_zero() {
		Monom m = new Monom(0,2);
		boolean ans = m.isZero();
		assertEquals(true, ans);
		if(!ans) fail("The Is_Zero function is not working well, coefficient is not 0") ; 
		m=new Monom (1,1);
		ans = m.isZero();
		assertEquals(false, ans);
		if(ans==true) fail("The Is_Zero function is not working well, coefficient is not 0") ; 
	}
	
	@Test
	public void testToString ()
	{
		Monom m=new Monom (-1.5,3);
		Monom m1=new Monom (m.toString());
        assertEquals( m.get_power(),m1.get_power());
        assertEquals(m.get_coefficient(),m1.get_coefficient(),eps);
	}
	
	
	
}
