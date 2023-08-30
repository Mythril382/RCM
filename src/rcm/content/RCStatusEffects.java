package rcm.content;

import arc.graphics.*;
import mindustry.type.*;
import rcm.type.status.*;

public class RCStatusEffects{
    public static StatusEffect
    weak;

    public static void load(){
        weak = new SpecificStatusEffect("weak"){{
            speedMultiplier = 0.6f;
            healthMultiplier = 0.8f;
            color = Color.valueOf("b5b5b5");
            init(() -> targets.addAll(RCUnitTypes.neckranea, RCUnitTypes.lightBug));
        }};
    }
}
