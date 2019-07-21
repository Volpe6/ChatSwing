/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.controller;

import cliente.view.View;
import cliente.view.ViewFrameControle;
import java.net.Socket;
import javax.swing.JTextField;
import util.ArquivoCliente;

/**
 *
 * @author Drew
 */
public class ControllerControle implements Controller {
    
    private View              oView;
    private ArquivoCliente    oArquivo;
    private Socket            oSoc;
    private Controller        oCon;
    
    public ControllerControle(View oView) {
        this.oView = oView;
    }
    
    public void setControllerCliente(Controller oCon) {
        this.oCon = oCon;
    }
    
    public void setSocket(Socket oSoc) {
        this.oSoc     = oSoc;
        this.oArquivo = new ArquivoCliente(oSoc, this);
    }

    @Override
    public Socket getSocket() {
        return oSoc;
    }

    @Override
    public void requestFocusInInput() {
        JTextField in = (JTextField)oView.getComponente("input");
        in.requestFocusInWindow();
    }

    @Override
    public void notificaMensagem(String msg) {
        oView.atualizaAreaTexto(msg);
    }

    @Override
    public void notificacao(String msg) {
        oView.notifica(msg);
    }

    @Override
    public void conectar() throws Exception {}

    @Override
    public void enviarMensagem() throws Exception {
        JTextField in = (JTextField)oView.getComponente("input");
        escreve(in.getText());
        in.setText("");
    }

    @Override
    public void mostra() {}

    @Override
    public void processa() throws Exception {
        while(true) {
            Thread.sleep(250);
            if(oSoc == null || !oSoc.isConnected()) {
                continue;
            }
            oArquivo.recebe();
        }
    }

    @Override
    public void execute(String[] comando) {}
    
    private void escreve(String msg) throws Exception {
        oArquivo.enviar(msg);
    }

    @Override
    public void notificaCorTexto(int cor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
