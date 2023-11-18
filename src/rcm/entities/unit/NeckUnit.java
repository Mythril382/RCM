package rcm.entities.unit;

import arc.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.world.blocks.environment.*;
import rcm.content.*;
import rcm.type.unit.*;

import static mindustry.Vars.*;

public class NeckUnit extends LegsUnit{
    public float
    neckRot, blinkProg, blinkTime,
    pupilX, pupilY,
    newPupilX, newPupilY;
    
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
        
        blinkTime -= Time.delta;
        if(blinkTime <= 0f){
            blinkProg += (1f / neck.blinkTime) * Time.delta / 60f;
            if(blinkProg >= 1f){
                blinkProg = 0f;
                blinkTime = Mathf.random(120, 600);
            }
        }
        
        nearby = null;
        cdist = 0f;
        
        float range = tilesize * 10f;
        Units.nearby(null, x - range, y - range, range * 2f, range * 2f, e -> {
            if(!e.isValid()) return;

            float dist = e.dst2(x, y);
            if(nearby == null || dist < cdist){
                nearby = e;
                cdist = dist;
            }
        });

        return nearby;
        
        if(nearby != null){
            float
            angleX = Angles.trnsx(neckRot, neck.neckOffset + neck.neckLength + neck.headOffset + neck.eyeOffset),
            angleY = Angles.trnsx(neckRot, neck.neckOffset + neck.neckLength + neck.headOffset + neck.eyeOffset),
            pupilAngle = Angles.angle(angleX, angleY, nearby.x, nearby.y),
            pupilDistance = Math.min(this.dst(nearby), neck.pupilDistance);
            
            newPupilX = Angles.trnsx(pupilAngle, pupilDistance);
            newPupilY = Angles.trnsx(pupilAngle, pupilDistance);
        }else if(moving()){
            newPupilX = Angles.trnsx(neckRot, neck.pupilDistance);
            newPupilY = Angles.trnsy(neckRot, neck.pupilDistance);
        }else{
            newPupilX = newPupilY = 0f;
        }
        
        pupilX = newPupilX;
        pupilY = newPupilY;
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
