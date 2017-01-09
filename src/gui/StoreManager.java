package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import store.*;
import store.department.Department;
import store.items.Item;

public class StoreManager extends JPanel{
	private Store store = Store.getInstance();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public StoreManager(){
		setBackground(new Color(245, 255, 250));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 131, 448, 0};
		gridBagLayout.rowHeights = new int[]{44, 41, 31, 12, 26, 0, 0, 0, 28, 28, 28, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSortingMethod = new JLabel("Sorting method:");
		lblSortingMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblSortingMethod.setFont(new Font("Calibri", Font.BOLD, 18));
		GridBagConstraints gbc_lblSortingMethod = new GridBagConstraints();
		gbc_lblSortingMethod.insets = new Insets(0, 0, 5, 5);
		gbc_lblSortingMethod.gridx = 1;
		gbc_lblSortingMethod.gridy = 0;
		add(lblSortingMethod, gbc_lblSortingMethod);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridheight = 11;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		DefaultListModel<Item> listModel = new DefaultListModel<Item>();
		for (Map.Entry<Integer, Department> d : store.getDepartments().entrySet())
			for (Map.Entry<Integer, Item> item : d.getValue().getItems().entrySet()){
				System.out.println(item.getValue());
				listModel.addElement(item.getValue());
			}
		JList<Item> list = new JList<Item>(listModel);
		scrollPane.setViewportView(list);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 19));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Alphabetical", "By Price (asc.)", "By Price (desc.)"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		add(comboBox, gbc_comboBox);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_3.setEditable(false);
		textField_3.setToolTipText("Name");
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 4;
		add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		textField = new JTextField();
		textField.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setToolTipText("ID");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 5;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setToolTipText("Price");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 6;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setToolTipText("Department");
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 7;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(new Font("Calibri", Font.BOLD, 16));
		btnAddItem.setMaximumSize(new Dimension(1024, 150));
		GridBagConstraints gbc_btnAddItem = new GridBagConstraints();
		gbc_btnAddItem.fill = GridBagConstraints.BOTH;
		gbc_btnAddItem.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddItem.gridx = 1;
		gbc_btnAddItem.gridy = 8;
		add(btnAddItem, gbc_btnAddItem);
		
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_btnDeleteItem = new GridBagConstraints();
		gbc_btnDeleteItem.fill = GridBagConstraints.BOTH;
		gbc_btnDeleteItem.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteItem.gridx = 1;
		gbc_btnDeleteItem.gridy = 9;
		add(btnDeleteItem, gbc_btnDeleteItem);
		
		JButton btnNewButton = new JButton("Modify Item");
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 10;
		add(btnNewButton, gbc_btnNewButton);

	}
}
