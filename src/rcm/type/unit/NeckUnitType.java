package rcm.type.unit;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.type.*;
import rcm.entities.unit.*;

public class NeckUnitType extends UnitType{
    /** Length of neck. */
    public float neckLength = 10f;
    /** Offset of neck to body. */
    public float neckOffset = 0f;
    /** Amount of rotation required for neck to be pulled along with the body. */
    public float neckTrns = 45f;
    /** Speed of neck. */
    public float neckSpeed = 0.5f;
    /** Offset of head to neck. */
    public float headOffset = 0f;
    /** If enabled, the unit will not render it's neck the same color as it's body when drowning. */
    public boolean longNeck = false;
    /** Maximum drown time. If enabled, unit will never drown. Set to <=0f or >=0.999f to disable. */
    public float maxDrown = 0.999f;
    
    public TextureRegion
    neckRegion, headRegion;
    
    public NeckUnitType(String name){
        super(name);
    }
    
    @Override
    public void load(){
        super.load();
        neckRegion = Core.atlas.find(name + "-neck");
        headRegion = Core.atlas.find(name + "-head");
    }
    
    @Override
    public void draw(Unit unit){
        super.draw(unit);
        if(unit instanceof NeckUnit neck){
            drawNeck(neck);
        }
    }
    
    public void drawNeck(NeckUnit neck){
        applyColorNeck(neck);
        
        float
        x = neck.x + Angles.trnsx(neck.rotation, neckOffset),
        y = neck.y + Angles.trnsy(neck.rotation, neckOffset),
        x2 = x + Angles.trnsx(neck.neckRot, neckLength),
        y2 = y + Angles.trnsy(neck.neckRot, neckLength),
        
        hx = x2 + Angles.trnsx(neck.neckRot, headOffset),
        hy = y2 + Angles.trnsy(neck.neckRot, headOffset);

        Lines.stroke(neckRegion.height * 0.25f);
        Lines.line(neckRegion, x, y, x2, y2, false);
        
        Draw.rect(headRegion, hx, hy, neck.neckRot);
        
        Draw.reset();
    }
    
    public void applyColorNeck(Unit unit){
        Draw.color();
        if(healFlash){
            Tmp.c1.set(Color.white).lerp(healColor, Mathf.clamp(unit.healTime - unit.hitTime));
        }
        Draw.mixcol(Tmp.c1, Math.max(unit.hitTime, !healFlash ? 0f : Mathf.clamp(unit.healTime)));
        
        if(longNeck) return;
        if(unit.drownTime > 0 && unit.lastDrownFloor != null){
            Draw.mixcol(Tmp.c1.set(unit.lastDrownFloor.mapColor).mul(0.83f), unit.drownTime * 0.9f);
        }
    }
}
