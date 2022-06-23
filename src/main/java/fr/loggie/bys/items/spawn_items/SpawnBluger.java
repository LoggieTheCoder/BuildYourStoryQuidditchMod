package fr.loggie.bys.items.spawn_items;

import fr.loggie.bys.entities.EntityBall;
import fr.loggie.bys.entities.EntityBludger;
import fr.loggie.bys.items.abstract_items.SpawnBall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class SpawnBluger extends SpawnBall {

    public SpawnBluger(String name, CreativeTabs creativeTab) {
        super(name, creativeTab);
    }

    @Override
    public EntityBall getBrutEntity(World world) {
        return new EntityBludger(world);
    }


}
