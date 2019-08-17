
package Controller;

import Model.Arquivo;
import Model.Palavra;
import Util.Array;
import Util.ArvoreAvl;
import Util.No;
import Util.Ordenador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Controlador do programa, possui os métodos que serão chamados na View.
 * @author Johnny e Gustavo.
 */
public class Controller {
    
    private ArvoreAvl arvore = new ArvoreAvl();
    private ArvoreAvl arquivos = new ArvoreAvl();
    
    /**
     * Cria a árvore de palavras, lendo todos os arquivos e adicionando na árvore, 
     * palavra por palavra, com suas respectivas quantidades de aparições.
     */
    public void geraArvore() {
        
        boolean jaTemArvore = verificaSeJaTemArvore();
        File[] files = pegaArquivos();
        
        for(File t : files){
            if(t!=null){
                Arquivo x = verificaArvoreArquivos(t);
             
                try{
                    FileReader a = new FileReader(t);
                    BufferedReader rd = new BufferedReader(a);
                    String linha;
                    while((linha = rd.readLine()) != null){
                        linha = linha.replaceAll("[^A-Za-z0_-áàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ\\s]", "");
                        String[] linhaSeparada = linha.split(" ");
                        for(int i=0; i<linhaSeparada.length; i++){
                            Palavra procurar = new Palavra(linhaSeparada[i]);
                            Palavra encontrada;
                            if((encontrada = buscaBinaria(arvore.inorder(), procurar)) != null){
                                encontrada.addAparicaoExistente(x, jaTemArvore);
                            }else{
                                procurar.addAparicao(x);
                                No no = new No(procurar);
                                arvore.inserir(no);
                            }                 
                        }                                                              
                    }
                arquivos.inserir(x);
                }catch(Exception ex){
                    System.out.println(ex);
                }
            }            
        } 
    }

    /**
     * Método responsável pela pesquisa. Recebe a palavra pesquisada pelo usuário e retorna
     * o resultado esperado.
     * @param pesquisa Palavra inserida pelo usuário a ser pesquisada
     * @param ordem 1 para crescente, 2 para decrescente
     * @return A Palavra encontrada na árvore (ou gerada caso ela não se encontrava na mesma).
     */
    public Palavra geraResultado(String pesquisa, int ordem) {
        Array palavras = arvore.inorder();
        Palavra pesquisada = new Palavra(pesquisa);
        Palavra encontrada;
        if((encontrada = buscaBinaria(palavras, pesquisada)) != null){
            encontrada.somaPesquisas();
            salvaArvore();
            return encontrada;
        }else{
            pesquisada.somaPesquisas();
            arvore.inserir(pesquisada);
            salvaArvore();
            return pesquisada;
        }
    }  
    
    /**
     * Retorna um array com todos os arquivos na pasta repositório.
     * @return 
     */
    private File[] pegaArquivos() {
        File diretorio = new File("repositório");
        if(!diretorio.exists()){
            diretorio.mkdir();
        }
        File files[] = diretorio.listFiles();
        File novo[] = new File[files.length];
        for(int i=0; i<files.length; i++){
            if(files[i].getName().endsWith(".txt")){
                novo[i] = files[i];
            }
        }
        return novo;        
    }   

    /**
     * Busca um objeto Palavra em um array de Palavras
     * @param todasAsPalavras Array a ser procurado
     * @param procura Palavra a ser procurada
     * @return o objeto Palavra encontrado, ou null caso não encontre.
     */
    private Palavra buscaBinaria(Array todasAsPalavras, Palavra procura) {
        if(todasAsPalavras.getArray().length > 0){
            No[] nos = (No[])todasAsPalavras.getArray();
            Palavra[] array = new Palavra[nos.length];
            for(int i=0; i<nos.length; i++){
                array[i] = (Palavra) nos[i].getChave();
            }     

            int meio;
            int inicio = 0;
            int fim = (array.length)-1;        
            while(inicio <= fim){
                meio = (inicio + fim) / 2;
                if (array[meio].equals(procura)){
                    return array[meio];
                }else if(procura.compareTo(array[meio]) < 0){
                    fim = meio-1;
                }else if(procura.compareTo(array[meio]) > 0){
                    inicio = meio+1;
                }
            }
        }
        return null;
    }

