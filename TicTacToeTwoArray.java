package com.don.example;

import java.util.Scanner;

public class TicTacToeTwoArray {

	private static int[][]board;
	private static int row = 0, col = 0;
	private static Scanner scanner = new Scanner(System.in);
	private static int player = 1;
	private static int position = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		start();
		while(true) {
			printBoard(board);
			System.out.print("Player " + player +": ");
			position = Integer.parseInt(scanner.nextInt()+"");
			move(board,position, player);
			if(checkHorizontal(board,player) || checkVertical(board,player) 
					|| checkDiagonalLeftToRight(board, player) || checkDiagonalRightToLeft(board, player)) {
				System.out.println("Player " + player + " Wins!!!");
				printBoard(board);
				System.out.println("Do you want to play again ?[y/n]: ");
				String input = scanner.next();
				if(input.equalsIgnoreCase("y")) {
					start();
					continue;
				}else {
					break;
				}
			}
			if(player == 1)
				player = 2;
			else
				player = 1;
		}
	}
	
	private static boolean checkDiagonalRightToLeft(int[][] board, int player2) {
		// TODO Auto-generated method stub
		for(int i = 0; i < row*col; i++) {
			String input = "";
			for(int j = i; j < col*row; j+=(col-1)) {
				int[]value = arraySplit(j);
				if(j == 0) {
					break;
				}
				input+=board[value[0]][value[1]];
				if(j % col == 0) {
					break;
				}
			}

			if(input.contains(player2+""+player2+""+player2)) {
				return true;
			}
			
		}
		return false;
	}

	private static boolean checkDiagonalLeftToRight(int[][] board, int player2) {
		for(int i = 0;  i < col*row; i++) {
			String input = "";
			for(int j = i; j < col*row; j+=(col+1)) {
				int[]value = arraySplit(j);
				if(j != 0 && j % col == 0) {
					continue;
				}
				input+=board[value[0]][value[1]];
			}
			if(input.contains(player2+""+player2+""+player2)){
				return true;
			}
		}
		return false;
	}

	private static boolean checkVertical(int[][] board, int player2) {
		// TODO Auto-generated method stub
		for(int i = 0; i < col; i++) {
			String input = "";
			for(int j = 0; j < row; j++) {
				input+=board[i][j];
			}
			if(input.contains(player2+""+player2+""+player2)) {
				return true;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean checkHorizontal(int[][] board, int player2) {
		// TODO Auto-generated method stub
		for(int i = 0; i < row; i++) {
			String input = "";
			for(int j = 0; j < col; j++) {
				input+=board[j][i];
			}
			if(input.contains(player2+""+player2+""+player2)) {
				return true;
			}
		}
		return false;
	}

	private static void start() {
		System.out.print("Input rows: ");
		col = scanner.nextInt();
		System.out.print("Input column: ");
		row = scanner.nextInt();
		board = initializeBoard(row, col);
		
	}
	
	private static int[][]initializeBoard(int row, int column){
		int[][]board = new int[row][column];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				board[i][j] = 0;
			}
		}
		return board;
	}
	
	private static void printBoard(int[][]board) {
		int count = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				int a = board[i][j];
				String b = "";
				if(a == 1) {
					b = "X";
				}else if(a == 2) {
					b = "O";
				}else {
					b = count+"";
				}
				count++;
				System.out.print(b+"\t");
			}
			System.out.println();
		}
	}
	
	private static int [] arraySplit(int position) {
		int a[] = {0,0};
		a[0] = position / row;
		a[1] = position % col;
		return a;
	}

	private static boolean check(int[][] board, int place) {
		int[] pos = arraySplit(place);
		if(board[pos[0]][pos[1]] == 0) {
			return true;
		}
		return false;
	}
	
	private static void move(int[][] board, int place, int p) {
		if(positionCheck(place)) {
			if(check(board, place)) {
				int []value = arraySplit(place);
				board[value[0]][value[1]] = p;
			}else {
				invalidMove(2);
			}
		}else {
			invalidMove(1);
		}
	}
	
	private static boolean positionCheck(int position) {
		if(position >= 0 && position < row * col) {
			return true;
		}
		return false;
	}

	private static void invalidMove(int check) {
		if(check == 1) {
			System.out.print("Invalid move, Choose a position again: ");
		}else if(check == 2){
			System.out.print("Position already taken, Choose a position again: ");
		}
		position = Integer.parseInt(scanner.nextInt()+"");
		move(board, position, player);
	}

}
