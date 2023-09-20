package rcm.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.graphics.*;

import static mindustry.Vars.*;

public class RCEnvRenderers{
    public static void init(){
        renderer.addEnvRenderer(RCEnv.vibrant, () -> {
            if(!state.rules.lighting) return;
            Draw.z(Layer.light + 1f);
            Draw.color(vibrantColor());
            Draw.rect(Core.atlas.find("rcm-big-square"), Core.camera.position.x, Core.camera.position.y, Core.camera.width, Core.camera.height);
            Draw.reset();
        });
    }
    
    public static Color vibrantColor(){
        float time = state.rules.ambientLight.a;
        Color color = new Color();
        color.lerp(new Color[]{Color.valueOf("dcdcdc"), Color.valueOf("ff6373"), Color.valueOf("4136ff")}, time);
        return color;
    }
}
