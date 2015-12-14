import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

/**
 * 
 * @author 
 * Color sensor Scomponent
 *
 */


public class DetectColor {

	private ColorSensor cs;

	public DetectColor(SensorPort p) {
		cs = new ColorSensor(p);
	}
	
	
	public int getActualColor(){
		int c = -1 ;
		c = cs.getColorID();
		return c;
	}

	
}
