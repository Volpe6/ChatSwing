/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.view.listener;

import cliente.controller.Controller;
import cliente.controller.ControllerCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.xml.ws.Action;

/**
 *
 * @author Drew
 */
public class ViewListenerEnter implements ActionListener, KeyListener {

    private final int ENTER = 10;
    
    private Controller oCon;
    
    public ViewListenerEnter(Controller oCon) {
        this.oCon = oCon;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        oCon.requestFocusInInput();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == ENTER) {
            try {
                oCon.enviarMensagem();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
