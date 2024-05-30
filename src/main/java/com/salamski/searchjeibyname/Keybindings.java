package com.salamski.searchjeibyname;
import org.lwjgl.glfw.GLFW;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public final class Keybindings {
  public static final Keybindings INSTANCE = new Keybindings();

  private Keybindings() {
  }

  public final KeyMapping searchKey = new KeyMapping(
      "key." + SearchJeiByName.MODID + ".searchKey",
      KeyConflictContext.GUI, 
      InputConstants.Type.KEYSYM, 
      GLFW.GLFW_KEY_I, 
      "key.categories." + SearchJeiByName.MODID);
}
