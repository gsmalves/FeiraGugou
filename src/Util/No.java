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

package Util;

import java.io.Serializable;

/**
 * Essa é a classe que representa os nós da árvore Avl, com o nó pai, filho da direita e filho da esquerda.
 * Além disso existe uma chave Comparable, tornando o nó genérico e posteriormente a árvore Avl
 * Tal estrutura de dados foi adaptada de um código pertencente a Rodrigo Vilar, disponível
 * em https://gist.github.com/rodrigovilar/5754425#file-arvoreavl-java. * 
 * @authors Johnny e Gustavo
 */
public class No implements Comparable, Serializable{
  
    private No esquerda;
    private No direita;
    private No pai;
    private Comparable chave;
    private int balanceamento;
    /**
     * Construtor do nó que inicialmente tem seus nós como null e recebe um objeto a ser adicionado.
     * @param k Objeto Comparable recebido como um elemento a ser adicionado ao nó.
     */
    public No(Comparable k) {
        setEsquerda(setDireita(setPai(null)));
        setBalanceamento(0);
        setChave(k);
    }
    /**
     * @return  O elemneto(chave) em String.
     */
    @Override
    public String toString(){
        return chave.toString();
    }
    /**
     * Verifica se os nós são iguais a partir das chaves.
     * @param a Objeto a ser comparado.
     * @return True caso sejam iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object a){
        No temp = (No) a;
        return chave.equals(temp.getChave());
    }
    /**
     * @return O elemneto(chave) Comparable.
     */
    public Comparable getChave() {
        return chave;
    }
    /**
     * @param chave Objeto Comparable a ser inserido.
     */
    public void setChave(Comparable chave) {
        this.chave = chave;
    }
    /**
     * @return Valor balanceamento.
     */
    public int getBalanceamento() {
        return balanceamento;
    }
    /**
     * Adiciona o valor do balanceamento da árvore.
     * @param balanceamento Valor do balanceamento.
     */
    public void setBalanceamento(int balanceamento) {
        this.balanceamento = balanceamento;
    }
    /**
     * @return O nó pai
     */
    public No getPai() {
        return pai;
    }
    /**
     * Adiciona o nó pai.
     * @param pai Nó que se deseja adicionar.
     * @return Nó pai
     */
    public No setPai(No pai) {
        this.pai = pai;
        return pai;
    }
    /**
     * @return  Nó da direita.
     */
    public No getDireita() {
        return direita;
    }
    /**
     * Adiciona um nó a direita.
     * @param direita Nó que se deseja adcionar.
     * @return Nó da direita.
     */
    public No setDireita(No direita) {
        this.direita = direita;
        return direita;
    }
    /**
     * @return Nó da esquerda.
     */
    public No getEsquerda() {
        return esquerda;
    }
    /**
     * Adiciona um nó a esquerda.
     * @param esquerda Nó que se deseja adicionar.
     */
    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }
    /**
     * Compara um nó com o outro.
     *@param t Nó a se comparar.
     * @return um número negativo se esse elemento do nó vir antes do recebido, um número positivo
     * caso contrário e 0 caso sejam iguais.
     */
    @Override
    public int compareTo(Object t) {
        No temp = (No) t;
        return chave.compareTo(temp.getChave());
    }
}