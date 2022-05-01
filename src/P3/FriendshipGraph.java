package P3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FriendshipGraph {
	public ArrayList<Person> graph = new ArrayList<Person>();
	public Set<Person> people = new HashSet<>();
	
	public void addVertex(Person friend) {
		boolean unique_name = true;
		for(Person temp : graph) {
			if(friend.name_of_person() == "" || friend.name_of_person() == null || temp.name_of_person().equals(friend.name_of_person()))
				unique_name = false;
		}
		
		if(unique_name) {
			graph.add(friend);
			people.add(friend);
		}
		else if(friend.name_of_person() == "" || friend.name_of_person() == null)
			System.out.println("The name shouldn't be empty");
		else
			System.out.println("This name is already used.");
	}
	
	public void addEdge(Person start, Person end) {
		/*
		 * To make sure two Persons have been added to 'graph'
		 * and the problem is defined as the
		 */
		if(graph.contains(start) && graph.contains(end) && !start.name_of_person().equals(end.name_of_person())) {
			start.connection(end);
		}
		else if(!graph.contains(start) || !graph.contains(end))
			System.out.println("Please make sure they're both added into graph.");
		else if(start.equals(end)) {
			System.out.println("Everyone is his own friend :)");
		}
	}
	public void clean() {
		for(int i = 0; i < graph.size(); i++) {
			graph.get(i).distance = 0;
			graph.get(i).visited = false;
		}
	}
	public int getDistance(Person start, Person end) {
		clean();
		/*
		 * The distance of the same Person is defined as 0 
		 */
		if(start.equals(end))
			return 0;
		/*
		 * we have learned the BFS algorithm, The following is implemented in the Java.
		 * 1.use the data construction: Queue using its quality: FIFO;
		 * 2.the Person visited will not be visited again;
		 */	
		int distance = 0;
		boolean reachable = false;
		Queue<Person> queue = new LinkedList<>();
		
		start.visited = true;
		queue.offer(start);
		
		Person target;
		while (!queue.isEmpty()) {
			
			target = queue.poll();
			ArrayList<Person> ownfriend = target.my_friend();
			for (Person search : ownfriend) {
				if (!search.visited) {
					queue.offer(search);
					search.distance = target.distance + 1;
					search.visited = true;

					if (search.name_of_person().equals(end.name_of_person())) {
						distance = search.distance;
						reachable = true;
						break;
					}
				}
			}
			if (reachable)
				return distance;
		}
		clean();
		return -1;
	}
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		
		System.out.println(graph.getDistance(rachel, ross));
		System.out.println(graph.getDistance(rachel, ben));
		System.out.println(graph.getDistance(rachel, rachel));
		System.out.println(graph.getDistance(rachel, kramer));
	}
}
