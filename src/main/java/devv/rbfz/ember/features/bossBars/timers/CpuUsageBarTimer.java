package devv.rbfz.ember.features.bossBars.timers;

import devv.rbfz.ember.EmberPlugin;
import devv.rbfz.ember.server.ServerStats;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

public class CpuUsageBarTimer extends BukkitRunnable {
    private final EmberPlugin plugin;
    private final BossBar cpuUsageBar;
    public CpuUsageBarTimer(EmberPlugin plugin) {
        this.plugin = plugin;
        this.cpuUsageBar = plugin.getBossBars().getCpuUsageBar();
    }

    private double green_yellow = 0.4;
    private double yellow_red = 0.75;

    @Override
    public void run() {
        if (cpuUsageBar.getPlayers().isEmpty()) {
            return;
        }

        ServerStats stats = plugin.getServerStats();
        double cpuUsage = stats.cpuUsage();

        cpuUsageBar.setTitle("CPU Usage (" + stats.cpuUsageFormatted(cpuUsage) + ")");

        cpuUsageBar.setProgress(cpuUsage);
        if (cpuUsage <= green_yellow) {
            cpuUsageBar.setColor(BarColor.GREEN);
        } else if (cpuUsage> green_yellow && cpuUsage <= yellow_red) {
            cpuUsageBar.setColor(BarColor.YELLOW);
        } else if (cpuUsage > yellow_red) {
            cpuUsageBar.setColor(BarColor.RED);
        }
    }
}
