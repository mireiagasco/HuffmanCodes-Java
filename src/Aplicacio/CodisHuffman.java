package Aplicacio;

import Estructures.Arbre;
import Estructures.Huffman;
import Excepcions.CaracterNoValid;
import Excepcions.HeapPle;

import java.util.Scanner;


public class CodisHuffman {

    public static void main(String[] args){
        Scanner teclat = new Scanner(System.in);
        String text;

        try {
            System.out.println("Indica el text que vols codificar:");
            text = teclat.nextLine();

            Huffman huffman = new Huffman(text);
            System.out.println(huffman.getArbre());
            System.out.println(huffman.getDiccionari());
            System.out.println("------------Missatge------------");
            System.out.println("Original: " + text);
            System.out.println("Codificat: " + huffman.getText() + " (llargada: " + huffman.getText().length() + ")");
            System.out.println("Descodificat: " + huffman.descodificar());
            System.out.println("\nMida del missatge: " + huffman.getText().length()/8 + " bytes");
            System.out.println("Mida de l'arbre de Huffman: " + Arbre.numNodes(huffman.getArbre().getArrel()) + " nodes");
            System.out.println("Mida total: " + huffman.getText().length()/8 + " bytes + " + (Arbre.numNodes(huffman.getArbre().getArrel())) + " * (mida(enter) + 2mida(punter) + mida(string)) + mida(punter) bytes");
        }
        catch (CaracterNoValid e){
            System.out.println(e + "\nNo es pot codificar la seqüència si hi ha caràcters no vàlids");
        }
        catch (HeapPle e){
            System.out.println(e);
        }
    }
}
