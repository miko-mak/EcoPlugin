package pl.ecocraft.ecoplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.ecocraft.ecoplugin.EcoPlugin;
import pl.ecocraft.ecoplugin.util.ColorUtil;
import pl.ecocraft.ecoplugin.util.SoundUtil;


public class PutMaskCommand implements CommandExecutor {

    private final EcoPlugin plugin;
    private final SoundUtil sound;

    public PutMaskCommand(EcoPlugin plugin, SoundUtil sound) {
        this.plugin = plugin;
        this.sound = sound;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInHand();

        if(player.getInventory().getHelmet() != null){
            player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("helmet_take_off")));
            return false;
        }

        String itemType = item.getType().toString();
        if (!itemType.contains("GLASS") || itemType.contains("PANE") || itemType.contains("BOTTLE") || itemType.contains("SPY")) {
            player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("invalid_item")));
            return false;
        }

        if(item.getAmount() == 1) {
            player.setItemInHand(null);
        }

        if(item.getAmount() > 1){
            item.setAmount(item.getAmount()-1);
        }

        ItemStack helmet = item.clone();
        helmet.setAmount(1);
        player.getEquipment().setHelmet(helmet);
        sound.playSound(player, "mask_on");
        player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("mask_on")));
        return true;
    }
}