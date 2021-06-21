package Estructures;

import Excepcions.CaracterNoValid;
import Excepcions.HeapPle;

public class Huffman {

    //Atributs
    private String text;
    private Diccionari diccionari;
    private Arbre arbre;

    //Constructor
    public Huffman(String text) throws CaracterNoValid, HeapPle {
        MinHeap heap = new MinHeap(text);
        this.arbre = heap.crearArbre();
        this.diccionari = arbre.crearDiccionari();
        this.text = codificar(text);
    }

    //Getters
    public String getText() {
        return text;
    }

    public Diccionari getDiccionari() {
        return diccionari;
    }

    public Arbre getArbre() {
        return arbre;
    }

    //Mètodes
    /**
     * Mètode que codifica el text
     * @return retorna el text codificat
     * @throws CaracterNoValid en cas que no es pugui codificar el text
     */
    public String codificar(String text) throws CaracterNoValid{
        StringBuilder sb = new StringBuilder();

        //recorrem el text i el codifiquem
        for (int i = 0; i < text.length(); i++){
            sb.append(diccionari.getCodi(text.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * Mètode que descodifica el text amb les dades de l'arbre
     * @return retorna el text descodificat
     */
    public String descodificar(){
        StringBuilder sb = new StringBuilder();
        Arbre.Node punterNode = this.arbre.getArrel();

        //Recorrem tot el text codificat
        for (int i = 0; i < this.text.length(); i++){
            if (punterNode.getFillDret() == null){
                sb.append(punterNode.getId());
                punterNode = arbre.getArrel();
            }
            if (text.charAt(i) =='0'){
                punterNode = punterNode.getFillEsquerre();
            }
            if (text.charAt(i) == '1'){
                punterNode = punterNode.getFillDret();
            }
        }
        sb.append(punterNode.getId());
        return sb.toString();
    }
}