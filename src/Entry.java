import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;


public class Entry extends JFrame {

	//Jpanels
	private JPanel contentPane;
	private JPanel panel_filepath;
	private JPanel panel_reg;
	private JPanel panel_result ;
	private JPanel panel_memory;
	private JPanel panel_showreg;
	private JPanel panel_showmemoy;
	//JButtons
	private JButton run;
	private JButton set_reg;
	private JButton set_memory;
	//filepath is present working directory plus Instruction.txt
	public static String filePath = System.getProperty("user.dir") + "/Instruction.txt";
	public static String showresult = "";
	private JLabel address;
	private JLabel content;
	private JLabel showresultlabel;
	private JLabel indexlabel;
	private JLabel regcontentlabel;
	private JLabel memorylabel;
	private JLabel memorycontentlabel;
	//JTextField
	private JTextField filepath;
	private JTextField addressTextField;
	private JTextField contentTextField;
	private JTextField indexTextField;
	
	private JTextField showregcontentTextField;
	private JButton show_reg;
	
	
	private JTextField showmemoryaddressTextField;
	private JButton show_memory;
	
	private JTextField showmemorycontentTextField;
	private JPanel panel_showpcx0;
	private JButton show_X0Reg;
	private JTextField showX0RegTextField;
	private JButton showPC;
	private JTextField showPCTextField;
	private JLabel registerindexlabel;
	private JTextField setRegIndexTextField;
	private JLabel setregcontentlabel;
	private JTextField setRegContentTextField;
	private JPanel panel;
	private JLabel keyboardLabel;
	private JButton keyboardButton1;
	private JButton keyboardButton2;
	private JButton keyboardButton3;
	private JButton keyboardButton4;
	private JPanel panel_1;
	private JLabel printerLabel;
	private JTextField printerTextfield;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Entry frame = new Entry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Entry() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_filepath = new JPanel();
		contentPane.add(panel_filepath);
		panel_filepath.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("filepath");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_filepath.add(lblNewLabel);
		
		filepath = new JTextField(filePath);
		panel_filepath.add(filepath);
		filepath.setColumns(33);
		
		run = new JButton("run");
		panel_filepath.add(run);
		//run listen to the run of the  program
		run.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ReadFile.readfile(filepath.getText().toString());
				Simulator.run();
				
