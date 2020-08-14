package stack;

import java.util.Stack;

/**
 * @author 47un
 * 
 * Given the number of disks, generate all the moves between the towers
 * 
 * http://www.geeksforgeeks.org/iterative-tower-of-hanoi/
 * http://quiz.geeksforgeeks.org/c-program-for-tower-of-hanoi/
 * 
 * ====================
 * METHOD 1 : Recursive
 * ====================
 * Source tower is initially X, Destination is Y, Auxiliary is A
 * If there is just 1 disk, move it from source to the destination
 * Move a disk from source tower to auxiliary
 * Print the move from source to destination
 * Move from auxiliary to destination
 * 
 * TIME     : O(2^n)
 * SPACE    : O(n)
 * 
 * ====================
 * METHOD 2 : Iterative
 * ====================
 * Find the number of moves (2^disks)
 * Swap auxiliary and destination if the number of disks are even
 *  If the disks = 2, then the moves would be : Disk 1 from X to A, Disk2 from X to Y, Disk1 from A to Y
 *  The first move in the logic given below is to move a disk from X to Y, which will fail for even number of disks
 *  Hence the swap
 * Push all disks onto source tower (the largest one goes at the bottom)
 * For all possible moves
 *  According to the value of move%3, do the appropriate thing
 *      1 : Move a disk from source to destination
 *      2 : Move a disk from source to auxiliary
 *      0 : Move a disk from auxiliary to destination
 *  While moving a disk between towers :
 *   If either tower is empty, pop a disk from the non empty tower and push it into the empty one
 *   If both towers are non empty, peek at the top values
         Pop the tower with the smaller disk and push it into the tower with the larger disk
 *   
 * TIME     : O(2^n)
 * SPACE    : O(n)
 *
 *
 */

public class Towers_Of_Hanoi {

    public static void main(String[] args) {
        int disks = 3;
        System.out.println("Recursive solution");		
        recursivelyMoveDisksWrapper(disks);
        System.out.println("\nIterative solution");
        iterativelyMoveDisks(disks);
    }

    private static void recursivelyMoveDisksWrapper(int disks) {
        recursivelyMoveDisks(disks, 'X', 'Y', 'A');
    }

    private static void recursivelyMoveDisks(int disks, char sourceTower, char destinationTower, char auxiliaryTower) {
        if(disks == 1){
            printMove(disks, sourceTower, destinationTower);
            return;
        }
        recursivelyMoveDisks(disks - 1, sourceTower, auxiliaryTower, destinationTower);
        printMove(disks, sourceTower, destinationTower);
        recursivelyMoveDisks(disks - 1, auxiliaryTower, destinationTower, sourceTower);
    }

    private static void printMove(int disks, char sourceTower, char destinationTower) {
        System.out.println("Move disk " + disks + " from " + sourceTower + " to " + destinationTower);
    }

    private static void iterativelyMoveDisks(int disks) {
        char source = 'X';
        char destination = 'Y';
        char auxiliary   = 'A';
        if(disks % 2 == 0){
            char temp = auxiliary;
            auxiliary = destination;
            destination = temp;
        }
        Stack<Integer> sourceTower = new Stack<>();
        Stack<Integer> destinationTower = new Stack<>();
        Stack<Integer> auxiliaryTower   = new Stack<>();
        for(int disk = disks; disk > 0; disk--)
            sourceTower.push(disk);
        int moves   = (int) Math.pow(2, disks) - 1;
        for(int move = 1; move <= moves; move++)
            switch(move % 3){
            case 1 : moveDiskBetweenTowers(source, destination, sourceTower, destinationTower);	   break;
            case 2 : moveDiskBetweenTowers(source, destination, sourceTower, auxiliaryTower);      break;
            case 0 : moveDiskBetweenTowers(source, destination, auxiliaryTower, destinationTower); break;
            }
    }

    private static void moveDiskBetweenTowers(char source,
            char destination, Stack<Integer> sourceTower, Stack<Integer> destinationTower) {
        if(sourceTower.isEmpty() && !destinationTower.isEmpty()){
            int disk = destinationTower.pop();
            sourceTower.push(disk);
            printMove(disk, destination, source);
        }
        else if(destinationTower.isEmpty() && !sourceTower.isEmpty()){
            int disk = sourceTower.pop();
            destinationTower.push(disk);
            printMove(disk, source, destination);
        }
        else{
            int diskAtSource = sourceTower.peek();
            int diskAtDestination = destinationTower.peek();
            if(diskAtDestination < diskAtSource){
                destinationTower.pop();
                sourceTower.push(diskAtDestination);
                printMove(diskAtDestination, destination, source);
            }
            else{
                sourceTower.pop();
                destinationTower.push(diskAtSource);
                printMove(diskAtSource, source, destination);
            }
        }
    }
}
