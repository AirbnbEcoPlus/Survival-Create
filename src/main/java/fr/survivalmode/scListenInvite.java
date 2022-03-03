package fr.survivalmode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class scListenInvite implements Listener {
    private Main main;
    public scListenInvite(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        String name = player.getName();
        ItemStack current = event.getCurrentItem();
        if (current == null) return;
        //Ouvre le menu Invite
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
            if(current.getType() == Material.JACK_O_LANTERN) {
                event.setCancelled(true);
                player.closeInventory();
                Inventory invinv = Bukkit.createInventory(null, 9, "§8SurvivalCreateInvite");

                ItemStack invite = new ItemStack(Material.BED, 1);
                ItemMeta inviteM = invite.getItemMeta();
                inviteM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.invitefriend"));
                invite.setItemMeta(inviteM);

                ItemStack publicworld = new ItemStack(Material.COMPASS, 1);
                ItemMeta publicworldM = publicworld.getItemMeta();
                publicworldM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.joinpublicworld"));
                publicworld.setItemMeta(publicworldM);

                if(!main.worldManager.ifWorldIsPublic(player) == true) {
                    ItemStack switchworld = new ItemStack(Material.EMERALD, 1);
                    ItemMeta switchworldM = switchworld.getItemMeta();
                    switchworldM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.setpublicworld"));
                    switchworld.setItemMeta(switchworldM);
                    invinv.setItem(6, switchworld);
                }else {
                    ItemStack switchworld = new ItemStack(Material.REDSTONE_BLOCK, 1);
                    ItemMeta switchworldM = switchworld.getItemMeta();
                    switchworldM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.setunpublicworld"));
                    switchworld.setItemMeta(switchworldM);
                    invinv.setItem(6, switchworld);
                }
                invinv.setItem(2, invite);
                invinv.setItem(4, publicworld);
                player.openInventory(invinv);
            }
        }
        //Tp le joueur
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateInviteDemande")) {
            if(current.getType() == Material.EMERALD_BLOCK) {
                event.setCancelled(true);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + name + " " + main.PlayerTp.get("target"));
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.tpaccept"));
                player.closeInventory();
            }
        }
        //Afficher les mondes en public
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateInvite")) {
            if(current.getType() == Material.COMPASS) {
                event.setCancelled(true);
                player.closeInventory();
                main.getArenaManager().inventoryPublic(player);
            }
        }
        //Rejoindre un monde en public
        if(inv.getName().equalsIgnoreCase("§2SurvivalCreateWorlds")) {
            if(current.getType() == Material.GRASS) {
                event.setCancelled(true);
                String worldName = current.getItemMeta().getDisplayName();
                Location location = main.getArenaManager().getWorldByPlayerHost(worldName);
                player.teleport(location);
            }
        }
        //Afficher les informations pour inviter des amis
        if(inv.getName().equalsIgnoreCase("§8SurvivalCreateInvite")) {
            if(current.getType() == Material.BED) {
                event.setCancelled(true);
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.invitefriendhelp"));
            }
        }
    }
}
