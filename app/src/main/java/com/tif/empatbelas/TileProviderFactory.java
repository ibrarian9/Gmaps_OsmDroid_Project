package com.tif.empatbelas;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import android.util.Log;

public class TileProviderFactory {
    public static GeoServerTileProvider getWmsTileProvider() {
        final String WMS_FORMAT_STRING =
                "https://ad38fc28.ngrok.io/geoserver/indonesia/wms" +
                        "?service=WMS" +
                        "&version=1.1.1" +
                        "&request=GetMap" +
                        "&layers=indonesia:ind_provinces" +
                        "&bbox=%f,%f,%f,%f" +
                        "&width=256" +
                        "&height=256" +
                        "&srs=EPSG:900913" +  // NB This is important, other SRS's won't work.
                        "&format=image/png" +
                        "&transparent=true";

        GeoServerTileProvider tileProvider = new GeoServerTileProvider(256,256) {
            @Override
            public synchronized URL getTileUrl(int x, int y, int zoom) {
                double[] bbox = getBoundingBox(x, y, zoom);
                String s = String.format(Locale.US, WMS_FORMAT_STRING, bbox[MINX],
                        bbox[MINY], bbox[MAXX], bbox[MAXY]);
                Log.d("WMSDEMO", s);
                URL url = null;
                try {
                    url = new URL(s);
                } catch (MalformedURLException e) {
                    throw new AssertionError(e);
                }
                return url;
            }
        };
        return tileProvider;
    }
}