
/*Christian Trisotto Alegri
 *Rossana Ariadna Schumann Dullius*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Util {

	public List<String> stack = new ArrayList<String>();

	public ArrayList<String> matrizImpar = new ArrayList<String>();

	public ArrayList<Vertice> verticesGrauImpar = new ArrayList<Vertice>();

	public ArrayList<String> matriz = new ArrayList<>();

	public HashMap<Character, List<String>> listaAux = new HashMap<Character, List<String>>();

	public List<String> verticesDijkstraList;

	public int CustoTotal = 0;

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

	public String Fleury(Vertice origem, Grafo f) {
		boolean controle = false;
		Aresta omega = origem.getArestas().get(0);
		String caminho = "Caminho:\n";
		Vertice v = omega.getV1();
		while (f.numArestas() != 0) {
			int idx = 0;
			while (v.getArestas().get(idx).getV1() == null)
				idx++;

			omega = v.getArestas().get(idx);
			CustoTotal += omega.getPeso();
			if (omega.getV1().getName() == 'A' && controle == true) {
				caminho += omega.getV2().getName() + "->" + omega.getV1().getName();
				break;
			}
			controle = true;
			if ((omega.getV2().getName() == v.getName()) && v.getName() != 'A') {
				caminho += omega.getV2().getName() + "->" + omega.getV1().getName() + "\n";
				v = omega.getV1();
			} else {
				caminho += omega.getV1().getName() + "->" + omega.getV2().getName() + "\n";
				v = omega.getV2();
			}
			f.removeAresta(omega);
		}
		return caminho;
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

	public int getCusto() {
		return CustoTotal;
	}

	public void CriaCaminhosVirtuais(Grafo g) {
		ArrayList<String> pares = new ArrayList<String>();
		for (Map.Entry<Character, List<String>> vertices : listaAux.entrySet()) {
			for (String string : vertices.getValue()) {
				pares.add(vertices.getKey() + ":" + string);
			}
		}

		ArrayList<String> paresSemRepeticao = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			for (int j = i + 1; j < pares.size(); j++) {
				{
					char verticeChar1 = pares.get(i).charAt(0);
					char verticeChar2 = pares.get(i).charAt(2);
					char verticeChar3 = pares.get(j).charAt(0);
					char verticeChar4 = pares.get(j).charAt(2);
					if ((verticeChar1 != verticeChar3) && (verticeChar1 != verticeChar4)
							&& (verticeChar2 != verticeChar3) && (verticeChar2 != verticeChar4)) {
						paresSemRepeticao.add(pares.get(i));
						paresSemRepeticao.add(pares.get(j));
						break;
					}

				}
			}
		}

		Vertice vertice1;
		Vertice vertice2;
		Vertice vertice3;
		Vertice vertice4;

		char v1 = paresSemRepeticao.get(0).charAt(0);
		char v2 = paresSemRepeticao.get(0).charAt(2);
		int valorPar = Integer
				.parseInt(paresSemRepeticao.get(0).substring(paresSemRepeticao.get(0).lastIndexOf(':') + 1));
		char v3 = paresSemRepeticao.get(1).charAt(0);
		char v4 = paresSemRepeticao.get(1).charAt(2);
		int valorPar1 = Integer
				.parseInt(paresSemRepeticao.get(1).substring(paresSemRepeticao.get(1).lastIndexOf(':') + 1));
		int somaTotalPar = valorPar + valorPar1;

		char v5 = paresSemRepeticao.get(2).charAt(0);
		char v6 = paresSemRepeticao.get(2).charAt(2);
		int valorPar2 = Integer
				.parseInt(paresSemRepeticao.get(2).substring(paresSemRepeticao.get(2).lastIndexOf(':') + 1));
		char v7 = paresSemRepeticao.get(3).charAt(0);
		char v8 = paresSemRepeticao.get(3).charAt(2);
		int valorPar3 = Integer
				.parseInt(paresSemRepeticao.get(3).substring(paresSemRepeticao.get(3).lastIndexOf(':') + 1));
		int somaTotalPar1 = valorPar2 + valorPar3;

		char v9 = paresSemRepeticao.get(4).charAt(0);
		char v10 = paresSemRepeticao.get(4).charAt(2);
		int valorPar4 = Integer
				.parseInt(paresSemRepeticao.get(4).substring(paresSemRepeticao.get(4).lastIndexOf(':') + 1));
		char v11 = paresSemRepeticao.get(5).charAt(0);
		char v12 = paresSemRepeticao.get(5).charAt(2);
		int valorPar5 = Integer
				.parseInt(paresSemRepeticao.get(5).substring(paresSemRepeticao.get(5).lastIndexOf(':') + 1));
		int somaTotalPar2 = valorPar4 + valorPar5;

		if ((somaTotalPar < somaTotalPar1 || somaTotalPar == somaTotalPar1)
				&& (somaTotalPar < somaTotalPar2 || somaTotalPar == somaTotalPar2)) {
			vertice1 = g.getVertice(v1);
			vertice2 = g.getVertice(v2);
			vertice3 = g.getVertice(v3);
			vertice4 = g.getVertice(v4);
			g.insertAresta(vertice1, vertice2, valorPar);
			g.insertAresta(vertice3, vertice4, valorPar1);
		} else if ((somaTotalPar1 < somaTotalPar || somaTotalPar1 == somaTotalPar)
				&& (somaTotalPar1 < somaTotalPar2 || somaTotalPar1 == somaTotalPar2)) {
			vertice1 = g.getVertice(v5);
			vertice2 = g.getVertice(v6);
			vertice3 = g.getVertice(v7);
			vertice4 = g.getVertice(v8);
			g.insertAresta(vertice1, vertice2, valorPar2);
			g.insertAresta(vertice3, vertice4, valorPar3);
		} else if ((somaTotalPar2 < somaTotalPar || somaTotalPar2 == somaTotalPar)
				&& (somaTotalPar2 < somaTotalPar1 || somaTotalPar2 == somaTotalPar1)) {
			vertice1 = g.getVertice(v9);
			vertice2 = g.getVertice(v10);
			vertice3 = g.getVertice(v11);
			vertice4 = g.getVertice(v12);
			g.insertAresta(vertice1, vertice2, valorPar4);
			g.insertAresta(vertice3, vertice4, valorPar5);
		}

	}
}