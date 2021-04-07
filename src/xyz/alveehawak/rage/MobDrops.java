package xyz.alveehawak.rage;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobDrops implements Listener {
    @EventHandler
    public void onMobKill(EntityDeathEvent event) {
        Entity mob = event.getEntity();
        if (mob.getEntityId() == 66){
            ItemStack stack = new ItemStack(Material.ANVIL,5);
            event.getDrops().clear();
            event.getDrops().add(stack);
        }
    }
}
