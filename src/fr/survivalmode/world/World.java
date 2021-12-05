package fr.survivalmode.world;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;


public class World {
		
		
		private Location locspawn;
		private List<Player> players;
		private String worldName;
		private String playerhost;
		private boolean isLoaded;
		public World(Location locspawn, int maxplayer,String playerhost, String worldName, boolean isLoaded) {
			this.locspawn = locspawn;
			this.players = new ArrayList<>();
			this.playerhost = playerhost;
			this.worldName= worldName;
			this.isLoaded = isLoaded;
		}
		public Location getLocSpawn() {
			return locspawn;
		}
		public List<Player> getPlayer() {
			return players;
		}
		
		public String getPlayerHost() {
			return playerhost;
		}
		public String getWorldName() {
			return worldName;
		}
		public boolean getWorldIsLoaded(){
			return isLoaded;
		}
		
}


