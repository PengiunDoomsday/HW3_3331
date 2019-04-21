/*
 * Name:Javier Soon
 * ID: 80436654
 * CS 3331
 * 
 */

package pricewatcher.base;

import java.awt.*;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pricewatcher.base.Item;

/** A special panel to display the detail of an item. */

@SuppressWarnings("serial")
public class ItemView extends JPanel {

	Item item;
	Item item2;

	/** Interface to notify a click on the view page icon. */
	public interface ClickListener {

		/** Callback to be invoked when the view page icon is clicked. */
		void clicked();
	}

	/** Directory for image files: src/image in Eclipse. */
	private final static String IMAGE_DIR = "/image/";

	/** View-page clicking listener. */
	private ClickListener listener;

	/** Create a new instance. */
	public ItemView(Item item, Item item2) {
		this.item = item;
		this.item2 = item2;
		setPreferredSize(new Dimension(100, 200));
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isViewPageClicked(e.getX(), e.getY()) && listener != null) {
					listener.clicked();
				}
			}
		});
	}

	/** Set the view-page click listener. */
	public void setClickListener(ClickListener listener) {
		this.listener = listener;
	}

	/** Overridden here to display the details of the item. */
	@Override
	public void paintComponent(Graphics g) {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = Calendar.getInstance().getTime();
		String dateAdd = dateFormat.format(date);

		item.setDateAdded(dateAdd);
		item2.setDateAdded(dateAdd);
		double priceChange = ((((item.getCurrentPrice()) * 100) / item.getInitialPrice()) - 100);
		double priceChange2 = ((((item2.getCurrentPrice()) * 100) / item2.getInitialPrice()) - 100);
		if (priceChange == -100 && priceChange2 == -100) {

			priceChange = 0;
			priceChange2 = 0;
		}
		DecimalFormat df = new DecimalFormat("0.00");
		item.setPriceChange(Double.parseDouble(df.format(priceChange)));
		item2.setPriceChange(Double.parseDouble(df.format(priceChange2)));

		super.paintComponent(g);

		int x = 20, y = 30;
		g.drawImage(getImage("view.png"), x, y - 20, null);

		y += 20;

		g.drawString("Name:", x, y);
		g.setFont(new Font("Arial", Font.BOLD, 13));
		g.drawString(item.getName(), x + 60, y);
		g.setFont(new Font("Arial", Font.PLAIN, 12));

		g.drawString("URL:", x, y + 20);
		g.drawString(item.getUrl(), x + 60, y + 20);

		g.drawString("Price:", x, y + 40);
		g.setColor(Color.BLUE);
		g.drawString("$" + item.getCurrentPrice(), x + 60, y + 40);
		g.setColor(Color.BLACK);

		g.drawImage(getImage("view.png"), x, y + 100, null);

		g.drawString("Name:", x, y + 140);
		g.setFont(new Font("Arial", Font.BOLD, 13));
		g.drawString(item2.getName(), x + 60, y + 140);
		g.setFont(new Font("Arial", Font.PLAIN, 12));

		g.drawString("URL:", x, y + 160);
		g.drawString(item2.getUrl(), x + 60, y + 160);

		g.drawString("Price:", x, y + 180);
		g.setColor(Color.BLUE);
		g.drawString("$" + item.getCurrentPrice(), x + 60, y + 180);
		g.setColor(Color.BLACK);

		if (item.getPriceChange() > 0) {
			g.drawString("Change:", x, y + 60);
			g.setColor(Color.RED);
			g.drawString(item.getPriceChange() + "%", x + 60, y + 60);
		}

		if (item2.getPriceChange() > 0) {
			g.drawString("Change:", x, y + 200);
			g.setColor(Color.RED);
			g.drawString(item2.getPriceChange() + "%", x + 60, y + 200);
		}

		if (item.getPriceChange() < 0) {
			g.drawString("Change:", x, y + 60);
			g.setColor(Color.GREEN);
			g.drawString(item.getPriceChange() + "%", x + 60, y + 60);
		}
		
		
		if (item2.getPriceChange() < 0) {
			g.drawString("Change:", x, y + 200);
			g.setColor(Color.GREEN);
			g.drawString(item2.getPriceChange() + "%", x + 60, y + 200);
		}

		else {
			g.drawString("Change:", x, y + 60);
			g.drawString("Change:", x, y + 200);
			g.setColor(Color.BLACK);
			g.drawString(item.getPriceChange() + "%", x + 60, y + 60);
			g.drawString(item2.getPriceChange() + "%", x + 60, y + 200);
		}

		g.setColor(Color.BLACK);
		g.drawString("Added:", x, y + 80);
		g.drawString(item.getDateAdded() + " ($" + item.getInitialPrice() + ")", x + 60, y + 80);
		g.drawString("Added:", x, y + 220);
		g.drawString(item.getDateAdded() + " ($" + item.getInitialPrice() + ")", x + 60, y + 220);

	}

	public URL getCodeBase() {
		return getClass().getResource("/");
	}



	/** Return true if the given screen coordinate is inside the viewPage icon. */
	private boolean isViewPageClicked(int x, int y) {
		// --
		// -- WRITE YOUR CODE HERE
		// --

		return new Rectangle(20, 20, 30, 20).contains(x, y);
	}

	/** Return the image stored in the given file. */
	public Image getImage(String file) {
		try {
			URL url = new URL(getClass().getResource(IMAGE_DIR), file);
			return ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
