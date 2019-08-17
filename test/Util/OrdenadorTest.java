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
import java.io.File;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Essa classe testa as implementações do quickSort na classe Ordenador.
 * @authors Johnny e Gustavo
 */
public class OrdenadorTest {
    
    Ordenador ordenador;
    
    @Before
    public void setUp() {
        ordenador = new Ordenador();
    }

    /**
     * Testa o método que ordena arquivos por relevância, criando três Aparições com quantidades
     * diferentes de aparições e verificando se o resultado é um array ordenado adequadamente.
     */
    @Test
    public void testOrdenaPorRelevancia() {
        File f1 = new File("a.txt");
        Aparicao a1 = new Aparicao(new Arquivo(f1));
        for(int i = 1; i<=5; i++){
            a1.somaVezes();            
        }
        File f2 = new File("b.txt");
        Aparicao a2 = new Aparicao(new Arquivo(f2));
        for(int i = 1; i<0; i++){
            a1.somaVezes();            
        }
        File f3 = new File("c.txt");
        Aparicao a3 = new Aparicao(new Arquivo(f3));
        for(int i = 1; i<=2; i++){
            a1.somaVezes();            
        }
        
        Aparicao[] aparicoes = {a1, a2, a3};        
        ordenador.ordenaPorRelevancia(aparicoes, "crescente");
        Aparicao[] comparar = {a2, a3, a1};
        Assert.assertArrayEquals(aparicoes, comparar);
        
        ordenador.ordenaPorRelevancia(aparicoes, "decrescente");
        Aparicao[] comparar2 = {a1, a3, a2};
        Assert.assertArrayEquals(aparicoes, comparar2);        
    }

    /**
     * Testa o método que ordena palavras por quantidade de pesquisas, criando três Palavra com quantidades
     * diferentes de pesquisas e verificando se o resultado é um array ordenado adequadamente.
     */
    @Test
    public void testOrdenaPorQtPesquisas() {
        Palavra p1 = new Palavra("java");
        for(int i=1; i<18; i++){
            p1.somaPesquisas();
        }
        Palavra p2 = new Palavra("universo");
        for(int i=1; i<26; i++){
            p2.somaPesquisas();
        }
        Palavra p3 = new Palavra("janelas");
        for(int i=1; i<2; i++){
            p3.somaPesquisas();
        }
        Palavra[] palavras = {p1,p2,p3};
        
        ordenador.ordenaPorQtPesquisas(palavras, 1);
        Palavra[] comparar = {p3,p1,p2};
        Assert.assertArrayEquals(palavras, comparar);
        
        ordenador.ordenaPorQtPesquisas(palavras, 2);
        Palavra[] comparar2 = {p2,p1,p3};
        Assert.assertArrayEquals(palavras, comparar2);
    }

    /**
     * Testa o método que ordena arquivos por quantidade de acessos, criando três Arquivo com quantidades
     * diferentes de pacessos e verificando se o resultado é um array ordenado adequadamente.
     */
    @Test
    public void testOrdenaPorQtAcessos() {
        File f1 = new File("joaozinho");
        Arquivo a1 = new Arquivo(f1);
        for(int i=1; i<18; i++){
            a1.somaAcessos();
        }
        File f2 = new File("pedrinho");
        Arquivo a2 = new Arquivo(f2);
        for(int i=1; i<26; i++){
            a2.somaAcessos();
        }
        File f3 = new File("jjoaquina");
        Arquivo a3 = new Arquivo(f3);
        for(int i=1; i<2; i++){
            a3.somaAcessos();
        }
        Arquivo[] arquivos = {a1,a2,a3};
        
        ordenador.ordenaPorQtAcessos(arquivos, 1);
        Arquivo[] comparar = {a3,a1,a2};
        Assert.assertArrayEquals(arquivos, comparar);
        
        ordenador.ordenaPorQtAcessos(arquivos, 2);
        Arquivo[] comparar2 = {a2,a1,a3};
        Assert.assertArrayEquals(arquivos, comparar2);
    }
    
}
