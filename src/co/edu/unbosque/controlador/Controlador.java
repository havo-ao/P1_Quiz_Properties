package co.edu.unbosque.controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import co.edu.unbosque.modelo.Operaciones;
import co.edu.unbosque.vista.VistaConsola;

public class Controlador {

	private VistaConsola vista;
	private Operaciones operaciones;

	private Properties archivoPropiedades;

	public Controlador() {
		vista = new VistaConsola();
		operaciones = new Operaciones();

		boolean archivoObtenido = obtenerArchivo();

		if (archivoObtenido)
			empezarQuiz(); // Iniciar, arrancar, comenzar
	}

	public void empezarQuiz() {

		String nombre = "";
		int edad = 0;

		try {
			nombre = archivoPropiedades.getProperty("nombre");
			edad = Integer.parseInt(archivoPropiedades.getProperty("edad"));

			if (operaciones.esMultiploDeTres(edad)) {
				contarVocales(nombre.toLowerCase());
			} else {
				obtenerFactores(edad);
			}

		} catch (NumberFormatException error) {
			vista.mostrarEnConsola("Por favor colocar numeros validos en la edad");

		} catch (NullPointerException error) {
			vista.mostrarEnConsola("El valor de edad o nombre no existe en el archivo de properties seleccionado");
		}

	}

	public boolean obtenerArchivo() {
		archivoPropiedades = new Properties();

		try {
			archivoPropiedades.load(new FileReader("archivo/cedula.properties"));
			return true;
		} catch (FileNotFoundException e) {
			vista.mostrarEnConsola("El archivo no fue encontrado");
		} catch (IOException e) {
			vista.mostrarEnConsola("Error al cargar el archivo");
		}

		return false;

	}

	public void contarVocales(String nombre) {
		char vocales[] = { 'a', 'e', 'i', 'o', 'u' };
		int indexVocal = 0;

		do {
			int numeroRepeticiones = operaciones.contarVocal(nombre, vocales[indexVocal]);
			vista.mostrarEnConsola("Cantidad con " + vocales[indexVocal] + ": " + numeroRepeticiones);
			indexVocal += 1;

		} while (indexVocal < vocales.length);
	}

	public void obtenerFactores(int numero) {
		int factorActual = 2;
		int valorActual = numero;

		vista.mostrarEnConsola("Factores de " + numero + ":");

		while (valorActual != 1) {
			if (valorActual % factorActual == 0) {
				vista.mostrarEnConsola(" " + factorActual);
				valorActual = valorActual / factorActual;
			} else {
				factorActual += 1;
			}
		}

	}

}
