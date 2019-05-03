package pricewatcher.base;


import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ItemRenderer extends JLabel implements ListCellRenderer<Item> {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ItemRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index, boolean isSelected,
            boolean cellHasFocus) {
        ItemView itemView = new ItemView(item);
        if (isSelected) {
            itemView.setBackground(list.getSelectionBackground());
            itemView.setForeground(list.getSelectionForeground());
        } else {
            itemView.setBackground(list.getBackground());
            itemView.setForeground(list.getForeground());
        }
        return itemView;

    }
}
