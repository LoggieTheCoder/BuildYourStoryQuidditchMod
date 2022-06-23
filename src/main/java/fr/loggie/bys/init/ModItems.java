package fr.loggie.bys.init;

import com.google.common.collect.Lists;

import fr.loggie.bys.BYS;
import fr.loggie.bys.entities.EntityFlyingBroom;
import fr.loggie.bys.items.abstract_items.BYSItem;
import fr.loggie.bys.items.public_items.BeaterItem;
import fr.loggie.bys.items.public_items.ItemQuidditchGuide;
import fr.loggie.bys.items.spawn_items.*;
import fr.loggie.bys.items.spawn_items.spawn_brooms.*;
import fr.loggie.bys.utils.References;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ModItems {
    public static final ModItems INSTANCE = new ModItems();

    public static Item QUAFFLE_ITEM;
    public static Item GOLDEN_SNITCH_ITEM;
    public static Item BLUDGER_ITEM;
    public static Item BEATER;
    public static Item QUIDDITCH_GUIDE;
    public static Item NET;
    public static Item MAGIC_QUIDDITCH_WAND;

    public static Item BLUE_BOMB ;
    public static Item YAJIRUSHI_ITEM;
    public static Item FIREBOLT_SUPREME_ITEM;
    public static Item VARAPIDOS_ITEM;
    public static Item BROSSDUR_1_ITEM;

    public static Item BROSSDUR_3_ITEM;

    public static Item BROSSDUR_5_ITEM;
    public static Item BROSSDUR_6_ITEM;
    public static Item BROSSDUR_7_ITEM;
    public static Item BROSSDUR_11_ITEM;
    public static Item COMETE_140_ITEM;
    public static Item COMETE_180_ITEM;
    public static Item COMETE_260_ITEM;
    public static Item COMETE_290_ITEM;
    public static Item FIREBOLT_ITEM;
    public static Item BALETOILE_XXI;
    public static Item FRISELUNE_ITEM;
    public static Item NIMBUS_1000_ITEM;
    public static Item NIMBUS_1001_ITEM;
    public static Item NIMBUS_1500_ITEM;
    public static Item NIMBUS_1700_ITEM;
    public static Item NIMBUS_2000_ITEM;
    public static Item NIMBUS_2001_ITEM;
    public static Item LANCECHENE_79_ITEM;
    public static Item ETOILE_FILANTE_ITEM;
    public static Item STEEL_ARROW_BROOM_ITEM;
    public static Item MANCHEVIF_ITEM;
    public static Item FIRETOPOWDERS_ITEM;
    public static Item MARGOTIN_90_ITEM;

    private List<Item> items;
    public void init(){
        items = Lists.newArrayList();

        BEATER = new BeaterItem("batte",BYS.QUIDDITCH_TAB);
        NET = new BYSItem("net",BYS.QUIDDITCH_TAB,1);
        QUAFFLE_ITEM = new SpawnQuaffle("quaffle_item", BYS.QUIDDITCH_TAB);
        GOLDEN_SNITCH_ITEM = new SpawnGoldenSnitch("golden_snitch_item",BYS.QUIDDITCH_TAB);
        BLUDGER_ITEM = new SpawnBluger("bludger_item",BYS.QUIDDITCH_TAB);
        QUIDDITCH_GUIDE = new ItemQuidditchGuide("quidditch_guide",BYS.QUIDDITCH_TAB);
        MAGIC_QUIDDITCH_WAND = new MagicQuiddichWand("magic_quidditch_wand",BYS.QUIDDITCH_TAB);
        //BROOM SPAWN ITEMS
        FIREBOLT_SUPREME_ITEM = new SpawnFireboltSupreme(EntityFlyingBroom.FlyingBrooms.FIREBOLT_SUPREME.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        YAJIRUSHI_ITEM = new SpawnYajirushi(EntityFlyingBroom.FlyingBrooms.YAJIRUSHI.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        BALETOILE_XXI = new SpawnBaletoileXXI(EntityFlyingBroom.FlyingBrooms.BALETOILE_XXI.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        BLUE_BOMB  =new SpawnBlueBomb(EntityFlyingBroom.FlyingBrooms.BLUE_BOMB.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        VARAPIDOS_ITEM=new SpawnVarapidos(EntityFlyingBroom.FlyingBrooms.VARAPIDOS.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        BROSSDUR_1_ITEM=new SpawnBrossdur1(EntityFlyingBroom.FlyingBrooms.BROSSDUR_1.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        BROSSDUR_3_ITEM=new SpawnBrossdur3(EntityFlyingBroom.FlyingBrooms.BROSSDUR_3.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        BROSSDUR_5_ITEM=new SpawnBrossdur5(EntityFlyingBroom.FlyingBrooms.BROSSDUR_5.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        BROSSDUR_6_ITEM=new SpawnBrossdur6(EntityFlyingBroom.FlyingBrooms.BROSSDUR_6.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        BROSSDUR_7_ITEM=new SpawnBrossdur7(EntityFlyingBroom.FlyingBrooms.BROSSDUR_7.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        BROSSDUR_11_ITEM=new SpawnBrossdur11(EntityFlyingBroom.FlyingBrooms.BROSSDUR_11.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        COMETE_140_ITEM=new SpawnComete140(EntityFlyingBroom.FlyingBrooms.COMETE_140.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        COMETE_180_ITEM=new SpawnComete180(EntityFlyingBroom.FlyingBrooms.COMETE_180.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        COMETE_260_ITEM=new SpawnComete260(EntityFlyingBroom.FlyingBrooms.COMETE_260.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        COMETE_290_ITEM=new SpawnComete290(EntityFlyingBroom.FlyingBrooms.COMETE_290.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        FIREBOLT_ITEM=new SpawnFirebolt(EntityFlyingBroom.FlyingBrooms.FIREBOLT.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        FRISELUNE_ITEM=new SpawnFriselune(EntityFlyingBroom.FlyingBrooms.FRISELUNE.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        NIMBUS_1000_ITEM=new SpawnNimbus1000(EntityFlyingBroom.FlyingBrooms.NIMBUS_1000.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        NIMBUS_1001_ITEM=new SpawnNimbus1001(EntityFlyingBroom.FlyingBrooms.NIMBUS_1001.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        NIMBUS_1500_ITEM=new SpawnNimbus1500(EntityFlyingBroom.FlyingBrooms.NIMBUS_1500.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        NIMBUS_1700_ITEM=new SpawnNimbus1700(EntityFlyingBroom.FlyingBrooms.NIMBUS_1700.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        NIMBUS_2000_ITEM=new SpawnNimbus2000(EntityFlyingBroom.FlyingBrooms.NIMBUS_2000.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        NIMBUS_2001_ITEM=new SpawnNimbus2001(EntityFlyingBroom.FlyingBrooms.NIMBUS_2001.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        LANCECHENE_79_ITEM=new SpawnLancechene79(EntityFlyingBroom.FlyingBrooms.LANCECHENE_79.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        ETOILE_FILANTE_ITEM=new SpawnEtoileFilante(EntityFlyingBroom.FlyingBrooms.ETOILE_FILANTE.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        STEEL_ARROW_BROOM_ITEM=new SpawnSteelArrowBroom(EntityFlyingBroom.FlyingBrooms.STEEL_ARROW_BROOM.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        MANCHEVIF_ITEM=new SpawnManchevif(EntityFlyingBroom.FlyingBrooms.MANCHEVIF.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        FIRETOPOWDERS_ITEM=new SpawnFiretopowders(EntityFlyingBroom.FlyingBrooms.FIRETOPOWDERS.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
        MARGOTIN_90_ITEM=new SpawnMargotin90(EntityFlyingBroom.FlyingBrooms.MARGOTIN_90.getCustomName()+"_item",BYS.QUIDDITCH_TAB);
    }
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent e){
        for(Item item:items){
            registerModel(item);
        }
    }
    private void registerModel(Item item){

        ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(new ResourceLocation(References.MODID,item.getUnlocalizedName().substring(5)),"inventory"));

    }

    public List<Item> getItems() {
        return items;
    }
}
