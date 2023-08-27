package rcm.type.unit;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;

public class WingUnitType extends UnitType{
    /** Wings. */
    public Seq<Wing> wings = new Seq<>();
    
    public WingUnitType(String name){
        super(name);
    }
    
    @Override
    public void init(){
        super.init();
        
        // steal from Weapon.java
        Seq<Wing> mapped = new Seq<>();
        for(Wing w : wings){
            mapped.add(w);
            
            if(w.mirror){
                Wing copy = w.copy();
                copy.flip();
                mapped.add(copy);
            }
        }
        wings = mapped;
        wings.each(Wing::init);
    }
    
    @Override
    public void load(){
        super.load();
        
        wings.each(w -> w.load(this));
    }
    
    @Override
    public void draw(Unit unit){
        super.draw(unit);
        
        drawWings(unit);
    }
    
    public void drawWings(Unit unit){
        wings.each(w -> w.draw(unit));
    }
    
    public static class Wing implements Cloneable{
        /** Sprite suffix.*/
        public String name;
        /** Wing position. */
        public float x = 0f, y = 0f;
        /** Wing rotation. */
        public float rotation = 0f;
        /** Wing width. Set to <=0f to detect from sprite. */
        public float width = -1f;
        /** Wing flap scale. */
        public float flapScl = 1f;
        /** Wing flap magnitude. Set to <=0f to detect from width. */
        public float flapMag = -1f;
        /** Wing draw layer. */
        public float layer = Layer.flyingUnit + 0.001f;
        /** Mirror to opposite side. */
        public boolean mirror = false;
        /** Internal use only. Determines if wing is mirrored. */
        public boolean flip = false;
        
        public TextureRegion region;
        
        public Wing(String name){
            this.name = name;
        }
        
        public void init(){
            // will probably use later
        }
        
        public void load(UnitType unit){
            region = Core.atlas.find(unit.name + name);
            
            if(width <= 0f) width = region.width * 0.25f;
            if(flapMag <= 0f) flapMag = width / 4f;
        }
        
        public void flip(){
            x *= -1f;
            rotation *= -1f;
            flip = true;
            mirror = false;
        }
        
        public void draw(Unit unit){
            unit.type.applyColor(unit);
            Draw.z(layer);
            
            float
            wx = unit.x + Angles.trnsx(unit.rotation, x, y),
            wy = unit.y + Angles.trnsy(unit.rotation, x, y),
            length = (width + Mathf.absin(Time.time, flapScl, flapMag)) * (flip ? -1f : 1f),
            
            ex = wx + Angles.trnsx(rotation, length),
            ey = wy + Angles.trnsy(rotation, length);
            
            Lines.stroke(region.height * 0.25f);
            Lines.line(region, wx, wy, ex, ey, false);
            
            Draw.reset();
        }
        
        public Wing copy(){
            try{
                return (Wing)clone();
            }catch(CloneNotSupportedException funnyHaha){
                throw new RuntimeException("so funny haha i love java we love java", funnyHaha);
            }
        }
    }
}