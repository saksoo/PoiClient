package PoiServicesClient;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JLabel labelUsername = new JLabel("Username:");
	private final JLabel labelPassword = new JLabel("Password:");
	private final JLabel labelPassword2 = new JLabel("Password verification:");
	private final JLabel labelNote = new JLabel("Note: Username is case insensitive.");
	private final JLabel labelNote2 = new JLabel("Note: Confirmation not needed in order to login");
	private final JTextField textFieldUsername = new JTextField(20);
	private final JPasswordField textFieldPassword1 = new JPasswordField(20);
	private final JPasswordField textFieldPassword2 = new JPasswordField(20);
	private final JButton buttonRegister = new JButton("--- Register ---");
	private final JButton buttonLogin = new JButton("--- Login ---");
	
	private final int WIDTH = 300;
	private final int HEIGHT = 300;
	private int screen_size = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	
	private final Application application;
	
	public RegistrationDialog( Application a ) {
		super((Window)null);
		setTitle("Please type your credentials:");
		
		// TODO Auto-generated constructor stub
				
		this.application = a;
		 
		setSize(WIDTH, HEIGHT);
		setLocation(screen_size/2-WIDTH/2,HEIGHT/3);
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(labelUsername);
		getContentPane().add(textFieldUsername);
		getContentPane().add(labelPassword);
		getContentPane().add(textFieldPassword1);
		getContentPane().add(labelPassword2);
		getContentPane().add(textFieldPassword2);
		getContentPane().add(buttonRegister);
		getContentPane().add(buttonLogin);
		getContentPane().add(labelNote);
		getContentPane().add(labelNote2);
		
		buttonRegister.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String username = textFieldUsername.getText().toLowerCase();
					textFieldUsername.setText(username); //name for frame perito dn to xreiazomaste pleon
					String password = new String(textFieldPassword1.getPassword());
					String password2 = new String(textFieldPassword2.getPassword());
					
					if (username.trim().length() == 0 ||
							password.trim().length()==0 ||
							password2.trim().length()==0) {
						JOptionPane.showMessageDialog(null, "Please fill all the fields!");
						return;
					}												
					if (!password.equals(password2)) {
						JOptionPane.showMessageDialog(null, "Passwords do not match!");
						return;
					} 
						
					if (!application.registerUser(username, password, password2)) {
						JOptionPane.showMessageDialog(null, "Registration failed.");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.toString());
				}
			}
		});
		
		buttonLogin.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					// TODO Auto-generated method stub
					String username = textFieldUsername.getText().toLowerCase();
					textFieldUsername.setText(username);
					String password = new String(textFieldPassword1.getPassword());
					
					if (username.trim().length() == 0 ||
							password.trim().length()==0) {
						JOptionPane.showMessageDialog(null, "Please fill all the fields!");
						return;
					}												
						
					if (!application.loginUser(username, password)) {
						JOptionPane.showMessageDialog(null, "Login failed.");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.toString());
				}
			}
		});
	}
}
