package gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

import gui.ListModel;
import store.Customer;
import store.Store;
import store.department.Department;
import store.items.Item;

import java.awt.event.*;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class CartPane extends JPanel{
	private static CartPane cartPane = null;
	private Store store = Store.getInstance();
	private HashMap<Customer, ListModel> listModels;
	private JTextField txtName;
	private JTextField txtId;
	private JTextField txtPrice;
	private JTextField txtDepartment;
	private JTextField textField;
	private JComboBox<Customer> comboBox;
	private JList<String> list;
	private DefaultComboBoxModel<Customer> comboBoxModel;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public static CartPane getInstance(){
		if (cartPane == null)
			cartPane = new CartPane();
		return cartPane;
	}
	
	/**
	 * Create the panel.
	 */
	private CartPane(){
		/* Init Panel */
		setBackground(new Color(245, 255, 250));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{95, 145, 232, 0, 0};
		gridBagLayout.rowHeights = new int[]{69, 37, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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
		
		/* Customer ComboBox */
		listModels = new HashMap<Customer, ListModel>();
		comboBox = new JComboBox<Customer>();
		comboBox.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				if (!(arg0.getSource() instanceof JComboBox<?>))
					return;
				JComboBox<Customer> box = (JComboBox<Customer>)arg0.getSource();
				Customer client = (Customer)box.getSelectedItem();
				ListModel tmpListModel = listModels.get(client);
				if (tmpListModel == null)
					tmpListModel = createListModel(client);
				list.setModel(tmpListModel);
				
				/* Update TextFields */
				updateSuggestion(client);
				textField_1.setText(client.getCart().getBudget().toString());
				txtName.setText("");
				txtName.setToolTipText("");
				txtId.setText("");
				txtPrice.setText("");
				txtDepartment.setText("");
			}
			
		});
		comboBox.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
		/* Budget Label */
		
		JLabel lblBudget = new JLabel("Budget:");
		lblBudget.setHorizontalAlignment(SwingConstants.CENTER);
		lblBudget.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_lblBudget = new GridBagConstraints();
		gbc_lblBudget.insets = new Insets(0, 0, 5, 0);
		gbc_lblBudget.gridx = 3;
		gbc_lblBudget.gridy = 0;
		add(lblBudget, gbc_lblBudget);
		
		/* Budget TextField */
		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 1;
		add(textField_1, gbc_textField_1);
		
		/* Item Name Label */
		JLabel lblItemName = new JLabel("Name:");
		lblItemName.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemName.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.fill = GridBagConstraints.VERTICAL;
		gbc_lblItemName.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName.gridx = 0;
		gbc_lblItemName.gridy = 2;
		add(lblItemName, gbc_lblItemName);
		
		/* Item Name TextField */
		txtName = new JTextField();
		txtName.setToolTipText("");
		txtName.setEditable(false);
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setFont(new Font("Calibri", Font.PLAIN, 14));
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.BOTH;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 2;
		add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		/* Suggestion Label */
		JLabel lblNewLabel = new JLabel("Suggestion:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		/* Item ID Label */
		JLabel lblItemId = new JLabel("ID:");
		lblItemId.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblItemId.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblItemId = new GridBagConstraints();
		gbc_lblItemId.fill = GridBagConstraints.VERTICAL;
		gbc_lblItemId.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemId.gridx = 0;
		gbc_lblItemId.gridy = 3;
		add(lblItemId, gbc_lblItemId);
		
		/* Item ID TextField */
		txtId = new JTextField();
		txtId.setToolTipText("");
		txtId.setEditable(false);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setFont(new Font("Calibri", Font.PLAIN, 14));
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.insets = new Insets(0, 0, 5, 5);
		gbc_txtId.fill = GridBagConstraints.BOTH;
		gbc_txtId.gridx = 1;
		gbc_txtId.gridy = 3;
		add(txtId, gbc_txtId);
		txtId.setColumns(10);
		
		/* Strategy Suggestion TextField */
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		/* Item Price Label */
		JLabel lblItemPrice = new JLabel("Price:");
		lblItemPrice.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblItemPrice.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblItemPrice = new GridBagConstraints();
		gbc_lblItemPrice.fill = GridBagConstraints.VERTICAL;
		gbc_lblItemPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemPrice.gridx = 0;
		gbc_lblItemPrice.gridy = 4;
		add(lblItemPrice, gbc_lblItemPrice);
		
		/* Item Price TextField */
		txtPrice = new JTextField();
		txtPrice.setToolTipText("");
		txtPrice.setEditable(false);
		txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrice.setFont(new Font("Calibri", Font.PLAIN, 14));
		GridBagConstraints gbc_txtPrice = new GridBagConstraints();
		gbc_txtPrice.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrice.fill = GridBagConstraints.BOTH;
		gbc_txtPrice.gridx = 1;
		gbc_txtPrice.gridy = 4;
		add(txtPrice, gbc_txtPrice);
		txtPrice.setColumns(10);
		
		/* Item Department Label */
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartment.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 5;
		add(lblDepartment, gbc_lblDepartment);
		
		/* Department TextField */
		txtDepartment = new JTextField();
		txtDepartment.setToolTipText("");
		txtDepartment.setEditable(false);
		txtDepartment.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtDepartment = new GridBagConstraints();
		gbc_txtDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_txtDepartment.fill = GridBagConstraints.BOTH;
		gbc_txtDepartment.gridx = 1;
		gbc_txtDepartment.gridy = 5;
		add(txtDepartment, gbc_txtDepartment);
		txtDepartment.setColumns(10);
		
		/* Add Item Button */
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent arg0){
				int id = 0;
				
				/* Show Error Message */
				String input = JOptionPane.showInputDialog(CartPane.this, "Please enter the item's ID:");
				if (input == null)
					return;
				try{
					id = Integer.parseInt(input);
				}
				catch (NumberFormatException err){
					JOptionPane.showMessageDialog(CartPane.this, "You can only enter a number!");
					return;
				}
				
				/* Add Element */
				Customer client = (Customer)comboBox.getSelectedItem();
				Item item = store.getItem(id).clone();
				addItemToList(client, item);
			}
			
		});
		btnAddItem.setToolTipText("");
		btnAddItem.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_btnAddItem = new GridBagConstraints();
		gbc_btnAddItem.fill = GridBagConstraints.BOTH;
		gbc_btnAddItem.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddItem.gridx = 1;
		gbc_btnAddItem.gridy = 7;
		add(btnAddItem, gbc_btnAddItem);
		
		/* Cart Total Label */
		JLabel lblCartTotal = new JLabel("Cart Total:");
		lblCartTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartTotal.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_lblCartTotal = new GridBagConstraints();
		gbc_lblCartTotal.insets = new Insets(0, 0, 5, 0);
		gbc_lblCartTotal.gridx = 3;
		gbc_lblCartTotal.gridy = 4;
		add(lblCartTotal, gbc_lblCartTotal);
		
		/* Cart Total TextField */
		textField_2 = new JTextField();
		textField_2.setToolTipText("");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 5;
		add(textField_2, gbc_textField_2);
		
		/* JScrollPane to hold the JList */
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridheight = 9;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		/* JList of items */
		list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener(){
			
			public void valueChanged(ListSelectionEvent arg0){
				if (!arg0.getValueIsAdjusting()){
					if (list.getSelectedIndex() == -1)
						return;
					Item item = ((ListModel)list.getModel()).getItem(list.getSelectedIndex());
					
					/* Update TextFields based on selection */
					if (item != null){
						txtName.setText(item.getName());
						txtName.setToolTipText(item.getName());
						txtId.setText(item.getId().toString());
						txtPrice.setText(item.getPrice().toString());
						txtDepartment.setText("#" + item.getDepartmentId() + " - " 
								+ store.getDepartment(item.getDepartmentId()).getName());
					}
				}
			}
			
		});
		scrollPane.setViewportView(list);
		
		/* Add Suggestion Button */
		JButton btnAddSuggestion = new JButton("Add Suggestion");
		btnAddSuggestion.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e){
				/* Apply Strategy */
				Customer client = (Customer)comboBox.getSelectedItem();
				WishListPane wListPane = WishListPane.getInstance();
				Item selected = client.getItem();
				if (selected == null){
					JOptionPane.showMessageDialog(CartPane.this, "No items in your WishList!");
					return;
				}
				wListPane.removeFromJList(client, selected);
				if (client.getCart().getMoneyLeft() > selected.getPrice())
					listModels.get(client).addElement(selected);
				else
					JOptionPane.showMessageDialog(CartPane.this, "Not enough money!");
				updateSuggestion(client);
			}
			
		});
		btnAddSuggestion.setToolTipText("Add the Suggestion to the Cart.");
		btnAddSuggestion.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_btnAddSuggestion = new GridBagConstraints();
		gbc_btnAddSuggestion.fill = GridBagConstraints.BOTH;
		gbc_btnAddSuggestion.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddSuggestion.gridx = 3;
		gbc_btnAddSuggestion.gridy = 7;
		add(btnAddSuggestion, gbc_btnAddSuggestion);
		
		/* Delete Item Button */
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e){
				int index = list.getSelectedIndex();
				if (index == -1){
					JOptionPane.showMessageDialog(CartPane.this, "No item was selected!");
					return;
				}
				
				/* Delete the item */
				Customer client = (Customer)comboBox.getSelectedItem();
				Item selected = listModels.get(client).getItem(index);
				removeFromJList(client, selected);
				client.removeFromCart(selected);
			}
			
		});
		btnDeleteItem.setToolTipText("");
		btnDeleteItem.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_btnDeleteItem = new GridBagConstraints();
		gbc_btnDeleteItem.fill = GridBagConstraints.BOTH;
		gbc_btnDeleteItem.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteItem.gridx = 1;
		gbc_btnDeleteItem.gridy = 8;
		add(btnDeleteItem, gbc_btnDeleteItem);
		
		/* Place Order Button */
		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e){
				Customer client = (Customer)comboBox.getSelectedItem();
				
				/* Ask for discounts */
				int result = JOptionPane.showConfirmDialog(CartPane.this, "Do you wish to apply discounts?");
				if (result == JOptionPane.YES_OPTION){
					for (Map.Entry<Integer, Department> d : store.getDepartments().entrySet())
						client.getCart().visit(d.getValue());
					JOptionPane.showMessageDialog(CartPane.this, "New total is: " + client.getCart().getTotalPrice());
				}
				
				/* Get a new Shopping Cart */
				client.setCart(store.getShoppingCart(client.getCart().getMoneyLeft()));
				
				/* Obtain a new ListModel */
				ListModel tmpListModel = createListModel(client);
				listModels.put(client, tmpListModel);
				list.setModel(tmpListModel);
				
				/* Update TextFields */
				textField_1.setText(client.getCart().getBudget().toString());
				textField_2.setText(client.getCart().getTotalPrice().toString());
			}
			
		});
		btnPlaceOrder.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_btnPlaceOrder = new GridBagConstraints();
		gbc_btnPlaceOrder.fill = GridBagConstraints.BOTH;
		gbc_btnPlaceOrder.gridx = 3;
		gbc_btnPlaceOrder.gridy = 8;
		add(btnPlaceOrder, gbc_btnPlaceOrder);
	}

	public void setComboBoxModel(DefaultComboBoxModel<Customer> comboBoxModel){
		this.comboBoxModel = comboBoxModel;
		comboBox.setModel(comboBoxModel);
		list.setModel(createListModel(comboBox.getItemAt(0)));
		
		/* Update TextFields */
		Item selected = comboBox.getItemAt(0).getWlist().executeStrategy();
		if (selected != null){
			textField.setText(selected.getName());
			textField.setToolTipText(selected.getName());
		}
		textField_1.setText(comboBox.getItemAt(0).getCart().getBudget().toString());
		textField_2.setText(comboBox.getItemAt(0).getCart().getTotalPrice().toString());
	}
	
	public DefaultComboBoxModel<Customer> getComboBoxModel(){
		return comboBoxModel;
	}
	
	/* Create the ListModel for a Customer's WishList */
	public ListModel createListModel(Customer c){
		ListModel newListModel = new ListModel();
		ListIterator<Item> it = c.getCart().listIterator();
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
		textField_2.setText(client.getCart().getTotalPrice().toString());
		
		/* Remove text from TextFields */
		txtName.setText("");
		txtName.setToolTipText("");
		txtId.setText("");
		txtPrice.setText("");
		txtDepartment.setText("");
	}
	
	/* Modify an item in the JList */
	public void modifyInJList(Customer client, Item selected, Double price){
		/* Modify Item it the ListModel */
		ListModel tmpListModel = listModels.get(client);
		if (tmpListModel == null)
			tmpListModel = createListModel(client);
		if (tmpListModel.modifyElement(selected, price) == false)
			return;
		tmpListModel.sort(client.getCart().getComparator());
		
		/* Update TextFields */
		txtPrice.setText(price.toString());
		textField_2.setText(client.getCart().getTotalPrice().toString());
	}
	
	/* Add Item to Cart */
	public void addItemToList(Customer client, Item item){
		ListModel tmpListModel = listModels.get(client);
		if (tmpListModel == null)
			tmpListModel = createListModel(client);
		if (tmpListModel.contains(item)){
			JOptionPane.showMessageDialog(CartPane.this, "Item already is in the Shopping Cart!");
			return;
		}
		if (client.addToCart(item)){
			tmpListModel.addElement(item);
			tmpListModel.sort(client.getCart().getComparator());
			if (tmpListModel.equals(list.getModel()))
				textField_2.setText(client.getCart().getTotalPrice().toString());
		}
		else
			JOptionPane.showMessageDialog(CartPane.this, "Not enough money!");
	}
	
	/* Update Suggestion TextFields */
	public void updateSuggestion(Customer client){
		Item newSuggestion = client.getWlist().executeStrategy();
		if (newSuggestion != null){
			textField.setText(newSuggestion.getName());
			textField.setToolTipText(textField.getText());				
		}
		else{
			textField.setText("");
			textField.setToolTipText("");
		}
		textField_2.setText(client.getCart().getTotalPrice().toString());
	}
}
