1) O arquivo de texto n�o pode conter strings compostas, ou seja, n�o pode haver espa�os entre palavras.

Ex.: "teste do codigo" -> n�o funciona
     "testedocodigo" -> funciona

2) O programa foi escrito utilizando o Visual Studio Code no Windows 10, logo poder�o haver problemas de execu��o na hora
de ler o caminho dos arquivos. Se estiver utilizando Linux ou outro SO, favor verificar as linhas de codigo marcadas para 
verifica��o.

Ex.: public static final String diretorio = "C:\\SIN213_Projeto2\\teste.txt" -> funciona no Windows
     public static final String diretorio = "C:\SIN213_Projeto2\teste.txt" -> em outros SOs, pode ser que seja 
									      necess�rio alterar as barras do 
									      endere�o

OBS.: O sistema cria a pasta C:\SIN213_Projeto2, mas n�o cria o arquivo .txt. Logo, crie um novo arquivo de texto e
escreva dentro dele.

3) Quando o arquivo .dvz � criado, a primeira linha representa a �rvore de Huffman como uma String. 
Da segunda linhapara baixo representa o conte�do do arquivo .txt codificado.
