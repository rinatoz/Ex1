package Ex1Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Polynom_able;
import Ex1.function;

public class ComplexFuncJunitTest {

	@Test
	public void testDipultiveConstructor() 
	{
	ComplexFunction comp=new ComplexFunction();
	ComplexFunction expected=new ComplexFunction(Operation.None,new Polynom ("0"),new Polynom("0"));
	assertEquals(comp.getOp(),expected.getOp());
	assertEquals(comp.left(),expected.left());
	assertEquals(comp.right(),expected.right());
	}
	@Test
	public void testConstructor3parameters() 
	{
	ComplexFunction comp=new ComplexFunction(Operation.Comp,new Polynom("x"),new Polynom ("x^2-5"));
	assertEquals(comp.getOp(),Operation.Comp);
	assertTrue(comp.left().equals(new Polynom("x")));
	assertTrue(comp.right().equals(new Polynom("x^2-5")));
	}
	@Test
	public void testConstructorString() 
	{
	//1:
	ComplexFunction comp=new ComplexFunction("mul(x,x-5)");
	assertEquals(comp.getOp(),Operation.Times);
	assertTrue(comp.left().equals(new Polynom("x")));
	assertTrue(comp.right().equals(new Polynom("x-5")));
	//2:
	comp=new ComplexFunction("div(x   +2x +3,x^   2-5)");
	assertEquals(comp.getOp(),Operation.Divid);
	assertTrue(comp.left().equals(new Polynom("3x+3")));
	assertTrue(comp.right().equals(new Polynom("x^2-5")));
	//3:
	comp=new ComplexFunction("div(x,none(0,0))");
	assertEquals(comp.getOp(),Operation.Divid);
	assertTrue(comp.left().equals(new Polynom("x")));
	assertTrue(comp.right().equals(new ComplexFunction()));

	ComplexFunction c=new ComplexFunction("f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)");
	c=new ComplexFunction("f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)");
	c=new ComplexFunction("f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)");
	c=new ComplexFunction("-1.0x^4 +2.4x^2 +3.1");
	c=new ComplexFunction("f(x)= +0.1x^5 -1.2999999999999998x +5.0");
	c=new ComplexFunction("max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)");
	
	}
	
	@Test
	public void testConstructorStringfuncfunc()
	{
		//1:
		ComplexFunction comp=new ComplexFunction("mul",new Polynom ("x"),new Polynom ("x"));
		assertEquals(comp.getOp(),Operation.Times);
		assertTrue(comp.left().equals(new Polynom("x")));
		assertTrue(comp.right().equals(new Polynom("x")));
		//2:
	     comp=new ComplexFunction("div",new Polynom ("x"),new Polynom ("x"));
		assertEquals(comp.getOp(),Operation.Divid);
		assertTrue(comp.left().equals(new Polynom("x")));
		assertTrue(comp.right().equals(new Polynom("x")));
		//3:
	     comp=new ComplexFunction("max",new Polynom ("x"),new Polynom ("x"));
		assertEquals(comp.getOp(),Operation.Max);
		assertTrue(comp.left().equals(new Polynom("x")));
		assertTrue(comp.right().equals(new Polynom("x")));
		//4:
	     comp=new ComplexFunction("min",new Polynom ("x"),new Polynom ("x"));
		assertEquals(comp.getOp(),Operation.Min);
		assertTrue(comp.left().equals(new Polynom("x")));
		assertTrue(comp.right().equals(new Polynom("x")));
		//5:
	     comp=new ComplexFunction("comp",new Polynom ("x"),new Polynom ("x"));
		assertEquals(comp.getOp(),Operation.Comp);
		assertTrue(comp.left().equals(new Polynom("x")));
		assertTrue(comp.right().equals(new Polynom("x")));
		//6:
	     comp=new ComplexFunction("plus",new Polynom ("x"),new Polynom ("x"));
		assertEquals(comp.getOp(),Operation.Plus);
		assertTrue(comp.left().equals(new Polynom("x")));
		assertTrue(comp.right().equals(new Polynom("x")));
		try
		{
		  comp=new ComplexFunction("mini",new Polynom ("x"),new Polynom ("x"));
		  comp=new ComplexFunction("ghgf",new Polynom ("x"),new Polynom ("x"));
		  comp=new ComplexFunction("$%#^",new Polynom ("x"),new Polynom ("x"));
		}
		catch (Exception e) 
		{
			System.out.println("***test constructor get (String s,function left,function right)***");
			System.out.println("at the trying to put uncorrect string as Operation , catch this try");
			System.out.println();
		}
	}
	@Test
	public void testFuncConstructor()
	{
	ComplexFunction c=new ComplexFunction(new Polynom("x"));
	ComplexFunction c1=new ComplexFunction("none(x,0)");
	assertEquals(c.toString(),c1.toString());
	}
	@Test
	public void test_tostring ()
	{
		ComplexFunction comp=new ComplexFunction("f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)");
		assertEquals(comp.toString(),"plus(div(1.0x+1.0,mul(mul(1.0x+3.0,1.0x-2.0),1.0x-4.0)),2.0)");
		comp=new ComplexFunction();
		assertEquals(comp.toString(),"none(0.0,0.0)");
	}
	
