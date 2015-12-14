import lejos.nxt.ColorSensor.Color; 
import lejos.nxt.SensorPort; 
import lejos.nxt.addon.ColorHTSensor;


public class ColorDetector { 

	private static ColorHTSensor chs; // private ColorSensor chs;
	private static int [][] datasColor = {{55,54,81} ,{27,32,29},{160,35,12},{28,76,37},{220,200,27},{231,238,224}} ; 
	private static String []namecolor = { "Bleu" , "Noir","Rouge","Vert","Jaune","Blanc"}; 


	public ColorDetector(SensorPort p) { //chs = new ColorSensor(p); 
		chs = new ColorHTSensor(p);
	} 

	public static String getMoyeneColor(){ /* * */ 
		double d;
		int r = chs.getRGBComponent(Color.RED); 
		int g = chs.getRGBComponent(Color.GREEN); 
		int b = chs.getRGBComponent(Color.BLUE); 
		double dmin;
		int idx=0; 

		dmin = d = 
				Math.sqrt(Math.pow((datasColor[0][0] - r),2) 
						+ Math.pow((datasColor[0][1] - g),2) 
						+ Math.pow((datasColor[0][2] - b),2));
		int i = 1;
		while(i < 6){ 
			d = Math.sqrt(Math.pow((datasColor[i][0] - r),2) 
					+ Math.pow((datasColor[i][1] - g),2) 
					+ Math.pow((datasColor[i][2] - b),2)); 
			if(d < dmin){
				dmin = d;
				idx = i; 
			}
			i++;
		}
	
		return namecolor[idx]; 
	}
}

