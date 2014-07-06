package projetaifcc2014.drawerFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projetaifcc2014.R;

public class FragmentContact extends Fragment {

	private ImageButton envoieMail ;
	private Spinner spinnerStatus, spinnerExistence ;
	
	private EditText txtNom, txtPrenom, txtSociete, txtAdresse,txtCodePostale, 
					  txtVille, txtTelephone, txtFax,txtEmail,txtMessage;
	private RadioGroup goupeCentre ;
	private String nom, prenom, societe, status, adresse,codePostale, 
					ville, telephone, fax,email, existence, checkBox1,
					checkBox2, checkBox3, checkBox4, checkBox5, checkBox6,
					checkBox7,messageContact,centre;
	
	private CheckBox chBox1,chBox2,chBox3,chBox4,chBox5,chBox6,chBox7;
	
    public FragmentContact(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    	// Le drawer n'est utilisable que pour le screen_formation_detail
        View vue = inflater.inflate(R.layout.fragment_contact, container, false);
        
        envoieMail = (ImageButton)vue.findViewById(R.id.bouton_mail);
        spinnerStatus = (Spinner) vue.findViewById(R.id.spinner_status);
        spinnerExistence = (Spinner) vue.findViewById(R.id.spinner_existence);
        txtNom = (EditText) vue.findViewById(R.id.nom_contact);
        txtNom.setTextColor(Color.BLUE);
        txtPrenom = (EditText) vue.findViewById(R.id.prenom_contact);
        txtSociete = (EditText) vue.findViewById(R.id.societe_contact);
        txtAdresse = (EditText) vue.findViewById(R.id.adresse_contact);
        txtCodePostale = (EditText) vue.findViewById(R.id.cp_contact);
        txtVille = (EditText) vue.findViewById(R.id.ville_contact);
        txtTelephone = (EditText) vue.findViewById(R.id.tel_contact);
        txtFax = (EditText) vue.findViewById(R.id.fax_contact);
        txtEmail = (EditText) vue.findViewById(R.id.mail_contact);
        txtMessage = (EditText) vue.findViewById(R.id.message_contact);
        goupeCentre = (RadioGroup) vue.findViewById(R.id.radioGroup_centre);
        
        
        chBox1 = (CheckBox) vue.findViewById(R.id.checkBox1);
        chBox2 = (CheckBox) vue.findViewById(R.id.checkBox2);
        chBox3 = (CheckBox) vue.findViewById(R.id.checkBox3);
        chBox4 = (CheckBox) vue.findViewById(R.id.checkBox4);
        chBox5 = (CheckBox) vue.findViewById(R.id.checkBox5);
        chBox6 = (CheckBox) vue.findViewById(R.id.checkBox6);
        chBox7 = (CheckBox) vue.findViewById(R.id.checkBox7);
        
        
        // Adapter pour spinner Status
        ArrayAdapter<?> adapterStatus = ArrayAdapter.createFromResource(vue.getContext(),  R.array.spinner_status, android.R.layout.simple_spinner_dropdown_item);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapterStatus);
        
        // Adapter pour spinner Existence
        ArrayAdapter<?> adapterExistence = ArrayAdapter.createFromResource(vue.getContext(),  R.array.spinner_existence, android.R.layout.simple_spinner_dropdown_item);
        adapterExistence.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExistence.setAdapter(adapterExistence);

        return vue;
    }

    
    
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
        spinnerStatus.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) 
			{
		        status = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
        
        spinnerExistence.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) 
			{
		        existence = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
        
        envoieMail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String[] TO = {"institut.alternance@aifcc.com"};
				nom = txtNom.getText().toString();
				prenom = txtPrenom.getText().toString();
				societe = txtSociete.getText().toString();
				adresse = txtAdresse.getText().toString();
				codePostale = txtCodePostale.getText().toString();
				ville = txtVille.getText().toString();
				telephone = txtTelephone.getText().toString();
				fax = txtFax.getText().toString();
				email = txtEmail.getText().toString();				
				messageContact = txtMessage.getText().toString();
				
		        // get selected radio button from radioGroup
				int selectedId = goupeCentre.getCheckedRadioButtonId();
 
				if (selectedId == R.id.radio_caen) centre = "Caen";
				else if (selectedId == R.id.radio_lisieux) centre = "Lisieux";
				
				
				if(chBox1.isChecked())checkBox1 = chBox1.getText().toString();
				else checkBox1 = null ;
				if(chBox2.isChecked())checkBox2 = chBox2.getText().toString();
				else checkBox2 = null ;
				if(chBox3.isChecked())checkBox3 = chBox3.getText().toString();
				else checkBox3 = null ;
				if(chBox4.isChecked())checkBox4 = chBox4.getText().toString();
				else checkBox4 = null ;
				if(chBox5.isChecked())checkBox5 = chBox5.getText().toString();
				else checkBox5 = null ;
				if(chBox6.isChecked())checkBox6 = chBox6.getText().toString();
				else checkBox6 = null ;
				if(chBox7.isChecked())checkBox7 = chBox7.getText().toString();
				else checkBox7 = null ;
				
				 Toast.makeText(v.getContext(), "Envoi du mail", Toast.LENGTH_SHORT).show();
				 
				 Intent intent = new Intent(Intent.ACTION_SEND);
				 intent.setType("text/html");
				 intent.putExtra(Intent.EXTRA_EMAIL,TO);
				 intent.putExtra(Intent.EXTRA_SUBJECT, "DEMANDES DE RENSEIGNEMENTS");
				 intent.putExtra(Intent.EXTRA_TEXT,construitMail());

				 intent.setType("message/rfc822");
				 
				 startActivity(Intent.createChooser(intent, "Quelle messagerie ? "));
			}
		});
	}
    
    private String construitMail(){
    	String message = null ;
    	
    	message = "Nom : " + nom +"\n";
    	message = message + "Prénom : " + prenom +"\n";
    	if(societe != null){
    	message = message + "Societé : " + societe +"\n";
    	}
    	message = message + "Status : " + status +"\n";
    	if(adresse != null){
    	message = message + "Adresse : " + adresse +"\n";
    	}
    	if(codePostale != null){
    	message = message + "Ville : " + codePostale +"  "+ville+"\n";
    	}
    	if(telephone != null){
    	message = message + "Téléphone : " + telephone +"\n";
    	}
    	if(fax != null){
    	message = message + "Fax : " + fax +"\n";
    	}
    	message = message + "Adresse mail : " + email +"\n\n";
    	if(existence != null || existence.equals("")){
    	message = message + "Comment avez-vous eu connaissance de l'existence de l'AIFCC ? : " + existence +"\n\n\n";
    	}
    	message = message + "Pour le centre de : " + centre +"\n";
    	if(checkBox1 != null){
    	message = message + checkBox1 +"\n";
    	}
    	if(checkBox2 != null){
    	message = message + checkBox2 +"\n";
    	}
    	if(checkBox3 != null){
    	message = message + checkBox3 +"\n";
    	}
    	if(checkBox4 != null){
    	message = message + checkBox4 +"\n";
    	}
    	if(checkBox5 != null){
    	message = message + checkBox5 +"\n";
    	}
    	if(checkBox6 != null){
    	message = message + checkBox6 +"\n";
    	}
    	if(checkBox7 != null){
    	message = message + checkBox7 +"\n";
    	}
    	if(messageContact != null){
        	message = message + "Message : \n" + messageContact ;
        }
    	
    	return message ;
    }
}