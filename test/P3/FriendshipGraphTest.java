package P3;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


public class FriendshipGraphTest {
	public FriendshipGraph graph = new FriendshipGraph();
	public Person p1 = new Person("P1");
	public Person p2 = new Person("P1");
	public Person p3 = new Person("");
	public Person p4 = new Person("P2");
	public Person p5 = new Person("P3");
	public Person p6 = new Person("P4");
	public Person p7 = new Person("P5");
	public Person p8 = new Person("P6");
	public Person p9 = new Person("P7");
	public Person p10 = new Person("P8");
	
	@Test
	public void addVertexTest() {
		Set<Person> test = new HashSet<>();
		graph.addVertex(p1);
		test.add(p1);
		assertEquals(test, graph.people);
		graph.addVertex(p2);
		assertEquals(test, graph.people);
		graph.addVertex(p3);
		assertEquals(test, graph.people);
		
		graph.addVertex(p4);
		test.add(p4);
		assertEquals(test, graph.people);
		graph.addVertex(p5);
		test.add(p5);
		assertEquals(test, graph.people);
		graph.addVertex(p6);
		test.add(p6);
		assertEquals(test, graph.people);
		graph.addVertex(p7);
		test.add(p7);
		assertEquals(test, graph.people);
		graph.addVertex(p8);
		test.add(p8);
		assertEquals(test, graph.people);
		graph.addVertex(p9);
		test.add(p9);
		assertEquals(test, graph.people);
		
	}
	@Test
	public void addEdgeTest() {
		Set<Person> test1 = new HashSet<>();
		Set<Person> test2 = new HashSet<>();
		Set<Person> test3 = new HashSet<>();
		Set<Person> test4 = new HashSet<>();
		Set<Person> test5 = new HashSet<>();
		Set<Person> test6 = new HashSet<>();
		Set<Person> test7 = new HashSet<>();
		Set<Person> test8 = new HashSet<>();
		test1.add(p4);
		test1.add(p5);
		test1.add(p6);
		
		test2.add(p1);
		test2.add(p6);
		test2.add(p5);
		
		test3.add(p1);
		test3.add(p4);
		test3.add(p7);
		
		test4.add(p1);
		test4.add(p4);
		
		test5.add(p5);
		
		test6.add(p9);
		
		test7.add(p8);
		
		graph.addVertex(p1);
		graph.addVertex(p4);
		graph.addVertex(p5);
		graph.addVertex(p6);
		graph.addVertex(p7);
		graph.addVertex(p8);
		graph.addVertex(p9);
		graph.addVertex(p10);
		
		graph.addEdge(p1, p4);
		graph.addEdge(p4, p1);
		
		graph.addEdge(p5, p1);
		graph.addEdge(p1, p5);
		
		graph.addEdge(p1, p6);
		graph.addEdge(p6, p1);
		
		graph.addEdge(p4, p6);
		graph.addEdge(p6, p4);
		
		graph.addEdge(p4, p5);
		graph.addEdge(p5, p4);
		
		graph.addEdge(p5, p7);
		graph.addEdge(p7, p5);
		
		graph.addEdge(p8, p9);
		graph.addEdge(p9, p8);
		
		assertEquals(test1, p1.myfriend());
		assertEquals(test2, p4.myfriend());
		assertEquals(test3, p5.myfriend());
		assertEquals(test4, p6.myfriend());
		assertEquals(test5, p7.myfriend());
		assertEquals(test6, p8.myfriend());
		assertEquals(test7, p9.myfriend());
		assertEquals(test8, p10.myfriend());
		
	}
	@Test
	public void getDistanceTest() {
		graph.addVertex(p1);
		graph.addVertex(p4);
		graph.addVertex(p5);
		graph.addVertex(p6);
		graph.addVertex(p7);
		graph.addVertex(p8);
		graph.addVertex(p9);
		graph.addVertex(p10);
		
		graph.addEdge(p1, p4);
		graph.addEdge(p4, p1);
		
		graph.addEdge(p5, p1);
		graph.addEdge(p1, p5);
		
		graph.addEdge(p1, p6);
		graph.addEdge(p6, p1);
		
		graph.addEdge(p4, p6);
		graph.addEdge(p6, p4);
		
		graph.addEdge(p4, p5);
		graph.addEdge(p5, p4);
		
		graph.addEdge(p5, p7);
		graph.addEdge(p7, p5);
		
		graph.addEdge(p8, p9);
		graph.addEdge(p9, p8);
		
		assertEquals(-1, graph.getDistance(p1, p8));
		assertEquals(-1, graph.getDistance(p10, p8));
		assertEquals(0, graph.getDistance(p1, p1));
		assertEquals(1, graph.getDistance(p4, p1));
		assertEquals(1, graph.getDistance(p1, p4));
		assertEquals(2, graph.getDistance(p7, p1));
		assertEquals(2, graph.getDistance(p1, p7));
		assertEquals(3, graph.getDistance(p6, p7));
		assertEquals(3, graph.getDistance(p7, p6));
		assertEquals(1, graph.getDistance(p8, p9));
		assertEquals(1, graph.getDistance(p9, p8));
	}
}
