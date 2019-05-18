import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JPanel;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class PanelAffichage extends JPanel implements ConstantesAffichages {

	private static final long serialVersionUID = 2L;

	private static boolean audioJoue = false;

	private Coureur coureurD;
	private Coureur coureurG;

	// permet de dessiner dans une image en arrière plan
	Image tamponImage = null;

	public PanelAffichage( Coureur coureur1, Coureur coureur2 ) {
		this.coureurD = coureur1;
		this.coureurG = coureur2;
	}

	@Override
	public void paintComponent( Graphics g ) {

		super.paintComponent( g );

		// créer une image tampon de dimension égale au panneau
		tamponImage = createImage( getWidth(), getHeight() );

		// obtenir le contexte graphique du tampon
		Graphics bg = tamponImage.getGraphics();

		// Dessiner dans l'image tampon en arrière plan
		// dessiner l’image du ciel dans le tampon
		bg.drawImage( IMAGE_SKY.getImage(), 0, 0, this );

		// dessiner la String dans le tampon
		bg.setFont( new Font( "Verdana", Font.BOLD, 18 ) );
		bg.drawString( "Cliquez sur le bouton D\u00E9marrer", FENETRE_LARGEUR / 4, 18 );

		// dessiner l’image de l’eau dans le tampon
		bg.drawImage( IMAGE_EAU.getImage(), 0, SKY_HAUTEUR, this );

		// dessiner l’image dynamique du courreurD
		bg.drawImage( coureurD.getImageCoureur(), coureurD.getPosX(), coureurD.getPosY(), this );

		// dessiner l’image dynamique du courreurG dans le tampon
		bg.drawImage( coureurG.getImageCoureur(), coureurG.getPosX(), coureurG.getPosY(), this );

		// dessiner l'image du tampon dans le panneau en dernier
		g.drawImage( tamponImage, 0, 0, this );

		if ( coureurD.getRectangle().intersects( coureurG.getRectangle() ) ) {

			coureurD.setFini( true );
			coureurG.setFini( true );
			
			if ( !audioJoue ) {
				try {
					AudioStream collisionSFX = new AudioStream(
							new FileInputStream( PanelAffichage.class.getResource( "/fic/crash.wav" ).getPath() ) );
					AudioPlayer.player.start( collisionSFX );

					audioJoue = true;
				} catch ( IOException ex ) {
				}
			}
		}
		
		/*
		 * L'acquisition explicite d'un contexte graphique doit être *
		 * accompagnée d'une libération explicite par la méthode * dispose().
		 */
		g.dispose();
	}
}
