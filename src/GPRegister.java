
public class GPRegister {
	//4 general purpose registers
	public static int Reg[] = new int[4];
	
	//set the register
	public static void setReg(int data, int index){
		Reg[index] = data;
	}
	//get the register
	public static int getReg(int index){
		return Reg[index];
	}
	//show the register
	public static void showRegByIndex(int index){
		System.out.println(Reg[index]);
	}
}
