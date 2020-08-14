package linked_list;

import java.util.Scanner;

/**
 * @author 47un
 *
 * Give a list of points. Remove all the mid points.
 * 
 * 
 * 
 * =========
 * METHOD 1
 * ===========
 * METHOD 1 : Sweep till the x coordinate changes, also keep count on number of nodes in between
 *            There are midpoints if count > 2, connect previous to current
 *            Sweep till the y coordinate changes, also keep count on number of nodes in between
 *            There are midpoints if count > 2, connect previous to current
 *            Advance till the end of the list
 *            
 * TIME     : O(n)
 * SPACE    : O(1)
 *
 * 
 */

public class Remove_Mid_Points {
	static Nodev3 first = null;

	public static void main(String...strings){
		try(Scanner scanner = new Scanner(System.in)){
			first = readCoordinates(scanner);
			removeMidPoints();
			displayCoordinates();
		}
	}

	private static void removeMidPoints() {
		Nodev3 current = first, previous = current;
		int count = 1;
		while(current != null){		
			while(current.link != null && current.x == current.link.x){
				current = current.link;
				count++;
			}
			if(count != 1 && count != 2){
				previous.link = current;
				count = 1;
				previous = current;
			}
			while(current.link != null && current.y == current.link.y){
				current = current.link;
				count++;
			}
			if(count != 1 && count != 2){
				previous.link = current;
				count = 1;
				previous = current;
			}
			current = current.link;
		}
	}
	
	private static Nodev3 readCoordinates(Scanner scanner) {
		Nodev3 first = null, last = null;
		String input = scanner.nextLine();
		String[] points = input.split(" ");
		for(String point : points){
			String[] xy = point.split(",");
			Nodev3 newNode = new Nodev3(Integer.parseInt(xy[0]),Integer.parseInt(xy[1]));
			if(first == null)
				last = first = newNode;
			else{
				last.link = newNode;
				last = last.link;
			}
		}
		return first;
	}
	
	private static void displayCoordinates() {
		Nodev3 current = first;
		while(current != null){
			System.out.print("(" + current.x + "," + current.y + ")");
			if(current.link != null)
				System.out.print(" -> ");
			current = current.link;
		}
	}
	
	private static class Nodev3{
		int x;
		int y;
		Nodev3 link;

		Nodev3(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
