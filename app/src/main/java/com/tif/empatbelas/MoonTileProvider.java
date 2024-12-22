package com.tif.empatbelas;

import com.google.android.gms.maps.model.UrlTileProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class MoonTileProvider extends UrlTileProvider {

    private static final int TILE_WIDTH = 256;
    private static final int TILE_HEIGHT = 256;

    private static final String MOON_MAP_URL_FORMAT =
            "http://mw1.google.com/mw-planetary/lunar/lunarmaps_v1/clem_bw/%d/%d/%d.jpg";

    public MoonTileProvider() {
        super(TILE_WIDTH, TILE_HEIGHT);
    }

    @Override
    public URL getTileUrl(int x, int y, int zoom) {
        // The moon tile coordinate system is reversed.  This is not normal.
        int reversedY = (1 << zoom) - y - 1;
        String s = String.format(Locale.US, MOON_MAP_URL_FORMAT, zoom, x, reversedY);
        URL url = null;
        try {
            url = new URL(s);
        } catch (MalformedURLException e) {
            throw new AssertionError(e);
        }
        return url;
    }

}