/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.command;

import servidor.Servidor;
import servidor.cliente.Cliente;

/**
 *
 * @author Drew
 */
public class CommandPrint extends Command {

    private String sMensagem;
    
    public CommandPrint(String sMen, Cliente oCli) {
        super(sMen, oCli);
        
        this.sMensagem = sMen;
        this.oCliente  = oCli;
    }

    @Override
    public void execute() throws Exception {
        Servidor serv = Servidor.getInstance();
        serv.notificaCor("setCorTexto-" + oCliente.getCor());
        serv.notificaMensagem("atualizar-" + oCliente.getNome() + ": " + sMensagem);
    }
    
}
