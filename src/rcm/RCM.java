package rcm;

import arc.*;
import arc.scene.ui.layout.*;
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
        Table statusTable = (Table)ui.hudGroup.find("statustable");
        statusTable.row();
        statusTable.button("@bestiary.title", bestiary::show).size(60f, 140f).visible(() -> state.rules.hasEnv(RCEnv.vibrant));
    }
}
