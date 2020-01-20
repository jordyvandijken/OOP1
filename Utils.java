
public class Utils {

	static public void DisplayText(String string, float pause) {
		char[] charString = string.toCharArray();
				
		pause = pause * 1000;
		
		for (int i = 0; i < string.length(); i++) {
			System.out.print(charString[i]);
			
			try {
			    Thread.sleep((long) pause);
			} 
			catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		System.out.println();
	}
}
