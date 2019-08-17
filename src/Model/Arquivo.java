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
import java.io.Serializable;

/**
 * Essa classe representa um arquivo. Possui uma referência a um arquivo e sua respectiva quantidade
 * de vezes que foi acessado.
 * @authors Johnny e Gustavo
 */
public class Arquivo implements Comparable, Serializable{
    
    private File file;
    private int qntAcessos;
     
    /**
     * @param x Referência ao arquivo que esse objeto representará.
     */
    public Arquivo(File x){
        file = x;
    }
    
    /**
     * Soma em 1 a quantidade de acessos desse arquivo.
     */
    public void somaAcessos(){
        qntAcessos++;
    }
    
    /**
     * @return Quantidade de vezes que esse arquivo foi acessado. 
     */
    public int getAcessos(){
        return qntAcessos;
    }
    
    /**
     * @return Referência do arquivo desse objeto. 
     */
    public File getFile(){
        return file;
    }

    /**
     * Compara esse objeto Arquivo com outro em ordem alfabética.
     * @param t Arquivo a ser comparado
     * @return um número negativo caso esse Arquivo deva "vir antes" do recebido, um número positivo
     * caso contrário e 0 caso sejam iguais.
     */
    @Override
    public int compareTo(Object t) {
        Arquivo temp = (Arquivo) t;
        return this.getName().compareTo(temp.getName());
    }
    
    /**
     * Veriifica se esse arquivo é igual a um recebido. O parâmetro a ser analisado é o nome do
     * arquivo referenciado.
     * @param a Arquivo a ser verificado
     * @return True caso sejam iguais, False caso contrário.
     */
    @Override
    public boolean equals(Object a){
        Arquivo temp = (Arquivo) a;
        return file.getName().equals(temp.getName());
    }
    
    /**
     * Compara esse objeto Arquivo com outro baseado na quantidade de acessos.
     * @param x Arquivo a ser comparado
     * @return um número negativo caso esse Arquivo deva "vir antes" do recebido, um número positivo
     * caso contrário e 0 caso tenham a mesma quantidade de acessos.
     */
    public int compareToAcessos(Arquivo x){
        return this.qntAcessos - x.getAcessos();
    }

    /**
     * @return diretório do arquivo que esse objeto representa
     */
    public String getPath() {
        return file.getPath();
    }

    /**
     * @return Nome do arquivo 
     */
    public String getName() {
        return file.getName();
    }
    
    /**
     * @return Uma string com o nome do arquivo e a quantidade de vezes que ele foi acessado pelo usuário. 
     */
    @Override
    public String toString(){
        return "Arquivo: " + file.getName() + ", acessado " + qntAcessos + " vezes.";
    }
        
}
