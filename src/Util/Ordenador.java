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

import Model.Aparicao;
import Model.Arquivo;
import Model.Palavra;

/**
 * Classe que possui métodos que aplicam o algoritmo de ordenação quickSort em um conjunto
 * de objetos específico.
 * @authors Johnny e Gustavo
 */
public class Ordenador {

    /**
     * Ordena um vetor de Aparicao a partir da quantidade de ocorrências de uma palavra no arquivo
     * que a Aparicao se refere.
     * @param aparicoesPraExibir vetor de Aparicao.
     * @param ordem "crescente" ou "decrescente".
     */
    public void ordenaPorRelevancia(Aparicao[] aparicoesPraExibir, String ordem) {
        if(ordem.equals("crescente")){
            quickSortCrescente(aparicoesPraExibir, 0, (aparicoesPraExibir.length)-1);
        }else{
            quickSortDecrescente(aparicoesPraExibir, 0, (aparicoesPraExibir.length)-1);
        }
    }
    
    /**
     * Ordena um vetor de Palavra a partir da quantidade de vezes que ela foi pesquisada.
     * @param palavras vetor de Palavra.
     * @param ordem "crescente" ou "decrescente".
     */
    public void ordenaPorQtPesquisas(Palavra[] palavras, int ordem) {
        if(ordem==1){
            quickSortCrescente(palavras, 0, (palavras.length)-1);
        }else{
            quickSortDecrescente(palavras, 0, (palavras.length)-1);
        }
    }
    
    /**
     * Ordena um vetor de Arquivo a partir da quantidade de vezes que o mesmo foi acessado
     * @param x vetor de Arquivo.
     * @param ordem "crescente" ou "decrescente".
     */
    public void ordenaPorQtAcessos(Arquivo[] x, int ordem) {
        if(ordem==1){
            quickSortCrescente(x, 0, (x.length)-1);
        }else{
            quickSortDecrescente(x, 0, (x.length)-1);
        }
    }
    
    /**
     * Aplica o quickSort em ordem crescente em um vetor de Aparicao.
     * @param a vetor de Aparicao
     * @param esquerda Primeiro índice a ser considerado no quickSort
     * @param direita Último índice a ser considerado no quickSort
     */
    private void quickSortCrescente(Aparicao[] a, int esquerda, int direita) {
        if(esquerda<direita){    
            int left = esquerda;
            int pivot = direita;
            int right = direita-1;
            while(left<=right){
                while(left<=right && a[left].compareToQtVezes(a[pivot]) < 0) left++;
                while(left<=right && a[right].compareToQtVezes(a[pivot]) > 0) right--;
                if(left <= right){
                    swap(a, left, right);
                    left++;
                    right--;
                }
            }
            swap(a, pivot, left);
            quickSortCrescente(a, esquerda, left-1);
            quickSortCrescente(a, left+1, direita);
        }              
    }
    
    /**
     * Aplica o quickSort em ordem decrescente em um vetor de Aparicao.
     * @param a vetor de Aparicao
     * @param esquerda Primeiro índice a ser considerado no quickSort
     * @param direita Último índice a ser considerado no quickSort
     */
    private void quickSortDecrescente(Aparicao[] a, int esquerda, int direita) {
        if(esquerda<direita){    
            int left = esquerda;
            int pivot = direita;
            int right = direita-1;
            while(left<=right){
                while(left<=right && a[left].compareToQtVezes(a[pivot]) > 0) left++;
                while(left<=right && a[right].compareToQtVezes(a[pivot]) < 0) right--;
                if(left <= right){
                    swap(a, left, right);
                    left++;
                    right--;
                }
            }
            swap(a, pivot, left);
            quickSortDecrescente(a, esquerda, left-1);
            quickSortDecrescente(a, left+1, direita);
        }              
    }
    
