/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ArquivoExterno {
	public static void salvar(String arquivo, String conteudo, boolean adicionar) throws IOException{
		FileWriter writer = new FileWriter(arquivo, adicionar);		
		writer.write(conteudo+"\n");
                writer.write("-----------------------------------------------------------\n");
		writer.close();
	}
	
	
	public static String carregar(String arquivo) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String conteudo = null;
        
        while (reader.ready()) {
        	conteudo += reader.readLine();
        }
        
        reader.close();
        return conteudo;
    }
	
	
}
