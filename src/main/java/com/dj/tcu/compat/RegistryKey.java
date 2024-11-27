package com.dj.tcu.compat;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

/**
 * This only serves two purposes: So I can use RegistryKey, and copy/paste old peices of code using RegistryKey without renaming every single reference to RegistryKey
 */
public class RegistryKey<T> extends ResourceKey<T> {
    public RegistryKey(ResourceLocation resourceLocation, ResourceLocation resourceLocation2) {
        super(resourceLocation, resourceLocation2);
    }

    /**
     * No clue if this works, havn't tested yet.
     */
    public RegistryKey<T> of(ResourceLocation resourceLocation, ResourceLocation resourceLocation2) {
        return new RegistryKey<>(resourceLocation, resourceLocation2);
    }
}
