package app;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static final String diretorio = "C:\\SIN213_Projeto2"; //<- verificar caminho se o SO != Windows

    public static void main(String[] args) {

        File dir = new File(App.diretorio);
        dir.mkdir();   
        
        Scanner scanner = new Scanner(System.in); 
        byte opcao = -1;

        System.out.println("LEIA O README ANTES DE TESTAR O PROGRAMA!!!!!"); 
        System.out.println();
        System.out.println("Diretório de pesquisa padrão (criado pelo sistema): " + diretorio + "\\..");
        System.out.println();

        do{
            try{
                System.out.println("Digite 0 para compactar um arquivo .txt existente");
                System.out.println("Digite 1 para descompactar um arquivo .dvz existente");
                opcao = scanner.nextByte();
                System.out.flush();
                System.out.println();
            }catch(InputMismatchException e){
                System.err.println("Favor digitar uma das opções disponíveis!");
                scanner.close();
                return;
            }
        }while(opcao < 0 || opcao > 1);
        
    
        switch(opcao){
            case 0:
                Compressor huffmanCompressor = new Compressor();
                System.out.println("Digite o nome do arquivo de texto ( não digite a extensão) que deseja comprimir: ");
                String fileName = scanner.next();
                System.out.flush();
                System.out.println("Dê um nome para o arquivo comprimido: ");
                String compressedName = scanner.next();
                System.out.flush();
                System.out.println();
                huffmanCompressor.compress(fileName, compressedName);  
                scanner.close();
                break;

            case 1:
                Decompressor huffmanDecompressor = new Decompressor();
                System.out.println("Digite o nome do arquivo dvz (não digite a extensão) que deseja descomprimir: ");
                String compressedFileName = scanner.next();
                huffmanDecompressor.decompress(compressedFileName);
                scanner.close();
                break;

            default:
                System.out.println("Opcão inválida");
                break;

        }
        
        
        System.out.println();
        
    } 

}