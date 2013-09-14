
public class Simulator {
	//max address of the memory of this system
//	public static final int MAX_ADDRESS=9999;
//	//
//	public static final int[] memory = new int[MAX_ADDRESS+1];
	//instruction code
	public static final int LDR = 1;
	public static final int STR = 2;

	//the value of some key words
	public static int opcode;//opcode
	public static int operands;//numbers after the opcode
	public static int ac;
	public static int x;
	public static int Address;

	//some registers
	//x0 register
	public static int x0;
	//general purpose register
	public static int Reg[] = new int[4];
	//mdr and mar and ir
	public static int mdr;
	public static int mar;
	public static int ir;

	//get the ea! effective address by switch the x value
	public static int getEA(int x){
		int ea = 0;
		switch(x){
		case 0:{
			ea = Address;
			break;
		}
		case 1:{
			ea = x0+Address;
			break;
		}
		case 2:{
			ea = Memory.getDataFromMemory(Address);
			break;
		}
		case 3:{
			ea = Memory.getDataFromMemory(x0 + Address);
			break;
		}
		}
		return ea;
	}
	
	//run the instruction
	public static void run(){
		//fetch the instruction from the memory
		mar = ProgramCounter.getPC();//mar <- pc
		ProgramCounter.incrementPC();//pc ++
		mdr = Memory.getDataFromMemory(mar);//mdr <- c(mar)
		ir = mdr;//ir <- mdr
		
		//decode the instruction
		operands = ir & 0b1111111111;// the last ten bits is operands
		int temp = ir>>>10;// right shift ten bits
		opcode = temp & 0b111111;// get the six bits opcode
//		System.out.println(Integer.toString(operands));
//		System.out.println(Integer.toString(opcode));

		//choose the instruction function
		switch(opcode){
		case LDR:{
			System.out.println("LDR");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			x = operands & 0b11;//I and IX  
			operands = operands>>>2;
			ac = operands & 0b11;//register number
			mar = getEA(x);
			mdr = Memory.getDataFromMemory(mar);
			Reg[ac] = mdr;
			
		}
		case STR:{
			Address = operands & 0b111111;
			operands = operands>>>6;
			ac = operands & 0b11;
			operands = operands>>>2;
			x = operands & 0b11;
			Memory.setData2Memory(Reg[ac], getEA(x));

			
		}
		
		}
		
		
	}
}
