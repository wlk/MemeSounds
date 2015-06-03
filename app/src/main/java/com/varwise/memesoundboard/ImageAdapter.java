package com.varwise.memesoundboard;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
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
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int imageSize = Math.min((size.x - 36)/ 3, 200);

        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(imageSize, imageSize));
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
            R.drawable.badum, R.drawable.bazinga,
            R.drawable.bye, R.drawable.challengeaccepted,
            R.drawable.dramatic, R.drawable.fart,
            R.drawable.fuckoff, R.drawable.fusrodah,
            R.drawable.gay, R.drawable.headshot,
            R.drawable.heya, R.drawable.hodor,
            R.drawable.idontwant, R.drawable.killme,
            R.drawable.nooo, R.drawable.nope,
            R.drawable.nyancat, R.drawable.petergriffin,
            R.drawable.police, R.drawable.saxguy,
            R.drawable.shit, R.drawable.silenceikillyou, R.drawable.syrena, R.drawable.takemymoney, R.drawable.thatswhatshesaid,
            R.drawable.thisissparta, R.drawable.trololo, R.drawable.whatdoesthefoxsay, R.drawable.whatthefuck, R.drawable.whatwhatwhat,
            R.drawable.youshallnotpass
    };
}