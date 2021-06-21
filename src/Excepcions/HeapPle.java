package Excepcions;

public class HeapPle extends Exception{

    public HeapPle(){
        System.out.println("El heap està ple, no es poden afegir més nodes");
    }
}