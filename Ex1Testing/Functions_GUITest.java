package Ex1Testing;
import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class Functions_GUITest {
	
	public static void main(String[] a) {
		Functions_GUI data = FunctionsFactory();
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		data.drawFunctions(w,h,rx,ry,res);
	}
	
	
	private Functions_GUI _data=null;

	@Before
	public void setUp() throws Exception {
		_data = FunctionsFactory();
	}
    @Test
    public void testFunctions_GUI() 
    {
    	//add
    	Functions_GUI col = new Functions_GUI();
    	col.add(new ComplexFunction("plus(x^2,x)"));
    	col.add(new Polynom("3+x^3-2"));
    	col.add(new Monom("2"));	
    	assertEquals(col.get(0).toString(),"plus(x^2,1.0x)");
    	assertEquals(col.get(1).toString(),"x^3+1.0");
    	assertEquals(col.get(2).toString(),"2.0");
        //addAll
    	col = new Functions_GUI();
    	Functions_GUI c = new Functions_GUI();
       	c.add(new ComplexFunction("plus(x^2,x)"));
    	c.add(new Polynom("3+x^3-2"));
    	c.add(new Monom("2"));
   	    col.addAll(c);
   		assertEquals(col.get(0).toString(),"plus(x^2,1.0x)");
    	assertEquals(col.get(1).toString(),"x^3+1.0");
    	assertEquals(col.get(2).toString(),"2.0");
    	//clear
    	col.clear();
    	assertEquals(col.size(),0);
    	//contains
    	col.add(new ComplexFunction("plus(x^2,x)"));
    	col.add(new Polynom("3+x^3-2"));
    	col.add(new Monom("2"));
    	assertTrue(col.contains(new ComplexFunction("plus(x^2,x)")));
    	assertEquals(col.contains(new ComplexFunction("plus(x^2,0)")),false);
    	//contains all
    	assertTrue(col.containsAll(c));
    	//isEmpty
    	c.clear();
    	assertTrue(c.isEmpty());
    	//remove
    	col.remove(new Polynom("3+x^3-2"));
       	c.add(new ComplexFunction("plus(x^2,x)"));
    	c.add(new Monom("2"));
    	assertEquals(col.get(0).toString(),c.get(0).toString());
    	assertEquals(col.get(1).toString(),c.get(1).toString());
    	//removeall
    	col.removeAll(c);
    	assertTrue(!(col.contains(c.get(0))));
    	assertTrue(!(col.contains(c.get(1))));
    	//retainall
    	col = new Functions_GUI();
    	col.add(new ComplexFunction("plus(x^2,x)"));
    	col.add(new Polynom("3+x^3-2"));
    	col.add(new Monom("2"));
    	c = new Functions_GUI();
       	c.add(new ComplexFunction("plus(x^2,x)"));
    	c.add(new Polynom("3+x^3-2"));
    	assertTrue(col.retainAll(c));
    	c.add(new Monom("x"));
    	assertTrue(!(col.retainAll(c)));
    	//size
    	assertEquals(col.size(),2);
    	
	}
    @Test
    public void testinittofile() throws IOException
    {
	Functions_GUI c1 = new Functions_GUI();
	c1.add(new Polynom("3+x^2-1"));
	c1.add(new Monom("12x"));
	c1.add(new ComplexFunction("plus(x^2,x)"));
	c1.add(new ComplexFunction("div(x^2,x)"));
	c1.saveToFile("check.txt");
	Functions_GUI c2 = new Functions_GUI();
	c2.initFromFile("check.txt");
	assertEquals(c2.toString(), c1.toString());
    }
    
	@Test
	public void testSaveToFile() throws IOException {
		Functions_GUI c1 = new Functions_GUI();
		c1.add(new Polynom("-4 + 5x^3 + 10x^10"));
		c1.add(new Monom("2x^2"));
		c1.add(new ComplexFunction("mul(comp(x^2,x),4)"));
		c1.saveToFile("check2.txt");
		Functions_GUI c2 = new Functions_GUI();
		c2.initFromFile("check2.txt");
		assertEquals(c2.toString(), c1.toString());
	}
	@Test
	public void testDrawFunctions() {
		_data.drawFunctions("GUI_params.txt");
		
	}
	
	public static Functions_GUI FunctionsFactory() {
		Functions_GUI ans = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		
		ComplexFunction cf = new ComplexFunction("plus", p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		ComplexFunction max = new ComplexFunction(ans.get(0).copy());
		ComplexFunction min = new ComplexFunction(ans.get(0).copy());
		for(int i=1;i<ans.size();i++) {
			max.max(ans.get(i));
			min.min(ans.get(i));
		}
		ans.add(max);
		ans.add(min);
		
		return ans;
	}
}