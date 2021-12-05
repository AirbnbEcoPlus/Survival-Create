package fr.survivalmode;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;




public class sclisten implements Listener { 
	 
		        

	        	
	        	
	        
	        
			
			
	   
	
	private Main main;
	
	   public sclisten(Main main) {
		   this.main = main;
	}
	@EventHandler
	    public void onQuit(PlayerQuitEvent event) {
	        Player player = event.getPlayer();
	        String name = player.getName();
	        if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
	        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_nether");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_the_end");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld_nether");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld_the_end");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
			main.getConfig().set("arenas." + name + "SurvivalCreateWorld.isLoaded", false);
			main.saveConfig();
	   }
	        }
	   @EventHandler
	   public void onClick(InventoryClickEvent event) {
			Inventory inv = event.getInventory();
			Player player = (Player) event.getWhoClicked();
			String name = player.getName();
			ItemStack current = event.getCurrentItem();		
			if(current == null) return;
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.GRASS) {
					event.setCancelled(true);
					player.closeInventory();
					
					Inventory invseed = Bukkit.createInventory(null, 27, "§8SurvivalCreateSeed");
					ItemStack seednormal = new ItemStack(Material.DIRT, 1);
					ItemMeta seednormalM = seednormal.getItemMeta();
					seednormalM.setDisplayName("Monde normal");
					seednormal.setItemMeta(seednormalM);
					
					
					ItemStack seedFlat = new ItemStack(Material.WOOD_PLATE, 1);
					ItemMeta seedFlatM = seedFlat.getItemMeta();
					seedFlatM.setDisplayName("Monde Plat");
					seedFlat.setItemMeta(seedFlatM);
					
					ItemStack minigames = new ItemStack(Material.STICK, 1);
					ItemMeta minigamesM = seedFlat.getItemMeta();
					minigamesM.setDisplayName("Graine Mini-Jeux");
					minigames.setItemMeta(minigamesM);
					
					if(main.VoidGeneratorEnable == true) {
						ItemStack seedVoid = new ItemStack(Material.BEDROCK, 1);
						ItemMeta seedVoidM = seedVoid.getItemMeta();
						seedVoidM.setDisplayName("Monde Vide");
						seedVoid.setItemMeta(seedVoidM);
						
						invseed.setItem(11, seednormal);
						invseed.setItem(13, seedFlat);
						invseed.setItem(15, seedVoid);
						player.openInventory(invseed);
					}
					
					
					invseed.setItem(11, seednormal);
					invseed.setItem(13, seedFlat);
					invseed.setItem(24, minigames);
					
					player.openInventory(invseed);

				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateSeed")) {
				if(current.getType() == Material.WOOD_PLATE) {
					event.setCancelled(true);
					player.closeInventory();
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loading"));
					WorldCreator wc_flat = new WorldCreator(name + "SurvivalCreateWorld");
					wc_flat.environment(World.Environment.NORMAL);
					wc_flat.type(WorldType.FLAT);
					wc_flat.createWorld();	
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
					
					

					
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loadingsucces"));
					
				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateSeed")) {
				if(current.getType() == Material.WOOD_PLATE) {
					event.setCancelled(true);
					player.closeInventory();
					
				}
			}
			
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateSeed")) {
				if(current.getType() == Material.DIRT) {
					event.setCancelled(true);
					player.closeInventory();
					
						player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loading"));
						player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.step1"));
						WorldCreator wc = new WorldCreator(name + "SurvivalCreateWorld");
						wc.environment(World.Environment.NORMAL);
						wc.type(WorldType.NORMAL);
						wc.createWorld();
	                    World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
						WorldBorder border = world.getWorldBorder();
						border.setSize(10000.0);
						border.setCenter(0.0, 0.0);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
						player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.step2"));
						WorldCreator wc_nether = new WorldCreator(name + "SurvivalCreateWorld_nether");
						wc_nether.environment(World.Environment.NETHER);
						wc_nether.type(WorldType.NORMAL);
						wc_nether.createWorld();
	                    World world_nether = Bukkit.getWorld(name + "SurvivalCreateWorld_nether");
						WorldBorder border_nether = world_nether.getWorldBorder();
						border_nether.setSize(10000.0);
						border_nether.setCenter(0.0, 0.0);
						player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.step3"));
						WorldCreator wc_the_end = new WorldCreator(name + "SurvivalCreateWorld_the_end");
						wc_the_end.environment(World.Environment.THE_END);
						wc_the_end.type(WorldType.NORMAL);
						wc_the_end.createWorld();
	                    World world_the_end = Bukkit.getWorld(name + "SurvivalCreateWorld_the_end");
						WorldBorder border_the_end = world_the_end.getWorldBorder();
						border_the_end.setSize(10000.0);
						border_the_end.setCenter(0.0, 0.0);
						player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loadingsucces"));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world inventory merge " + name + "SurvivalCreateWorld " + name + "SurvivalCreateWorld_nether " + name + "SurvivalCreateWorld_the_end");
					
						
					

					
				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateSeed")) {
				if(current.getType() == Material.BEDROCK) {
					event.setCancelled(true);
					player.closeInventory();
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loading"));
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world create " + name + "SurvivalCreateWorld:VoidGenerator");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.loadingsucces"));
				}
			}
			
			
			

			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateInviteDemande")) {
				if(current.getType() == Material.EMERALD_BLOCK) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + name + " " + main.PlayerTp.get("target"));
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.tpaccept"));
					player.closeInventory();	
	}
}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateTime")) {
				if(current.getType() == Material.PUMPKIN) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 World worldtime = Bukkit.getWorld(name + "SurvivalCreateWorld");
						 worldtime.setTime(12000);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.setnight"));
					 }else {
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
					 }
				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateTime")) {
				if(current.getType() == Material.JACK_O_LANTERN) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 World worldtime = Bukkit.getWorld(name + "SurvivalCreateWorld");
						 worldtime.setTime(0);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.setday"));
					 }else {
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
					 }
				}
			}
			
				
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.ENDER_PEARL) {
					if (!player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
					event.setCancelled(true);
					player.closeInventory();
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld_nether");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld_the_end");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world tp " + name + " " + name + "SurvivalCreateWorld " );
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.tp"));
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.help"));
					main.getConfig().set("arenas." + name + "SurvivalCreateWorld.isLoaded", true);
					main.saveConfig();
					}else {
						player.sendMessage(ChatColor.GREEN+ "Vous etes deja dans votre monde");
					}
			
		
   			}
  }
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.BED) {
					event.setCancelled(true);
					player.closeInventory();
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_nether");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_the_end");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld_nether");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld_the_end");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world unload " + name + "SurvivalCreateWorld");
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.quitworld"));
   			}
  }
			
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.COMPASS) {
					event.setCancelled(true);
					player.closeInventory();
					Inventory invGamemode = Bukkit.createInventory(null, 9, "§8SurvivalCreateGamemode");
					ItemStack survival = new ItemStack(Material.CHEST, 1);
					ItemMeta survivalM = survival.getItemMeta();
					survivalM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.survival"));
					survival.setItemMeta(survivalM);
					
					ItemStack creative = new ItemStack(Material.BRICK, 1);
					ItemMeta creativeM = creative.getItemMeta();
					creativeM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.creative"));
					creative.setItemMeta(creativeM);
					
					ItemStack spectator = new ItemStack(Material.EYE_OF_ENDER, 1);
					ItemMeta spectatorM = spectator.getItemMeta();
					spectatorM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.spectator"));
					spectator.setItemMeta(spectatorM);
					
					ItemStack adventure = new ItemStack(Material.BOOKSHELF, 1);
					ItemMeta adventureM = adventure.getItemMeta();
					adventureM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.adventure"));
					adventure.setItemMeta(adventureM);
					
					invGamemode.setItem(1, survival);
					invGamemode.setItem(3, creative);
					invGamemode.setItem(5, spectator);
					invGamemode.setItem(7, adventure);
					player.openInventory(invGamemode);
				}
		}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
			if(current.getType() == Material.WATCH) {
				event.setCancelled(true);
				player.closeInventory();
				
				Inventory invtime = Bukkit.createInventory(null, 27, "§8SurvivalCreateTime");
				ItemStack day = new ItemStack(Material.JACK_O_LANTERN, 1);
				ItemMeta dayM = day.getItemMeta();
				dayM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.day"));
				day.setItemMeta(dayM);
				
				
				ItemStack night = new ItemStack(Material.PUMPKIN, 1);
				ItemMeta nightM = night.getItemMeta();
				nightM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.night"));
				night.setItemMeta(nightM);
				
				
				
				invtime.setItem(12, day);
				invtime.setItem(14, night);
				player.openInventory(invtime);

			}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.DIAMOND_CHESTPLATE) {
					event.setCancelled(true);
					player.closeInventory();
					
					Inventory invdifi = Bukkit.createInventory(null, 27, "§8SurvivalCreateDifficulty");
					ItemStack peace = new ItemStack(Material.IRON_BLOCK, 1);
					ItemMeta peaceM = peace.getItemMeta();
					peaceM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.peacefull"));
					peace.setItemMeta(peaceM);
					
					
					ItemStack facile = new ItemStack(Material.GOLD_BLOCK, 1);
					ItemMeta facileM = facile.getItemMeta();
					facileM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.easy"));
					facile.setItemMeta(facileM);
					
					ItemStack normal = new ItemStack(Material.DIAMOND_BLOCK, 1);
					ItemMeta normalM = normal.getItemMeta();
					normalM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.normal"));
					normal.setItemMeta(normalM);
					
					ItemStack hard = new ItemStack(Material.EMERALD_BLOCK, 1);
					ItemMeta hardM = hard.getItemMeta();
					hardM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.hard"));
					hard.setItemMeta(hardM);
					
					invdifi.setItem(10, peace);
					invdifi.setItem(12, facile);
					invdifi.setItem(14, normal);
					invdifi.setItem(16, hard);
					player.openInventory(invdifi);
				}
			}
			
			
			
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
				if(current.getType() == Material.CHEST) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 player.setGameMode(GameMode.SURVIVAL);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.survival"));
					 }else
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
				
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
						 player.setGameMode(GameMode.SURVIVAL);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.survival"));
					 }
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
						 player.setGameMode(GameMode.SURVIVAL);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.survival"));
					 }
				}
				
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
				if(current.getType() == Material.BRICK) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 player.setGameMode(GameMode.CREATIVE);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.creative"));
					 }else {
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
					 }
						
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
						 player.setGameMode(GameMode.CREATIVE);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.creative"));
					 }
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
						 player.setGameMode(GameMode.CREATIVE);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.creative"));
					 }
				}
				
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
				if(current.getType() == Material.EYE_OF_ENDER) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 player.setGameMode(GameMode.SPECTATOR);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.spectator"));
					 }else {
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
					 }
						
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
						 player.setGameMode(GameMode.SPECTATOR);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.spectator"));
					 }
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
						 player.setGameMode(GameMode.SPECTATOR);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.spectator"));
					 }
				}
				
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateGamemode")) {
				if(current.getType() == Material.BOOKSHELF) {
					event.setCancelled(true);
					player.closeInventory();
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						 player.setGameMode(GameMode.ADVENTURE);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.adventure"));
					 }else {
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
					 }
						
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_nether")) {
						 player.setGameMode(GameMode.ADVENTURE);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.adventure"));
					 }
					 if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld_the_end")) {
						 player.setGameMode(GameMode.ADVENTURE);
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.adventure"));
					 }
				}
				
			}
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
					
					ItemStack switchworld = new ItemStack(Material.EMERALD, 1);
					ItemMeta switchworldM = switchworld.getItemMeta();
					switchworldM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.setpublicworld"));
					switchworld.setItemMeta(switchworldM);
					
					invinv.setItem(2, invite);
					invinv.setItem(4, publicworld);
					invinv.setItem(6, switchworld);
					player.openInventory(invinv);
					
					
				}
			}
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateInvite")) {
				if(current.getType() == Material.BED) {
					event.setCancelled(true);
					player.closeInventory();
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.invitefriendhelp"));
   			}
  }
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateInvite")) {
				if(current.getType() == Material.COMPASS) {
					event.setCancelled(true);
					player.closeInventory();
					main.getArenaManager().inventoryPublic(player);
					
					
   			}
  }
			if(inv.getName().equalsIgnoreCase("§2SurvivalCreateWorlds")) {
				if(current.getType() == Material.GRASS) {
					event.setCancelled(true);
					String worldName = current.getItemMeta().getDisplayName();
					Location location = main.getArenaManager().getWorldByPlayerHost(worldName);
					player.teleport(location);

				}
			}
			
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
				if(current.getType() == Material.IRON_BLOCK) {
					player.closeInventory();
					if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						event.setCancelled(true);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world difficulty peaceful " + name + "SurvivalCreateWorld");
						player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.difficultychange"));
					}else {
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
					 }
				}
	  }  
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
				if(current.getType() == Material.GOLD_BLOCK) {
					player.closeInventory();
					if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						event.setCancelled(true);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world difficulty easy " + name + "SurvivalCreateWorld");
						player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.difficultychange"));
					} else {
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
					 }
				}
	 }
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
				if(current.getType() == Material.DIAMOND_BLOCK) {
					player.closeInventory();
					if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						event.setCancelled(true);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world difficulty normal " + name + "SurvivalCreateWorld");
						player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.difficultychange"));
					}else {
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
					 }
				}
	 }
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateDifficulty")) {
				if(current.getType() == Material.EMERALD_BLOCK) {
					player.closeInventory();
					if (player.getWorld().getName().equalsIgnoreCase(name + "SurvivalCreateWorld")) {
						event.setCancelled(true);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world difficulty hard " + name + "SurvivalCreateWorld");
						player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.difficultychange"));
						
					}else {
						 player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.notInWorld"));
					 }
				}
	 }
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				if(current.getType() == Material.BARRIER) {
					event.setCancelled(true);
					player.closeInventory();
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld_nether");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world load " + name + "SurvivalCreateWorld_the_end");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_nether");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world evacuate " + name + "SurvivalCreateWorld_the_end");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world delete " + name + "SurvivalCreateWorld");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world delete " + name + "SurvivalCreateWorld_nether");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "world delete " + name + "SurvivalCreateWorld_the_end");
                	main.getConfig().set("arenas." + name + "SurvivalCreateWorld", null);
                	main.saveConfig();
					
					
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.deleteworld"));
					
					
   }
  }

					
					
   
  
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateMenu")) {
				Inventory invborder = Bukkit.createInventory(null, 27, "§8SurvivalCreateBorder");
				if(current.getType() == Material.BEACON) {
					event.setCancelled(true);
					player.closeInventory();
					
					
						ItemStack  borderdefault = new ItemStack(Material.GLASS, 1);
						ItemMeta borderdefaultM = borderdefault.getItemMeta();
						borderdefaultM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.borderlimit1"));
						borderdefault.setItemMeta(borderdefaultM);
						
						ItemStack  bordervip = new ItemStack(Material.IRON_BLOCK, 1);
						ItemMeta bordervipM = bordervip.getItemMeta();
						bordervipM.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.borderlimit2"));
						bordervip.setItemMeta(bordervipM);
						
						ItemStack  bordervip2 = new ItemStack(Material.BARRIER, 1);
						ItemMeta bordervip2M = bordervip2.getItemMeta();
						bordervip2M.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.borderlimit3"));
						bordervip2.setItemMeta(bordervip2M);
						
						ItemStack  bordervip3 = new ItemStack(Material.BARRIER, 1);
						ItemMeta bordervip3M = bordervip3.getItemMeta();
						bordervip3M.setDisplayName(ChatColor.GOLD + main.getConfig().getString("message.menu.items.borderlimit4"));
						bordervip3.setItemMeta(bordervip3M);
						
						invborder.setItem(10, borderdefault);
						invborder.setItem(12, bordervip);
						invborder.setItem(14, bordervip2);
						invborder.setItem(16, bordervip3);
						player.openInventory(invborder);
						
			}
				}
			
	   
				
			if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
			if(current.getType() == Material.GLASS) {
						player.closeInventory();
						World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
						
						WorldBorder border = world.getWorldBorder();
						
						border.setSize(10000.0);
						border.setCenter(0.0, 0.0);
						
						player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.borderset1"));
				 
					}
    }
	   
				
		if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
			if(current.getType() == Material.IRON_BLOCK) {
				if(player.hasPermission("sc.vip")) {
					player.closeInventory();
					World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
					
					WorldBorder border = world.getWorldBorder();
					
					border.setSize(30000.0);
					border.setCenter(0.0, 0.0);
					
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.borderset2"));
						}
						
  }
		}


		if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
			if(current.getType() == Material.GOLD_BLOCK) {
				if(player.hasPermission("sc.vip2")) {
					player.closeInventory();
					World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
					
					WorldBorder border = world.getWorldBorder();
					
					border.setSize(60000.0);
					border.setCenter(0.0, 0.0);
					
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.borderset3"));
						}
						
    }
   }		

		if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
			if(current.getType() == Material.DIAMOND_BLOCK) {
				if(player.hasPermission("sc.vip3")) {
					player.closeInventory();
					World world = Bukkit.getWorld(name + "SurvivalCreateWorld");
					
					WorldBorder border = world.getWorldBorder();
					
					border.setSize(120000.0);
					border.setCenter(0.0, 0.0);
					
					player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.borderset4"));
						}
						
    }	   
   }
  
		if(inv.getName().equalsIgnoreCase("§8SurvivalCreateBorder")) {
			if(current.getType() == Material.BARRIER) {
				player.closeInventory();
				player.sendMessage(ChatColor.GREEN + main.getConfig().getString("message.menu.answerResponse.nopermission"));
    }
   }

}
				}
			
	   

