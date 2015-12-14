import lejos.robotics.navigation.DifferentialPilot;


public class Wheel {

	private DifferentialPilot pilot;


	public Wheel(DifferentialPilot _pilot){
		pilot = _pilot ;
		pilot.setTravelSpeed(20);
	}

	public void forward(){
		pilot.forward();
	}
	
	public void forward(double cm){
		pilot.travel(cm);
	}

	public void backward(){
		pilot.backward();
	}
	
	public void backward(double cm){
		pilot.travel(-cm);
	}
	
	public void stop(){
		pilot.stop();
	}

	public void turnBack() {
		pilot.rotate(180);
	}
}
