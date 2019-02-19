import java.lang.Object;
public final class OpeMatrices{

  private static double[][] rx;
	private static double[][] ry;
	private static double[][] rz;
  private static double[][] matTransla3d = {{1,0,0},{0,1,0},{0,0,1}};
  public static double[][] matProj = {{1,0,0},{0,1,0}};

  public static double[][] Rotx(double[][] listevecteurs, double angle){

    OpeMatrices.rx = new double[][] {
      {1,0,0},
      {0, Math.cos(angle), -Math.sin(angle)},
      {0, Math.sin(angle), Math.cos(angle)}
    };

    double[][]listev = new double[listevecteurs.length][3];
    for(int i = 0; i < listevecteurs.length; i++){
      listev[i] = matmulv(OpeMatrices.rx,listevecteurs[i]);
    }
    return listev;
  }

  public static double[][] Roty(double[][] listevecteurs, double angle){

    OpeMatrices.ry = new double[][] {
      {Math.cos(angle),0,Math.sin(angle)},
      {0, 1, 0},
      {-Math.sin(angle), 0,  Math.cos(angle)}
    };

    double[][]listev = new double[listevecteurs.length][3];
    for(int i = 0; i < listevecteurs.length; i++){
      listev[i] = matmulv(OpeMatrices.ry,listevecteurs[i]);
    }
    return listev;
  }

  public static double[][] Rotz(double[][] listevecteurs, double angle){

    OpeMatrices.rz = new double[][] {
      {Math.cos(angle),-Math.sin(angle),0},
      {Math.sin(angle),Math.cos(angle),0},
      {0, 0, 1}
    };

    double[][]listev = new double[listevecteurs.length][3];
    for(int i = 0; i < listevecteurs.length; i++){
      listev[i] = matmulv(OpeMatrices.rz,listevecteurs[i]);
    }
    return listev;
  }

  public static double[][] translateX(double[][] vecteur, double depl){
    double[][] vectClone = new double[vecteur.length][3];
    OpeMatrices.matTransla3d[0][2] = depl;
    for(int i = 0; i < vecteur.length; i++){
      vectClone[i] = matmulv(OpeMatrices.matTransla3d,vecteur[i]);
    }
    OpeMatrices.matTransla3d[0][2] = 0;
    return vectClone;
  }

  public static double[][] translateY(double[][] vecteur, double depl){
    double[][] vectClone = new double[vecteur.length][3];
    OpeMatrices.matTransla3d[1][2] = depl;
    for(int i = 0; i < vecteur.length; i++){
      vectClone[i] = matmulv(OpeMatrices.matTransla3d,vecteur[i]);
    }
    OpeMatrices.matTransla3d[1][2] = 0;
    return vectClone;
  }

  public static double[][] translateZ(double[][] vecteur, double depl){
    double[][] vectClone = new double[vecteur.length][3];
    OpeMatrices.matTransla3d[2][2] = depl;
    for(int i = 0; i < vecteur.length; i++){
      vectClone[i] = matmulv(OpeMatrices.matTransla3d,vecteur[i]);
    }
    OpeMatrices.matTransla3d[2][2] = 0;
    return vectClone;
  }

  public static void affMat(double[][] matrice) {
    String s = "";
    for(int y = 0; y < matrice.length; y++){
      for(int x = 0; x < matrice[0].length; x++){
        s += "["+ matrice[y][x] + "]\t";
      }
      s += "\n";
    }
    System.out.println(s);
  }

  public static double[] matmulv(double[][] mat1, double vect[]){
    int y1,x2;
    y1 = mat1[0].length;
    x2 = vect.length;
    double[] res = new double[mat1[0].length];
    double temp;
    if(y1 == x2){
      for(int i = 0; i < mat1.length; i++){
          for(int k = 0; k < x2; k++){
            res[i] += mat1[i][k] * vect[k];
          }
      }
    }
    return res;
  }

  public static double[][] matmul(double[][] mat1, double mat2[][]){
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

  public static double[][] ROTATE(double[][]listevecteurs, double xAngle, double yAngle, double zAngle){
    double[][] listev = new double[listevecteurs.length][3];
    if(xAngle != 0){
      listev = OpeMatrices.Rotx(listevecteurs, xAngle);
    }
    if(yAngle != 0){
      if(listev[0][0] == 0.0)
        listev = OpeMatrices.Roty(listevecteurs, yAngle);
      else
        listev = OpeMatrices.Roty(listev, yAngle);
    }
    if(zAngle != 0){
      if(listev[0][0] == 0.0){
        listev = OpeMatrices.Rotz(listevecteurs, zAngle);
      }
      else
        listev = OpeMatrices.Rotz(listev, zAngle);
    }
    return listev;
  }
  public static double[][] TRANSLATE(double[][]listevecteurs, double xVal, double yVal, double zVal){
    double[][] listev = new double[listevecteurs.length][3];
    if(xVal != 0){
      listev = OpeMatrices.translateX(listevecteurs, xVal);
    }
    if(yVal != 0){
      if(listev[0][0] == 0.0)
        listev = OpeMatrices.translateY(listevecteurs, yVal);
      else
        listev = OpeMatrices.translateY(listev, yVal);
    }
    if(zVal != 0){
      if(listev[0][0] == 0.0){
        listev = OpeMatrices.translateZ(listevecteurs, zVal);
      }
      else
        listev = OpeMatrices.translateZ(listev, zVal);
    }
    return listev;
  }
}
