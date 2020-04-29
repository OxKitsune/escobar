package com.kitsune.escobar.command;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!command.getName().equalsIgnoreCase("hat")) return false;

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "This command cannot be used by console");
            return true;
        }

        Player player = (Player) sender;

        // Make sure the player is holding an item
        if(player.getEquipment().getItemInMainHand() != null && player.getEquipment().getItemInMainHand().getType() != Material.AIR){

            // Save the current helmet
            ItemStack oldHelmet = player.getEquipment().getHelmet() != null ? player.getEquipment().getHelmet().clone() : null;

            // Put the item on their head
            player.getEquipment().setHelmet(player.getEquipment().getItemInMainHand());

            // Set item in hand
            player.getEquipment().setItemInMainHand((oldHelmet != null && oldHelmet.getType() != Material.AIR ? oldHelmet : new ItemStack(Material.AIR)));

            // Update their inventory
            player.updateInventory();
            player.sendMessage(ChatColor.GREEN + "Set your hat!");
            return true;
        }
        else {
            player.sendMessage(ChatColor.RED + "Please hold an item in your hands!");
        }


        return false;
    }
}
