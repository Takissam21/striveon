package inf353;
//import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class MatriceIndexNaive implements MatriceIndex {

    
    //attributs
    public int nbdoc;// nombre de ligne
    public int nbMots;// nombre de colomuns
    public int [] [] matrice;

    // Constructeur1 (Créer une matrice intialiser avec 0)

    public  MatriceIndexNaive (int m,int d){

        this.nbdoc=d;
        this.nbMots=m;
        this.matrice=new int[d][m];

        int i=0;
        //remplissage de la matrice
        while(i!=d){
            int j=0;
            while (j!=m){
                this.matrice[i][j]=0; 
                j++;
            }
            i++;
        }

    }
    // Constructeur 2
    public  MatriceIndexNaive (String nomDeFichier){
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomDeFichier));
            // Lire le nombre de lignes et de colonnes depuis les deux premières lignes du fichier
            nbdoc = Integer.parseInt(br.readLine());
            nbMots = Integer.parseInt(br.readLine());
        
            // Initialiser la matrice en fonction des dimensions lues
            matrice = new int[nbdoc][nbMots];
        
            // Lire la matrice à partir du reste du fichier
            for (int i = 0; i < nbdoc; i++) {
                for (int j = 0; j < nbMots; j++) {
                    matrice[i][j] = Integer.parseInt(br.readLine());
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    /**
     * Sauvegarde de la matrice d'occurence dans le fichier nomDeFichier. Le format est libre,
     * mais doit privilégier la vitesse de chargement et la compacité (taille du fichier).
     *
     *  nomDeFichier
     */
     //@param
    @Override
    public void sauver(String nomDeFichier){
       try{
          String chemin = "/src/main/java/inf353/ressources/"+ nomDeFichier +".txt";
          File fichier = new File(chemin); //creation du fichier 
          FileWriter fileWriter = new FileWriter(fichier);
          // Créer un objet BufferedWriter pour écrire du texte dans le fichier de manière efficace
          BufferedWriter writer = new BufferedWriter(fileWriter);
          writer.write(String.valueOf(nbdoc));
          writer.newLine();
          writer.write(String.valueOf(nbMots));
          writer.newLine();
          // Parcourir la matrice et écrire chaque élément dans le fichier
            for (int i = 0; i < matrice.length; i++) {
                for (int j = 0; j < matrice[i].length; j++) {
                    writer.write(String.valueOf(matrice[i][j]));
                    writer.newLine();
                }
            }
            writer.close();
            fileWriter.close();
        }catch (IOException e){
           e.printStackTrace();
       }   
    }

    /**
     * retourne le nombre d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     * @return       le nombre d'occurences du terme dans le document
     */
     @Override
    public  int val(int ndoc, int nterm){
        return matrice[ndoc][nterm];
    }

    /**
     * Ajoute 1 au nombre d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     */
     @Override
    public  void incremente(int ndoc, int nterm) {
        this.matrice[ndoc][nterm]++;
    }

    /**
     * affecte à val le numéro d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     * @param val    la nouvelle valeur du nombre d'occurence
     */
     @Override
    public void affecte(int ndoc, int nterm, int val){
        this.matrice[ndoc][nterm]=val;
    }
}