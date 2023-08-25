package rcm;

import mindustry.mod.*;
import rcm.content.*;

public class RCM extends Mod{
    @Override
    public void loadContent(){
        RCUnitTypes.load();
        RCBlocks.load();
    }
}
