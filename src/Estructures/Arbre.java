package Estructures;

public class Arbre {

    //Nodes de l'arbre
    public class Node {

        //Atributs
        private String id;
        private int freq;
        private Node fillDret;
        private Node fillEsquerre;

        //Constructor
        public Node(String id, int freq, Node fillDret, Node fillEsquerre){
            this.id = id;
            this.freq = freq;
            this.fillDret = fillDret;
            this.fillEsquerre = fillEsquerre;
        }

        //Getters
        public String getId() {
            return id;
        }

        public int getFreq() {
            return freq;
        }

        public Node getFillDret() {
            return fillDret;
        }

        public Node getFillEsquerre() {
            return fillEsquerre;
        }
    }

    //Atributs
    private Node arrel;

    //Constructor
    public Arbre(String lletra, int freq, Node fillDret, Node fillEsquerre){
        this.arrel = new Node(lletra, freq, fillDret, fillEsquerre);
    }

    //Getters & Setters
    public Node getArrel() {
        return arrel;
    }

    public void setArrel(Node arrel) {
        this.arrel = arrel;
    }

    public int getFreq(){
        return this.arrel.freq;
    }


    //Mètode toString i auxiliars
    public String toString(){
        return escriureArrel(arrel);
    }

    private String escriureArrel(Node node){
        StringBuilder sb = new StringBuilder();
        sb.append("--------------Arbre--------------\n");
        if (node!= null){
            sb.append(node.id);

            String pointerDreta = "└──";
            String pointerEsquerra = (node.fillDret != null) ? "├──" : "└──";

            escriureNode(node.fillEsquerre, sb, "", pointerEsquerra, node.fillDret != null);
            escriureNode(node.fillDret, sb, "", pointerDreta, false);
        }
        return sb.toString();
    }

    private void escriureNode(Node node, StringBuilder sb, String padding, String pointer, boolean teNodeDret){
        if (node != null){
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.id);

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (teNodeDret){
                paddingBuilder.append("│  ");
            }
            else {
                paddingBuilder.append("   ");
            }

            String paddingPelsDos = paddingBuilder.toString();
            String pointerDreta = "└──";
            String pointerEsquerra = (node.fillDret != null) ? "├──" : "└──";

            escriureNode(node.fillEsquerre, sb, paddingPelsDos, pointerEsquerra, node.fillDret != null);
            escriureNode(node.fillDret, sb, paddingPelsDos, pointerDreta, false);
        }
    }

    //Mètodes per crear el diccionari

    /**
     * Mètode que crea un diccionari a partir d'un arbre
     * @return el nou diccionari
     */
    public Diccionari crearDiccionari(){
        Diccionari diccionari = new Diccionari();
        StringBuilder sb = new StringBuilder();

        codificarNode(arrel, diccionari, sb);

        return diccionari;
    }

    /**
     * Mètode que codifica els valors de l'arbre
     * @param node node que es vol codificar
     * @param diccionari diccionari en el que el volem desar
     * @param sb codificació del valor
     */
    private void codificarNode(Node node, Diccionari diccionari, StringBuilder sb){

        if (node.fillEsquerre != null){
            codificarNode(node.fillEsquerre, diccionari, sb.append(0));
        }
        else {
            diccionari.afegir(node.id.charAt(0), sb.toString());
        }
        if (node.fillDret != null){
            codificarNode(node.fillDret, diccionari, sb.append(1));
        }
        if (sb.length() > 0){
            sb.deleteCharAt(sb.length()-1);
        }
    }

    /**
     * Mètode que compta quants nodes té un arbre
     * @param arrel arrel de l'arbre que es vol comptar
     * @return retorna el número de nodes de l'arbre
     */
    public static int numNodes(Node arrel){
        return arrel == null ? 0 : numNodes(arrel.fillDret) + numNodes(arrel.fillEsquerre) + 1;
    }
}
