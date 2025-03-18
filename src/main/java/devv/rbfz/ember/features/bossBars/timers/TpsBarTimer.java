package devv.rbfz.ember.features.bossBars.timers;

import devv.rbfz.ember.EmberPlugin;
import devv.rbfz.ember.enums.TpsMetric;
import devv.rbfz.ember.server.ServerStats;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

public class TpsBarTimer extends BukkitRunnable {
    private final EmberPlugin plugin;
    private final BossBar tpsBar;
    public TpsBarTimer(EmberPlugin plugin) {
        this.plugin = plugin;
        this.tpsBar = plugin.getBossBars().getTpsBar();
    }

    private double green_yellow = 0.75;
    private double yellow_red = 0.6;

    @Override
    public void run() {
        if (tpsBar.getPlayers().isEmpty()) {
            return;
        }

        ServerStats stats = plugin.getServerStats();
        double tps = stats.tps(TpsMetric.ONE_MINUTE);
        double roundedTps = Math.round(tps * 10.0) / 10.0;
        double progress = Math.min(1.0, tps / 20.0);

        tpsBar.setTitle("TPS (" + roundedTps + ")");

        tpsBar.setProgress(progress);
        if (progress >= green_yellow) {
            tpsBar.setColor(BarColor.GREEN);
        } else if (progress < green_yellow && progress >= yellow_red) {
            tpsBar.setColor(BarColor.YELLOW);
        } else if (progress < yellow_red) {
            tpsBar.setColor(BarColor.RED);
        }
    }
}
