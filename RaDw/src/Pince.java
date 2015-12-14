import lejos.robotics.RegulatedMotor;

public class Pince {


	private RegulatedMotor center;
	private final int angle = 30 ;
	private int speedMotor = 80 ;

	public Pince(RegulatedMotor m){
		center = m;
		center.resetTachoCount();
		center.setSpeed(speedMotor);
	}


	public void OpenPince(){
		
		center.rotate(angle - center.getTachoCount());
		while (center.isMoving());
	}


	public void ClosePince(){
		if(isOpen()){
			center.rotate(-center.getTachoCount());
			while (center.isMoving());
		}
	}

	public boolean isOpen(){

		if( center.getTachoCount() > 25 )
			return true;
		else return false;
	}

	public boolean isClose(){

		if( center.getTachoCount() <= 25 )
			return true;
		else return false;
	}
}
