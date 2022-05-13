package jcolonia.daw2021.mayo;

import java.util.Scanner;

public class VistaSumandos extends Vista {
	/**
	 * Texto del titulo de la aplicacion
	 */
	private String título;
	public VistaSumandos(String título, Scanner scEntrada) {
		super(título, scEntrada);
		this.título=título;
	}
	/**
	 * Muestra un mensaje por pantalla para recoger un numero
	 */
	public void vistaGetNumeros() {
		mostrarTexto("Introduce un número porfavor:");
	}

}
