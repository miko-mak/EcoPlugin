package pl.ecocraft.ecoplugin.pollution;


import java.text.DecimalFormat;

public class ServerPollution {

    public double pointsCollection;
    private final DecimalFormat format = new DecimalFormat("##.00");

    public double getTotalPoints() {
        return Double.parseDouble(format.format(pointsCollection));
    }

    public void addPoints(double num) {
        double result = getTotalPoints() + num;
        if(result > 100) {
            pointsCollection = 100;
            return;
        }

        pointsCollection = result;
    }

    public void removePoints(double num) {
        double result = getTotalPoints() - num;
        if(result < 0){
            pointsCollection = 0;
            return;
        }

        pointsCollection = result;
    }
}