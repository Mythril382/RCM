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
        
        Unit closest = Units.closestEnemy(unit.team, unit.x, unit.x, range, u -> true);
        if(closest != null){
            // TODO add fx along with temporary unmoving and invincible
            unshift.spawn(unit.team, unit.x, unit.y);
            unit.remove();
        }
    }
}
