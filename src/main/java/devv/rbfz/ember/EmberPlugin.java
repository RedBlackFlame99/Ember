package devv.rbfz.ember;

import devv.rbfz.ember.commands.EmberCommand;
import devv.rbfz.ember.server.ServerConfiguration;
import devv.rbfz.ember.server.ServerStats;
import devv.rbfz.ember.features.bossBars.plugin.EmberBossBars;
import devv.rbfz.ember.utilities.MapUtils;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class EmberPlugin extends JavaPlugin {

    private ServerConfiguration config;
    private ServerStats stats;
    private EmberBossBars bossBars;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.config = new ServerConfiguration(this);
        this.stats = new ServerStats(this);
        this.bossBars = new EmberBossBars(this);

        PluginCommand emberCommand = getCommand("ember");
        if (emberCommand != null) {
            emberCommand.setExecutor(new EmberCommand(this));
        }

        this.bossBars.load();
        /*
        Map<Object, Integer> mapTest = new HashMap<>();
        mapTest.put("Sigma", 1);
        mapTest.put(5, 0);
        mapTest.put("Aura", -1);
        getLogger().severe(String.valueOf(MapUtils.sort(mapTest)));
        */

    }

    @Override
    public void onDisable() {
        this.bossBars.clearBars();
    }

    public ServerConfiguration config() {
        return this.config;
    }
    public ServerStats getServerStats() {
        return this.stats;
    }
    public EmberBossBars getBossBars() {
        return this.bossBars;
    }
}
