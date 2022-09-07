package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.ecocraft.ecoplugin.listener.ActionType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;


public class BlockPlaceListener implements Listener {

    private final ServerPollution pollution;

    public BlockPlaceListener(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Material block = event.getBlockPlaced().getType();

        if(block == Material.CAMPFIRE || block == Material.SOUL_CAMPFIRE){
            pollution.addPoints(ActionType.CAMPFIRE_PLACE.getPoints());
            return;
        }

        if(block.toString().contains("SAPLING")) {
            if(pollution.getTotalPoints() < 65){
                pollution.removePoints(ActionType.SAPLINGS.getPoints());
                return;
            }
        }

        if(block == Material.BEEHIVE) {
            pollution.removePoints(ActionType.BEEHIVE.getPoints());
            return;
        }

        if(block == Material.DAYLIGHT_DETECTOR) {
            pollution.removePoints(ActionType.SOLAR_PANELS.getPoints());
        }
    }
}