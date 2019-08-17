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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Teste de unidade da classe Array, basicamente cria um novoArray de tamanho 1 e adiciona 5 objetos
 * nele, e verifica se os 5 objetos foram adicionados mesmo o array tendo sido inicialmente criado como
 * tamanho 1.
 * @authors Johnny e Gustavo
 */
public class ArrayTest {
    
    Array array;
    No no1;
    No no2;
    No no3;
    No no4;
    No no5;
    
    @Before
    public void setUp() {
        array = new Array(1);
        no1 = new No(1);
        no2 = new No(2);
        no3 = new No(3);
        no4 = new No(4);
        no5 = new No(5);
    }

    @Test
    public void testGeral() {
        array.add(no1);
        array.add(no2);
        array.add(no3);
        array.add(no4);
        array.add(no5);
        
        No[] comparar = {no1, no2, no3, no4, no5};
        
        Assert.assertArrayEquals(comparar, array.getArray());
        assertEquals(array.getArray().length, 5);
    }
    
}
