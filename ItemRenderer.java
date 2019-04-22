package pricewatcher.base;
import java.awt.Component;
import javax.swing.*;

public class ItemRenderer extends JLabel implements ListCellRenderer<Item>{

    public ItemRenderer() {
        setOpaque(true);
    }
    
	@Override
	public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index, boolean isSelected,
			boolean cellHasFocus) {
		// TODO Auto-generated method stub
		ItemView itemView = new ItemView(item);
       
		
		if (isSelected) {
            		setBackground(list.getSelectionBackground());
            		setForeground(list.getSelectionForeground());
       		} else {
            		setBackground(list.getBackground());
           		setForeground(list.getForeground());
       		}
		return itemView;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	

}
