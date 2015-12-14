import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;


public class CapteurTouch {
	
	private TouchSensor touch;
	
	public CapteurTouch(SensorPort p){
		touch = new TouchSensor(p);
	}
	
	public boolean isTouched(){
		return touch.isPressed();
	}

}
