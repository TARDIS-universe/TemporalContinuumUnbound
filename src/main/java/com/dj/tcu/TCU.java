package com.dj.tcu;

import com.dj.tcu.registries.TBlocks;
import com.dj.tcu.registries.TItemGroup;
import com.dj.tcu.registries.TItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.dj.tcu.registries.TItemGroup.*;

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


		TBlocks t = new TBlocks();

//		for (Field f : t.getClass().getFields()) {
//			try {
//				if (f.getGenericType() instanceof Block) {
//					Block block = (Block) f.get(Block.class);
//					if (!TBlocks.class.getDeclaredField(f.getName()).isAnnotationPresent(INoItemBlock.class)) {
//						if (!TBlocks.class.getDeclaredField(f.getName()).isAnnotationPresent(IMainTab.class)) {
//							BlockItem item = new BlockItem(block, new Item.Properties());
//							ResourceLocation ID = new ResourceLocation(TCU.MOD_ID, block.getName().toString());
//							ITEM_LIST.add(item);
//							Registry.register(BuiltInRegistries.ITEM, ID, item);
//							AddToMainTab(item);
//						}
//
//						if (!TBlocks.class.getDeclaredField(f.getName()).isAnnotationPresent(IUtilitiesTab.class)) {
//							BlockItem item = new BlockItem(block, new Item.Properties());
//							ResourceLocation ID = new ResourceLocation(TCU.MOD_ID, block.getName().toString());
//							ITEM_LIST.add(item);
//							Registry.register(BuiltInRegistries.ITEM, ID, item);
//							AddToUtilitiesTab(item);
//						}
//
//						if (!TBlocks.class.getDeclaredField(f.getName()).isAnnotationPresent(IMiscTab.class)) {
//							BlockItem item = new BlockItem(block, new Item.Properties());
//							ResourceLocation ID = new ResourceLocation(TCU.MOD_ID, block.getName().toString());
//							ITEM_LIST.add(item);
//							Registry.register(BuiltInRegistries.ITEM, ID, item);
//							AddToMiscTab(item);
//						}
//					}
//				}
//			} catch (NoSuchFieldException | IllegalAccessException e) {
//				throw new RuntimeException(e);
//			}
//		}
	}
}