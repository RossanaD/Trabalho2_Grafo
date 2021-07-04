
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
		g.insertAresta(g.getVertice('A'), g.getVertice('B'), 5);
		g.insertAresta(g.getVertice('A'), g.getVertice('H'), 4);
		
		//Vertice b
		g.insertAresta(g.getVertice('B'), g.getVertice('C'), 8);
		g.insertAresta(g.getVertice('B'), g.getVertice('G'), 10);
		
		//Vertice c
		g.insertAresta(g.getVertice('C'), g.getVertice('D'), 1);
		
		//Vertice d
		g.insertAresta(g.getVertice('D'), g.getVertice('E'), 4);
		g.insertAresta(g.getVertice('D'), g.getVertice('F'), 2);
		
		//Vertice e
		g.insertAresta(g.getVertice('E'), g.getVertice('F'), 6);
		
		//Vertce f
		g.insertAresta(g.getVertice('F'), g.getVertice('G'), 3);
		
		//Vertice g
		g.insertAresta(g.getVertice('G'), g.getVertice('H'), 1);
		
		Util util = new Util();

		util.Realizadijkstra(g);
		
		util.teste(g);
		
		//Matriz impares
		util.ImprimeMatrizImpares();
		
		//Matriz D
		util.ImprimeMatrizD(util.getVerticesGrauImpar(), util.getMatriz());
		
	}

}
