package fr.loggie.bys.entities.obj;

import fr.loggie.bys.entities.obj.model.AbstractObjModel;
import net.minecraft.util.ResourceLocation;



public class ObjModelLoader {

    /**
     * Loads Obj Model. For now Builder supports only triangular and square faces, so before loading model, be sure,
     * that you triangulated stuff like circles (for example in Blender).
     * <p>
     * If you want to rotate some parts of your model, you'll need to set rotation points.
     * This can be done by creating .rp file which name is equal to obj file name (you can see an example in "resources" package)
     * The instruction that explains how to create .rp file you can see in wiki page on github (look at @see)
     *
     * @param resourceLocation resourceLocation of obj file.
     * @return built Obj Model which you can use in Entity Models or TESRs.
     * @see <a href="https://github.com/TimeConqueror/Time-Core/wiki/.rp-File-Creation">https://github.com/TimeConqueror/Time-Core/wiki/.rp-File-Creation</a>
     */
    public static AbstractObjModel load(ResourceLocation resourceLocation) {
        return new ObjModelBuilder(resourceLocation).loadModel();
    }
}