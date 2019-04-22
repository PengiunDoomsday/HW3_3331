package pricewatcher.base;
import java.awt.Component;
import javax.swing.*;

public class ItemRenderer extends JLabel implements ListCellRenderer<Item>{

	@Override
	public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index, boolean isSelected,
			boolean cellHasFocus) {
		// TODO Auto-generated method stub
		ItemView itemView = new ItemView(item);
		return itemView;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	

}
