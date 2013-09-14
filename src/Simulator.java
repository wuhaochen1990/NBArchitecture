
public class Simulator {
	//max address of the memory of this system
	public static final int MAX_ADDRESS=9999;
	//
	public static final int[] memory = new int[MAX_ADDRESS+1];
	//instruction code
	public static final int LDR = 1;
	public static final int STR = 2;

	//the value of some key words
	public static int pc;
	public static int x0;
	public static int opcode;
	public static int operands;//numbers after the opcode
	public static int x;
	public static int Address;
	public static int ac;
	public static int ea;
	//general purpose register
	public static int Reg[] = new int[4];
	//mdr and mar and ir
	public static int mdr;
	public static int mar;
	public static int ir;

	//get the ea! effective address
	/*public int GetEA(int x){
		switch(x){
		case 0:{
			return Address;
		}
		case 1:{
			return x0+Address;
		}
		case 2:{
			return memory[Address];
		}
		case 3:{
			return memory[x0+Address];
		}
		}
		
	}*/
	//run the instruction
	public static void run(){
		//fetch the instruction from the memory
		memory[0] = 0b100011101100;
		Reg[3] = 4;
		pc = 0;
		mar = pc;
		pc += 1;
		mdr = memory[mar];
		ir = mdr;
		
		//decode the instruction
		operands = ir & 0b1111111111;
		int temp = ir>>>10;
		opcode = temp & 0b111111;
		System.out.println(Integer.toString(operands));
		System.out.println(Integer.toString(opcode));

		//choose the instruction function
		switch(opcode){
		case LDR:{
			System.out.println("LDR");
			Address = operands & 0b111111;
			operands = operands>>>6;
			ac = operands & 0b11;
			operands = operands>>>2;
			x = operands & 0b11;
			switch(x){
			case 0:{
				ea = Address;
				mar = ea;
				mdr = memory[mar];
				Reg[ac] = mdr;
				break;
			}
			case 1:{
				ea = x0 + Address;
				mar = ea;
				mdr = memory[mar];
				Reg[ac] = mdr;
				break;
			}
			case 2:{
				ea = memory[Address];
				mar = ea;
				mdr = memory[mar];
				Reg[ac] = mdr;
				break;
			}
			case 3:{
				ea = memory[x0 + Address];
				mar = ea;
				mdr = memory[mar];
				Reg[ac] = mdr;
				break;
			}
			}
			
		}
		case STR:{
			Address = operands & 0b111111;
			operands = operands>>>6;
			ac = operands & 0b11;
			operands = operands>>>2;
			x = operands & 0b11;
			switch(x){
				case 0:{
					ea = Address;
					memory[ea] = Reg[ac];
				}
				case 1:{
					ea = x0 + Address;
					memory[ea] = Reg[ac];
					
				}
				case 2:{
					ea = memory[Address];
					memory[ea] = Reg[ac];
				}
				case 3:{
					ea = memory[x0 + Address];
					memory[ea] = Reg[ac];
				}
			}
			
		}
		
		}
		
		
	}
}
