package pricewatcher.base;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

/**
 * A dialog for tracking the price of an item.
 *
 * @author Javier Soon and Matthew Iglesias and Alejandro Villarreal
 * @ID 80436654 and 80591632 and 88759517
 */
@SuppressWarnings("serial")
public class Main extends JFrame {

    /**
     * Default dimension of the dialog.
     */
    private final static Dimension DEFAULT_SIZE = new Dimension(400, 300);

    private ActionListener menuItemListener = null;

    /**
     * Special panel to display the watched item.
     */
    private ItemView itemView;
    // private Toolbar toolBar;
    /**
     * Message bar to display various messages.
     */
    private JLabel msgBar = new JLabel(" ");
    private Item item;
    private Item item2;
    private AddressDialog dialog;
    private PriceFinder itemPrice = new PriceFinder();
    private DefaultListModel<Item> listModel;
    private JList<Item> itemList;

    /**
     * Create a new dialog.
     *
     */
    public Main() {
        this(DEFAULT_SIZE);
    }

    /**
     * Create a new dialog of the given screen dimension.
     *
     * @param dim
     */
    public Main(Dimension dim) {
        super("Price Watcher");
        setSize(dim);

        item = new Item();
        item.setName("Marvel's Spider-Man - PlayStation 4");
        item.setUrl(
                "https://www.amazon.com/Marvels-Spider-Man-PlayStation-4/dp/B01GW8YDLK/ref=sr_1_1?ie=UTF8&qid=1549050988&sr=8-1&keywords=spiderman%2Bgame&th=1");
        item.setInitialPrice((itemPrice.priceFinder1()));

        item2 = new Item();
        item2.setName("Alphonse Figure");
        item2.setUrl(
                "https://otakumode.com/shop/596ecc1c47bf74901e1ac932/Nendoroid-Fullmetal-Alchemist-Alphonse-Elric");
        item2.setInitialPrice(itemPrice.priceFinder3());

        // create the model and adds elements
        listModel = new DefaultListModel<>();
        listModel.addElement(item);
        listModel.addElement(item2);

        // create the list
        itemList = new JList<>(listModel);
        itemList.setVisibleRowCount(2);

        configureUI();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

    }

    /**
     * Callback to be invoked when the refresh button is clicked. Find the
     * current price of the watched item and display it along with a percentage
     * price change.
     */
    private void refreshButtonClicked(ActionEvent event) {
        item.setCurrentPrice(itemPrice.priceFinder2());
        itemView.repaint();
    }

