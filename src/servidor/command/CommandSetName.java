
package servidor.command;

import servidor.cliente.Cliente;

/**
 *
 * @author Drew
 */
public class CommandSetName extends Command {

    private String sNome;
    
    public CommandSetName(String sCommand, Cliente oCli) {
        super(sCommand, oCli);
        
        this.sNome = sCommand;
    }

    @Override
    public void execute() throws Exception {
        oCliente.setNome(sNome);
        oCliente.escreve("setNome-" + sNome);
    }
    
}
