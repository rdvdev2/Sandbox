package com.hrznstudio.sandbox.fabric;

import com.hrznstudio.sandbox.api.*;
import com.hrznstudio.sandbox.api.addon.AddonInfo;
import com.hrznstudio.sandbox.util.Log;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class Sandbox implements ModInitializer, ISandbox {
    public static Sandbox SANDBOX;

    private static List<AddonInfo> ADDONS = new ArrayList<>();

    public Sandbox() {
        SANDBOX = this;
    }

    public static boolean setup() {
        Log.info("Setting up Sandbox environment");
        ScriptEngine.init(SANDBOX);
        ADDONS = SandboxLoader.locateAddons(SandboxLocation.ADDONS);
        Block block = new SlabBlock(Block.Settings.of(Material.METAL));
        Registry.register(Registry.BLOCK, new Identifier("sandbox", "test_block"), block);
        BlockItem item = new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
        item.appendBlocks(Item.BLOCK_ITEMS, item);
        Registry.register(Registry.ITEM, new Identifier("sandbox", "test_block"), item);
        block.getStateFactory().getStates().forEach(Block.STATE_IDS::add);
        MinecraftClient.getInstance().reloadResourcesConcurrently();
        return true;
    }

    public static void shutdown() {
        ((SandboxRegistry) Registry.BLOCK).remove(new Identifier("sandbox", "test_block"));
        ((SandboxRegistry) Registry.ITEM).remove(new Identifier("sandbox", "test_block"));
    }

    @Override
    public void onInitialize() {

    }

    @Override
    public Side getSide() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT ? Side.CLIENT : Side.SERVER;
    }
}