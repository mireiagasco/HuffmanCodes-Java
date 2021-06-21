package Estructures;

import Excepcions.CaracterNoValid;
import Excepcions.HeapPle;

/**
 * MinHeap implementat sobre un vector:
 *      fill dret -> posició * 2
 *      fill esquerre -> (posició * 2) + 1
 *      pare -> posició/2
 */
public class MinHeap {

    //Atributs
    private Arbre[] heap;
    private int mida;

    private static final int midaMax = 256;
    private static final int primeraPos = 1;

    //Constructor
    public MinHeap(String text) throws CaracterNoValid, HeapPle {
        this.mida = 0;
        this.heap = new Arbre[this.midaMax];
        omplirHeap(text);
    }

    //Mètodes

    /**
     * Mètode que obté la posició del pare del node passat per paràmetre
     * @param posicio node del que volem cercar el pare
     * @return posició del pare
     */
    private int obtenirPosPare(int posicio){
        return posicio/2;
    }

    /**
     * Mètode que obté la posició del fill dret d'un node
     * @param posicio node del que volem cercar el fill dret
     * @return posició del fill dret
     */
    private int obtenirPosFillDret(int posicio){
        return posicio*2;
    }

    /**
     * Mètode que obté la posició del fill esquerre d'un node
     * @param posicio node del que volem cercar el fill esquerre
     * @return posició del fill esquerre
     */
    private int obtenirPosFillEsquerre(int posicio){
        return (posicio * 2) + 1;
    }

    /**
     * Mètode que, donat un text, calcula les freqüències de les lletres i les desa en un minHeap
     * @param text text que es vol codificar
     * @throws CaracterNoValid en cas que el text contingui algun caràcter no vàlid
     */
    private void omplirHeap(String text) throws CaracterNoValid, HeapPle {
        Diccionari diccionari = new Diccionari();

        //recorrem el text
        for (int i = 0; i < text.length(); i++){
            if (!diccionari.existeixClau(text.charAt(i))){    //si és el primer cop que trobem la lletra
                diccionari.afegir(text.charAt(i), Integer.toString(1));
            }
            else {
                int freq = Integer.parseInt(diccionari.getCodi(text.charAt(i)))+1;
                diccionari.afegir(text.charAt(i), Integer.toString(freq));
            }
        }

        //carreguem el heap amb els valors trobats
        for (Diccionari.Element element:diccionari.getDiccionari()){
            if (element != null){
                inserirNode(new Arbre(String.valueOf(element.getLletra()), Integer.parseInt(element.getCodi()), null, null));    //afegim el nou node a la llista
            }
        }
    }

    /**
     * Mètode que afegeix un nou node al heap
     * @param nouArbre arbre que es vol afegir al heap
     * @throws HeapPle en cas que no es pugui afegir el node
     */
    private void inserirNode(Arbre nouArbre) throws HeapPle {
        mida++;
        if (mida == midaMax) throw new HeapPle();

        heap[mida] = nouArbre;    //col·loquem el nou arbre al final del heap
        int actual = mida;

        if (mida > 1){
            //mentre la freqüència del nou arbre sigui menor que la del seu pare, anem intercanviant els nodes
            while (existeixPare(actual) && heap[actual].getFreq() < heap[obtenirPosPare(actual)].getFreq()){
                intercanviarNodes(actual, obtenirPosPare(actual));
                actual = obtenirPosPare(actual);
            }
        }

    }

    private boolean existeixPare(int posicio){
        return heap[obtenirPosPare(posicio)] != null;
    }

    /**
     * Mètode que intercanvia els nodes en dues posicions
     * @param actual node que es vol intercanviar
     * @param pare node que es vol intercanviar
     */
    private void intercanviarNodes(int actual, int pare){
        Arbre aux = heap[pare];
        heap[pare] = heap[actual];
        heap[actual] = aux;
    }

    /**
     * Mètode que crea l'arbre per fer la codificació de Huffman
     * @return l'arbre
     * @throws HeapPle en cas que hi hagi problemes d'espai en el heap
     */
    public Arbre crearArbre() throws HeapPle{
        int i = 0;
        while (mida > 1){
            //obtenim els dos valors mínims del heap
            Arbre aux1 = obtenirMinim();
            Arbre aux2 = obtenirMinim();

            int freq = aux1.getFreq() + aux2.getFreq();                    //calculem la freqüència del nou arbre
            inserirNode(new Arbre("n" + i, freq, aux1.getArrel(),aux2.getArrel()));       //afegim el nou arbre al heap
            i++;
        }
        return heap[primeraPos];
    }

    /**
     * Mètode que elimina l'element més petit del heap
     * @return retorna l'element més petit del heap
     */
    private Arbre obtenirMinim(){
        Arbre aux = heap[primeraPos];   //desem l'arbre en la primera posició del heap
        heap[primeraPos] = heap[mida];  //col·loquem l'últim node del heap en la primera posició
        heap[mida] = null;              //buidem l'última posició del heap
        mida--;                         //actualitzem la mida del heap
        if (mida > 1){
            reorganitzarHeap(primeraPos);
        }
        return aux;
    }

    /**
     * Mètode que elimina el node amb més prioritat del heap i reorganitza l'estructura perquè es mantingui l'ordre adequat
     * @param posicio posició que es vol eliminar
     */
    private void reorganitzarHeap(int posicio){

        int freqPare = heap[posicio].getFreq();
        int mesPetit = posicio;
        int auxFreq;
        int auxPos;

        //si el node te fills
        if (!esFulla(posicio)){
            //si no te fill dret
            if (heap[obtenirPosFillDret(posicio)] == null){
                if (heap[obtenirPosFillEsquerre(posicio)].getFreq() < freqPare){
                    mesPetit = obtenirPosFillEsquerre(posicio);
                }
            }
            //si no te fill esquerre
            if (heap[obtenirPosFillEsquerre(posicio)] == null){
                if (heap[obtenirPosFillDret(posicio)].getFreq() < freqPare){
                    mesPetit = obtenirPosFillDret(posicio);
                }
            }
            //si te els dos fills
            else {
                int freqDret = heap[obtenirPosFillDret(posicio)].getFreq();
                int freqEsq = heap[obtenirPosFillEsquerre(posicio)].getFreq();

                auxFreq = freqEsq;
                auxPos = obtenirPosFillEsquerre(posicio);
                if (freqDret < freqEsq){
                    auxFreq = freqDret;
                    auxPos = obtenirPosFillDret(posicio);
                }

                if (auxFreq < freqPare){
                    mesPetit = auxPos;
                }
            }
        }

        if (mesPetit != posicio){
            intercanviarNodes(posicio, mesPetit);
            reorganitzarHeap(mesPetit);
        }
    }

    private boolean esFulla(int posicio){
        return (heap[obtenirPosFillDret(posicio)] == null) && (heap[obtenirPosFillEsquerre(posicio)] == null);
    }

}
