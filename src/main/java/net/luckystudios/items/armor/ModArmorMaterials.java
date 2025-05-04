package net.luckystudios.items.armor;

import net.luckystudios.LuckysArmory;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final Holder<ArmorMaterial> BONE_ARMOR = register("bone_armor",
            Util.make(new EnumMap<>(HeavyArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 1);
                attribute.put(ArmorItem.Type.LEGGINGS, 3);
                attribute.put(ArmorItem.Type.CHESTPLATE, 3);
                attribute.put(ArmorItem.Type.HELMET, 2);
                attribute.put(ArmorItem.Type.BODY, 3);
            }),
            SoundEvents.ARMOR_EQUIP_GENERIC,
            12,
            0.0F,
            0.0F,
            () -> Items.BONE
    );

    public static final Holder<ArmorMaterial> PLATED_ARMOR = register("plated_armor",
            Util.make(new EnumMap<>(HeavyArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 1);
                attribute.put(ArmorItem.Type.LEGGINGS, 4);
                attribute.put(ArmorItem.Type.CHESTPLATE, 5);
                attribute.put(ArmorItem.Type.HELMET, 3);
                attribute.put(ArmorItem.Type.BODY, 4);
            }),
            SoundEvents.ARMOR_EQUIP_IRON,
            9,
            0.0F,
            0.0F,
            () -> Items.RAW_IRON
    );

    public static final Holder<ArmorMaterial> LIGHT_IRON_ARMOR = register("light_iron_armor",
            Util.make(new EnumMap<>(HeavyArmorItem.Type.class), attribute -> {
                // Increased protection values by 1 and body by 3
                attribute.put(ArmorItem.Type.BOOTS, 1);
                attribute.put(ArmorItem.Type.LEGGINGS, 4);
                attribute.put(ArmorItem.Type.CHESTPLATE, 5);
                attribute.put(ArmorItem.Type.HELMET, 1);
                attribute.put(ArmorItem.Type.BODY, 3);
            }),
            9,
            SoundEvents.ARMOR_EQUIP_IRON,
            0.0f,
            0.0f,
            () -> Items.IRON_INGOT,
            List.of(
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "light_iron_armor"), "", false),
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "light_iron_armor"), "_overlay", true)
            )
    );

    public static final Holder<ArmorMaterial> LIGHT_GOLDEN_ARMOR = register("light_golden_armor",
            Util.make(new EnumMap<>(HeavyArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 1);
                attribute.put(ArmorItem.Type.LEGGINGS, 2);
                attribute.put(ArmorItem.Type.CHESTPLATE, 4);
                attribute.put(ArmorItem.Type.HELMET, 1);
                attribute.put(ArmorItem.Type.BODY, 5);
            }),
            25,
            SoundEvents.ARMOR_EQUIP_GOLD,
            0.0f,
            0.0f,
            () -> Items.GOLD_INGOT,
            List.of(
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "light_golden_armor"), "", false),
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "light_golden_armor"), "_overlay", true)
            )
    );

    public static final Holder<ArmorMaterial> LIGHT_DIAMOND_ARMOR = register("light_diamond_armor",
            Util.make(new EnumMap<>(HeavyArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 2);
                attribute.put(ArmorItem.Type.LEGGINGS, 5);
                attribute.put(ArmorItem.Type.CHESTPLATE, 7);
                attribute.put(ArmorItem.Type.HELMET, 3);
                attribute.put(ArmorItem.Type.BODY, 9);
            }),
            10,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            1.0f,
            0.0f,
            () -> Items.DIAMOND,
            List.of(
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "light_diamond_armor"), "", false),
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "light_diamond_armor"), "_overlay", true)
            )
    );

    public static final Holder<ArmorMaterial> LIGHT_NETHERITE_ARMOR = register("light_netherite_armor",
            Util.make(new EnumMap<>(HeavyArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 2);
                attribute.put(ArmorItem.Type.LEGGINGS, 5);
                attribute.put(ArmorItem.Type.CHESTPLATE, 7);
                attribute.put(ArmorItem.Type.HELMET, 3);
                attribute.put(ArmorItem.Type.BODY, 9);
            }),
            15,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            2.0f,
            0.0f,
            () -> Items.NETHERITE_INGOT,
            List.of(
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "light_netherite_armor"), "", false),
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "light_netherite_armor"), "_overlay", true)
            )
    );

    public static final Holder<ArmorMaterial> HEAVY_IRON_ARMOR = register("heavy_iron_armor",
            Util.make(new EnumMap<>(HeavyArmorItem.Type.class), attribute -> {
                // Increased protection values by 1 and body by 3
                attribute.put(ArmorItem.Type.BOOTS, 3);
                attribute.put(ArmorItem.Type.LEGGINGS, 6);
                attribute.put(ArmorItem.Type.CHESTPLATE, 7);
                attribute.put(ArmorItem.Type.HELMET, 5);
                attribute.put(ArmorItem.Type.BODY, 8);
            }),
            9,
            SoundEvents.ARMOR_EQUIP_IRON,
            1f,
            0.05f,
            () -> Items.IRON_INGOT,
            List.of(
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "heavy_iron_armor"), "", false),
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "heavy_iron_armor"), "_overlay", true)
            )
    );

    public static final Holder<ArmorMaterial> HEAVY_GOLDEN_ARMOR = register("heavy_golden_armor",
            Util.make(new EnumMap<>(HeavyArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 2);
                attribute.put(ArmorItem.Type.LEGGINGS, 4);
                attribute.put(ArmorItem.Type.CHESTPLATE, 6);
                attribute.put(ArmorItem.Type.HELMET, 5);
                attribute.put(ArmorItem.Type.BODY, 10);
            }),
            25,
            SoundEvents.ARMOR_EQUIP_GOLD,
            1f,
            0.05f,
            () -> Items.GOLD_INGOT,
            List.of(
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "heavy_golden_armor"), "", false),
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "heavy_golden_armor"), "_overlay", true)
            )
    );

    public static final Holder<ArmorMaterial> HEAVY_DIAMOND_ARMOR = register("heavy_diamond_armor",
            Util.make(new EnumMap<>(HeavyArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 4);
                attribute.put(ArmorItem.Type.LEGGINGS, 7);
                attribute.put(ArmorItem.Type.CHESTPLATE, 9);
                attribute.put(ArmorItem.Type.HELMET, 6);
                attribute.put(ArmorItem.Type.BODY, 14);
            }),
            10,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            3f,
            0.1f,
            () -> Items.DIAMOND,
            List.of(
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "heavy_diamond_armor"), "", false),
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "heavy_diamond_armor"), "_overlay", true)
            )
    );

    public static final Holder<ArmorMaterial> HEAVY_NETHERITE_ARMOR = register("heavy_netherite_armor",
            Util.make(new EnumMap<>(HeavyArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 4);
                attribute.put(ArmorItem.Type.LEGGINGS, 7);
                attribute.put(ArmorItem.Type.CHESTPLATE, 9);
                attribute.put(ArmorItem.Type.HELMET, 6);
                attribute.put(ArmorItem.Type.BODY, 14);
            }),
            15,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            4f,
            0.2f,
            () -> Items.NETHERITE_INGOT,
            List.of(
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "heavy_netherite_armor"), "", false),
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "heavy_netherite_armor"), "_overlay", true)
            )
    );

    public static final CauldronInteraction DYED_ARMOR_ITEM = (state, level, pos, player, hand, stack) -> {
        ItemStack result = stack.copy();
        result.set(DataComponents.DYED_COLOR, new DyedItemColor(DyeColor.WHITE.getTextureDiffuseColor(), false));
        level.setBlock(pos, state.setValue(LayeredCauldronBlock.LEVEL, 1), 3);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        if (!player.getInventory().add(result)) {
            player.drop(result, false);
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    };

    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> typeProtection,
            Holder<SoundEvent> equipSound,
            int enchantability,
            float toughness,
            float knockbackResistance,
            Supplier<Item> repairIngredient) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, name);
        Supplier<Ingredient> ingredient = () -> Ingredient.of(repairIngredient.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<HeavyArmorItem.Type, Integer> typeMap = new EnumMap<>(HeavyArmorItem.Type.class);
        for (HeavyArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }

    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> typeProtection,
            int enchantability,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Item> repairIngredient,
            List<ArmorMaterial.Layer> layers) {

        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, name);
        Supplier<Ingredient> ingredient = () -> Ingredient.of(repairIngredient.get());

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}