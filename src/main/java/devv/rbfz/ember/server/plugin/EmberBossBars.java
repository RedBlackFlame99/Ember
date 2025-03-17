package devv.rbfz.ember.server.plugin;

import devv.rbfz.ember.EmberPlugin;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

public class EmberBossBars {
    private final EmberPlugin plugin;

    private final BossBar memoryBar;

    public EmberBossBars(EmberPlugin plugin) {
        this.plugin = plugin;
        memoryBar = plugin.getServer().createBossBar("Loading...", BarColor.WHITE, BarStyle.SEGMENTED_6);
    }

    public BossBar getMemoryBar() {
        return this.memoryBar;
    }
}