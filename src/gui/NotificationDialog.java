package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import store.Customer;
import store.Notification;

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NotificationDialog extends JDialog{

	/**
	 * Create the dialog.
	 */
	public NotificationDialog(Customer client){
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter(){
					
					public void mouseClicked(MouseEvent e){
						dispose();
					}
					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				DefaultListModel<Notification> model = new DefaultListModel<Notification>();
				for (Notification n : client.getNotifications())
					model.addElement(n);
				JList<Notification> list = new JList<Notification>(model);
				scrollPane.setViewportView(list);
			}
		}
	}

}