	@Test
	public void test_plus ()
	{
		//1:
		ComplexFunction comp=new ComplexFunction("f(x)=comp(x,x)");
		ComplexFunction comp1=new ComplexFunction("f(x)=plus(x,x)");
		comp.plus(comp1);
		assertEquals(comp.toString(),"plus(comp(1.0x,1.0x),plus(1.0x,1.0x))");
		//2:
		comp=new ComplexFunction("f(x)=none(x,0)");
		comp1=new ComplexFunction("plus(x,x)");
		comp.plus(comp1);
		assertEquals(comp.toString(),"plus(1.0x,plus(1.0x,1.0x))");
		//3
		comp=new ComplexFunction("f(x)=none(x,0)");
		Polynom p=new Polynom ("x^2-5");
		comp.plus(p);
		assertEquals(comp.toString(),"plus(1.0x,x^2-5.0)");
		//4:
		comp=new ComplexFunction("f(x)=none(x,0)");
		comp1=new ComplexFunction("comp(x,1)");
		comp.plus(comp1);
		assertEquals(comp.toString(),"plus(1.0x,comp(1.0x,1.0))");

	}
	@Test
	public void test_mul ()
	{
		//1:
		ComplexFunction comp=new ComplexFunction("f(x)=comp(x,x)");
		ComplexFunction comp1=new ComplexFunction("f(x)=plus(x,x)");
		comp.mul(comp1);
		assertEquals(comp.toString(),"mul(comp(1.0x,1.0x),plus(1.0x,1.0x))");
		//2:
		comp=new ComplexFunction("f(x)=none(x,0)");
		comp1=new ComplexFunction("plus(x,x)");
		comp.mul(comp1);
		assertEquals(comp.toString(),"mul(1.0x,plus(1.0x,1.0x))");
		//3:
		comp=new ComplexFunction("f(x)=none(x^2-3,0)");
		Polynom p=new Polynom ("x^2-5");
		comp.mul(p);
		assertEquals(comp.toString(),"mul(x^2-3.0,x^2-5.0)");
		//4:
		comp=new ComplexFunction("f(x)=none(1,0)");
		comp1=new ComplexFunction("comp(x,1)");
		comp.mul(comp1);
		assertEquals(comp.toString(),"mul(1.0,comp(1.0x,1.0))");
		//5:
		comp=new ComplexFunction("f(x)=comp(0,x)"); //"show that "1" have no influence at multiply
		p=new Polynom ("1");
		comp.mul(p);
		assertEquals(comp.toString(),"comp(0.0,1.0x)");

	}
	@Test
	public void test_div ()
	{
		//1:
		ComplexFunction comp=new ComplexFunction("f(x)=comp(x,x)");
		ComplexFunction comp1=new ComplexFunction("f(x)=plus(x,x)");
		comp.div(comp1);
		assertEquals(comp.toString(),"div(comp(1.0x,1.0x),plus(1.0x,1.0x))");
		//2:
		comp=new ComplexFunction("f(x)=none(x,0)");
		comp1=new ComplexFunction("plus(x,x)");
		comp.div(comp1);
		assertEquals(comp.toString(),"div(1.0x,plus(1.0x,1.0x))");
		//3:
		comp=new ComplexFunction("f(x)=none(x,0)");
		Polynom p=new Polynom ("x^2-5");
		comp.div(p);
		assertEquals(comp.toString(),"div(1.0x,x^2-5.0)");
		//4:
		comp=new ComplexFunction("f(x)=none(x,0)");
		comp1=new ComplexFunction("comp(x,1)");
		comp.div(comp1);
		assertEquals(comp.toString(),"div(1.0x,comp(1.0x,1.0))");
		//5:
		comp=new ComplexFunction("f(x)=comp(x-5,0)"); //"show that "1" have no influence at multiply
		p=new Polynom ("1");
		comp.div(p);
		assertEquals(comp.toString(),"comp(1.0x-5.0,0.0)");
		
		try
		{
			comp=new ComplexFunction("f(x)=comp(x,0)"); //"show that "1" have no influence at multiply
			p=new Polynom ("0");
			comp.div(p);	
		}

		catch (Exception e)
		{
			System.out.println("***test div function***");
			System.out.println("at trying to divid by zero , catch this try. ");
			System.out.println();
		}
	}
	
