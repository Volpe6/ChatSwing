/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.view;

import java.awt.Component;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Drew
 */
public interface View {
    
    public void notifica(String msg);
    
    public void atualizaAreaTexto(String msg);
    
    public void atualizaUsuario(String nome);
    
    public void atualizaEndereco(String endereco);
    
    public void atualizaPorta(String porta);
    
    public void atualizaFont(String font);
    
    public void atualizaCor(String cor);
    
    public void atualizaCor(int cor);
    
    public Component getComponente(String sNome);
    
    public void escutar();
}
