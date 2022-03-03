package fr.survivalmode;




import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import fr.survivalmode.commands.SmCreate;

import fr.survivalmode.commands.SmInviteMenu;
import fr.survivalmode.commands.scdefspawn;
import fr.survivalmode.world.World;
import fr.survivalmode.world.WorldManager;

import java.util.HashMap;

public class Main extends JavaPlugin {
	public HashMap<String, String> PlayerTp = new HashMap<String, String>();
	public WorldManager worldManager = new WorldManager(this);
	public boolean VoidGeneratorEnable = false;
	@Override
	public void onEnable() {
		saveDefaultConfig();
		System.out.println("Merci d'utiliser Survival Create");
		getCommand("sc").setExecutor(new SmCreate(this));
		getServer().getPluginManager().registerEvents(new scListen(this), this);
		getServer().getPluginManager().registerEvents(new scShare(this), this);
		getServer().getPluginManager().registerEvents(new scListenCreate(this), this);
		getServer().getPluginManager().registerEvents(new scListenBorder(this), this);
		getServer().getPluginManager().registerEvents(new scListenInvite(this), this);
		getServer().getPluginManager().registerEvents(new scListenManager(this), this);
		getCommand("scinvite").setExecutor(new SmInviteMenu(this));
		getCommand("scspawn").setExecutor(new scdefspawn(this));
		if(getServer().getPluginManager().getPlugin("VoidGen") != null) {
			VoidGeneratorEnable = true;
		}
		
  }
	public void GetWorldList(){
		worldManager.delWorld();
	    ConfigurationSection arenaSection = getConfig().getConfigurationSection("arenas");
	    for(String string : arenaSection.getKeys(false)) {
	    	String worldName = arenaSection.getString(string + ".worldName");
	    	String world = arenaSection.getString(string + ".world");
	    	String playerhost = arenaSection.getString(string + ".playerhost");
	    	int maxplayer = arenaSection.getInt(string + ".maxplayer");
	    	Double posX = arenaSection.getDouble(string + ".posX");
	    	Double posY = arenaSection.getDouble(string + ".posY");
	    	Double posZ = arenaSection.getDouble(string + ".posZ");
	    	boolean isLoaded = arenaSection.getBoolean(string + ".isLoaded");
	    	Location locspawn = new Location(Bukkit.getWorld(world), posX,posY,posZ);
	    	
	    	World worlds = new World(locspawn, maxplayer, playerhost, worldName, isLoaded);
	    	worldManager.addWorld(worlds);
	    }
	}
	public WorldManager getArenaManager() {
		return worldManager;
	}
}