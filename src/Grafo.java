import java.util.ArrayList;
import java.util.List;

public class Grafo {
	private int numVertices = 0;
	private int numArestas = 0;
	private List<Vertice> vertices = new ArrayList<Vertice>();
	private List<Aresta> arestas = new ArrayList<Aresta>();

	public Vertice getVertice(Integer id) {
		for (Vertice v : vertices) {
			if (v.getId().equals(id)) {
				return v;
			}
		}
		return null;
	}

	public int numVertices() {
		return numVertices;
	}

	public int numArestas() {
		return numArestas;
	}

	public List<Vertice> vertices() {
		return vertices;
	}

	public List<Aresta> arestas() {
		return arestas;
	}

	public Vertice aVertice() {
		return null;
	}

	public int degree(Vertice v) {
		return v.getGrau();
	}

	public List<Vertice> adjacentVertices(Vertice v) {
		return v.getAdjacents();
	}

	public List<Aresta> incidentArestas(Vertice v) {
		return v.getArestas();
	}

	public List<Vertice> endVertices(Aresta e) {
		List<Vertice> vs = new ArrayList<Vertice>();
		vs.add(e.getV1());
		vs.add(e.getV2());
		return vs;
	}

	public static Vertice oposto(Vertice v, Aresta e) {
		for (Aresta edge : v.getArestas()) {
			if (edge.equals(e)) {
				if (v.equals(edge.getV1())) {
					return edge.getV2();
				} else {
					return edge.getV1();
				}
			}
		}
		
		return null;
	}

	public boolean areAdjacent(Vertice v, Vertice w) {
		for (Vertice vertex : v.getAdjacents()) {
			if (vertex.equals(w)) {
				return true;
			}
		}
		return false;
	}

	public Aresta insertAresta(Vertice v, Vertice w, Integer weigth) {
		Aresta e = v.insertAresta(w, weigth);
		w.insertAresta(v, e, weigth);
		v.addAdjacents(w);
		w.addAdjacents(v);
		arestas.add(e);
		numArestas++;
		return e;
	}

	public Vertice insertVertice(Integer id, char name){
		Vertice v = new Vertice(id, name);
		vertices.add(v);
		numVertices++;
		return v;
	}

	public void removeVertice(Vertice v) {
		vertices.remove(v);
		numVertices--;
		v.removeAllArestas();
	}

	public void removeAresta(Aresta e) {
		e.setV1(null);
		e.setV2(null);
		arestas.remove(e);
	}


	public void limpa() {
		// TODO Auto-generated method stub
		for (Aresta aresta : arestas) {
			Vertice vertice = aresta.getV1();
			Vertice vertice2 = aresta.getV2();
			vertice.LimpaVertice(vertice);
			vertice.LimpaVertice(vertice2);
		}
	}
	
}