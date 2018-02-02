import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
* Lab 1 reads in an input file containing pairs of square matrices and their associated
* order. The program then multiplies these pairs together using a standard multiplication
* method and Strassen's Method. The product of the matrices pairs and their run times are
* writen to an output file.
*
* @author Eric Vogel
* @version 9/25/16
*/

public class EVogelLab1 {

	/**
	* Main method takes in an input file and uses the method readInData to
	* manupulate the data
	*
	* @param Args is the file name in the command line
	* @exception FileNotFoundException
	*/
	public static void main(String[] args) throws IOException {

		try {
			readInData(new Scanner(new FileReader(args[0])), new File(args[1]));
		} catch (FileNotFoundException ex) {
			System.out.print("Error: " + ex);
		}
	}

	/**
	* Takes input file from main method and reads in line by line to build the matrices
	*
	* @param input file with containing matrices and their orders
	* @param output file
	*/
	private static void readInData(Scanner input, File file) throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
		int order;
		String line;
		Stopwatch watch = new Stopwatch();
		StringBuilder stringBuilder = new StringBuilder();

		// Sets header for results table.
		stringBuilder.append("----------------------Algorithm Running Time--------------------------\n");
		stringBuilder.append("Matrix Order            Algorithm                   Time In Nanosecond\n");

		try {
			// Parses through the input file line by line
			while ((line = (input.nextLine())) != null) {

				// Skips blank lines in input file
				if(line.trim().equals("") || line.equals("\n")) {
    				continue;		
    			}
    			// Checks for the order of the matrix
				else if ((Integer.parseInt(line.trim()) > 0)) {

					// Sets the order.
					order = Integer.parseInt(line.trim());

					int[][] tmpA = new int[order][order];
					int[][] tmpB = new int[order][order];

					// Creates matrix from the input
					int[][] matrixA = MatrixGenerator.createMatrix(tmpA, input, order);
					int[][] matrixB = MatrixGenerator.createMatrix(tmpB, input, order);

					// Error checking.
					if (matrixA[0][0] != -9999999 && matrixB[0][0] != -9999999){

						// Creates matrix from multiplying two square input matrices
						int[][] matrixC = MatrixMultiplier.multiply(matrixA, matrixB, order);

						// Writes the matrix and its order to output file
						writer.write("Order: " + order + "\n");
						writer.write("Matrix A: \n");
						MatrixGenerator.writeMatrixToFile(matrixA, writer);
						writer.write("Matrix B: \n");
						MatrixGenerator.writeMatrixToFile(matrixB, writer);
						writer.write("Matrix A * Matrix B: \n");
						MatrixGenerator.writeMatrixToFile(matrixC, writer);

						// Runs both standar multiplication and Strassen's Method and records how long each take in nanoseconds
						watch.start();
						int[][] matrixRegMult = MatrixMultiplier.multiply(matrixA, matrixB, order);
						watch.stop();
						stringBuilder.append("     " + order + "                  Regular Multiplication      " + watch.timeInNanoseconds() +"\n" );
						watch.start();
						int[][] matrixStrassen = StrassenAlgorithm.multiply(matrixA, matrixB);
						watch.stop();
						stringBuilder.append("     " + order + "                  Strassen's Method           " + watch.timeInNanoseconds() +"\n" );
						writer.write("----------------------\n");	
					} else {
						writer.write("Your Matrix is not square.\n");
						writer.write("----------------------\n");					}
				}
			}
		} catch (NoSuchElementException ex) {
			// End of file
		} catch (NumberFormatException ex) {
			// If a a letter is in the matrix
			writer.write("Invalid Matrix.\n");
		}
		// Writes the results to the file and closes the writer
		String results = stringBuilder.toString();
		writer.write(results);
		writer.close();
	}
}