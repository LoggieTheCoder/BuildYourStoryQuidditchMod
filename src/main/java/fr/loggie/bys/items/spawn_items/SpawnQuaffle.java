package fr.loggie.bys.items.spawn_items;

import fr.loggie.bys.entities.EntityBall;
import fr.loggie.bys.entities.EntityGoldenSnitch;
import fr.loggie.bys.entities.EntityQuaffle;
import fr.loggie.bys.items.abstract_items.SpawnBall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class SpawnQuaffle extends SpawnBall {

    public SpawnQuaffle(String name, CreativeTabs creativeTab) {
        super(name, creativeTab);
    }

    @Override
    public EntityBall getBrutEntity(World world) {
        return new EntityQuaffle(world);
    }
}
