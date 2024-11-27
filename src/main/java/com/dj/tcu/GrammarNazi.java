package com.dj.tcu;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.dj.tcu.TCU.MOD_ID;

/**
 * This is for functions related to string manipulation
 * Recycled from ASEOHA
 * @author Codiak540
 */
public class GrammarNazi {
    public GrammarNazi() {}

    /**
     * @return The ID of a Block
     */
    public static String IDFromBlock(Block block) {
        return block.toString().replaceAll(MOD_ID, "").replaceAll("minecraft", "").replace("{", "").replace("}", "").replace(":", "").toLowerCase().substring(5);
    }

    /**
     * @return The ID of a Block (With Namespace)
     */
    public static String FullIDFromBlock(Block block) {
        return block.toString().replace("{", "").replace("}", "").toLowerCase().substring(5);
    }

    /**
     * @return The ID of an Item
     */
    public static String IDFromItem(Item item) {
        return item.toString().replaceAll(MOD_ID, "").replaceAll("[{}:]", "").toLowerCase();
    }

    /**
     * Made to accept Item#toString(), remove the minecraft:item@modid, and capitalize every first letter of every word
     * @param text Item#toString()
     * @return Item#toString() without minecraft:item@modid and every first letter of every word capitalized
     */
    public static String CleanItemString(String text) {
        /**
         * remove minecraft:item@modid
         */
        text = text.replace("minecraft:item@" + MOD_ID + ":", "");
        return CapitalizeFirstLetters(ScoreToSpace(text));
    }

    /**
     * @return The Original text with all first letters capitalized
     */
    public static String CapitalizeFirstLetters(String text) {
        String firstLetter = text.substring(0, 1).toUpperCase();
        /**
         * Find any characters coming after a space char and replace it with the uppercase variant
         */
        for(int i = 0; i < text.length(); i++) {
            if (text.substring(i, i + 1).contains(" "))
                text = text.replace(text.substring(i, i + 2), text.substring(i, i + 2).toUpperCase());
        }
        return firstLetter + text.substring(1);
    }

    /**
     * Replace _ chars with space chars
     */
    public static String ScoreToSpace(String text){
        return text.replace("_", " ");
    }
}
