/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.controller;

import java.awt.Dimension;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import util.ArquivoCliente;

/**
 *
 * @author Drew
 */
public interface Controller {
    
    public void setSocket(Socket oSoc);
    
    public Socket getSocket();
    
    public void requestFocusInInput();
    
    public void notificaMensagem(String msg);
    
    public void notificacao(String msg);
    
    public void notificaCorTexto(int cor);
    
    public void conectar() throws Exception;
    
    public void enviarMensagem() throws Exception;
    
    public void mostra();
    
    
    public void processa() throws Exception;
    
    public void execute(String [] comando);
}
