import lejos.geom.Point;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;


public class Navigate {

	DifferentialPilot pilot;
	Navigator nav;
	Pose pose;
	Point origin,goal;
	

	public Navigate(DifferentialPilot _pilot) {
		origin = new Point(0, 0);
		pilot = _pilot;
		nav = new Navigator(pilot);
		init();
	}

	private void init(){
		pose = new Pose(0,0,0);
		nav.getPoseProvider().setPose(pose); 
	}

	public void saveGoal(){
		nav.rotateTo(pose.getHeading());
	}

	public boolean isMoving(){
		return pilot.isMoving();
	}

	public float getX() {
		return nav.getPoseProvider().getPose().getX();
	}
	
	public float getY() {
		return nav.getPoseProvider().getPose().getY();
	}
	
	public float getHeading() {
		return nav.getPoseProvider().getPose().getHeading();
	}
	
	public float angleTo(Point p) {
		return nav.getPoseProvider().getPose().angleTo(p) ;
	}
	
	
	public void goToGoal(Point pGoal){
		float h = getHeading(); 
		float angle = angleTo(pGoal);
		
		float rot = ( -h + angle);
		turnTo(rot);
		
		travelTo(distanceTo(pGoal),true);
		
	}
	
	public float distanceTo(Point p){
		return
				nav.getPoseProvider().getPose().distanceTo(p);
	}
	
	public void travelTo(float d, boolean immedit){
		pilot.travel(d,immedit);
	}
	public void turnTo(float a){
		pilot.rotate(a);
	}
	
	public void setSpeedNav(float v){
		pilot.setTravelSpeed(v);
	}
	
	public void avancer(float v){
		setSpeedNav(v);
		pilot.forward();
	}
	
	public void setSpeedRot(float v){
		pilot.setRotateSpeed(v);
	}

	public void stop() {
		pilot.stop();
	}
	
	public boolean isMovingNav(){
		return pilot.isMoving();
	}

	

}
