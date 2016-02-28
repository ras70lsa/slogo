package Utilities;

public class Distance {
	public static double calculateDistance(double startX, double startY, double endX, double endY){
		return Math.sqrt(Math.pow((endX - startX), 2) + Math.pow((endY - startY), 2));
	}

}

