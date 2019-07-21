
package servidor.command;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Drew
 */
public class CommandInvoker {
    
    private static CommandInvoker oInstance;
    
    public Map<String, Class<? extends Command>> comandos;
    
    private CommandInvoker() {
        comandos = new HashMap<>();
        
        comandos.put("print"  , CommandPrint.class);
        comandos.put("setNome", CommandSetName.class);
        comandos.put("setCor" , CommandSetCor.class);
    }
    
    public static CommandInvoker getInstance() {
        if(oInstance == null) {
            oInstance = new CommandInvoker();
        }
        return oInstance;
    }
    
    public void constroiExecuteCommand(String sCom, String sValor, servidor.cliente.Cliente oCli) throws Exception {
        Class<? extends Command> oClasse = comandos.get(sCom);
        
        Constructor<? extends Command> oCons = oClasse.getConstructor(String.class, servidor.cliente.Cliente.class);
        Command oCom = oCons.newInstance(sValor, oCli);
        
        execute(oCom);
    }
    
    public void execute(Command oCom) throws Exception {
        oCom.execute();
    }
}
