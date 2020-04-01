package com.laroche.git.pdl;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UILogin extends JFrame implements ActionListener{
	
	private JTextField JTEmail;
	private JPasswordField JPMDP;
	private JButton BTValider;
	private JLabel JLErreur;

	public UILogin() {
		this.setTitle("login");
		this.setResizable(false);
		this.setBounds(100, 100, 450, 219);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new JLabel(new ImageIcon("drawables/softBack0.jpg")));
	    this.setLocationRelativeTo(null);
		
		JLabel lblAuthentification = new JLabel("Authentification");
		lblAuthentification.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAuthentification.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthentification.setBounds(6, 18, 438, 16);
		this.getContentPane().add(lblAuthentification);
		
		JLabel lblEmail = new JLabel("email :");
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(131, 62, 61, 16);
		this.getContentPane().add(lblEmail);
		
		JLabel lblMotsDePasse = new JLabel("Mot de passe :");
		lblMotsDePasse.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblMotsDePasse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotsDePasse.setBounds(66, 100, 126, 16);
		this.getContentPane().add(lblMotsDePasse);
		
		JTEmail = new JTextField();
		JTEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		JTEmail.setBounds(205, 58, 202, 26);
		this.getContentPane().add(JTEmail);
		JTEmail.setColumns(10);
		
		JPMDP = new JPasswordField();
		JPMDP.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		JPMDP.setBounds(204, 96, 203, 26);
		this.getContentPane().add(JPMDP);
		JPMDP.addActionListener(this);
		
		JLErreur = new JLabel("");
		JLErreur.setForeground(Color.RED);
		JLErreur.setHorizontalAlignment(SwingConstants.CENTER);
		JLErreur.setBounds(6, 134, 438, 16);
		this.getContentPane().add(JLErreur);
		
		BTValider = new JButton("Valider");
		BTValider.setBounds(181, 145, 87, 29);
		this.getContentPane().add(BTValider);
		BTValider.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public String getEmail() {
		return JTEmail.getText();
	}
	
	public String getPassword() {
		return JPMDP.getText();
	}
	
	public void clearErreur() {
		JLErreur.setText("");
	}
	
	public void addErreur(String erreur) {
		JLErreur.setText(erreur);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == BTValider) {
			BDD db = null;
			try {
				db = new BDD();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(!db.isConnected() || db == null) {
				addErreur("Echec de connection à la BDD !");
			}else {
				ArrayList<ArrayList<String>> result = db.request("SELECT * FROM utilisateur WHERE email_user = '"+getEmail()+"'", 6);
				
				if(result.size() != 0) {
					String dbPasswd = result.get(0).get(2);
					if(dbPasswd.equals(getPassword())) {
						//utilisateur auth au pres de la bdd
						String statut = result.get(0).get(3);
						statut.toLowerCase();
						System.out.println(statut);
						switch(statut) {
						case "gestionnaire":
							
							break;
						case "etudiant":
							this.setVisible(false);
							this.dispose();
							new UIEtudiant(Integer.parseInt(result.get(0).get(0)));
							//result.get(0).get(4),result.get(0).get(5)
							break;
						case "enseignant":
							
							break;
						default:
						    JOptionPane.showMessageDialog(null, "Statut inexistant !\nFermeture de l'application", "Erreur", JOptionPane.ERROR_MESSAGE);
						    this.setVisible(false);
						    this.dispose();
						    System.exit(0);
							break;
						}
						
					}else {
						addErreur("Mots de passe incorrect");
						JPMDP.setText("");
					}
				}else {
					addErreur("Email invalide");
				}
			}
		}
		
		if(e.getSource() == JPMDP) {
			BTValider.doClick();
		}
	}
	
}

