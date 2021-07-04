/*Christian Trisotto Alegri
 *Rossana Ariadna Schumann Dullius*/
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class Testeunitario {

	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void MatrizD() {
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
		
		util.CriaCaminhosVirtuais(g);
		
		//Matriz D
		util.ImprimeMatrizD(util.getVerticesGrauImpar(), util.getMatriz());
		System.out.println();
	}

	@Test
	void MatrizImpares() {
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
		
		//A quantidade de vertices impares é igual a 4?
		int impar = g.getOddVerticesCount();
		assertEquals(4, impar);
		
		util.Realizadijkstra(g);
		
		util.CriaCaminhosVirtuais(g);
		
		//Continua tendo vertices impares depois do Dijkstra e criar os caminhos virtuais?
		//Se for 0, não ah vertices impares
		impar = g.getOddVerticesCount();
		assertEquals(0, impar);
		
		util.ImprimeMatrizImpares();
	}
	
	@Test
	void Fleury() {
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
		
		util.CriaCaminhosVirtuais(g);
		
		//Começando do Vertice A
		String caminho = util.Fleury(g.getVertice('A'), g);
		System.out.println();
		System.out.println(caminho);
		
		int custo = util.getCusto();
		assertEquals(56, custo);
	}

}
