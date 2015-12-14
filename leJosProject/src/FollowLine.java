import lejos.robotics.subsumption.Behavior;


public class FollowLine implements Behavior {

	RawDawRobot robot;
	String currentColor;
	ColorDetector colorDetect;
	String colorBlanc = "Blanc" ;
	Navigate nav;
	boolean stop , follow ;
	float speedRot = 30;


	public FollowLine(RawDawRobot _robot) {
		stop = follow = false;
		robot = _robot;
		colorDetect = robot.getCaptColor(); 
		nav = robot.getNavigator();
	}

	@Override
	public boolean takeControl() {
		currentColor = colorDetect.getCurrentColor();
		if(currentColor != colorBlanc)
			return true;
		else 
			return false;
	}

	@Override
	public void action() {

		followLigne(currentColor);
		stop = false;
	}

	private void followLigne(String colorActuel) {
		float rotation= 5;
		String colorRechercher = colorActuel ;
		boolean turnLeft = true;
		// avance tq colorActuel ne change pas
		nav.setSpeedRot(speedRot);
		
		while(rotation < 90 && !stop){
			while(colorRechercher == colorDetect.getCurrentColor())
				nav.avancer(speedRot);
			
			nav.stop();
			if(colorActuel!=colorBlanc) colorRechercher = colorActuel;
			rotation = (float) 45.0;
			//trouve = false;
			
			//sinon Racherche la couleur
			while(colorRechercher != colorDetect.getCurrentColor() && !stop && (rotation < 90) ){
				if(turnLeft){
					robot.tournerLeft(rotation);
					if(colorRechercher == colorDetect.getCurrentColor()){
						turnLeft = true;
						break;
					}
					
					robot.tournerLeft(-2*rotation, true);
					while(nav.isMovingNav() && !stop)
						if (colorDetect.getCurrentColor()==colorRechercher){
							turnLeft = false;
							break;
						}
					if(stop) break;
					robot.tournerLeft(rotation, false);
					
					
				
					
				}else {
					robot.tournerLeft(-rotation);
					
					while(nav.isMovingNav() && !stop)
							if (colorDetect.getCurrentColor()==colorRechercher){
								turnLeft = false;
								break;
							}
					if(stop) break;
					robot.tournerLeft(2*rotation, true);
					while(nav.isMoving() && !stop)
						if (colorDetect.getCurrentColor()==colorRechercher){
							turnLeft = true;
							//trouve = true;
							break;
						}
					if(stop) break;
					robot.tournerLeft(-rotation, false);
				}
				rotation += rotation;
			}
		}
		


	}

	@Override
	public void suppress() {
		stop = true;
	}
}
