import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LightsOutSolverBFS {

	static int count = 0;
	static int[] board = new int[16];
	
	static LinkedList<Integer> moves = new LinkedList<Integer>();
	
	static Queue<int[]> queue  = new LinkedList<int[]>();
	static Hashtable<String, Integer> hashTable = new Hashtable<String, Integer>();

	static void printBoard(int[] arr){
		for(int i = 12; i < 16; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
		for(int i = 8; i < 12; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
		for(int i = 4; i < 8; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
		for(int i = 0; i < 4; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
	}
	
	static void invert(int moleID, int[] currentState){
	    int val = currentState[moleID];
	    currentState[moleID] = (val + 1) % 2;
	}

	static void whack(int moleID, int[] currentState){
	    invert(moleID, currentState);
	    if(moleID+1 < 16 && moleID != 11 && moleID != 7 && moleID != 3){
	        invert(moleID+1, currentState);
	    }
	    if(moleID-1 >= 0 && moleID != 12 && moleID != 8 && moleID != 4){
	        invert(moleID-1, currentState);
	    }
	    if(moleID+4 < 16){
	        invert(moleID+4, currentState);
	    }
	    if(moleID-4 >= 0){
	        invert(moleID-4, currentState);
	    }
	}

	static boolean checkIfClear(int[] currentState){
	    int i;
	    for(i = 0; i < 16; i++){
	        if(currentState[i] == 1){
	            return false;
	        }
	    }
	    return true;
	}

	static void solve(){
		
		while(!queue.isEmpty()){
			//dequeue a board from the queue
			int[] currentState = queue.remove();
			
			//consider all moves
			for(int i = 0; i < 16; i++){
				int[] newBoard = currentState.clone();
				whack(i, newBoard);
				
				//If a mole is present and hashtable does not contain the current board state
				if(currentState[i] == 1 && !hashTable.containsKey(Arrays.toString(newBoard))){
					//set last move to i and add board state to hashtable
					hashTable.put(Arrays.toString(newBoard), i);
					System.out.println(Arrays.toString(newBoard));
					queue.add(newBoard); //add the new state to the queue
				}
				
				//Check if the board is cleared
				if(checkIfClear(newBoard)){
					System.out.println("BFS COMPLETE. SOLUTION FOUND.");
					return;
				}
			}
			
		}
	}
	
	//this method will backtrack and find the moves that led to the solution
	static void backtrack(int[] arr){
		System.out.println("Backtracking...to find moves used to get to solution");
		
		String key = Arrays.toString(arr);
		
		while(!key.equals(Arrays.toString(board))){
			int lastMove = hashTable.get(key);
			moves.add(lastMove+1);
			whack(lastMove, arr);
			key = Arrays.toString(arr);
		}
		
		printSolution();
	}
	
	static void printSolution(){
		for(int i = moves.size()-1; i >= 0; i--){
			System.out.print(moves.get(i) + " ");
		}
		System.out.println("");
	}
	
	
	/*
	 * THIS CODE IS USED FOR PLAYING THE GAME :D
	 * 
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	    System.out.println("enter moles: ");
	    
	    while(true){
	        int pos;
	        pos = scan.nextInt();

	        if(pos <= 0 || count == 16 || pos > 16){
	            break;
	        }
	        
			board[pos - 1] = 1;
			count++;
	    }
	    
	    printBoard(board);
	    
		while(true){
	        System.out.println("enter mole # to whack: ");
	        int id;
	        id = scan.nextInt();
	        if(id < 1 || id > 16){
	            break;
	        }
	        whack(id-1, board);
	        printBoard(board);
	    }
	}
	*/
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	    System.out.println("enter moles: ");
	   
	    
	    while(true){
	        int pos;
	        pos = scan.nextInt();

	        if(pos <= 0 || count == 16 || pos > 16){
	            break;
	        }
	        
			board[pos - 1] = 1;
			count++;
	    }
	    
	    
	    System.out.println("Solving..");
	    
	    queue.add(board); //add initial state to queue
	    solve();
	    
	    int[] solvedBoard = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	    backtrack(solvedBoard);
	   
	    scan.close();
	}

}
