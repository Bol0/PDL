package com.laroche.git.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;

import com.laroche.git.DAO.ajoutEnseignantDAO;
import com.laroche.git.DAO.ajoutEtudiantDAO;
import com.laroche.git.DAO.listeGroupeDAO;
import com.laroche.git.objets.Authentification;
import com.laroche.git.objets.Enseignant;
import com.laroche.git.objets.Etudiant;
import com.laroche.git.objets.Groupe;

public class UIAjoutEnseignant extends JFrame implements ActionListener{
	
	private int LARGEUR_FENETRE = 450;
	private int HAUTEUR_FENETRE = 450;
	
	private JButton JBValider;
	private JTextField JTEmail, JTMdp, JTNom, JTPrenom;
	private JSpinner JSTelephone;
	private JLabel JLErreur;
	
	private UIGestionnaire parent;
	
	//Constructeur
	public UIAjoutEnseignant(UIGestionnaire parent) {
		this.parent = parent;
		
		this.setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setLocationRelativeTo(null);
		
		//listener pour fermer la fenetre et re afficher la fenetre principale du gestionnaire
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
		        System.out.println("Abandon de l'ajout de l'enseignant");
		        setVisible(false);
		        dispose();
		        parent.setVisible(true);
		    }
		});
		
		JLErreur = new JLabel("");
		this.getContentPane().add(JLErreur,BorderLayout.NORTH);
		JLErreur.setForeground(Color.RED);
		JLErreur.setHorizontalAlignment(SwingConstants.CENTER);
	
		JBValider = new JButton("Valider");
		this.getContentPane().add(JBValider,BorderLayout.SOUTH);
		JBValider.addActionListener(this);
		
		JPanel panel = new BackgroundPanel();
		panel.setLayout(new GridLayout(6,2));
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblEmail);
		
		JTEmail = new JTextField();
		JTEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(JTEmail);
		JTEmail.setColumns(10);
		
		JLabel lblMotsDePasse = new JLabel("Mot de passe :");
		lblMotsDePasse.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblMotsDePasse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotsDePasse.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMotsDePasse);
		
		JTMdp = new JTextField();
		JTMdp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(JTMdp);
		JTMdp.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNom);
		
		JTNom = new JTextField();
		JTNom.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(JTNom);
		JTNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrenom.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPrenom);
		
		JTPrenom = new JTextField();
		JTPrenom.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(JTPrenom);
		JTPrenom.setColumns(10);
		
		JLabel lblTelephone = new JLabel("Telephone :");
		lblTelephone.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblTelephone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelephone.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTelephone);
		
		JSTelephone = new JSpinner();
		JSTelephone.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(JSTelephone);
		
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == JBValider) {
			if ((!JTEmail.getText().equals("")) && (!JTMdp.getText().equals("")) && !JTNom.getText().equals("") && !JTPrenom.getText().equals("") && !JSTelephone.getValue().equals(null)) {
				Enseignant enseignant;
				Authentification auth;
				String nom = JTNom.getText();
				String prenom = JTPrenom.getText();
				int telephone = (int) JSTelephone.getValue();
				String email = JTEmail.getText() ;
				String mdp = JTMdp.getText();
				enseignant = new Enseignant(nom, prenom, telephone);
				auth = new Authentification(email,mdp,"enseignant");
				
				ajoutEnseignantDAO db = new ajoutEnseignantDAO(enseignant,auth);
				
				if(!db.isEmailExisting(email)) {
					db.updateEnseignant();
					this.setVisible(false);
					this.dispose();
					parent.setVisible(true);
					parent.refreshUtilisateur();
				}else {
					JLErreur.setText("L'email existe deja  !!");
				}
			
				
			}else {
				JLErreur.setText("Champ(s) nul(s) !!");
			}
				
		}
		
	}


	
}