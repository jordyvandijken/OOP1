import java.util.concurrent.TimeUnit;

public class Utils {

	static public void DisplayText(String string, float duration) {
		char[] byteString = string.toCharArray();
			
		long stepduration = (long)(duration * 1000);
		
		for (int i = 0; i < string.length(); i++) {
			System.out.print(byteString[i]);
			
			try {
				Thread.sleep(stepduration);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
	}
	
	static public void DisplayTextSameLine(String string, float duration) {
		char[] byteString = string.toCharArray();
				
		long stepduration = (long)(duration * 1000);
		
		for (int i = 0; i < string.length(); i++) {
			System.out.print(byteString[i]);
			
			try {
				Thread.sleep(stepduration);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
