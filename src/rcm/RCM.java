package rcm;

import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.*;
import rcm.content.*;
import rcm.ui.dialogs.*;

import static mindustry.Vars.*;

public class RCM extends Mod{
    public static BestiaryDialog bestiary;
    
    @Override
    public void loadContent(){
        RCStatusEffects.load();
        RCUnitTypes.load();
        RCBlocks.load();

        Events.on(ClientLoadEvent.class, e -> {
            RCEnvRenderers.init();

            Time.runTask(7f, this::loadUI);
        });
    }

    public void loadUI(){
        bestiary = new BestiaryDialog();
        ui.hudfrag.find("statustable").button("@bestiary.title", bestiary::show);
    }
}
