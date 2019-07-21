
package cliente.view.acao;

import cliente.controller.ControllerCliente;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Drew
 */
public class ViewAcaoAspas extends AbstractAction {
    
    private ControllerCliente oCon;
    
    public ViewAcaoAspas(ControllerCliente oCon) {
        this.oCon = oCon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        oCon.mostra();
    }
    
}
