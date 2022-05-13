package jcolonia.daw2021.mayo;

import java.util.Scanner;

public class VistaMenúBásico extends Vista{
	private String título;
	private String[] opciones;
	Scanner sc;
	public VistaMenúBásico(String título, Scanner scEntrada, String[] opciones) {
		super(título, scEntrada);
		this.título=título;
		this.opciones=opciones;
	}
	public void mostrarOpciones() {
		for (int i = 0; i<opciones.length;i++) {
		System.out.println(opciones[i]);}
		
	}
	public int pedirOpción() {
		sc = new Scanner(System.in);
		int opción=sc.nextInt();
		return opción;
	}

}
