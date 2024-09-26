package alumnos.acceso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alumnos.modelo.Alumno;

public class AccesoAlumno {
	private static final String FicheroAlumnos = "data/alumnos.txt";
	
	
	public static List<Alumno> ConsultarAlumnos () throws IOException {
		List<Alumno> alumnos = new ArrayList<>();
		FileReader fr = new FileReader(FicheroAlumnos);
		BufferedReader br = new BufferedReader(fr);
		try {
			String linea = br.readLine();
			while (linea != null) {
				Alumno alumno = new Alumno(linea);
				alumnos.add(alumno);
				linea = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null)
				br.close();
		}
		return alumnos;
	}

	public static List<Alumno> consultarAlumnoPorCodigo(int codigo) throws IOException {
		List<Alumno> alumnosAux = ConsultarAlumnos();
		List<Alumno> alumnos = new ArrayList<>();
		for (Alumno alumno : alumnosAux) {
			if (alumno.getCodigo() == codigo) {
				alumnos.add(alumno);
			}
		}
		return alumnos;
	}
	public static void escribirLista(List<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            System.out.println(alumno.toString());
        }
    }
	
	public static List<Alumno> consultarAlumnoPorNombre(String nombre) throws IOException {
		List<Alumno> alumnosAux = ConsultarAlumnos();
		List<Alumno> alumnos = new ArrayList<>();
		for (Alumno alumno : alumnosAux) {
			if (alumno.getNombre().equals(nombre)) {
				alumnos.add(alumno);
			}
		}
		return alumnos;
	}

	public static List<Alumno> consultarAlumnosAprobados() throws IOException {
		List<Alumno> alumnosAux = ConsultarAlumnos();
		List<Alumno> alumnos = new ArrayList<>();
		for (Alumno alumno : alumnosAux) {
			if (alumno.getNota1() >= 5 && alumno.getNota2() >= 5) {
				alumnos.add(alumno);
			}
		}
		return alumnos;
	}

	public static void insertar(Alumno alumno) throws IOException {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(FicheroAlumnos, true));
			writer.write(alumno.toCSV());
			writer.newLine();
		} finally {
			if (writer != null) {
				writer.close();
			}
			
		}
	}
	public static boolean eliminarAlumnoPorCodigo(int codigo) throws IOException {
        List<Alumno> alumnosAux = new ArrayList<>();
        List<Alumno> alumnos = new ArrayList<>();
        alumnosAux = ConsultarAlumnos();
        for (Alumno alum : alumnosAux) {
			if (alum.getCodigo() != codigo) {
				alumnos.add(alum);
			}
        }
        BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(FicheroAlumnos));
			for (Alumno alumno : alumnos) {
				writer.write(alumno.toCSV());
				writer.newLine();
			}
		} catch (IOException e) {
            e.printStackTrace();
            return false;
        } 
		finally {
			if (writer != null) {
				writer.close();
			}
		}
		return true;
        
    }
}
	

