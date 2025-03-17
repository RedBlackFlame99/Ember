package devv.rbfz.ember;

import devv.rbfz.ember.commands.EmberCommand;
import devv.rbfz.ember.server.ServerStats;
import devv.rbfz.ember.features.bossBars.plugin.EmberBossBars;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class EmberPlugin extends JavaPlugin {

    private ServerStats stats;
    private EmberBossBars bossBars;

    @Override
    public void onEnable() {
        this.stats = new ServerStats(this);
        this.bossBars = new EmberBossBars(this);

        PluginCommand emberCommand = getCommand("ember");
        if (emberCommand != null) {
            emberCommand.setExecutor(new EmberCommand(this));
        }

        this.bossBars.load();

    }

    @Override
    public void onDisable() {
        this.bossBars.clearBars();
    }

    public ServerStats getServerStats() {
        return this.stats;
    }
    public EmberBossBars getBossBars() {
        return this.bossBars;
    }
}
