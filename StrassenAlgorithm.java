/**
* Class StrassenAlgorithm used to mutiply two matrices together 
* at a theta(n^lg7) complexity.
*/
public class StrassenAlgorithm {

	/**
	* Empty StrassenAlgorithm contructor to call methods to main class
	*/
	public static void StrassenAlgorithm() {
	}

	/**
	* Takes two matrices of the power of 2 in and multipies them together
	*
	* @param matrixA - first square matirx
	* @param matrixB - second square matrix	
	* @return matrixC - result from multiplying matrixA and matrixB
	*/
	static int[][] multiply(int[][] matrixA, int[][] matrixB) {

		int order = matrixA.length;
		int[][] matrixC = new int[order][order];

		// Base case
		if (order == 1) {
			matrixC[0][0] = matrixA[0][0] * matrixB[0][0];
		} else {

			// Step 1: divide matrixA and matrixB into order/2 submatrices
			int[][] matrixA11 = new int[order/2][order/2];
            int[][] matrixA12 = new int[order/2][order/2];
            int[][] matrixA21 = new int[order/2][order/2];
            int[][] matrixA22 = new int[order/2][order/2];
            int[][] matrixB11 = new int[order/2][order/2];
            int[][] matrixB12 = new int[order/2][order/2];
            int[][] matrixB21 = new int[order/2][order/2];
            int[][] matrixB22 = new int[order/2][order/2];

            // Divide matrixA
            submatrix(matrixA, matrixA11, 0, 0);
            submatrix(matrixA, matrixA12, 0, order/2);
            submatrix(matrixA, matrixA21, order/2, 0);
            submatrix(matrixA, matrixA22, order/2, order/2);

            // Divide matrixB
            submatrix(matrixB, matrixB11, 0, 0);
            submatrix(matrixB, matrixB12, 0, order/2);
            submatrix(matrixB, matrixB21, order/2, 0);
            submatrix(matrixB, matrixB22, order/2, order/2);

            // Step 2: create 10 matrices s1, s2,...,s10 and sum or difference matrices
            // from step 1
            int[][] s1 = subtraction(matrixB12, matrixB22);
            int[][] s2 = addition(matrixA11, matrixA12);
            int[][] s3 = addition(matrixA21, matrixA22);
            int[][] s4 = subtraction(matrixB21, matrixB11);
            int[][] s5 = addition(matrixA11, matrixA22);
            int[][] s6 = addition(matrixB11, matrixB22);
            int[][] s7 = subtraction(matrixA12, matrixA22);
            int[][] s8 = addition(matrixB21, matrixB22);
            int[][] s9 = subtraction(matrixA11, matrixA21);
            int[][] s10 = addition(matrixB11, matrixB12);

            // Step 3: Recursively compute seven matrix product p1,p2,...,p7
            int[][] p1 = multiply(matrixA11, s1);
            int[][] p2 = multiply(s2, matrixB22);
            int[][] p3 = multiply(s3, matrixB11);
            int[][] p4 = multiply(matrixA22, s4);
            int[][] p5 = multiply(s5, s6);
            int[][] p6 = multiply(s7, s8);
            int[][] p7 = multiply(s9, s10);

            // step 4: Compute the desired submatireces from step 3
            int[][] matrixC11 = addition(subtraction(addition(p5, p4), p2), p6);
            int[][] matrixC12 = addition(p1, p2);
            int[][] matrixC21 = addition(p3, p4);
            int[][] matrixC22 = subtraction(subtraction(addition(p5, p1), p3), p7);

            // Combine to create matrixC
            combine(matrixC11, matrixC, 0, 0);
            combine(matrixC12, matrixC, 0, order/2);
            combine(matrixC21, matrixC, order/2, 0);
            combine(matrixC22, matrixC, order/2, order/2);

		}
		return matrixC;
	}

	/**
	* Method use to create submatricies from partent matrix
	*
	* @param matrix - partent matrix
	* @param subMatrix - subMatrix from parent matrix
	* @param r - row
	* @param c - column
	*/
    static void submatrix(int[][] matrix, int[][] subMatrix, int r, int c) {

	    for(int i = 0, j = r; i < subMatrix.length; i++, j++)

	        for(int x = 0, y = c; x < subMatrix.length; x++, y++)

	            subMatrix[i][x] = matrix[j][y];
    }

	/**
	* Method use to subtract two matrices
	*
	* @param matrixA - first square matrix
	* @param subMatrix - second square matrix
	* @return matrixC - result from subtacting two matrices
	*/
	static int[][] subtraction(int[][] matrixA, int[][] matrixB) {

    	int order = matrixA.length;

	    int[][] matrixC = new int[order][order];

	    for (int i = 0; i < order; i++)

	        for (int j = 0; j < order; j++)

	            matrixC[i][j] = matrixA[i][j] - matrixB[i][j];

	    return matrixC;

    }

	/**
	* Method use to add two matrices
	*
	* @param matrixA - first square matrix
	* @param subMatrix - second square matrix
	* @return matrixC - result from adding two matrices
	*/    
	static int[][] addition(int[][] matrixA, int[][] matrixB) {

    	int order = matrixA.length;

        int[][] matrixC = new int[order][order];

        for (int i = 0; i < order; i++)

            for (int j = 0; j < order; j++)

                matrixC[i][j] = matrixA[i][j] + matrixB[i][j];

        return matrixC;

    }

    // Combine submatrices to parent matrix
    /**
	* Method use to subtract two matrices
	*
	* @param submMatrix - subMatrix from parent matrix
	* @param matrix - partent matrix
	* @param r - row
	* @param c - column
	*/
    static void combine(int[][] submMatrix, int[][] matrix, int r, int c) {

	    for(int i = 0, j = r; i < submMatrix.length; i++, j++)

	        for(int x = 0, y = c; x < submMatrix.length; x++, y++)

	            matrix[j][y] = submMatrix[i][x];

	}  
}









