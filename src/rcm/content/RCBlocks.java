package rcm.content;

import mindustry.world.*;
import mindustry.world.blocks.environment.*;

public class RCBlocks{
    public static Block
    // cherry
    cherryGrass, cherrySand,
    
    cherryWall, cherryDune,
    
    cherryTush,

    // blueberry
    blueberrySnow, blueberryIce,
    
    blueberryWall;

    public static void load(){
        // cherry
        cherryGrass = new Floor("cherry-grass");

        cherrySand = new Floor("cherry-sand");

        cherryWall = new StaticWall("cherry-wall"){{
            variants = 3;
        }};

        cherryDune = new StaticWall("cherry-dune"){{
            variants = 3;
        }};

        cherryTush = new TreeBlock("cherry-tush");

        // blueberry
        blueberrySnow = new Floor("blueberry-snow");

        blueberryIce = new Floor("blueberry-ice"){{
            variants = 5;
        }};

        blueberryWall = new StaticWall("blueberry-wall"){{
            variants = 3;
        }};
    }
}
