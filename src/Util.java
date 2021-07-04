import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Util {
	public String VISITED = "VISITED";

	public static String UNVISITED = "UNVISITED";

	public String RETURN = "RETURN";

	public String CROSS = "CROSS";

	public List<String> stack = new ArrayList<String>();

	public ArrayList<String> matrizImpar = new ArrayList<String>();

	public ArrayList<Vertice> verticesGrauImpar = new ArrayList<Vertice>();

	public ArrayList<String> matriz = new ArrayList<>();

	public HashMap<Character, List<String>> listaAux = new HashMap<Character, List<String>>();

	public List<String> verticesDijkstraList;

	public void resetStatus(Grafo grafo) {
		for (int i = 0; i < grafo.arestas().size(); i++) {
			grafo.arestas().get(i).setStatus(UNVISITED);
		}
		for (int i = 0; i < grafo.vertices().size(); i++) {
			grafo.vertices().get(i).setStatus(UNVISITED);
		}
	}

	public void dijkstra(Vertice init) {
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
					if (listaTabela.contains(v)) {
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

		// Matriz dos impares
		String str = "";
		for (Vertice vertice : listaTabela) {
			str += vertice.toString() + "\n";
		}
		matrizImpar.add(str);
	}

	public List<Vertice> getMenorCaminho(Vertice target) {
		List<Vertice> path = new ArrayList<Vertice>();
		for (Vertice vertice = target; vertice != null; vertice = vertice.getAnterior()) {
			path.add(vertice);
		}
		Collections.reverse(path);
		return path;
	}

	public void imprimeMenorCaminho(Grafo grafo) {
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

	public void ImprimeMatrizImpares() {
		System.out.println("Matriz de Dijkstra dos vértices impares");
		for (String string : matrizImpar) {
			System.out.println(string);
			System.out.println("=================");
		}
	}

	public void ImprimeMatrizD(List<Vertice> verticesGrauImpar, ArrayList<String> matriz) {
		System.out.println("Matriz D");
		System.out.print("  ");
		for (int i = 0; i < verticesGrauImpar.size(); i++) {
			System.out.print(verticesGrauImpar.get(i).getName() + " ");
		}
		System.out.println();
		for (String string : matriz) {
			System.out.println(string);
		}
	}

	public void Realizadijkstra(Grafo g) {
		List<Aresta> arestas = g.arestas();
		for (Aresta aresta : arestas) {
			Vertice vertice = Grafo.oposto(aresta.getV1(), aresta);
			if (!verticesGrauImpar.contains(vertice)) {
				if ((vertice.getGrau() % 2) != 0) {
					verticesGrauImpar.add(vertice);
				}
			}
		}
		String str = "";
		char nil = 'c';
		Collections.sort(verticesGrauImpar, (o1, o2) -> o1.getName() - o2.getName());
		for (Vertice vertice : verticesGrauImpar) {
			verticesDijkstraList = new ArrayList<String>();
			g.limpa();
			dijkstra(vertice);
			str += vertice.getName() + " ";
			nil = vertice.getName();
			for (Vertice v : verticesGrauImpar) {
				str += v.getDist() + " ";
				if (v.getDist() != 0) {
					verticesDijkstraList.add(v.getName() + ":" + v.getDist());
				}

			}
			listaAux.put(nil, verticesDijkstraList);
			matriz.add(str);
			str = "";
		}

	}

	public ArrayList<String> getMatrizImpar() {
		return matrizImpar;
	}

	public ArrayList<Vertice> getVerticesGrauImpar() {
		return verticesGrauImpar;
	}

	public ArrayList<String> getMatriz() {
		return matriz;
	}

	public void teste(Grafo g) {
		ArrayList<String> pares = new ArrayList<String>();
		for (Map.Entry<Character, List<String>> vertices : listaAux.entrySet()) {
			for (String string : vertices.getValue()) {
				pares.add(vertices.getKey() + ":" + string);
			}
		}

		ArrayList<String> paresSemRepeticao = new ArrayList<String>();
		for (int i = 0; i < pares.size(); i++) {
			for (int j = 1; j < pares.size(); j++) {
				char verticeChar1 = pares.get(i).charAt(0);
				char verticeChar2 = pares.get(i).charAt(2);
				char verticeChar3 = pares.get(j).charAt(0);
				char verticeChar4 = pares.get(j).charAt(2);
				if ((verticeChar1 != verticeChar3) && (verticeChar1 != verticeChar4) && (verticeChar2 != verticeChar3)
						&& (verticeChar2 != verticeChar4)) {
					paresSemRepeticao.add(pares.get(j));
				}

			}
		}
	}
}