
public class ALU {
	public static int destination;//destination register index 0-4
	public static int source;//sources of the operation
	public static int operation;//operation means add 0 , sub 1 , mul 2  ,  div 3 ,testequality 4, and 5, or 6, not 7,shift 8,rotate 9
	public static int cc[] = new int[16];//condition code. cc[0] is for judge whether the result of the subtract is negative 
	public static int LR;//L <- 0,R <- 1
	public static int AL;//A <- 0,L <- 1
	public static int count;
	
	//set and get
	public static int getDestination() {
		return destination;
	}


	public static void setDestination(int destination) {
		ALU.destination = destination;
	}


	public static int getSource() {
		return source;
	}


	public static void setSource(int source) {
		ALU.source = source;
	}


	public static int getOperation() {
		return operation;
	}


	public static void setOperation(int operation) {
		ALU.operation = operation;
	}

	

	public static int getLR() {
		return LR;
	}


	public static void setLR(int lR) {
		LR = lR;
	}


	public static int getAL() {
		return AL;
	}


	public static void setAL(int aL) {
		AL = aL;
	}


	public static int getCount() {
		return count;
	}


	public static void setCount(int count) {
		ALU.count = count;
	}


	public static void runALU(){
		switch(operation){
			case(0):{
				//add
				int data1 = GPRegister.getReg(destination);
				int data2 = source;
				int result = data1 + data2;
				//add overflow
				GPRegister.setReg(result, destination);
				break;
			}
			case(1):{
				//sub
				int data1 = GPRegister.getReg(destination);
				int data2 = source;
				int result = data1 - data2;
				if(result >= 0){
					//set the cc
					Simulator.cc = 1;
				}else{
					Simulator.cc = 0;
				}
				GPRegister.setReg(result, destination);
				break;
			}
			case(2):{
				//mul
				int rx = GPRegister.getReg(destination);
				int ry = GPRegister.getReg(source);
				int result = rx * ry;
				int lowbit = result & 0b1111111111111111;
				int highbit = result >>> 16;
				GPRegister.setReg(lowbit, destination+1);
				GPRegister.setReg(highbit, destination);
				break;
			}
			case(3):{
				//div
				int rx = GPRegister.getReg(destination);
				int ry = GPRegister.getReg(source);
				if(ry == 0){
					//set cc(3)=1
					cc[3]=1;
				}else{
					int quotient = rx/ry;
					int remainder = rx%ry;
					GPRegister.setReg(quotient, destination);
					GPRegister.setReg(remainder, destination+1);
				}
				break;
			}
			case(4):{
				//test equality
				int rx = GPRegister.getReg(destination);
				int ry = GPRegister.getReg(source);
				if(rx == ry){
					cc[4] = 1;
				}else{
					cc[4] = 0;
				}
				break;
			}
			case(5):{
				//and
				int rx = GPRegister.getReg(destination);
				int ry = GPRegister.getReg(source);
				int result = rx & ry;
				GPRegister.setReg(result, destination);
				break;
			}
			case(6):{
				//or
				int rx = GPRegister.getReg(destination);
				int ry = GPRegister.getReg(source);
				int result = rx | ry;
				GPRegister.setReg(result, destination);
				break;
			}
			case(7):{
				//not
				int rx = GPRegister.getReg(destination);
				int result = ~rx;
				GPRegister.setReg(result, destination);
				break;
			}
			case(8):{
				//shift
				int r = GPRegister.getReg(destination);
				int result;
				if(AL == 0){
					//arithmetic shift
					if(LR == 0){
						//left
						result = r << count;
					}else{
						//right
						result = r >> count;
					}
				}else{
					//logical shift
					if(LR == 0){
						//left
						result = r << count;
					}else{
						//right
						result = r >>> count;
					}
				}
				GPRegister.setReg(result, destination);
				break;
			}
			case(9):{
				//rotate
				System.out.println("rotate");
				int r = GPRegister.getReg(destination);
				System.out.println(r);
				int result;
				if(LR == 0){
					//left
					
					result = (r << count & 0b1111111111111111) | (r >>> (16-count));
				}else{
					//right
					result = r >> count | r << (16-count);
				}
				GPRegister.setReg(result, destination);
				break;
			}
		}
	}
	
}
