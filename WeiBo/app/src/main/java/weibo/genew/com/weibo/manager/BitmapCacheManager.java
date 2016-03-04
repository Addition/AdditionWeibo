package weibo.genew.com.weibo.manager;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Addition on 2016/2/18.
 */
public class BitmapCacheManager
{
    private LruCache<String, Bitmap> mMemoryCache;

//    private DiskLruCache mDiskLruCache;
//    private final Object mDiskCacheLock = new Object();

    private static BitmapCacheManager instance;

    private static Object instanceLock = new Object();

    public static BitmapCacheManager getInstance()
    {
        synchronized (instanceLock)
        {
            if(null == instance)
                instance = new BitmapCacheManager();
        }
        return instance;
    }

    private BitmapCacheManager()
    {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}
