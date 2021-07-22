package fr.survivalmode;




import org.bukkit.plugin.java.JavaPlugin;

import fr.survivalmode.commands.SmCreate;

import fr.survivalmode.commands.SmInviteMenu;
import java.util.HashMap;

public class Main extends JavaPlugin {
	public HashMap<String, String> PlayerTp = new HashMap<String, String>();
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		System.out.println("Le plugin est en train de se charger...");
		
		getCommand("sc").setExecutor(new SmCreate());
		getServer().getPluginManager().registerEvents(new sclisten(this), this);
		getCommand("scinvite").setExecutor(new SmInviteMenu(this));
		
  }
}