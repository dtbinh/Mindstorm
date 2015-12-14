import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.ColorSensor.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MoveController;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class RawDawRobot {

	/* left, right and Center Motor */
	// private static SensorPort P_TOUCH = SensorPort.S2;
	
	Yeux eyes;
	DifferentialPilot pilot;
	Pince pince;
	CapteurTouch touch;
	Wheel roue;
	Navigate nav;
	ColorDetector color;
	int MAX_DIST = 20 ;
	double largeurWheels = 11.0;
	String currentColor;

	public RawDawRobot(SensorPort p_ULTRA, SensorPort p_COLOR,
			SensorPort p_TOUCH, SensorPort p_INFRA, RegulatedMotor rMotor,
			RegulatedMotor lMotor, RegulatedMotor cMotor) {
		
		pince = new Pince(cMotor);
		pilot = new DifferentialPilot(
				MoveController.WHEEL_SIZE_NXT1-0.18,
				largeurWheels, rMotor, lMotor);
		eyes = new Yeux(p_ULTRA);
		touch = new CapteurTouch(p_TOUCH);
		roue = new Wheel(pilot);
		nav = new Navigate(pilot);
		color = new ColorDetector(p_COLOR);
	}
	
	public void suivreLigne(){
		
	}
	
	public Navigate getNavigator() {
		return nav;
	}
	
	
	public boolean isPinceClosed(){
		return pince.isClose();
	}
	
	public boolean isPinceOpen(){
		return pince.isOpen();
	}
	
	public void ouvrirPince(){
		pince.OpenPince();
	}
	
	public void fermerPince(){
		pince.ClosePince();
	}
	
	public void avancer(){
		roue.forward();
	}
	
	public void avancer(int cm){
		roue.forward(cm);
	}
	
	public void reculer(){
		roue.backward();
	}
	
	public void reculer(double cm){
		roue.backward(cm);
	}
	
	public void demiTour(){
		roue.turnBack();
	}
	
	public void arreter(){
		roue.stop();
	}

	public void tournerLeft(){
		pilot.rotate(90);
	}
	
	public void tournerLeft(double angle){
		pilot.rotate(angle);
	}
	
	public void tournerLeft(double angle , boolean mode){
		pilot.rotate(angle,mode);
	}
	
	public void tournerRight(){
		pilot.rotate(-90);
	}
	
	public void TournerBut(){
		nav.saveGoal();
	}
	
	public DifferentialPilot getPilot(){
		return pilot;
	}
	
	public ColorDetector getCaptColor() {
		return color;
	}
	
	public static void main(String[] args) {
		SensorPort P_COLOR = SensorPort.S2;
		SensorPort P_ULTRA = SensorPort.S1;
		SensorPort P_TOUCH = SensorPort.S3;
		SensorPort P_INFRA = SensorPort.S4;

		RegulatedMotor rMotor = Motor.A;
		RegulatedMotor cMotor = Motor.B;
		RegulatedMotor lMotor = Motor.C;

		RawDawRobot robot = new RawDawRobot(P_ULTRA, P_COLOR, P_TOUCH, P_INFRA,
				rMotor, lMotor, cMotor);
		
		Behavior b0  = new RobotForward(robot);
		Behavior bf  = new FollowLine(robot);
		
		Behavior b1  = new DetectPalet(robot);
		Behavior b2 = new DetectObject(robot);// prio evit obstacle
		Behavior b3 = new Goal(robot);
		
		Behavior[] array = {b0,bf,b1,b3,b2};
		Arbitrator arby = new Arbitrator(array);
		robot.pince.OpenPince();
		
		arby.start();
	}

	public boolean touchPalet() {
		return touch.isTouched();
	}
	
	public boolean detectObstacl() {
		return eyes.getDistance() <  MAX_DIST;
	}
	
	/**
	 * 
	 * 0 rouge
	 * 1 vert
	 * 2 bleu
	 * 3 yello
	 * 4 maganta
	 * 5 orange
	 * 6 blanc
	 * 7 noir
	 * rose
	 * gris
	 * cricler
	 * gris fonce
	 * 12 cyan 
	 */
	public boolean putPaletInGoal(){
		currentColor = 	color.getCurrentColor();
		if(currentColor.equals("Noir"))
			return true;
		return false;
	}

	/*
	 * Initialise la requête préparée basée sur la connexion passée en argument,
	 * avec la requête SQL et les objets donnés.
	 */

}