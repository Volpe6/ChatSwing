
package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import servidor.cliente.Cliente;

/**
 *
 * @author Drew
 */
public class Servidor {
    
    private static Servidor oInstance;
    
    private ServerSocket oServer;
    private List<servidor.cliente.Cliente> aClientes;
    
    private Servidor() {
        aClientes = new ArrayList<>();
    }
    
    public static Servidor getInstance() {
        if(oInstance == null) {
            oInstance = new Servidor();
        }
        return oInstance;
    }
    
    public void iniciar() throws Exception {
        oServer = new ServerSocket(5566);
        System.out.println("Servidor iniciado");
    }
    
    public void conectar() throws Exception {
        while(true) {
            servidor.cliente.Cliente c = new Cliente(oServer.accept());
            c.start();
            addCliente(c);
        }
    }
    
    public void addCliente(servidor.cliente.Cliente oCli) {
        aClientes.add(oCli);
    }
    
    public void removeCliente(servidor.cliente.Cliente oCli) {
        aClientes.remove(oCli);
    }
    
    public void notificaCor(String msg) throws Exception {
        for(servidor.cliente.Cliente oCli : aClientes) {
            oCli.escreve(msg);
        }
    }
    
    public void notificaMensagem(String msg) throws Exception {
        for(servidor.cliente.Cliente oCli : aClientes) {
            oCli.escreve(msg);
        }
    }
    
    public static void main(String[] args) {
        try {
//            List<Socket> lista = new ArrayList<>();
            
            Servidor srv = Servidor.getInstance();
           
            srv.iniciar();
            srv.conectar();

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
