package jcolonia.daw2021.mayo;

import static java.lang.System.out;
import static java.lang.System.err;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Vista general sobre consola de texto.
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 1.1 (20220512)
 */
public abstract class Vista {
	/** Formato tipo «printf» para mensajes generales. */
	private static final String FORMATO_AVISO = " → %s%n";
	// - {field}{static}FORMATO_AVISO: String = " → %s%n" {solo lectura}

	/** Texto visible para identificar la vista. */
	private final String título;
	// - título: String

	/**
	 * Analizador/escáner compartido vinculado a la entrada estándar del sistema. Da
	 * soporte a las operaciones de entrada por teclado.
	 */
	private final Scanner scEntrada;
	// - scEntrada: Scanner

	/**
	 * Gestión común y utilidades para las vistas en consola de texto.
	 * 
	 * @param título    el texto identificador
	 * @param scEntrada el analizador/escaner de entrada
	 */
	public Vista(String título, Scanner scEntrada) {
		this.scEntrada = scEntrada;
		this.título = título;
	}
	// + Vista(título: String, scEntrada: Scanner)

	/**
	 * Facilita el texto identificador.
	 *
	 * @return el texto correspondiente
	 */
	public final String getTítulo() {
		return título;
	}
	// + getTítulo(): String

	/**
	 * Facilita el analizador/escaner de entrada.
	 *
	 * @return el objeto correspondiente
	 */
	public final Scanner getEntrada() {
		return scEntrada;
	}
	// + getEntrada(): Scanner

	/**
	 * Envía a la salida el título con un marco destacado. <div> Ejemplo:
	 *
	 * <pre>
	 * ===============
	 * Vista principal
	 * ===============
	 * </pre>
	 *
	 * </div>
	 */
	public final void mostrarTítulo1() {
		String líneaSubrayado = hacerRelleno(título.length() + 2, '=');
		out.println("\n" + líneaSubrayado + "\n " + título + "\n" + líneaSubrayado);
	}
	// + getTítulo(): String

	/**
	 * Envía a la salida el título con un subrayado sencillo. <div> Ejemplo:
	 *
	 * <pre>
	 * Vista secundaria
	 * ----------------
	 * </pre>
	 *
	 * </div>
	 */
	public final void mostrarTítulo2() {
		String líneaSubrayado = hacerRelleno(título.length(), '-');
		out.println("\n" + título + "\n" + líneaSubrayado);
	}
	// + mostrarTítulo2(): void

	/**
	 * Envía directamente a la salida un texto arbitrario.
	 *
	 * @param texto el texto indicado
	 */
	public final static void mostrarTexto(String texto) {
		out.println(texto);
	}
//	+ {static} mostrarTexto(texto: String): void

	/**
	 * Envía a la salida un texto destacado como aviso.
	 * 
	 * <pre>
	 * 	→ Texto de aviso
	 * </pre>
	 *
	 * @param texto el texto indicado
	 */
	public final static void mostrarAviso(String texto) {
		out.printf(FORMATO_AVISO, texto);
	}
//	+ {static} mostrarAviso(texto: String): void

	/**
	 * Crea una línea por repetición de un mismo carácter. <div> Ejemplo:
	 *
	 * <pre>
	 * ^^^^^^^^^^^^^^^^^^^^^^
	 * </pre>
	 *
	 * </div>
	 *
	 * @param tamaño  la longitud de la línea
	 * @param símbolo el carácter a emplear
	 * @return el texto con la línea creada
	 */
	public final static String hacerRelleno(int tamaño, char símbolo) {
		String resultado;
		char[] línea;

		línea = new char[tamaño];
		Arrays.fill(línea, símbolo);

		resultado = new String(línea);
		return resultado;
	}
	// + {static} hacerSubrayado(tamaño: int, character símbolo): String

	/**
	 * Convierte un texto a minúsculas con solo el primer carácter en mayúscula.
	 * 
	 * @param texto el texto original
	 * @return el texto resultante
	 */
	public static String capitalizar(String texto) {
		String resultado;

		if (texto != null && texto.length() > 0) {
			char[] caracteres = texto.toLowerCase().toCharArray();
			caracteres[0] = Character.toUpperCase(caracteres[0]);
			resultado = new String(caracteres);
		} else {
			resultado = texto;
		}

		return resultado;
	}
	// + {static} capitalizar(texto: String): String

	/**
	 * Envía directamente a la salida de error un texto arbitrario.
	 *
	 * <div>Introduce un pequeño tiempo de espera para facilitar la sincronización
	 * del búffer de salida y no mezclarse con la salida estándar en terminales con
	 * limitaciones (i.e. la consola de eclipse).</div>
	 *
	 * @param texto el texto indicado
	 */
	public static final void mostrarError(String texto) {
		err.println(texto);
		esperar(200); // 0.2 s, evita atascos en consola de eclipse
	}
	// + {static} mostrarError(texto: String): void

	/**
	 * Introduce una pausa, un pequeño tiempo de espera.
	 *
	 * @param milisegundos el tiempo indicado, en milisegundos
	 */
	public static void esperar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
		} // Nada útil que hacer…
	}
	// + {static} esperar(milisegundos: int): void

	/**
	 * Solicita una respuesta de «sí» o «no» con el texto proporcionado. La pregunta
	 * se repite de maera estricta hasta obtener una entrada con solamente una de
	 * las dos letras iniciales admitidas «S/N», sea mayúscula o minúscula.
	 * <div>Ejemplo:
	 *
	 * <pre>
	 * ¿Desea continuar (S/N)?
	 * </pre>
	 *
	 * @param texto formato tipo «printf» que incluya la secuencia «%s» donde
	 *              colocar la opción «S/N»
	 * @return true para «S» o «s», false para «N» o «n»
	 */
	public final boolean pedirConfirmación(String texto) {
		String textoEntrada;
		boolean resultado = false, aceptado = false;

		do {
			out.printf(texto, "S/N");
			textoEntrada = scEntrada.nextLine();
			if ("s".equalsIgnoreCase(textoEntrada)) {
				resultado = true;
				aceptado = true;
			} else if ("n".equalsIgnoreCase(textoEntrada)) {
				resultado = false;
				aceptado = true;
			} else {
				mostrarError(" Escriba solo «S» o «N» y pulse «Intro»\n");
			}
		} while (!aceptado);
		return resultado;
	}
	// + {static} pedirConfirmación(texto: String): boolean

	/**
	 * Muestra un texto predeterminado y espera hasta que se pulse la tecla «Intro».
	 * Si el usuario teclea algún texto previo, tal texto carece de utilidad y queda
	 * descartado. <div>Ejemplo:
	 * 
	 * <pre>
	 *  → Pulsar «Intro» para continuar…
	 * </pre>
	 */
	public final void pedirContinuar() {
		out.print(" → Pulsar «Intro» para continuar…");
		scEntrada.nextLine();
	}
	// + pedirContinuar(): void
}
