package app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import java.io.*;

public class Decompressor {

    private static Map<Character, String> extractedHuffmanTree = new HashMap<Character, String>();

    public void decompress(String fileName){  //https://bukkit.org/threads/saving-loading-hashmap.56447/   
                                                    //Adaptado por Vinícius Silva Grilo
        
        String dir = App.diretorio;
        String name = fileName;
        String huffmanString;
        String content = "";
        System.out.println("Pesquisando diretorio = " + dir + "\\" + name + ".dvz"); //<- verificar caminho se o SO != Windows
        System.out.println();                                            
        try{    //tratamento de excessões de E/S  
             
            Reader file = new FileReader(dir + "\\" + name + ".dvz");
            BufferedReader in = new BufferedReader(file);
            String linha = in.readLine();
            huffmanString = linha;
            while(linha != null){
                linha = in.readLine();
                content += " " + linha;
                if(linha == null);
                    break;
            }

            file.close();
            in.close();

            char key = ' ';
            String value = "";

            for(int i = 0; i <= huffmanString.length(); i++){

                if(i == huffmanString.length()){
                    if(key != ' ' && value != ""){
                        extractedHuffmanTree.put(key, value);
                        break;
                    }
                }

                char aux = huffmanString.charAt(i);

                if(Character.isLetter(aux) || i == huffmanString.length()){  //se for letra
                    if(key != ' ' && value != ""){
                        extractedHuffmanTree.put(key, value);
                    }
                    key = aux;
                    value = "";
        
                }else{  //se for dígito
                    value += aux;
                }   
            }         
            System.out.println("Arvore de Huffman: " + Arrays.asList(extractedHuffmanTree)); 
            System.out.println();
            System.out.println("Conteúdo do arquivo:" + content); 
            System.out.println();
            System.out.print("Conteúdo decodificado: ");
            System.out.println(this.decode(content));
            



            }catch(FileNotFoundException e) {
                System.err.println("Arquivo não existe!");
            }catch(IOException ex){
                System.err.println("Erro de leitura!");
            }
    }


    public String decode(String encoded){

        String decoded = "";
        String aux = "";
        
        for(int i = 1; i < encoded.length(); i++){

            aux += encoded.charAt(i);
            
            if(this.getKeyByValue(extractedHuffmanTree, aux) != null){
                System.out.print(this.getKeyByValue(extractedHuffmanTree, aux));
                aux = "";
            }
            //System.out.println(this.getKeyByValue(extractedHuffmanTree, aux));
            // if(this.getKeyByValue(extractedHuffmanTree, aux) != null){
            //     System.out.println(this.getKeyByValue(extractedHuffmanTree, aux));
            //     aux = "";
            // }else{
            //     aux += encoded.charAt(i);
            //     System.out.println(aux);
            // }
        }

        return decoded;

    }

    public <T, E> String getKeyByValue(Map<T, E> map, E value) {  //https://stackoverflow.com/questions/1383797/java-hashmap-how-to-get-key-from-value
                                                                  //Adaptado por Vinícius Silva Grilo
        for (Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey().toString();
            }
        }
        return null;
    }
    

        

}