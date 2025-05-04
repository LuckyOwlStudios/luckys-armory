package net.luckystudios;

import net.luckystudios.components.ModDataComponents;
import net.luckystudios.items.ModCreativeModeTabs;
import net.luckystudios.items.ModItems;
import net.luckystudios.items.armor.ModArmorMaterials;
import net.luckystudios.keybinds.HelmetTogglePacket;
import net.luckystudios.keybinds.ModKeybinds;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.HashMap;
import java.util.Map;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(LuckysArmory.MOD_ID)
public class LuckysArmory
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "luckysarmory";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    private static boolean networkingRegistered = false;
    private static final Map<CustomPacketPayload.Type<?>, NetworkMessage<?>> MESSAGES = new HashMap<>();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public LuckysArmory(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (LuckysArmory) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::registerNetworking);
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModDataComponents.register(modEventBus);

        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(ModCreativeModeTabs::addVanillaCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        NeoForge.EVENT_BUS.addListener(ModKeybinds::onClientTick);
    }

    private record NetworkMessage<T extends CustomPacketPayload>(StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
    }

    public static <T extends CustomPacketPayload> void addNetworkMessage(CustomPacketPayload.Type<T> id, StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
        if (networkingRegistered)
            throw new IllegalStateException("Cannot register new network messages after networking has been registered");
        MESSAGES.put(id, new NetworkMessage<>(reader, handler));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void registerNetworking(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(MOD_ID);
        MESSAGES.forEach((id, networkMessage) -> registrar.playBidirectional(id, ((NetworkMessage) networkMessage).reader(), ((NetworkMessage) networkMessage).handler()));
        networkingRegistered = true;
    }



    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LuckysArmory.addNetworkMessage(HelmetTogglePacket.TYPE, HelmetTogglePacket.STREAM_CODEC, HelmetTogglePacket::handleData);

        event.enqueueWork(() -> {
            // Register the cauldron interaction for dyeing armor items
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_IRON_HELMET.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_IRON_CHESTPLATE.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_IRON_LEGGINGS.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_IRON_BOOTS.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_GOLDEN_HELMET.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_GOLDEN_CHESTPLATE.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_GOLDEN_LEGGINGS.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_GOLDEN_BOOTS.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_DIAMOND_HELMET.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_DIAMOND_CHESTPLATE.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_DIAMOND_LEGGINGS.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_DIAMOND_BOOTS.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_NETHERITE_HELMET.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_NETHERITE_CHESTPLATE.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_NETHERITE_LEGGINGS.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
            CauldronInteraction.WATER.map().put(ModItems.HEAVY_NETHERITE_BOOTS.get(), ModArmorMaterials.DYED_ARMOR_ITEM);
        });
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }
}
