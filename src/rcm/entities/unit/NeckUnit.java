package rcm.entities.unit;

import arc.*;
import arc.math.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.world.blocks.environment.*;
import rcm.content.*;
import rcm.type.unit.*;

import static mindustry.Vars.*;

public class NeckUnit extends LegsUnit{
    public float neckRot, blinkProgress, eyeOffset;
    
    @Override
    public void add(){
        super.add();
        neckRot = rotation;
    }
    
    @Override
    public void unloaded(){
        super.unloaded();
        neckRot = rotation;
    }
    
    @Override
    public void update(){
        super.update();
        NeckUnitType neck = (NeckUnitType)type;
        neckRot = Angles.clampRange(neckRot, rotation, neck.neckTrns);
        float neckAngle = Angles.angle(x, y, aimX(), aimY());
        neckRot = Angles.moveToward(neckRot, neckAngle, neck.neckSpeed * Time.delta);
    }
    
    @Override
    public void updateDrowning(){
        Floor floor = drownFloor();

        if(floor != null && floor.isLiquid && floor.drownTime > 0){
            lastDrownFloor = floor;
            
            drownTime += Time.delta / floor.drownTime / type.drownTimeMultiplier;
            NeckUnitType neck = (NeckUnitType)type;
            if(neck.maxDrown > 0f && neck.maxDrown < 0.999f){
                drownTime = Math.min(drownTime, neck.maxDrown);
            }
            
            if(Mathf.chanceDelta(0.05f)){
                floor.drownUpdateEffect.at(x, y, hitSize, floor.mapColor);
            }

            if(drownTime >= 0.999f && !net.client()){
                kill();
                Events.fire(new UnitDrownEvent(self()));
            }
        }else{
            drownTime -= Time.delta / 50f;
        }

        drownTime = Mathf.clamp(drownTime);
    }

    public static NeckUnit create(){
        return new NeckUnit();
    }

    @Override
    public int classId(){
        return RCUnitTypes.classID(NeckUnit.class);
    }
}
