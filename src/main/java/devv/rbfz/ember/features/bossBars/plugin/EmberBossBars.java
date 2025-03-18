package devv.rbfz.ember.features.bossBars.plugin;

import devv.rbfz.ember.EmberPlugin;
import devv.rbfz.ember.features.bossBars.listeners.BarListeners;
import devv.rbfz.ember.features.bossBars.timers.CpuUsageBarTimer;
import devv.rbfz.ember.features.bossBars.timers.MemoryBarTimer;
import devv.rbfz.ember.features.bossBars.timers.TpsBarTimer;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.List;

public class EmberBossBars {
    private final EmberPlugin plugin;

    private final List<BossBar> bars;

    private final BossBar memoryBar;
    private final BossBar cpuUsageBar;
    private final BossBar tpsBar;

    public EmberBossBars(EmberPlugin plugin) {
        this.plugin = plugin;

        this.memoryBar = plugin.getServer().createBossBar("Loading...", BarColor.WHITE, BarStyle.SEGMENTED_6);
        this.cpuUsageBar = plugin.getServer().createBossBar("Loading...", BarColor.WHITE, BarStyle.SEGMENTED_6);
        this.tpsBar = plugin.getServer().createBossBar("Loading...", BarColor.WHITE, BarStyle.SEGMENTED_6);


        this.bars = List.of(
                memoryBar,
                cpuUsageBar,
                tpsBar
        );
    }

    public void load() {
        PluginManager p = plugin.getServer().getPluginManager();
        p.registerEvents(new BarListeners(plugin), plugin);


        new MemoryBarTimer(plugin).runTaskTimer(plugin, 0, 20);
        new CpuUsageBarTimer(plugin).runTaskTimer(plugin, 0, 20);
        new TpsBarTimer(plugin).runTaskTimer(plugin, 0, 20);
    }

    // ONLY USE THIS WHEN SERVER SHUTDOWN
    public void clearBars() {
        for (BossBar bar : bars) {
            for (Player player : bar.getPlayers()) {
                bar.removePlayer(player);
            }
        }
    }

    public List<BossBar> getBars() {
        return bars;
    }

    public BossBar getMemoryBar() {
        return this.memoryBar;
    }
    public BossBar getCpuUsageBar() {
        return this.cpuUsageBar;
    }
    public BossBar getTpsBar() {
        return this.tpsBar;
    }
}