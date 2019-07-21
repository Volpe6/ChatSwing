/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.command;

/**
 *
 * @author Drew
 */
public abstract class Command {
    
    protected servidor.cliente.Cliente oCliente;
    
    public Command(String sCommand, servidor.cliente.Cliente oCli) {
        this.oCliente = oCli;
    }
    
    public abstract void execute() throws Exception;
    
}
