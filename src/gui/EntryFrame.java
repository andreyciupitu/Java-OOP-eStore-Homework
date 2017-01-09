package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import net.miginfocom.swing.MigLayout;
import store.Store;

/////////////////////TODO - add keyboard shortcuts ////////////////////////
public class EntryFrame extends JFrame{

	private JPanel contentPane;
	private Store store = Store.getInstance();

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
		setTitle("Ciupi's Virtual Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		layeredPane.add(panel, "name_2189620832239330");
		panel.setBackground(new Color(245, 255, 250));
		panel.setLayout(new MigLayout("", "[612px,grow 50,center]", "[84px,grow][82.25px,grow,center][82.25px,grow,center][82.25,grow][82.25px,grow,center][33.00px,grow]"));
		
		JLabel lblAndreiVirtualStore = new JLabel("Andrei' Virtual Store");
		lblAndreiVirtualStore.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblAndreiVirtualStore.setHorizontalAlignment(SwingConstants.CENTER);
		lblAndreiVirtualStore.setLabelFor(contentPane);
		lblAndreiVirtualStore.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		panel.add(lblAndreiVirtualStore, "cell 0 0,alignx center,growy");
		
		JButton btnOpenStore = new JButton("Open Store");
		btnOpenStore.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent arg0){
				Store.getInstance().loadDepartments("input\\store.txt");
				Store.getInstance().loadCustomers("input\\customers.txt");
				CardLayout layout = (CardLayout)layeredPane.getLayout();
				layout.next(layeredPane);
			}
			
		});
		btnOpenStore.setPreferredSize(new Dimension(612, 83));
		btnOpenStore.setMaximumSize(new Dimension(1024, 150));
		panel.add(btnOpenStore, "cell 0 1,grow");
		btnOpenStore.setToolTipText("Open the store with the current data");
		btnOpenStore.setFont(new Font("Calibri", Font.BOLD, 16));
		
		JButton btnImportStoreData = new JButton("Import Store Data");
		btnImportStoreData.setPreferredSize(new Dimension(612, 83));
		btnImportStoreData.setMaximumSize(new Dimension(1024, 150));
		btnImportStoreData.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				JFileChooser chooser = new JFileChooser("Select Store Data File");
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("Store Data Files (.txt, .in)", "txt", "in");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(chooser.getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION){
			    	store.loadDepartments(chooser.getSelectedFile().getAbsolutePath());
			    }
			}
			
		});
		panel.add(btnImportStoreData, "cell 0 2,grow");
		btnImportStoreData.setToolTipText("Load store data from a file");
		btnImportStoreData.setFont(new Font("Calibri", Font.BOLD, 16));
		
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
		
		JButton btnImportCustomerData = new JButton("Import Customer Data");
		btnImportCustomerData.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e){
				JFileChooser chooser = new JFileChooser("Select Customer Data File");
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("Customer Data Files (.txt, .in)", "txt", "in");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(chooser.getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION){
			    	store.loadCustomers(chooser.getSelectedFile().getAbsolutePath());
			    }
			}
			
		});
		btnImportCustomerData.setPreferredSize(new Dimension(612, 83));
		btnImportCustomerData.setMaximumSize(new Dimension(1024, 150));
		btnImportCustomerData.setFont(new Font("Calibri", Font.BOLD, 16));
		panel.add(btnImportCustomerData, "cell 0 3,grow");
		btnNewButton.setPreferredSize(new Dimension(612, 83));
		btnNewButton.setMaximumSize(new Dimension(1024, 150));
		panel.add(btnNewButton, "cell 0 4,grow");
		btnNewButton.setToolTipText("Exit the application");
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 16));
		
		JLabel lblVersion = new JLabel("Version 1.1 - 9/1/2017");
		panel.add(lblVersion, "cell 0 5,grow");
		lblVersion.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		layeredPane.setLayer(tabbedPane, 1);
		layeredPane.add(tabbedPane, "name_2189624879236729");
		
		StoreManager storeManager = new StoreManager();
		tabbedPane.addTab("Store Manager", null, storeManager, null);
	}

}
