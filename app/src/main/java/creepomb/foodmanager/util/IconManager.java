package creepomb.foodmanager.util;

import android.widget.ImageView;

import creepomb.foodmanager.R;

public class IconManager {
    public static void setIconSrc(ImageView ivName, int iconIndex) {
        switch (iconIndex) {
            case 1:
                ivName.setImageResource(R.drawable.icon_refrigerator);
                break;
            case 2:
                ivName.setImageResource(R.drawable.icon_cabinet);
                break;
        }
    }
}
