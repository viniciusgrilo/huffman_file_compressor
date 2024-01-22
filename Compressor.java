package app;

import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.HashMap; 
import java.util.Map; 

public class Compressor{

    private static Map<Character, String> huffmanTree = new HashMap<Character, String>();

    public void compress(String fileName, String compressedName){ //https://www.geeksforgeeks.org/print-characters-frequencies-order-occurrence/
        
        String str = this.readStandardFile(fileName);
        if(str == ""){
            System.out.println("Arquivo vazio!");
            return;
        }
        System.out.println("Conteúdo do arquivo: " + str);
        List<Character> charArrayList = new ArrayList<Character>();
        List<Integer> charFreqList = new ArrayList<Integer>();

         // size of the string 'str' 
        int n = str.length(); 
  
        // 'freq[]' implemented as hash table 
        int[] freq = new int[26];  
  
        // accumulate freqeuncy of each character 
        // in 'str' 
        for (int i = 0; i < n; i++) {
            freq[str.charAt(i) - 'a']++; 
        
        }

        // traverse 'str' from left to right 
        for (int i = 0; i < n; i++) { 
  
            // if frequency of character str.charAt(i) 
            // is not equal to 0 
            if (freq[str.charAt(i) - 'a'] != 0) { 
  
                // print the character along with its 
                // frequency 
                //System.out.print("char = " + str.charAt(i) + " "); 
                charArrayList.add(str.charAt(i));
                //System.out.println("freq = " + freq[str.charAt(i) - 'a']);  
                charFreqList.add(freq[str.charAt(i) - 'a']);
  
                // update frequency of str.charAt(i) to  
                // 0 so that the same character is not 
                // printed again 
                freq[str.charAt(i) - 'a'] = 0; 
            } 
        }   

        Iterator<Character> i = charArrayList.iterator();
        System.out.print("Vetor de char:       ");
        while(i.hasNext()){
            char aux = (char) i.next();
            System.out.print(aux + " ");
        }
    
        System.out.println();

        Iterator<Integer> j = charFreqList.iterator();
        System.out.print("Vetor de frequencia: ");
        while(j.hasNext()){
            int aux = (int) j.next();
            System.out.print(aux + " ");
        }
        System.out.println();
        System.out.println();

        this.huffmanEncode(charArrayList, charFreqList);  //linha de código que realiza a codificação da string

        System.out.println("Codificação de Huffman:"); 
        for(char c : huffmanTree.keySet()) {
            System.out.print(c + " = ");
            System.out.println(huffmanTree.get(c));
        }
        System.out.println();

        System.out.println("Conteúdo codificado");
        String compressedContent = "";
        for(int m = 0; m < n; m++) {
            System.out.print(huffmanTree.get(str.charAt(m)));
            compressedContent += huffmanTree.get(str.charAt(m));
        }
        System.out.println();
        System.out.println();


        this.createCompressedFile(compressedName, compressedContent);

        System.out.println("Arquivo comprimido (.dvz) criado com sucesso!");
        System.out.println("Arvore de Huffman: " + Arrays.asList(huffmanTree)); 

    } 


    public void huffmanEncode(List<Character> charArray, List<Integer> charFreq){  
        //https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
        //adaptado por Vinícius Silva Grilo

            int n = charArray.size();
       
            // creating a priority queue q. 
            // makes a min-priority queue(min-heap). 
            
            PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new MyComparator()); 
    
            for (int i = 0; i < n; i++) { 
    
                // creating a Huffman node object 
                // and add it to the priority queue. 
                HuffmanNode hn = new HuffmanNode(); 
    
                hn.c = charArray.get(i); 
                hn.data = charFreq.get(i); 
    
                hn.left = null; 
                hn.right = null; 
    
                // add functions adds 
                // the huffman node to the queue. 
                q.add(hn); 
            } 
    
            // create a root node 
            HuffmanNode root = null; 
    
            // Here we will extract the two minimum value 
            // from the heap each time until 
            // its size reduces to 1, extract until 
            // all the nodes are extracted. 
            while (q.size() > 1) { 
    
                // first min extract. 
                HuffmanNode x = q.peek(); 
                q.poll(); 
    
                // second min extarct. 
                HuffmanNode y = q.peek(); 
                q.poll(); 
    
                // new node f which is equal 
                HuffmanNode f = new HuffmanNode(); 
    
                // to the sum of the frequency of the two nodes 
                // assigning values to the f node. 
                f.data = x.data + y.data; 
                f.c = '-'; 
    
                // first extracted node as left child. 
                f.left = x; 
    
                // second extracted node as the right child. 
                f.right = y; 
    
                // marking the f node as the root node. 
                root = f; 
    
                // add this node to the priority-queue. 
                q.add(f); 
            } 
    
            // print the codes by traversing the tree 
            setHuffmanTree(root, ""); 
    }


    public void setHuffmanTree(HuffmanNode root, String s) { //https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
                                                             //adaptado por Vinícius Silva Grilo
        // base case; if the left and right are null
        // then its a leaf node and we print
        // the code s generated by traversing the tree.
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            huffmanTree.put(root.c, s);
            //System.out.println(root.c + ":" + s); // c is the character in the node
            return;
        }

        // if we go to left then add "0" to the code.
        // if we go to the right add"1" to the code.

        // recursive calls for left and
        // right sub-tree of the generated tree.
        setHuffmanTree(root.left, s + "0");
        setHuffmanTree(root.right, s + "1");
    }



    public String readStandardFile(String fileName) {

        String dir = App.diretorio;
        String name = fileName;
        String content = "";
        System.out.println("Pesquisando diretorio = " + dir + "\\" + name + ".txt"); //<- verificar caminho se o SO != Windows
        System.out.println();

        try{    //tratamento de excessões de E/S   
            Reader file = new FileReader(dir + "\\" + name + ".txt");
            BufferedReader in = new BufferedReader(file);
            String linha = in.readLine();
            if(linha == null){
                content = "";
            }else{
                content = linha;
                while(linha !=null){   
                    linha = in.readLine(); 
                    if(linha == null)
                        break;
                    content += linha;     
                }
            }
                
            file.close();
            in.close();
                
            }catch(FileNotFoundException e) {
                System.err.println("Arquivo não existe!");
            }catch(IOException ex){
                System.err.println("Erro de leitura!");
            }

        return content;
    }

    // manipulação de arquivos

    public void createCompressedFile(String fileName, String fileContent){
        File arquivo;
        FileWriter arquivoWriter;
        BufferedWriter arquivoBuffer; 
        String dir = App.diretorio;
        String content = fileContent;

        try{  //https://bukkit.org/threads/saving-loading-hashmap.56447/
              //adaptado por Vinícius Silva Grilo
            arquivo = new File(dir + "\\" + fileName + ".dvz"); //<- verificar caminho se o SO != Windows
            arquivoWriter = new FileWriter(arquivo);
            arquivoBuffer = new BufferedWriter(arquivoWriter);
            try{
                for(char c:huffmanTree.keySet()){
                    arquivoBuffer.write(c + huffmanTree.get(c));
                }
                arquivoBuffer.newLine();
                arquivoBuffer.write(content);
                arquivoBuffer.flush();
                arquivoBuffer.close();
            }catch(Exception e){
                e.printStackTrace();
            }           
            // this.writeHuffmanTree(arquivo);
            
            //arquivoBuffer.write(content);

            arquivoBuffer.close();
            arquivoWriter.close(); 
            
        }catch(IOException e) {
            System.err.println("Erro na criação!");
            e.printStackTrace();
        }
    }


    
     













    

    

  
}