import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
public class Matrices{

	/* TODO : passer de la projection orthogonale a la projection avec perspective
						et trouver une solution pour la transition de matrices
	 */

	public double[][] listevecteurs;
	public static double[][] listevecteursPosBase;
	public double[][] matPosCam;
	public double[][] matOriCam;

	public Matrices(){
		this.init();
	}

	public void init(){
		this.readVect();
	}

	public double[][] matRand(int x, int y){
		double[][] matrice = new double[y][x];
		for(int i = 0; i < y; i++){
			for(int j = 0; j < x; j++){
				matrice[i][j] = Math.random() * 10;
			}
		}
		return matrice;
	}

	public int countLinesFrom(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];

	        int readChars = is.read(c);
	        if (readChars == -1) {
	            // bail out if nothing to read
	            return 0;
	        }

	        // make it easy for the optimizer to tune this loop
	        int count = 0;
	        while (readChars == 1024) {
	            for (int i=0; i<1024;) {
	                if (c[i++] == '\n') {
	                    ++count;
	                }
	            }
	            readChars = is.read(c);
	        }

	        // count remaining characters
	        while (readChars != -1) {
	            System.out.println(readChars);
	            for (int i=0; i<readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	            readChars = is.read(c);
	        }

	        return count == 0 ? 1 : count;
	    } finally {
	        is.close();
	    }
	}

	public void readVect(){
		try{
			File file = new File("/iutv/Mes_Montages/11701691/S3/pourletravail/projection/Vecteurs/posVecteurs.txt");
			int nbvect = countLinesFrom("/iutv/Mes_Montages/11701691/S3/pourletravail/projection/Vecteurs/posVecteurs.txt");
			this.listevecteursPosBase = new double[nbvect][3];
			this.listevecteurs = new double[nbvect][3];
			Scanner sc = new Scanner(file);
			int i = 0;
			while(sc.hasNextLine())
			{
    		String inputOfFile = sc.nextLine();

    		String[] info = inputOfFile.split(":");
    		try{
        	if(info.length == 3)
        	{
						this.listevecteursPosBase[i][0] = Double.parseDouble(info[0]);
						this.listevecteursPosBase[i][1] = Double.parseDouble(info[1]);
						this.listevecteursPosBase[i][2] = Double.parseDouble(info[2]);
						this.listevecteurs[i][0] = Double.parseDouble(info[0]);
						this.listevecteurs[i][1] = Double.parseDouble(info[1]);
						this.listevecteurs[i][2] = Double.parseDouble(info[2]);
            i++;
        	}
        	else
        	{
          	System.err.println("Input incorrectly formatted: " + inputOfFile);
        	}
    		}catch (NumberFormatException e) {
        	System.err.println("Error when trying to parse: " + inputOfFile);
    		}
			}
			sc.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}


	public double getPointX(int pos){
		return (OpeMatrices.matmulv(OpeMatrices.matProj, this.listevecteurs[pos])[0]);
	}
	public double getPointy(int pos){
		return (OpeMatrices.matmulv(OpeMatrices.matProj, this.listevecteurs[pos])[1]);
	}
}


//}[] matricemul =
