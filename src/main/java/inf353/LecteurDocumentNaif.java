package inf353;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
//import inf353.AccesSequentielModele1;
public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
    public BufferedReader br ;
    public String motCourant;
    public boolean fds=false;
    public int caractereCourant;
    public int premier;

    // Constructeur
    public LecteurDocumentNaif(String nomdeFichier){

        try{
            this.br= new BufferedReader(new FileReader(nomdeFichier));
            this.motCourant=null;
            //stocker le premier caractere 
            premier = br.read();
            if(premier!=-1){
                br.mark(premier);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    // Méthode pour commencer la lecture du document
    public void demarrer(){
        try{     
            if(fds== true){
                fds = false;
            }
            //on doit placer dans le premier caractère 
            if(premier!=-1){
                br.reset();
            }
            caractereCourant = premier;
            // on doit boucler jusqu'a le premier caractère no pnctuation
            while ((caractereCourant!=-1) && (!estAlphabetique ((char) caractereCourant))) {
                caractereCourant=br.read();
            }
            avancer(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour passer au mot suivante dans le document
    public void avancer(){

       try{
        
            motCourant="";//initialement du mot courant
		    
		    while ((caractereCourant!=-1) && (estAlphabetique ((char) caractereCourant))) {
			    motCourant=motCourant+(char) caractereCourant;
			    caractereCourant=br.read();
		    } 
            
            while ((caractereCourant!=-1) && (!estAlphabetique ((char) caractereCourant))) {
			    caractereCourant=br.read();
		    } 

		    // Fermer le lecteur si la fin du fichier est atteinte et le mot courant est vide
            if (caractereCourant == -1) {
                fds = true;
                br.close();
            }  
            // a completer 
	    }catch (IOException e) {
            e.printStackTrace();
        }

    }

	private boolean estAlphabetique(char caractere) {
        // Vérifier si le caractère est une lettre alphabétique (majuscule ou minuscule)
        return (caractere >= 'A' && caractere <= 'Z') || (caractere >= 'a' && caractere <= 'z') || (caractere >= '0' && caractere <= '9');
    }

    // Méthode pour vérifier si la fin du document est atteinte
    public boolean finDeSequence() {
        return fds;
    }

    // Méthode pour obtenir l'élément actuel dans la séquence
    public String elementCourant(){
           return motCourant;
    }

	}