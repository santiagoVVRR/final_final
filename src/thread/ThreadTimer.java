package thread;

import javafx.scene.text.Text;
import model.Field;

public class ThreadTimer extends Thread{

	private Field f;
	private Text t;
	
	
	public ThreadTimer(Field f, Text t) {
		
		this.f = f;
		this.t = t;
	}
	
	@Override
	public void run() {
		while(true) {
			f.getTimer().start();
			String time = f.getTimer().getTime();
			t.setText(time);
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
