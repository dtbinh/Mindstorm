import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;


public class Navigate {

	DifferentialPilot pilot;
	Navigator nav;
	Pose p;
	
	public Navigate(DifferentialPilot _pilot) {
		pilot = _pilot;
		nav = new Navigator(pilot);
		initposition();
	}

	private void initposition(){
		nav.getPoseProvider().setPose(new Pose(0,0,0));
		p = new Pose(0,0,0);
	}
	
	public void saveGoal(){
		nav.rotateTo(p.getHeading());
	}
	
	public void goAndBack(){
	
	}
	
	
	
}
