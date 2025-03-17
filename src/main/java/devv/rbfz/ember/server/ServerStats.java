package devv.rbfz.ember.server;

import com.sun.management.OperatingSystemMXBean;
import devv.rbfz.ember.EmberPlugin;
import devv.rbfz.ember.enums.ByteUnit;
import devv.rbfz.ember.enums.MemoryMetric;
import devv.rbfz.ember.enums.TpsMetric;

import java.lang.management.ManagementFactory;

public class ServerStats {

    private final EmberPlugin plugin;

    public ServerStats(EmberPlugin plugin) {
        this.plugin = plugin;
    }

    public long memory(MemoryMetric metric) {
        long value = -1;
        if (metric == MemoryMetric.TOTAL) {
            value = Runtime.getRuntime().totalMemory();
        } else if (metric == MemoryMetric.FREE) {
            value = Runtime.getRuntime().freeMemory();
        } else if (metric == MemoryMetric.MAX) {
            value = Runtime.getRuntime().maxMemory();
        } else if (metric == MemoryMetric.USED) {
            value = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        }
        return value;
    }

    public String memoryFormatted(long memory) {
        ByteUnit unit = ByteUnit.MEGABYTES;
        long result = unit.convert(memory, unit);
        return (result + unit.getSuffix());
    }

    public double cpuUsage() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getProcessCpuLoad(); // Returns CPU load as a value between 0.0 and 1.0
    }

    public String cpuUsageFormatted(double cpuUsage) {
        double usage = (double) Math.round(cpuUsage * 1000) / 10;
        return usage + "%";
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
