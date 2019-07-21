
package cliente.view;

import cliente.controller.ControllerCliente;
import cliente.view.acao.ViewAcaoAspas;
import cliente.view.listener.ViewListenerConectar;
import cliente.view.listener.ViewListenerEnter;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Drew
 */
public class ViewFrameCliente extends JFrame implements View {
    
    
    private final String ENDERECO = "127.0.0.1";
    private final String PORTA    = "5566";
    
    private ViewPanel          jPanel;
    private ViewPanel          jPanelConecao;
    private ControllerCliente  oCon;
    private Socket             oSoc;
    private AbstractDocument   oDoc;
    private SimpleAttributeSet atr;
    
    private HashMap<Integer, Color> colors;
    
    private List<ViewPanel> panels;
    
    public ViewFrameCliente() {
        this.setTitle("teste");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(500, 280));
        this.setResizable(false);
        this.panels = new ArrayList<>();
        
        initComponentes();
        this.setVisible(true);
    }
    
    private void initComponentes() {
        try {
            colors = new HashMap<>();
            atr    = new SimpleAttributeSet();
            setColors();
            
            oCon  = new ControllerCliente(this);
            
            JPanel painelEsquerda = new JPanel(new BorderLayout());
            
            
            addAreaConecao();
            addAreaChat();
            
            jPanelConecao.setBorder(new EmptyBorder(10, 0, 0, 0));
            jPanel.setBorder(new EmptyBorder(new Insets(-50, 0, 0, 0)));
            
            painelEsquerda.add(jPanelConecao, BorderLayout.PAGE_START);
            
            trataCampos();
            addEventos();
            mapaTeclas();
            
            this.add(painelEsquerda, BorderLayout.LINE_START);
            this.add(jPanel, BorderLayout.LINE_END);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setColors() {
        colors.put(1, Color.BLACK);
        colors.put(2, Color.BLUE);
        colors.put(3, Color.CYAN);
        colors.put(4, Color.DARK_GRAY);
        colors.put(5, Color.GRAY );
        colors.put(6, Color.GREEN);
        colors.put(7, Color.LIGHT_GRAY);
        colors.put(8, Color.MAGENTA);
        colors.put(9, Color.ORANGE);
        colors.put(10, Color.PINK);
        colors.put(11, Color.RED);
    }
    
    private Color getCor(int cor) {
        return colors.get(cor);
    }
    
    private void addAreaChat() {
        jPanel = new ViewPanel();

        JTextPane jt = new JTextPane();
        jt.setMargin(new Insets(5, 5, 5, 5));
        
        StyledDocument styleDoc = jt.getStyledDocument();
        
        if(styleDoc instanceof AbstractDocument) {
            oDoc = (AbstractDocument) styleDoc;
        }
        
        jt.setEditable(false);
        JScrollPane js = new JScrollPane();
        js.setPreferredSize(new Dimension(250, 155));
        js.setMinimumSize(new Dimension(10, 10));

        jPanel.addComponente("areatexto", "", jt);

        js.setViewportView(jPanel.getComponente("areatexto"));

        jPanel.addComponente("scroll", "", js);
        jPanel.addComponente("input" , "", new JTextField());
        
        panels.add(jPanel);
    }
    
    private void addAreaConecao() { 
        jPanelConecao = new ViewPanel();
        
        jPanelConecao.addCampo("endloc", "End. Local: ");
        jPanelConecao.addCampo("porta" , "Porta: ");
        jPanelConecao.addCampo("user"  , "Usuario: ");
        
        jPanelConecao.addComponente("lbluser"    , "Usuario : " , null);
        jPanelConecao.addComponente("lblendereco", "Endereco : ", null);
        jPanelConecao.addComponente("lblporta"   , "Porta:"     , null);
        
        jPanelConecao.addComponente("btnconectar", "", new JButton("Conectar"));
        
        panels.add(jPanelConecao);
    }
    
    private void trataCampos() {
        JTextField jtCampos [] = new JTextField[3];
        jtCampos[0] = (JTextField)getComponente("endloc");
        jtCampos[1] = (JTextField)getComponente("porta");
        
        jtCampos[0].setText(ENDERECO);
        jtCampos[1].setText(PORTA);
        
    }
    
    public void mapaTeclas() {
        JRootPane root = this.getRootPane();
        root.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("F9"), "painelControle");
        root.getActionMap().put("painelControle", new ViewAcaoAspas(oCon));
    }
    
    public void addEventos() {
        JTextField jt  = (JTextField)getComponente("input");
        JButton    btn = (JButton)getComponente("btnconectar");
        
        jt.addKeyListener(new ViewListenerEnter(oCon));
        btn.addActionListener(new ViewListenerConectar(oCon));
    }
    
    public void notifica(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    
    public void atualizaAreaTexto(String msg) {
        try {
            oDoc.insertString(0, msg + "\n", getAtributo());
//        JTextArea jt = (JTextArea)getComponente("areatexto");
//        jt.append(msg+"\n");
//        jt.revalidate();
//        jt.repaint();
        } catch (BadLocationException ex) {
            Logger.getLogger(ViewFrameCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizaCor(int cor) {
        StyleConstants.setForeground(atr, getCor(cor));
    }
    
    private SimpleAttributeSet getAtributo() {
        return atr;
    }
    
    public Component getComponente(String sNome) {
        Component comp = null;
        for(ViewPanel view : panels) {
            comp = view.getComponente(sNome);
            if(comp != null) {
                break;
            }
        }
        return comp;
    }
    
    public void conectar() throws Exception {
        oSoc = new Socket("127.0.0.1", 5566);
        oCon.setSocket(oSoc);
    }
    
    public void escutar() {
        try {
            oCon.processa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try {
            
            ViewFrameCliente v = new ViewFrameCliente();
//            v.conectar();
            v.escutar();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizaUsuario(String nome) {
        JLabel lb = (JLabel)getComponente("lbluser");
        lb.setText("Usuario: " + nome);
    }

    @Override
    public void atualizaEndereco(String endereco) {
        JLabel lb = (JLabel)getComponente("lblendereco");
        lb.setText("Endereco: " + endereco);
    }

    @Override
    public void atualizaPorta(String porta) {
        JLabel lb = (JLabel)getComponente("lblporta");
        lb.setText("Porta: " + porta);
    }

    @Override
    public void atualizaFont(String font) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizaCor(String cor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
