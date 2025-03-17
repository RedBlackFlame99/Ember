package devv.rbfz.ember;

import devv.rbfz.ember.server.ServerStats;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class EmberPlugin extends JavaPlugin {

    private ServerStats stats;

    @Override
    public void onEnable() {
        stats = new ServerStats(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ServerStats getServerStats() {
        return stats;
    }
}
