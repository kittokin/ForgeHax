package com.matt.forgehax.mods;

import com.matt.forgehax.Helper;
import com.matt.forgehax.events.Render2DEvent;
import com.matt.forgehax.util.color.Colors;
import com.matt.forgehax.util.command.Setting;
import com.matt.forgehax.util.draw.SurfaceHelper;
import com.matt.forgehax.util.mod.Category;
import com.matt.forgehax.util.mod.ToggleMod;
import com.matt.forgehax.util.mod.loader.RegisterMod;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Babbaj on 10/28/2017.
 */
@RegisterMod
public class CompassMod extends ToggleMod {
  
  public final Setting<Double> scale =
      getCommandStub()
          .builders()
          .<Double>newSettingBuilder()
          .name("scale")
          .description("size of the compass")
          .defaultTo(3.D)
          .build();
  
  private static final double HALF_PI = Math.PI / 2;
  
  private enum Direction {
    N,
    W,
    S,
    E
  }
  
  public CompassMod() {
    super(Category.RENDER, "Compass", false, "cool compass overlay");
  }
  
  @SubscribeEvent
  public void onRender(Render2DEvent event) {
    final double centerX = event.getScreenWidth() / 2;
    final double centerY = event.getScreenHeight() * 0.8;
    
    for (Direction dir : Direction.values()) {
      double rad = getPosOnCompass(dir);
      SurfaceHelper.drawTextShadowCentered(
          dir.name(),
          (float) (centerX + getX(rad)),
          (float) (centerY + getY(rad)),
          dir == Direction.N ? Colors.RED.toBuffer() : Colors.WHITE.toBuffer());
      
    }
    
  }
  
  private double getX(double rad) {
    return Math.sin(rad) * (scale.getAsDouble() * 10);
  }
  
  private double getY(double rad) {
    final double epicPitch = MathHelper
<<<<<<< HEAD
<<<<<<< HEAD
        .clamp(Helper.getLocalPlayer().rotationPitch + 30f, -90f, 90f);
=======
        .clamp(Helper.getRenderEntity().rotationPitch + 30f, -90f, 90f);
>>>>>>> dfbf3717fcf8fca944a70062db7192a2f09f70d0
=======
        .clamp(Helper.getRenderEntity().rotationPitch + 30f, -90f, 90f);
>>>>>>> fr1kin-master
    final double pitchRadians = Math.toRadians(epicPitch); // player pitch
    return Math.cos(rad) * Math.sin(pitchRadians) * (scale.getAsDouble() * 10);
  }
  
  // return the position on the circle in radians
  private static double getPosOnCompass(Direction dir) {
    double yaw =
        Math.toRadians(
<<<<<<< HEAD
<<<<<<< HEAD
            MathHelper.wrapDegrees(Helper.getLocalPlayer().rotationYaw)); // player yaw
=======
            MathHelper.wrapDegrees(Helper.getRenderEntity().rotationYaw)); // player yaw
>>>>>>> dfbf3717fcf8fca944a70062db7192a2f09f70d0
    int index = dir.ordinal();
=======

>>>>>>> fr1kin-master
    return yaw + (index * HALF_PI);
  }
}
