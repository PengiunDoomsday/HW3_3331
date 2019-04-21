/*
 * Name:Javier Soon
 * ID: 80436654
 * CS 3331
 * 
 */

package pricewatcher.base;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.io.*;


import javax.swing.*;


import pricewatcher.base.Item;


/**
* A dialog for tracking the price of an item.
*
* @author Yoonsik Cheon
*/
@SuppressWarnings("serial")
public class Main extends JFrame {

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(400, 300);
      
    /** Special panel to display the watched item. */
    private ItemView itemView;
      
    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");
    private Item item;
    private Item item2;
    /** Create a new dialog. */
    public Main() {
    	this(DEFAULT_SIZE);
    }
    
    /** Create a new dialog of the given screen dimension. */
    public Main(Dimension dim) {
        super("Price Watcher");
        setSize(dim);
        item = new Item();
        
        item.setName("Marvel's Spider-Man - PlayStation 4");
    	item.setUrl("https://www.amazon.com/Marvels-Spider-Man-PlayStation-4/dp/B01GW8YDLK/ref=sr_1_1?ie=UTF8&qid=1549050988&sr=8-1&keywords=spiderman%2Bgame&th=1");
    	item.setInitialPrice ((PriceFinder.priceFinder1()));

    	item2 = new Item();
    	item2.setName("Alphonse Figure");
    	item2.setUrl("https://otakumode.com/shop/596ecc1c47bf74901e1ac932/Nendoroid-Fullmetal-Alchemist-Alphonse-Elric");
    	item2.setInitialPrice(PriceFinder.priceFinder3());
        configureUI();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


    }
  
    /** Callback to be invoked when the refresh button is clicked. 
     * Find the current price of the watched item and display it 
     * along with a percentage price change. */
    private void refreshButtonClicked(ActionEvent event) {
    	
    	item.setCurrentPrice(PriceFinder.priceFinder2());
    	item2.setCurrentPrice(PriceFinder.priceFinder2());
        itemView.repaint();
    }
    
    
    
    
    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item. */
    private void viewPageClicked() {    	
    
    	if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			String url = itemView.item.getUrl();
			String url2 = itemView.item2.getUrl();
			
    		try {
				Desktop.getDesktop().browse(java.net.URI.create(url));
				Desktop.getDesktop().browse(java.net.URI.create(url2));
			} catch (NullPointerException e) {
				System.out.println("Url does not exist.");

			} catch (UnsupportedOperationException ee) {
				// TODO Auto-generated catch block
				System.out.println("Current platform is not supported.");

			} catch (SecurityException eee) {
				System.out.println("Do not have security clearance.");

			} catch (IOException eeee) {
				System.out.println("Could not launch");
			}
		}
    }
        
    
    
    
    /** Configure UI. */
    private void configureUI() {
        setLayout(new BorderLayout());
        JPanel control = makeControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16)); 
        add(control, BorderLayout.NORTH);
        JPanel board = new JPanel();
        board.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createEmptyBorder(10,16,0,16),
        		BorderFactory.createLineBorder(Color.GRAY)));
        board.setLayout(new GridLayout(1,1));
        itemView = new ItemView(item,item2);
        
        
        itemView.setClickListener(this::viewPageClicked);
        board.add(itemView);
        add(board, BorderLayout.CENTER);
        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);
    }
      
    /** Create a control panel consisting of a refresh button. */
    private JPanel makeControlPanel() {
    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    	JButton refreshButton = new JButton("Refresh Check prices");
    	refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonClicked);
        panel.add(refreshButton);
        return panel;
    }

    public static void main(String[] args) {
        new Main();
    }

}
