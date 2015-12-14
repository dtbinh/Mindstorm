import lejos.robotics.subsumption.Behavior;


public class Goal implements Behavior {

	RawDawRobot robot;
	private boolean stop = false;
	
	public Goal(RawDawRobot _robot) {
		robot = _robot;
	}

	@Override
	public boolean takeControl() {
		//return robot.isPinceClosed() && robot.touchPalet() ;
		return robot.isPinceClosed() ;
	}

	@Override
	public void action() {
		robot.tournerLeft(45);
		robot.avancer(25);
		robot.TournerBut();
		robot.avancer();
		while(!stop) {
			robot.avancer();
			if(robot.putPaletInGoal()){
				robot.avancer(15);
				robot.ouvrirPince();
				robot.reculer(20);
				robot.demiTour();
				//avancer + lacher
				stop = true;
			}
		}
		robot.arreter();
		stop = false;

	}

	@Override
	public void suppress() {
		stop = true ;
	}

}
