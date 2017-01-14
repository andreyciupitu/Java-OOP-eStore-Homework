package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

public class InputData extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private boolean failed = true;
	private String departmentId = null;
	private String itemId = null;
	private String itemName = null;
	private String itemPrice = null;
	private JTextField textField_2;
	private JTextField textField_3;
	

	/**
	 * Create the dialog.
	 */
	public InputData(){
		/* Init Frame */
		setTitle("Enter Item Details");
		setBounds(100, 100, 520, 333);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		
		/* Set Layout */
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{59, 170, 209, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{61, 33, 41, 28, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		/* Enter Item Name LABEL & TextField */
		{
			JLabel lblEnterItemName = new JLabel("Enter Item Name:");
			lblEnterItemName.setHorizontalAlignment(SwingConstants.CENTER);
			lblEnterItemName.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc_lblEnterItemName = new GridBagConstraints();
			gbc_lblEnterItemName.anchor = GridBagConstraints.WEST;
			gbc_lblEnterItemName.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterItemName.gridx = 1;
			gbc_lblEnterItemName.gridy = 0;
			contentPanel.add(lblEnterItemName, gbc_lblEnterItemName);
		}
		{
			textField_3 = new JTextField();
			textField_3.setHorizontalAlignment(SwingConstants.CENTER);
			textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textField_3.setColumns(10);
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.insets = new Insets(0, 0, 5, 5);
			gbc_textField_3.gridx = 2;
			gbc_textField_3.gridy = 0;
			contentPanel.add(textField_3, gbc_textField_3);
		}
		
		/* Enter Item ID Label & TextField */
		{
			JLabel lblNewLabel = new JLabel("Enter Item ID:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.gridx = 2;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		
		/* Enter Item Price label & TextField */
		{
			JLabel lblEnterItemPrice = new JLabel("Enter Item Price:");
			lblEnterItemPrice.setHorizontalAlignment(SwingConstants.CENTER);
			lblEnterItemPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc_lblEnterItemPrice = new GridBagConstraints();
			gbc_lblEnterItemPrice.anchor = GridBagConstraints.WEST;
			gbc_lblEnterItemPrice.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterItemPrice.gridx = 1;
			gbc_lblEnterItemPrice.gridy = 2;
			contentPanel.add(lblEnterItemPrice, gbc_lblEnterItemPrice);
		}
		{
			textField_2 = new JTextField();
			textField_2.setHorizontalAlignment(SwingConstants.CENTER);
			textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textField_2.setColumns(10);
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 5, 5);
			gbc_textField_2.gridx = 2;
			gbc_textField_2.gridy = 2;
			contentPanel.add(textField_2, gbc_textField_2);
		}
		
		/* Enter Department ID label & TextField */
		{
			JLabel lblEnterDepartmentId = new JLabel("Enter Department ID:");
			lblEnterDepartmentId.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblEnterDepartmentId.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblEnterDepartmentId = new GridBagConstraints();
			gbc_lblEnterDepartmentId.anchor = GridBagConstraints.WEST;
			gbc_lblEnterDepartmentId.insets = new Insets(0, 0, 0, 5);
			gbc_lblEnterDepartmentId.gridx = 1;
			gbc_lblEnterDepartmentId.gridy = 3;
			contentPanel.add(lblEnterDepartmentId, gbc_lblEnterDepartmentId);
		}
		{
			textField_1 = new JTextField();
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textField_1.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 0, 5);
			gbc_textField_1.gridx = 2;
			gbc_textField_1.gridy = 3;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
		}
		
		/* Confirmation buttons */
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				/* OK Button */
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter(){
					
					public void mouseClicked(MouseEvent e){
						failed = false;
						setItemId(textField.getText());
						setDepartmentId(textField_1.getText());
						setItemName(textField_3.getText());
						setItemPrice(textField_2.getText());
						dispose();
					}
					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				/* Cancel Button */
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter(){
					
					public void mouseClicked(MouseEvent e){
						dispose();
					}
					
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/* GETTERS & SETTERS */
	public String getDepartmentId(){
		return departmentId;
	}

	public void setDepartmentId(String departmentId){
		this.departmentId = departmentId;
	}

	public String getItemName(){
		return itemName;
	}

	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public String getItemPrice(){
		return itemPrice;
	}

	public void setItemPrice(String itemPrice){
		this.itemPrice = itemPrice;
	}

	public String getItemId(){
		return itemId;
	}


	public void setItemId(String itemId){
		this.itemId = itemId;
	}


	public boolean hasFailed(){
		return failed;
	}

}
