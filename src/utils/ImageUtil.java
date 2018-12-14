package utils;

import java.net.URL;

public class ImageUtil {
    public static URL getImage(String name) {
        return ImageUtil.class.getResource("/img/" + name);
    }
}
