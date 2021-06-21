package Excepcions;

public class CaracterNoValid extends Exception{

    public CaracterNoValid(char caracter){
        System.out.println("El caràcter " + caracter + " no s'ha pogut codificar");
    }
}
