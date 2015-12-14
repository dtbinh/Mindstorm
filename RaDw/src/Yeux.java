import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;


public class Yeux  {

	private UltrasonicSensor sonar;	
	
	public Yeux(SensorPort port){
		sonar = new UltrasonicSensor(port);
	}
	

	/* Return la distance de l'objet scanner */
	public int getDistance(){
		return sonar.getDistance();
	}


	
	

	

}
