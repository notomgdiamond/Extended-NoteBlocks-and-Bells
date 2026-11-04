
package extendednoteblocks.mixin.client;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SoundEngine.class)
public abstract class SoundEngineMixin {

    @Redirect(
        method = "play",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/resources/sounds/Sound;getAttenuationDistance(F)F"
        )
    )
    private float extendednoteblocks$attenuationDistance(
            Sound sound,
            float volume,
            SoundInstance instance
    ) {
        String id = instance.getLocation().toString();

        if (id.startsWith("minecraft:block.note_block.")
                || id.startsWith("minecraft:block.bell.")) {
            return 200.0F;
        }

        return sound.getAttenuationDistance(volume);
    }
}