				//console printer
				if(Printer.active == 1){
					printerTextfield.setText(Integer.toString(Printer.content));
				}
						
			}
					
		});
		
		panel_reg = new JPanel();
		contentPane.add(panel_reg);
		panel_reg.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		registerindexlabel = new JLabel("register index");
		panel_reg.add(registerindexlabel);
		
		setRegIndexTextField = new JTextField();
		panel_reg.add(setRegIndexTextField);
		setRegIndexTextField.setColumns(10);
		
		setregcontentlabel = new JLabel("content");
		panel_reg.add(setregcontentlabel);
		
		setRegContentTextField = new JTextField();
		panel_reg.add(setRegContentTextField);
		setRegContentTextField.setColumns(10);
		
		set_reg = new JButton("set reg");
		panel_reg.add(set_reg);
		//set_reg listen to the change of the reg
		set_reg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = Integer.parseInt(setRegIndexTextField.getText());
				int content = Integer.parseInt(setRegContentTextField.getText());
				GPRegister.setReg(content, index);
				
				}	
				});
		
		panel_memory = new JPanel();
		contentPane.add(panel_memory);
		panel_memory.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		address = new JLabel("address");
		panel_memory.add(address);
		
		addressTextField = new JTextField();
		panel_memory.add(addressTextField);
		addressTextField.setColumns(10);
		
		content = new JLabel("content");
		panel_memory.add(content);
		
		contentTextField = new JTextField();
		panel_memory.add(contentTextField);
		contentTextField.setColumns(10);
		
		set_memory = new JButton("set memory");
		panel_memory.add(set_memory);
		//set_memory listen to the change of the memory
		set_memory.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			if((addressTextField.getText()!=null) &&(contentTextField.getText()!=null)){
				Memory.setData2Memory(Integer.parseInt(contentTextField.getText().toString()), Integer.parseInt(addressTextField.getText().toString()));
			}
						
			}
					
		});
		
		panel_result = new JPanel();
		contentPane.add(panel_result);
		
		showresultlabel = new JLabel("show result");
		showresultlabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		panel_result.add(showresultlabel);
		
		panel_showreg = new JPanel();
		contentPane.add(panel_showreg);
		
		indexlabel = new JLabel("register index");
		panel_showreg.add(indexlabel);
		
		indexTextField = new JTextField();
		panel_showreg.add(indexTextField);
		indexTextField.setColumns(10);
		
		show_reg = new JButton("show register");
		panel_showreg.add(show_reg);
		//listen
		show_reg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int regindex = Integer.parseInt(indexTextField.getText().toString());
				showregcontentTextField.setText(Integer.toString(GPRegister.getReg(regindex)));
			}
			
		});
		
		regcontentlabel = new JLabel("content");
		panel_showreg.add(regcontentlabel);
		
		showregcontentTextField = new JTextField();
		panel_showreg.add(showregcontentTextField);
		showregcontentTextField.setColumns(10);
		
		panel_showmemoy = new JPanel();
		contentPane.add(panel_showmemoy);
		
		memorylabel = new JLabel("memory address");
		panel_showmemoy.add(memorylabel);
		
		showmemoryaddressTextField = new JTextField();
		panel_showmemoy.add(showmemoryaddressTextField);
		showmemoryaddressTextField.setColumns(10);
		
		show_memory = new JButton("show memory");
		panel_showmemoy.add(show_memory);
		//listen
		show_memory.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int memoryaddress = Integer.parseInt(showmemoryaddressTextField.getText().toString());
				if(memoryaddress<50){
					showmemorycontentTextField.setText(Integer.toBinaryString(Memory.getDataFromMemory(memoryaddress)));
				}else{
					showmemorycontentTextField.setText(Integer.toString(Memory.getDataFromMemory(memoryaddress)));
				}
			}
			
		});
		
		memorycontentlabel = new JLabel("content");
		panel_showmemoy.add(memorycontentlabel);
		
		showmemorycontentTextField = new JTextField();
		panel_showmemoy.add(showmemorycontentTextField);
		showmemorycontentTextField.setColumns(10);
		
		panel_showpcx0 = new JPanel();
		contentPane.add(panel_showpcx0);
		
		show_X0Reg = new JButton("show X0");
		show_X0Reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showX0RegTextField.setText(Integer.toString(X0Reg.getX0()));
			}
		});
		panel_showpcx0.add(show_X0Reg);
		
		showX0RegTextField = new JTextField();
		panel_showpcx0.add(showX0RegTextField);
		showX0RegTextField.setColumns(10);
		
		showPC = new JButton("show pc");
		showPC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPCTextField.setText(Integer.toString(ProgramCounter.getPC()));
			}
		});
		panel_showpcx0.add(showPC);
		
		showPCTextField = new JTextField();
		panel_showpcx0.add(showPCTextField);
		showPCTextField.setColumns(10);
		
		panel = new JPanel();
		contentPane.add(panel);
		
		keyboardLabel = new JLabel("keyboard");
		panel.add(keyboardLabel);
		
		keyboardButton1 = new JButton("key1");
		keyboardButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					//only if the keyboard is active
					Keyboard.setContent(1);;//value of key 1 is 1
					Keyboard.runKeyboard();
					Keyboard.inactivateKeyboard();;//inactivate the keyboard
				}
			}
		});
		panel.add(keyboardButton1);
		
		keyboardButton2 = new JButton("key2");
		keyboardButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.setContent(2);;//value of key 2 is 2
					Keyboard.inactivateKeyboard();
				}
			}
		});
		panel.add(keyboardButton2);
		
		keyboardButton3 = new JButton("key3");
		keyboardButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.setContent(3);;//value of key 3 is 3
					Keyboard.inactivateKeyboard();
				}
			}
		});
		panel.add(keyboardButton3);
		
		keyboardButton4 = new JButton("key4");
		keyboardButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Keyboard.active == 1){
					Keyboard.setContent(4);;//value of key 4 is 4
					Keyboard.inactivateKeyboard();
				}
			}
		});
		panel.add(keyboardButton4);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		printerLabel = new JLabel("console printer");
		panel_1.add(printerLabel);
		
		printerTextfield = new JTextField();
		panel_1.add(printerTextfield);
		printerTextfield.setColumns(30);
		
		
		
		
		
	}
}
