package BlockBlackList;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class BlockBlackList extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        int block = e.getBlock().getId();
        if (this.getConfig().getIntegerList("Blocks").contains(block)) {
            if (p.hasPermission("bbl.bypass") || p.hasPermission("bbl.bypass.id." + block)) {
                return;
            }

            e.setCancelled(true);
            this.sendCustomMessage(p);
        }
   }

    public void sendCustomMessage(Player p) {
        p.sendMessage(TextFormat.colorize('&', this.getConfig().getString("Message")));
    }
}
