package rcm.content;

import arc.graphics.*;
import mindustry.type.*;
import rcm.type.status.*;

import static mindustry.Vars.*;

public class RCStatusEffects{
    public static StatusEffect
    weak, berryshifted;

    public static void load(){
        weak = new SpecificStatusEffect("weak"){{
            speedMultiplier = 0.6f;
            healthMultiplier = 0.8f;
            color = Color.valueOf("b5b5b5");
            init(() -> targets.addAll(RCUnitTypes.neckranea, RCUnitTypes.lightBug));
        }};
        
        berryshifted = new ShiftStatusEffect("berryshifted"){{
            damageMultiplier = 0.5f;
            range = 100f * tilesize;
            color = Color.valueOf("7a78ff");
            init(() -> unshift = RCUnitTypes.berryshift);
        }};
    }
}
