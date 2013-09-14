
public class Memory {
	
	//the max address of the memory
	public static final int MAX_ADDRESS=9999;
	//memory content
	public static final int[] memory = new int[MAX_ADDRESS+1];
	//empty index of the memory
	public static int emptyIndex = 0;
	
	//show memory
	public static void showMemory(){
		System.out.println("This is instruction memory!");
		//instruction memory
		for(int i = 0; i < MAX_ADDRESS; i++){
			//show memory until the empty one
			if(memory[i]!=0){
				System.out.println(Integer.toBinaryString(memory[i]));
			}else{
				System.out.println("\n");
				break;
			}
			
		}
		//data memory,after address 50
		System.out.println("This is data memory!");
		for (int j = 50; j < MAX_ADDRESS; j++){
			if(memory[j]!=0){
				System.out.println("Address: "+Integer.toString(j));
				System.out.println("Content: "+Integer.toString(memory[j]));
			}
		}
		System.out.println("\n");
	}
	
	//set instruction to memory
	public static void setInstr2Memory(int instruction){
		//emptyIndex point to the empty position of the memory
		if(memory[emptyIndex] == 0){
			memory[emptyIndex] = instruction;
			emptyIndex += 1;
		}else{
			System.out.println("Full memory!");
		}
	}
	
	//set data into memory
	public static void setData2Memory(int data, int address){
		memory[address] = data;
	}
	
	//get data from memory
	public static int getDataFromMemory(int address){
		return memory[address];
	}
}
