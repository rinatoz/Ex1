package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;

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
		Iterator<function> itr = this.Flist.iterator();
		return itr;
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
		File fileName = new File(file);
		BufferedReader buffer = new BufferedReader(new FileReader(fileName)); 

		String s=buffer.readLine(); 
		while (s!= null) 
		{
		 try
			{
			this.add(new Polynom(s));
			}
			catch (Exception e) 
		    {
			this.add(new ComplexFunction(s));
			}
		}

		
	}

	@Override
	public void saveToFile(String file) throws IOException
	{
		try {
			FileOutputStream f =new FileOutputStream(file);
			OutputStreamWriter o=new OutputStreamWriter(f, "utf-8");
			Writer writer = new BufferedWriter(o);
			for(int i = 0; i<this.Flist.size(); i++) {
				writer.write(this.Flist.get(i).toString()+"\n");
			}
			writer.close();
		}
		catch (Exception e) {
			throw new RuntimeException("ERR: coud not save the Cokkection of Functions into a file");
		}
		
	}

	public static Color[] Colors = {Color.blue, Color.cyan,
			Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
	@Override
	public void drawFunctions(int w, int h, Range rx, Range ry, int resolution)
	{
		StdDraw.setCanvasSize(w,h);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		setGrid(rx, ry);
		StdDraw.setPenRadius(0.005);
		double pointX = (rx.get_max()-rx.get_min())/resolution;
		Iterator<function> it = this.Flist.iterator();
		int iColor = 0;
		while(it.hasNext()) {	
			function f = it.next();
			if(iColor==Colors.length)
				iColor = 0;
			StdDraw.setPenColor(Colors[iColor]);
			iColor++;
			for(double j = rx.get_min(); j<rx.get_max(); j+=pointX) {
				double x1 = j-pointX;
				double x2 = j;
				double y1 = f.f(x1);
				double y2 = f.f(x2);
				StdDraw.line(x1, y1, x2, y2);
			}
		}
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 16));
		StdDraw.save("Functions_GUI.jpg");
	}

	private void setGrid(Range rx, Range ry) {
		for(double i = ry.get_max(); i>=ry.get_min(); i--) {
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.001);
			if(i==0) {
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.003);
			}
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(-0.3, i, (int)i+"");
		}
		for(double i = rx.get_max(); i>=rx.get_min(); i--) {
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.001);
			if(i==0) {
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.003);
			}
			StdDraw.line(i, ry.get_min(), i , ry.get_max());
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(i, -0.35, (int)i+"");
		}

	}
	
	@Override
	public void drawFunctions(String json_file)
	{

		try {

			FileReader fileReader = new FileReader(json_file);
			JsonObject jsonObject = (JsonObject) jsonParser.parse(fileReader);
			int width2 = (int) jsonObject.get("Width");
			int width = width2.intValue();
			Long height2 = (Long) jsonObject.get("Height");
			int height = height2.intValue();
			Long resolution2 = (Long) jsonObject.get("Resolution");
			int resolution = resolution2.intValue();
			JsonArray rx = (JsonArray) jsonObject.get("Range_X");
			JsonArray ry = (JsonArray) jsonObject.get("Range_Y");
			Range rxnew;
			Range rynew;
			double rx0;
			double rx1;
			double ry0;
			double ry1;
			Long rx00 = (Long) rx.get(0);
			Long rx11 = (Long) rx.get(1);
			Long ry00 = (Long) ry.get(0);
			Long ry11 = (Long) ry.get(1);
			rx0 = rx00.doubleValue();
			rx1 = rx11.doubleValue();
			ry0 = ry00.doubleValue();
			ry1 = ry11.doubleValue();
			if(rx0<rx1)
				rxnew = new Range(rx0,rx1);
			else
				rxnew = new Range(rx1,rx0);
			if(ry0<ry1)
				rynew = new Range(ry0,ry1);
			else
				rynew = new Range(ry1,ry0);
			this.drawFunctions(width, height, rxnew, rynew, resolution);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	public String toString()
	{
	String ans ="";
	for(int i = 0; i<this.Flist.size(); i++)
		ans += this.Flist.get(i) + " , ";
	ans = ans.substring(0, ans.length()-2);
	return ans;
}
}
