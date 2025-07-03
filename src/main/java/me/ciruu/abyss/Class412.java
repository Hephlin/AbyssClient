package me.ciruu.abyss;

import java.net.URI;
import java.net.URISyntaxException;

public final class Class412 {
    private static final URI Field3101;

    static {
        try {
            Field3101 = new URI("ws://abyssclient.com:56434");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static final URI Method1757() {
        return Field3101;
    }
}
