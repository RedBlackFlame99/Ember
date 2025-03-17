package devv.rbfz.ember.features.bossBars.timers;

import devv.rbfz.ember.EmberPlugin;
import devv.rbfz.ember.enums.MemoryMetric;
import devv.rbfz.ember.server.ServerStats;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

public class MemoryBarTimer extends BukkitRunnable {
    private final EmberPlugin plugin;
    private final BossBar memoryBar;
    public MemoryBarTimer(EmberPlugin plugin) {
        this.plugin = plugin;
        this.memoryBar = plugin.getBossBars().getMemoryBar();
    }

    private double green_yellow = 0.5;
    private double yellow_red = 0.75;

    @Override
    public void run() {
        if (memoryBar.getPlayers().isEmpty()) {
            return;
        }

        ServerStats stats = plugin.getServerStats();
        long memoryTotal = stats.memory(MemoryMetric.TOTAL);
        long memoryUsed = stats.memory(MemoryMetric.USED);

        memoryBar.setTitle("Memory (" + stats.memoryFormatted(memoryUsed) + "/" + stats.memoryFormatted(memoryTotal) + ")");

        double ratio = (double) memoryUsed/memoryTotal;
        memoryBar.setProgress(ratio);
        if (ratio <= green_yellow) {
            memoryBar.setColor(BarColor.GREEN);
        } else if (ratio > green_yellow && ratio <= yellow_red) {
            memoryBar.setColor(BarColor.YELLOW);
        } else if (ratio > yellow_red) {
            memoryBar.setColor(BarColor.RED);
        }
    }
}
