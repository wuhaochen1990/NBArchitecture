
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
//	//show the whole four register
//	public static void showReg(){
//		System.out.println("Register0:"+Integer.toString(Reg[0]));
//		System.out.println("Register1:"+Integer.toString(Reg[1]));
//		System.out.println("Register2:"+Integer.toString(Reg[2]));
//		System.out.println("Register3:"+Integer.toString(Reg[3]));
//	}
//	//show the register by index
//	public static void showRegByIndex(int index){
//		System.out.println(Reg[index]);
//	}
}
