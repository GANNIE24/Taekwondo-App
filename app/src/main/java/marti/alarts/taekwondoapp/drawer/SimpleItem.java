package marti.alarts.taekwondoapp.drawer;


import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import marti.alarts.taekwondoapp.R;

public class SimpleItem extends DrawerItem<SimpleItem.ViewHolder> {

    private int selectedIconTint;
    private int selectedTextTint;

    private int normalIconTint;
    private int normalTextTint;

    private final Drawable icon;
    private final String title;

    public SimpleItem(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_option, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {
        holder.title.setText(title);
        holder.icon.setImageDrawable(icon);
        holder.title.setTextColor(isChecked ? selectedTextTint : normalTextTint);
        holder.icon.setColorFilter(isChecked ? selectedIconTint : normalIconTint);
    }

    public SimpleItem withSelectedIconTint(int selectedIconTint) {
        this.selectedIconTint = selectedIconTint;
        return this;
    }

    public SimpleItem withSelectedTextTint(int selectedTextTint) {
        this.selectedTextTint = selectedTextTint;
        return this;
    }

    public SimpleItem withIconTint(int normalIconTint) {
        this.normalIconTint = normalIconTint;
        return this;
    }

    public SimpleItem withTextTint(int normalTextTint) {
        this.normalTextTint = normalTextTint;
        return this;
    }

    static class ViewHolder extends DrawerAdapter.ViewHolder {

        private final ImageView icon;
        private final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
        }
    }
}
