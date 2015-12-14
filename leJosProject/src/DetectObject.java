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
	
	public DetectObject(RawDawRobot _robot){
		robot = _robot;
	}

	@Override
	public boolean takeControl() {
		return robot.detectObstacl();
	}

	@Override
	public void action() {
		robot.arreter();
		robot.tournerLeft();
		robot.avancer(25);
	}

	@Override
	public void suppress() {
		
	}
	
	
	
	
	
}
