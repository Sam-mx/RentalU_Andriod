package com.example.rentalu.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.rentalu.R;

public class OnBoardingAdapter extends PagerAdapter {

    Context context ;
    LayoutInflater layoutInflater;

    public OnBoardingAdapter(Context context)
    {
        this.context = context;
    }

    int titles[] = {
            R.string.title1,
            R.string.title2,
            R.string.title3
    };

    int subtitles[] = {
            R.string.subtitle1,
            R.string.subtitle2,
            R.string.subtitle3,
    };

    int images[] = {
            R.mipmap.intro2_foreground,
            R.mipmap.intro_foreground,
            R.mipmap.intro3_foreground
    };

    int bg[] = {
            R.drawable.bg3,
            R.drawable.bg2,
            R.drawable.bg1
    };
    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.slide,container,false);

        ImageView image = v.findViewById(R.id.slideImg);
        TextView title = v.findViewById(R.id.sliderTitle);
        TextView subtitle = v.findViewById(R.id.sliderSubtitle);
        ConstraintLayout layout= v.findViewById(R.id.sliderLayout);

        image.setImageResource(images[position]);
        title.setText(titles[position]);
        subtitle.setText(subtitles[position]);
        layout.setBackgroundResource(bg[position]);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