    /**
     * Callback to be invoked when the view-page icon is clicked. Launch a
     * (default) web browser by supplying the URL of the item.
     */
    private void viewPageClicked() {

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            String url = listModel.get(itemList.getSelectedIndex()).getUrl();
            try {
                Desktop.getDesktop().browse(java.net.URI.create(url));
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

    /**
     * Configure UI.
     */
    private void configureUI() {
        setLayout(new BorderLayout());
        JScrollPane scroll = (new JScrollPane(itemList));
        JPanel control = makeControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10, 16, 0, 16));
        add(control, BorderLayout.NORTH);
        JPanel board = new JPanel();
        board.setLayout(new BorderLayout());
        JMenuBarUI();
        add(JToolBarUI(), BorderLayout.NORTH);
        board.add(scroll, BorderLayout.CENTER);
        itemList.setCellRenderer(new ItemRenderer());
        add(board, BorderLayout.CENTER);
        msgBar.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 0));
        add(msgBar, BorderLayout.SOUTH);
    }

    ImageIcon checkIcon = new ImageIcon(getClass().getClassLoader().getResource("image/" + "blue check.png"));
    ImageIcon addIcon = new ImageIcon(getClass().getClassLoader().getResource("image/blue +.png"));
    ImageIcon searchIcon = new ImageIcon(getClass().getClassLoader().getResource("image/blue search.png"));
    ImageIcon firstIcon = new ImageIcon(getClass().getClassLoader().getResource("image/blue back.png"));
    ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("image/blue forward.png"));
    ImageIcon selectedIcon = new ImageIcon(getClass().getClassLoader().getResource("image/green check.png"));
    ImageIcon webIcon = new ImageIcon(getClass().getClassLoader().getResource("image/green file.png"));
    ImageIcon editIcon = new ImageIcon(getClass().getClassLoader().getResource("image/green pencil.png"));
    ImageIcon deleteIcon = new ImageIcon(getClass().getClassLoader().getResource("image/green -.png"));
    ImageIcon questionIcon = new ImageIcon(getClass().getClassLoader().getResource("image/" + "blue check.png"));

    Action addAction = new AbstractAction("Add an item", addIcon) {
        @Override
        public void actionPerformed(ActionEvent e) {
            add(dialog);
            System.out.println("Adding");
        }

    };
    Action checkAction = new AbstractAction("Check all Prices", checkIcon) {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Checking Prices");
        }
    };
    Action searchAction = new AbstractAction("Search", searchIcon) {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Search item");
        }
    };
    Action firstAction = new AbstractAction("First", firstIcon) {
        @Override
        public void actionPerformed(ActionEvent e) {
            itemList.setSelectedIndex(0);
            if (itemList.getSelectedIndex() > -1) {
                itemList.setSelectedIndex(0);
            }
        }
    };
    Action backAction = new AbstractAction("Back", backIcon) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (itemList.getSelectedIndex() > -1) {
                itemList.setSelectedIndex(listModel.getSize() - 1);
            }
        }
    };
    Action selectedAction = new AbstractAction("New", selectedIcon) {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("New File");
        }
    };
    Action webAction = new AbstractAction("New", webIcon) {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewPageClicked();
        }
    };
    Action editAction = new AbstractAction("New", editIcon) {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("New File");
        }
    };
    Action deleteAction = new AbstractAction("New", deleteIcon) {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("New File");
        }
    };
    Action aboutAction = new AbstractAction("About", questionIcon) {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "PriceWatcher, version 13.1");
        }
    };

    private JToolBar JToolBarUI() {
        JToolBar toolBar = new JToolBar("Tool bar");
        toolBar.add(checkAction);
        toolBar.add(addAction);
        toolBar.add(searchAction);
        toolBar.add(firstAction);
        toolBar.add(backAction);
        toolBar.addSeparator();
        toolBar.add(selectedAction);
        toolBar.add(webAction);
        toolBar.add(editAction);
        toolBar.add(deleteAction);
        toolBar.addSeparator();
        toolBar.add(aboutAction);
        return toolBar;
    }

    private void JMenuBarUI() {
        JMenuBar menuBar = new JMenuBar();
        /**
         *
         * Create Menus
         *
         */
        JMenu appMenu = new JMenu("App");
        JMenu itemMenu = new JMenu("Item");
        JMenu sortMenu = new JMenu("Sort");

        // App
        JMenuItem aboutMenuItem = new JMenuItem("About");

        aboutMenuItem.setMnemonic(KeyEvent.VK_N);
        aboutMenuItem.setAction(aboutAction);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("Exit");

        // Item
        JMenuItem checkMenuItem = new JMenuItem("Check Prices");
        checkMenuItem.setAction(checkAction);
        JMenuItem addMenuItem = new JMenuItem("Add Item");
        addMenuItem.setAction(addAction);
        JMenuItem searchMenuItem = new JMenuItem("Search");
        searchMenuItem.setAction(searchAction);
        JMenuItem firstMenuItem = new JMenuItem("Search first");
        firstMenuItem.setAction(firstAction);
        JMenuItem lastMenuItem = new JMenuItem("Search last");
        lastMenuItem.setAction(backAction);

        // Sort
        JMenuItem addOldItem = new JMenuItem("Added Oldest");
        addOldItem.setActionCommand("Added Oldest");
        JMenuItem addNewItem = new JMenuItem("Added newest");
        addNewItem.setActionCommand("Added Newest");
        JMenuItem nameAscItem = new JMenuItem("Name ascending");
        nameAscItem.setActionCommand("Name ascending");
        JMenuItem nameDesItem = new JMenuItem("Name descending");
        nameDesItem.setActionCommand("Name descending");
        JMenuItem priceChangeItem = new JMenuItem("Price change (%)");
        priceChangeItem.setActionCommand("Price change (%)");
        JMenuItem priceLowItem = new JMenuItem("Price low ($)");
        priceLowItem.setActionCommand("Price low ($)");
        JMenuItem priceHighItem = new JMenuItem("Price high ($)");
        priceHighItem.setActionCommand("Price high ($)");

        /**
         *
         * JMenuItem Action Listeners
         *
         */
        // app
        aboutMenuItem.addActionListener(menuItemListener);
        exitMenuItem.addActionListener(menuItemListener);

        // Item
        checkMenuItem.addActionListener(menuItemListener);
        addMenuItem.addActionListener(menuItemListener);
        searchMenuItem.addActionListener(menuItemListener);
        firstMenuItem.addActionListener(menuItemListener);
        lastMenuItem.addActionListener(menuItemListener);

        // Sort
        addOldItem.addActionListener(menuItemListener);
        addNewItem.addActionListener(menuItemListener);
        nameAscItem.addActionListener(menuItemListener);
        nameDesItem.addActionListener(menuItemListener);
        priceChangeItem.addActionListener(menuItemListener);
        priceLowItem.addActionListener(menuItemListener);
        priceHighItem.addActionListener(menuItemListener);

        menuBar.add(appMenu);
        menuBar.add(itemMenu);
        menuBar.add(sortMenu);

        appMenu.add(aboutMenuItem);
        appMenu.addSeparator();
        appMenu.add(exitMenuItem);

        itemMenu.add(checkMenuItem);
        itemMenu.add(addMenuItem);
        itemMenu.addSeparator();
        itemMenu.add(searchMenuItem);
        itemMenu.add(firstMenuItem);
        itemMenu.add(lastMenuItem);
        itemMenu.addSeparator();

        sortMenu.add(addOldItem);
        sortMenu.add(addNewItem);
        sortMenu.addSeparator();
        sortMenu.add(nameAscItem);
        sortMenu.add(nameDesItem);
        sortMenu.addSeparator();
        sortMenu.add(priceChangeItem);
        sortMenu.add(priceLowItem);
        sortMenu.add(priceHighItem);
        setJMenuBar(menuBar);
    }

    /**
     * Create a control panel consisting of a refresh button.
     */
    private JPanel makeControlPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JButton refreshButton = new JButton("Refresh Check prices");
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonClicked);
        panel.add(refreshButton);
        return panel;
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(Main::new);
    }
}
