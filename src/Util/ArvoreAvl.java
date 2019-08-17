/*******************************
Autores: Johnny Quest Dantas Pereira, Gustavo dos Santos Menezes Alves
Componente Curricular: MI-Programação
Concluido em: 27/06/2018
Declaro que este código foi elaborado por nós de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************/

package Util;

import java.io.Serializable;

/**
 * Esta classe trata-se de uma árvore binária de busca balanceada. As operações de busca,
 * inserção e remoção de elementos possuem complexidade O(log n) O(log n) no qual n é o número de elementos da árvore.
 * Nessa estrutura de dados cada elemento é chamado de nó.Sendo que cada nó armazena uma referencia para um objeto Comparable,
 * e três referências para outros nós, o nó paiz a subárvore da esquerda e a subárvore da direita. 
 * Essa estrutura de dados foi adaptada de um código pertencente a Rodrigo Vilar, disponível
 * em https://gist.github.com/rodrigovilar/5754425#file-arvoreavl-java. 
 * @authors Johnny e Gustavo
 */

 
public class ArvoreAvl implements Serializable{
    private int numeroDeNos; //atributo utilizado para contar a quantidade de nós da árvore
    private No raiz;
 /**
  * Método que transforma o objeto a ser inserido em um nó e passa para o método inserirAVL.
  * @param k Objeto a ser inserido na árvore
  */  
    public void inserir(Comparable k) {
        No n = new No(k);
        inserirAVL(this.raiz, n);
        numeroDeNos++;

    }
/**
 * Metódo que recebe um nó e faz a chamada do método inserirAVL,passando o mesmo
 * @param n Referência para um nó da árvore
 */
    public void inserir(No n) {
        inserirAVL(this.raiz, n);
        numeroDeNos++;

    }
/**
 * Método que auxilia na remoção do nó
 * @param k posição do nó a remover
 */
    public void remover(Comparable k) {
        removerAVL(this.raiz, k);
        numeroDeNos--;
    } 
    
    /**
     *Método que indica o tamanho da arvoré, de acordo com a quantidade de nós
     * @return Quantidade de nós da arvore
     */
    public int tamanho() {
	return numeroDeNos;
    }
	
