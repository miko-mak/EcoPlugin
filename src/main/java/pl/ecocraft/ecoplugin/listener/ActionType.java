package pl.ecocraft.ecoplugin.listener;

public enum ActionType {

    CAMPFIRE_PLACE(0.06),
    FURNACE_BURN(0.07),
    GROUND_ITEMS(0.01),
    EXPLOSIONS(0.025),
    SAPLINGS(0.033),
    BEEHIVE(0.033),
    ANIMALS_KILL(0.02),
    SOLAR_PANELS(0.03);

    private final double points;

    ActionType(double points) {
        this.points = points;
    }

    public double getPoints() {
        return this.points;
    }
}