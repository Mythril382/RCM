package rcm.entities.abilities;

import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.gen.*;
import mindustry.type.*;
import rcm.type.status.*;

public class ShiftAbility extends Ability{
    protected float timer;
    
    /** Range of enemy detection. */
    public float range = 8f;
    /** Amount of ticks until shift. */
    public float wait = 60f;
    /** Effect applied to shifted unit. */
    public ShiftStatusEffect detect;
    /** Units that can be shifted to. */
    public ObjectSet<UnitType> allowShifts = new ObjectSet<>();
    
    public ShiftAbility(){}
    
    public ShiftAbility(float range, float wait, ObjectSet<UnitType> allowShifts){
        this.range = range;
        this.wait = wait;
        this.allowShifts = allowShifts;
    }
    
    @Override
    public void update(Unit unit){
        Unit closest = Units.closestEnemy(unit.team, unit.x, unit.y, range, u -> allowShifts.contains(u));
        if(closest == null){
            timer = 0f;
            return;
        }
        
        timer += Time.delta;
        if(timer >= wait){
            Unit shift = closest.type.spawn(unit.team, unit.x, unit.y);
            shift.apply(detect);
            timer = 0f;
            unit.kill();
        }
    }
}
