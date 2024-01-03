package marti.alarts.taekwondoapp;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;
import java.util.Objects;

import marti.alarts.taekwondoapp.drawer.DrawerAdapter;
import marti.alarts.taekwondoapp.drawer.DrawerItem;
import marti.alarts.taekwondoapp.drawer.SimpleItem;
import marti.alarts.taekwondoapp.drawer.SpaceItem;
import marti.alarts.taekwondoapp.fragments.BeltFragment;
import marti.alarts.taekwondoapp.fragments.HomeFragment;
import marti.alarts.taekwondoapp.fragments.KeyFragment;
import marti.alarts.taekwondoapp.fragments.SafetyFragment;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {
    private static final int POS_CLOSE = 0;
    private static final int POS_HOME = 1;
    private static final int POS_BELT = 2;
    private static final int POS_KEY = 3;
    private static final int POS_SAFE = 4;

    private String[] screenTitles;
    private Drawable[] screenIcons;
    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                Objects.requireNonNull(createItemFor(POS_HOME)).setChecked(true),
                createItemFor(POS_BELT),
                createItemFor(POS_KEY),
                createItemFor(POS_SAFE),
                new SpaceItem(260)
        ));
        adapter.setListener(this);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_HOME);
    }

    private DrawerItem createItemFor(int position) {
        if (position >= 0 && position < screenIcons.length && position < screenTitles.length) {
            return new SimpleItem(screenIcons[position], screenTitles[position])
                    .withTextTint(color(R.color.black))
                    .withSelectedTextTint(color(R.color.blue));
        } else {
            // Handle the case where the position is out of bounds
            return null; // or provide a default DrawerItem
        }
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenIcon);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @Override
    public void onBackPressed() {
        // If the navigation drawer is open, close it
        super.onBackPressed();
        if (slidingRootNav.isMenuOpened()) {
            slidingRootNav.closeMenu();
        } else {
            // If the navigation drawer is closed, handle back press based on your app's logic

            // For example, you can show a confirmation dialog before exiting the app
            showCloseConfirmationDialog();
        }
    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (position == POS_HOME) {
            HomeFragment homeFragment = new HomeFragment();
            transaction.replace(R.id.container, homeFragment);
        } else if (position == POS_BELT) {
            BeltFragment beltFragment = new BeltFragment();
            transaction.replace(R.id.container, beltFragment);
        } else if (position == POS_KEY) {
            KeyFragment keyFragment = new KeyFragment();
            transaction.replace(R.id.container, keyFragment);
        } else if (position == POS_SAFE) {
            SafetyFragment safetyFragment = new SafetyFragment();
            transaction.replace(R.id.container, safetyFragment);
        } else if (position == POS_CLOSE) {
            // Handle the POS_CLOSE action here
            showCloseConfirmationDialog();
            return;
        }

        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void showCloseConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Close App");
        builder.setMessage("Do you really want to close the app?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            // User clicked Yes button
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            // User clicked No button
            // You can perform any additional actions or leave this empty
        });
        builder.show();
    }
}
