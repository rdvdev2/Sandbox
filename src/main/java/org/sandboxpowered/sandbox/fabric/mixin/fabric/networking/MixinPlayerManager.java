package org.sandboxpowered.sandbox.fabric.mixin.fabric.networking;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.sandboxpowered.sandbox.fabric.network.NetworkManager;
import org.sandboxpowered.sandbox.fabric.server.SandboxServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class MixinPlayerManager {

    @Inject(method = "onPlayerConnect", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;sendWorldInfo(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/server/world/ServerWorld;)V"), cancellable = true)
    public void tick(ClientConnection clientConnection_1, ServerPlayerEntity serverPlayerEntity_1, CallbackInfo info) {
        NetworkManager.sendTo(SandboxServer.INSTANCE.createAddonSyncPacket(), serverPlayerEntity_1);
    }
}
