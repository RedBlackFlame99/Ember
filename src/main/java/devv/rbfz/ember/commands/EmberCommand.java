package devv.rbfz.ember.commands;

import devv.rbfz.ember.EmberPlugin;
import devv.rbfz.ember.features.bossBars.plugin.EmberBossBars;
import devv.rbfz.ember.features.entityChecker.EntityChecker;
import devv.rbfz.ember.features.entityChecker.EntityCheckerMenuListener;
import devv.rbfz.ember.utilities.CustomID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static devv.rbfz.ember.utilities.TextUtils.processColorCodes;

public class EmberCommand implements CommandExecutor {
    private final EmberPlugin plugin;
    public EmberCommand(EmberPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can execute this command!");
            return true;
        }

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("stats")) {
                EmberBossBars emberBars = plugin.getBossBars();
                List<BossBar> bars = emberBars.getBars();

                for (BossBar bar : bars) {
                    if (!bar.getPlayers().contains(player)) {
                        bar.addPlayer(player);
                    } else {
                        bar.removePlayer(player);
                    }
                }
            } else if (args[0].equalsIgnoreCase("entityCheck")) {
                EntityChecker checker = new EntityChecker(player, plugin);

                int index = 0;
                String id = checker.getID();
                Inventory inv = Bukkit.createInventory(null, 45, processColorCodes("&8Entity Check (" + id + ")"));

                for (Map.Entry key : checker.getSortedMap().entrySet()) {
                    if (index > 44) {
                        break;
                    }
                    Chunk chunk = (Chunk) key.getKey();
                    int entityCount = (int) key.getValue();

                    ItemStack item = new ItemStack(Material.GRASS_BLOCK);
                    ItemMeta meta = item.getItemMeta();
                    Component name = Component.text("(" + entityCount + ")")
                            .color(TextColor.color(235, 64, 52))
                            .decoration(TextDecoration.ITALIC, false)
                            .append(Component.text(" Chunk: " + chunk.getX() + ", " + chunk.getZ() + ", " + chunk.getWorld().getName())
                                    .color(TextColor.color(128, 128, 128))
                                    .decoration(TextDecoration.ITALIC, false));
                    meta.displayName(name);
                    meta.lore(List.of(
                            Component.text("Click to teleport!")
                                    .color(TextColor.color(232, 190, 93))
                                    .decoration(TextDecoration.ITALIC, false)
                            // 235, 64, 52
                    ));
                    item.setItemMeta(meta);
                    inv.setItem(index, item);
                    index++;
                }

                plugin.getServer().getPluginManager().registerEvents(new EntityCheckerMenuListener(plugin, player, id), plugin);
                player.openInventory(inv);
            }
        }

        return false;
    }
}
