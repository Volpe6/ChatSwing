/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.command;

import servidor.cliente.Cliente;

/**
 *
 * @author Drew
 */
public class CommandSetCor extends Command {
    
    private String cor;
    
    public CommandSetCor(String sCommand, Cliente oCli) {
        super(sCommand, oCli);
        
        this.cor = sCommand;
    }

    @Override
    public void execute() throws Exception {
        this.oCliente.setCor(cor);
        this.oCliente.escreve("setCorTexto-" + this.oCliente.getCor());
    }
    
}