    /**
     * Busca um objeto Arquivo em um array de Arquivos
     * @param todosArquivos Array a ser procurado
     * @param procura Arquivo a ser procurado
     * @return o objeto Arquivo encontrado, ou null caso não encontre.
     */
    private Arquivo buscaBinaria(Array todosArquivos, Arquivo procura) {
        if(todosArquivos.getArray().length > 0){
            No[] nos = (No[])todosArquivos.getArray();
            Arquivo[] array = new Arquivo[nos.length];
            for(int i=0; i<nos.length; i++){
                array[i] = (Arquivo) nos[i].getChave();
            }     

            int meio;
            int inicio = 0;
            int fim = (array.length)-1;        
            while(inicio <= fim){
                meio = (inicio + fim) / 2;
                if (array[meio].equals(procura)){
                    return array[meio];
                }else if(procura.compareTo(array[meio]) < 0){
                    fim = meio-1;
                }else if(procura.compareTo(array[meio]) > 0){
                    inicio = meio+1;
                }
            }
        }
        return null;
    }
    
    /**
     * Chama o método buscaBinaria anterior.
     * @param arquivos Array a ser procurado
     * @param procura Arquivo a ser procurada
     * @return o objeto Arquivo encontrado, ou null caso não encontre.
     */
    public Arquivo buscaBinaria(Arquivo[] arquivos, Arquivo procura) {
        Array arquivosArray = new Array(1);
        for(Arquivo z : arquivos){
            No no = new No(z);
            arquivosArray.add(no);
        }
        return buscaBinaria(arquivosArray, procura);
    }
    
    /**
     * Verifica se já existe um arquivo Árvore.avl (e desserializa ele) e faz o mesmo com
     * o Arquivos.avl.
     * @return True caso já existia um arquivo árvore de palavras, false caso contrário.
     */
    private boolean verificaSeJaTemArvore() {
        File file = new File("Arquivos.avl");
        if(file.exists()){
            try{
                FileInputStream arq = new FileInputStream(file);
                ObjectInputStream arquivosLidos = new ObjectInputStream(arq);
                arquivos = (ArvoreAvl) arquivosLidos.readObject();           
            }
            catch (Exception erro){
                System.out.println(erro);
            }
        }
        
        File file2 = new File("Arvore.avl");
        if(file2.exists()){
            try{
                FileInputStream arq = new FileInputStream(file2);
                ObjectInputStream arvoreLida = new ObjectInputStream(arq);
                arvore = (ArvoreAvl) arvoreLida.readObject();           
            }
            catch (Exception erro){
                System.out.println(erro);
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * Serialização da árvore avl de Palavras.
     */
    private void salvaArvore() {
        Array a = arvore.inorder();
        No[] b = a.getArray();
        for(No x : b){
            ((Palavra)x.getChave()).preparaPalavraPraSerSalva();
        }        
        try { 
            FileOutputStream arq = new FileOutputStream("Arvore.avl");
            ObjectOutputStream arvoreSalva = new ObjectOutputStream(arq);
            arvoreSalva.writeObject(arvore);               
            arvoreSalva.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Chama um método da classe Ordenador para ordenar as palavras pela quantidade de
     * vezes pesquisadas.
     * @param entrada2 1 para crescente, 2 para decrescente.
     * @return 
     */
    public Palavra[] ordenaPalavrasQtPesquisas(int entrada2) {
        Array x = arvore.inorder();
        No[] y = x.getArray();
        Palavra[] palavras = new Palavra[y.length];
        for(int i=0; i<y.length; i++){
            palavras[i] = (Palavra)y[i].getChave();
        }
        Ordenador ordenador = new Ordenador();
        ordenador.ordenaPorQtPesquisas(palavras, entrada2);
        return palavras;
    }

    /**
     * @return Retorna um vetor com todos os Arquivos já lidos.
     */
    public Arquivo[] getArquivos(){
        Array a = arquivos.inorder();
        No[] nos = a.getArray();
        Arquivo[] ret = new Arquivo[nos.length];
        for(int i=0; i<nos.length; i++){
            ret[i] = (Arquivo)nos[i].getChave();
        }
        return ret;
    }
    
    /**
     * Procura um arquivo na árvore avl de arquivos.
     * @param t Arquivo a ser procurado
     * @return Arquivo encontrado.
     */
    private Arquivo verificaArvoreArquivos(File t) {
        Arquivo procura = new Arquivo(t);
        Arquivo encontrou;
        
        if((encontrou = buscaBinaria(arquivos.inorder(), procura)) != null){
            return encontrou;
        }else{
            return procura;
        }      
    }

    /**
     * Serializa a árvore de arquivos.
     */
    public void salvaArquivos() {
        try { 
            FileOutputStream arq = new FileOutputStream("Arquivos.avl");
            ObjectOutputStream arquivosSalvo = new ObjectOutputStream(arq);
            arquivosSalvo.writeObject(arquivos);               
            arquivosSalvo.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
