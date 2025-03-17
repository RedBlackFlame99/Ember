package devv.rbfz.ember.server;

import com.sun.management.OperatingSystemMXBean;
import devv.rbfz.ember.EmberPlugin;
import devv.rbfz.ember.enums.MemoryMetric;
import devv.rbfz.ember.enums.TpsMetric;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import java.lang.management.ManagementFactory;
import java.util.Arrays;

public class ServerStats {

    private final EmberPlugin plugin;

    public ServerStats(EmberPlugin plugin) {
        this.plugin = plugin;
    }

    public double memory(MemoryMetric metric) {
        double value = -1;
        if (metric == MemoryMetric.TOTAL) {
            value = Runtime.getRuntime().totalMemory();
        } else if (metric == MemoryMetric.FREE) {
            value = Runtime.getRuntime().freeMemory();
        } else if (metric == MemoryMetric.MAX) {
            value = Runtime.getRuntime().maxMemory();
        } else if (metric == MemoryMetric.USED) {
            value = Runtime.getRuntime().freeMemory() - Runtime.getRuntime().freeMemory();
        }
        return value;
    }

    public double cpuUsage() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getProcessCpuLoad(); // Returns CPU load as a value between 0.0 and 1.0
    }

    public double tps(TpsMetric metric) {
        double tps;
        double[] tpsArray = plugin.getServer().getTPS();
        if (metric == TpsMetric.ONE_MINUTE) {
            tps = tpsArray[0];
        } else if (metric == TpsMetric.FIVE_MINUTES) {
            tps = tpsArray[1];
        } else if (metric == TpsMetric.FIFTEEN_MINUTES) {
            tps = tpsArray[2];
        } else {
            tps = -1;
            plugin.getLogger().severe("Error fetching tps!");
        }
        return tps;
    }

}
