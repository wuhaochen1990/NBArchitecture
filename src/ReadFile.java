import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


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
				/*//print the instruction
				System.out.println(instruction+"\n");
				//split the instruction
				String[] splited = instruction.split(" ");
				//deal with the splited parts
				System.out.println(splited[0]);
				System.out.println(splited[1]);
				//split the second string with ','
				String[] splited2 = splited[1].split(",");
				//print the new splited2
				System.out.println(splited2[0]);
				System.out.println(splited2[1]);
				System.out.println(splited2[2]);*/
				
				//run the instruction of that line
				Simulator.run();
			}
		}catch(Exception ex){
			//for exception situation
			System.out.println(ex.getMessage());  
		}
	}
}
