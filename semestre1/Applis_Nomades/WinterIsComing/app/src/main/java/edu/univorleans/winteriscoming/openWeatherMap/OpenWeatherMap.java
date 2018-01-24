package edu.univorleans.winteriscoming.openWeatherMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class OpenWeatherMap implements TileProvider  {

    private Paint opacityPaint = new Paint();
    private String tileType = "temp";
    private static String appid = "7de56b18f9e770cbe1a3ecbec44feaab";
    private static final String OWM_TILE_URL = "http://tile.openweathermap.org/map/%s/%d/%d/%d.png?appid=" + appid;

    public OpenWeatherMap()
    {
        int alpha = (int)Math.round(50 * 2.55);
        opacityPaint.setAlpha(alpha);
    }

    @Override
    public Tile getTile(int x, int y, int zoom)
    {
        URL tileUrl = getTileUrl(x, y, zoom);

        Tile tile = null;
        ByteArrayOutputStream stream = null;

        try
        {
            Bitmap image = BitmapFactory.decodeStream(tileUrl.openConnection().getInputStream());
            image = adjustOpacity(image);

            stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);

            byte[] byteArray = stream.toByteArray();

            tile = new Tile(256, 256, byteArray);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(stream != null)
            {
                try
                {
                    stream.close();
                }
                catch(IOException e) {}
            }
        }

        return tile;
    }

    private URL getTileUrl(int x, int y, int zoom)
    {
        String tileUrl = String.format(OWM_TILE_URL, tileType, zoom, x, y);
        try
        {
            return new URL(tileUrl);
        }
        catch(MalformedURLException e)
        {
            throw new AssertionError(e);
        }
    }


    private Bitmap adjustOpacity(Bitmap bitmap)
    {
        Bitmap adjustedBitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(adjustedBitmap);
        canvas.drawBitmap(bitmap, 0, 0, opacityPaint);

        return adjustedBitmap;
    }
}
