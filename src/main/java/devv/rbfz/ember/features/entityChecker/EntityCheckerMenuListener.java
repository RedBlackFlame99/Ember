package devv.rbfz.ember.features.entityChecker;

import devv.rbfz.ember.EmberPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import static devv.rbfz.ember.utilities.TextUtils.processColorCodes;

public class EntityCheckerMenuListener implements Listener {
    private final EmberPlugin plugin;
    private final Player player;
    private final String id;

    private final String title;

    public EntityCheckerMenuListener(EmberPlugin plugin, Player player, String id) {
        this.plugin = plugin;
        this.player = player;
        this.id = id;

        this.title = processColorCodes("&8EntityChecker ({ID})"
                .replace("{ID}", id));
    }


    @EventHandler
    public void on(InventoryClickEvent event) {

    }

    @EventHandler
    public void on(InventoryCloseEvent event) {

    }
}
