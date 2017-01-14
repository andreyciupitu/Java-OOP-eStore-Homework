package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.util.Map;

import store.Customer;
import store.Store;
import store.department.Department;
import store.items.Item;

public class EntryFrame extends JFrame{

	private JPanel contentPane;
	private JPanel panel;
	private JTabbedPane tabbedPane;
	private StoreManager storeManager;
	private WishListPane wishListPane;
	private JLayeredPane layeredPane;
	private CartPane cartPane;
	private Store store = Store.getInstance();
	private String storeDataFilePath = "input\\store.txt";
	private String customerDataFilePath = "input\\customers.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					EntryFrame frame = new EntryFrame();
					frame.setVisible(true);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EntryFrame(){
		/* Init Frame */
		setTitle("Ciupi's Virtual Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 577);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		/* Add Layered Pane */
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		/* Construct Main Menu */
		panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		layeredPane.add(panel, "name_2189620832239330");
		panel.setBackground(new Color(245, 255, 250));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 598, 0, 0};
		gbl_panel.rowHeights = new int[]{75, 72, 73, 73, 72, 24, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		/* Exit Button */
		JButton btnNewButton = new JButton("Quit to Desktop");
		btnNewButton.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent arg0){
				if (!(arg0.getSource() instanceof JButton))
					return;
				int choice = JOptionPane.showConfirmDialog(EntryFrame.this, "Are you sure you want to exit?");
				if (choice == 0)
					System.exit(0);
			}
			
		});
		btnNewButton.setPreferredSize(new Dimension(612, 83));
		btnNewButton.setMaximumSize(new Dimension(1024, 150));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 4;
		panel.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.setToolTipText("Exit the application");
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 16));

		/* Store Title */
		JLabel lblAndreiVirtualStore = new JLabel("Ciupi's Virtual Store");
		lblAndreiVirtualStore.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblAndreiVirtualStore.setHorizontalAlignment(SwingConstants.CENTER);
		lblAndreiVirtualStore.setLabelFor(contentPane);
		lblAndreiVirtualStore.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		GridBagConstraints gbc_lblAndreiVirtualStore = new GridBagConstraints();
		gbc_lblAndreiVirtualStore.fill = GridBagConstraints.VERTICAL;
		gbc_lblAndreiVirtualStore.insets = new Insets(0, 0, 5, 5);
		gbc_lblAndreiVirtualStore.gridx = 1;
		gbc_lblAndreiVirtualStore.gridy = 0;
		panel.add(lblAndreiVirtualStore, gbc_lblAndreiVirtualStore);

		/* Open Store Button */
		JButton btnOpenStore = new JButton("Open Store");
		btnOpenStore.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent arg0){
				/* Load Store Data */
				Store.getInstance().loadDepartments(storeDataFilePath);
				Store.getInstance().loadCustomers(customerDataFilePath);
				storeManager.setListModel(createItemListModel());
				DefaultComboBoxModel<Customer> tmp = createCustomerComboBoxModel();
				wishListPane.setComboBoxModel(tmp);
				cartPane.setComboBoxModel(tmp);
				
				/* Show App */
				CardLayout layout = (CardLayout)layeredPane.getLayout();
				layout.next(layeredPane);
			}
			
		});
		btnOpenStore.setPreferredSize(new Dimension(612, 83));
		btnOpenStore.setMaximumSize(new Dimension(1024, 150));
		GridBagConstraints gbc_btnOpenStore = new GridBagConstraints();
		gbc_btnOpenStore.fill = GridBagConstraints.BOTH;
		gbc_btnOpenStore.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpenStore.gridx = 1;
		gbc_btnOpenStore.gridy = 1;
		panel.add(btnOpenStore, gbc_btnOpenStore);
		btnOpenStore.setToolTipText("Open the store with the current data");
		btnOpenStore.setFont(new Font("Calibri", Font.BOLD, 16));
		
		/* Customer Data File Button */
		JButton btnImportCustomerData = new JButton("Import Customer Data");
		btnImportCustomerData.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e){
				/* Open file chooser and set new file path */
				JFileChooser chooser = new JFileChooser("Select Customer Data File");
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("Customer Data Files (.txt, .in)", "txt", "in");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(chooser.getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION){
			    	customerDataFilePath = chooser.getSelectedFile().getAbsolutePath();
			    }
			}
	
		});
		btnImportCustomerData.setPreferredSize(new Dimension(612, 83));
		btnImportCustomerData.setMaximumSize(new Dimension(1024, 150));
		btnImportCustomerData.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_btnImportCustomerData = new GridBagConstraints();
		gbc_btnImportCustomerData.fill = GridBagConstraints.BOTH;
		gbc_btnImportCustomerData.insets = new Insets(0, 0, 5, 5);
		gbc_btnImportCustomerData.gridx = 1;
		gbc_btnImportCustomerData.gridy = 3;
		panel.add(btnImportCustomerData, gbc_btnImportCustomerData);
		
		/* Store Data Import Button */	
		JButton btnImportStoreData = new JButton("Import Store Data");
		btnImportStoreData.setPreferredSize(new Dimension(612, 83));
		btnImportStoreData.setMaximumSize(new Dimension(1024, 150));
		btnImportStoreData.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				/* Open file chooser and set new file path */
				JFileChooser chooser = new JFileChooser("Select Store Data File");
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("Store Data Files (.txt, .in)", "txt", "in");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(chooser.getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION){
			    	storeDataFilePath = chooser.getSelectedFile().getAbsolutePath();
			    }
			}
			
		});
		GridBagConstraints gbc_btnImportStoreData = new GridBagConstraints();
		gbc_btnImportStoreData.fill = GridBagConstraints.BOTH;
		gbc_btnImportStoreData.insets = new Insets(0, 0, 5, 5);
		gbc_btnImportStoreData.gridx = 1;
		gbc_btnImportStoreData.gridy = 2;
		panel.add(btnImportStoreData, gbc_btnImportStoreData);
		btnImportStoreData.setToolTipText("Load store data from a file");
		btnImportStoreData.setFont(new Font("Calibri", Font.BOLD, 16));
		
		/* Version label */
		JLabel lblVersion = new JLabel("Version 1.1 - 9/1/2017");
		GridBagConstraints gbc_lblVersion = new GridBagConstraints();
		gbc_lblVersion.insets = new Insets(0, 0, 0, 5);
		gbc_lblVersion.fill = GridBagConstraints.BOTH;
		gbc_lblVersion.gridx = 1;
		gbc_lblVersion.gridy = 5;
		panel.add(lblVersion, gbc_lblVersion);
		lblVersion.setHorizontalAlignment(SwingConstants.TRAILING);	
		
		/* Add Tabbed Pane */
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		layeredPane.setLayer(tabbedPane, 1);
		layeredPane.add(tabbedPane, "name_2189624879236729");
		
		/* Add Store Management tab */
		storeManager = new StoreManager();
		tabbedPane.addTab("Store Manager", null, storeManager, null);
		
		/* Add WishList tab */
		wishListPane = WishListPane.getInstance();
		GridBagLayout gridBagLayout = (GridBagLayout) wishListPane.getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
		tabbedPane.addTab("WishList", null, wishListPane, null);
		
		/* Add Shopping Cart tab */
		cartPane = CartPane.getInstance();
		tabbedPane.addTab("Shopping Cart", null, cartPane, null);
	}
	
	/* Load Store items to a ListModel */
	public ListModel createItemListModel(){
		ListModel listModel = new ListModel();
		for (Map.Entry<Integer, Department> d : store.getDepartments().entrySet())
			for (Map.Entry<Integer, Item> item : d.getValue().getItems().entrySet())
				listModel.addElement(item.getValue());
		listModel.sort(new ListModel.AlphabeticSorter());
		return listModel;
	}
	
	/* Load Store Clients to a ComboBoxModel */
	public DefaultComboBoxModel<Customer> createCustomerComboBoxModel(){
		DefaultComboBoxModel<Customer> comboBoxModel = new DefaultComboBoxModel<Customer>();
		for (Map.Entry<String, Customer> c : store.getCustomers().entrySet())
			comboBoxModel.addElement(c.getValue());
		return comboBoxModel;
	}
}
