package rcm.type.status;

import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.type.*;

public class ShiftStatusEffect extends StatusEffect{
    /** Range of enemy detection */
    public float range = 8f;
    /** Unit to unshift to. */
    public UnitType unshift;
    
    public ShiftStatusEffect(String name){
        super(name);
        
        permanent = true;
        show = false;
    }
    
    @Override
    public void update(Unit unit, float time){
        super.update(unit, time);
        
        Unit closest = Units.closestEnemy(unit.team, unit.x, unit.x, u -> {});
        if(closest != null){
            unshift.spawn(unit.team, unit.x, unit.y);
            unit.kill();
        }
    }
}