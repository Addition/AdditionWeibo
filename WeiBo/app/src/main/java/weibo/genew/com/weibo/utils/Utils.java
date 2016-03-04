package weibo.genew.com.weibo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import weibo.genew.com.weibo.manager.BitmapCacheManager;

/**
 * Created by Addition on 2015/8/14.
 */
public class Utils
{
    public static Bitmap getBitmapFromURL(String src,int reqWidth, int reqHeight) {
        try {
            Log.e("src", src);
            Bitmap bitmap = BitmapCacheManager.getInstance().getBitmapFromMemCache(src);
            if(null != bitmap)
            {
                return bitmap;
            }
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap =
//                    BitmapUtils.decodeSampledBitmapFromInputStream(input,reqWidth,reqHeight);
                    BitmapFactory.decodeStream(input);
            BitmapCacheManager.getInstance().addBitmapToMemoryCache(src,myBitmap);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}
