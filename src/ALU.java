
public class ALU {
	public static int destination;//destination register index 0-4
	public static int source;//sources of the operation
	public static int operation;//operation means add 0 or sub 1 or mul 2  or div 3 
	
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


	public static void runALU(){
		switch(operation){
			case(0):{
				//add data1 and data2
				int data1 = GPRegister.getReg(destination);
				int data2 = source;
				int result = data1 + data2;
				GPRegister.setReg(result, destination);
				break;
			}
			case(1):{
				//sub
				int data1 = GPRegister.getReg(destination);
				int data2 = source;
				int result = data1 - data2;
				GPRegister.setReg(result, destination);
			}
		}
	}
	
}
