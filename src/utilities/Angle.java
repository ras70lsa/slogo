package utilities;

public class Angle {
	public static final double HALF_CIRCLE = 180;
	public static final double FULL_CIRCLE = 360;
	
	public static double mod360(double angle){
		return angle % (2*HALF_CIRCLE);
	}
	
	public static double calculateAngleRotated(double start, double end){
		double startingAngle = handleNegativeAngle(start);
		double endingAngle = handleNegativeAngle(end);
		return endingAngle - startingAngle;
	}
	public static double calculateAngleBetweenPoints(double x1, double y1, double x2, double y2){
		double dy =  y2 - y1;
		double dx = x2 - x1;
		return handleNegativeAngle(Math.toDegrees(Math.atan(dy/dx)));
	}
	
	public static double handleNegativeAngle(double angle){
		return (angle >= 0)? angle: (FULL_CIRCLE-mod360(-angle));
	}
}
