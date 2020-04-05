package scrapper_anime;



public class MainPage {

	
	public static void main(String [] args) {
		System.setProperty("file.encoding", "UTF-8");

		// Stating Threading
		for (int p = 1 ; p<=55 ; p++) {
			 new MainThread(p).start();
		}
	}
	
	
}
