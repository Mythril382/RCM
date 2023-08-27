package rcm.content;

import arc.func.*;
import arc.graphics.*;
import arc.struct.*;
import arc.struct.ObjectMap.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import rcm.entities.unit.*;
import rcm.type.unit.*;
import rcm.type.unit.WingUnitType.*;

import static mindustry.Vars.*;

public class RCUnitTypes{
    public static UnitType
    neckranea, lightBug,
    cherryCrawler;
    
    public static void load(){
        neckranea = new NeckUnitType("neckranea"){{
            health = 1000;
            armor = 1;
            speed = 0.7f;
            drag = 0.4f;
            hitSize = 12f;
            rotateSpeed = 3f;
            maxDrown = 0.8f;
            legCount = 8;
            legGroupSize = 2;
            legLength = 16f;
            legExtension = -3f;
            legStraightness = 0.2f;
            legMoveSpace = 0.7f;
            legBaseOffset = 2f;
            neckLength = 8.45f;
            neckSpeed = 2f;
            neckOffset = 4f;
            headOffset = 4f;
            shadowElevation = 0.2f;
            longNeck = true;
            lockLegBase = true;
            legContinuousMove = true;
            allowLegStep = true;
            hovering = true;
            hidden = true;
            useUnitCap = false;
            outlineColor = Color.valueOf("332480");
            constructor = NeckUnit::create;
        }};
        
        lightBug = new WingUnitType("light-bug"){{
            health = 10;
            speed = 3f;
            accel = 0.06f;
            drag = 0.04f;
            engineSize = 0f;
            hitSize = 2f;
            flying = true;
            hidden = true;
            outlineColor = Color.valueOf("121775");
            constructor = UnitEntity::create;
            
            wings.add(new Wing("-wing"){{
                x = 1f;
                flapScl = 2f;
                mirror = true;
            }});
            
            parts.add(new RegionPart("-glow"){{
                layer = Layer.flyingUnit + 0.002f;
                outline = false;
                blending = Blending.additive;
                color = Color.valueOf("d7a4f5").a(0.5f);
            }});
        }};
        
        cherryCrawler = new UnitType("cherry-crawler"){{
            health = 1600;
            armor = 2;
            speed = 0.8f;
            drag = 0.5f;
            hitSize = 10f;
            rotateSpeed = 4.5f;
            range = 20f * tilesize;
            legCount = 6;
            legGroupSize = 2;
            legLength = 10f;
            legExtension = -3f;
            legStraightness = 0.3f;
            legMoveSpace = 0.6f;
            legBaseOffset = 2f;
            shadowElevation = 0.1f;
            lockLegBase = true;
            legContinuousMove = true;
            allowLegStep = true;
            hovering = true;
            hidden = true;
            useUnitCap = false;
            outlineColor = Color.valueOf("b54a58");
            constructor = LegsUnit::create;
            
            parts.add(new RegionPart("-tooth"){{
                x = 2f;
                y = 4f;
                moveY = 3.5f;
                layerOffset = -0.001f;
                progress = PartProgress.reload;
                mirror = true;
            }});
            
            weapons.add(new Weapon(){{
                shootSound = Sounds.none;
                reload = 15f;
                shootCone = 90f;
                alternate = false;
                rotate = false;
                
                bullet = new RailBulletType(){{
                    length = 4f;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;
                    pierceEffect = Fx.none;
                    damage = 20f;
                }};
            }});
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
