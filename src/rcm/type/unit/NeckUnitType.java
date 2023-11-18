package rcm.type.unit;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;
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
    /** Maximum drown time. If enabled, unit will never drown. Set to <=0f or >=0.999f to disable. */
    public float maxDrown = 0.999f;
    /** Offset of head to neck. */
    public float headOffset = 0f;
    /** If enabled, the unit will not render it's neck the same color as it's body when drowning. */
    public boolean longNeck = false;
    
    /** Whether unit has eyes. */
    public boolean drawEye = false;
    /** Offset of eyes to head. */
    public float eyeOffset = 0f;
    /** Max distance the pupil can get to from its origin. */
    public float pupilDistance = 0f;
    /** Pupil speed. */
    public float pupilSpeed = 0.5f;
    /** Blink duration. */
    public float blinkTime = 0.2f;
    /** Eyelids. */
    public Seq<Eyelid> eyelids = new Seq<>();
    
    public TextureRegion
    neckRegion, headRegion,
    eyeRegion, pupilRegion;
    
    public NeckUnitType(String name){
        super(name);
    }
    
    @Override
    public void load(){
        super.load();
        neckRegion = Core.atlas.find(name + "-neck");
        headRegion = Core.atlas.find(name + "-head");
        
        eyeRegion = Core.atlas.find(name + "-eye");
        pupilRegion = Core.atlas.find(name + "-pupil");
        
        eyelids.each(e -> {
            e.load(this);
        });
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
        hy = y2 + Angles.trnsy(neck.neckRot, headOffset),
        
        ex = hx + Angles.trnsx(neck.neckRot, eyeOffset),
        ey = hy + Angles.trnsy(neck.neckRot, eyeOffset),
        
        px = ex + Angles.trnsx(neck.neckRot, eyeOffset),
        py = ey + Angles.trnsy(neck.neckRot, eyeOffset);

        Lines.stroke(neckRegion.height * 0.25f);
        Lines.line(neckRegion, x, y, x2, y2, false);
        
        if(drawEye){
            Draw.rect(eyeRegion, ex, ey, neck.neckRot);
            
            Draw.rect(pupilRegion, px, py, neck.neckRot);
            
            eyelids.each(e -> {
                float
                blinkX = Mathf.lerp(e.openX, e.closeX, Interp.slope.apply(neck.blinkProg)),
                blinkY = Mathf.lerp(e.openY, e.closeY, Interp.slope.apply(neck.blinkProg)),
                
                lx = ex + Angles.trnsx(neck.neckRot - 90f, blinkX, blinkY),
                ly = ey + Angles.trnsy(neck.neckRot - 90f, blinkX, blinkY);
                
                Draw.rect(e.region, lx, ly, neck.neckRot);
            });
        }
        
        Draw.rect(headRegion, hx, hy, neck.neckRot);
        
        Draw.reset();
    }
    
    @Override
    public void drawShadow(Unit unit){
        super.drawShadow(unit);

        float e = Mathf.clamp(unit.elevation, shadowElevation, 1f) * shadowElevationScl * (1f - unit.drownTime);
        float sx = unit.x + UnitType.shadowTX * e, sy = unit.y + UnitType.shadowTY * e;
        
        Draw.color(Pal.shadow, Pal.shadow.a * unit.shadowAlpha);
        
        NeckUnit neck = (NeckUnit)unit;
        float
        x = sx + Angles.trnsx(neck.rotation, neckOffset),
        y = sy + Angles.trnsy(neck.rotation, neckOffset),
        x2 = x + Angles.trnsx(neck.neckRot, neckLength),
        y2 = y + Angles.trnsy(neck.neckRot, neckLength),
        
        hx = x2 + Angles.trnsx(neck.neckRot, headOffset),
        hy = y2 + Angles.trnsy(neck.neckRot, headOffset);
        
        Lines.stroke(neckRegion.height * 0.25f);
        Lines.line(neckRegion, x, y, x2, y2, false);
        
        Draw.rect(headRegion, hx, hy, neck.neckRot);
        
        Draw.color();
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
    
    public class Eyelid{
        /** X offset from pupil origin when opened. */
        public float openX = 0f;
        /** Y offset from pupil origin when opened. */
        public float openY = 0f;
        /** X offset from openX when closed. */
        public float closeX = 0f;
        /** Y offset from openY when closed. */
        public float closeY = 0f;
        
        public String name;
        public TextureRegion region;
        
        public Eyelid(String name){
            this.name = name;
        }
        
        public void load(UnitType unit){
            region = Core.atlas.find(unit.name + name);
        }
    }
}
