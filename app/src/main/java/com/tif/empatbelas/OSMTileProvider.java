package com.tif.empatbelas;

import com.google.android.gms.maps.model.UrlTileProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class OSMTileProvider extends UrlTileProvider {

    private static final int TILE_WIDTH = 256;
    private static final int TILE_HEIGHT = 256;

    private static final String OSM_MAP_URL_FORMAT = "http://a.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/%d/%d/%d.png";

    public OSMTileProvider() {
        super(TILE_WIDTH, TILE_HEIGHT);
    }

    @Override
    public URL getTileUrl(int x, int y, int zoom) {
        String s = String.format(Locale.US, OSM_MAP_URL_FORMAT, zoom, x, y);

        URL url = null;
        try {
            url = new URL(s);
        } catch (MalformedURLException e) {
            return null;
        }
        return url;
    }

}