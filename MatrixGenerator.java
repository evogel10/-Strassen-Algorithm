import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

/**
* Class MatrixGenerator is used to create matrices from an input file
* and to write the matrices to an output file.
*
* @author Eric Vogel
*/
public class MatrixGenerator {

	/**
	* Empty MatrixGenerator contructor to call methods to main class.
	*/
	public static void MatrixGenerator(){
	}

	/**
	* Uses the input string to crate a 2D matrix.
	*
	* @param matrix - empty matrix.
	* @param line - input file.
	* @param order - order of the matrix.
	* @return matrix - populated matrix.
	*/
	static int[][] createMatrix(int[][] matrix, Scanner line, int order) {
		
		String[] arr;
		// int[][] error = new int[0][0];

		// Loops thorough 2D matrix.
		for (int i = 0; i < order; i++) {

			// 1D array to contain all values excluding spaces.
			arr = line.nextLine().split(" +");

			for (int j = 0; j < arr.length; j++) {

				// Error checking.
				if (arr.length != order){
					matrix[0][0] = -9999999;
				}else{
					// Adds values to matrix.
					matrix[i][j] = Integer.parseInt(arr[j]);	
				}
			}
		}

		return matrix;
	}

	/**
	* Writes matrix to output file.
	*
	* @param matrix - matrix contain values from input file.
	* @param writer - output file.
	*/
	static void writeMatrixToFile(int[][] matrix, BufferedWriter writer) throws IOException {

		for (int i = 0; i < matrix.length; i++) {
        
        	for (int j = 0; j < matrix.length; j++) {
            	
            	// Write to file and add space after the element.
            	writer.write(Integer.toString(matrix[i][j]) + " ");
			}
			// mMve to next line of matrix.
			writer.newLine();
		}
		writer.newLine();

	}
}
