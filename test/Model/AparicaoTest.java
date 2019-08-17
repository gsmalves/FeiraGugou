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

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Teste da classe Aparicao.
 * Getters não foram testados, e o compareTo já foi indiretamente testado no OrdenadorTest.
 * @author Johnny, Gustavo
 */
public class AparicaoTest {
    
    /**
     * Teste do toString.
     */
    @Test
    public void testToString() {
        File file = new File("joao");
        Aparicao a = new Aparicao(new Arquivo(file));
        assertEquals(a.toString(), "joao\n\taparece 0 vezes.");
        a.somaVezes();
        a.somaVezes();
        assertEquals(a.toString(), "joao\n\taparece 2 vezes.");
    }
    
}
