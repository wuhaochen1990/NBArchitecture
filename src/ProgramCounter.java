
public class ProgramCounter {
	public static int pc = 2;
	//get the program counter
	public static int getPC(){
		return pc;
	}
	//set the program counter
	public static void setPC(int newpc){
		pc = newpc;
	}
	//increment of pc
	public static void incrementPC(){
		pc += 1;
	}
}
