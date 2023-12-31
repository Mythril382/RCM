package rcm.ui.dialogs;

import arc.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;
import rcm.content.*;

public class BestiaryDialog extends BaseDialog{
    public Seq<UnitType> entrys = new Seq<>();
    
    public BestiaryDialog(){
        super("@bestiary.title");
        
        shouldPause = true;
        
        shown(this::rebuild);
        addCloseButton();
        
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
                t.table(Tex.whiteui, e -> {
                    // TODO add locks
                    e.add(u.localizedName).top().grow().left().pad(10f).padBottom(20f);
                    e.row();
                    e.image(Core.atlas.find(u.name + "-portrait")).size(150f).grow().left().pad(10f);
                    e.add(u.description).grow().left().pad(10f).padLeft(20f).wrap();
                    t.row();
                }).pad(20f).growX().color(Pal.darkishGray);
            });
        }).growX().scrollX(false);
    }
}
