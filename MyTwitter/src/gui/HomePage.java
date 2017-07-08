package frontend.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HomePage extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField topArea;
	private JTextField cadastroTxt;
	private JTextField userTxt;
	private JTextField cpfTxt;
	private JButton signInBtn;
	private JCheckBox userTerms;
	private JTextArea termosTxt;
	private JTextArea disclaimerArea;

	public HomePage() {
		
		// construct components
		topArea = new JTextField(5);
		topArea.setEditable(false);
		topArea.setText("MyTwitter");
		topArea.setHorizontalAlignment(JTextField.CENTER);
		topArea.setBackground(Color.ORANGE);
		
		cadastroTxt = new JTextField(5);
		cadastroTxt.setEditable(false);
		cadastroTxt.setText("Inscreva-se agora!");
		cadastroTxt.setHorizontalAlignment(JTextField.CENTER);
		cadastroTxt.setBackground(Color.ORANGE);
		
		userTxt = new JTextField("Usuario", 5);
	
		cpfTxt = new JTextField("CPF", 5);
	
		signInBtn = new JButton("Inscreva-se");
	
		userTerms = new JCheckBox("");
		userTerms.setBackground(Color.WHITE);
		
		termosTxt = new JTextArea("Ao inscrever-se, você concorda com os Termos de Serviço e a Política \nde Privacidade,"
				+ " incluindo o Uso de Cookies.", 5, 5);
		termosTxt.setAlignmentY(BOTTOM_ALIGNMENT);
		termosTxt.setEditable(false);
		disclaimerArea = new JTextArea(5, 5);

		// adjust size and set layout
		setPreferredSize(new Dimension(950, 604));
//		setPreferredSize(new Dimension(1080, 720));
		setLayout(null);
		setBackground(Color.white);
		
		// add components
		add(topArea);
		add(cadastroTxt);
		add(userTxt);
		add(cpfTxt);
		add(signInBtn);
		add(userTerms);
		add(termosTxt);
		add(disclaimerArea);

		// set component bounds (only needed by Absolute Positioning)
		topArea.setBounds(0, 0, 950, 70);
		cadastroTxt.setBounds(235, 90, 475, 55);
		cpfTxt.setBounds(235, 165, 475, 35);
		userTxt.setBounds(235, 215, 475, 35);
		signInBtn.setBounds(235, 320, 475, 50);
		userTerms.setBounds(235, 270, 25, 30);
		termosTxt.setBounds(265, 270, 445, 30);
		disclaimerArea.setBounds(235, 395, 475, 75);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("HomePage");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new HomePage());
		frame.pack();
		frame.setVisible(true);
	}
}