    /**
     *  Método que indica se a arvore está vazia
     * @return Nulo, caso esteja vazia
     */
    public boolean estaVazia(){
	return raiz == null;
    }
    /**
     * @return A raíz.
     */
    public No getRaiz() {
        return raiz;
    }

/**
 * Método utilizado para auxiliar no ato de percorrer a arvore e salvar os objetos na estrutura Array
 * @return Uma estrutura array com os objetos percorridos na árvore
 */
    public Array inorder() {
        Array ret = new Array(5);
        inorder(raiz, ret);
        return ret;
    }
/**
 * Método recursivo que percorre a árvore binária até achar a posição correta para a inserção.
 * Após esta feita, é efetuado um calculo do fator de balanceamento e caso necessário o balanceamento
 * da árvore é feito.
 * @param aComparar Referência para um nó da árvore
 * @param aInserir Referência para um objeto igual, pelos critérios definidos no compareTo(), ao objeto buscado
 */
    private void inserirAVL(No aComparar, No aInserir){
        if (aComparar == null){
            this.raiz = aInserir;            
        }else{
            if (aInserir.getChave().compareTo(aComparar.getChave()) < 0){                
                if (aComparar.getEsquerda() == null) {
                    aComparar.setEsquerda(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento(aComparar);
                }else{
                    inserirAVL(aComparar.getEsquerda(), aInserir);
                }
            }else if(aInserir.getChave().compareTo(aComparar.getChave()) > 0){                
                if (aComparar.getDireita() == null) {
                    aComparar.setDireita(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento(aComparar);
                }else{
                    inserirAVL(aComparar.getDireita(), aInserir);
                }
            }
        }
    }
/**
 * Método que faz a verificação se árvore está ou não balanceada,
 * caso esteja desbalanceada são aplicadas as devidas rotações, a fim de organizá-la
 * @param atual Referência para um nó da árvore
 */
    private void verificarBalanceamento(No atual) {
        setBalanceamento(atual);
        int balanceamento = atual.getBalanceamento();
        if (balanceamento == -2) {
            if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
                atual = rotacaoDireita(atual);
            } else {
                atual = duplaRotacaoEsquerdaDireita(atual);
            }
        }else if (balanceamento == 2) {
            if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
                atual = rotacaoEsquerda(atual);
            }else{
                atual = duplaRotacaoDireitaEsquerda(atual);
            }
        }

        if (atual.getPai() != null) {
            verificarBalanceamento(atual.getPai());
        } else {
            this.raiz = atual;
        }
    }
/**
 * Método recursivo que percorre os nós da árvore até encontrar o elemento ao qual pretende-se remover
 * @param atual Referência para um nó da árvore
 * @param k Referente a posição do nó
 */
    private void removerAVL(No atual, Comparable k) {
        if (atual == null) {
            return;
        }else {
            if (atual.getChave().compareTo(k) > 0) {
                removerAVL(atual.getEsquerda(), k);
            }else if (atual.getChave().compareTo(k) < 0) {
                removerAVL(atual.getDireita(), k);
            }else if (atual.getChave().compareTo(k) == 0) {
                removerNoEncontrado(atual);
            }
        }
    }
/**
 * Método que remove o nó passado no argumento, de maneira a manter a integridade da estrutura,
 * verificando o desbalancemaneto e reorganizando de modo balanceado
 * @param aRemover Nó que fora encontrado e se deseja remover
 */
    private void removerNoEncontrado(No aRemover) {
        No r;
        if (aRemover.getEsquerda() == null || aRemover.getDireita() == null){
            if (aRemover.getPai() == null) {
                this.raiz = null;
                aRemover = null;
                return;
            }
            r = aRemover;
        }else{
            r = sucessor(aRemover);
            aRemover.setChave(r.getChave());
        }

        No p;
        if (r.getEsquerda() != null) {
            p = r.getEsquerda();
        } else {
            p = r.getDireita();
        }

        if (p != null) {
            p.setPai(r.getPai());
        }

        if (r.getPai() == null) {
            this.raiz = p;
        } else {
            if (r == r.getPai().getEsquerda()) {
                r.getPai().setEsquerda(p);
            }else{
                r.getPai().setDireita(p);
            }
            verificarBalanceamento(r.getPai());
        }
        r = null;
    }
/**
 * Método utilizado quando a diferença da altura h dos filhos do nó Pai é igual a 2,
 * e a diferença da altura dos filhos do nó Filho da Direita é igual a 1. O nó Filho da Direita deve tornar o novo pai,
 * e o nó Pai deve se tornar o filho da esquerda do nó Filho da Direita.
 * @param inicial Nó que se deseja aplicar a rotação
 * @return O novo nó pai
 */
    private No rotacaoEsquerda(No inicial) {
        No direita = inicial.getDireita();
        direita.setPai(inicial.getPai());

        inicial.setDireita(direita.getEsquerda());

        if (inicial.getDireita() != null) {
            inicial.getDireita().setPai(inicial);
        }

        direita.setEsquerda(inicial);
        inicial.setPai(direita);

        if (direita.getPai() != null) {
            if (direita.getPai().getDireita() == inicial) {
                direita.getPai().setDireita(direita);
            } else if (direita.getPai().getEsquerda() == inicial) {
                direita.getPai().setEsquerda(direita);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(direita);

        return direita;
    }
/**
 * Método utilizado quando a diferença da altura h dos filhos do nó Pai é igual a 2,
 * e a diferença da altura dos filhos do nó Filho da Esquerda é igual a 1. O nó Filho da Esquerda deve tornar o novo pai,
 * e o nó Pai deve se tornar o filho da direita do nó Filho da Esquerda.
 * @param inicial Nó que se deseja aplicar a rotação
 * @return O novo nó pai
 */
    private No rotacaoDireita(No inicial) {

        No esquerda = inicial.getEsquerda();
        esquerda.setPai(inicial.getPai());

        inicial.setEsquerda(esquerda.getDireita());

        if (inicial.getEsquerda() != null) {
            inicial.getEsquerda().setPai(inicial);
        }

        esquerda.setDireita(inicial);
        inicial.setPai(esquerda);

        if (esquerda.getPai() != null) {

            if (esquerda.getPai().getDireita() == inicial) {
                esquerda.getPai().setDireita(esquerda);

            } else if (esquerda.getPai().getEsquerda() == inicial) {
                esquerda.getPai().setEsquerda(esquerda);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(esquerda);

        return esquerda;
    }
/**
 * Método recurssivo utilizado quando  a diferença da altura dos filhos do nó Pai é igual a -2,
 * e a diferença da altura dos filhos do nó Filho da Esquerda é igual a 1,
 * nesse caso devemos aplicar uma rotação à esquerda no nó Filho da Esquerda e, em seguida, uma rotação à direita no nó Pai.
 * @param inicial Nó que se deseja aplicar a rotação
 * @return Chamada do método de rotação a direita
 */
    private No duplaRotacaoEsquerdaDireita(No inicial) {
        inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
        return rotacaoDireita(inicial);
    }
/**
 * Método recurssivo utilizado quando  a diferença da altura dos filhos do nó Pai é igual a -2,
 * e a diferença da altura dos filhos do nó Filho da Direita é igual a 1. 
 * nesse caso devemos aplicar uma rotação à direita no nó Filho da Direita e, em seguida, uma rotação à esquerda no nó Pai.
 * @param inicial Nó que se deseja aplicar a rotação
 * @return Chamada do método de rotação a esquerda
 */
    private No duplaRotacaoDireitaEsquerda(No inicial) {
        inicial.setDireita(rotacaoDireita(inicial.getDireita()));
        return rotacaoEsquerda(inicial);
    }
/**
 * Método utilizado para auxiliar na remoçaõ do nó
 * @param q Referência para um nó da árvore
 * @return Nó sucessor ao argumentado
 */
    private No sucessor(No q) {
        if (q.getDireita() != null) {
            No r = q.getDireita();
            while (r.getEsquerda() != null) {
                r = r.getEsquerda();
            }
            return r;
        } else {
            No p = q.getPai();
            while (p != null && q == p.getDireita()) {
                q = p;
                p = q.getPai();
            }
            return p;
        }
    }
/**
* Conta a altura do node que invoca o método 
* @param atual Nó que se deseja descobrir a altura
* @return A altura do nó
*/
    private int altura(No atual) {
        if (atual == null) {
            return -1;
        }
        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            return 0;
        } else if (atual.getEsquerda() == null) {
            return 1 + altura(atual.getDireita());
        } else if (atual.getDireita() == null) {
            return 1 + altura(atual.getEsquerda());
        } else {
            return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
        }
    }
/**
 * Método que auxilia na verificação  balanceamento do nó passado no argumento,
 * através do fator de balanceamento
 * @param no Nó da árvore que se dejesa verificar o balanceamento
 */
    private void setBalanceamento(No no) {
        no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
    }
/**
 * Método que percorre a árvore em ordem, anteriormente pelos filhos da esquerda e em seguida o nó pai,
 * e vai adcionando a referência para cada elemento em um array de objetos
 * @param no Nó inicial a ser percorrido até o nó folha
 * @param lista Estrutura utilizada para guardar as refrencias de cada objeto
 */
    private void inorder(No no, Array lista) {
        if (no == null) {
            return;
        }
        inorder(no.getEsquerda(), lista);
        lista.add(no);
        inorder(no.getDireita(), lista);
    }
}