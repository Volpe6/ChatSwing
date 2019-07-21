/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.view;

import cliente.controller.Controller;
import cliente.controller.ControllerControle;
import cliente.view.listener.ViewListenerConectar;
import cliente.view.listener.ViewListenerEnter;
import java.awt.Component;
import java.awt.Dimension;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Drew
 */
public class ViewFrameControle extends JFrame implements View, Runnable {
    
    private static ViewFrameControle oInstance;
    
    private ViewPanel  jPanel;
    private Controller oCon;
    
    private ViewFrameControle() {
        this.setTitle("Controle");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(500, 265));
        this.setResizable(false);
        
        initComponents();
//        this.setVisible(true);
    }
    
    public static ViewFrameControle getInstance() {
        if(oInstance == null) {
            oInstance = new ViewFrameControle();
        }
        
        return oInstance;
    }
    
    private void initComponents() {
        this.oCon = new ControllerControle(this);
        
        jPanel = new ViewPanel();
        
        jPanel.setBorder(new EmptyBorder(-30, 0, 0, 0));
        
        JTextArea jt = new JTextArea(10, 40);
        jt.setEditable(false);
        
        JScrollPane js = new  JScrollPane();
        
        jPanel.addComponente("areatexto", "", jt);
        
        js.setViewportView(getComponente("areatexto"));
        
        jPanel.addComponente("scroll"   , "", js);
        jPanel.addComponente("input"    , "", new JTextField());
        
        addEventos();
        
        this.add(jPanel);
    }
    
    public void addEventos() {
        JTextField jt  = (JTextField)getComponente("input");
        
        jt.addKeyListener(new ViewListenerEnter(oCon));
    }
    
    public Controller getController() {
        return oCon;
    }
    
    public void setSocket(Socket soc) {
        oCon.setSocket(soc);
    }
    
    public void setControllerCliente(Controller oCon) {
        ((ControllerControle)this.oCon).setControllerCliente(oCon); 
    }
    
    public Component getComponente(String sNome) {
        return jPanel.getComponente(sNome);
    }

    @Override
    public void notifica(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    @Override
    public void atualizaAreaTexto(String msg) {
        JTextArea jt = (JTextArea)getComponente("areatexto");
        jt.append(msg+"\n");
        jt.revalidate();
        jt.repaint();
    }

    @Override
    public void escutar() {
        try {
            oCon.processa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void conectar(Socket soc, Controller oCon) {

        ((ControllerControle)this.oCon).setControllerCliente(oCon); 
        this.oCon.setSocket(soc);
    }
    
    @Override
    public void atualizaUsuario(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizaEndereco(String endereco) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizaPorta(String porta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        escutar();
    }

    @Override
    public void atualizaFont(String font) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizaCor(String cor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizaCor(int cor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
