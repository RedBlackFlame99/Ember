package devv.rbfz.ember.server;

import devv.rbfz.ember.EmberPlugin;
import org.bukkit.configuration.Configuration;

import java.io.ObjectInputFilter;

public class ServerConfiguration {
    private final EmberPlugin plugin;

    private final Configuration config;

    public ServerConfiguration(EmberPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public Configuration getConfig() {
        return config;
    }

    public Object getvalue(String key, Object orElse) {
        return config.get(key, orElse);
    }
    public Object getValue(String key) {
        return config.get(key, null);
    }

    public void setValue(String key, Object value) {
        config.set(key, value);
    }
}
