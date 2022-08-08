package pl.ecocraft.ecoplugin.pollution;


public class ServerPollution {

    public double pointsCollection;

    public double getTotalPoints() {
        return pointsCollection;
    }

    public void addPoints(double num) {
        double result = pointsCollection + num;
        if(result > 100) {
            pointsCollection = 100;
            return;
        }

        pointsCollection = result;
    }

    public void removePoints(double num) {
        double result = pointsCollection - num;
        if(result < 0){
            pointsCollection = 0;
            return;
        }

        pointsCollection = result;
    }
}