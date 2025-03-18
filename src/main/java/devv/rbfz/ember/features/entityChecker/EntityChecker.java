package devv.rbfz.ember.features.entityChecker;

import devv.rbfz.ember.EmberPlugin;
import devv.rbfz.ember.utilities.CustomID;
import devv.rbfz.ember.utilities.MapUtils;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityChecker {
    private final Player player;
    private final EmberPlugin plugin;
    private final String id;

    private final List<EntityType> blacklisted = List.of(
            EntityType.PLAYER,
            EntityType.BLOCK_DISPLAY,
            EntityType.ITEM_DISPLAY,
            EntityType.TEXT_DISPLAY,
            EntityType.ITEM_FRAME,
            EntityType.GLOW_ITEM_FRAME,
            EntityType.ITEM
    );

    private final Map<Chunk, Integer> sortedMap;

    public EntityChecker(Player player, EmberPlugin plugin) {
        this.player = player;
        this.plugin = plugin;
        this.id = CustomID.generate();

        Map<Chunk, Integer> map = new HashMap<>();

        for (World world : plugin.getServer().getWorlds()) {
            for (Chunk chunk : world.getLoadedChunks()) {
                int entityCount = (int) Arrays.stream(chunk.getEntities())
                        .filter(entity -> !blacklisted.contains(entity.getType()))
                        .count();
                if (entityCount > 0) {
                    map.put(chunk, entityCount);
                }
            }
        }

        this.sortedMap = MapUtils.sort(map);

    }

    public Map<Chunk, Integer> getSortedMap() {
        return this.sortedMap;
    }
    public String getID() {
        return this.id;
    }
}
