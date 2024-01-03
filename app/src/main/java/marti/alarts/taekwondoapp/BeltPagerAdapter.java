package marti.alarts.taekwondoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class BeltPagerAdapter extends PagerAdapter {

    private List<Belt> belts;
    private LayoutInflater inflater;
    private Context context;

    public BeltPagerAdapter(Context context, List<Belt> belts) {
        this.context = context;
        this.belts = belts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return belts.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = inflater.inflate(R.layout.item_belt, container, false);

        ImageView imageView = itemView.findViewById(R.id.beltImageView);
        TextView titleTextView = itemView.findViewById(R.id.beltTitleTextView);
        TextView descriptionTextView = itemView.findViewById(R.id.beltDescriptionTextView);

        Belt belt = belts.get(position);

        imageView.setImageResource(belt.getImageResource());
        titleTextView.setText(belt.getTitle());
        descriptionTextView.setText(belt.getDescription());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

