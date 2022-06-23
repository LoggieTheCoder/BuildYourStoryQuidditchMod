package fr.loggie.bys.items.spawn_items;

import fr.loggie.bys.entities.EntityBall;
import fr.loggie.bys.entities.EntityGoldenSnitch;
import fr.loggie.bys.items.abstract_items.SpawnBall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnGoldenSnitch extends SpawnBall {

    public SpawnGoldenSnitch(String name, CreativeTabs creativeTab) {
        super(name, creativeTab);
    }

    @Override
    public EntityBall getBrutEntity(World world) {
        return new EntityGoldenSnitch(world);
    }
}
