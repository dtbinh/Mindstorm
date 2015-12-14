import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.RegulatedMotor;


public class TEST {

	/**
	 * @param args
	 */
	
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
		
		robot.putPaletInGoal();
		Button.waitForAnyPress();
	}
}
