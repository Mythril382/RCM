package rcm.ui.dialogs;

import arc.*;
import arc.struct.*;
import mindustry.ui.dialogs.*;
import rcm.content.*;

public class BestiaryDialog extends BaseDialog{
    public Seq<UnitType> entrys = new Seq<>();
    
    public BestiaryDialog(){
        super("@bestiary.title");
        
        shouldPause = true;
        
        shown(this::rebuild);
        addCloseListener();
        
        entrys.addAll(
            RCUnitTypes.neckranea,
            RCUnitTypes.lightBug,
            RCUnitTypes.cherryCrawler,
            RCUnitTypes.kiwi,
            RCUnitTypes.berryshift
        );
    }
    
    void rebuild(){
        cont.clear();
        
        cont.pane(t -> {
            t.defaults().height(220f);
            entrys.each(u -> {
                t.table(e -> {
                    // TODO add locks
                    e.image(Core.atlas.find(u.name + "-portrait")).size(210f).left().growX(5f);
                    e.add(u.description).padLeft(10f).wrap();
                }).growX();
            });
        }).scrollX(false);
    }
}
