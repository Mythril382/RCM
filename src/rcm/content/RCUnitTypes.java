package rcm.content;

import mindustry.graphics.*;
import mindustry.type.*;
import rcm.type.unit.*;

public class RCUnitTypes{
    public static UnitType
    neckranea;
    
    public static void load(){
        neckranea = new NeckUnitType("neckranea"){{
            health = 1800;
            speed = 0.4f;
            drag = 0.4f;
            hitSize = 12f;
            rotateSpeed = 3f;
            legCount = 6;
            legLength = 16f;
            legExtension = -3f;
            legStraightness = 0.2f;
            neckLength = 9f;
            neckOffset = 5.75f;
            lockLegBase = true;
            legContinuousMove = true;
            allowLegStep = true;
            groundLayer = Layer.legUnit + 1f;
        }};
    }
}