package com.example.hotbarzoom.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Unique
    private static final float HOTBAR_SCALE = 2.0F;

    @Shadow
    private int scaledWidth;

    @Shadow
    private int scaledHeight;

    @Inject(method = "renderHotbar", at = @At("HEAD"))
    private void hotbarZoom$before(float tickDelta, GuiGraphics graphics, CallbackInfo ci) {
        int hotbarX = this.scaledWidth / 2 - 91;
        int hotbarY = this.scaledHeight - 22;

        graphics.getMatrices().push();
        graphics.getMatrices().translate(hotbarX, hotbarY, 0);
        graphics.getMatrices().scale(HOTBAR_SCALE, HOTBAR_SCALE, 1.0F);
        graphics.getMatrices().translate(-hotbarX, -hotbarY, 0);
    }

    @Inject(method = "renderHotbar", at = @At("RETURN"))
    private void hotbarZoom$after(float tickDelta, GuiGraphics graphics, CallbackInfo ci) {
        graphics.getMatrices().pop();
    }
}
