package BlockBlackList;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import java.util.List;

public class BlockBlackList
extends PluginBase
implements Listener {
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
        this.getConfig();
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
       List<Integer> listedBlocks = this.getConfig().getIntegerList("Blocks");
       for (Integer block : listedBlocks) {
           if (e.getBlock().getId() != block) continue;
           if (e.getPlayer().isOp()) continue;
           e.setCancelled(true);
           this.sendCustomMessage(e.getPlayer());
       }
   }

    public void sendCustomMessage(Player p) {
        p.sendMessage(TextFormat.colorize('&', this.getConfig().getString("Message")));
    }
}
