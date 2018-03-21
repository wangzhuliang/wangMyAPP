package zl.wang.cn.com.wangmyapp.explain;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;

import java.security.MessageDigest;

/**
 * Created by hahaha on 2018/3/7.
 */

public class glide {

    //glide 用法传承目前流行的链式调用。
    /**
     * 基础用法.
     */
    /*private void baseUsed(){
        Glide.with(this)
                .load(URL)
                .into(mImageView);
    }*/

    /*private void gildeOptions(){
        RequestOptions options = new RequestOptions();
        options.centerCrop()
                .placeholder(R.drawable.default_avatar)
                .error(R.drawable.image_error)
                .fallback(R.drawable.fallback_nodata);

        Glide.with(this)
                .load(URL)
                .apply(options)
                .into(mImageView);
    }*/

    /**
     * 自定义圆角
     */
    /*public class CircleTransform extends BitmapTransformation {

        public CircleTransform(Context context){
            super(context);
        }

        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool,toTransform);
        }

        @Override
        public void updateDiskCacheKey(MessageDigest messageDigest) {

        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            //画布中背景图片与绘制图片交集部分
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }
    }*/

    /**
     * 自定义圆形裁剪.
     */
    /*private void customerOptions(){
        RequestOptions options = new RequestOptions();
        options.centerCrop()
                .placeholder(R.drawable.default_avatar)
                .error(R.drawable.image_error)
                .fallback(R.drawable.fallback_nodata)
                .transform(new CircleTransform(this));

        Glide.with(this)
                .load(URL)
                .apply(options)
                .into(mImageView);
    }*/

    /**
     * 圆形图片
     */
    /*public class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override public String getId() {
            return getClass().getName();
        }
    }*/
}
