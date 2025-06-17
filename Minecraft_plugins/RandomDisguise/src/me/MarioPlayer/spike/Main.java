package me.MarioPlayer.spike;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		getLogger().info("Random Disguise enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("Random Disguise disabled!");
	}
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args, Random random) {
    	List<String> mobs = Arrays.asList("Area_Effect_Cloud", "Arrow", "Armor_Stand", "Bat", "Bee", "Blaze", "Boat", "Cat", "Cave_Spider", "Chicken", "Cod", "Cow", "Creeper", "Dolphin", "Donkey", "Dragon_Fireball", "Dropped_Item", "Drowned", "Egg", "Elder_Guardian", "Ender_Crystal", "Ender_Dragon", "Ender_Pearl", "Ender_Signal", "Enderman", "Endermite", "Evoker", "Evoker_Fangs", "Experience_Orb", "Falling_Block", "Fireball", "Firework", "Fishing_Hook", "Fox", "Ghast", "Giant", "Guardian", "Hoglin", "Horse", "Husk", "Illusioner", "Iron_Golem", "Item_Frame", "Leash_Hitch", "Llama", "Llama_Spit", "Magma_Cube", "Minecart", "Minecart_Chest", "Minecart_Furnace", "Minecart_Hopper", "Minecart_Mob_Spawner", "Minecart_Tnt", "Mule", "Mushroom_Cow", "Ocelot", "Painting", "Panda", "Parrot", "Phantom", "Pig", "Piglin", "Piglin_Brute", "Pillager", "Polar_Bear", "Primed_Tnt", "Rabbit", "PufferFish", "Ravager", "Salmon", "Sheep", "Shulker", "Shulker_Bullet", "Silverfish", "Skeleton", "Skeleton_Horse", "Slime", "Small_Fireball", "Snowball", "Snowman", "Spectral_Arrow", "Spider", "Splash_Potion", "Squid", "Strider", "Thrown_Exp_Bottle", "Trader_Llama", "Trident", "Tropical_Fish", "Turtle", "Vex", "Villager", "Vindicator", "Wandering_Trader", "Witch", "Wither", "Wither_Skeleton", "Wither_Skull", "Wolf", "Zombie", "Zoglin", "Zombie_Horse", "Zombie_Villager", "Zombified_Piglin");
    	String rmob = "";
    	int rint = random.nextInt(107);
    	rmob = mobs.get(rint);
        if (label.equalsIgnoreCase("disguiserandom")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.chat("/disguise "+rmob);
                return true;
            }
        }
        return false;
    }
		
}
