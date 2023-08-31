package rcm;

import arc.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import rcm.content.*;

public class RCM extends Mod{
    @Override
    public void loadContent(){
        RCStatusEffects.load();
        RCUnitTypes.load();
        RCBlocks.load();

        Events.on(ClientLoadEvent.class, e -> {
            RCEnvRenderers.init();
        });
    }
}
