import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Coureur extends Thread implements ConstantesAffichages {

	// simule le sleep. La vitesse peut être fixe exemple 10 pour 10
	// millisecondes ou aléatoire.
	private int vitesse = 10;
	private int posX; // position X du coureur
	private int posY; // position Y du coureur

	public boolean fini = false;// fini =true quand posX= extrémité fenêtre
	private boolean attente = false;// attente=true quand le bouton arrêter est
									// cliqué

	private ImageIcon imageCoureur;// L'image du coureur
	private int largeur;// largeur de l'image
	private int hauteur;// hauteur de l'image
	private char orientation;// D pour droite, G pour gauche

	public Coureur( char orientation, ImageIcon imageCoureur ) {

		this.imageCoureur = imageCoureur;
		this.orientation = orientation;

		largeur = imageCoureur.getIconWidth();
		hauteur = imageCoureur.getIconHeight();

		// initialiser la position de départ du coureur
		posY = SKY_HAUTEUR - hauteur;

		if ( orientation == 'D' ) {

			posX = FENETRE_LARGEUR;
		} else {

			posX = 0 - largeur;
		}
	}

	@Override
	public void run() {
		while ( !fini ) {
			if ( !attente ) {

				avancer();
			} else {
				try {
					sleep(10);
				} catch ( InterruptedException e ) {
				}
			}
		}
	}

	private void avancer() {

		try {
			// pour simuler la vitesse de la course si orientation = ‘D’ avancer
			// à droite sinon avancer à gauche
			sleep( vitesse );

			if ( orientation == 'G' ) {

				posX++;
			} else {

				posX--;
			}
		} catch ( InterruptedException e ) {
		}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX( int posX ) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY( int posY ) {
		this.posY = posY;
	}

	public boolean isFini() {
		return fini;
	}

	public void setFini( boolean fini ) {
		this.fini = fini;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur( int largeur ) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur( int hauteur ) {
		this.hauteur = hauteur;
	}

	public Image getImageCoureur() {
		return imageCoureur.getImage();
	}

	public void setAttente( boolean attente ) {
		this.attente = attente;
	}
	
	public synchronized Rectangle getRectangle() {
		
		return new Rectangle(posX, posY, largeur, hauteur);
	}
}
