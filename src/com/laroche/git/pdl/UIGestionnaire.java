package com.laroche.git.pdl;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//import java.beans.Statement;
//import javax.sql.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

//import com.sun.jdi.connect.spi.Connection;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.rowset.serial.SerialBlob;



public class UIGestionnaire extends JFrame implements ActionListener{
	
	private int LARGEUR_FENETRE = 450;
	private int HAUTEUR_FENETRE = 300;
	
	private int userID;
	private JButton JBGerer,JBAbsences,JBGroupes,JBDeconnexion;
	private JPanel content,JPGerer,JPAbsences, JPGroupes;
	private String[] listeCard = {"card_gerer", "card_absences", "card_groupes"};
	private CardLayout cardLayoutCentre = new CardLayout();
	
	//On d�finit les JRadioButton pour "g�rer"
	private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton rdoEtudiant = new JRadioButton("Etudiant(s)");
    private JRadioButton rdoEnseignant = new JRadioButton("Enseignant(s)");
    private JRadioButton rdoGroupe = new JRadioButton("Groupe(s)");
    private JRadioButton rdoCours = new JRadioButton("Cours");
	
    private JButton JBAjout, JBModif, JBSupp;
    
    //Pour tableau du milieu
    private DefaultTableModel valeursTableau;
    String buttonSelect="etudiant";
    
    
	public UIGestionnaire(int userID) {
		this.userID = userID;
		this.setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setLocationRelativeTo(null);
		System.out.println(userID);
		
	    JBAjout = new JButton("Ajouter");
	    JBAjout.addActionListener(this);
	    JBModif = new JButton("Modifier");
	    JBModif.addActionListener(this);
	    JBSupp = new JButton("Supprimer");
	    JBSupp.addActionListener(this);
		
		//On definit chaque JPanel : Gerer, Absences et Groupes
		content = new JPanel();
		JPGerer = new JPanel();
		JPAbsences = new JPanel();
		JPGroupes = new JPanel();
		
		
		//G�rer********************************************************************************************
		
		//Ic�ne du gestionnaire#######################################################################
		JLabel lblIcone = new JLabel(new ImageIcon("drawables/GestionnaireIcon.png"));
		lblIcone.setHorizontalAlignment(SwingConstants.LEADING);
		
		
		//Cr�ation des diff�rentes cellules############################################################
		//On cr�e nos diff�rents conteneurs de couleur diff�rente
	    JPanel cellIcon = new JPanel();
	    cellIcon.setBackground(Color.YELLOW);
	    cellIcon.add(lblIcone);
	    cellIcon.setPreferredSize(new Dimension(80/5, 50/5));
	    
	    JPanel cellList = new JPanel();
	    cellList.setBackground(Color.black);
	    cellList.setPreferredSize(new Dimension(370/5, 280/5));
	    
	    JPanel cellChoix = new JPanel();
	    cellChoix.setBackground(Color.green);
	    cellChoix.setPreferredSize(new Dimension(80/5, 130/5));
	    
	    JPanel cellAction = new JPanel();
	    cellAction.setBackground(Color.cyan);
	    cellAction.setPreferredSize(new Dimension(450/5, 20/5));
	    
	    JPGerer.setLayout(new GridBagLayout());
	    
	    //L'objet servant � positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
			
	    //On positionne la case de d�part du composant
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    //La taille en hauteur et en largeur
	    gbc.gridheight = 2;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH;
	    
	    gbc.weightx = 0.3;
	    //gbc.weighty = 2/6;
	    gbc.weighty =0.3;
	    
	    JPGerer.add(cellIcon, gbc);
	    //---------------------------------------------
	    gbc.gridx = 2;
	    gbc.gridy = 0;
	    gbc.gridheight = 5;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.fill = GridBagConstraints.BOTH;
	    
	    gbc.weightx = 0.7;
	    gbc.weighty = 0.7;
	    
	    JPGerer.add(cellList, gbc);
	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridheight = 3;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH;
	    
	    gbc.weightx = 0.3;
	    gbc.weighty = 0.4;
	    
	    JPGerer.add(cellChoix, gbc);		

	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 5;
	    gbc.gridheight = 1;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.fill = GridBagConstraints.BOTH;
	    
	    gbc.weightx = 1;
	    gbc.weighty = 0.3;
	    
	    JPGerer.add(cellAction, gbc);
	    //---------------------------------------------

		JPAbsences.setBackground(Color.red);
		JPGroupes.setBackground(Color.pink);
		
		JPanel panel = new BackgroundPanel();
		JBGerer = new JButton("Gerer");
		JBAbsences = new JButton("Absences");
		JBGroupes = new JButton("Groupes");
		JBDeconnexion = new JButton("D�connexion");

		JBGerer.addActionListener(this);
		JBAbsences.addActionListener(this);
		JBGroupes.addActionListener(this);
		JBDeconnexion.addActionListener(this);
		
		panel.add(JBGerer);
		panel.add(JBAbsences);
		panel.add(JBGroupes);
		panel.add(JBDeconnexion);
		
		content.setLayout(cardLayoutCentre);
		content.add(JPGerer,listeCard[0]);
		content.add(JPAbsences,listeCard[1]);
		content.add(JPGroupes,listeCard[2]);
		
		
		//Cellule Choix################################################################
		rdoEtudiant.setSelected(true);
		rdoEtudiant.addActionListener(this);
		cellChoix.add(rdoEtudiant);
        
        cellChoix.add(rdoEnseignant);
        rdoEnseignant.addActionListener(this);
        
        cellChoix.add(rdoGroupe);
        rdoGroupe.addActionListener(this);
        
        cellChoix.add(rdoCours);
        rdoCours.addActionListener(this);
        
        buttonGroup.add(rdoEtudiant);
        buttonGroup.add(rdoEnseignant);
        buttonGroup.add(rdoGroupe);
        buttonGroup.add(rdoCours);
        
        
        
        //Cellule Liste############################################################################

    	String[] columnNames = {"Nom","Pr�nom", "Email", "MDP", "Statut"};
    	String[][] valeurs = new String[5][0];
    	valeursTableau = new DefaultTableModel(valeurs, columnNames);
    	
        JTable listeUtilisateur;

        listeUtilisateur = new JTable(valeursTableau);
        refreshUtilisateur();
               
      	cellList.add(new JScrollPane(listeUtilisateur));
      	
      	//Cellule Action############################################################################
      	
      	//1 ligne sur 3 colonnes
        cellAction.setLayout(new GridLayout(1, 3));
        //On ajoute le bouton au content pane de la JFrame
        
        cellAction.add(JBAjout);
        cellAction.add(JBModif);
        cellAction.add(JBSupp);
       
		
		//***************************************************************************************************************

		
		this.getContentPane().add(panel, BorderLayout.NORTH);
		this.getContentPane().add(content,BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void refreshUtilisateur() {
		
		AuthentificationDAO authDao = new AuthentificationDAO();
		ArrayList<Personne> listPersonne = authDao.getListPersonne();
		ArrayList<Authentification> listAuth = authDao.getListAuth();
		
		
		//Efface les anciennes valeurs
		while(valeursTableau.getRowCount()>0) {
			valeursTableau.removeRow(valeursTableau.getRowCount()-1);
		}
		
		for(int i=0; i<listAuth.size(); i++) {
				String nom = listPersonne.get(i).getNom();
				String prenom = listPersonne.get(i).getPrenom();
				String email = listAuth.get(i).getEmail();
				String mdp = listAuth.get(i).getMDP();
				String statut = listAuth.get(i).getStatut();
				
				String[] valeurs = {nom, prenom, email, mdp, statut};

				if (buttonSelect.equals(listAuth.get(i).getStatut()))
					valeursTableau.addRow(valeurs);
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == JBGerer) {
			cardLayoutCentre.show(content, listeCard[0]);

		}
		
		if(e.getSource() == JBAbsences) {
			cardLayoutCentre.show(content,listeCard[1]);
		}
		
		if(e.getSource() == JBGroupes) {
			cardLayoutCentre.show(content,listeCard[2]);
		}
		
		if(e.getSource() == rdoEtudiant) {
			buttonSelect = "etudiant";
        	refreshUtilisateur();
		}
		
		if(e.getSource() == rdoEnseignant) {
			buttonSelect = "enseignant";
        	refreshUtilisateur();
		}
		
		if(e.getSource() == rdoGroupe) {
			
		}
		
		if(e.getSource() == rdoCours) {
			
		}
		
		if(e.getSource() == JBAjout) {
			this.setVisible(false);
			new UIAjoutEtudiant(this);
			
		}
		
		if(e.getSource() == JBModif) {
			
		}
		
		if(e.getSource() == JBSupp) {
			
			//new suppEtudiantDAO.deteleteEtudiant();
			
		}
		
		if(e.getSource() == JBDeconnexion) {
			System.out.println("D�connexion");
			this.setVisible(false);
			this.dispose();
			new UILogin();
		}
	}

}