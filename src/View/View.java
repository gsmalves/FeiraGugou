/*****************************************************************************************
Autores: Johnny Quest Dantas Pereira, Gustavo dos Santos Menezes Alves
Componente Curricular: MI-Programação
Concluido em: 27/06/2018
Declaramos que este código foi elaborado por nós de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package View;

import Controller.Controller;
import Model.Aparicao;
import Model.Arquivo;
import Model.Palavra;
import Util.Ordenador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Classe principal do programa, trata de tudo o que será visualizado pelo usuário.
 * @authors Johnny e Gustavo
 */
public class View {
    
    private static Controller controller;
    private static Scanner scanner;
    
    /**
     * Método main, que exibe o menu principal do programa, recebe todas as entradas do usuário
     * e chama métodos do controller sempre que necessário.
     * @param args Null, esse programa não usa argumentos de linha de comando.
     */
    public static void main(String[] args) {
        controller = new Controller();
        scanner = new Scanner(System.in, "latin1");       
        int entrada=0;
        while(entrada != 5){
            System.out.println("///////////////////////////////////////////////");
            System.out.print("[1] Pesquisar palavra\n[2] Top 10 palavras buscadas"
                + "\n[3] Top 10 páginas visitadas\n[4] Como utilizar o programa\n[5] Sair\n-> ");
            entrada = scanner.nextInt();
            scanner.nextLine();
            if(entrada!=5)
                System.out.println("\t(Os arquivos estão sendo lidos, aguarde...)");
            controller.geraArvore();
            
            switch(entrada){
                case 1:                         
                    System.out.print("Palavra a ser pesquisada: ");
                    String pesquisa = scanner.nextLine();
                    System.out.print("[1] Ordem crescente de relevância\n[2] Ordem decrescente de relevância\n-> ");
                    int ordem = scanner.nextInt();
                    scanner.nextLine();
                    Palavra resultado = controller.geraResultado(pesquisa, ordem);
                    System.out.println(resultado.toString(ordem));
                    if(resultado.getAparicoes().inorder().getArray().length == 0){
                        System.out.println("Essa palavra não está presente em nenhum arquivo!");
                    }else{                    
                        System.out.print("Digite o número correspondente a um arquivo para visualizá-lo."
                                + "\n(Digite 0 para voltar o menu inicial)\n-> ");
                        int entrada2 = scanner.nextInt();
                        scanner.nextLine();
                        
                        if(entrada2 != 0 && entrada2 > resultado.getAparicoes().inorder().getArray().length
                                || entrada2 < 0){
                            System.out.println("Arquivo inexistente.");
                        }else if(entrada2 != 0){
                            Aparicao[] pImprimir = resultado.getAparicoesPraExibir();
                            Arquivo[] arrayPraSomar = controller.getArquivos();
                            Arquivo pSomar = controller.buscaBinaria(arrayPraSomar, pImprimir[entrada2-1].getFile());
                            pSomar.somaAcessos();
                            imprimeArquivo(pImprimir[entrada2-1].getFile().getFile());
                            controller.salvaArquivos();
                        }
                    }
                    break;
                case 2:
                    System.out.print("[1] Ordem crescente\n[2] Ordem decrescente\n-> ");
                    int entrada2 = scanner.nextInt();
                    scanner.nextLine();
                    if(entrada2 > 0 && entrada2 < 3){
                        Palavra[] ordenadas = controller.ordenaPalavrasQtPesquisas(entrada2);
                        if(ordenadas.length==0){
                            System.out.println("ERRO:\nVerifique se existe algum arquivo na pasta.");
                        }else{
                            int j=0;
                            for(int i=0; i<ordenadas.length; i++){
                                if(i<ordenadas.length && ordenadas[i].getQtPesquisas() > 0 && j<=10){
                                    System.out.println(ordenadas[i]);
                                    j++;
                                }
                            }
                            if(j==0){
                                System.out.println("Nenhuma palavra foi pesquisada!");
                            }
                        }
                    }
                    break;
                case 3:
                    Arquivo[] x = controller.getArquivos();
                    System.out.print("[1] Ordem crescente\n[2] Ordem decrescente\n-> ");
                    entrada2 = scanner.nextInt();
                    scanner.nextLine();
                    Ordenador ordenador = new Ordenador();
                    ordenador.ordenaPorQtAcessos(x, entrada2);
                    int j=0;
                    for(int i=0; i<x.length; i++){
                        if(i<x.length && x[i].getAcessos() > 0 && j<=10){
                            System.out.println(x[i]);
                            j++;
                        }
                    }
                    if(j==0)
                            System.out.println("Nenhum arquivo foi acessado!");
                    break;
                case 4:
                    System.out.println("Coloque arquivos .txt dentro da pasta ''repositório'' e através desse programa, "
                            + "você poderá pesquisar palavras \na partir da opção 1 do menu e receber uma lista de "
                            + "arquivos onde essa palavra se encontra e quantas vezes em cada. \nVocê pode escolher "
                            + "ordenar a lista do mais relevantes para o menos relevante e vice-versa (considere como "
                            + "critério \nde relevância a quantidade de vezes que a palavra x aparece no arquivo.\n"
                            + "Além disso, é possível visualizar as top palavras mais buscadas ou menos buscadas a "
                            + "partir da opção 2.\nNa opção 3, você pode visualizar quais foram os arquivos mais acessados"
                            + "ou os menos acessados pelo programa.");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Comando inválido");
            }
        }
    }

    /**
     * Imprime todas as linhas de um arquivo .txt na tela.
     * @param file Arquivo a ser impresso.
     */
    private static void imprimeArquivo(File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String linha;
            while((linha = br.readLine()) != null){
                System.out.println(linha);        
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
