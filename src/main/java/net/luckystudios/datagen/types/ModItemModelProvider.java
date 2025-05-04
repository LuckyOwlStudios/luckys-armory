package net.luckystudios.datagen.types;

import net.luckystudios.LuckysArmory;
import net.luckystudios.items.ModItems;
import net.luckystudios.items.armor.HeavyArmorItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    private ResourceLocation HEAVY_CHESTPLATE_DYE_OVERLAY = ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "item/heavy_chestplate_dye_overlay");
    private ResourceLocation HEAVY_LEGGINGS_DYE_OVERLAY = ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "item/heavy_leggings_dye_overlay");
    private ResourceLocation HEAVY_BOOTS_DYE_OVERLAY = ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "item/heavy_boots_dye_overlay");

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LuckysArmory.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        // Light Iron Armor
        basicItem(ModItems.LIGHT_IRON_CHESTPLATE.get());
        basicItem(ModItems.LIGHT_IRON_LEGGINGS.get());

        // Light Golden Armor
        basicItem(ModItems.LIGHT_GOLDEN_CHESTPLATE.get());
        basicItem(ModItems.LIGHT_GOLDEN_LEGGINGS.get());

        // Light Diamond Armor
        basicItem(ModItems.LIGHT_DIAMOND_CHESTPLATE.get());
        basicItem(ModItems.LIGHT_DIAMOND_LEGGINGS.get());

        // Light Netherite Armor
        basicItem(ModItems.LIGHT_NETHERITE_CHESTPLATE.get());
        basicItem(ModItems.LIGHT_NETHERITE_LEGGINGS.get());

        // Heavy Iron Armor
        withExistingParent("heavy_iron_helmet_opened", "item/generated")
                .texture("layer0", "luckysarmory:item/heavy_iron_helmet_opened")
                .texture("layer1", "luckysarmory:item/heavy_iron_helmet_dye_overlay");
        withExistingParent("heavy_iron_helmet", "item/generated")
                .texture("layer0", "luckysarmory:item/heavy_iron_helmet")
                .texture("layer1", "luckysarmory:item/heavy_iron_helmet_dye_overlay")
                .override()
                .predicate(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "opened"), 1.0F)
                .model(getExistingFile(modLoc("item/heavy_iron_helmet_opened")))
                .end();
        trimmedArmorItem(ModItems.HEAVY_IRON_CHESTPLATE, HEAVY_CHESTPLATE_DYE_OVERLAY);
        trimmedArmorItem(ModItems.HEAVY_IRON_LEGGINGS, HEAVY_LEGGINGS_DYE_OVERLAY);
        trimmedArmorItem(ModItems.HEAVY_IRON_BOOTS, HEAVY_BOOTS_DYE_OVERLAY);

        // Golden Armor
        withExistingParent("heavy_golden_helmet_opened", "item/generated")
                .texture("layer0", "luckysarmory:item/heavy_golden_helmet_opened")
                .texture("layer1", "luckysarmory:item/heavy_golden_helmet_dye_overlay");
        withExistingParent("heavy_golden_helmet", "item/generated")
                .texture("layer0", "luckysarmory:item/heavy_golden_helmet")
                .texture("layer1", "luckysarmory:item/heavy_golden_helmet_dye_overlay")
                .override()
                .predicate(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "opened"), 1.0F)
                .model(getExistingFile(modLoc("item/heavy_golden_helmet_opened")))
                .end();
        trimmedArmorItem(ModItems.HEAVY_GOLDEN_CHESTPLATE, HEAVY_CHESTPLATE_DYE_OVERLAY);
        trimmedArmorItem(ModItems.HEAVY_GOLDEN_LEGGINGS, HEAVY_LEGGINGS_DYE_OVERLAY);
        trimmedArmorItem(ModItems.HEAVY_GOLDEN_BOOTS, HEAVY_BOOTS_DYE_OVERLAY);

        // Diamond Armor
        withExistingParent("heavy_diamond_helmet_opened", "item/generated")
                .texture("layer0", "luckysarmory:item/heavy_diamond_helmet_opened")
                .texture("layer1", "luckysarmory:item/heavy_diamond_helmet_opened_dye_overlay");
        withExistingParent("heavy_diamond_helmet", "item/generated")
                .texture("layer0", "luckysarmory:item/heavy_diamond_helmet")
                .texture("layer1", "luckysarmory:item/heavy_diamond_helmet_dye_overlay")
                .override()
                .predicate(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "opened"), 1.0F)
                .model(getExistingFile(modLoc("item/heavy_diamond_helmet_opened")))
                .end();
        trimmedArmorItem(ModItems.HEAVY_DIAMOND_CHESTPLATE, HEAVY_CHESTPLATE_DYE_OVERLAY);
        trimmedArmorItem(ModItems.HEAVY_DIAMOND_LEGGINGS, HEAVY_LEGGINGS_DYE_OVERLAY);
        trimmedArmorItem(ModItems.HEAVY_DIAMOND_BOOTS, HEAVY_BOOTS_DYE_OVERLAY);

        // Netherite Armor
        withExistingParent("heavy_netherite_helmet_opened", "item/generated")
                .texture("layer0", "luckysarmory:item/heavy_netherite_helmet_opened")
                .texture("layer1", "luckysarmory:item/heavy_netherite_helmet_opened_dye_overlay");
        withExistingParent("heavy_netherite_helmet", "item/generated")
                .texture("layer0", "luckysarmory:item/heavy_netherite_helmet")
                .texture("layer1", "luckysarmory:item/heavy_netherite_helmet_dye_overlay")
                .override()
                .predicate(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "opened"), 1.0F)
                .model(getExistingFile(modLoc("item/heavy_netherite_helmet_opened")))
                .end();
        trimmedArmorItem(ModItems.HEAVY_NETHERITE_CHESTPLATE, HEAVY_CHESTPLATE_DYE_OVERLAY);
        trimmedArmorItem(ModItems.HEAVY_NETHERITE_LEGGINGS, HEAVY_LEGGINGS_DYE_OVERLAY);
        trimmedArmorItem(ModItems.HEAVY_NETHERITE_BOOTS, HEAVY_BOOTS_DYE_OVERLAY);
    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(DeferredItem<HeavyArmorItem> itemDeferredItem, ResourceLocation overlayTexture) {
        final String MOD_ID = LuckysArmory.MOD_ID; // Change this to your mod id

        if(itemDeferredItem.get() instanceof HeavyArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", overlayTexture)
                        .texture("layer2", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                modLoc("item/" + itemName(itemDeferredItem.get())).toString())
                        .texture("layer1", overlayTexture);
            });
        }
    }

    private void registerDyedArmorItem(Item item, ResourceLocation overlayTexture) {
        withExistingParent(itemName(item), "item/generated")
                .texture("layer0", modLoc("item/" + itemName(item)).toString())
                .texture("layer1", overlayTexture);
    }

    private static String itemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }
}
