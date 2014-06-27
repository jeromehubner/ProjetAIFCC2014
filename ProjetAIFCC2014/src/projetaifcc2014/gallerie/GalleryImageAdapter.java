package projetaifcc2014.gallerie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.projetaifcc2014.R;

public class GalleryImageAdapter extends BaseAdapter 
{
    private Context mContext;

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

    public GalleryImageAdapter(Context context) 
    {
        mContext = context;
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }


    // Override this method according to your need
    public View getView(int index, View view, ViewGroup viewGroup) 
    {
        ImageView i = new ImageView(mContext);

        i.setImageResource(mImageIds[index]);
        i.setLayoutParams(new Gallery.LayoutParams(100, 100));
        i.setScaleType(ImageView.ScaleType.FIT_XY);

        return i;
    }
}