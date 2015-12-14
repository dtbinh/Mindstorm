import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.robotics.subsumption.Behavior;


/**
 * 
 * @author 
 * Detect object and avoid it (can be wall or robot)
 */
 

public class DetectObject implements Behavior {
	RawDawRobot robot;
	boolean suppress = false;
	public DetectObject(RawDawRobot _robot){
		robot = _robot;
	}

	@Override
	public boolean takeControl() {
		return robot.detectObstacl();
	}

	@Override
	public void action() {
		while(!suppress){
			robot.arreter();
			robot.tournerLeft();
			robot.avancer(25);
			if( !ColorDetector.getMoyeneColor().equals("Blanc"))
				break;
		}
		suppress = false;
	}

	@Override
	public void suppress() {
		suppress = true;
	}
	
	
	
	
	
}
