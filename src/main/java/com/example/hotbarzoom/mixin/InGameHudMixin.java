package com.example.hotbarzoom.mixin;

import net.minecraft.client.gui.DrawContext;
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
    private int screenWidth;

    @Shadow
    private int screenHeight;

    @Inject(method = "renderHotbar", at = @At("HEAD"))
    private void hotbarZoom$before(DrawContext context, CallbackInfo ci) {
        int hotbarX = this.screenWidth / 2 - 91;
        int hotbarY = this.screenHeight - 22;

        context.getMatrices().push();
        context.getMatrices().translate(hotbarX, hotbarY, 0);
        context.getMatrices().scale(HOTBAR_SCALE, HOTBAR_SCALE, 1.0F);
        context.getMatrices().translate(-hotbarX, -hotbarY, 0);
    }

    @Inject(method = "renderHotbar", at = @At("RETURN"))
    private void hotbarZoom$after(DrawContext context, CallbackInfo ci) {
        context.getMatrices().pop();
    }
}
