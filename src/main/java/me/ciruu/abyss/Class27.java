package me.ciruu.abyss;

import me.ciruu.abyss.events.MinecraftEvent;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class Class27
extends MinecraftEvent {
    private final Entity entity;
    private final World world;

    public Class27(Entity entity, World world) {
        this.entity = entity;
        this.world = world;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public World getWorld() {
        return this.world;
    }
}
