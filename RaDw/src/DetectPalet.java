import lejos.robotics.subsumption.Behavior;

public class DetectPalet implements Behavior {

	RawDawRobot robot;
	boolean stop = false;
	
	public DetectPalet(RawDawRobot _robot){
		robot = _robot;
	}

	
	public void action() {
		while(!stop)
			robot.fermerPince();
		stop = false;
	}

	// to stop immediatly action
	public void suppress() {
		stop = true;
	}

	public boolean takeControl() {
		return robot.touchPalet();
	}
	
	
}