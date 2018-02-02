/**
* Class MatrixMultiplier is used to multiply two matricies together
* at a theta(n^3) complexity.
*/
public class MatrixMultiplier {

	/**
	* Empty MatrixMultiplier contructor to call methods to main class
	*/
	public static void MatrixMultiplier() {
	}

	/**
	* Takes two matrices of the power of 2 in and multipies them together
	*
	* @param matrixA - first square matirx
	* @param matrixB - second square matrix
	* @param order - order of square matrices
	* @return matrixC - result from multiplying matrixA and matrixB
	*/
	static int[][] multiply(int[][] matrixA, int [][] matrixB, int order) {

        int[][] matrixC = new int[order][order];
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                for (int k = 0; k < order; k++) {
                   matrixC[i][j] = matrixC[i][j] + matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return matrixC;
	}
}