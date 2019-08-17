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

import java.io.Serializable;

/**
 * Toda Palavra possui uma árvore de objetos desse tipo, que possui referencia pra um arquivo e
 * uma quantidade de vezes que tal palavra aparece nele.
 * @authors Johnny e Gustavo
 */
public class Aparicao implements Comparable, Serializable{
    
    private final Arquivo file;
    private int qtVezes;
    
    /**
     * @param file Arquivo que essa aparição representará.
     */
    public Aparicao(Arquivo file){
        this.file = file;
    }
    
    /**
     * Compara duas Aparições a partir da ordem alfabética.
     * @param t Aparição a ser comparada.
     * @return um número negativo caso essa Aparição deva vir "antes" da recebida, um número positivo
     * caso contrário e 0 caso sejam iguais.
     */
    @Override
    public int compareTo(Object t) {
        Aparicao temp = (Aparicao) t;
        String a = file.getName();
        String b = temp.getFile().getName();
        return a.compareTo(b);
    }
    
    /**
     * Compara duas Aparições a partir da quantidade de vezes que a palavra aparece no arquivo.
     * @param a Aparição a ser comparada.
     * @return um número negativo caso essa Aparição deva vir "antes" da recebida, um número positivo
     * caso contrário e 0 caso tenham a mesma quantidade de vezes.
     */
    public int compareToQtVezes(Aparicao a){
        return qtVezes - a.getQtVezes();
    }

    /**
     * @return quantidade de vezes que a palavra que possui essa Aparição aparece no arquivo que essa
     * Aparição representa.
     */
    public int getQtVezes() {
        return qtVezes;
    }
    
    /**
     * Soma em 1 a quantidade de vezes que a palavra que possui essa Aparicao aparece nesse arquivo.
     */
    public void somaVezes(){
        qtVezes++;
    }
    
    /**
     * @return Uma string com o nome do arquivo e quantas vezes a palavra que possui essa Aparicao
     * aparece nesse arquivo.
     */
    @Override
    public String toString(){
        return file.getPath() + "\n\taparece " + qtVezes + " vezes.";
    }
    
    /**
     * Verifica se duas Aparicoes são iguais a partir do nome dos seus arquivos.
     * @param a Aparição a ser comparada.
     * @return True caso sejam iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object a){
        Aparicao temp = (Aparicao) a;
        return this.file.equals(temp.getFile());
    }

    /**
     * @return O arquivo que esse objeto representa. 
     */
    public Arquivo getFile() {
        return file;
    }

    /**
     * Zera a quantidade de vezes que a palavra que possui essa aparição aparece no arquivo
     * que essa aparição representa.
     */
    public void zeraVezes() {
        qtVezes = 0;
    }
    
        
}
