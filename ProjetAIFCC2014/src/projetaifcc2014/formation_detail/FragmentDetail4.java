package projetaifcc2014.formation_detail;

import projetaifcc2014.database.formation.Formation;
import projetaifcc2014.database.formation.FormationBdd;
import projetaifcc2014.drawer.Activity_drawer;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projetaifcc2014.R;

public class FragmentDetail4 extends FragmentDetail{
	
	Button btnEnvoi;
	EditText mailSujet;
	EditText mailMessage;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_formation_detail_4, container, false);
		
		FormationBdd formationBdd = new FormationBdd(getActivity());

		formationBdd.open();			
		Formation formation = formationBdd.getFormationById(((Activity_drawer) getActivity()).getIdFormation());
		formationBdd.close();
		
		couleurDepartement = getHTMLCouleurDepartement(formation);
		emailFormation = formation.getEmail();

		Integer couleur = Color.parseColor(couleurDepartement);
		TextView textView = (TextView) view.findViewById(R.id.mail_sujet);
		textView.setTextColor(couleur);
		textView = (TextView) view.findViewById(R.id.mail_message);
		textView.setTextColor(couleur);
		
		btnEnvoi = (Button) view.findViewById(R.id.btn_envoi);
		mailSujet = (EditText) view.findViewById(R.id.mail_sujet_edit);
		mailMessage = (EditText) view.findViewById(R.id.mail_message_edit);

		if(emailFormation != null && !emailFormation.equals("")){
			btnEnvoi.setOnClickListener(new OnClickListener() {
	
				@Override
				public void onClick(View v) {
					
					String subject = mailSujet.getText().toString();
					String message = mailMessage.getText().toString();
	
					Intent email = new Intent(Intent.ACTION_SEND);
					email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailFormation});
					email.putExtra(Intent.EXTRA_SUBJECT, subject);
					email.putExtra(Intent.EXTRA_TEXT, message);
					email.setType("message/rfc822");
	
					startActivity(Intent.createChooser(email, "Choisir un client Email :"));
				}
			});
		} else {
			btnEnvoi.setEnabled(false);
			btnEnvoi.setText("Auncun contact");
		}

		return view;
	}
}

