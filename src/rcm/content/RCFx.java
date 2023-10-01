package rcm.content;

import arc.*;
import mindustry.entities.*;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;

public class RCFx{
    public static Effect

    droneBlade = new Effect(360f / 6f, e -> {
        color(e.color);
        rect(Core.atlas.find("rcm-blade"), e.x, e.y, e.fin() * 360f);
        reset();
    }).layer(Layer.flyingUnit - 0.1f);
    droneBlade.rotWithParent = true;
}
