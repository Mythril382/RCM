package rcm.type.status;

import arc.struct.*;
import mindustry.type.*;

import static mindustry.Vars.*;

public class SpecificStatusEffect extends StatusEffect{
    /** Specific units that shouldn't have immunity to this effect. */
    public ObjectSet<UnitType> targets = new ObjectSet<>();

    public SpecificStatusEffect(String name){
        super(name);
    }

    @Override
    public void init(){
        content.units().select(u -> !targets.contains(u)).each(u -> u.immunities.add(this));
        super.init();
    }
}
