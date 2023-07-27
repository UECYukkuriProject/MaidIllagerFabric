package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.render.entity.IllusionerEntityRenderer;
import net.minecraft.entity.mob.IllusionerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.yokohama_miyazawa.maidillager.MaidIllager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
@Mixin(IllusionerEntityRenderer.class)
public class MixinIllusionerRenderer {
    private static final Identifier ILLUSIONER = new Identifier(MaidIllager.MODID + ":textures/entity/maid_illusioner.png");

    @Inject(method = "getTexture", at = @At("RETURN"), cancellable = true)
    public void onGetTexture(IllusionerEntity entity, CallbackInfoReturnable<Identifier> cir){
        cir.setReturnValue(ILLUSIONER);
    }
}