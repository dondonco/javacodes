package com.don.example;

import java.util.Scanner;

public class TicTacToe {
	
	private static int player = 1;
	private static int position = -1;
	private static Scanner scanner = new Scanner(System.in);
	private static int[] array;
	private static int column = 0, row = 0;
	
	public static void main(String[]args) {
		start();
		while(true){
			printBoard(array);
			System.out.print("Player " + player +": ");
			position = Integer.parseInt(scanner.nextInt()+"");
			move(array, position, player);
			if(checkHorizontal(array,player) || checkVertical(array,player) 
					|| checkDiagonalLeftToRight(array, player) || checkDiagonalRightToLeft(array, player)) {
				System.out.println("Player " + player + " Wins!!!");
				printBoard(array);
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
	
	private static void start() {
		System.out.print("Input rows: ");
		column = scanner.nextInt();
		System.out.print("Input column: ");
		row = scanner.nextInt();
		array = clearBoard(row, column);
	}
	
	private static int[] clearBoard(int row, int column) {
		int[] array = new int[row*column];
		for(int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
		return array;
	}
	
	private static boolean check(int[] array, int place) {
		if(array[place] == 0)
			return true;
		return false;
	}
	
	private static void move(int[] array, int place, int p) {
		if(positionCheck(place)) {
			if(check(array, place)) {
				array[place] = p;
			}else {
				invalidMove(2);
			}
		}else {
			invalidMove(1);
		}
	}
	
	private static boolean positionCheck(int position) {
		if(position >= 0 && position < array.length) {
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
		move(array, position, player);
	}
	
	private static void printBoard(int[]array) {
		for(int i = 0; i < array.length; i++) {
			String a = i+"";
			if(i != 0 && i % row == 0) {
				System.out.println("");
			}
			if(array[i] == 1) {
				a = "X";
			}else if(array[i] == 2) {
				a = "O";
			}else if(array[i] == 0) {
				a = i+"";
			}
			System.out.print(a + "\t");
		}
		System.out.println("");
	}
	
	private static boolean checkHorizontal(int[]array, int player) {
		String input = "";
		for(int i = 0; i < array.length; i+=column) {
			for(int j = i; j < i+column; j++) {
				input += array[j];
			}
			if(input.contains(player+""+player+""+player)){
				return true;
			}
			input = "";
			
		}
		return false;
	}
	
	private static boolean checkVertical(int[]array, int player) {
		for(int i = 0; i < column; i++) {
			String input = "";
			for(int j = i; j < array.length; j+=row) {
				input += array[j];
			}
			if(input.contains(player+""+player+""+player)){
				return true;
			}
		}
		return false;
	}

	private static boolean checkDiagonalLeftToRight(int[]array, int player) {
		for(int i = 0;  i < array.length; i++) {
			String input = "";
			for(int j = i; j < array.length; j+=(column+1)) {
				if(j != 0 && j % column == 0) {
					continue;
				}
				input+=array[j];
			}
			if(input.contains(player+""+player+""+player)){
				return true;
			}
		}
		return false;
	}	
	private static boolean checkDiagonalRightToLeft(int[]array, int player) {
		for(int i = 0;  i < (array.length-(2*column)); i++) {
			String input = "";
			for(int j = i; j < array.length; j+=(column-1)) {
				if(j == 0) {
					break;
				}
				input+=array[j];
				if(j % column == 0) {
					break;
				}
			}
			if(input.contains(player+""+player+""+player)){
				return true;
			}
		}
		return false;
	}
}
