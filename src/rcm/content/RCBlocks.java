package rcm.content;

import mindustry.world.*;
import mindustry.world.blocks.environment.*;

public class RCBlocks{
    public static Block
    cherryGrass,
    cherrySand,
    
    cherryWall,
    cherryDune,
    
    cherryTush;

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
    }
}
