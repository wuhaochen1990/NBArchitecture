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
	//JLabels
	private JLabel R0Label;
	private JLabel R1Label;
	private JLabel R2Label;
	private JLabel R3Laebl;
	private JLabel address;
	private JLabel content;
	private JLabel showresultlabel;
	private JLabel indexlabel;
	private JLabel regcontentlabel;
	private JLabel memorylabel;
	private JLabel memorycontentlabel;
	//JTextField
	private JTextField filepath;
	private JTextField R0TextField;
	private JTextField R1TextField;
	private JTextField R2TextField;
	private JTextField R3TextField;
	private JTextField addressTextField;
	private JTextField contentTextField;
	private JTextField indexTextField;
	
	private JTextField showregcontentTextField;
	private JButton show_reg;
	
	
	private JTextField showmemoryaddressTextField;
	private JButton show_memory;
	
	private JTextField showmemorycontentTextField;
	
	

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
		setBounds(100, 100, 600, 400);
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
						
			}
					
		});
		
		panel_reg = new JPanel();
		contentPane.add(panel_reg);
		panel_reg.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		R0Label = new JLabel("R0");
		panel_reg.add(R0Label);
		
		R0TextField = new JTextField();
		panel_reg.add(R0TextField);
		R0TextField.setColumns(5);
		
		R1Label = new JLabel("R1");
		panel_reg.add(R1Label);
		
		R1TextField = new JTextField();
		panel_reg.add(R1TextField);
		R1TextField.setColumns(5);
		
		R2Label = new JLabel("R2");
		panel_reg.add(R2Label);
		
		R2TextField = new JTextField();
		panel_reg.add(R2TextField);
		R2TextField.setColumns(5);
		
		R3Laebl = new JLabel("R3");
		panel_reg.add(R3Laebl);
		
		R3TextField = new JTextField();
		panel_reg.add(R3TextField);
		R3TextField.setColumns(5);
		
		set_reg = new JButton("set reg");
		panel_reg.add(set_reg);
		//set_reg listen to the change of the reg
				set_reg.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						if(R0TextField.getText()!=null){
							int temp = Integer.parseInt(R0TextField.getText().toString());
							GPRegister.setReg(temp, 0);
							
						}
						if(R1TextField.getText()!=null){
							int temp = Integer.parseInt(R1TextField.getText().toString());
							GPRegister.setReg(temp, 1);
							
						}
						if(R2TextField.getText()!=null){
							int temp = Integer.parseInt(R2TextField.getText().toString());
							GPRegister.setReg(temp, 2);
							
						}
						if(R3TextField.getText()!=null){
							int temp = Integer.parseInt(R3TextField.getText().toString());
							GPRegister.setReg(temp, 3);
							
						}
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
		
		
		
		
		
	}
}
