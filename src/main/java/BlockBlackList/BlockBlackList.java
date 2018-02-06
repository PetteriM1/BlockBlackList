package BlockBlackList;

import java.util.List;
import java.lang.Object;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class BlockBlackList
extends PluginBase
implements Listener {
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
        this.getLogger().info(TextFormat.GREEN + "BlockBlackList enabled!");
        this.saveDefaultConfig();
        this.getConfig();
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
       List<Integer> listedBlocks = this.getConfig().getIntegerList("Blocks");
       for (Integer block : listedBlocks) {
       if (e.getBlock().getId() !=block) continue;
       if (e.getPlayer().isOp()) continue;
       e.setCancelled(true);
       this.sendCustomMessage(e.getPlayer());
    }
}

    public void sendCustomMessage(Player p) {
        p.sendMessage(TextFormat.colorize((char)'&', (String)this.getConfig().getString("Message")));
    }

    @Override
    public void onDisable() {
        this.getLogger().info(TextFormat.RED + "BlockBlackList disabled!");
    }
}
