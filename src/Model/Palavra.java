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
package Model;

import Util.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe que representa uma Palavra, ela armazena a palavra, a quantidade de vezes que ela
 * foi pesquisada e uma árvore de Aparicao (todos os arquivos que essa palavra aparece).
 * @author Johnny e Gustavo
 */
public class Palavra implements Comparable, Serializable{
    
    private final String palavra;
    private int qtPesquisas;
    private ArvoreAvl aparicoes = new ArvoreAvl();
    private Aparicao[] aparicoesPraExibir;
    private boolean jaZerou;
    
    /**
     * Construtor de Palavra, recebe a palavra que a define.
     * @param a a palavra que esse objeto Palavra representa.
     */
    public Palavra(String a){
        palavra = a;
    }

    /**
     * Recebe um arquivo, cria um objeto Aparicao com ele e o adiciona na lista de
     * Aparicao dessa palavra.
     * @param x Arquivo a ser adicionado.
     */
    public void addAparicao(Arquivo x){
        Aparicao aparicao = new Aparicao(x);
        aparicao.somaVezes();
        aparicoes.inserir(aparicao);
    }
    
    /**
     * Caso durante a leitura de um arquivo, seja encontrada uma palavra que já se encontra na árvore,
     * esse método é chamado. Caso já exista um arquivo árvore salvo e seja a primeira vez que o programa
     * está tentando acessar essa palavra, ela será "zerada", todas as quantidades de vezes que ela aparece
     * em cada um dos arquivos será zerada para que seja contabilizada novamente ao ler de novo o arquivo.
     * Após isso, o método irá procurar esse arquivo na árvore de aparicões dessa palavra...
     * Se ele existir, a quantidade de vezes é somada. Caso contrário, será uma nova aparição a ser
     * adicionada na árvore de aparições dessa palavra.
     * @param x Arquivo que essa palavra foi encontrada
     * @param jaTemArquivo True caso a árvore já tenha sido gerada alguma vez, false caso contrário.
     */
    public void addAparicaoExistente(Arquivo x, boolean jaTemArquivo) {
        Array a = aparicoes.inorder();
        No[] n = a.getArray();
        Aparicao[] ap = new Aparicao[n.length];
        for(int i=0; i<n.length; i++){
            ap[i] = (Aparicao)n[i].getChave();
            if(jaTemArquivo && !jaZerou)
                ap[i].zeraVezes();
        }
        Aparicao encontrada;
        Aparicao procura = new Aparicao(x);
        if((encontrada = buscaBinaria(ap, procura)) != null){
            encontrada.somaVezes();
        }else{
            procura.somaVezes();
            aparicoes.inserir(procura);
        }
        
        if(jaTemArquivo && !jaZerou)
            jaZerou = true;
    }
    /**
     * @return a palavra
     */
    public String getPalavra() {
        return palavra;
    }

    /**
     * @return a quantidade de vezes que essa palavra foi pesquisada
     */
    public int getQtPesquisas() {
        return qtPesquisas;
    }
    
    /**
     * soma em 1 a quantidade de vezes que essa palavra foi pesquisada.
     */
    public void somaPesquisas(){
        qtPesquisas++;
    }
    
    /**
     * Modifica o atributo interno "jaZerou" pra false, para que quando essa palavra seja
     * acessada novamente após a árvore de palavras ter sido gerada uma vez, as quantidades
     * de vezes que essa palavra aparece em cada arquivo sejam zeradas, para poderem ser
     * somadas novamente.
     */
    public void preparaPalavraPraSerSalva(){
        jaZerou = false;
    }

    /**
     * @param ordem 1 para ordem crescente e 2 para ordem decrescente
     * @return Uma string com todos os arquivos em que essa palavra aparece e suas respectivas
     * quantidades de vezes.
     */
    public String toString(int ordem) {
        String ret = "Palavra: " + palavra + ", qts. vezes foi pesquisada:" + qtPesquisas;
        Array a = aparicoes.inorder();
        No[] noAparicao = a.getArray();
        Aparicao[] ap = new Aparicao[noAparicao.length];
        
        for(int i=0; i<noAparicao.length; i++){
            ap[i] = (Aparicao)noAparicao[i].getChave();
        }
        
        aparicoesPraExibir = Arrays.copyOf(ap, ap.length);
        Ordenador ordenador = new Ordenador();
        if(ordem==1)
            ordenador.ordenaPorRelevancia(aparicoesPraExibir, "crescente");
        else
            ordenador.ordenaPorRelevancia(aparicoesPraExibir, "decrescente");
        int i=1;
        for (Aparicao x : aparicoesPraExibir) {
            String temp = ret.concat("\n\t" + i + ") " + x.toString());
            ret = temp;
            i++;
        }
        return ret;
    }
    
    /**
     * @return Uma string com a palavra e a quantidade de vezes que ela foi pesquisada.
     */
    @Override
    public String toString(){
        return "Palavra: " + this.palavra + " -> pesquisada " + qtPesquisas + " vezes.";
    }

    /**
     * Compara essa palavra com outra por ordem alfabética.
     * @param t Palavra a se comparar
     * @return um número negativo se essa palavra vir antes da recebida, um número positivo
     * caso contrário e 0 caso sejam iguais.
     */
    @Override
    public int compareTo(Object t) {
        Palavra temp = (Palavra) t;
        return palavra.compareToIgnoreCase(temp.getPalavra());
    }
    
    /**
     * Compara essa palavra com outra baseado na quantidade de vezes que ela foi pesquisada.
     * @param t Palavra a se comparar
     * @return um número negativo se essa palavra vir antes da recebida, um número positivo
     * caso contrário e 0 caso tenham a mesma quantidade de vezes pesquisadas.
     */
    public int compareToPesquisas(Palavra t){
        return this.qtPesquisas - t.getQtPesquisas();
    }
    
    /**
     * Verifica se uma Palavra é igual a outra.
     * @param a Palavra a ser comparada
     * @return True caso sejam iguais, False caso contrário.
     */
    @Override
    public boolean equals(Object a){
        Palavra x = (Palavra) a;
        return this.palavra.equalsIgnoreCase(x.getPalavra());
    }

    /**
     * @return árvore de apariçoes dessa Palavra
     */
    public ArvoreAvl getAparicoes() {
        return aparicoes;
    }   

    /**
     * Busca uma aparição específica em um array de aparições. Este método possui complexidade
     * O(log n).
     * @param ap array de aparições
     * @param aparicao Aparicao a ser encontrada.
     * @return O objeto aparição encontrado ou null caso não seja encontrado
     */
    private Aparicao buscaBinaria(Aparicao[] ap, Aparicao aparicao) {
              
        int meio;
        int inicio = 0;
        int fim = (ap.length)-1;        
        while(inicio <= fim){
            meio = (inicio + fim) / 2;
            if (ap[meio].equals(aparicao)){
                return ap[meio];
            }else if(aparicao.compareTo(ap[meio]) < 0){
                fim = meio-1;
            }else if(aparicao.compareTo(ap[meio]) > 0){
                inicio = meio+1;
            }
        }
        return null;
    }

    /**
     * @return Um vetor com todas as aparições que são exibidas no toString(int ordem)
     */
    public Aparicao[] getAparicoesPraExibir() {
        return aparicoesPraExibir;
    }    
    
}
