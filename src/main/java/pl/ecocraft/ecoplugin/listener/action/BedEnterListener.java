package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import pl.ecocraft.ecoplugin.listener.ActionType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;


public class BedEnterListener implements Listener {

    private final ServerPollution pollution;

    public BedEnterListener(ServerPollution pollution){
        this.pollution = pollution;
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        World.Environment environment = player.getWorld().getEnvironment();

        if(environment == World.Environment.THE_END) {
            pollution.removePoints(ActionType.EXPLOSIONS.getPoints());
        }
    }
}