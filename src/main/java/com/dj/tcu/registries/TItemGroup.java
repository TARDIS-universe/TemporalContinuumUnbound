package com.dj.tcu.registries;

import com.dj.tcu.compat.RegistryKey;
import com.dj.tcu.interfaces.tabs.IMainTab;
import com.dj.tcu.interfaces.tabs.IMiscTab;
import com.dj.tcu.interfaces.tabs.IUtilitiesTab;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

import static com.dj.tcu.TCU.MOD_ID;

public class TItemGroup {
    public static final RegistryKey<CreativeModeTab> MAIN_GROUP_KEY = new RegistryKey<>(Registries.CREATIVE_MODE_TAB.location(), new ResourceLocation(MOD_ID, "tcu_main_tab"));
    public static final RegistryKey<CreativeModeTab> UTILITIES_GROUP_KEY = new RegistryKey<>(Registries.CREATIVE_MODE_TAB.location(), new ResourceLocation(MOD_ID, "tcu_utility_tab"));
    public static final RegistryKey<CreativeModeTab> MISC_GROUP_KEY = new RegistryKey<>(Registries.CREATIVE_MODE_TAB.location(), new ResourceLocation(MOD_ID, "tcu_misc_tab"));
    public static final CreativeModeTab MAIN_ITEM_GROUP;
    public static final CreativeModeTab UTILITIES_ITEM_GROUP;
    public static final CreativeModeTab MISC_ITEM_GROUP;

    static {
        MAIN_ITEM_GROUP = FabricItemGroup.builder().title(Component.translatable("tcu.tabs.main"))
                .icon(() -> new ItemStack(TItems.TEST_ITEM)).build();

        UTILITIES_ITEM_GROUP = FabricItemGroup.builder().title(Component.translatable("tcu.tabs.utility"))
                .icon(() -> new ItemStack(TItems.TEST_ITEM)).build();

        MISC_ITEM_GROUP = FabricItemGroup.builder().title(Component.translatable("tcu.tabs.misc"))
                .icon(() -> new ItemStack(TItems.TEST_ITEM)).build();
    }

    /** Adds all registered items to tabs **/
    public static void Initialize(){
        ItemGroupEvents.modifyEntriesEvent(MAIN_GROUP_KEY).register(itemGroup -> {
            TItems t = new TItems();

            for(Field f : t.getClass().getFields()) {
                try {
                    f.getName();
                    if(TItems.class.getDeclaredField(f.getName()).isAnnotationPresent(IMainTab.class)){
                        itemGroup.accept(((Item) f.get(Item.class)));
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        ItemGroupEvents.modifyEntriesEvent(UTILITIES_GROUP_KEY).register(itemGroup -> {
            TItems t = new TItems();

            for(Field f : t.getClass().getFields()) {
                try {
                    if(TItems.class.getDeclaredField(f.getName()).isAnnotationPresent(IUtilitiesTab.class)){
                        itemGroup.accept(((Item) f.get(Item.class)));
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        ItemGroupEvents.modifyEntriesEvent(MISC_GROUP_KEY).register(itemGroup -> {
            TItems t = new TItems();

            for(Field f : t.getClass().getFields()) {
                try {
                    if(TItems.class.getDeclaredField(f.getName()).isAnnotationPresent(IMiscTab.class)){
                        itemGroup.accept(((Item) f.get(Item.class)));
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void AddToMainTab(Item item) {
        ItemGroupEvents.modifyEntriesEvent(MISC_GROUP_KEY).register(itemGroup -> {
            itemGroup.accept(item);
        });
    }

    public static void AddToUtilitiesTab(Item item) {
        ItemGroupEvents.modifyEntriesEvent(MISC_GROUP_KEY).register(itemGroup -> {
            itemGroup.accept(item);
        });
    }

    public static void AddToMiscTab(Item item) {
        ItemGroupEvents.modifyEntriesEvent(MISC_GROUP_KEY).register(itemGroup -> {
            itemGroup.accept(item);
        });
    }

    public static boolean GetAnnotation(Class<?> parent, Class<? extends Annotation> annotationClass) {
//        return Stream.of(parent.getDeclaredFields())
//                .filter(field -> field.isAnnotationPresent(annotationClass.asSubclass(Annotation.class)))
//                .map(field -> {
//                    // Access the annotation
//                    Annotation annotation = field.getAnnotation(annotationClass);
//                    // Pair the instance with its annotation in a Map.Entry
//                    try {
//                        return new AbstractMap.SimpleEntry<>((Item) field.get(null), annotation);
//                    } catch (IllegalAccessException e) {
//                        LOGGER.info("WE GOT AN IllegalAccessException IN THE TItemGroup#GetAnnotation");
//                        return null;
//                    }
//                })
//                .collect(Collectors.toCollection(Boolean::new));
            return Arrays.stream(parent.getDeclaredFields()).toList().contains(annotationClass);
    }
}
