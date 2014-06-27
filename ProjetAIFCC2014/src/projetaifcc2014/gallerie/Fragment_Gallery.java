package projetaifcc2014.gallerie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projetaifcc2014.R;

public class Fragment_Gallery extends Fragment
{
	ImageView selectedImage;  
	private Integer[] mImageIds = {
			R.drawable.gallery_01,
			R.drawable.gallery_02,
			R.drawable.gallery_03,
			R.drawable.gallery_04,
			R.drawable.gallery_05,
			R.drawable.gallery_06,
			R.drawable.gallery_07,
			R.drawable.gallery_08,
			R.drawable.gallery_09,
			R.drawable.gallery_10,
			R.drawable.gallery_11,
			R.drawable.gallery_12,
			R.drawable.gallery_13,
			R.drawable.gallery_14,
			R.drawable.gallery_15,
			R.drawable.gallery_16,
			R.drawable.gallery_17,
			R.drawable.gallery_18,
			R.drawable.gallery_19,
			R.drawable.gallery_20,
			R.drawable.gallery_21,
			R.drawable.gallery_22,
			R.drawable.gallery_23,
			R.drawable.gallery_24,
			R.drawable.gallery_25,
			R.drawable.gallery_26,
			R.drawable.gallery_27,
			R.drawable.gallery_28,
			R.drawable.gallery_29,
			R.drawable.gallery_30,
			R.drawable.gallery_31,
			R.drawable.gallery_32,
			R.drawable.gallery_33,
			R.drawable.gallery_34,
			R.drawable.gallery_35,
			R.drawable.gallery_36,
			R.drawable.gallery_37,
			R.drawable.gallery_38,
			R.drawable.gallery_39
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.gallery,container, false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		Gallery gallery = (Gallery) view.findViewById(R.id.gallery1);
		selectedImage=(ImageView)view.findViewById(R.id.imageView1);
		gallery.setSpacing(1);
		gallery.setAdapter(new GalleryImageAdapter(this.getActivity()));

		// clicklistener for Gallery
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				// show the selected Image
				selectedImage.setImageResource(mImageIds[position]);
			}
		});
	}
}
