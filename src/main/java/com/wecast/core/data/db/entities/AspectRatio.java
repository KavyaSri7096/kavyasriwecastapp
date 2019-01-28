package com.wecast.core.data.db.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ageech@live.com
 */

public class AspectRatio {

    public static final String ASPECT_RATIO_ORIGINAL = "Original";
    public static final String ASPECT_RATIO_16_9 = "16:9";
    public static final String ASPECT_RATIO_4_3 = "4:3";

    public static final int RATIO_ORIGINAL = 0;
    public static final int RATIO_16_9 = 1;
    public static final int RATIO_4_3 = 2;

    public static final float RATIO_MULTIPLIER_ORIGINAL = 0;
    public static final float RATIO_MULTIPLIER_16_9 = 16f / 9f;
    public static final float RATIO_MULTIPLIER_4_3 = 4f / 3f;

    private String name;
    private int value;
    private float widthHeightRatio;

    public AspectRatio() {
    }

    public AspectRatio(String name, int value, float widthHeightRatio) {
        this.name = name;
        this.value = value;
        this.widthHeightRatio = widthHeightRatio;

    }

    public int getId() {
        return value;
    }

    public float getWidthHeightRatio() {
        return widthHeightRatio;
    }

    public String getName() {
        return name;
    }

    public static ArrayList<AspectRatio> getAvailableAspectRatios() {
        ArrayList<AspectRatio> items = new ArrayList<>();
        AspectRatio ratio_original = new AspectRatio(ASPECT_RATIO_ORIGINAL, RATIO_ORIGINAL, RATIO_MULTIPLIER_ORIGINAL);
        items.add(ratio_original);
        AspectRatio ratio_16_9 = new AspectRatio(ASPECT_RATIO_16_9, RATIO_16_9, RATIO_MULTIPLIER_16_9);
        items.add(ratio_16_9);
        AspectRatio ratio_4_3 = new AspectRatio(ASPECT_RATIO_4_3, RATIO_4_3, RATIO_MULTIPLIER_4_3);
        items.add(ratio_4_3);
        return items;
    }

    public static AspectRatio getAspectRatio(int index) {
        List<AspectRatio> rations = getAvailableAspectRatios();
        for (AspectRatio ratio : rations) {
            if (ratio.getId() == index) {
                return ratio;
            }
        }
        return rations.get(0);
    }
}
