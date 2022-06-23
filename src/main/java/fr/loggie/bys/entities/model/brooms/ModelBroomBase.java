package fr.loggie.bys.entities.model.brooms;


import fr.loggie.bys.utils.References;
import net.minecraft.util.ResourceLocation;

import javax.jws.WebParam;


public class ModelBroomBase extends ModelObjBroom {
    public ModelBroomBase(String un) {
        super(new ResourceLocation(References.MODID, "models/entity/"+un+".obj"),1);
    }
    public ModelBroomBase(String un,double size){super(new ResourceLocation(References.MODID, "models/entity/"+un+".obj"),size);}
}