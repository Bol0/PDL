package com.laroche.git.pdl;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UIAjoutEtudiant extends JFrame implements ActionListener{
	
	private int LARGEUR_FENETRE = 450;
	private int HAUTEUR_FENETRE = 450;
	
	private JButton JBValider;
	private JTextField JTEmail, JTMdp, JTNom, JTPrenom;
	private JLabel JLErreur;
	private JComboBox filiereList, groupeList;
	
	private UIGestionnaire parent;
	
	//Constructeur
	public UIAjoutEtudiant(UIGestionnaire parent) {
		this.parent = parent;
		
		this.setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setLocationRelativeTo(null);
		
		JLErreur = new JLabel("");
		this.getContentPane().add(JLErreur,BorderLayout.NORTH);
	
		JBValider = new JButton("Valider");
		this.getContentPane().add(JBValider,BorderLayout.SOUTH);
		JBValider.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6,2));
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblEmail);
		
		JTEmail = new JTextField();
		JTEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(JTEmail);
		JTEmail.setColumns(10);
		
		JLabel lblMotsDePasse = new JLabel("Mot de passe :");
		lblMotsDePasse.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblMotsDePasse.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblMotsDePasse);
		
		JTMdp = new JTextField();
		JTMdp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(JTMdp);
		JTMdp.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNom);
		
		JTNom = new JTextField();
		JTNom.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(JTNom);
		JTNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblPrenom);
		
		JTPrenom = new JTextField();
		JTPrenom.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(JTPrenom);
		JTPrenom.setColumns(10);
		
		JLabel lblFiliere = new JLabel("Filiere :");
		lblFiliere.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblFiliere.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblFiliere);
		
		String[] filiere = { "Classique", "Alternant" };
		filiereList = new JComboBox(filiere);
		filiereList.setSelectedIndex(1);
		filiereList.addActionListener(this);
		panel.add(filiereList);
		
		JLabel lblGroupe = new JLabel("Groupe :");
		lblGroupe.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblGroupe.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblGroupe);
		
		ArrayList<Groupe> listeGroupe = new listeGroupeDAO().getList();
		String[] groupe = new String[listeGroupe.size()];
		
		for(int i = 0;i < listeGroupe.size();i++) {
			groupe[i] = Integer.toString(listeGroupe.get(i).getNumero());
		}
		
		groupeList = new JComboBox(groupe);
		groupeList.setSelectedIndex(2);
		groupeList.addActionListener(this);
		panel.add(groupeList);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == JBValider) {
			if ((!JTEmail.getText().equals("")) && (!JTMdp.getText().equals("")) && !JTNom.getText().equals("") && !JTPrenom.getText().equals("")) {
				Etudiant etudiant;
				Authentification auth;
				String nom = JTNom.getText();
				String prenom = JTPrenom.getText();
				boolean filiere = (filiereList.getSelectedIndex() == 0);
				int groupe = Integer.parseInt(groupeList.getSelectedItem().toString());
				String email = JTEmail.getText() ;
				String mdp = JTMdp.getText();
				etudiant = new Etudiant(prenom, nom, filiere, groupe);
				auth = new Authentification(email,mdp,"etudiant");
				
				ajoutEtudiantDAO db = new ajoutEtudiantDAO(etudiant,auth);
				
				if(!db.isEmailExisting(email)) {
					db.updateEtudiant();
					this.setVisible(false);
					this.dispose();
					parent.setVisible(true);
					parent.refreshUtilisateur();
				}else {
					JLErreur.setText("L'email existe déjà !!");
				}
			
				
			}else {
				JLErreur.setText("Champ(s) nul(s) !!");
			}
				
		}
		
	}


	
}