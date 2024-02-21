import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class LoginRubrica extends JFrame {
	
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginRubrica() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);

        setContentPane(panel);

        loginButton.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

	private void login() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
               

        if (Database.checkCredentials(username, new String(password))) {
            JOptionPane.showMessageDialog(this, "Login effettuato!");  
            dispose();
            new RubricaTelefonica(username).setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(this, "Username/Password non corretti", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            new LoginRubrica().setVisible(true);
	        }
	    });
	}
	

}
