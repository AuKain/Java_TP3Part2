import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FenetreCourse extends JFrame implements Runnable, ActionListener, ConstantesAffichages {

	private static final long serialVersionUID = 1L;

	// déclarez ici les boutons
	private JButton btnDemarrer, btnArreter, btnContinuer;

	// déclarez ici les images des coureurs
	private ImageIcon imageCoureurD = new ImageIcon( PanelAffichage.class.getResource( "/fic/cycliste.gif" ) );
	private ImageIcon imageCoureurG = new ImageIcon( PanelAffichage.class.getResource( "/fic/coureur.gif" ) );

	// déclarer et initialiser les deux coureurs
	private Coureur coureur1 = new Coureur( 'D', imageCoureurD );
	private Coureur coureur2 = new Coureur( 'G', imageCoureurG );

	// déclarer ici le panneau du milieu (objet PanelAffichage)
	private PanelAffichage affiche;

	public FenetreCourse() {
		setResizable( false );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize( new Dimension( 607, 363 ) );
		setTitle( "Course" );

		JPanel panelBoutons = new JPanel();
		getContentPane().add( panelBoutons, BorderLayout.SOUTH );

		btnDemarrer = new JButton( "D\u00E9marrer" );
		btnDemarrer.addActionListener( this );
		panelBoutons.add( btnDemarrer );

		btnArreter = new JButton( "Arr\u00EAter" );
		btnArreter.addActionListener( this );
		panelBoutons.add( btnArreter );

		btnContinuer = new JButton( "Continuer" );
		btnContinuer.addActionListener( this );
		panelBoutons.add( btnContinuer );

		affiche = new PanelAffichage( coureur1, coureur2 );
		getContentPane().add( affiche, BorderLayout.CENTER );
	}

	@Override
	public void run() {
		while ( true ) {
			
			affiche.repaint();
			try {
				
				Thread.sleep( 10 );
			} catch ( InterruptedException e ) {
			}
		}
	}

	@Override
	public void actionPerformed( ActionEvent e ) {

		if ( e.getSource() == btnDemarrer ) {

			coureur1.start();
			coureur2.start();

			btnDemarrer.setEnabled( false );

		} else if ( e.getSource() == btnArreter ) {

			// changer la condition pour empêcher les coureurs d'avancer
			coureur1.setAttente( true );
			coureur2.setAttente( true );
			
		} else if ( e.getSource() == btnContinuer ) {

			// changer la condition pour faire avancer les 2 coureurs
			coureur1.setAttente( false );
			coureur2.setAttente( false );
		}
	}
}
