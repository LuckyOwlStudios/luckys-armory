package net.luckystudios.datagen.types;

import net.luckystudios.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.ArmorDyeRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        // Light Iron Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.LIGHT_IRON_CHESTPLATE.get())
                .pattern("# #")
                .pattern("@#@")
                .define('#', Items.IRON_INGOT)
                .define('@', Items.LEATHER)
                .unlockedBy("has_iron", has(Items.IRON_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.LIGHT_IRON_LEGGINGS.get())
                .pattern("@#@")
                .pattern("# #")
                .define('#', Items.IRON_INGOT)
                .define('@', Items.LEATHER)
                .unlockedBy("has_iron", has(Items.IRON_INGOT)).save(recipeOutput);

        // Light Gold Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.LIGHT_GOLDEN_CHESTPLATE.get())
                .pattern("# #")
                .pattern("@#@")
                .define('#', Items.GOLD_INGOT)
                .define('@', Items.LEATHER)
                .unlockedBy("has_gold", has(Items.GOLD_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.LIGHT_GOLDEN_LEGGINGS.get())
                .pattern("@#@")
                .pattern("# #")
                .define('#', Items.GOLD_INGOT)
                .define('@', Items.LEATHER)
                .unlockedBy("has_gold", has(Items.GOLD_INGOT)).save(recipeOutput);

        // Light Diamond Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.LIGHT_DIAMOND_CHESTPLATE.get())
                .pattern("# #")
                .pattern("@#@")
                .define('#', Items.DIAMOND)
                .define('@', Items.LEATHER)
                .unlockedBy("has_diamond", has(Items.DIAMOND)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.LIGHT_DIAMOND_LEGGINGS.get())
                .pattern("@#@")
                .pattern("# #")
                .define('#', Items.DIAMOND)
                .define('@', Items.LEATHER)
                .unlockedBy("has_diamond", has(Items.DIAMOND)).save(recipeOutput);

        // Light Netherite Armor
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.LIGHT_DIAMOND_CHESTPLATE.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        ModItems.LIGHT_NETHERITE_CHESTPLATE.get())
                .unlocks("has_diamond", has(ModItems.LIGHT_DIAMOND_CHESTPLATE.get()))
                .save(recipeOutput, "light_netherite_chestplate");
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.LIGHT_DIAMOND_LEGGINGS.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        ModItems.LIGHT_NETHERITE_LEGGINGS.get())
                .unlocks("has_diamond", has(ModItems.LIGHT_DIAMOND_LEGGINGS.get()))
                .save(recipeOutput, "light_netherite_leggings");

        // Heavy Iron Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_IRON_HELMET.get())
                .pattern("###")
                .pattern("#@#")
                .define('@', Items.IRON_HELMET)
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_IRON_CHESTPLATE.get())
                .pattern("# #")
                .pattern("#@#")
                .pattern("###")
                .define('@', Items.IRON_CHESTPLATE)
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_IRON_LEGGINGS.get())
                .pattern("###")
                .pattern("#@#")
                .pattern("# #")
                .define('@', Items.IRON_LEGGINGS)
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_IRON_BOOTS.get())
                .pattern("# #")
                .pattern("#@#")
                .define('@', Items.IRON_BOOTS)
                .define('#', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT)).save(recipeOutput);

        // Heavy Gold Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_GOLDEN_HELMET.get())
                .pattern("###")
                .pattern("#@#")
                .define('@', Items.GOLDEN_HELMET)
                .define('#', Items.GOLD_INGOT)
                .unlockedBy("has_gold", has(Items.GOLD_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_GOLDEN_CHESTPLATE.get())
                .pattern("# #")
                .pattern("#@#")
                .pattern("###")
                .define('@', Items.GOLDEN_CHESTPLATE)
                .define('#', Items.GOLD_INGOT)
                .unlockedBy("has_gold", has(Items.GOLD_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_GOLDEN_LEGGINGS.get())
                .pattern("###")
                .pattern("#@#")
                .pattern("# #")
                .define('@', Items.GOLDEN_LEGGINGS)
                .define('#', Items.GOLD_INGOT)
                .unlockedBy("has_gold", has(Items.GOLD_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_GOLDEN_BOOTS.get())
                .pattern("# #")
                .pattern("#@#")
                .define('@', Items.GOLDEN_BOOTS)
                .define('#', Items.GOLD_INGOT)
                .unlockedBy("has_gold", has(Items.GOLD_INGOT)).save(recipeOutput);

        // Heavy Diamond Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_DIAMOND_HELMET.get())
                .pattern("###")
                .pattern("#@#")
                .define('@', Items.DIAMOND_HELMET)
                .define('#', Items.DIAMOND)
                .unlockedBy("has_diamond", has(Items.DIAMOND)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_DIAMOND_CHESTPLATE.get())
                .pattern("# #")
                .pattern("#@#")
                .pattern("###")
                .define('@', Items.DIAMOND_CHESTPLATE)
                .define('#', Items.DIAMOND)
                .unlockedBy("has_diamond", has(Items.DIAMOND)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_DIAMOND_LEGGINGS.get())
                .pattern("###")
                .pattern("#@#")
                .pattern("# #")
                .define('@', Items.DIAMOND_LEGGINGS)
                .define('#', Items.DIAMOND)
                .unlockedBy("has_diamond", has(Items.DIAMOND)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HEAVY_DIAMOND_BOOTS.get())
                .pattern("# #")
                .pattern("#@#")
                .define('@', Items.DIAMOND_BOOTS)
                .define('#', Items.DIAMOND)
                .unlockedBy("has_diamond", has(Items.DIAMOND)).save(recipeOutput);

        // Heavy Netherite Armor
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.HEAVY_DIAMOND_HELMET.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        ModItems.HEAVY_NETHERITE_HELMET.get())
                .unlocks("has_diamond", has(ModItems.HEAVY_DIAMOND_HELMET.get()))
                .save(recipeOutput, "heavy_netherite_helmet");
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.HEAVY_DIAMOND_CHESTPLATE.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        ModItems.HEAVY_NETHERITE_CHESTPLATE.get())
                .unlocks("has_diamond", has(ModItems.HEAVY_DIAMOND_CHESTPLATE.get()))
                .save(recipeOutput, "heavy_netherite_chestplate");
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.HEAVY_DIAMOND_LEGGINGS.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        ModItems.HEAVY_NETHERITE_LEGGINGS.get())
                .unlocks("has_diamond", has(ModItems.HEAVY_DIAMOND_LEGGINGS.get()))
                .save(recipeOutput, "heavy_netherite_leggings");
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.HEAVY_DIAMOND_BOOTS.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        ModItems.HEAVY_NETHERITE_BOOTS.get())
                .unlocks("has_diamond", has(ModItems.HEAVY_DIAMOND_BOOTS.get()))
                .save(recipeOutput, "heavy_netherite_boots");
    }
}
