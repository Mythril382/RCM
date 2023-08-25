package rcm.entities.unit;

import arc.math.*;
import arc.util.*;
import mindustry.gen.*;
import rcm.content.*;
import rcm.type.unit.*;

public class NeckUnit extends LegsUnit{
    public float neckResetTimer = 0f;
    public float neckRot;
    
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
        neckResetTimer += Time.delta;
        if(moving()) neckResetTimer = 0f;
        
        NeckUnitType neck = (NeckUnitType)type;
        neckRot = Angles.clampRange(neckRot, rotation, neck.neckTrns);
        
        if(neckResetTimer > neck.neckReset){
            neckRot = Angles.moveToward(neckRot, rotation, neck.neckSpeed);
            neckResetTimer = 0f;
        }
    }

    public static NeckUnit create(){
        return new NeckUnit();
    }

    @Override
    public int classId(){
        return RCUnitTypes.classID(NeckUnit.class);
    }
}
