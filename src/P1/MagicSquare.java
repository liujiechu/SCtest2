package P1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MagicSquare {
	static String[][] square;
	static int[][] sq;
	/*
	 * To see whether the symbol of the n.txt opened is legal 
	 * we say it's illegal when it does not conform the definition of Magic Square
	 * (the number of rows and columns is not equal, not a matrix, etc),
	 * some numbers in the matrix are not positive integers, 
	 * numbers are not divided by "\t", etc
	 */
	public static boolean isLegalMagicSquare(String filename) {
		Set<Integer> numbers = new HashSet<>();
		File fl = new File(filename);
		try {
			int cnt_row = 0;
			int cnt_com = 0;
			int sum_row = 0;
			int sum_com = 0;
			int sum_dia1 = 0;
			int sum_dia2 = 0;
			int sum = 0;
			try (BufferedReader in = new BufferedReader(new FileReader(fl))) {
				String str = new String();
				String[] myline = null;
				
				str = in.readLine();
				if(str != null) {
					myline = str.split("\t");
					cnt_com = myline.length;
				}
				else
					return false;
				
				//whether there are illegal chars without "\t"
				for(int i = 0; i < cnt_com; i++) {
					if(!myline[i].matches("[0-9]+")) {
						System.out.println("Illegal number in txt!");
						return false;
					}
				}
				
				ArrayList<String[]> list = new ArrayList<String[]>();
				list.add(myline);
				cnt_row++;
				
				while((str = in.readLine()) != null) {
					myline = str.split("\t");
					if(myline.length != cnt_com) {
						System.out.println("That's not even a matrix!");
						return false;
					}
					
					for(int i = 0; i < cnt_com; i++) {
						if(!myline[i].matches("[0-9]+")) {
							System.out.println("Illegal number in txt!");
							return false;
						}
					}
					list.add(myline);
					cnt_row++;
				}
				if(cnt_row != cnt_com) {
					System.out.println("That's not even a strict matrix!");
					return false;
				}
				sq = new int[cnt_row][cnt_row];
				for(int k = 0; k < cnt_row; k++) {
					for(int j = 0; j < cnt_com; j++) {
						sq[k][j] = Integer.valueOf(list.get(k)[j]);
						if(!numbers.contains(sq[k][j]))
							numbers.add(sq[k][j]);
					}
				}
				in.close();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//we read a file above, following is to check it's a Magic Square or not
			for(int i = 0; i < cnt_com; i++) {
				sum += sq[0][i];
			}
			for(int i = 0; i < cnt_row; i++) {
				for(int j = 0; j < cnt_com; j++) {
					sum_row += sq[i][j];
					sum_com += sq[j][i];
					if(i == j) {
						sum_dia1 += sq[i][j];
					}
					if(i + j == cnt_row - 1) {
						sum_dia2 += sq[i][j];
					}
				}
				if(sum_row != sum || sum_com != sum) {
					System.out.println("The sum of rows or columns is not equal with others");
					return false;
				}
				sum_row = 0;
				sum_com = 0;
			}
			if(sum_dia1 != sum || sum_dia2 != sum) {
				System.out.println("The sum of diagonal is not equal with the sum of rows and columns");
				return false;
			}
			if(numbers.size() != cnt_row * cnt_com)
				return false;
			return true;
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean generateMagicSquare(int n) {
		if(n % 2 == 0 || n <= 0) {
			System.out.println("Input Illeagl number n!");
			return false;
		}
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;
			if (i % n == 0)
				row++;
			else {
				if (row == 0)
					row = n - 1;
				else
					row--;
				if (col == (n - 1))
					col = 0;
				else
					col++;
			} 
		}
		try {
			File fl = new File("src/P1/txt/6.txt");
			FileWriter out = new FileWriter(fl); 
			
			for(i = 0; i < n; i++) {
				for(j = 0; j < n; j++) {
					out.write(magic[i][j] + "\t");
				}
				out.write("\n");
			}
			out.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		/*
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");
			System.out.println();
		}
		*/
		return true;
	}
	
	
	public static void main(String[] args) {
		boolean flag;
		for(int i = 1; i <= 5; i++) {
			String add = new String("src/P1/txt/" + i + ".txt");
			flag = isLegalMagicSquare(add);
			if(flag)
				System.out.println(i + ".txt is a magic square." + "\n");
			else
				System.out.println(i + ".txt is not a magic sqaure." + "\n");
		}
		System.out.println("Input the power of the magic sqaure you want:");
		try (Scanner scan = new Scanner(System.in)) {
			flag = generateMagicSquare(scan.nextInt());
		}if(flag) {
			flag = isLegalMagicSquare("src/P1/txt/6.txt");
			if(flag)
				System.out.println("6.txt is a magic square." + "\n");
			else
				System.out.println("6.txt is not a magic sqaure." + "\n");
		}
	}
}
