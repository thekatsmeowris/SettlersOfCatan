/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author makogenq
 */
public class BoardHelper {
    
    BoardHelper(){
        
    }
    
    void saveBoard(HexBoard board) throws Exception{
            String boardFile="SoMBoard.xml";
            
            
                XMLEncoder encoder= new XMLEncoder(new BufferedOutputStream(new FileOutputStream(boardFile)));
                encoder.writeObject(board);
                encoder.close();
                
        }
        
        ///loadBoard allows a generated board to be loaded
        private HexBoard loadHexBoard (String loadFile) throws Exception{
        XMLDecoder decoder= new XMLDecoder(new BufferedInputStream(new FileInputStream(loadFile)));
        HexBoard hexBoard=(HexBoard)decoder.readObject();
        decoder.close();
        
        return hexBoard;
        }
        HexBoard loadHexBoard () throws Exception{
        String boardFile="SoMBoard.xml";
        XMLDecoder decoder= new XMLDecoder(new BufferedInputStream(new FileInputStream(boardFile)));
        HexBoard hexBoard=(HexBoard)decoder.readObject();
        decoder.close();
        
        return hexBoard;
        }
    
}
