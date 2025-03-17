package comercio.acceso;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import comercio.modelo.Libro;

public class AccesoLibro {
	private static final String BASE_DATOS = "comercio";
	private static final String COLECCION = "libros";

	public static void insertarLibro(Libro libro) {
		// TODO Auto-generated method stub
		MongoClient cliente = null;
		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(BASE_DATOS);
			MongoCollection<Document> libros = bd.getCollection(COLECCION);
			Document lib = new Document();
			lib.put("codigo", libro.getCodigo());
			lib.put("titulo", libro.getTitulo());
			lib.put("autor", libro.getAutor());
			lib.put("agno", libro.getAgno());
			lib.put("genero", libro.getGenero());
			lib.put("precio", libro.getPrecio());
			libros.insertOne(lib);
			cliente.close();
		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}
	}

	public static void insertarLibroV2(Libro libro) {
		// TODO Auto-generated method stub
		MongoClient cliente = new MongoClient();
		MongoDatabase bd = cliente.getDatabase("biblioteca");
		MongoCollection<Document> libros = bd.getCollection("libros");
		Document libroConCodigoMaximo = libros.find().sort(descending("codigo")).first();
		int codigo = 1;
		if (libroConCodigoMaximo != null) {
			codigo = libroConCodigoMaximo.getInteger("codigo") + 1;
		}
		Document doc = new Document();
		doc.put("codigo", libro.getCodigo());
		doc.put("titulo", libro.getTitulo());
		doc.put("autor", libro.getAutor());
		doc.put("agno", libro.getAgno());
		doc.put("genero", libro.getGenero());
		doc.put("precio", libro.getPrecio());
		libros.insertOne(doc);
		System.out.println("Se ha insertado un libro.");
		cliente.close();
	}

	public static Libro consultarPorCodigo(int codigo) {
		Libro libro = null;
		MongoClient cliente = new MongoClient();
		MongoDatabase bd = cliente.getDatabase(BASE_DATOS);
		MongoCollection<Document> libros = bd.getCollection(COLECCION);
		Document doc = libros.find(eq("codigo", codigo)).first();
		if (doc != null) {
			libro = new Libro(doc);
		}
		cliente.close();
		return libro;
	}

	public static List<Libro> consultarPorAutor(String autor) {
		List<Libro> listaLibros = new ArrayList<>();
		MongoClient cliente = null;
		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(BASE_DATOS);
			MongoCollection<Document> libros = bd.getCollection(COLECCION);
			MongoCursor<Document> cursor = libros.find(eq("autor", autor)).sort(ascending("agno")).iterator();
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Libro libro = new Libro(doc);
				listaLibros.add(libro);
			}
		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}
		return listaLibros;
	}

	public static boolean eliminarLibro(int codigo) {
		MongoClient cliente = null;
		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(BASE_DATOS);
			MongoCollection<Document> libros = bd.getCollection(COLECCION);
			DeleteResult resultados = libros.deleteOne(eq("codigo", codigo));
			if (resultados.getDeletedCount() >= 1) {
				return true;
			}
		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}
		return false;
	}

	public static boolean modificarLibro(Libro libro) {
		MongoClient cliente = null;
		boolean modificado = false;
		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(BASE_DATOS);
			MongoCollection<Document> libros = bd.getCollection(COLECCION);
			Bson filtro = eq("codigo", libro.getCodigo());
			Bson modificaciones = combine(set("titulo", libro.getTitulo()), set("autor", libro.getAutor()),
					set("agno", libro.getAgno()), set("genero", libro.getGenero()), set("precio", libro.getPrecio()));
			UpdateResult resultado = libros.updateOne(filtro, modificaciones);
			if (resultado.getModifiedCount() >= 1) {
				modificado = true;
			}
		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}
		return modificado;
	}

	public static List<Libro> consultarEntreAgnos(int agno1, int agno2) {
		List<Libro> listaLibros = new ArrayList<>();
		MongoClient cliente = null;
		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(BASE_DATOS);
			MongoCollection<Document> libros = bd.getCollection(COLECCION);
			MongoCursor<Document> cursor = libros.find(and(gte("agno", agno1), lte("agno", agno2))).iterator();
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Libro libro = new Libro(doc);
				listaLibros.add(libro);
			}
		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}
		return listaLibros;
	}

	public static List<String> consultarEntreAgnosV2(int agno1, int agno2) {

		List<String> listaCadenas = new ArrayList<>();
		MongoClient cliente = null;
		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(BASE_DATOS);
			MongoCollection<Document> libros = bd.getCollection(COLECCION);
			Bson proyecciones = fields(include("titulo", "autor"), excludeId());
			MongoCursor<Document> cursor = libros.find(and(gte("agno", agno1), lte("agno", agno2)))
					.projection(proyecciones).iterator();
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				String cadena = "Libro [titulo= " + doc.getString("titulo") + ", autor= " + doc.getString("autor")
						+ "]";

				listaCadenas.add(cadena);
			}
		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}
		return listaCadenas;

	}

	public static int anadirStock(int stock) {
		MongoClient cliente = null;
		int afectados = 0;
		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(BASE_DATOS);
			MongoCollection<Document> libros = bd.getCollection(COLECCION);
			Bson filtro = new Document();
			UpdateResult resultado = libros.updateMany(filtro, set("stock", stock));
			afectados = (int) resultado.getModifiedCount();
		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}
		return afectados;

	}

	public static boolean comprarLibro(int codigo, int cantidad) {
		MongoClient cliente = null;
		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(BASE_DATOS);
			MongoCollection<Document> libros = bd.getCollection(COLECCION);
			
			Bson filtro = and(eq("codigo", codigo), gte("stock", cantidad));
			MongoCursor<Document> cursor = libros.find(filtro).iterator();
			
			if (cursor.hasNext()) {
				Document doc = cursor.next();
				int stock = doc.getInteger("stock");
				stock -= cantidad;
				Bson modificaciones = set("stock", stock);
				UpdateResult resultado = libros.updateOne(filtro, modificaciones);
				if (resultado.getModifiedCount() >= 1) {
					return true;
				}
			}
			
		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}
		return false;
	}

}
