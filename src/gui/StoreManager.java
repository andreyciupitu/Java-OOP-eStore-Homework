package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import store.*;
import store.interfaces.Observer;
import store.items.Item;
import store.strategy.StrategyFactory;

public class StoreManager extends JPanel{
	private Store store = Store.getInstance();
	private ListModel listModel = new ListModel();
	private JTextField textField_ID;
	private JTextField textField_Price;
	private JTextField textField_DepartmentID;
	private JTextField textField_Name;
	private JList<String> list;

	/**
	 * Create the panel.
	 */
	public StoreManager(){
		/* Init Panel */
		setBackground(new Color(245, 255, 250));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{103, 197, 285, 0};
		gridBagLayout.rowHeights = new int[]{44, 41, 26, 0, 0, 0, 12, 31, 28, 28, 28, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		/* Sorting Method Label */
		JLabel lblSortingMethod = new JLabel("Sorting method:");
		lblSortingMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblSortingMethod.setFont(new Font("Calibri", Font.BOLD, 18));
		GridBagConstraints gbc_lblSortingMethod = new GridBagConstraints();
		gbc_lblSortingMethod.insets = new Insets(0, 0, 5, 5);
		gbc_lblSortingMethod.gridx = 1;
		gbc_lblSortingMethod.gridy = 0;
		add(lblSortingMethod, gbc_lblSortingMethod);
		
		/* Init JScrollPane to contain the JList */
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridheight = 11;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		/* JList of Store items */
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Calibri", Font.PLAIN, 16));
		list.addListSelectionListener(new ListSelectionListener(){
			
			public void valueChanged(ListSelectionEvent arg0){
				if (!arg0.getValueIsAdjusting()){
					if (list.getSelectedIndex() == -1)
						return;
					Item item = listModel.getItem(list.getSelectedIndex());
					
					/* Update TextFields based on selection */
					if (item != null){
						textField_Name.setText(item.getName());
						textField_Name.setToolTipText(item.getName());
						textField_ID.setText(item.getId().toString());
						textField_Price.setText(item.getPrice().toString());
						textField_DepartmentID.setText("#" + item.getDepartmentId() + " - " 
								+ store.getDepartment(item.getDepartmentId()).getName());
					}
				}
			}
			
		});
		scrollPane.setViewportView(list);
		
		/* JComboBox for sorting options */
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				if (!(arg0.getSource() instanceof JComboBox<?>))
					return;
				JComboBox<String> tmp = (JComboBox<String>)arg0.getSource();
				String selected = (String)tmp.getSelectedItem();
				switch (selected){
					case "Alphabetical":
						listModel.sort(new ListModel.AlphabeticSorter());
						break;
					case "By Price (asc.)":
						listModel.sort(new ListModel.AscendingPriceSorter());
						break;
					case "By Price (desc.)":
						listModel.sort(new ListModel.DescendingPriceSorter());
						break;
					default:
						break;
				}
				
				/* Clear TextFields */
				list.clearSelection();
				textField_Name.setText("");
				textField_Name.setToolTipText("");
				textField_ID.setText("");
				textField_Price.setText("");
				textField_DepartmentID.setText("");
			}
			
		});
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 19));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Alphabetical", 
				"By Price (asc.)", "By Price (desc.)"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		add(comboBox, gbc_comboBox);
		
		/* Name Label */
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		add(lblName, gbc_lblName);
		
		/* Name TextField */
		textField_Name = new JTextField();
		textField_Name.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Name.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_Name.setEditable(false);
		textField_Name.setToolTipText("");
		GridBagConstraints gbc_textField_Name = new GridBagConstraints();
		gbc_textField_Name.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Name.fill = GridBagConstraints.BOTH;
		gbc_textField_Name.gridx = 1;
		gbc_textField_Name.gridy = 2;
		add(textField_Name, gbc_textField_Name);
		textField_Name.setColumns(10);
		
		/* ID Label */
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 3;
		add(lblId, gbc_lblId);
		
		/* ID TextField */
		textField_ID = new JTextField();
		textField_ID.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_ID.setHorizontalAlignment(SwingConstants.CENTER);
		textField_ID.setEditable(false);
		textField_ID.setToolTipText("");
		GridBagConstraints gbc_textField_ID = new GridBagConstraints();
		gbc_textField_ID.insets = new Insets(0, 0, 5, 5);
		gbc_textField_ID.fill = GridBagConstraints.BOTH;
		gbc_textField_ID.gridx = 1;
		gbc_textField_ID.gridy = 3;
		add(textField_ID, gbc_textField_ID);
		textField_ID.setColumns(10);
		
		/* Proce Label */
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 4;
		add(lblPrice, gbc_lblPrice);
		
		/* Price TextField */
		textField_Price = new JTextField();
		textField_Price.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_Price.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Price.setEditable(false);
		textField_Price.setToolTipText("");
		GridBagConstraints gbc_textField_Price = new GridBagConstraints();
		gbc_textField_Price.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Price.fill = GridBagConstraints.BOTH;
		gbc_textField_Price.gridx = 1;
		gbc_textField_Price.gridy = 4;
		add(textField_Price, gbc_textField_Price);
		textField_Price.setColumns(10);
		
		/* Department Button */
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 5;
		add(lblDepartment, gbc_lblDepartment);
		
		/* DepartmentID TextField */
		textField_DepartmentID = new JTextField();
		textField_DepartmentID.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_DepartmentID.setHorizontalAlignment(SwingConstants.CENTER);
		textField_DepartmentID.setEditable(false);
		textField_DepartmentID.setToolTipText("");
		GridBagConstraints gbc_textField_DepartmentID = new GridBagConstraints();
		gbc_textField_DepartmentID.insets = new Insets(0, 0, 5, 5);
		gbc_textField_DepartmentID.fill = GridBagConstraints.BOTH;
		gbc_textField_DepartmentID.gridx = 1;
		gbc_textField_DepartmentID.gridy = 5;
		add(textField_DepartmentID, gbc_textField_DepartmentID);
		textField_DepartmentID.setColumns(10);
		
		/* Add Item Button */
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setToolTipText("");
		btnAddItem.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent arg0){
				/* Open Input Dialogue Window */
				InputData dialog = new InputData();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
				/* If input was provided, add the item */
				if (!dialog.hasFailed()){
					int itemId = 0;
					int departmentId = 0;
					double price = 0;
					String name = dialog.getItemName();
					
					/* Check if input was correct */
					try{
						itemId = Integer.parseInt(dialog.getItemId());
						departmentId = Integer.parseInt(dialog.getDepartmentId());
						price = Double.parseDouble(dialog.getItemPrice());
					}
					catch (NumberFormatException err){
						JOptionPane.showMessageDialog(StoreManager.this, "IDs and Price can only be numbers!");
						return;
					}
					
					/* Check if department is valid */
					if (store.getDepartment(departmentId) == null){
						JOptionPane.showMessageDialog(StoreManager.this, "Not a valid department!");
						return;
					}
					
					/* Add the item */
					Item newItem = new Item(name, itemId, price, departmentId);
					if (listModel.contains(newItem)){
						JOptionPane.showMessageDialog(StoreManager.this, "Item is already in the Store!");
						return;
					}
					list.clearSelection(); // SO IT DOESN'T CRASH
					listModel.addElement(newItem);
					comboBox.setSelectedIndex(comboBox.getSelectedIndex());
					list.setSelectedValue(newItem.getName(), true);
					store.getDepartment(departmentId).addItem(newItem);
				}
			}
			
		});
		
		/* Add to Cart Button */
		JButton btnAddToCart = new JButton("");
		btnAddToCart.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e){
				int index = list.getSelectedIndex();
				if (index == -1){
					JOptionPane.showMessageDialog(StoreManager.this, "No item was selected!");
					return;
				}

				Item selected = listModel.getItem(index);
				String input = JOptionPane.showInputDialog(StoreManager.this, "Please enter the customer name:");
				if (input == null)
					return;
				Customer client = store.getCustomers().get(input);
				if (client == null){
					client = new Customer(input, 2000.0, StrategyFactory.getInstance().createStrategy("A"));
					WishListPane.getInstance().getComboBoxModel().addElement(client);
				}
				CartPane.getInstance().addItemToList(client, selected);
			}
			
		});
		btnAddToCart.setIcon(new ImageIcon("resources/shopping-cart.png"));
		btnAddToCart.setToolTipText("Add to Shopping Cart.");
		GridBagConstraints gbc_btnAddToCart = new GridBagConstraints();
		gbc_btnAddToCart.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddToCart.gridx = 0;
		gbc_btnAddToCart.gridy = 8;
		add(btnAddToCart, gbc_btnAddToCart);
		btnAddItem.setFont(new Font("Calibri", Font.BOLD, 16));
		btnAddItem.setMaximumSize(new Dimension(1024, 150));
		GridBagConstraints gbc_btnAddItem = new GridBagConstraints();
		gbc_btnAddItem.fill = GridBagConstraints.BOTH;
		gbc_btnAddItem.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddItem.gridx = 1;
		gbc_btnAddItem.gridy = 8;
		add(btnAddItem, gbc_btnAddItem);
		
		/* Delete Item Button */
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.setToolTipText("");
		btnDeleteItem.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e){
				int index = list.getSelectedIndex();
				if (index == -1){
					JOptionPane.showMessageDialog(StoreManager.this, "No item was selected!");
					return;
				}
				
				/* Delete the item */
				Item selected = listModel.getItem(index);
				listModel.removeElement(selected);
				WishListPane wListPane = WishListPane.getInstance();
				CartPane cartPane = CartPane.getInstance();
				for (Observer o : store.getDepartment(selected.getDepartmentId()).getSubscribers()){
					wListPane.removeFromJList((Customer)o, selected);
					cartPane.removeFromJList((Customer)o, selected);
				}
				store.getDepartment(selected.getDepartmentId()).removeItem(selected.getId());
				
				/*Update TextFields */
				textField_Name.setText("");
				textField_Name.setToolTipText("");
				textField_ID.setText("");
				textField_Price.setText("");
				textField_DepartmentID.setText("");
			}
			
		});
		
		/* Add to WishList Button */
		JButton btnAddtowlist = new JButton("");
		btnAddtowlist.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e){
				int index = list.getSelectedIndex();
				if (index == -1){
					JOptionPane.showMessageDialog(StoreManager.this, "No item was selected!");
					return;
				}

				Item selected = listModel.getItem(index);
				String input = JOptionPane.showInputDialog(StoreManager.this, "Please enter the customer name:");
				if (input == null)
					return;
				Customer client = store.getCustomers().get(input);
				if (client == null){
					client = new Customer(input, 2000.0, StrategyFactory.getInstance().createStrategy("A"));
					WishListPane.getInstance().getComboBoxModel().addElement(client);
				}
				WishListPane.getInstance().addItemToList(client, selected);
			}
			
		});
		btnAddtowlist.setIcon(new ImageIcon("resources/Wishlist.png"));
		btnAddtowlist.setToolTipText("Add to WishList.");
		GridBagConstraints gbc_btnAddtowlist = new GridBagConstraints();
		gbc_btnAddtowlist.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddtowlist.gridx = 0;
		gbc_btnAddtowlist.gridy = 9;
		add(btnAddtowlist, gbc_btnAddtowlist);
		btnDeleteItem.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_btnDeleteItem = new GridBagConstraints();
		gbc_btnDeleteItem.fill = GridBagConstraints.BOTH;
		gbc_btnDeleteItem.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteItem.gridx = 1;
		gbc_btnDeleteItem.gridy = 9;
		add(btnDeleteItem, gbc_btnDeleteItem);
		
		/* Modify Item Button */
		JButton btnNewButton = new JButton("Modify Item");
		btnNewButton.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e){
				Double price = 0.0;
				int index = list.getSelectedIndex();
				if (index == -1){
					JOptionPane.showMessageDialog(StoreManager.this, "No item was selected!");
					return;
				}
				Item selected = listModel.getItem(index);
				
				/* Show Error Message */
				String input = JOptionPane.showInputDialog(StoreManager.this, "Please enter the new price:");
				if (input == null)
					return;
				try{
					price = Double.parseDouble(input);
				}
				catch (NumberFormatException err){
					JOptionPane.showMessageDialog(StoreManager.this, "You can only enter a number!");
					return;
				}
				
				/* Modify Element */
				listModel.modifyElement(selected, price);
				comboBox.setSelectedIndex(comboBox.getSelectedIndex());
				list.setSelectedValue(selected.getName(), true);		
				WishListPane wListPane = WishListPane.getInstance();
				CartPane cartPane = CartPane.getInstance();
				for (Observer o : store.getDepartment(selected.getDepartmentId()).getSubscribers()){
					wListPane.modifyInJList((Customer)o, selected, price);
					cartPane.modifyInJList((Customer)o, selected, price);
				}
				store.getDepartment(selected.getDepartmentId()).modifyItem(selected.getId(), price);
				
				/* Update TextField */
				textField_Price.setText(price.toString());
			}
			
		});
		btnNewButton.setToolTipText("");
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 10;
		add(btnNewButton, gbc_btnNewButton);
	}
	
	public void setListModel(ListModel listModel){
		this.listModel = listModel;
		list.setModel(listModel);
	}
}
