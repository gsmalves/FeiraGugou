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

import java.util.Arrays;

/**
 * Estrutura de dados que possui um vetor que duplica o seu tamanho máximo
 * sempre que necessário.
 * @authors Johnny e Gustavo
 */
public class Array{

    private int next;
    private int size;
    private No objetos[];
    
    /**
     * Construtor do Array dinâmico. É necessário informar o seu tamanho inicial.
     * @param size Tamanho mínimo do array.
     */
    public Array(int size){
        objetos = new No[size];
        this.size = size;
    }
    
    /**
     * Adiciona um objeto no array, duplica seu tamanho máximo caso o mesmo seja excedido.
     * @param data Objeto a ser adicionado.
     */
    public void add(No data) {
        if(next<size){
            objetos[next]=data;
            next++;
        }else{
            int newSize=size*2;
            No temp[] = new No[newSize];
            for(int i=0; i<size; i++){
                temp[i]=objetos[i];
            }
            objetos = temp;
            size=newSize;
            add(data);
        }
    }

    /**
     * Retorna o array de nós que está armazenado nessa estrutura de dados.
     * @return array de nós.
     */
    public No[] getArray() {
        No[] ret = Arrays.copyOfRange(objetos, 0, next);
        return ret;
    }  
    
}
