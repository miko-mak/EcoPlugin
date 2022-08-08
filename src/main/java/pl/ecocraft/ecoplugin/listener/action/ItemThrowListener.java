package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import pl.ecocraft.ecoplugin.listener.ActionType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;


public class ItemThrowListener implements Listener {

    private final ServerPollution pollution;

    public ItemThrowListener(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onItemThrow(PlayerDropItemEvent event) {
        if(event.getItemDrop().getThrower().equals(event.getPlayer().getUniqueId())) {
            int amount = event.getItemDrop().getItemStack().getAmount();
            pollution.addPoints(ActionType.GROUND_ITEMS.getPoints() * amount);
        }
    }
}