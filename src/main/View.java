package main;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

import dataBase.Connector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View {

	private JFrame frame;
	private JTextField value;
	private JTextField applicant;
	private JTextField reason;
	private JRadioButton isCredit;
	private JRadioButton isDebit;
	private JLabel balance;
	private JPanel panel;
	private double _balance;
	private Connector connector;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public View() 
	{
		initialize();
		connector = new Connector();
		_balance = connector.getAmount();
		balance.setText("Total: "+_balance);
	}
	
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 368, 238);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		tabbedPane.addTab("Buchung", null, panel, null);
		panel.setLayout(null);
		
		balance = new JLabel("New label");
		balance.setBounds(10, 142, 212, 14);
		panel.add(balance);
		
		value = new JTextField();
		value.setHorizontalAlignment(SwingConstants.CENTER);
		value.setColumns(10);
		value.setBounds(166, 41, 126, 20);
		panel.add(value);
		
		applicant = new JTextField();
		applicant.setHorizontalAlignment(SwingConstants.CENTER);
		applicant.setColumns(10);
		applicant.setBounds(166, 69, 126, 20);
		panel.add(applicant);
		
		JButton button = new JButton("calculate");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				calculate();
			}
		});
		button.setBounds(232, 138, 104, 23);
		panel.add(button);
		
		JLabel label_1 = new JLabel("Betrag");
		label_1.setBounds(10, 47, 146, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Verantwortlicher");
		label_2.setBounds(10, 75, 146, 14);
		panel.add(label_2);
		
		JLabel lblGrund = new JLabel("Grund");
		lblGrund.setBounds(10, 105, 146, 14);
		panel.add(lblGrund);
		
		reason = new JTextField();
		reason.setHorizontalAlignment(SwingConstants.CENTER);
		reason.setBounds(166, 99, 126, 20);
		panel.add(reason);
		reason.setColumns(10);
		
		
		isDebit = new JRadioButton("Minus");
		isDebit.setBounds(10, 7, 75, 23);
		panel.add(isDebit);
		
		isCredit = new JRadioButton("Plus");
		isCredit.setSelected(true);
		isCredit.setBounds(87, 7, 75, 23);
		panel.add(isCredit);
		
		ButtonGroup group = new ButtonGroup();
		group.add(isCredit);
		group.add(isDebit);
		
	}

	private void calculate()
	{	
		double myValue = 0.0;
		try
		{
			if("".equals(applicant.getText())||"".equals(reason.getText()))
			{
				throw new Exception();
			}
			myValue = Double.parseDouble(value.getText());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Bitte alle Felder korrekt ausfüllen");
			
		}
		
		_balance = connector.booking(isCredit.isSelected(), myValue, applicant.getText(), reason.getText());
		balance.setText("Total: "+ _balance);
		panel.repaint();
	}
}
