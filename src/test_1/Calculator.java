package test_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {
	
	private JTextField inputSpace;
	private String num = "";
	private ArrayList<String> equation = new ArrayList<String>();
	
	
	public Calculator() {
		
		setLayout(null);
		
		inputSpace = new JTextField();
		inputSpace.setEditable(false);
		inputSpace.setBackground(Color.WHITE);
		inputSpace.setHorizontalAlignment(JTextField.RIGHT);
		inputSpace.setFont(new Font("Arial" , Font.BOLD, 60));
		inputSpace.setBounds(8, 10, 370, 70);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4,4,10,10));
		buttonPanel.setBounds(8,90,370,335);
		
		String button_names[] = {"C","÷", "×", "=", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "0" };
		JButton buttons[] = new JButton[button_names.length];
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(button_names[i]);
			buttons[i].setFont(new Font("Arial", Font.BOLD,20));
			
			if (button_names[i] == "C") buttons[i].setBackground(Color.RED);
			else if ((i >= 4 && i <= 6) || (i >= 8 && i <= 10) || (i >= 12 && i <= 14)) buttons[i].setBackground(Color.BLACK);
			else buttons[i].setBackground(Color.GRAY);
			
			buttons[i].setForeground(Color.white);
			buttons[i].setBorderPainted(false);
			buttons[i].addActionListener(new PadActionListener());
			buttonPanel.add(buttons[i]);
		}
		
		add(inputSpace);
		add(buttonPanel);
		
		setTitle("계산기");
		setVisible(true);
		setSize(400, 470);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	class PadActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String operation = e.getActionCommand();
				
				if (operation.equals("C")) {
					inputSpace.setText("");
				} else if (operation.equals("=")) {
					String result = Double.toString(calculate1(inputSpace.getText()));
					inputSpace.setText("" + result);
					num = "";
				} else {
					inputSpace.setText(inputSpace.getText() + e.getActionCommand());
					
				}
			}
		}
	private void fullTextParsing(String inputText) {
		equation.clear();
		
		for (int i = 0; i < inputText.length(); i++) {
				char ch = inputText.charAt(i);
				
				if (ch == '-' || ch == '+' || ch == 'x' || ch == '÷') {
					equation.add(num);
					num = "";
					equation.add(ch+"");
					
					
				} else { num = num + ch;

				}
		}
		equation.add(num);
		}
	
	public double calculate1(String inputText) {
		fullTextParsing(inputText);
		
		double prev = 0;
		double current = 0;
		String mode ="";
		
		for (String s : equation) {
			if (s.equals("+")) {
				mode = "add";
			} else if (s.equals("-")){
				mode = "sub";
			}else if (s.equals("x")) {
				mode = "mul";
			}else if (s.equals("÷")) {
				mode = "div";
			} else {
				current = Double.parseDouble(s);
				
				if (mode.equals("add")) {
					prev += current;
				} else if (mode.equals("sub")) {
					prev -= current;
				} 
				else if (mode.equals("mul")) {
					prev *= current;
				} 
				else if (mode.equals("div")) {
					prev /= current;
				} else {
					prev = current;
				}
			}
		}
		return prev;
		
	}
	public static void main(String[] args) {
		new Calculator();

	}
	

	}
