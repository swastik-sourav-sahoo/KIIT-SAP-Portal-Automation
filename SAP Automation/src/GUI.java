import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI implements ActionListener {
	
	private JTextField roll;
	private JPasswordField password;
	private JLabel message;
	
	public void run(GUI object) {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(700, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		
		panel.setLayout(null);
		
		JLabel rollLabel = new JLabel("Roll Number");
		rollLabel.setBounds(20, 20, 80, 25);
		panel.add(rollLabel);
		
		roll = new JTextField(20);
		roll.setBounds(100, 20, 165, 25);
		panel.add(roll);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(20, 50, 80, 25);
		panel.add(passwordLabel);
		
		password = new JPasswordField();
		password.setBounds(100, 50, 165, 25);
		panel.add(password);
		
		JButton submit = new JButton("Submit");
		submit.setBounds(20, 80, 245, 25);
		submit.addActionListener(object);
		panel.add(submit);
		
		message = new JLabel("");
		message.setBounds(20, 110, 650, 100);
		panel.add(message);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String status = null;
		String rollNumberEntered = roll.getText();
		@SuppressWarnings("deprecation")
		String passwordEntered = password.getText();
		if(!rollNumberEntered.isEmpty() && !passwordEntered.isEmpty()) {
			try {
				Action nav = new Action();
				status = nav.run(rollNumberEntered,passwordEntered);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else
			status = "Invalid Credentials";
		message.setText(status);
	}
	
}
