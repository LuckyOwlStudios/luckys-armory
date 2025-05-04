package net.luckystudios.items;

import net.luckystudios.LuckysArmory;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LuckysArmory.MOD_ID);

    public static final Supplier<CreativeModeTab> ARMORY_CREATIVE_TAB = CREATIVE_MODE_TAB.register("luckysarmory_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HEAVY_IRON_HELMET.get()))
                    .title(Component.translatable("itemGroup.luckysarmory"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.LIGHT_IRON_CHESTPLATE);
                        output.accept(ModItems.LIGHT_IRON_LEGGINGS);
                        output.accept(ModItems.LIGHT_GOLDEN_CHESTPLATE);
                        output.accept(ModItems.LIGHT_GOLDEN_LEGGINGS);
                        output.accept(ModItems.LIGHT_DIAMOND_CHESTPLATE);
                        output.accept(ModItems.LIGHT_DIAMOND_LEGGINGS);
                        output.accept(ModItems.LIGHT_NETHERITE_CHESTPLATE);
                        output.accept(ModItems.LIGHT_NETHERITE_LEGGINGS);
                        output.accept(ModItems.HEAVY_IRON_HELMET);
                        output.accept(ModItems.HEAVY_IRON_CHESTPLATE);
                        output.accept(ModItems.HEAVY_IRON_LEGGINGS);
                        output.accept(ModItems.HEAVY_IRON_BOOTS);
                        output.accept(ModItems.HEAVY_GOLDEN_HELMET);
                        output.accept(ModItems.HEAVY_GOLDEN_CHESTPLATE);
                        output.accept(ModItems.HEAVY_GOLDEN_LEGGINGS);
                        output.accept(ModItems.HEAVY_GOLDEN_BOOTS);
                        output.accept(ModItems.HEAVY_DIAMOND_HELMET);
                        output.accept(ModItems.HEAVY_DIAMOND_CHESTPLATE);
                        output.accept(ModItems.HEAVY_DIAMOND_LEGGINGS);
                        output.accept(ModItems.HEAVY_DIAMOND_BOOTS);
                        output.accept(ModItems.HEAVY_NETHERITE_HELMET);
                        output.accept(ModItems.HEAVY_NETHERITE_CHESTPLATE);
                        output.accept(ModItems.HEAVY_NETHERITE_LEGGINGS);
                        output.accept(ModItems.HEAVY_NETHERITE_BOOTS);
                    }).build());

    // Add the example block item to the building blocks tab
    public static void addVanillaCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.insertAfter(Items.IRON_BOOTS.getDefaultInstance(), ModItems.HEAVY_IRON_HELMET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_IRON_HELMET.get().getDefaultInstance(), ModItems.HEAVY_IRON_CHESTPLATE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_IRON_CHESTPLATE.get().getDefaultInstance(), ModItems.HEAVY_IRON_LEGGINGS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_IRON_LEGGINGS.get().getDefaultInstance(), ModItems.HEAVY_IRON_BOOTS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.GOLDEN_BOOTS.getDefaultInstance(), ModItems.HEAVY_GOLDEN_HELMET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_GOLDEN_HELMET.get().getDefaultInstance(), ModItems.HEAVY_GOLDEN_CHESTPLATE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_GOLDEN_CHESTPLATE.get().getDefaultInstance(), ModItems.HEAVY_GOLDEN_LEGGINGS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_GOLDEN_LEGGINGS.get().getDefaultInstance(), ModItems.HEAVY_GOLDEN_BOOTS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.DIAMOND_BOOTS.getDefaultInstance(), ModItems.HEAVY_DIAMOND_HELMET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_DIAMOND_HELMET.get().getDefaultInstance(), ModItems.HEAVY_DIAMOND_CHESTPLATE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_DIAMOND_CHESTPLATE.get().getDefaultInstance(), ModItems.HEAVY_DIAMOND_LEGGINGS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_DIAMOND_LEGGINGS.get().getDefaultInstance(), ModItems.HEAVY_DIAMOND_BOOTS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.NETHERITE_BOOTS.getDefaultInstance(), ModItems.HEAVY_NETHERITE_HELMET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_NETHERITE_HELMET.get().getDefaultInstance(), ModItems.HEAVY_NETHERITE_CHESTPLATE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_NETHERITE_CHESTPLATE.get().getDefaultInstance(), ModItems.HEAVY_NETHERITE_LEGGINGS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.HEAVY_NETHERITE_LEGGINGS.get().getDefaultInstance(), ModItems.HEAVY_NETHERITE_BOOTS.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
