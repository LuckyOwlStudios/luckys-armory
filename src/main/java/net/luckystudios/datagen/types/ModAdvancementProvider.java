package net.luckystudios.datagen.types;

import net.luckystudios.LuckysArmory;
import net.luckystudios.items.ModItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class ModAdvancementProvider implements AdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
        AdvancementHolder heavy_iron_armor = Advancement.Builder.advancement()
                .parent(VANILLA_OBTAIN_ARMOR)
                .display(ModItems.HEAVY_IRON_CHESTPLATE,
                        Component.translatable("advancement.luckysarmory.heavy_iron_armor.title"),
                        Component.translatable("advancement.luckysarmory.heavy_iron_armor.description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementType.TASK, true, true, false)
                .addCriterion(
                        "heavy_iron_armor",
                        InventoryChangeTrigger.TriggerInstance.hasItems(
                                ModItems.HEAVY_IRON_HELMET, ModItems.HEAVY_IRON_CHESTPLATE, ModItems.HEAVY_IRON_LEGGINGS, ModItems.HEAVY_IRON_BOOTS
                        )
                )
                .rewards(AdvancementRewards.Builder.experience(10))
                .save(consumer, getNameId("heavy_iron_armor"));

        AdvancementHolder heavy_netherite_armor = Advancement.Builder.advancement()
                .parent(VANILLA_NETHERITE_ARMOR)
                .display(ModItems.HEAVY_NETHERITE_CHESTPLATE,
                        Component.translatable("advancement.luckysarmory.heavy_netherite_armor.title"),
                        Component.translatable("advancement.luckysarmory.heavy_netherite_armor.description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementType.CHALLENGE, true, true, false)
                .addCriterion(
                        "heavy_netherite_armor",
                        InventoryChangeTrigger.TriggerInstance.hasItems(
                                ModItems.HEAVY_NETHERITE_HELMET, ModItems.HEAVY_NETHERITE_CHESTPLATE, ModItems.HEAVY_NETHERITE_LEGGINGS, ModItems.HEAVY_NETHERITE_BOOTS
                        )
                )
                .rewards(AdvancementRewards.Builder.experience(200))
                .save(consumer, getNameId("heavy_netherite_armor"));
    }

    AdvancementHolder VANILLA_OBTAIN_ARMOR = new AdvancementHolder(
            ResourceLocation.withDefaultNamespace("story/obtain_armor"),
            null // you can pass `null` because it's not used in this context
    );

    AdvancementHolder VANILLA_NETHERITE_ARMOR = new AdvancementHolder(
            ResourceLocation.withDefaultNamespace("nether/netherite_armor"),
            null // you can pass `null` because it's not used in this context
    );

    protected static Advancement.Builder getAdvancement(AdvancementHolder parent, ItemLike display, String name, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(display,
                Component.translatable("advancement." + name),
                Component.translatable("advancement." + name + ".desc"),
                null, frame, showToast, announceToChat, hidden);
    }

    private String getNameId(String id) {
        return LuckysArmory.MOD_ID + ":" + id;
    }
}