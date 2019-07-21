
package servidor.cliente;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Arquivo;

/**
 *
 * @author Drew
 */
public class Cliente extends Thread {
    
    private String  sNome;
    private Socket  oSocket;
    private boolean bFinaliza;
    private Arquivo oArquivo;
    private int     iCor;
    private HashMap<String, Integer> colors;
    
    public Cliente(Socket oSoc) {
        this.colors    = new HashMap<>();
        setColors();
        
        this.oSocket   = oSoc;
        this.bFinaliza = false;
        this.oArquivo  = new Arquivo(oSocket, this);
        
        Random r = new Random();
        this.iCor = r.nextInt(11);
    }
    
    private void setColors() {
        colors.put("BLACK"     , 1);
        colors.put("BLUE"      , 2);
        colors.put("CYAN"      , 3);
        colors.put("DARK_GRAY" , 4);
        colors.put("GRAY"      , 5);
        colors.put("GREEN"     , 6);
        colors.put("LIGHT_GRAY", 7);
        colors.put("MAGENTA"   , 8);
        colors.put("ORANGE"    , 9);
        colors.put("PINK"      , 10);
        colors.put("RED"       , 11);
    }
    
    private int getColor(String cor) {
        return colors.get(cor);
    }
    
    public void setNome(String sNome) {
        this.sNome = sNome;
    }
    
    public String getNome() {
        return sNome;
    }
    
    public void setCor(String cor) {
        this.iCor = getColor(cor);
    }
    
    public int getCor() {
        return iCor;
    }
    
    public void escreve(String msg) throws Exception {
        oArquivo.enviar(msg);
    }
    
    public void processa() throws Exception {
        while(!bFinaliza) {
            oArquivo.recebe();
        }
    }

    @Override
    public void run() {
        try {
           processa();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                oSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
