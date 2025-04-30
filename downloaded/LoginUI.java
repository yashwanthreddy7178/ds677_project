package main.userinterface;

import java.awt.BorderLayout;
import main.interfaces.LoginUIInterface;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import main.queryengine.SystemUserQuery;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.GridLayout;


public class LoginUI extends JPanel implements LoginUIInterface {

	public JFrame parentFrame;

	public JTextField userNameField = new JTextField(30);
	public JPasswordField passwordField = new JPasswordField(30);
	public JOptionPane jOptionPane = new JOptionPane();
	public JButton signinButton = new JButton("SignIn");

	// constuctor for LoginUI to initialize the fields and buttons on the frame
	public LoginUI() {
		parentFrame = Main.getMainFrame();
		parentFrame.setVisible(true);

		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);
	}

	// method to initialize the signin button and implement the code upon click action 
	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		signinButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		signinButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// validate the credentials of the user being logged in 
				if(ValidCredentials()){
					// if credentials are valid, initialize differnt panes
					// search , committee, update profile and logout tabs
					parentFrame.getContentPane().removeAll();
					parentFrame.getContentPane().revalidate();
					parentFrame.getContentPane().repaint();
					parentFrame.getContentPane().setLayout(new BorderLayout());
					
					JTabbedPane tabbedPane = new JTabbedPane();
					tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
					tabbedPane.addTab("Search", new SearchUI(null));
					tabbedPane.addTab("Committee List", new CommitteeListUI());
					tabbedPane.addTab("Update Profile", new UpdateProfileUI());
					
					tabbedPane.add("Logout", new LogoutUI());
					parentFrame.getContentPane().add(tabbedPane);
				}
				else{
					// if the credentials are invalid, pop up the following message window
					jOptionPane.showMessageDialog(LoginUI.this,
							"Invalid username or password",
							"Login",
							jOptionPane.ERROR_MESSAGE);
					// reset username and password
					userNameField.setText("");
					passwordField.setText("");
				}
			}
		});
		panel.add(signinButton);
		return panel;
	}

	// initializes all the labels and the text fields of the login UI screen
	private JPanel initFields() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		JLabel usernameLabel = new JLabel("UserName");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panel.add(usernameLabel, "align label");
		userNameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(userNameField, "wrap");

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(passwordLabel, "align label");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(passwordField, "wrap");
		return panel;
	}

	// validate the user credentials by checking against the systemuser database table
	public boolean ValidCredentials() { 
		if(!isEmptyFieldData()){
			SystemUserQuery sq = new SystemUserQuery();
			User user = sq.loginQuery(getUsername(), getPassword());
			
			if(user != null){
				Main.setCurrentUser(user);
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	private boolean isEmptyFieldData() {
		return (getUsername().isEmpty() || getPassword().isEmpty());
	}

	public String getUsername() {
		return userNameField.getText().trim();
	}

	public String getPassword() {
		return new String(passwordField.getPassword());
	}
}
