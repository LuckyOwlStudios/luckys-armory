package net.luckystudios.datagen.types;

import net.luckystudios.LuckysArmory;
import net.luckystudios.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, LuckysArmory.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.DYEABLE)
                .add(ModItems.HEAVY_IRON_HELMET.get())
                .add(ModItems.HEAVY_IRON_CHESTPLATE.get())
                .add(ModItems.HEAVY_IRON_LEGGINGS.get())
                .add(ModItems.HEAVY_IRON_BOOTS.get())
                .add(ModItems.HEAVY_GOLDEN_HELMET.get())
                .add(ModItems.HEAVY_GOLDEN_CHESTPLATE.get())
                .add(ModItems.HEAVY_GOLDEN_LEGGINGS.get())
                .add(ModItems.HEAVY_GOLDEN_BOOTS.get())
                .add(ModItems.HEAVY_DIAMOND_HELMET.get())
                .add(ModItems.HEAVY_DIAMOND_CHESTPLATE.get())
                .add(ModItems.HEAVY_DIAMOND_LEGGINGS.get())
                .add(ModItems.HEAVY_DIAMOND_BOOTS.get())
                .add(ModItems.HEAVY_NETHERITE_HELMET.get())
                .add(ModItems.HEAVY_NETHERITE_CHESTPLATE.get())
                .add(ModItems.HEAVY_NETHERITE_LEGGINGS.get())
                .add(ModItems.HEAVY_NETHERITE_BOOTS.get())
        ;

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.HEAVY_IRON_CHESTPLATE.get())
                .add(ModItems.HEAVY_IRON_LEGGINGS.get())
                .add(ModItems.HEAVY_IRON_BOOTS.get())
                .add(ModItems.HEAVY_GOLDEN_CHESTPLATE.get())
                .add(ModItems.HEAVY_GOLDEN_LEGGINGS.get())
                .add(ModItems.HEAVY_GOLDEN_BOOTS.get())
                .add(ModItems.HEAVY_DIAMOND_CHESTPLATE.get())
                .add(ModItems.HEAVY_DIAMOND_LEGGINGS.get())
                .add(ModItems.HEAVY_DIAMOND_BOOTS.get())
                .add(ModItems.HEAVY_NETHERITE_CHESTPLATE.get())
                .add(ModItems.HEAVY_NETHERITE_LEGGINGS.get())
                .add(ModItems.HEAVY_NETHERITE_BOOTS.get())
        ;

        tag(ItemTags.PIGLIN_LOVED)
                .add(ModItems.LIGHT_GOLDEN_CHESTPLATE.get())
                .add(ModItems.LIGHT_GOLDEN_LEGGINGS.get())
                .add(ModItems.HEAVY_GOLDEN_HELMET.get())
                .add(ModItems.HEAVY_GOLDEN_CHESTPLATE.get())
                .add(ModItems.HEAVY_GOLDEN_LEGGINGS.get())
                .add(ModItems.HEAVY_GOLDEN_BOOTS.get())
        ;
    }
}
