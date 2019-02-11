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
	public static double[][] rx;
	public static double[][] ry;
	public static double[][] rz;
	public static double[][] matProj = new double [2][3];
	public static double[][] matTransla3d = {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};
	public static double[][] listevecteurs;

	public Matrices(){
		this.init();
	}

	public void init(){
		this.readVect();
	}

	public void Rotx(double angle){
		this.rx = new double[][] { {1,0,0}, {0, Math.cos(angle), -Math.sin(angle)}, {0, -Math.sin(angle), Math.cos(angle)} };
		for(int i = 0; i < listevecteurs.length; i++){
			matmulv(this.rx,this.listevecteurs[i]);
		}
	}

	public void Roty(double angle){
		this.ry = new double[][] { {Math.cos(angle),0,Math.sin(angle)}, {0, 1, 0}, {-Math.sin(angle), 0,  Math.cos(angle)} };
		for(int i = 0; i < listevecteurs.length; i++){
			matmulv(this.ry,this.listevecteurs[i]);
		}
	}

	public void Rotz(double angle){
		this.rz = new double[][] { {Math.cos(angle),-Math.sin(angle),0}, {Math.sin(angle),Math.cos(angle),0}, {0, 0, 1} };
		for(int i = 0; i < listevecteurs.length; i++){
			matmulv(this.rz,this.listevecteurs[i]);
		}
	}

	public void translateX(double depl, double[][] vecteur){
		this.matTransla3d[0][3] = depl;
		matmul(this.matTransla3d,vecteur);
		this.matTransla3d[0][3] = 0;
	}
	public void translateY(double depl, double[][] vecteur){
		this.matTransla3d[1][3] = depl;
		matmul(this.matTransla3d,vecteur);
		this.matTransla3d[1][3] = 0;
	}
	public void translateZ(double depl, double[][] vecteur){
		this.matTransla3d[2][3] = depl;
		matmul(this.matTransla3d,vecteur);
		this.matTransla3d[2][3] = 0;
	}
	public void affMat(double[][] matrice) {
		String s = "";
		for(int y = 0; y < matrice.length; y++){
			for(int x = 0; x < matrice[0].length; x++){
				s += "["+ matrice[y][x] + "]\t";
			}
			s += "\n";
		}
		System.out.println(s);
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

	public static int countLinesFrom(String filename) throws IOException {
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

	public double[][] matmul(double[][] mat1, double mat2[][]){
		int y1,x2;
		y1 = mat1.length;
		x2 = mat2[0].length;
		double[][] res = new double[y1][x2];
		double temp;
		if(y1 == x2){
			for(int i = 0; i < x2; i++){
				for(int j = 0; j < y1; j++){
					for(int k = 0; k < x2; k++){
						res[i][j] += mat1[i][k] * mat2[k][j];
					}
				}
			}
		}
		return res;
	}

	public double[][] matmulv(double[][] mat1, double vect[]){
		int y1,x2;
		y1 = mat1.length;
		x2 = vect.length;
		double[][] res = new double[y1][x2];
		double temp;
		if(y1 == x2){
			for(int i = 0; i < x2; i++){
				for(int j = 0; j < y1; j++){
					for(int k = 0; k < x2; k++){
						res[i][j] += mat1[i][k] * vect[k];
					}
				}
			}
		}
		return res;
	}
}


//}[] matricemul =
