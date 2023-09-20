package rcm.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static mindustry.Vars.*;

public class RCEnvRenderers{
    public static void init(){
        renderer.addEnvRenderer(RCEnv.vibrant, () -> {
            if(!state.rules.lighting) return;

            if(state.rules.ambientLight <= 0.25f){
                Draw.draw(Layer.light + 1f, () -> {
                    Draw.color(vibrantColor());
                    Draw.rect(Core.atlas.find("rcm-big-square"), Core.camera.position.x, Core.camera.position.y, Core.camera.width, Core.camera.height);
                    Draw.reset();
                });
            }

            if(state.rules.ambientLight.a >= 0.75f){
                Draw.draw(Layer.light + 1f;, () -> {
                    Weather.drawParticles(
                        Core.atlas.find("particle"), Color.valueOf("d7a4f5"),
                        1f, 4f,
                        1000f, (state.rules.ambientLight.a - 0.75f) / 0.25f, 1f,
                        1f, 1f,
                        0.25f, 0.5f,
                        30f, 80f,
                        1f, 7f,
                        false
                    );
               });
            }
        });
    }
    
    public static Color vibrantColor(){
        float time = state.rules.ambientLight.a;
        Color color = new Color();
        color.lerp(new Color[]{Color.valueOf("000000"), Color.valueOf("ff6373"), Color.valueOf("4136ff")}, time);
        return color;
    }
}
