package Estructures;

import Excepcions.CaracterNoValid;

/**
 * Classe amb el diccionari (taula amb les lletres i els seus codis associats)
 */
public class Diccionari {

    /**
     * Classe que defineix els elements del diccionari, amb la seva lletra i el codi associat
     */
    public class Element {

        private char lletra;    //lletra
        private String codi;   //codi

        public Element(char lletra, String codi){
            this.lletra = lletra;
            this.codi = codi;
        }

        //Getters
        public char getLletra() {
            return lletra;
        }

        public String getCodi() {
            return codi;
        }
    }

    //Atributs
    private final int llargada;
    private Element[] diccionari;

    //Constructor
    public Diccionari(){
        this.llargada = 255;
        diccionari = new Element[llargada];
    }

    //Setters & Getters
    public Element[] getDiccionari() {
        return diccionari;
    }

    public void setDiccionari(Element[] diccionari) {
        this.diccionari = diccionari;
    }


    //Mètodes
    /**
     * Mètode que afegeix un nou element al diccionari
     * @param lletra lletra que es vol afegir
     * @param codi codi associat a la lletra
     */
    public void afegir(char lletra, String codi){
        Element nouElement = new Element(lletra, codi);
        diccionari[lletra] = nouElement;
    }

    /**
     * Mètode que obté el codi associat a una lletra
     * @param lletra lletra de la que volem el codi
     * @return retorna el codi de la lletra
     */
    public String getCodi(char lletra) throws CaracterNoValid{
        String codi = "No s'ha trobat la lletra " + lletra;
        try {
            if (diccionari[lletra] != null){
                codi = diccionari[lletra].codi;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new CaracterNoValid(lletra);
        }
        return codi;
    }

    public String toString(){
        String text = "------------Diccionari------------\n";
        for (Element element:diccionari){
            if (element != null){
                text += element.lletra + " = " + element.codi + "\n";
            }
        }
        return text;
    }

    /**
     * Mètode que comprova si existeix una clau en el diccionari
     * @param clau clau que es vol cercar
     * @return booleà que indica si s'ha trobat o no
     */
    public boolean existeixClau(char clau) throws CaracterNoValid{
        return !this.getCodi(clau).equals("No s'ha trobat la lletra " + clau);
    }
}