	@Test
	public void test_comp ()
	{
		//1:
		ComplexFunction comp=new ComplexFunction("f(x)=comp(x,x)");
		ComplexFunction comp1=new ComplexFunction("f(x)=plus(x,x)");
		comp.comp(comp1);
		assertEquals(comp.toString(),"comp(comp(1.0x,1.0x),plus(1.0x,1.0x))");
		//2:
		comp=new ComplexFunction("f(x)=none(x,0)");
		comp1=new ComplexFunction("plus(x,x)");
		comp.comp(comp1);
		assertEquals(comp.toString(),"comp(1.0x,plus(1.0x,1.0x))");
		//3:
		comp=new ComplexFunction("f(x)=none(x,0)");
		Polynom p=new Polynom ("x^2-5");
		comp.comp(p);
		assertEquals(comp.toString(),"comp(1.0x,x^2-5.0)");
		//4:
		comp=new ComplexFunction("f(x)=mul(0,x)");
		comp1=new ComplexFunction("mul(0.0,1.0x)");
		comp.comp(comp1);
		assertEquals(comp.toString(),"comp(mul(0.0,1.0x),mul(0.0,1.0x))");
		//5:
		comp=new ComplexFunction("f(x)=comp(0,x)"); //"show that "1" have no influence at multiply
		p=new Polynom ("1");
		comp.comp(p);
		assertEquals(comp.toString(),"comp(comp(0.0,1.0x),1.0)");	
	}
	@Test
	public void test_max ()
	{
		//1:
		ComplexFunction comp=new ComplexFunction("f(x)=comp(x,x)");
		ComplexFunction comp1=new ComplexFunction("f(x)=plus(x,x)");
		comp.max(comp1);
		assertEquals(comp.toString(),"max(comp(1.0x,1.0x),plus(1.0x,1.0x))");
		//2:
		comp=new ComplexFunction("f(x)=none(x,0)");
		comp1=new ComplexFunction("plus(x,x)");
		comp.max(comp1);
		assertEquals(comp.toString(),"max(1.0x,plus(1.0x,1.0x))");
		//3
		comp=new ComplexFunction("f(x)=none(x-5,0)");
		Polynom p=new Polynom ("x^2-5");
		comp.max(p);
		assertEquals(comp.toString(),"max(1.0x-5.0,x^2-5.0)");
		//4:
		comp=new ComplexFunction("f(x)=none(x,0)");
		comp1=new ComplexFunction("comp(x,1)");
		comp.max(comp1);
		assertEquals(comp.toString(),"max(1.0x,comp(1.0x,1.0))");

	}
	 
	@Test
	public void test_min ()
	{
		//1:
		ComplexFunction comp=new ComplexFunction("f(x)=comp(x,x)");
		ComplexFunction comp1=new ComplexFunction("f(x)=plus(x,x)");
		comp.min(comp1);
		assertEquals(comp.toString(),"min(comp(1.0x,1.0x),plus(1.0x,1.0x))");
		//2:
		comp=new ComplexFunction("f(x)=none(x,0)");
		comp1=new ComplexFunction("plus(x,x)");
		comp.min(comp1);
		assertEquals(comp.toString(),"min(1.0x,plus(1.0x,1.0x))");
		//3
		comp=new ComplexFunction("f(x)=none(x^2,0)");
		Polynom p=new Polynom ("x^2-5");
		comp.min(p);
		assertEquals(comp.toString(),"min(x^2,x^2-5.0)");
		//4:
		comp=new ComplexFunction("f(x)=none(x,0)");
		comp1=new ComplexFunction("comp(x,1)");
		comp.min(comp1);
		assertEquals(comp.toString(),"min(1.0x,comp(1.0x,1.0))");

	}
	
