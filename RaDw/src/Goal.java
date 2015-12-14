import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.subsumption.Behavior;


public class Goal implements Behavior {

	RawDawRobot robot;
	Waypoint origin = new Waypoint(0, 0, 180);
	Waypoint goal = new Waypoint(180, 0, 0);
	private boolean stop = false;
	
	public Goal(RawDawRobot _robot) {
		robot = _robot;
	}
	
	public void MovetoGoal(){
		Pose p = robot.PositionRawDaw();
		
		float angle = (float) (-p.getHeading() + goal.getHeading());
		
		robot.tournerLeft(angle);
		p = robot.PositionRawDaw();
		
		while(!ColorDetector.getMoyeneColor().equals("Noir")){
			robot.avancer();
		}
		robot.ouvrirPince();
		robot.reculer(10);
		/* Doit tourner vert le point de depart ( tourner et non pas avancer ) */
		p = robot.PositionRawDaw();
		angle = (float) (-p.getHeading() + origin.getHeading());
		robot.tournerLeft(angle);
	}
	@Override
	public boolean takeControl() {
		//		return robot.isPinceClosed() && robot.touchPalet() ;
		return robot.isPinceClosed() && robot.touchPalet() ;
	}

	@Override
	public void action(){
		robot.tournerLeft(10);
		robot.avancer(5);
		robot.pilot.setRotateSpeed(170);
		MovetoGoal();
		while(ColorDetector.getMoyeneColor().equals("Blanc") || ColorDetector.getMoyeneColor().equals("Noir")){
			robot.avancer();
		}
		stop = false;

	}

	@Override
	public void suppress() {
		stop = true ;
	}

}
