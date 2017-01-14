package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ListIterator;

import javax.swing.event.*;

import store.Customer;
import store.Store;
import store.items.Item;

public class WishListPane extends JPanel{
	private static WishListPane wListPane = null;
	private Store store = Store.getInstance();
	private JTextField textField_Name;
	private JTextField textField_Id;
	private JTextField textField_Price;
	private JTextField textField_Department;
	private DefaultComboBoxModel<Customer> comboBoxModel;
	private HashMap<Customer, ListModel> listModels;
	private JList<String> list;
	private JComboBox<Customer> comboBox;
	
	public static WishListPane getInstance(){
		if (wListPane == null)
			wListPane = new WishListPane();
		return wListPane;
	}

	/**
	 * Create the panel.
	 */
	private WishListPane(){
		/* Init Panel */
		setBackground(new Color(245, 255, 250));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{117, 254, 0, 0};
		gridBagLayout.rowHeights = new int[]{60, 0, 0, 0, 0, 41, 39, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		/* Customer Label */
		JLabel lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
		gbc_lblCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomer.gridx = 0;
		gbc_lblCustomer.gridy = 0;
		add(lblCustomer, gbc_lblCustomer);
		
		/* Customer JComboBox */
		listModels = new HashMap<Customer, ListModel>();
		comboBoxModel = new DefaultComboBoxModel<Customer>();
		comboBox = new JComboBox<Customer>(comboBoxModel);
		comboBox.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				if (!(arg0.getSource() instanceof JComboBox<?>))
					return;
				JComboBox<Customer> box = (JComboBox<Customer>)arg0.getSource();
				ListModel tmpListModel = listModels.get(box.getSelectedItem());
				if (tmpListModel == null)
					tmpListModel = createListModel((Customer)box.getSelectedItem());
				list.setModel(tmpListModel);
				
				/* Reset TextFields */
				textField_Name.setText("");
				textField_Name.setToolTipText("");
				textField_Id.setText("");
				textField_Price.setText("");
				textField_Department.setText("");
			}
			
		});
		comboBox.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
		/* Init JScrollPane to contain the JList */
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 8;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		/* WishList*/
		list = new JList<String>();
		list.addListSelectionListener(new ListSelectionListener(){
			
			public void valueChanged(ListSelectionEvent arg0){
				if (!arg0.getValueIsAdjusting()){
					if (list.getSelectedIndex() == -1)
						return;
					Item item = ((ListModel)list.getModel()).getItem(list.getSelectedIndex());
					
					/* Update TextFields based on selection */
					if (item != null){
						textField_Name.setText(item.getName());
						textField_Name.setToolTipText(item.getName());
						textField_Id.setText(item.getId().toString());
						textField_Price.setText(item.getPrice().toString());
						textField_Department.setText("#" + item.getDepartmentId() + " - " 
								+ store.getDepartment(item.getDepartmentId()).getName());
					}
				}
			}
			
		});
		scrollPane.setViewportView(list);
		
		/* Item Name Label */
		JLabel lblItemName = new JLabel("Name:");
		lblItemName.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName.gridx = 0;
		gbc_lblItemName.gridy = 1;
		add(lblItemName, gbc_lblItemName);
		
		/* Item Name TextField */
		textField_Name = new JTextField();
		textField_Name.setToolTipText("");
		textField_Name.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Name.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_Name.setEditable(false);
		GridBagConstraints gbc_textField_Name = new GridBagConstraints();
		gbc_textField_Name.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Name.fill = GridBagConstraints.BOTH;
		gbc_textField_Name.gridx = 1;
		gbc_textField_Name.gridy = 1;
		add(textField_Name, gbc_textField_Name);
		textField_Name.setColumns(10);
		
		/* Item ID Label */
		JLabel lblItemId = new JLabel("ID:");
		lblItemId.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemId = new GridBagConstraints();
		gbc_lblItemId.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemId.gridx = 0;
		gbc_lblItemId.gridy = 2;
		add(lblItemId, gbc_lblItemId);
		
		/* Item ID TextField */
		textField_Id = new JTextField();
		textField_Id.setToolTipText("");
		textField_Id.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Id.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_Id.setEditable(false);
		GridBagConstraints gbc_textField_Id = new GridBagConstraints();
		gbc_textField_Id.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Id.fill = GridBagConstraints.BOTH;
		gbc_textField_Id.gridx = 1;
		gbc_textField_Id.gridy = 2;
		add(textField_Id, gbc_textField_Id);
		textField_Id.setColumns(10);
		
		/* Item Price Label */
		JLabel lblItemPrice = new JLabel("Price:");
		lblItemPrice.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemPrice = new GridBagConstraints();
		gbc_lblItemPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemPrice.gridx = 0;
		gbc_lblItemPrice.gridy = 3;
		add(lblItemPrice, gbc_lblItemPrice);
		
		/* Item Price TextField */
		textField_Price = new JTextField();
		textField_Price.setToolTipText("");
		textField_Price.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_Price.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Price.setEditable(false);
		GridBagConstraints gbc_textField_Price = new GridBagConstraints();
		gbc_textField_Price.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Price.fill = GridBagConstraints.BOTH;
		gbc_textField_Price.gridx = 1;
		gbc_textField_Price.gridy = 3;
		add(textField_Price, gbc_textField_Price);
		textField_Price.setColumns(10);
		
		/* Department Label */
		JLabel lblDepartmentId = new JLabel("Department:");
		lblDepartmentId.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblDepartmentId = new GridBagConstraints();
		gbc_lblDepartmentId.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartmentId.gridx = 0;
		gbc_lblDepartmentId.gridy = 4;
		add(lblDepartmentId, gbc_lblDepartmentId);
		
		/* Department TextField */
		textField_Department = new JTextField();
		textField_Department.setToolTipText("");
		textField_Department.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Department.setFont(new Font("Calibri", Font.PLAIN, 16));
		textField_Department.setEditable(false);
		GridBagConstraints gbc_textField_Department = new GridBagConstraints();
		gbc_textField_Department.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Department.fill = GridBagConstraints.BOTH;
		gbc_textField_Department.gridx = 1;
		gbc_textField_Department.gridy = 4;
		add(textField_Department, gbc_textField_Department);
		textField_Department.setColumns(10);
		
		/* Add Item Button */
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int id = 0;
				
				/* Show Error Message */
				String input = JOptionPane.showInputDialog(WishListPane.this, "Please enter the item's ID:");
				if (input == null)
					return;
				try{
					id = Integer.parseInt(input);
				}
				catch (NumberFormatException err){
					JOptionPane.showMessageDialog(WishListPane.this, "You can only enter a number!");
					return;
				}
				
				/* Add Element */
				Customer client = (Customer)comboBox.getSelectedItem();
				Item item = store.getItem(id).clone();
				addItemToList(client, item);
			}
			
		});
		
		/* Delete Item Button */
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent arg0){
				int index = list.getSelectedIndex();
				if (index == -1){
					JOptionPane.showMessageDialog(WishListPane.this, "No item was selected!");
					return;
				}
				
				/* Delete the item */
				Customer client = (Customer)comboBox.getSelectedItem();
				Item selected = listModels.get(client).getItem(index);
				removeFromJList(client, selected);
				client.removeFromWList(selected);
			}
			
		});
		
		/* Notifications Button */
		JButton btnNotifications = new JButton("");
		btnNotifications.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e){
				NotificationDialog dialog = new NotificationDialog((Customer)comboBox.getSelectedItem());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			
		});
		btnNotifications.setIcon(new ImageIcon("resources\\1484369307_43.Bell.png"));
		GridBagConstraints gbc_btnNotifications = new GridBagConstraints();
		gbc_btnNotifications.insets = new Insets(0, 0, 0, 5);
		gbc_btnNotifications.gridx = 0;
		gbc_btnNotifications.gridy = 7;
		add(btnNotifications, gbc_btnNotifications);
		btnDeleteItem.setToolTipText("Remove an item from the list.");
		btnDeleteItem.setFont(new Font("Calibri", Font.BOLD, 18));
		GridBagConstraints gbc_btnDeleteItem = new GridBagConstraints();
		gbc_btnDeleteItem.fill = GridBagConstraints.BOTH;
		gbc_btnDeleteItem.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteItem.gridx = 1;
		gbc_btnDeleteItem.gridy = 7;
		add(btnDeleteItem, gbc_btnDeleteItem);
		
		/* Add to Shopping Cart Button */
		JButton btnAddToCart = new JButton("");
		btnAddToCart.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent arg0){
				int index = list.getSelectedIndex();
				if (index == -1){
					JOptionPane.showMessageDialog(WishListPane.this, "No item was selected!");
					return;
				}
			
				/* Delete the item from the WishList */
				Customer client = (Customer)comboBox.getSelectedItem();
				Item selected = listModels.get(client).getItem(index);
				removeFromJList(client, selected);
				client.removeFromWList(selected);
				
				/* Add the item to the Shopping Cart */
				CartPane.getInstance().addItemToList(client, selected);
				
			}
			
		});
		btnAddToCart.setToolTipText("Add to Shopping Cart.");
		btnAddToCart.setIcon(new ImageIcon("resources/shopping-cart.png"));
		GridBagConstraints gbc_btnAddToCart = new GridBagConstraints();
		gbc_btnAddToCart.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddToCart.gridx = 0;
		gbc_btnAddToCart.gridy = 6;
		add(btnAddToCart, gbc_btnAddToCart);
		btnAddItem.setToolTipText("Add an item to the list.");
		btnAddItem.setFont(new Font("Calibri", Font.BOLD, 18));
		GridBagConstraints gbc_btnAddItem = new GridBagConstraints();
		gbc_btnAddItem.fill = GridBagConstraints.BOTH;
		gbc_btnAddItem.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddItem.gridx = 1;
		gbc_btnAddItem.gridy = 6;
		add(btnAddItem, gbc_btnAddItem);
	}

	public void setComboBoxModel(DefaultComboBoxModel<Customer> comboBoxModel){
		this.comboBoxModel = comboBoxModel;
		comboBox.setModel(comboBoxModel);
		list.setModel(createListModel(comboBox.getItemAt(0)));
	}
	
	public DefaultComboBoxModel<Customer> getComboBoxModel(){
		return comboBoxModel;
	}
	
	/* Create the ListModel for a Customer's WishList */
	public ListModel createListModel(Customer c){
		ListModel newListModel = new ListModel();
		ListIterator<Item> it = c.getWlist().listIterator();
		while (it.hasNext())
			newListModel.addElement(it.next());
		listModels.put(c, newListModel);
		return newListModel;
	}

	/* Remove an item from the JList */
	public void removeFromJList(Customer client, Item selected){
		/* Delete Item from the ListModel */
		ListModel tmpListModel = listModels.get(client);
		if (tmpListModel == null)
			tmpListModel = createListModel(client);
		if (tmpListModel.removeElement(selected) == false)
			return;
		
		/* Update Shopping Cart Suggestion */
		CartPane cartPane = CartPane.getInstance();
		cartPane.updateSuggestion(client);
		
		/* Remove text from TextFields */
		textField_Name.setText("");
		textField_Name.setToolTipText("");
		textField_Id.setText("");
		textField_Price.setText("");
		textField_Department.setText("");
	}
	
	/* Modify an item in the JList */
	public void modifyInJList(Customer client, Item selected, Double price){
		/* Modify Item it the ListModel */
		ListModel tmpListModel = listModels.get(client);
		if (tmpListModel == null)
			tmpListModel = createListModel(client);
		if (tmpListModel.modifyElement(selected, price) == false)
			return;
		
		/* Update Shopping Cart Suggestion */
		CartPane cartPane = CartPane.getInstance();
		cartPane.updateSuggestion(client);
		
		/* Update TextFields */
		textField_Price.setText(price.toString());
	}
	
	/* Add item to WishList */
	public void addItemToList(Customer client, Item item){
		ListModel tmpListModel = listModels.get(client);
		if (tmpListModel == null)
			tmpListModel = createListModel(client);
		if (tmpListModel.contains(item)){
			JOptionPane.showMessageDialog(WishListPane.this, "Item already is in the WishList!");
			return;
		}
		tmpListModel.addElement(item);
		tmpListModel.sort(client.getWlist().getComparator());
		client.addToWList(item);
		
		/* Update Shopping Cart Suggestion */
		CartPane cartPane = CartPane.getInstance();
		cartPane.updateSuggestion(client);
	}
}
