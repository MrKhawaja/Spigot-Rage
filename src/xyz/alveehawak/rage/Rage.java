package xyz.alveehawak.rage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.TimeUnit;

public class Rage extends JavaPlugin {
    public static Plugin plugin;


    public Rage() {
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"EEEEEE primeedge er RAGE plugin on hoise");
        plugin = this;
        getServer().getPluginManager().registerEvents(new MobDrops(), this);
    }

    private final CooldownManager rageCooldownManager = new CooldownManager();
    private final CooldownManager furyCooldownManager = new CooldownManager();
    private final CooldownManager regenCooldownManager = new CooldownManager();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("rage")) {
                long timeLeft = System.currentTimeMillis() - rageCooldownManager.getCooldown(p.getUniqueId());
                if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= CooldownManager.DEFAULT_COOLDOWN){
                    ((Player) sender).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 900, 2));
                    ((Player) sender).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 2));
                    ((Player) sender).addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 900, 0));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l[&f&lPixelEffects&7&l]  &cRage &a has been used!"));
                    rageCooldownManager.setCooldown(p.getUniqueId(), System.currentTimeMillis());
                }else{
                    Long timeleft = CooldownManager.DEFAULT_COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(timeLeft);
                    p.sendMessage(ChatColor.RED +" "+ timeleft.toString() + " seconds before you can use rage again.");
                }
                return true;
            }else if(label.equalsIgnoreCase("fury")){
                long timeLeft = System.currentTimeMillis() - furyCooldownManager.getCooldown(p.getUniqueId());
                if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= CooldownManager.DEFAULT_COOLDOWN){
                    ((Player) sender).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 1));
                    ((Player) sender).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 900, 1));
                    ((Player) sender).addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 900, 0));
                    ((Player) sender).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 3));
                    ((Player) sender).addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 900, 2));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l[&f&lPixelEffects&7&l]  &6Fury &ahas been used!"));
                    furyCooldownManager.setCooldown(p.getUniqueId(), System.currentTimeMillis());
                }else{
                    Long timeleft = CooldownManager.DEFAULT_COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(timeLeft);
                    p.sendMessage(ChatColor.RED +" "+ timeleft.toString() + " seconds before you can use fury command again.");
                }
                return true;
            }else if(label.equalsIgnoreCase("regen")){
                long timeLeft = System.currentTimeMillis() - regenCooldownManager.getCooldown(p.getUniqueId());
                if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= CooldownManager.DEFAULT_COOLDOWN){
                    ((Player) sender).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 3));
                    ((Player) sender).addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 600, 3));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l[&f&lPixelEffects&7&l]  &dRegen &ahas been used!"));
                    regenCooldownManager.setCooldown(p.getUniqueId(), System.currentTimeMillis());
                }else{
                    Long timeleft = CooldownManager.DEFAULT_COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(timeLeft);
                    p.sendMessage(ChatColor.RED + " "+ timeleft.toString() + " seconds before you can use regen command again.");
                }
                return true;
            }
        }
        return false;
    }


    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"PixelRage Off hoilo");
    }


}
