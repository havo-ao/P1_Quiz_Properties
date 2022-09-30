package co.edu.unbosque.modelo;

public class Operaciones {

	public boolean esMultiploDeTres(int numero) {
		int residuo = numero % 3;

		if (residuo == 0)
			return true;

		return false;
	}

	public int contarVocal(String nombre, char vocal) {

		char caracteresDeNombre[] = nombre.toCharArray();

		int contador = 0;

		for (int numeroDeCaracter = 0; numeroDeCaracter < caracteresDeNombre.length; numeroDeCaracter++) {

			if (caracteresDeNombre[numeroDeCaracter] == vocal)
				contador += 1;
		}

		return contador;
	}
}
