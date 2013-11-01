
public class IO {
	public static int r;
	public static int devid;
	public static int operation;//in 0, out 1, chk 2
	
	public static int getR() {
		return r;
	}
	public static void setR(int r) {
		IO.r = r;
	}
	public static int getDevid() {
		return devid;
	}
	public static void setDevid(int devid) {
		IO.devid = devid;
	}
	public static int getOperation() {
		return operation;
	}
	public static void setOperation(int operation) {
		IO.operation = operation;
	}
	
	
	public static void runIO(){
		switch(operation){
		case(0):{
			//IN 
			switch(devid){
			case(0):{
				//keyboard
				Keyboard.setInterrupt(0);//set the interrupt
				Keyboard.activateKeyboard();
				break;
			}
			case(2):{
				//card reader
				break;
			}
			}
			break;
		}
		case(1):{
			//OUT
			if(devid == 1){
				//OUT only works on console printer whose devid is 1
				Printer.activatePrinter();//activate the printer
				Printer.setContent(GPRegister.getReg(r));//content to print
			}
			break;
		}
		case(2):{
			//CHK
			switch(devid){
			case(0):{
				//keyboard
				int status = Keyboard.active;
				GPRegister.setReg(status, r);
				break;
			}
			case(1):{
				//printer
				int status = Printer.active;
				GPRegister.setReg(status, r);
				break;
			}
			case(2):{
				//card reader
				
				break;
			}
			}
			break;
		}
		}
	}
}
