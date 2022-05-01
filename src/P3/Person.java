package P3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Person {
	/*
	 * visited and distance are both used to calculate the distance between two Person,
	 * so, they are public
	 * "my_friend" is a arraylist that contains all the Person who's connected with this person
	 */
	private String name = null;
	public boolean visited = false;
	public int distance = 0;
	private ArrayList<Person> my_friend = new ArrayList<Person>();
	private Set<Person> myfriend = new HashSet<>();
	/*
	 * ¹¹Ôìº¯Êý
	 */
	public Person(String name) {
		this.name = name;
	}
	/*
	 * The name shouldn't be null
	 */
	public String name_of_person() {
		if(name != null)
			return name;
		else
			return null;
	}
	public void connection(Person end) {
		my_friend.add(end);
		myfriend.add(end);
	}
	
	public ArrayList<Person> my_friend() {
		return my_friend;
	}
	public Set<Person> myfriend(){
		return myfriend;
	}
	public boolean visit() {
		return visited;
	}
}
