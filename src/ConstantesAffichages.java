import javax.swing.ImageIcon;

public interface ConstantesAffichages {

	public ImageIcon IMAGE_EAU = new ImageIcon( PanelAffichage.class.getResource( "/fic/eau.jpg" ) );
	public ImageIcon IMAGE_SKY = new ImageIcon( PanelAffichage.class.getResource( "/fic/Sky.gif" ) );
	public final int SKY_LARGEUR = IMAGE_SKY.getIconWidth();
	public final int SKY_HAUTEUR = IMAGE_SKY.getIconHeight();
	public final int EAU_LARGEUR = IMAGE_EAU.getIconWidth();
	public final int EAU_HAUTEUR = IMAGE_EAU.getIconHeight();

	/*
	 * pour fixer les dimensions de la fenêtre. Ces dimensions doivent être
	 * exprimées en fonction des constantes déclarées ci-haut
	 */
	public final int FENETRE_LARGEUR = SKY_LARGEUR;
	public final int FENETRE_HAUTEUR = EAU_HAUTEUR + SKY_HAUTEUR + 100;
	// 100 est la hauteur du panneau contenant les boutons
}
