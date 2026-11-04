package extendednoteblocks.mixin;

import net.minecraft.sounds.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoundEvent.class)
public abstract class ExampleMixin {

    private static final float TARGET_RANGE = 200.0F;

    @Inject(
        method = "getRange(F)F",
        at = @At("HEAD"),
        cancellable = true
    )
    private void extendednoteblocks$extendRange(
            float volume,
            CallbackInfoReturnable<Float> cir
    ) {
        SoundEvent event = (SoundEvent) (Object) this;

        String soundId = event.location().toString();

        if (soundId.startsWith("minecraft:block.note_block.")
                || soundId.startsWith("minecraft:block.bell.")) {

            cir.setReturnValue(TARGET_RANGE);
        }
    }
}
