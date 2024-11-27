package com.dj.tcu.registries;

import com.dj.tcu.TCU;
import com.dj.tcu.interfaces.tabs.IMainTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TItems {

    public static ArrayList<Item> ITEM_LIST = new ArrayList<>();

    /** Registers an item **/
    public static @NotNull Item register(Item Item, String id) {
        ResourceLocation ID = new ResourceLocation(TCU.MOD_ID, id);
        ITEM_LIST.add(Item);
        return Registry.register(BuiltInRegistries.ITEM, ID, Item);
    }

    @IMainTab
    public static final Item TEST_ITEM;

    static {
        TEST_ITEM = register(new Item(new Item.Properties()), "test");
    }

    /**
     * Dummy initialize class to statically initialize this class if it isn't loaded yet
     */
    public static void initialize() {
    }
}