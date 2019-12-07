package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;




public class Functions_GUI implements functions {

	ArrayList<function> Flist = new ArrayList<function>();
	@Override
	public boolean add(function arg0)
	{
	if  (this.Flist.contains(arg0))
		return false;
	else
	{
		this.Flist.add(arg0);
		return true; 
	}
	}

	@Override
	public boolean addAll(Collection<? extends function> c)
	{
		if (this.Flist.contains(c))
		{
		return false;
		}
		else
		{
			this.Flist.addAll(c);
			return true;
		}
	}

	@Override
	public void clear()
	{
	this.Flist.clear();
	}

	@Override
	public boolean contains(Object o)
	{
		return this.Flist.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.Flist.containsAll(c);
	}

	@Override
	public boolean isEmpty()
	{
		return this.Flist.isEmpty();
	}

	@Override
	public Iterator<function> iterator() 
	{
		Iterator<function> iterator = this.Flist.iterator();
		return iterator;
	}

	@Override
	public boolean remove(Object o)
	{
		return this.Flist.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) 
	{
		return this.Flist.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
      return this.Flist.retainAll(c);
	}

	@Override
	public int size() 
	{
	return this.Flist.size();
	}

	public function get(int i) {
		return this.Flist.get(i);
	}
	@Override
	public Object[] toArray() 
	{
		return this.Flist.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) 
	{
		return this.Flist.toArray(a);
	}

	@Override
	public void initFromFile(String file) throws IOException 
	{
	       String line = "";
	       int i=0;

	        try 
	        {
	        	BufferedReader br = new BufferedReader(new FileReader(file));
	        	line=br.readLine();
	            String[] userInfo = line.split(" ");
	            
	            while (i<userInfo.length)
	            {
                    try
                    {
                    	this.add(new Polynom(userInfo[i]));
                    }
                    catch (Exception E)
                    {
                    	this.add(new ComplexFunction(userInfo[i]));
                    }
                   i++;
	            }
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            System.out.println("could not read file");
	        }		
	}

	@Override
	public void saveToFile(String file) throws IOException
	{
		try {
			File f=new File(file);
			PrintWriter pw = new PrintWriter(f);
			String intoFile="";
			for(int i = 0; i<this.Flist.size(); i++) 
			{
	         intoFile=intoFile+this.get(i).toString()+ " ";
			}
			pw.write(intoFile.toString());
			pw.close();
		}
		catch (Exception e) {
			throw new RuntimeException("couldn't save the collection");
		}
		
	}

	public static Color[] Colors = {Color.blue, Color.cyan,
			Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
	@Override
	public void drawFunctions(int w, int h, Range rx, Range ry, int resolution)
	{
		double pointX = (rx.get_max()-rx.get_min())/resolution;
		double x1,x2,y1,y2;
		StdDraw.setCanvasSize(w,h);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		Around_x(rx, ry);
		Around_y(rx,ry);
		StdDraw.setPenRadius(0.005);
		Iterator<function> it = this.Flist.iterator();
		int iColor = 0;
		while(it.hasNext()) {	
			function f = it.next();
			if(iColor==Colors.length) //array of colors are final, when the counter arrive to the maximal size, return all over again(again Color[0],Color[1]...)
				iColor = 0;
			StdDraw.setPenColor(Colors[iColor]);
			iColor++;
			for(double j = rx.get_min(); j<rx.get_max(); j+=pointX) {
				x1 = j-pointX;
				x2 = j;
				y1 = f.f(x1);
				y2 = f.f(x2);
				StdDraw.line(x1, y1, x2, y2);
			}
		}
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 16));
		StdDraw.save("Draw_functions.jpg"); //save the draw as a jpg file
	}

	private void Around_x(Range rx,Range ry) {
		
		for(double i = rx.get_max(); i>=rx.get_min(); i--) {
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.002);
			if(i==0) {
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.008);
			}
			StdDraw.line(i, ry.get_min(), i , ry.get_max());
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.004);
			StdDraw.text(i, -0.35, (int)i+"");
		}
		
	}
	
	private void Around_y (Range rx,Range ry)
	{
		for(double i = ry.get_max(); i>=ry.get_min(); i--) {
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.002);
			if(i==0) {
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.008);
			}
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.02);
			StdDraw.text(-0.16, i, (int)i+"");
		}

	}
	
	public String toString()
	{
	String s ="";
	for(int i = 0; i<this.Flist.size(); i++)
	s =s+ this.Flist.get(i) + " , ";
	s = s.substring(0, s.length()-2);
	return s;
}

	@Override
	public void drawFunctions(String json_file) 
	{
		JSONParser p = new JSONParser();

		try {

			FileReader fileReader = new FileReader(json_file);
			JSONObject Obj = (JSONObject) p.parse(fileReader);
			Long widthL = (Long) Obj.get("Width");
			Long heightL = (Long) Obj.get("Height");
			Long resolutionL = (Long) Obj.get("Resolution");
			JSONArray rx = (JSONArray) Obj.get("Range_X");
			JSONArray ry = (JSONArray) Obj.get("Range_Y");
			int width = widthL.intValue();
			int height = heightL.intValue();
			int resolution = resolutionL.intValue();
			Range rxN;
			Range ryN;
			double rx0,rx1,ry0,ry1;
			Long rx0L = (Long) rx.get(0);
			Long rx1L = (Long) rx.get(1);
			Long ry0L = (Long) ry.get(0);
			Long ry1L = (Long) ry.get(1);
			rx0 = rx0L.doubleValue();
			rx1 = rx1L.doubleValue();
			ry0 = ry0L.doubleValue();
			ry1 = ry1L.doubleValue();
			if(rx0<rx1)
				rxN = new Range(rx0,rx1);
			else
				rxN = new Range(rx1,rx0);
			if(ry0<ry1)
				ryN = new Range(ry0,ry1);
			else
				ryN = new Range(ry1,ry0);
			this.drawFunctions(width, height, rxN, ryN, resolution);

		}
		catch (Exception E) {}
	}


		
	
}
