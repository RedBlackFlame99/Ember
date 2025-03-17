package devv.rbfz.ember.commands;

import devv.rbfz.ember.EmberPlugin;
import devv.rbfz.ember.features.bossBars.plugin.EmberBossBars;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EmberCommand implements CommandExecutor {
    private final EmberPlugin plugin;
    public EmberCommand(EmberPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can execute this command!");
            return true;
        }

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("stats")) {
                EmberBossBars emberBars = plugin.getBossBars();
                List<BossBar> bars = emberBars.getBars();

                for (BossBar bar : bars) {
                    if (!bar.getPlayers().contains(player)) {
                        bar.addPlayer(player);
                    } else {
                        bar.removePlayer(player);
                    }
                }

            }
        }

        return false;
    }
}
