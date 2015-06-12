package com.varwise.trollmemesoundboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new SquareImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1, 1, 1, 1);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.badum,
            R.drawable.gay,
            R.drawable.nyancat,
            R.drawable.police,
            R.drawable.siren,
            R.drawable.headshot,
            R.drawable.challengeaccepted,
            R.drawable.fart,
            R.drawable.whatthefuck,
            R.drawable.dramatic,
            R.drawable.nope,
            R.drawable.killme,
            R.drawable.saxguy,
            R.drawable.silenceikillyou,
            R.drawable.nooo,
            R.drawable.shit,
            R.drawable.trololo,
            R.drawable.bazinga,
            R.drawable.bye,
            R.drawable.fusrodah,
            R.drawable.heya,
            R.drawable.hodor,
            R.drawable.idontwant,
            R.drawable.petergriffin,
            R.drawable.takemymoney,
            R.drawable.thatswhatshesaid,
            R.drawable.thisissparta,
            R.drawable.whatwhatwhat,
            R.drawable.youshallnotpass,
            R.drawable.fuckoff
    };
}