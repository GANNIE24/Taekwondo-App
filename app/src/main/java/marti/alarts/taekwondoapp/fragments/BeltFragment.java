package marti.alarts.taekwondoapp.fragments;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import marti.alarts.taekwondoapp.Belt;
import marti.alarts.taekwondoapp.BeltPagerAdapter;
import marti.alarts.taekwondoapp.R;

public class BeltFragment extends Fragment {

    private ViewPager viewPager;
    private int currentPage = 0;
    private final int DELAY_MS = 5000; // Delay in milliseconds before swiping to the next page
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable runnable = new Runnable() {
        public void run() {
            if (currentPage == belts.size()) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
            handler.postDelayed(this, DELAY_MS);
        }
    };

    private List<Belt> belts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_belt, container, false);

        viewPager = view.findViewById(R.id.viewPager);

        belts = new ArrayList<>();
        belts.add(new Belt(R.drawable.beltwhite, "White Belt 10th Kup", "The White Belt, signifying the beginning of the Taekwondo journey, represents purity and the start of one's training in the martial art. As a 10th Kup, the practitioner is like a blank canvas, ready to absorb the fundamental principles of Taekwondo."));
        belts.add(new Belt(R.drawable.yellowstripe, "Yellow Stripe 9th Kup", "The Yellow Stripe marks progress in the Taekwondo journey. At the 9th Kup, practitioners start refining basic techniques and develop a deeper understanding of the art. The yellow stripe symbolizes the first rays of sunlight breaking through the darkness."));
        belts.add(new Belt(R.drawable.yellowbelt, "Yellow Belt 8th Kup", "Achieving the Yellow Belt at the 8th Kup level signifies the growth and strengthening of the foundation laid in earlier stages. Practitioners begin to exhibit more confidence and control in their movements, building a solid base for further advancement."));
        belts.add(new Belt(R.drawable.greenstripe, "Green Stripe 7th Kup", "The Green Stripe represents the flourishing of skills and knowledge. At the 7th Kup, practitioners delve into more complex techniques, resembling the growth of a plant reaching for the sky. This stage is about continued development and expansion."));
        belts.add(new Belt(R.drawable.greenbelt, "Green Belt 6th Kup", "As practitioners attain the Green Belt at the 6th Kup, they embody a deeper understanding of Taekwondo's principles. This stage emphasizes the harmony between physical and mental aspects, akin to a tree with strong roots and branches."));
        belts.add(new Belt(R.drawable.bluestripe, "Blue Stripe 5th Kup", "The Blue Stripe signifies a deeper commitment to the art. At the 5th Kup, practitioners dive into advanced techniques and patterns, reflecting the depth of their knowledge. Like the blue sky, they aim for limitless possibilities in their Taekwondo journey."));
        belts.add(new Belt(R.drawable.bluebelt, "Blue Belt 4th Kup", "Achieving the Blue Belt at the 4th Kup level demonstrates proficiency in both basic and advanced techniques. Practitioners refine their skills with a focus on precision and fluidity, resembling the calm and steady flow of a deep blue river."));
        belts.add(new Belt(R.drawable.redstripe, "Red Stripe 3rd Kup", "The Red Stripe marks the transition to the higher echelons of Taekwondo. At the 3rd Kup, practitioners exhibit mastery over intricate patterns and demonstrate a higher level of physical and mental discipline, resembling the intensity of a red flame."));
        belts.add(new Belt(R.drawable.redbelt, "Red Belt 2nd Kup", "As practitioners attain the Red Belt at the 2nd Kup, they symbolize the approaching completion of their color belt journey. This stage emphasizes a refined understanding of techniques and principles, akin to the maturation of a fruit nearing ripeness."));
        belts.add(new Belt(R.drawable.blackstripe, "Black Stripe 1st Kup", "The Black Stripe signifies the final stages before achieving the coveted Black Belt. At the 1st Kup, practitioners refine their skills to the highest level, demonstrating dedication and proficiency in all aspects of Taekwondo."));
        belts.add(new Belt(R.drawable.blackbelt, "1st-9th Dan Black Belt", "The Black Belt represents the pinnacle of achievement in Taekwondo. Each Dan level signifies a new beginning, and practitioners continue to refine their skills, deepen their understanding, and contribute to the legacy of this ancient martial art. The journey is now about continuous improvement and sharing knowledge with others."));

        BeltPagerAdapter pagerAdapter = new BeltPagerAdapter(requireContext(), belts);
        viewPager.setAdapter(pagerAdapter);

        // Start automatic swiping
        handler.postDelayed(runnable, DELAY_MS);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Stop automatic swiping when the fragment is destroyed
        handler.removeCallbacks(runnable);
    }
}