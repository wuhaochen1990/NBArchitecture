
public class Printer {
	public static int active;//status of the printer
	public static String content;//source register of the printer
	public static int getActive() {
		return active;
	}
	public static void setActive(int active) {
		Printer.active = active;
	}
	
	
	
	
	public static String getContent() {
		return content;
	}
	public static void setContent(String content) {
		Printer.content = content;
	}
	//function to activate the printer
	public static void activatePrinter(){
		active = 1;
	}
	//function to inactivate the printer
	public static void inactivatePrinter(){
		active = 0;
	}
	
	public static void runPrinter(){
		
	}
}
