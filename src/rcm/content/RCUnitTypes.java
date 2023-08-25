package rcm.content;

import arc.func.*;
import arc.graphics.*;
import arc.struct.*;
import arc.struct.ObjectMap.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import rcm.entities.unit.*;
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
            legCount = 8;
            legLength = 16f;
            legExtension = -3f;
            legStraightness = 0.2f;
            legGroupSize = 4;
            neckLength = 8.45f;
            neckSpeed = 1f;
            neckOffset = 5.75f;
            lockLegBase = true;
            legContinuousMove = true;
            allowLegStep = true;
            groundLayer = Layer.legUnit + 1f;
            outlineColor = Color.valueOf("332480");
            constructor = NeckUnit::create;
        }};
    }
    
    //Steal from Hearth which stole from Progressed Materials which stole from Endless Rusting which stole from Progressed Materials in the past which stole from BetaMindy
    private static final Entry<Class<? extends Entityc>, Prov<? extends Entityc>>[] types = new Entry[]{
        prov(NeckUnit.class, NeckUnit::new)
    };

    private static final ObjectIntMap<Class<? extends Entityc>> idMap = new ObjectIntMap<>();

    /** Internal function to flatmap {@code Class -> Prov} into an {@link Entry}.
     @author GlennFolker */
    private static <T extends Entityc> Entry<Class<T>, Prov<T>> prov(Class<T> type, Prov<T> prov){
        Entry<Class<T>, Prov<T>> entry = new Entry<>();
        entry.key = type;
        entry.value = prov;
        return entry;
    }

    /** Setups all entity IDs and maps them into {@link EntityMapping}.
     @author GlennFolker */
    private static void setupID(){
        for(int i = 0, j = 0, len = EntityMapping.idMap.length; i < len; i++){
            if(EntityMapping.idMap[i] == null){
                idMap.put(types[j].key, i);
                EntityMapping.idMap[i] = types[j].value;

                if(++j >= types.length) break;
            }
        }
    }

    /** Retrieves the class ID for a certain entity type.
     @author GlennFolker */
    public static <T extends Entityc> int classID(Class<T> type){
        return idMap.get(type, -1);
    }
}
