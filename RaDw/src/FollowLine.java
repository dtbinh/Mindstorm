import lejos.robotics.subsumption.Behavior;


public class FollowLine implements Behavior {
	RawDawRobot robot;
	boolean suppress;
	boolean trouve = true;
	String CouleurAct;
	public FollowLine(RawDawRobot _robot) {
		// TODO Auto-generated constructor stub
		robot = _robot;
		suppress = false;
	}

	@Override
	public boolean takeControl() {
		CouleurAct = ColorDetector.getMoyeneColor();
		if(!CouleurAct.equals("Blanc")){
			return true;
		}else
			return false;
	}

	@Override
	public void action() {
		String fcolor = CouleurAct;
		robot.pilot.setRotateSpeed(20);
		SuivreLigne(fcolor);
		suppress = false;
	}

	@Override
	public void suppress() {
		suppress = true;
	}

	public boolean onLine(){
		if (!ColorDetector.getMoyeneColor().equals("Blanc"))
			return true;
		return false;
	}

	public void SuivreLigne(String colorAc){
		String _colorActual , _colorRechercher ;
		boolean turnLeft = true;
		boolean trouve = true;
		float rot = 10;
		_colorActual = _colorRechercher = "" ;
		_colorRechercher = colorAc;

		while (((_colorActual = ColorDetector.getMoyeneColor()).equals(_colorRechercher)) && !suppress){
			robot.avancer();
		}


		if(!_colorActual.equals("Blanc")) _colorRechercher = _colorActual;
		rot = (float) 10;
		trouve = false;


		while((!ColorDetector.getMoyeneColor().equals(_colorRechercher)) && !suppress && (rot < 90)){
			if (turnLeft){
				robot.tourner(rot, true);
				while(robot.isPilotMoving() && !suppress)
					if (ColorDetector.getMoyeneColor().equals(_colorRechercher)){ 
						turnLeft = true;
						trouve = true;
						break;
					}
				if(trouve || suppress) break;
				robot.tourner(-2*rot, true);
				while(robot.isPilotMoving() && !suppress)
					if (ColorDetector.getMoyeneColor().equals(_colorRechercher)){
						turnLeft = false;
						trouve = true;
						break;
					}
				if(trouve || suppress) break;
				robot.tourner(rot, false);
			}else{
				robot.tourner(-rot, true);
				while(robot.isPilotMoving() && !suppress)
					if (ColorDetector.getMoyeneColor().equals(_colorRechercher)){
						turnLeft = false;
						trouve = true;
						break;
					}
				if(trouve || suppress) break;
				robot.tourner(2*rot, true);
				while(robot.isPilotMoving() && !suppress)
					if (ColorDetector.getMoyeneColor().equals(_colorRechercher)){
						turnLeft = true;
						trouve = true;
						break;
					}
				if(trouve || suppress) break;
				robot.tourner(-rot, false);
			}
			rot += rot;
		}
		//robot.arreter();
	}

	public void SuivreUneLigne(String colorAct){

		boolean turnleft = true;
		float rot = (float)10.0;
		while(EstsurUneLigne(rot) && !suppress){
			robot.avancer();
		}

		trouve = false;
		while(!EstsurUneLigne(rot) && !suppress ){

			if(turnleft){
				robot.pilot.rotate(rot);
				if(EstsurUneLigne(rot)){
					turnleft = true;
					trouve = true;
					break;
				}
				
				robot.pilot.rotate(-2*rot);
				if(!suppress)
					System.out.println("isMov");
			

				if(EstsurUneLigne(rot)){
					turnleft = false;
					trouve = true;
					break;
				}
				//}
				//if ( trouve || suppress)break;
				robot.pilot.rotate(rot);
			}else{
				robot.pilot.rotate(-rot);
				//while(robot.pilot.isMoving() && !suppress){
				System.out.println("PP");
				if(EstsurUneLigne(rot)){
					turnleft = false;
					trouve = true;
					break;
				}
				//}
				//if ( trouve || suppress)break;
				robot.pilot.rotate(2*rot);
				//while(robot.pilot.isMoving() && !suppress){
				if(EstsurUneLigne(rot)){
					turnleft = true;
					trouve = true;
					break;
				}
				//}
				//if (trouve || suppress)
				//break;
				robot.pilot.rotate(-rot);
			}
			rot += (float)5.0;

		}


	}
	public boolean EstsurUneLigne(float r){
		if (!CouleurAct.equals("Blanc"))
			return true;
		float rot = 5;
		if( r < 0 )
			rot = -5;

		robot.pilot.rotate(rot);
		while(rot !=r ){
			CouleurAct = robot.color.getMoyeneColor();
			if (!CouleurAct.equals("Blanc"))
				return true;
			else{
				if( r < 0)
					rot -= 5;
				else
					rot += 5;
			}
		}
		return false;
	}

}