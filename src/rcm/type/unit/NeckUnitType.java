package rcm.type.unit;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
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
    public float neckSpeed = 0.3f;
    /** Offset of head to neck. */
    public float headOffset = 0f;
    
    public TextureRegion
    neckRegion, headRegion,
    neckOutlineRegion, headOutlineRegion;
    
    public NeckUnitType(String name){
        super(name);
    }
    
    @Override
    public void load(){
        super.load();
        neckRegion = Core.atlas.find(name + "-neck");
        headRegion = Core.atlas.find(name + "-head");
        neckOutlineRegion = Core.atlas.find(name + "-neck-outline");
        headOutlineRegion = Core.atlas.find(name + "-head-outline");
    }
    
    @Override
    public void draw(Unit unit){
        super.draw(unit);
        if(unit instanceof NeckUnit neck){
            drawNeck(neck);
        }
    }
    
    public void drawNeck(NeckUnit neck){
        float
        x = neck.x + Angles.trnsx(neck.rotation, neckOffset),
        y = neck.y + Angles.trnsy(neck.rotation, neckOffset),
        x2 = x + Angles.trnsx(neck.neckRot, neckLength),
        y2 = y + Angles.trnsy(neck.neckRot, neckLength),
        
        hx = x2 + Angles.trnsx(neck.neckRot, headOffset),
        hy = y2 + Angles.trnsy(neck.neckRot, headOffset);
        
        Lines.line(neckOutlineRegion, x, y, x2, y2, false);
        Lines.line(neckRegion, x, y, x2, y2, false);
        
        Draw.rect(headOutlineRegion, hx, hy, neck.neckRot);
        Draw.rect(headRegion, hx, hy, neck.neckRot);
    }
}
