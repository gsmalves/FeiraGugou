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

import Util.No;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes da classe Palavra.
 * OBS: O compareTo não foi testado pois o mesmo já é testado automaticamente no OrdenadorTest.
 * @authors Johnny, Gustavo
 */
public class PalavraTest {
    
    Palavra p = new Palavra("java");

    /**
     * Adiciona uma aparição na palavra p e verifica se essa aparição está na árvore de aparições
     * dessa palavra.
     */
    @Test
    public void testAddAparicao() {
        File file = new File("aa");
        Arquivo x = new Arquivo(file);
        p.addAparicao(x);
        No[] n = p.getAparicoes().inorder().getArray();
        Aparicao[] a = new Aparicao[n.length];
        for(int i=0; i<n.length; i++){
            a[i] = (Aparicao)n[i].getChave();
        }
        assertEquals(a[0], new Aparicao(x));        
    }

    /**
     * adiciona uma aparição e, em seguida, adiciona uma aparição existente para ver se
     * a quantidade de vezes que a palavra aparece nesse arquivo está como 2.
     */
    @Test
    public void testAddAparicaoExistente() {
        File file = new File("aa");
        Arquivo x = new Arquivo (file);
        p.addAparicao(x);
        p.addAparicaoExistente(x, false);
        
        No[] n = p.getAparicoes().inorder().getArray();
        Aparicao[] a = new Aparicao[n.length];
        for(int i=0; i<n.length; i++){
            a[i] = (Aparicao)n[i].getChave();
        }
        System.out.println(a.length);
        assertEquals(a[0].getQtVezes(), 2); 
    }

    /**
     * Testa o toString da palavra.
     */
    @Test
    public void testToString() {
        assertEquals(p.toString(), "Palavra: java, pesquisada 0 vezes.");
    }

    /**
     * Testa o toString que imprime todas as aparições daquela palavra.
     */
    @Test
    public void testToString_int() {
        File fx = new File("a");
        File fy = new File("b");
        Arquivo x = new Arquivo (fx);
        Arquivo y = new Arquivo(fy);
        p.addAparicao(x);
        p.addAparicao(y);
        p.addAparicaoExistente(x, false);
        
        assertEquals(p.toString(1), "Palavra: java, qts. vezes foi pesquisada:0\n\t1) b\n\taparece 1 vezes.\n\t2) a\n\taparece 2 vezes.");
    }
    
}
