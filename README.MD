1) O arquivo de texto não pode conter strings compostas, ou seja, não pode haver espaços entre palavras.

Ex.: "teste do codigo" -> não funciona
     "testedocodigo" -> funciona

2) O programa foi escrito utilizando o Visual Studio Code no Windows 10, logo poderão haver problemas de execução na hora
de ler o caminho dos arquivos. Se estiver utilizando Linux ou outro SO, favor verificar as linhas de codigo marcadas para 
verificação.

Ex.: public static final String diretorio = "C:\\SIN213_Projeto2\\teste.txt" -> funciona no Windows
     public static final String diretorio = "C:\SIN213_Projeto2\teste.txt" -> em outros SOs, pode ser que seja 
									      necessário alterar as barras do 
									      endereço

OBS.: O sistema cria a pasta C:\SIN213_Projeto2, mas não cria o arquivo .txt. Logo, crie um novo arquivo de texto e
escreva dentro dele.

3) Quando o arquivo .dvz é criado, a primeira linha representa a árvore de Huffman como uma String. 
Da segunda linhapara baixo representa o conteúdo do arquivo .txt codificado.
