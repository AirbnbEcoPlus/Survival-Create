package fr.survivalmode.commands;



import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.survivalmode.Main;

public class SmInviteMenu implements CommandExecutor {

	private Main main;
	public SmInviteMenu(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		String namePlayerSend = player.getName();
		 if(!cmd.getName().equalsIgnoreCase("scinvite")) return false;
		    if(args.length == 0) { 
		    	Inventory invinv = Bukkit.createInventory(null, 9, "§8SurvivalCreateInvite");
				
				ItemStack invite = new ItemStack(Material.BED, 1);
				ItemMeta inviteM = invite.getItemMeta();
				inviteM.setDisplayName("Inviter un ami");
				invite.setItemMeta(inviteM);
				
				ItemStack publicworld = new ItemStack(Material.COMPASS, 1);
				ItemMeta publicworldM = publicworld.getItemMeta();
				publicworldM.setDisplayName("Rejoindre un Monde en Public");
				publicworld.setItemMeta(publicworldM);
				
				ItemStack switchworld = new ItemStack(Material.EMERALD, 1);
				ItemMeta switchworldM = switchworld.getItemMeta();
				switchworldM.setDisplayName("Mettre votre monde en public");
				switchworld.setItemMeta(switchworldM);
				
				invinv.setItem(2, invite);
				invinv.setItem(4, publicworld);
				invinv.setItem(6, switchworld);
				player.openInventory(invinv);
		    } else if(args.length == 1) {
		        String playerName = args[0];
		        Player target = Bukkit.getPlayer(playerName);
		        if(target == null || !target.isOnline()) {
		            sender.sendMessage("'"+playerName+"' est Hors Ligne!");
		        }
		        if (target.getWorld().getName().equalsIgnoreCase("world")) {
		        	Inventory invinvaccept = Bukkit.createInventory(null, 9, "§8SurvivalCreateInviteDemande");
					
					ItemStack accept = new ItemStack(Material.EMERALD_BLOCK, 1);
					ItemMeta acceptM = accept.getItemMeta();
					acceptM.setDisplayName("Accepter sa demande d'invitation");
					accept.setItemMeta(acceptM);
					
					ItemStack refuse = new ItemStack(Material.REDSTONE_BLOCK, 1);
					ItemMeta refuseM = refuse.getItemMeta();
					refuseM.setDisplayName("Refuser sa demande d'invitation");
					refuse.setItemMeta(refuseM);
					
					
					invinvaccept.setItem(2, accept);
					invinvaccept.setItem(6, refuse);
					main.PlayerTp.put("target", namePlayerSend);
					target.openInventory(invinvaccept);
		        }else 
		        	player.sendMessage("§2Ce joueur n'est pas present dans le spawn du [GAMEMODE]");
              
		        
		    } else if(args.length == 2) {
		        sender.sendMessage("Vous devez faire /sc invite (nom de la personne)");
	
		
		return false;
	}
			return false;
	}

			
	


	


	


}
