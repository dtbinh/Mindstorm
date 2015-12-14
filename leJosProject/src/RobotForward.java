import lejos.robotics.subsumption.Behavior;


public class RobotForward implements Behavior  {

	public static int AREA_WIDTH = 200;
	public static int AREA_LENGTH = 300;
	private boolean stop = false;
	private RawDawRobot robot;
	
	
	public RobotForward(RawDawRobot _robot){
		robot = _robot; 
	}
	
	public void action() {
		robot.ouvrirPince();
		robot.avancer();
		while(!stop ) {
			robot.avancer();
		}
		robot.arreter();
		stop = false;
		
	}

	public void suppress() {
		stop = true;
	}

	public boolean takeControl() {
		return true;
	}

}