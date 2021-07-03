import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Util {
	public static String VISITED = "VISITED";

	public static String UNVISITED = "UNVISITED";

	public static String RETURN = "RETURN";

	public static String CROSS = "CROSS";

	public static List<String> stack = new ArrayList<String>();


	public static void resetStatus(Grafo grafo) {
		for (int i = 0; i < grafo.arestas().size(); i++) {
			grafo.arestas().get(i).setStatus(UNVISITED);
		}
		for (int i = 0; i < grafo.vertices().size(); i++) {
			grafo.vertices().get(i).setStatus(UNVISITED);
		}
	}

	public static void dijkstra(Vertice init) {
		init.setDist(0);
		List<Vertice> listaTabela = new ArrayList<Vertice>();
		PriorityQueue<Vertice> queue = new PriorityQueue<Vertice>();
		queue.add(init);
		listaTabela.add(init);
		while (!queue.isEmpty()) {
			Vertice u = queue.poll();			
			for (Aresta e : u.getArestas()) {
				Vertice v = Grafo.oposto(u, e);
				int peso = e.getPeso();
				int distanciaEntre = u.getDist() + peso;
				if (distanciaEntre < v.getDist()) {
					if(listaTabela.contains(v)) {
						listaTabela.remove(v);
					}
					queue.remove(v);
					v.setDist(distanciaEntre);
					v.setAnterior(u);
					queue.add(v);
					listaTabela.add(v);
				}
			}
		}
		
		for (Vertice vertice : listaTabela) {
			System.out.println(vertice.toString());
		}
	}

	public static List<Vertice> getMenorCaminho(Vertice target) {
		List<Vertice> path = new ArrayList<Vertice>();
		for (Vertice vertice = target; vertice != null; vertice = vertice.getAnterior()) {
			path.add(vertice);
		}
		Collections.reverse(path);
		return path;
	}

	public static void imprimeMenorCaminho(Grafo grafo) {
		Vertice vertice;
		System.out.println("+--    MENOR CAMINHO - Algoritmo de Dijkstra   --+%n");
		for (Vertice v : grafo.vertices()) {
			System.out.println("%nDistancia até " + v.getName() + ": " + v.getDist());
			List<Vertice> path = getMenorCaminho(v);
			System.out.println(" | Caminho: ");
			for (int x = 0; x < path.size(); x++) {
				vertice = path.get(x);
				System.out.println(vertice.getName());
				if (x < path.size() - 1) {
					System.out.println(" -> ");
				}
			}
		}
		System.out.println("%n%n+------------------------------------------------+%n");
	}
}