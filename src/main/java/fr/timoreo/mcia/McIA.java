package fr.timoreo.mcia;

import fr.timoreo.mcia.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = "mcia",name = "Minecraft AI",version = "1.0",acceptedMinecraftVersions = "1.12.2")
public class McIA {

    public static Logger logger;

    @SidedProxy(clientSide = "fr.timoreo.mcia.proxy.ClientProxy", serverSide = "fr.timoreo.mcia.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preinit(FMLPreInitializationEvent e){
        logger = e.getModLog();
        proxy.preinit();
    }
    @Mod.EventHandler
    public static void init(FMLInitializationEvent e){
        proxy.init();
    }
}
