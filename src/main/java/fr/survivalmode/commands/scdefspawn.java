package fr.survivalmode.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.survivalmode.Main;

public class scdefspawn implements CommandExecutor {

	private Main main;
	public scdefspawn(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		String name = player.getName();
	    if(player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
	    	Location locspawn = player.getLocation();
	    	double getY = locspawn.getY();
	    	double getX = locspawn.getX();
	    	double getZ = locspawn.getZ();
	    	main.getConfig().set("arenas." + name + "SurvivalCreateWorld.posY", getY);
	    	main.getConfig().set("arenas." + name + "SurvivalCreateWorld.posX", getX);
	    	main.getConfig().set("arenas." + name + "SurvivalCreateWorld.posZ", getZ);
	    	main.saveConfig();
	    	player.sendMessage(main.getConfig().getString("message.menu.answerResponse.scspawnsucces"));
	    }else {
	    	player.sendMessage(ChatColor.RED + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
	    }
	    	
		return false;
		
	}
	

}
