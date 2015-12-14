import lejos.robotics.subsumption.Behavior;

public class DetectPalet implements Behavior {

	RawDawRobot robot;
	
	public DetectPalet(RawDawRobot _robot){
		robot = _robot;
	}

	
	public void action() {
		robot.fermerPince();
	}

	// to stop immediatly action
	public void suppress() {
		//stop = true;
	}

	public boolean takeControl() {
		return robot.touchPalet();
	}
	
	
}