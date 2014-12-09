package com.wt.studio.plugin.modeldesigner.utils;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class ColorResource {
	public static Color GRID_COLOR = new Color(Display.getCurrent(), 220, 220, 255);
	private static Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
public static Color getColor(int[] rgb) {
	int key = rgb[0] * 1000000 + rgb[1] * 1000 + rgb[2];

	Color color = colorMap.get(key);

	if (color != null) {
		return color;
	}

	color = new Color(Display.getCurrent(), rgb[0], rgb[1], rgb[2]);
	colorMap.put(key, color);

	return color;
}

public static void disposeColorMap() {
	for (Color color : colorMap.values()) {
		if (!color.isDisposed()) {
			color.dispose();
		}
	}
}
}