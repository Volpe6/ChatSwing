
package cliente.view.listener;

import cliente.controller.ControllerCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Drew
 */
public class ViewListenerConectar implements ActionListener {
    
    private ControllerCliente oCon;
    
    public ViewListenerConectar(ControllerCliente oCon) {
        this.oCon = oCon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            oCon.conectar();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ViewListenerConectar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
