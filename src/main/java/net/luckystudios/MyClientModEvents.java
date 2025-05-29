package net.luckystudios;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.luckystudios.components.ModDataComponents;
import net.luckystudios.items.ModItems;
import net.luckystudios.keybinds.TestModKeyMappings;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

import java.awt.*;
import java.util.Map;

public class MyClientModEvents {

    private static final Map<Item, ResourceLocation> HELMET_OVERLAYS = Map.of(
            ModItems.HEAVY_IRON_HELMET.get(), ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/misc/iron_helmet_overlay.png"),
            ModItems.HEAVY_GOLDEN_HELMET.get(), ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/misc/golden_helmet_overlay.png"),
            ModItems.HEAVY_DIAMOND_HELMET.get(), ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/misc/diamond_helmet_overlay.png"),
            ModItems.HEAVY_NETHERITE_HELMET.get(), ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/misc/netherite_helmet_overlay.png")
    );

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void renderHelmetOverlay(RenderGuiEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        // Only render in first person
        if (player == null || mc.options.getCameraType() != CameraType.FIRST_PERSON) return;

        ItemStack helmetStack = player.getItemBySlot(EquipmentSlot.HEAD);
        if (helmetStack.isEmpty()) return;

        Item helmetItem = helmetStack.getItem();
        // Only continue if the item is one of the heavy helmets (has the OPENED component)
        if (!helmetStack.has(ModDataComponents.OPENED)) return;
        int screenWidth = event.getGuiGraphics().guiWidth();
        int screenHeight = event.getGuiGraphics().guiHeight();

        // 🔸 Only draw the overlay mask if OPENED is FALSE
        if (!Boolean.TRUE.equals(helmetStack.get(ModDataComponents.OPENED))) {

            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.blendFuncSeparate(
                    GlStateManager.SourceFactor.SRC_ALPHA,
                    GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                    GlStateManager.SourceFactor.ONE,
                    GlStateManager.DestFactor.ZERO
            );
            RenderSystem.setShaderColor(1, 1, 1, 1);

            if (getHelmetOverlayTexture(helmetItem) == null) return;

            event.getGuiGraphics().blit(
                    getHelmetOverlayTexture(helmetItem),
                    0, 0,
                    0.0F, 0.0F,
                    screenWidth, screenHeight,
                    screenWidth, screenHeight
            );

            RenderSystem.depthMask(true);
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(1, 1, 1, 1);
        }

        // 🔸 Draw icon and keybinding hint (always shown if it's a heavy helmet)
        String message = "[" + TestModKeyMappings.getKeyName(TestModKeyMappings.TOGGLE_HELMET) + "]";
        int y = screenHeight - 15;
        int textX = (screenWidth / 2) + 120;
        int iconX = textX - 20;
        int iconY = y - 4;

        event.getGuiGraphics().renderItem(helmetStack, iconX, iconY);
        event.getGuiGraphics().drawString(
                mc.font,
                message,
                textX,
                y,
                Color.ORANGE.getRGB()
        );
    }

    private static ResourceLocation getHelmetOverlayTexture(Item helmet) {
        return HELMET_OVERLAYS.get(helmet);
    }
}
