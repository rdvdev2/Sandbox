package com.hrznstudio.sandbox.api;

import net.minecraft.util.Identifier;

public interface SandboxRegistry<T> {

    void register(Identifier identifier, T object);

    T remove(Identifier identifier);
}