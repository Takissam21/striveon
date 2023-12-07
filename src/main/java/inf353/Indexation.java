package inf353;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException ;

public class Indexation {
	/*attributs ,1/pour creer la matrice d'occurence
	et stocker les occurences des termes dans les documents
	on utilise MatriceIndexNaif
	2/ pour associer a chaque document un indice unique 
	à savoir le numéro de ligne dans la matrice 
	on a besoin de dictionnairenaif
	3/pour associer à chaque terme unique un indice unique 
	à savoir le numéro de colonne on a besoin de dictionnairenaif*/
	private MatriceIndex matriceIndex;//cela permet de travailler avec n'importe quelle classe qui implémente l'interface MatriceIndex
	private DictionnaireNaif dictermes;
	private DictionnaireNaif dicdocs; 


    public static int nbmots=60000;
	//constructeur 
	public Indexation (String chemin){
        File d= new File (chemin);
		this.matriceIndex=new MatriceIndexNaive(nbmots,compterFichiersTexte(d));
		this.dictermes=new DictionnaireNaif(nbmots);
		this.dicdocs=new DictionnaireNaif(compterFichiersTexte(d));
		
	}
    private  int compterFichiersTexte(File dossier) {
        int count = 0;

        // Liste des fichiers dans le dossier actuel
        File[] fichiers = dossier.listFiles();

        if (fichiers != null) {
            for (File fichier : fichiers) {
                if (fichier.isFile() && fichier.getName().toLowerCase().endsWith(".txt")) {
                    // Si c'est un fichier texte, incrémenter le compteur
                    count++;
                } else if (fichier.isDirectory()) {
                    // Si c'est un sous-dossier, récursivement compter les fichiers texte à l'intérieur
                    count += compterFichiersTexte(fichier);
                }
            }
        }

        return count;
    }

	/*pour associer à chaque document du corpus un indice 
	dans dicdocs .cette méthode prend eventuellement en 
	paramètre le chemin vers le repertoire qui contient les documents */
    
	public void indexerdocs(String chemin){
		File corpus=new File(chemin);//crée un objet File qui represente le repertoire du corpus donné
	    if (corpus.isDirectory()){//on verifie si le chemin correspond a un repertoireexistant{
			File [] documents =corpus.listFiles();//on stocke les fichiers dans un tableau d'objets 
		    if  (documents.length!=0){//repertoire non vide 
				int i=0;
				while (i!=documents.length){
					File document=documents[i];
					indexerundoc(document);
			} 
		    }else{
			    System.out.println("le chemin donné ne correspond pas à un repertoire");
		    }
		}
    }
	private void indexerundoc (File d){
		LecteurDocumentNaif lec=new LecteurDocumentNaif(d.getPath());
		//on lit le document courant
		//indexation du document
		   while (!lec.finDeSequence()) {
            String mot = lec.elementCourant();

            // Appliquer la tokenisation
            String[] tokens = tokeniser(mot);

            for (String token : tokens) {
                int indiceTerme = dictermes.indiceMot(token);
                if (indiceTerme == -1) {
                    // Le terme n'est pas encore dans le dictionnaire des termes
                    dictermes.ajouterMot(token);
                    indiceTerme = dictermes.indiceMot(token);
                }

                int indiceDocument = dicdocs.indiceMot(d.getName());
                if (indiceDocument == -1) {
                    // Le document n'est pas encore dans le dictionnaire des documents
                    dicdocs.ajouterMot(d.getName());
                    indiceDocument = dicdocs.indiceMot(d.getName());
                }

                // Mise à jour de la matrice d'index
                matriceIndex.incremente(indiceDocument, indiceTerme);
            }

            // Passage au mot suivant dans le document
            lec.avancer();
        }
	}
	    private String[] tokeniser(String mot) {
        // Ici, vous pouvez implémenter la tokenisation en prenant en compte les espaces, apostrophes, tirets, points, etc.
        // Vous pouvez utiliser des expressions régulières ou des méthodes spécifiques pour cela.
        // Exemple simple pour commencer (sépare les mots par les espaces)
        return mot.split("\\s+");
    }

    public void sauvegarderIndex(String cheminMatrice, String cheminDictionnaireTermes, String cheminDictionnaireDocs)throws FileNotFoundException {
        // Sauvegarde de la matrice d'index
        matriceIndex.sauver(cheminMatrice);

        // Sauvegarde du dictionnaire des termes
        sauvegarderDictionnaire(dictermes, cheminDictionnaireTermes);

        // Sauvegarde du dictionnaire des documents
        sauvegarderDictionnaire(dicdocs, cheminDictionnaireDocs);
    }

    private void sauvegarderDictionnaire(Dictionnaire dictionnaire, String chemin) {
        // La sauvegarde du dictionnaire peut varier en fonction de votre implémentation concrète.
        // Vous pouvez choisir un format adapté à vos besoins.
        // Ici, on suppose que chaque mot est sur une ligne du fichier.
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(chemin)));

            for (int i = 0; i < dictionnaire.nbMots(); i++) {
                String mot = dictionnaire.motIndice(i);
                if (mot != null) {
                    writer.write(mot);
                    writer.newLine();
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) throws FileNotFoundException {
        Indexation indexeur = new Indexation(1000, 50);// Modifier les valeurs en fonction de votre corpus
        indexeur.indexerdocs("chemin/vers/votre/corpus");
        indexeur.sauvegarderIndex("chemin/vers/matrice.txt", "chemin/vers/dictionnaireTermes.txt", "chemin/vers/dictionnaireDocs.txt");
    }*/
}
	
	