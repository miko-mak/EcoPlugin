package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import pl.ecocraft.ecoplugin.listener.ActionType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;


public class ItemPickUpListener implements Listener {

    private final ServerPollution pollution;

    public ItemPickUpListener(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event) {
        if(event.getItem().getThrower() == null){
            return;
        }

        int amount = event.getItem().getItemStack().getAmount();
        pollution.removePoints(ActionType.GROUND_ITEMS.getPoints() * amount);
    }
}