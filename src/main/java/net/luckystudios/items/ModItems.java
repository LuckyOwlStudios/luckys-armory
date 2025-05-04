package net.luckystudios.items;

import net.luckystudios.LuckysArmory;
import net.luckystudios.components.ModDataComponents;
import net.luckystudios.items.armor.HeavyArmorItem;
import net.luckystudios.items.armor.ModArmorMaterials;
import net.luckystudios.items.armor.custom.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LuckysArmory.MOD_ID);

    public static final DeferredItem<ArmorItem> LIGHT_IRON_CHESTPLATE = ITEMS.register("light_iron_chestplate",
            () -> new LightIronArmorItem(ModArmorMaterials.LIGHT_IRON_ARMOR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(12))));
    public static final DeferredItem<ArmorItem> LIGHT_IRON_LEGGINGS = ITEMS.register("light_iron_leggings",
            () -> new LightIronArmorItem(ModArmorMaterials.LIGHT_IRON_ARMOR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(12))));

    public static final DeferredItem<ArmorItem> LIGHT_GOLDEN_CHESTPLATE = ITEMS.register("light_golden_chestplate",
            () -> new LightGoldenArmorItem(ModArmorMaterials.LIGHT_GOLDEN_ARMOR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(6))));
    public static final DeferredItem<ArmorItem> LIGHT_GOLDEN_LEGGINGS = ITEMS.register("light_golden_leggings",
            () -> new LightGoldenArmorItem(ModArmorMaterials.LIGHT_GOLDEN_ARMOR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(6))));

    public static final DeferredItem<ArmorItem> LIGHT_DIAMOND_CHESTPLATE = ITEMS.register("light_diamond_chestplate",
            () -> new LightDiamondArmorItem(ModArmorMaterials.LIGHT_DIAMOND_ARMOR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(25))));
    public static final DeferredItem<ArmorItem> LIGHT_DIAMOND_LEGGINGS = ITEMS.register("light_diamond_leggings",
            () -> new LightDiamondArmorItem(ModArmorMaterials.LIGHT_DIAMOND_ARMOR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(25))));

    public static final DeferredItem<ArmorItem> LIGHT_NETHERITE_CHESTPLATE = ITEMS.register("light_netherite_chestplate",
            () -> new LightNetheriteArmorItem(ModArmorMaterials.LIGHT_NETHERITE_ARMOR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(30)).fireResistant()));
    public static final DeferredItem<ArmorItem> LIGHT_NETHERITE_LEGGINGS = ITEMS.register("light_netherite_leggings",
            () -> new LightNetheriteArmorItem(ModArmorMaterials.LIGHT_NETHERITE_ARMOR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(30)).fireResistant()));

    // Doubled the durability of the armor items
    public static final DeferredItem<HeavyArmorItem> HEAVY_IRON_HELMET = ITEMS.register("heavy_iron_helmet",
            () -> new HeavyIronArmorItem(ModArmorMaterials.HEAVY_IRON_ARMOR, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(30)).component(ModDataComponents.OPENED, false)));
    public static final DeferredItem<HeavyArmorItem> HEAVY_IRON_CHESTPLATE = ITEMS.register("heavy_iron_chestplate",
            () -> new HeavyIronArmorItem(ModArmorMaterials.HEAVY_IRON_ARMOR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(30))));
    public static final DeferredItem<HeavyArmorItem> HEAVY_IRON_LEGGINGS = ITEMS.register("heavy_iron_leggings",
            () -> new HeavyIronArmorItem(ModArmorMaterials.HEAVY_IRON_ARMOR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(30))));
    public static final DeferredItem<HeavyArmorItem> HEAVY_IRON_BOOTS = ITEMS.register("heavy_iron_boots",
            () -> new HeavyIronArmorItem(ModArmorMaterials.HEAVY_IRON_ARMOR, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(30))));

    public static final DeferredItem<HeavyArmorItem> HEAVY_GOLDEN_HELMET = ITEMS.register("heavy_golden_helmet",
            () -> new HeavyGoldenArmorItem(ModArmorMaterials.HEAVY_GOLDEN_ARMOR, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(14))));
    public static final DeferredItem<HeavyArmorItem> HEAVY_GOLDEN_CHESTPLATE = ITEMS.register("heavy_golden_chestplate",
            () -> new HeavyGoldenArmorItem(ModArmorMaterials.HEAVY_GOLDEN_ARMOR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(14))));
    public static final DeferredItem<HeavyArmorItem> HEAVY_GOLDEN_LEGGINGS = ITEMS.register("heavy_golden_leggings",
            () -> new HeavyGoldenArmorItem(ModArmorMaterials.HEAVY_GOLDEN_ARMOR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(14))));
    public static final DeferredItem<HeavyArmorItem> HEAVY_GOLDEN_BOOTS = ITEMS.register("heavy_golden_boots",
            () -> new HeavyGoldenArmorItem(ModArmorMaterials.HEAVY_GOLDEN_ARMOR, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(14))));

    public static final DeferredItem<HeavyArmorItem> HEAVY_DIAMOND_HELMET = ITEMS.register("heavy_diamond_helmet",
            () -> new HeavyDiamondArmorItem(ModArmorMaterials.HEAVY_DIAMOND_ARMOR, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(66)).component(ModDataComponents.OPENED, false)));
    public static final DeferredItem<HeavyArmorItem> HEAVY_DIAMOND_CHESTPLATE = ITEMS.register("heavy_diamond_chestplate",
            () -> new HeavyDiamondArmorItem(ModArmorMaterials.HEAVY_DIAMOND_ARMOR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(66))));
    public static final DeferredItem<HeavyArmorItem> HEAVY_DIAMOND_LEGGINGS = ITEMS.register("heavy_diamond_leggings",
            () -> new HeavyDiamondArmorItem(ModArmorMaterials.HEAVY_DIAMOND_ARMOR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(66))));
    public static final DeferredItem<HeavyArmorItem> HEAVY_DIAMOND_BOOTS = ITEMS.register("heavy_diamond_boots",
            () -> new HeavyDiamondArmorItem(ModArmorMaterials.HEAVY_DIAMOND_ARMOR, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(66))));

    public static final DeferredItem<HeavyArmorItem> HEAVY_NETHERITE_HELMET = ITEMS.register("heavy_netherite_helmet",
            () -> new HeavyNetheriteArmorItem(ModArmorMaterials.HEAVY_NETHERITE_ARMOR, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(74)).fireResistant().component(ModDataComponents.OPENED, false)));
    public static final DeferredItem<HeavyArmorItem> HEAVY_NETHERITE_CHESTPLATE = ITEMS.register("heavy_netherite_chestplate",
            () -> new HeavyNetheriteArmorItem(ModArmorMaterials.HEAVY_NETHERITE_ARMOR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(74)).fireResistant()));
    public static final DeferredItem<HeavyArmorItem> HEAVY_NETHERITE_LEGGINGS = ITEMS.register("heavy_netherite_leggings",
            () -> new HeavyNetheriteArmorItem(ModArmorMaterials.HEAVY_NETHERITE_ARMOR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(74)).fireResistant()));
    public static final DeferredItem<HeavyArmorItem> HEAVY_NETHERITE_BOOTS = ITEMS.register("heavy_netherite_boots",
            () -> new HeavyNetheriteArmorItem(ModArmorMaterials.HEAVY_NETHERITE_ARMOR, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(74)).fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
