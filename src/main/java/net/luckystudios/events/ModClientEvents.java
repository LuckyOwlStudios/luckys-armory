package net.luckystudios.events;

import net.luckystudios.LuckysArmory;
import net.luckystudios.MyClientModEvents;
import net.luckystudios.components.ModDataComponents;
import net.luckystudios.items.ModItems;
import net.luckystudios.items.armor.HeavyArmorItem;
import net.luckystudios.items.armor.models.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;

@EventBusSubscriber(modid = LuckysArmory.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        NeoForge.EVENT_BUS.addListener(MyClientModEvents::renderHelmetOverlay);

        // So the items update when the player is holding them
        registerHelmetPredicate(ModItems.HEAVY_IRON_HELMET.get());
        registerHelmetPredicate(ModItems.HEAVY_GOLDEN_HELMET.get());
        registerHelmetPredicate(ModItems.HEAVY_DIAMOND_HELMET.get());
        registerHelmetPredicate(ModItems.HEAVY_NETHERITE_HELMET.get());
    }

    // This is so that the item model in hand cab be changed by the value of its component
    private static void registerHelmetPredicate(Item heavyHelmetItem) {
        ItemProperties.register(
                heavyHelmetItem,
                ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "opened"),
                (stack, clientLevel, livingEntity, num) -> HeavyArmorItem.getFullnessDisplay(stack)
        );
    }

    // Copied from ItemColors.class
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        renderItemColor(event, DyedItemColor.LEATHER_COLOR, ModItems.HEAVY_IRON_HELMET, ModItems.HEAVY_IRON_CHESTPLATE, ModItems.HEAVY_IRON_LEGGINGS, ModItems.HEAVY_IRON_BOOTS);
        renderItemColor(event, DyeColor.RED.getTextureDiffuseColor(), ModItems.HEAVY_GOLDEN_HELMET, ModItems.HEAVY_GOLDEN_CHESTPLATE, ModItems.HEAVY_GOLDEN_LEGGINGS, ModItems.HEAVY_GOLDEN_BOOTS);
        renderItemColor(event, DyeColor.PURPLE.getTextureDiffuseColor(), ModItems.HEAVY_DIAMOND_HELMET, ModItems.HEAVY_DIAMOND_CHESTPLATE, ModItems.HEAVY_DIAMOND_LEGGINGS, ModItems.HEAVY_DIAMOND_BOOTS);
        renderItemColor(event, DyeColor.BLACK.getTextureDiffuseColor(), ModItems.HEAVY_NETHERITE_HELMET, ModItems.HEAVY_NETHERITE_CHESTPLATE, ModItems.HEAVY_NETHERITE_LEGGINGS, ModItems.HEAVY_NETHERITE_BOOTS);
    }

    // This is for the dyeable items that have a default value. Also the default value is boosted by 20% to make it appear more vibrant
    private static void renderItemColor(RegisterColorHandlersEvent.Item event, int color, ItemLike... items) {
        event.register(
                (stack, layer) -> {
                    if (layer != 1) return -1;

                    DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
                    if (dyedColor == null) return color;

                    int rgb = dyedColor.rgb();
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;

                    // Boost the vibrancy (increase each channel by 20%, capped at 255)
                    r = Math.min((int)(r * 1.2f), 255);
                    g = Math.min((int)(g * 1.2f), 255);
                    b = Math.min((int)(b * 1.2f), 255);

                    int boostedRgb = (r << 16) | (g << 8) | b;

                    return FastColor.ARGB32.opaque(boostedRgb);
                },
                items
        );
    }

    @SubscribeEvent
    public static void registerItemExtensions(RegisterClientExtensionsEvent event) {

        registerArmorModel(event, ModItems.LIGHT_IRON_CHESTPLATE.get(), LightChestplate.LAYER_LOCATION);
        registerArmorModel(event, ModItems.LIGHT_GOLDEN_CHESTPLATE.get(), LightChestplate.LAYER_LOCATION);
        registerArmorModel(event, ModItems.LIGHT_DIAMOND_CHESTPLATE.get(), LightChestplate.LAYER_LOCATION);
        registerArmorModel(event, ModItems.LIGHT_NETHERITE_CHESTPLATE.get(), LightChestplate.LAYER_LOCATION);

        registerHeavyIronHelmetModel(event, ModItems.HEAVY_IRON_HELMET.get(), DyeColor.WHITE.getTextureDiffuseColor());
        registerDyedArmorModel(event, ModItems.HEAVY_IRON_CHESTPLATE.get(), HeavyChestplate.LAYER_LOCATION, DyedItemColor.LEATHER_COLOR);
        registerDyedArmorModel(event, ModItems.HEAVY_IRON_LEGGINGS.get(), HeavyLeggings.LAYER_LOCATION, DyedItemColor.LEATHER_COLOR);
        registerDyedArmorModel(event, ModItems.HEAVY_IRON_BOOTS.get(), HeavyBoots.LAYER_LOCATION, DyedItemColor.LEATHER_COLOR);

        registerHeavyGoldenHelmetModel(event, ModItems.HEAVY_GOLDEN_HELMET.get(), DyeColor.RED.getTextureDiffuseColor());
        registerDyedArmorModel(event, ModItems.HEAVY_GOLDEN_CHESTPLATE.get(), HeavyChestplate.LAYER_LOCATION, DyeColor.RED.getTextureDiffuseColor());
        registerDyedArmorModel(event, ModItems.HEAVY_GOLDEN_LEGGINGS.get(), HeavyLeggings.LAYER_LOCATION, DyeColor.RED.getTextureDiffuseColor());
        registerDyedArmorModel(event, ModItems.HEAVY_GOLDEN_BOOTS.get(), HeavyBoots.LAYER_LOCATION, DyeColor.RED.getTextureDiffuseColor());

        registerHeavyDiamondHelmetModel(event, ModItems.HEAVY_DIAMOND_HELMET.get(), DyeColor.PURPLE.getTextureDiffuseColor());
        registerDyedArmorModel(event, ModItems.HEAVY_DIAMOND_CHESTPLATE.get(), HeavyChestplate.LAYER_LOCATION, DyeColor.PURPLE.getTextureDiffuseColor());
        registerDyedArmorModel(event, ModItems.HEAVY_DIAMOND_LEGGINGS.get(), HeavyLeggings.LAYER_LOCATION, DyeColor.PURPLE.getTextureDiffuseColor());
        registerDyedArmorModel(event, ModItems.HEAVY_DIAMOND_BOOTS.get(), HeavyBoots.LAYER_LOCATION, DyeColor.PURPLE.getTextureDiffuseColor());

        registerHeavyNetheriteHelmetModel(event, ModItems.HEAVY_NETHERITE_HELMET.get(), DyeColor.BLACK.getTextureDiffuseColor());
        registerDyedArmorModel(event, ModItems.HEAVY_NETHERITE_CHESTPLATE.get(), HeavyChestplate.LAYER_LOCATION, DyeColor.BLACK.getTextureDiffuseColor());
        registerDyedArmorModel(event, ModItems.HEAVY_NETHERITE_LEGGINGS.get(), HeavyLeggings.LAYER_LOCATION, DyeColor.BLACK.getTextureDiffuseColor());
        registerDyedArmorModel(event, ModItems.HEAVY_NETHERITE_BOOTS.get(), HeavyBoots.LAYER_LOCATION, DyeColor.BLACK.getTextureDiffuseColor());
    }

    private static void registerArmorModel(RegisterClientExtensionsEvent event, Item item, ModelLayerLocation layer) {
        event.registerItem(new IClientItemExtensions() {

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                ModelPart part = Minecraft.getInstance().getEntityModels().bakeLayer(layer);
                return new HumanoidModel<>(part);
            }
        }, item);
    }

    private static void registerDyedArmorModel(RegisterClientExtensionsEvent event, Item item, ModelLayerLocation layer, int defaultColor) {
        event.registerItem(new IClientItemExtensions() {

            @Override
            @OnlyIn(Dist.CLIENT)
            public int getDefaultDyeColor(ItemStack stack) {
                return stack.is(ItemTags.DYEABLE) ? FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, defaultColor)) : 0xFFFFFFFF;
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                ModelPart part = Minecraft.getInstance().getEntityModels().bakeLayer(layer);
                return new HumanoidModel<>(part);
            }
        }, item);
    }

    private static void registerHeavyIronHelmetModel(RegisterClientExtensionsEvent event, Item item, int defaultColor) {
        event.registerItem(new IClientItemExtensions() {

            @Override
            @OnlyIn(Dist.CLIENT)
            public void setupModelAnimations(LivingEntity entity, ItemStack stack, EquipmentSlot slot, Model model, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
                if (model instanceof HeavyIronHelmet heavyIronHelmet) {
                    boolean isOpened = Boolean.TRUE.equals(stack.get(ModDataComponents.OPENED));
                    heavyIronHelmet.getHead().getChild("cap_hinge").y = isOpened ? -9.0F : -4.0F;
                    heavyIronHelmet.getHead().getChild("cap_hinge").xRot = isOpened ? -0.5F : 0.0F;
                }
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            public int getDefaultDyeColor(ItemStack stack) {
                return stack.is(ItemTags.DYEABLE) ? FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, defaultColor)) : 0xFFFFFFFF;
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                ModelPart part = Minecraft.getInstance().getEntityModels().bakeLayer(HeavyIronHelmet.LAYER_LOCATION);
                return new HeavyIronHelmet<>(part);
            }
        }, item);
    }

    private static void registerHeavyGoldenHelmetModel(RegisterClientExtensionsEvent event, Item item, int defaultColor) {
        event.registerItem(new IClientItemExtensions() {

            @Override
            @OnlyIn(Dist.CLIENT)
            public void setupModelAnimations(LivingEntity entity, ItemStack stack, EquipmentSlot slot, Model model, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
                if (model instanceof HeavyGoldenHelmet heavyGoldenHelmet) {
                    boolean isOpened = Boolean.TRUE.equals(stack.get(ModDataComponents.OPENED));
                    heavyGoldenHelmet.getHead().getChild("cap_hinge").y = isOpened ? -9.0F : -4.0F;
                    heavyGoldenHelmet.getHead().getChild("cap_hinge").xRot = isOpened ? -0.5F : 0.0F;
                }
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            public int getDefaultDyeColor(ItemStack stack) {
                return stack.is(ItemTags.DYEABLE) ? FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, defaultColor)) : 0xFFFFFFFF;
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                ModelPart part = Minecraft.getInstance().getEntityModels().bakeLayer(HeavyGoldenHelmet.LAYER_LOCATION);
                return new HeavyGoldenHelmet<>(part);
            }
        }, item);
    }

    private static void registerHeavyDiamondHelmetModel(RegisterClientExtensionsEvent event, Item item, int defaultColor) {
        event.registerItem(new IClientItemExtensions() {

            @Override
            @OnlyIn(Dist.CLIENT)
            public void setupModelAnimations(LivingEntity entity, ItemStack stack, EquipmentSlot slot, Model model, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
                if (model instanceof HeavyDiamondHelmet heavyDiamondHelmet) {
                    boolean isOpened = Boolean.TRUE.equals(stack.get(ModDataComponents.OPENED));
                    heavyDiamondHelmet.getHead().getChild("offset").y = isOpened ? -6.0F : 0.0F;
                    heavyDiamondHelmet.getHead().getChild("offset").xRot = isOpened ? -0.5F : 0.0F;
                }
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            public int getDefaultDyeColor(ItemStack stack) {
                return stack.is(ItemTags.DYEABLE) ? FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, defaultColor)) : 0xFFFFFFFF;
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                ModelPart part = Minecraft.getInstance().getEntityModels().bakeLayer(HeavyDiamondHelmet.LAYER_LOCATION);
                return new HeavyDiamondHelmet<>(part);
            }
        }, item);
    }

    private static void registerHeavyNetheriteHelmetModel(RegisterClientExtensionsEvent event, Item item, int defaultColor) {
        event.registerItem(new IClientItemExtensions() {

            @Override
            @OnlyIn(Dist.CLIENT)
            public int getDefaultDyeColor(ItemStack stack) {
                return stack.is(ItemTags.DYEABLE) ? FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, defaultColor)) : 0xFFFFFFFF;
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                ModelPart part = Minecraft.getInstance().getEntityModels().bakeLayer(HeavyNetheriteHelmet.LAYER_LOCATION);
                return new HeavyNetheriteHelmet<>(part);
            }
        }, item);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LightChestplate.LAYER_LOCATION, LightChestplate::createBodyLayer);
        event.registerLayerDefinition(HeavyIronHelmet.LAYER_LOCATION, HeavyIronHelmet::createBodyLayer);
        event.registerLayerDefinition(HeavyGoldenHelmet.LAYER_LOCATION, HeavyGoldenHelmet::createBodyLayer);
        event.registerLayerDefinition(HeavyDiamondHelmet.LAYER_LOCATION, HeavyDiamondHelmet::createBodyLayer);
        event.registerLayerDefinition(HeavyNetheriteHelmet.LAYER_LOCATION, HeavyNetheriteHelmet::createBodyLayer);
        event.registerLayerDefinition(HeavyChestplate.LAYER_LOCATION, HeavyChestplate::createBodyLayer);
        event.registerLayerDefinition(HeavyLeggings.LAYER_LOCATION, HeavyLeggings::createBodyLayer);
        event.registerLayerDefinition(HeavyBoots.LAYER_LOCATION, HeavyBoots::createBodyLayer);
    }
}
