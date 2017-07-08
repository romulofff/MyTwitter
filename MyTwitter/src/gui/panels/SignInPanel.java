package frontend.gui.panels;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignInPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private String convite = "Cadastre-se já!";

	public SignInPanel() {
		setLayout(null);

		JTextField conviteCadastro = new JTextField();
		conviteCadastro.setEditable(false);
		conviteCadastro.setText(convite);
		conviteCadastro.setBounds(0, 0, 350, 25);
		conviteCadastro.setBackground(Color.ORANGE);
		

		JTextField user = new JTextField("Usuario");
		user.setBounds(0, 35, 350, 50);

		JTextField cpf = new JTextField("Cpf");
		cpf.setBounds(0, 95, 350, 50);

		JButton sign = new JButton();
		sign.setText("Sign in");
		sign.setBounds(250, 160, 100, 25);

		add(conviteCadastro);
		add(user);
		add(cpf);
		add(sign);

		this.setVisible(true);
	}

}
