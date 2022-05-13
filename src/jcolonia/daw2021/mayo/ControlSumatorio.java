package jcolonia.daw2021.mayo;

import java.util.Scanner;

/**
 * Gestión de números «decimales»: recogida y visualización de la suma.
 * 
 * @versión 2022.3.1
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 */
public class ControlSumatorio {
	/**
	 * Texto identificativo de las funciones de la aplicación que aparecerán en el
	 * menú principal.
	 */
	private static final String[] OPCIONES_MENÚ_PRINCIPAL = { "Agregar valor", "Mostrar valores", "Mostrar suma",
			"Restablecer", "SALIR" };

	/**
	 * Título de la aplicación. Se mostrará como encabezado del menú principal.
	 */
	private static final String TÍTULO_MENÚ_PRINCIPAL = "Sumatorio";

	/**
	 * Recurso asociado a la entrada estándar de la aplicación. Debe ser un objeto
	 * único a compartir con las diferentes vistas creadas.
	 */
	private Scanner entrada;

	private ListaNúmeros conjunto;
	private VistaMenúBásico menúPrincipal;
	private VistaSumandos vistaSumandos;
	/**
	 * Constructor principal Sumatorio
	 * @param in entrada de datos
	 */
	public ControlSumatorio(Scanner in) {
		this.entrada = in;
		conjunto = new ListaNúmeros();
	}
	/**
	 * Bucle principal del programa, encargado de dar respuesta a las opcioens
	 */
	private void buclePrincipal() {
		int opciónElegida;
		boolean fin = false;

		menúPrincipal = new VistaMenúBásico(TÍTULO_MENÚ_PRINCIPAL, entrada, OPCIONES_MENÚ_PRINCIPAL);

		// Bucle general
		do {
			menúPrincipal.mostrarTítulo1();
			menúPrincipal.mostrarOpciones();
			opciónElegida = menúPrincipal.pedirOpción();
			ejecutarGenérico(opciónElegida);
			switch (opciónElegida) {
			case 5: // SALIR
				fin = true;
				Vista.mostrarAviso("¡¡¡A-D-I-O-S!!");
				break;
			case 1: // Opción 1: Entrada datos
				cargarSumando();
				break;
			case 2: // Opción 2: Mostrar sumandos
				mostrarSumandos();
				break;
			case 3: // Opción 3: Mostrar suma
				mostrarSuma();
				break;
			case 4: // Opción 4: Reset
				restablecer();
				break;
			default: // Opción no esperada: abortar
				ejecutarGenérico(opciónElegida);
				System.err.println("Error interno de programa - operación pendiente de desarrollo");
				System.exit(1);
			}
		} while (!fin);
	}
	/**
	 *Resetea la Lista
	 */
	private void restablecer() {
		vistaSumandos.mostrarAviso("reseteado!");
		conjunto.restablecerLista();
		
	}
	/**
	 * Muestra la suma proporcionada
	 */
	private void mostrarSuma() {
		conjunto.mostrarNúmeros();
		
	}
	/**
	 * Muestra los Sumandos proporcionados
	 */
	private void mostrarSumandos() {
		conjunto.mostrarSumandos();
		
	}
	/**
	 * Recoge por entrada el sumando via consola del usuario
	 */
	private void cargarSumando() {
		vistaSumandos = new VistaSumandos("Introducir", entrada);
		vistaSumandos.vistaGetNumeros();
		String número=entrada.nextLine();
		try {
			Double cambio=conjunto.transformarEntradaTexto(número);
			conjunto.comprobarRangoPermitido(cambio);
			
		} catch (SumatorioNumberException e1) {
			// TODO Bloque catch generado automáticamente
			e1.printStackTrace();
		}
		try {
			conjunto.add(conjunto.transformarEntradaTexto(número));
		} catch (SumatorioNumberException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Recibe un número el cual muestra por pantalla formateado
	 * @param id Número a recivir
	 */
	private void ejecutarGenérico(int id) {
		String mensaje;
		mensaje = String.format("%n  Ha elegido la opción %d: «%s»", id, OPCIONES_MENÚ_PRINCIPAL[id - 1]);
		Vista.mostrarTexto(mensaje);
	}
	/**
	 * Metodo principal MAIN
	 * @param args 
	 */
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);

		ControlSumatorio control = new ControlSumatorio(entrada);
		control.buclePrincipal();
		entrada.close();
	}
}
