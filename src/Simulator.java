
public class Simulator {

	//instruction code
	public static final int LDR = 1;
	public static final int STR = 2;
	public static final int LDA = 3;
	public static final int LDX = 41;
	public static final int STX = 42;
	public static final int JZ = 10;
	public static final int AMR = 4;

	//the value of some key words
	public static int opcode;//opcode
	public static int operands;//numbers after the opcode
	public static int ac;
	public static int x;
	public static int Address;

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
			ea = X0Reg.getX0()+Address;
			break;
		}
		case 2:{
			ea = Memory.getDataFromMemory(Address);
			break;
		}
		case 3:{
			ea = Memory.getDataFromMemory(X0Reg.getX0() + Address);
			break;
		}
		}
		return ea;
	}
	
	//run the instruction
	public static void run(){
		//run until there is not instruction in the memory where pc point to
		while(Memory.getDataFromMemory(ProgramCounter.getPC()) != 0){
		//fetch the instruction from the memory
		mar = ProgramCounter.getPC();//mar <- pc
		ProgramCounter.incrementPC();//pc ++
		mdr = Memory.getDataFromMemory(mar);//mdr <- c(mar)
		ir = mdr;//ir <- mdr
		
		//decode the instruction
		operands = ir & 0b1111111111;// the last ten bits is operands
		int temp = ir>>>10;// right shift ten bits
		opcode = temp & 0b111111;// get the six bits opcode
		

		//choose the instruction function
		switch(opcode){
		case LDR:{
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			mar = getEA(x);
			mdr = Memory.getDataFromMemory(mar);
			GPRegister.setReg(mdr, ac);
			break;
		}
		case STR:{
			Address = operands & 0b111111;
			operands = operands>>>6;
			ac = operands & 0b11;
			operands = operands>>>2;
			x = operands & 0b11;
			Memory.setData2Memory(GPRegister.getReg(ac), getEA(x));
			break;
		}
		case LDA:{
			Address = operands & 0b111111;
			operands = operands>>>6;
			ac = operands & 0b11;
			operands = operands>>>2;
			x = operands & 0b11;
			if(x == 2 || x == 3){
				GPRegister.setReg(Memory.getDataFromMemory(getEA(x)), ac);
			}else{
				GPRegister.setReg(getEA(x), ac);
			}
			break;
		}
		case LDX:{
			Address = operands & 0b111111;
			operands = operands>>>6;
			x = operands&0b11;
			X0Reg.setX0(getEA(x));
			break;
		}
		case STX:{
			Address = operands & 0b111111;
			operands = operands>>>6;
			x = operands&0b11;
			Memory.setData2Memory(X0Reg.getX0(), getEA(x));
			break;
		}
		case JZ:{
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			if(GPRegister.getReg(ac) == 0){
				if(x == 2 || x == 3){
					//because we have do incrementPC above, so we have to decrement
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(Memory.getDataFromMemory(getEA(x)));
				}else{
					ProgramCounter.decrementPC();
					ProgramCounter.setPC(getEA(x));
				}
			}else{
				
				ProgramCounter.decrementPC();
				ProgramCounter.incrementPC();
			}
		}
		case AMR:{
			System.out.println("AMR");
			Address = operands & 0b111111;//address from the instruction
			operands = operands>>>6;
			ac = operands & 0b11;//register number  
			operands = operands>>>2;
			x = operands & 0b11;//I and IX
			ALU.setDestination(ac);//set destination register
			ALU.setSource(Memory.getDataFromMemory(getEA(x)));//set source content
			ALU.setOperation(0);//add operation number is 0
			ALU.runALU();
			break;
		}
		}
		}
		
	}
}
