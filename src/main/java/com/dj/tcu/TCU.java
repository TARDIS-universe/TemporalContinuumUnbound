package com.dj.tcu;

import com.dj.tcu.interfaces.INoItemBlock;
import com.dj.tcu.interfaces.tabs.IMainTab;
import com.dj.tcu.interfaces.tabs.IMiscTab;
import com.dj.tcu.interfaces.tabs.IUtilitiesTab;
import com.dj.tcu.registries.TBlocks;
import com.dj.tcu.registries.TItemGroup;
import com.dj.tcu.registries.TItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import static com.dj.tcu.registries.TItemGroup.*;
import static com.dj.tcu.registries.TItems.ITEM_LIST;

public class TCU implements ModInitializer {
	public static final String MOD_ID = "tcu";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		TItems.initialize();
		TBlocks.initialize();
		TItemGroup.Initialize();
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MAIN_GROUP_KEY, MAIN_ITEM_GROUP);
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, UTILITIES_GROUP_KEY, UTILITIES_ITEM_GROUP);
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MISC_GROUP_KEY, MISC_ITEM_GROUP);


		TBlocks t = new TBlocks();

		for (Field f : t.getClass().getFields()) {
			try {
				Class<?> fieldType = f.getType();
				LOGGER.info(fieldType.toString());
				if (fieldType.equals(Block.class)) {
					Block block = (Block) f.get(Block.class);
					if (!TBlocks.class.getDeclaredField(f.getName()).isAnnotationPresent(INoItemBlock.class)) {
						if (TBlocks.class.getDeclaredField(f.getName()).isAnnotationPresent(IMainTab.class)) {
							BlockItem item = new BlockItem(block, new Item.Properties());
							ResourceLocation ID = new ResourceLocation(TCU.MOD_ID, GrammarNazi.IDFromBlock(block));
							ITEM_LIST.add(item);
							Registry.register(BuiltInRegistries.ITEM, ID, item);
							AddToMainTab(item);
						}

						if (TBlocks.class.getDeclaredField(f.getName()).isAnnotationPresent(IUtilitiesTab.class)) {
							BlockItem item = new BlockItem(block, new Item.Properties());
							ResourceLocation ID = new ResourceLocation(TCU.MOD_ID, GrammarNazi.IDFromBlock(block));
							ITEM_LIST.add(item);
							Registry.register(BuiltInRegistries.ITEM, ID, item);
							AddToUtilitiesTab(item);
						}

						if (TBlocks.class.getDeclaredField(f.getName()).isAnnotationPresent(IMiscTab.class)) {
							BlockItem item = new BlockItem(block, new Item.Properties());
							ResourceLocation ID = new ResourceLocation(TCU.MOD_ID, GrammarNazi.IDFromBlock(block));
							ITEM_LIST.add(item);
							Registry.register(BuiltInRegistries.ITEM, ID, item);
							AddToMiscTab(item);
						}
					}
				}
			} catch (NoSuchFieldException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}
}