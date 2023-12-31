package rcm.content;

import arc.func.*;
import arc.graphics.*;
import arc.struct.*;
import arc.struct.ObjectMap.*;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import rcm.entities.abilities.*;
import rcm.entities.unit.*;
import rcm.type.status.*;
import rcm.type.unit.*;
import rcm.type.unit.NeckUnitType.*;
import rcm.type.unit.WingUnitType.*;

import static mindustry.Vars.*;

public class RCUnitTypes{
    public static UnitType
    neckranea, lightBug,
    cherryCrawler,
    kiwi, berryshift,
    transportDrone;
    
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
            eyeOffset = 1.375f;
            pupilDistance = 1.375f;
            blinkTime = 0.1f;
            shadowElevation = 0.2f;
            lockLegBase = true;
            legContinuousMove = true;
            allowLegStep = true;
            longNeck = true;
            drawEye = true;
            hovering = true;
            hidden = true;
            drawCell = false;
            useUnitCap = false;
            outlineColor = Color.valueOf("332480");
            constructor = NeckUnit::create;
            
            eyelids.add(
                new Eyelid("-eyelid1"){{
                    openY = 2.375f;
                    closeY = -1.75f;
                }},
                new Eyelid("-eyelid2"){{
                    openY = -2f;
                    closeY = 1.25f;
                }}
            );
        }};
        
        lightBug = new WingUnitType("light-bug"){{
            health = 10;
            speed = 3f;
            accel = 0.06f;
            drag = 0.04f;
            engineSize = 0f;
            hitSize = 2f;
            lightRadius = 2f * tilesize;
            flying = true;
            lowAltitude = true;
            hidden = true;
            drawCell = false;
            useUnitCap = false;
            lightColor = Color.valueOf("d7a4f5");
            outlineColor = Color.valueOf("121775");
            constructor = UnitEntity::create;
            
            wings.add(new Wing("-wing"){{
                x = 1f;
                flapScl = 1f;
                flapMag = -4f;
                mirror = true;
            }});
            
            parts.add(new RegionPart("-glow"){{
                layer = Layer.flyingUnitLow + 0.002f;
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
            drawCell = false;
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
                    damage = 20f;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;
                    pierceEffect = Fx.none;
                    status = RCStatusEffects.weak;
                    statusDuration = 60f * 2.5f;
                }};
            }});
        }};

        kiwi = new NeckUnitType("kiwi"){{
            health = 1800;
            speed = 0.5f;
            drag = 0.4f;
            hitSize = 13f;
            rotateSpeed = 2f;
            maxDrown = 0.6f;
            legCount = 4;
            legGroupSize = 2;
            legLength = 25f;
            legExtension = -10f;
            legStraightness = 0.1f;
            legMoveSpace = 0.8f;
            legBaseOffset = 2f;
            neckLength = 12.5f;
            neckSpeed = 1f;
            neckTrns = 90f;
            neckOffset = 3.75f;
            shadowElevation = 0.4f;
            flipBackLegs = true;
            lockLegBase = true;
            legContinuousMove = true;
            allowLegStep = true;
            longNeck = true;
            hovering = true;
            hidden = true;
            drawCell = false;
            useUnitCap = false;
            groundLayer = Layer.flyingUnitLow + 0.1f;
            outlineColor = Color.valueOf("634040");
            constructor = NeckUnit::create;
        }};
        
        berryshift = new UnitType("berryshift"){{
            health = 1100;
            hitSize = 9f;
            speed = 1f;
            rotateSpeed = 2.6f;
            segments = 3;
            segmentScl = 3f;
            segmentPhase = 5f;
            segmentMag = 0.5f;
            hidden = true;
            drawBody = false;
            drawCell = false;
            omniMovement = false;
            useUnitCap = false;
            outlineColor = Color.valueOf("324194");
            constructor = CrawlUnit::create;
            
            abilities.add(new ShiftAbility(100f * tilesize, 60f){{
                detect = (ShiftStatusEffect)RCStatusEffects.berryshifted;
                allowShifts.add(cherryCrawler);
            }});
        }};
        
        transportDrone = new UnitType("transport-drone"){{
            health = 2500;
            hitSize = 3.5f;
            speed = 3.5f;
            accel = 0.07f;
            drag = 0.025f;
            engineSize = 0f;
            itemCapacity = 25;
            flying = true;
            hidden = true;
            createWreck = false;
            useUnitCap = false;
            outlineColor = Color.valueOf("365861");
            controller = u -> new CargoAI();
            constructor = BuildingTetherPayloadUnit::create;
            abilities.addAll(
                new MoveEffectAbility(5.5f, -5.5f, Color.valueOf("a1c2d1"), RCFx.droneBlade, 180f / 10f){{
                    minVelocity = 0f;
                    parentizeEffects = true;
                }},
                new MoveEffectAbility(5.5f, 5.5f, Color.valueOf("a1c2d1"), RCFx.droneBlade, 180f / 10f){{
                    minVelocity = 0f;
                    parentizeEffects = true;
                }},
                new MoveEffectAbility(-5.5f, -5.5f, Color.valueOf("a1c2d1"), RCFx.droneBlade, 180f / 10f){{
                    minVelocity = 0f;
                    parentizeEffects = true;
                }},
                new MoveEffectAbility(-5.5f, 5.5f, Color.valueOf("a1c2d1"), RCFx.droneBlade, 180f / 10f){{
                    minVelocity = 0f;
                    parentizeEffects = true;
                }}
            );
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
