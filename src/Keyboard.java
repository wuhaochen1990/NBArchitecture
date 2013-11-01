
public class Keyboard {
	public static int active;//status of the keyboard, 0 is inactive and 1 is active
	public static int r;//destination register index
	public static int content;//content of the key 
	public static int interrupt=1;//interrupt
	
	public static int getActive() {
		return active;
	}
	public static void setActive(int active) {
		Keyboard.active = active;
	}
	public static int getR() {
		return r;
	}
	public static void setR(int r) {
		Keyboard.r = r;
	}
	
	public static int getContent() {
		return content;
	}
	public static void setContent(int content) {
		Keyboard.content = content;
	}
	//function to activate the keyboard
	public static void activateKeyboard(){
		active = 1;
	}
	//function to inactivate the keyboard
	public static void inactivateKeyboard(){
		active = 0;
	}
	
	public static int getInterrupt() {
		return interrupt;
	}
	public static void setInterrupt(int interrupt) {
		Keyboard.interrupt = interrupt;
	}
	public static void runKeyboard(){
		
		GPRegister.setReg(content, r);
		
	}
}
