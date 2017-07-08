package frontend.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import backend.repositorio.RepositorioVector;
import backend.twitter.MyTwitter;
import frontend.gui.panels.SignInPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private String welcome = "Seja bem vindo ao MyTwitter \nsua mais nova rede social de micro-blogging.";

	public MainFrame() {

		setBounds(50, 0, 1080, 720);
		setTitle("MyTwitter by @Romulofff");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));

		ImageIcon icon = new ImageIcon("/home/romulofff/workspace/MyTwitter/src/frontend/images/birdresized.png");
		JLabel iconField = new JLabel();
		iconField.setIcon(icon);
		iconField.setText("MyTwitter");

		add(iconField, BorderLayout.NORTH);

		JTextArea welcomeTxt = new JTextArea();
		welcomeTxt.setEditable(false);
//		welcomeTxt.setBackground(Color.ORANGE);
		welcomeTxt.setText(welcome);

		add(welcomeTxt, BorderLayout.WEST);

		SignInPanel signIn = new SignInPanel();
		add(signIn, BorderLayout.CENTER);
	}

	public static void main(String[] args) throws Exception {
		RepositorioVector repo = new RepositorioVector();
		MyTwitter Twitter = new MyTwitter(repo);
		new MainFrame().setVisible(true);
	}
}
