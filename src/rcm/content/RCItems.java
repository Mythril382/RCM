package rcm.content;

import arc.graphics.*;
import mindustry.type.*;

public class RCItems{
    public static Item
    aluminium;

    public static void load(){
        aluminium = new Item("aluminium", Color.valueOf("a1c2d1")){{
            hardness = 2;
            cost = 1f;
        }};
    }
}
