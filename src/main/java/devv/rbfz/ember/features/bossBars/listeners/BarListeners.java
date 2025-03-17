package devv.rbfz.ember.features.bossBars.listeners;

import devv.rbfz.ember.EmberPlugin;
import devv.rbfz.ember.features.bossBars.plugin.EmberBossBars;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class BarListeners implements Listener {

    private final EmberPlugin plugin;

    public BarListeners(EmberPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        EmberBossBars emberBars = plugin.getBossBars();
        List<BossBar> bars = emberBars.getBars();
        for (BossBar bar : bars) {
            if (bar.getPlayers().contains(player)) {
                bar.removePlayer(player);
            }
        }
    }
}
