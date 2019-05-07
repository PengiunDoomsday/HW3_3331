package pricewatcher.base;


import java.util.*;
import java.util.zip.GZIPInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class WebPriceFinder extends PriceFinder{
	
	public static double priceFinder1(String stringUrl) {
		double price = 0;
		
		HttpURLConnection con = null;
		try {
		  URL url = new URL(stringUrl);
		  con = (HttpURLConnection) url.openConnection();
		  // con.setRequestProperty("User-Agent", "...");
		  String encoding = con.getContentEncoding();
		  if (encoding == null) { encoding = "utf-8"; }
		  InputStreamReader reader = null;
		  if ("gzip".equals(encoding)) { // gzipped document?
		     reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
		  } else {
		     reader = new InputStreamReader(con.getInputStream(), encoding);
		  }
		  BufferedReader in = new BufferedReader(reader);
		  String line;
		  while ((line = in.readLine()) != null) {
			  System.out.println(line);
		     
		  }
		} catch (IOException e) { e.printStackTrace(); 
		} finally {
		  if (con != null) {  con.disconnect(); }
		}
		
		return price;

	}


}
