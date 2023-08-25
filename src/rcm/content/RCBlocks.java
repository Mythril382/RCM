package rcm.content;

import mindustry.world.*;
import mindustry.world.blocks.environment.*;

public class RCBlocks{
    public static Block
    cherryGrass,
    
    cherryWall,
    
    cherryTush;

    public static void load(){
        cherryGrass = new Floor("cherry-grass");

        cherryWall = new StaticWall("cherry-wall"){{
            variants = 3;
        }};

        cherryTush = new TreeBlock("cherry-tush");
    }
}
