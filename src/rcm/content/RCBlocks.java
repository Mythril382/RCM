package rcm.content;

import mindustry.world.*;
import mindustry.world.blocks.environment.*;

public class RCBlocks{
    public static Block
    cherryGrass,
    
    cherryWall;

    public static void load(){
        cherryGrass = new Floor("cherry-grass");

        cherryWall = new StaticWall("cherry-wall"){{
            variants = 3;
        }};
    }
}
