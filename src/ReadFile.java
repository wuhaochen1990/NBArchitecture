import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//read instruction from a file
public class ReadFile {
	public static void main(String arg[]){
		// Input file path
		String filePath = "/Users/haochenwu/Documents/Instruction.txt";
		//string from the certain file
		String instruction = "";
		//try read file from that path
		try{
			//create a input stream to read a file
			File file = new File(filePath);
			FileReader fileread = new FileReader(file);  
			BufferedReader br = new BufferedReader(fileread);
			//start read the file
			while((instruction=br.readLine())!=null){
				//print the instruction
				System.out.println(instruction+"\n");
				//turn the instruction string to binary and write to the memory
				Memory.setInstr2Memory(Instr2Binary(instruction));
			}
		}catch(Exception ex){
			//for exception situation
			System.out.println(ex.getMessage());  
		}
		//run the instruction of that line
		//Simulator.run();
		Memory.showMemory();
	}
	//turn instruction string to its opcode binary
	public static int Instr2Opcode(String instruction){
		if(instruction.equals("LDR")){
			return 1 << 10;
		}else if(instruction.equals("STR")){
			return 2 << 10;
		}
		return 0;
	}
	//turn the rest of the instruction string into operands binary
	public static int Instr2Operands(String instruction){
		String[] splited = instruction.split(",");
		//for three operands instruction
		if(splited.length == 3){
			//extract three operands and change them to binary
			int operands1 = Integer.parseInt(splited[0])<<8;
			int operands2 = Integer.parseInt(splited[1])<<6;
			int operands3 = Integer.parseInt(splited[2]);
			//merge them into a binary
			int operands = operands1 + operands2  + operands3;
			return operands;
		}else{
			return 0;
		}
	}
	//turn the whole instruction string into binary
	public static int Instr2Binary(String instruction){
		String[] splited = instruction.split(" ");
		//deal with the splited parts with Instr2Opcode and Instr2Operands and return
		return Instr2Opcode(splited[0]) + Instr2Operands(splited[1]);
		
	}
}