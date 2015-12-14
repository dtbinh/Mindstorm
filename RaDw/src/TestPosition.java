import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.Transmittable;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;


public class TestPosition {

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

		Waypoint origin = new Waypoint(0, 0, 180);
		Waypoint goal = new Waypoint(220, 0, 0);
		Waypoint goal2 = new Waypoint(50, 50, 0);
		
		OdometryPoseProvider pp = new OdometryPoseProvider(robot.pilot);
		Pose pose ;

		pose = pp.getPose();
		System.out.println("X" + pose.getX()+" Y"+pose.getY());
		System.out.println("H"+pose.getHeading());
		float angle = 0;
		float distance = 0;
		Button.ENTER.waitForPressAndRelease();	

		while(!Button.ESCAPE.isDown()){
			//robot.pilot.rotate(78);
			pose = pp.getPose();
			angle = (float) (-pose.getHeading() + goal.getHeading());
			robot.pilot.rotate(angle);
			distance = pose.distanceTo(goal);
			robot.pilot.travel(distance);

			pose = pp.getPose();
			System.out.println("XG" + pose.getX()+" YG"+pose.getY());
			System.out.println("HG"+pose.getHeading());

			Button.ENTER.waitForPressAndRelease();	

			//robot.pilot.rotate(180);
			pose = pp.getPose();
			angle = (float) (-pose.getHeading() + origin.getHeading());
			robot.pilot.rotate(angle);
			distance = pose.distanceTo(origin);
			robot.pilot.travel(distance);
			
			pose = pp.getPose();
			System.out.println("OX" + pose.getX()+" OY"+pose.getY());
			System.out.println("OH"+pose.getHeading());

			Button.ENTER.waitForPressAndRelease();	

			//robot.pilot.rotate(180);
			/*
			pose = pp.getPose();
			angle = (float) (-pose.getHeading() + goal2.getHeading());
			robot.pilot.rotate(angle);
			distance = pose.distanceTo(origin);
			robot.pilot.travel(distance);

			System.out.println("O2" + pose.getX()+" O2"+pose.getY());
			System.out.println("O2"+pose.getHeading());
*/
		}



	}

}
