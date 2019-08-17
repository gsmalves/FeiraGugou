
package Util;

import Model.Palavra;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
/**
 * Teste de unidade que verifica a inserção e remoção na árvore avl, de modo geral.
 * São inseridos 3 objetos, verificando o tamanho da árvore, se está vazia ou não e o balanceamento.
 * Em seguidas esses objetos são removidos e o tamanho é verificado, até a árvore ficar vazia.
 * @author Johnny e Gustavo
 */
public class ArvoreAvlTest {
    private ArvoreAvl arvore;
    private Palavra p1, p2, p3;
    private Comparable n, n1, n2;
    
    @Before
    public void setUp() {
        arvore = new ArvoreAvl();
        p1= new Palavra("Data1");
        p2= new Palavra("Data2");
        p3= new Palavra ("Data3");
    }
    
    @Test
     public void testGeral() {
        
        
        assertEquals(arvore.estaVazia(), true);
        arvore.inserir(p1);
        assertEquals(arvore.estaVazia(), false);
        assertEquals(arvore.getRaiz().getBalanceamento(), 0);
        assertEquals(arvore.tamanho(),1);
        arvore.inserir(p2);
        assertEquals(arvore.estaVazia(), false);
        assertEquals(arvore.getRaiz().getBalanceamento(), 1);
        assertEquals(arvore.tamanho(),2);
        arvore.inserir(p3);
        assertEquals(arvore.estaVazia(), false);
        assertEquals(arvore.getRaiz().getBalanceamento(), 0);
        assertEquals(arvore.tamanho(),3);
        arvore.remover(p3);
        assertEquals(arvore.tamanho(),2);
        arvore.remover(p2);
        assertEquals(arvore.tamanho(),1);
        arvore.remover(p1);
        assertEquals(arvore.tamanho(),0);
        assertEquals(arvore.estaVazia(), true);
        

        
        
     }
    
}
