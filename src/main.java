import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		Grafo g = new Grafo();
		g.insertVertice(1, 'A');
		g.insertVertice(2, 'B');
		g.insertVertice(3, 'C');
		g.insertVertice(4, 'D');
		g.insertVertice(5, 'E');
		g.insertVertice(6, 'F');
		g.insertVertice(7, 'G');
		g.insertVertice(8, 'H');
		
		//Vertice a
		g.insertAresta(g.getVertice(1), g.getVertice(2), 5);
		g.insertAresta(g.getVertice(1), g.getVertice(8), 4);
		
		//Vertice b
		g.insertAresta(g.getVertice(2), g.getVertice(3), 8);
		g.insertAresta(g.getVertice(2), g.getVertice(7), 10);
		
		//Vertice c
		g.insertAresta(g.getVertice(3), g.getVertice(4), 1);
		
		//Vertice d
		g.insertAresta(g.getVertice(4), g.getVertice(5), 4);
		g.insertAresta(g.getVertice(4), g.getVertice(6), 2);
		
		//Vertice e
		g.insertAresta(g.getVertice(5), g.getVertice(6), 6);
		
		//Vertce f
		g.insertAresta(g.getVertice(6), g.getVertice(7), 3);
		
		//Vertice g
		g.insertAresta(g.getVertice(7), g.getVertice(8), 1);
		
		
		List<Vertice> verticesGrauImpar = new ArrayList<Vertice>();
		List<Aresta> arestas = g.arestas();
		for (Aresta aresta : arestas) {			
			Vertice vertice = Grafo.oposto(aresta.getV1(), aresta);
			if(!verticesGrauImpar.contains(vertice)) {
				if((vertice.getGrau() % 2 ) != 0) {
					verticesGrauImpar.add(vertice);
				}
			}			
		}
		
		
		Util util = new Util();
		for (Vertice vertice : verticesGrauImpar) {
			g.limpa();
			util.dijkstra(vertice);	
			for (Vertice v : verticesGrauImpar) {
				System.out.println(v.getName() + ": " + v.getDist());	
			}
			System.out.println("==================");
		}		
		
	}

}
