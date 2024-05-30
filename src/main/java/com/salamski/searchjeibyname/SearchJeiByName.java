package com.salamski.searchjeibyname;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(SearchJeiByName.MODID)
@JeiPlugin
public class SearchJeiByName implements IModPlugin {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "searchjeibyname";
    private static final ResourceLocation UID = new ResourceLocation(SearchJeiByName.MODID, "jei_plugin");

    public SearchJeiByName() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        ClientForgeHandler.setJeiRuntime(jeiRuntime);
    }
}
