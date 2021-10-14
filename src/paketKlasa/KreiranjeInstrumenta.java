package paketKlasa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import paketInstrument.Bubnjevi;
import paketInstrument.Gitara;
import paketInstrument.Klavir;
import paketInstrument.Violina;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class KreiranjeInstrumenta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtModel;
	private JTextField txtProizvodjac;
	private JTextField txtMaterijal;
	private JTextField txtID;
	private JTextField txtKolicina;
	private JTextField txtCena;

	public void cistac() {
		txtProizvodjac.setText("");
		txtModel.setText("");
		txtMaterijal.setText("");
		txtID.setText("");
		txtKolicina.setText("");
		txtCena.setText("");
	}

	public KreiranjeInstrumenta() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Runtime.getRuntime().exit(1);
			}
		});
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setLocationRelativeTo(null);

		txtModel = new JTextField();
		txtModel.setColumns(10);
		txtModel.setBounds(110, 70, 120, 25);
		contentPanel.add(txtModel);

		txtProizvodjac = new JTextField();
		txtProizvodjac.setColumns(10);
		txtProizvodjac.setBounds(110, 105, 120, 25);
		contentPanel.add(txtProizvodjac);

		txtMaterijal = new JTextField();
		txtMaterijal.setColumns(10);
		txtMaterijal.setBounds(110, 140, 120, 25);
		contentPanel.add(txtMaterijal);

		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(110, 175, 120, 25);
		contentPanel.add(txtID);

		txtKolicina = new JTextField();
		txtKolicina.setColumns(10);
		txtKolicina.setBounds(110, 210, 120, 25);
		contentPanel.add(txtKolicina);

		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(10, 70, 90, 25);
		contentPanel.add(lblModel);

		String[] instrumenti = { "gitara", "bubnjevi", "violina", "klavir" };
		JComboBox<String> cmbBoxInstrumenti = new JComboBox<String>(instrumenti);
		cmbBoxInstrumenti.setBounds(110, 35, 120, 25);
		contentPanel.add(cmbBoxInstrumenti);

		JLabel lblNaziv = new JLabel("Naziv instrumenta");
		lblNaziv.setBounds(10, 35, 90, 25);
		contentPanel.add(lblNaziv);

		JLabel lblProizvodjac = new JLabel("Proizvodjac");
		lblProizvodjac.setBounds(10, 105, 90, 25);
		contentPanel.add(lblProizvodjac);

		JLabel lblMaterijal = new JLabel("Materijal");
		lblMaterijal.setBounds(10, 140, 90, 25);
		contentPanel.add(lblMaterijal);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 175, 90, 25);
		contentPanel.add(lblId);

		JLabel lblKolicina = new JLabel("Kolicina");
		lblKolicina.setBounds(10, 210, 90, 25);
		contentPanel.add(lblKolicina);

		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(10, 245, 90, 25);
		contentPanel.add(lblCena);

		txtCena = new JTextField();
		txtCena.setColumns(10);
		txtCena.setBounds(110, 245, 120, 25);
		contentPanel.add(txtCena);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Unos");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (txtProizvodjac.getText().equals("") || txtModel.getText().equals("")
								|| txtMaterijal.getText().equals("") || txtID.getText().equals("")
								|| txtKolicina.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Unesite sve podatke!");

						} else {
							try {
								int kolicina = Integer.parseInt(txtKolicina.getText());
								int cena = Integer.parseInt(txtCena.getText());
								if (cmbBoxInstrumenti.getSelectedItem().equals("gitara")) {
									Gitara g = new Gitara(txtProizvodjac.getText(), txtModel.getText(),
											txtMaterijal.getText(), txtID.getText(), kolicina, cena);

									g.kreiraj();

								}
								if (cmbBoxInstrumenti.getSelectedItem().toString() == "bubnjevi") {
									Bubnjevi b = new Bubnjevi(txtProizvodjac.getText(), txtModel.getText(),
											txtMaterijal.getText(), txtID.getText(), kolicina, cena);
									b.kreiraj();
								}
								if (cmbBoxInstrumenti.getSelectedItem().toString() == "violina") {
									Violina v = new Violina(txtProizvodjac.getText(), txtModel.getText(),
											txtMaterijal.getText(), txtID.getText(), kolicina, cena);
									v.kreiraj();
								}
								if (cmbBoxInstrumenti.getSelectedItem().toString() == "klavir") {
									Klavir k = new Klavir(txtProizvodjac.getText(), txtModel.getText(),
											txtMaterijal.getText(), txtID.getText(), kolicina, cena);
									k.kreiraj();
								}
							} catch (Exception ex) {
								System.out.println(ex);
							}

						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Nazad");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						MainMenu m = new MainMenu();
						m.setVisible(true);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
