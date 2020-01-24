/**
 *  This is the Utils class
 *  With this it's easier to show text in a nice way
 * 
 * @author  Jordy v Dijken
 * @version 2020.1.22
 */
public class Utils {
	/**
     * Show the text in a typing way. Single line.
     * Duration 1 is 1 sec.
     * @param String the text you want to show
     * @param float the speed of showing the text
     * @return void.
     */
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
	/**
     * Show the text in a typing way. Single line no end line.
     * Duration 1 is 1 sec.
     * @param String the text you want to show
     * @param float the speed of showing the text
     * @return void.
     */
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
