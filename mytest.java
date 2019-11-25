package myMath;


import java.util.Scanner;

public class mytest {

	public static void test1 ()    //test which check the add monom to Polynom
	{
		System.out.println("test1:");
		System.out.println("this test check the function add(Monom m)");
		Scanner s=new Scanner (System.in);
		Monom[] monoms=new Monom [5];
		String a;
		Polynom p=new Polynom();
		for (int i=0;i<monoms.length;i++)
		{
			System.out.println("please enter a monom: ");
			a=s.next();
			monoms[i]=new Monom(a);
			p.add(monoms[i]);	
		}

	
		System.out.println(p.tostring());
		System.out.println("______________________________________________");
		System.out.println();		
	}
	
    public static void test2()      //test which check the add polynom to polynom
	{
		Scanner s=new Scanner (System.in);
		System.out.println("test2:");
		System.out.println("this test check the function add(polynom m)");
		System.out.println("please enter a P1: ");
		String pol=s.next();
		Polynom p=new Polynom(pol);
		System.out.println("please enter a P2: ");
        pol=s.next();
        Polynom p1=new Polynom(pol);
		p.add(p1);
		System.out.println();
        System.out.println("p+p1");
        System.out.print(p.tostring());	
		System.out.println();
		System.out.println("______________________________________________");
		System.out.println();
		
	}
	
    public static void test3()           //check substract
	{
		Scanner s=new Scanner (System.in);
		System.out.println("test3:");
		System.out.println("this test check the function Substract(polynom m)");
		System.out.println("please enter a p1: ");
		String pol=s.next();
		Polynom p=new Polynom(pol);
		System.out.println("please enter a p2: ");
        pol=s.next();
        Polynom_able p1=new Polynom(pol);
		p.substract(p1);
		System.out.println();
        System.out.println("p-p1");
        System.out.print(p.tostring());	
		System.out.println();
		System.out.println("______________________________________________");
		System.out.println();
	}
	
	public static void test4()      //test which check the f(x) of any x
	{
		int a;
		Scanner s=new Scanner (System.in);
		System.out.println("test4:");
		System.out.println("this test check the function f(x)");
		System.out.println("please enter a p1: ");
		String pol=s.next();
		Polynom p=new Polynom(pol);
		System.out.println();
		for (int i=0;i<5;i++)
		{
			System.out.println("please enter x to do f(x): ");
			a=s.nextInt();
			System.out.println(p.f(a));
			System.out.println("______________________________________________");
			System.out.println();
		}
	}
	
    public static void test5()       //test multiply between p1*p2
    {
		Scanner s=new Scanner (System.in);
		System.out.println("test5:");
		System.out.println("this test check the function multiply(polynom m)");
		System.out.println("please enter a P1: ");
		String pol=s.next();
		Polynom p=new Polynom(pol);
		System.out.println("please enter a P2: ");
        pol=s.next();
        Polynom p1=new Polynom(pol);
		p.multiply(p1);
		System.out.println();
        System.out.println("p*p1");
        System.out.print(p.tostring());	
		System.out.println();
		System.out.println("______________________________________________");
		System.out.println();
    }

    
    public static void test6()        //test which check derivative of polynom p
    {
    	Scanner s=new Scanner (System.in);
		System.out.println("test6:");
		System.out.println("this test check the function derivative()");
		System.out.println("please enter a P: ");
		String pol=s.next();
		Polynom p=new Polynom(pol);
		p.derivative();
        System.out.print(p.tostring());	
		System.out.println();
	
		System.out.println("______________________________________________");
    	System.out.println();
    }
  
    public static void test7()
    {
		System.out.println("test7:");
		System.out.println("this test check the function area(double x0,double x1, double eps)");
    	Scanner s=new Scanner(System.in);
    	System.out.println("enter Polynom: ");
    	String Poly=s.next();
    	Polynom p=new Polynom(Poly);
    	System.out.println("enter x0");
    	double x0=s.nextDouble();
    	System.out.println("enter x1");
    	double x1=s.nextDouble();
    	System.out.println("calculate area between x0 to x1 by using Riemann sums:");
    	System.out.println(p.area(x0, x1, 0.00001));
    	System.out.println("______________________________________________");
    	System.out.println();
    }
    
    public static void test8()
    {
    	System.out.println("test8:");
		System.out.println("this test check the function multiply(Monom m)");
		Scanner s=new Scanner (System.in);
		String a;
		String b;
		System.out.println("enter polynom p");
		b=s.next();
		Polynom p=new Polynom(b);
		System.out.println("please enter a monom m: ");
		a=s.next();
		Monom m=new Monom(a);
        System.out.println("m*p");
        p.multiply(m);
		System.out.println(p.tostring());
		System.out.println("______________________________________________");
		System.out.println();	
    }
    
    public static void test9()
    {
    	System.out.println("test9:");
		System.out.println("this test check the function root");
		Scanner s=new Scanner (System.in);
		String b;
		System.out.println("enter polynom p");
		b=s.next();
		Polynom p=new Polynom (b);
		double x0,x1;
		System.out.println("enter x0");
		x0=s.nextInt();
		System.out.println("enter x1");
		x1=s.nextInt();
		System.out.println(p.root(x0, x1, 0.001));
    	
    }
    
    public static void main(String[] args)
	{	
       test1();
      test2();
   test3();
       test4();
        test5();
        test6();
          test7();
    	test8();
       test9();
	}

    
}