	@Test
	public void test_left()
	{
		//1:
		ComplexFunction c=new ComplexFunction();
		assertEquals(c.left().toString(),"0.0");
		//2:
	     c=new ComplexFunction("mul",new ComplexFunction("mul(x,x)"),new Monom("x"));
		assertEquals(c.left().toString(),"mul(1.0x,1.0x)");
		//3:
	     c=new ComplexFunction("mul",new Polynom("x-1"),new Monom("x"));
		assertEquals(c.left().toString(),"1.0x-1.0");
	}
	@Test
	public void test_right()
	{
		//1:
		ComplexFunction c=new ComplexFunction();
		assertEquals(c.right().toString(),"0.0");
		//2:
	     c=new ComplexFunction("mul",new Monom("x"),new ComplexFunction("mul(x,x)"));
		assertEquals(c.right().toString(),"mul(1.0x,1.0x)");
		//3:
	     c=new ComplexFunction("mul",new Monom("x"),new Polynom("x-1"));
		assertEquals(c.right().toString(),"1.0x-1.0");
	}
	@Test
	public void test_OP()
	{
		//1:
		ComplexFunction c=new ComplexFunction();
		assertEquals(c.getOp().toString(),"None");
		//2:
	     c=new ComplexFunction("div",new Monom("x"),new ComplexFunction("mul(x,x)"));
		assertEquals(c.getOp().toString(),"Divid");
		//3:
	     c=new ComplexFunction("comp",new Monom("x"),new Polynom("x"));
		assertEquals(c.getOp().toString(),"Comp");
	}
	@Test
	public void test_F()
	{
		//1:
		ComplexFunction c=new ComplexFunction();
		double expected=0;
		assertEquals(c.f(5),expected,0.001);
		//2:
	     c=new ComplexFunction("div",new Monom("x"),new ComplexFunction("mul(x,x)"));
	     expected=0.5;
		assertEquals(c.f(2),expected,0.001);
		//3:
	     c=new ComplexFunction("comp",new Polynom("x+3"),new Polynom("x-1"));
	    expected=4;
		assertEquals(c.f(2),expected,0.001);
		//4:
	     c=new ComplexFunction("max",new Polynom("x+3"),new Polynom("x-1"));
	    expected=15;
		assertEquals(c.f(12),expected,0.001);
		//5:
		  c=new ComplexFunction("max",new ComplexFunction("mul(x,x)"),new Polynom("0"));
		  expected=144;
		  assertEquals(c.f(12),expected,0.001);
		  //6:
		//5:
		  c=new ComplexFunction("none",new ComplexFunction("mul(x,x)"),new Polynom("0"));
		  expected=144;
		  assertEquals(c.f(12),expected,0.001);
	}
	
	@Test
	public void initTest ()
	{
		//1:
		ComplexFunction c=new ComplexFunction("mul(x-5,1)");
		function f=c.initFromString("mul(x-5,1)");
		assertEquals(f.toString(),c.toString());
		//2:
		 c=new ComplexFunction("div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)");
		 f=c.initFromString("div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)");
		assertEquals(f.toString(),c.toString());
	}
	
	@Test
	public void copyTest ()
	{
		//1:
		ComplexFunction c=new ComplexFunction("mul(x-5,1)");
		function f=c.copy();
		assertEquals(f.toString(),c.toString());
		//2:
		 c=new ComplexFunction("div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)");
		 f=c.copy();
		assertEquals(f.toString(),c.toString());
		//3:
		 c=new ComplexFunction("0");
		 f=c.copy();
		assertEquals(f.toString(),c.toString());
	}
	
	@Test
	public void equalTest()
	{
		//1:
		ComplexFunction c=new ComplexFunction("mul(x-5,1)");
		function f=c.copy();
		boolean ans=c.equals(f);
		assertTrue(ans);
		//2:
		 c=new ComplexFunction("div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)");
		 f=c.copy();
		 ans=c.equals(f);
		 assertTrue(ans);
		//3:
		 c=new ComplexFunction("0");
		 f=c.copy();
		 ans=c.equals(f);
		 assertTrue(ans);
		 
		//4:
		 c=new ComplexFunction("mul(1,0)");
		 f=new Polynom("0");
		 ans=c.equals(f);
		 assertTrue(ans);
		//4:
		 c=new ComplexFunction("plus(x,x+3)");
		 f=new Polynom("2x+3.0");
		 ans=c.equals(f);
		 assertTrue(ans);
		 //5:
		 c=new ComplexFunction("plus(x,x)");
		 f=new Monom("2x");
		 ans=c.equals(f);
		 assertTrue(ans);
		 //6:
		 c=new ComplexFunction("none(2x,0)");
		 f=new Monom("2x");
		 ans=c.equals(f);
		 assertTrue(ans);
		 
	}

}