    /**
     * "Troca" dois valores de posição em um vetor
     * @param a O vetor
     * @param p1 Posição 1
     * @param p2 Posição 2
     */
    private void swap(Comparable[] a, int p1, int p2){
        Comparable temp = a[p1];
        a[p1] = a[p2];
        a[p2] = temp;
    }

    /**
     * Aplica o quickSort em ordem crescente em um vetor de Palavra.
     * @param a vetor de Palavra
     * @param esquerda Primeiro índice a ser considerado no quickSort
     * @param direita Último índice a ser considerado no quickSort
     */
    private void quickSortCrescente(Palavra[] a, int esquerda, int direita) {
        if(esquerda<direita){    
            int left = esquerda;
            int pivot = direita;
            int right = direita-1;
            while(left<=right){
                while(left<=right && a[left].compareToPesquisas(a[pivot]) < 0) left++;
                while(left<=right && a[right].compareToPesquisas(a[pivot]) > 0) right--;
                if(left <= right){
                    swap(a, left, right);
                    left++;
                    right--;
                }
            }
            swap(a, pivot, left);
            quickSortCrescente(a, esquerda, left-1);
            quickSortCrescente(a, left+1, direita);
        }     
    }

    /**
     * Aplica o quickSort em ordem decrescente em um vetor de Palavra.
     * @param a vetor de Palavra
     * @param esquerda Primeiro índice a ser considerado no quickSort
     * @param direita Último índice a ser considerado no quickSort
     */
    private void quickSortDecrescente(Palavra[] a, int esquerda, int direita) {
        if(esquerda<direita){    
            int left = esquerda;
            int pivot = direita;
            int right = direita-1;
            while(left<=right){
                while(left<=right && a[left].compareToPesquisas(a[pivot]) > 0) left++;
                while(left<=right && a[right].compareToPesquisas(a[pivot]) < 0) right--;
                if(left <= right){
                    swap(a, left, right);
                    left++;
                    right--;
                }
            }
            swap(a, pivot, left);
            quickSortDecrescente(a, esquerda, left-1);
            quickSortDecrescente(a, left+1, direita);
        }     
    } 

    /**
     * Aplica o quickSort em ordem crescente em um vetor de Arquivo.
     * @param x vetor de Arquivo
     * @param esquerda Primeiro índice a ser considerado no quickSort
     * @param direita Último índice a ser considerado no quickSort
     */
    private void quickSortCrescente(Arquivo[] x, int esquerda, int direita) {
        if(esquerda<direita){    
            int left = esquerda;
            int pivot = direita;
            int right = direita-1;
            while(left<=right){
                while(left<=right && x[left].compareToAcessos(x[pivot]) < 0) left++;
                while(left<=right && x[right].compareToAcessos(x[pivot]) > 0) right--;
                if(left <= right){
                    swap(x, left, right);
                    left++;
                    right--;
                }
            }
            swap(x, pivot, left);
            quickSortCrescente(x, esquerda, left-1);
            quickSortCrescente(x, left+1, direita);
        }  
    }

    /**
     * Aplica o quickSort em ordem decrescente em um vetor de Arquivo.
     * @param x vetor de Arquivo
     * @param esquerda Primeiro índice a ser considerado no quickSort
     * @param direita Último índice a ser considerado no quickSort
     */
    private void quickSortDecrescente(Arquivo[] x, int esquerda, int direita) {
        if(esquerda<direita){    
            int left = esquerda;
            int pivot = direita;
            int right = direita-1;
            while(left<=right){
                while(left<=right && x[left].compareToAcessos(x[pivot]) > 0) left++;
                while(left<=right && x[right].compareToAcessos(x[pivot]) < 0) right--;
                if(left <= right){
                    swap(x, left, right);
                    left++;
                    right--;
                }
            }
            swap(x, pivot, left);
            quickSortDecrescente(x, esquerda, left-1);
            quickSortDecrescente(x, left+1, direita);
        } 
    }
    
}
