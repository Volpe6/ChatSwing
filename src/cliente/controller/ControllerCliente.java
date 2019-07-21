
package cliente.controller;

import cliente.view.View;
import cliente.view.ViewFrameCliente;
import cliente.view.ViewFrameControle;
import java.awt.Dimension;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import util.ArquivoCliente;

/**
 *
 * @author Drew
 */
public class ControllerCliente implements Controller {
    
    private View             oView;
    private Socket           oSoc;
    private ArquivoCliente   oArquivo;
    
    public ControllerCliente(ViewFrameCliente oView, Socket oSoc) {
        this.oView    = oView;
        this.oSoc     = oSoc;
        this.oArquivo = new ArquivoCliente(oSoc, this);
    }
    
    public ControllerCliente(ViewFrameCliente oView) {
        this.oView = oView;
    }
    
    public void setSocket(Socket oSoc) {
        this.oSoc     = oSoc;
        this.oArquivo = new ArquivoCliente(oSoc, this);
    }
    
    public Socket getSocket() {
        return oSoc;
    }
    
    public void requestFocusInInput() {
        JTextField in = (JTextField)oView.getComponente("input");
        in.requestFocusInWindow();
    }
    
    public void notificaMensagem(String msg) {
        oView.atualizaAreaTexto(msg);
    }
    
    public void notificacao(String msg) {
        oView.notifica(msg);
    }

    @Override
    public void notificaCorTexto(int cor) {
        oView.atualizaCor(cor);
    }
    
    private void notificaAtualizacaoUsuario(String nome) {
        oView.atualizaUsuario(nome);
    }
    
    private void notificaAtualizacaoEndereco(String endereco) {
        oView.atualizaEndereco(endereco);
    }
    
    private void notificaAtualizacaoPorta(String porta) {
        oView.atualizaPorta(porta);
    }
    
    public void conectar() throws Exception {
        if(oSoc != null) {
            return;
        }
        
        JTextField[] jtCampos  = new JTextField[3];
        
        jtCampos[0] = (JTextField)oView.getComponente("endloc");
        jtCampos[1] = (JTextField)oView.getComponente("porta");
        jtCampos[2] = (JTextField)oView.getComponente("user");
        
        setSocket(new Socket(jtCampos[0].getText(), Integer.parseInt(jtCampos[1].getText())));
        
        String nome = jtCampos[2].getText();
        
        notificaAtualizacaoUsuario(jtCampos[2].getText());
        notificaAtualizacaoEndereco(jtCampos[0].getText());
        notificaAtualizacaoPorta(jtCampos[1].getText());
        
        for(int i = 0; i < jtCampos.length; i++) {
            jtCampos[i].setText("");
        }
        
        escrever("setNome-" + nome);
    }
    
    public void enviarMensagem() throws Exception {
        JTextField in = (JTextField)oView.getComponente("input");
        escreve(in.getText());
        in.setText("");
    }
    
    public void mostra() {
//        if(this.oSoc == null) {
//            return;
//        }
        ViewFrameControle v = ViewFrameControle.getInstance();
        v.setVisible(true);
        v.conectar(oSoc, this);

//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread t = new Thread(v);
//                    t.start();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        v.setSocket(oSoc);
//        v.setControllerCliente(this);
//        v.conectar(oSoc, this);
//        v.escutar();
    }
    
    private void escreve(String msg) throws Exception {
        oArquivo.enviar("print-"+msg);
    }
    
    private void escrever(String msg) throws Exception {
        oArquivo.enviar(msg);
    }
    
    public void processa() throws Exception {
        while(true) {
            Thread.sleep(250);
            if(oSoc == null || !oSoc.isConnected()) {
                continue;
            }
            oArquivo.recebe();
        }
    }
    
    public void execute(String [] comando) {
        
        switch(comando[0]) {
            case "atualizar":
                this.notificaMensagem(comando[1]);
                break;
            case "setNome":
                if(ViewFrameControle.getInstance().isVisible()) {
                    ViewFrameControle.getInstance().getController().notificaMensagem("Nome alterado para: " + comando[1]);
                }
                this.notificaAtualizacaoUsuario(comando[1]);
                break;
            case "setCorTexto":
                this.notificaCorTexto(Integer.parseInt(comando[1]));
                break;
        }
     
    }
}
