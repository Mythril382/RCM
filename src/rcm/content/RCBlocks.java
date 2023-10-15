package rcm.content;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;

import static mindustry.type.ItemStack.*;

public class RCBlocks{
    public static Block
    cherryGrass, cherrySand, cherryWall, cherryDune, cherryTush,
    blueberrySnow, blueberryIce, blueberryWall,
    coreOutpost,
    transportDroneLoader, transportDroneUnloadPoint;

    public static void load(){
        cherryGrass = new Floor("cherry-grass");

        cherrySand = new Floor("cherry-sand");

        cherryWall = new StaticWall("cherry-wall"){{
            variants = 3;
        }};

        cherryDune = new StaticWall("cherry-dune"){{
            variants = 3;
        }};

        cherryTush = new TreeBlock("cherry-tush");

        blueberrySnow = new Floor("blueberry-snow");

        blueberryIce = new Floor("blueberry-ice"){{
            variants = 5;
        }};

        blueberryWall = new StaticWall("blueberry-wall"){{
            variants = 3;
        }};
        
        coreOutpost = new CoreBlock("core-outpost"){{
            requirements(Category.effect, with(RCItems.aluminium, 2000));
            health = 4000;
            armor = 3;
            size = 3;
            itemCapacity = 4000;
            unitCapModifier = 10;
            researchCostMultiplier = 0.075f;
            isFirstTier = true;
            unitType = UnitTypes.poly;
        }};
        
        transportDroneLoader = new UnitCargoLoader("transport-drone-loader"){{
            requirements(Category.distribution, with(RCItems.aluminium, 40));
            size = 2;
            itemCapacity = 50;
            buildTime = 30f;
            polyStroke = 1f;
            polySides = 3;
            polyRadius = 4f;
            polyRotateSpeed = 2f;
            polyColor = Color.valueOf("ff8c82");
            unitType = RCUnitTypes.transportDrone;
        }};
        
        transportDroneUnloadPoint = new UnitCargoUnloadPoint("transport-drone-unload-point"){{
            requirements(Category.distribution, with(RCItems.aluminium, 10));
            size = 1;
            itemCapacity = 25;
            staleItemDuration = 15f;
        }};
    }
}
