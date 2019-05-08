package pricewatcher.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.swing.JOptionPane;

import java.io.*;
import java.net.*;

public class WebPriceFinder {

	public static void PriceFinder(String url) {
		int check = 0;
		double currentPrice = 0;
		if (url.contains("walmart")) {
			check = 1;
		}

		if (url.contains("bestbuy")) {
			check = 2;
		} else if (url.contains("newegg")) {
			check = 3;
		}

		switch (check) {
		case 1:
			currentPrice = CheckWalmart(url);
			break;
		case 2:
			currentPrice = CheckBestbuy(url);
			break;
		case 3:
			currentPrice = CheckNewegg(url);
			break;
		default:
			System.out.println("invaild url");
			JOptionPane.showMessageDialog(null, "Invalid URL", "Invalid", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static double CheckWalmart(String stringUrl) {
		HttpURLConnection con = null;
		double currentPrice_v_2 = 0;

		try {
			URL url = new URL(stringUrl);
			con = (HttpURLConnection) url.openConnection();
			// con.setRequestProperty("User-Agent", "...");
			String encoding = con.getContentEncoding();
			if (encoding == null) {
				encoding = "utf-8";
			}
			InputStreamReader reader = null;
			if ("gzip".equals(encoding)) { // gzipped document?
				reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
			} else {
				reader = new InputStreamReader(con.getInputStream(), encoding);
			}
			BufferedReader in = new BufferedReader(reader);
			String line;
			Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");

			while ((line = in.readLine()) != null) {
				if (line.contains("\"price-group\" role=\"text\" aria-label=")) {
//					System.out.println("found the price" + line);
					Matcher matcher = pattern.matcher(line);
					while (matcher.find()) {
						String price = matcher.group(1);
						double currentPrice = Double.parseDouble(price);
						currentPrice_v_2 = currentPrice;
						System.out.println(currentPrice);
						return currentPrice;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return currentPrice_v_2;
	}

	public static double CheckBestbuy(String stringUrl) {
		double currentPrice_v_2 = 0;
		HttpURLConnection con = null;

		try {
			URL url = new URL(stringUrl);
			con = (HttpURLConnection) url.openConnection();
			// con.setRequestProperty("User-Agent", "...");
			String encoding = con.getContentEncoding();
			if (encoding == null) {
				encoding = "utf-8";
			}
			InputStreamReader reader = null;
			if ("gzip".equals(encoding)) { // gzipped document?
				reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
			} else {
				reader = new InputStreamReader(con.getInputStream(), encoding);
			}
			BufferedReader in = new BufferedReader(reader);
			String line;
			Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");

			while ((line = in.readLine()) != null) {
				if (line.contains(">Your price for this item is $<!-- -->")) {
					System.out.println("found the price" + line);
					Matcher matcher = pattern.matcher(line);
					while (matcher.find()) {
						String price = matcher.group(1);
						double currentPrice = Double.parseDouble(price);
						currentPrice_v_2 = currentPrice;
						System.out.println(currentPrice);
						return currentPrice;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return currentPrice_v_2;
	}

	public static double CheckNewegg(String stringUrl) {
		double currentPrice_v_2 = 0;
		HttpURLConnection con = null;

		try {
			URL url = new URL(stringUrl);
			con = (HttpURLConnection) url.openConnection();
			// con.setRequestProperty("User-Agent", "...");
			String encoding = con.getContentEncoding();
			if (encoding == null) {
				encoding = "utf-8";
			}
			InputStreamReader reader = null;
			if ("gzip".equals(encoding)) { // gzipped document?
				reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
			} else {
				reader = new InputStreamReader(con.getInputStream(), encoding);
			}
			BufferedReader in = new BufferedReader(reader);
			Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");
			String line;

			while ((line = in.readLine()) != null) {
				if (line.contains("price-current")) {
					System.out.println("found the price" + line);
					Matcher matcher = pattern.matcher(line);
					while (matcher.find()) {
						String price = matcher.group(1);
						double currentPrice = Double.parseDouble(price);
						currentPrice_v_2 = currentPrice;
						System.out.println(currentPrice);
						return currentPrice;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return currentPrice_v_2;
	}

	public static void main(String[] args) {
		String wally = "https://www.bestbuy.com/site/days-gone-playstation-4/5358601.p?skuId=5358601";
		PriceFinder(wally);
		System.out.println("hey");
	}

}
