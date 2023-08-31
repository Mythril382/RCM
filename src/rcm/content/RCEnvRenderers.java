package rcm.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.graphics.*;

import static mindustry.Vars.*;

public class RCEnvRenderers{
    public static void init(){
        renderer.addEnvRenderer(RCEnv.vibrant, () -> {
            Draw.z(Layer.light + 1f);
            Draw.color(vibrantColor());
            
            Fill.rect(Core.camera.position.x, Core.camera.position.y, Core.camera.width, Core.camera.height);
            
            Draw.reset();
        });
    }
    
    public static Color vibrantColor(){
        float time = state.rules.ambientLight.a;
        Color color = new Color();
        color.lerp(new Color[]{Color.valueOf("000000").a(0f), Color.valueOf("000000").a(0f), Color.valueOf("f5b56c").a(0.4f), Color.valueOf("313163").a(0.4f)}, time);
        return color;
    }
}
