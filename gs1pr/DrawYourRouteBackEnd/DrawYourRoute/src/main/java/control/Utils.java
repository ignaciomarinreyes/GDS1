
package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Coordinate;

public class Utils {
    
    public static double calculateAccuracy(List<Coordinate> routeToCopyHibernate, ArrayList<Coordinate> routeToDo){
        ArrayList<Coordinate> routeToCopy = new ArrayList<Coordinate>(routeToCopyHibernate);
        return 100 - calculateError(routeToCopy, routeToDo);
    }

    private static double calculateError(ArrayList<Coordinate> routeToCopy, ArrayList<Coordinate> routeToDo) {
        double distanceRouteToCopy = calculateDistanceRoute(routeToCopy);
        double distanceBetweenRoutes = calculateDifferenceBetweenRoutes(routeToCopy, routeToDo);
        double error = (distanceBetweenRoutes * 100) / distanceRouteToCopy;
        double errorRound = ((double)Math.round(error * 100d) / 100d);
        if (error > 100){
            errorRound = 100;
        }
        return errorRound;
    }

    private static double calculateDifferenceBetweenRoutes(ArrayList<Coordinate> routeToCopy, ArrayList<Coordinate> routeToDo) {
        double[] distanceArray = new double[routeToDo.size()];
        int i = 0;
        double distanceBetweenRoutes = 0;
        for (Coordinate coordinateToCopy: routeToCopy){
            for (Coordinate coordinateToDo: routeToDo) {
                distanceArray[i] = distanceBetweenCoordinates(coordinateToCopy.getLat(),coordinateToCopy.getLng(), coordinateToDo.getLat(), coordinateToDo.getLng());
                i++;
            }
            distanceBetweenRoutes += Arrays.stream(distanceArray).min().getAsDouble();
            i = 0;
        }
        return distanceBetweenRoutes;
    }

    private static double calculateDistanceRoute(ArrayList<Coordinate> routeToCopy) {
        double sum = 0;
        for (int i = 0; i < routeToCopy.size() - 1; i++){
            sum += distanceBetweenCoordinates(routeToCopy.get(i).getLat(), routeToCopy.get(i).getLng(), routeToCopy.get(i + 1).getLat(), routeToCopy.get(i + 1).getLng());
        }
        return sum;
    }

    public static double distanceBetweenCoordinates(double lat1, double lng1, double lat2, double lng2) {
        //double radioTierra = 3958.75;//en millas
        double radioEarth = 6371;//en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        return radioEarth * va2;
    }   
}
