package pl.ecocraft.ecoplugin.command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.ecocraft.ecoplugin.EcoPlugin;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;
import pl.ecocraft.ecoplugin.util.ColorUtil;


public class PollutionCheckCommand implements CommandExecutor {

    private final EcoPlugin plugin;
    private final ServerPollution pollution;

    public PollutionCheckCommand(EcoPlugin plugin, ServerPollution pollution) {
        this.plugin = plugin;
        this.pollution = pollution;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        double result = pollution.getTotalPoints();
        player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_WOLOLO, 2, 1);
        player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("living_conditions").replace("{CONDITIONS}", getConditionsMessage(result))));
        player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("pollution_size").replace("{PERCENTAGE}", String.format("%.2f", result) + "%")));
        return true;
    }

    private String getConditionsMessage(double num) {
        int points = (int)num;
        String result = "";

        if(points <= 9){
            result = plugin.getConfig().getString("conditions_excellent");
        }

        else if(points >= 10 && points < 30) {
            result = plugin.getConfig().getString("conditions_good");
        }

        else if(points >= 30 && points < 50) {
            result = plugin.getConfig().getString("conditions_okay");
        }

        else if(points >= 50 && points < 80) {
            result = plugin.getConfig().getString("conditions_bad");
        }

        else if(points >= 80 && points < 100) {
            result = plugin.getConfig().getString("conditions_terrible");
        }

        else if(points >= 100) {
            result = plugin.getConfig().getString("conditions_horrible");
        }

        return result;
    }
}