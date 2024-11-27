package com.dj.tcu.registries;

import com.dj.tcu.interfaces.INoItemBlock;
import com.dj.tcu.interfaces.tabs.IMiscTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.ToIntFunction;

import static com.dj.tcu.TCU.MOD_ID;

public class TBlocks {

    /** The max light value **/
    public static ToIntFunction<BlockState> MaxLightLevel = BlockState -> 15;

    /** Takes a number and converts it to blockstate definition for light emission **/
    @Contract(pure = true)
    private static @NotNull ToIntFunction<BlockState> litBlockEmission(int light) {
        return (p_lambda$litBlockEmission$34_1_) -> (Boolean) p_lambda$litBlockEmission$34_1_.getValue(BlockStateProperties.LIT) ? Math.min(light, 15) : 0;
    }

    /** Registers a block **/
    public static @NotNull Block register(Block block, String id) {
        ResourceLocation ID = new ResourceLocation(MOD_ID, id);
        return Registry.register(BuiltInRegistries.BLOCK, ID, block);
    }

    @IMiscTab
    public static final Block TEST_BLOCK;
    @INoItemBlock
    public static final Block TEST_NO_ITEM;

    static {
        TEST_BLOCK = register(new Block(BlockBehaviour.Properties.of()), "test_block");
        TEST_NO_ITEM = register(new Block(BlockBehaviour.Properties.of()), "test_no_item");
    }


    /**
     * Dummy initialize class to statically initialize this class if it isn't loaded yet
     */
    public static void initialize() {
    }
}