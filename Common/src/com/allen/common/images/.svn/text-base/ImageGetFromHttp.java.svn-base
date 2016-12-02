package com.allen.common.images;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;

public class ImageGetFromHttp {
	private static final String LOG_TAG = "ImageGetFromHttp";

	public static Bitmap downloadBitmap(String url) {
		final HttpClient client = new DefaultHttpClient();
		final HttpGet getRequest = new HttpGet(url);

		try {
			HttpResponse response = client.execute(getRequest);
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(LOG_TAG, "Error " + statusCode
						+ " while retrieving bitmap from " + url);
				return null;
			}

			final HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = null;
				try {
					inputStream = entity.getContent();
					FilterInputStream fit = new FlushedInputStream(inputStream);
					return BitmapFactory.decodeStream(fit);
				} finally {
					if (inputStream != null) {
						inputStream.close();
						inputStream = null;
					}
					entity.consumeContent();
				}
			}
		} catch (IOException e) {
			getRequest.abort();
			Log.w(LOG_TAG, "I/O error while retrieving bitmap from " + url, e);
		} catch (IllegalStateException e) {
			getRequest.abort();
			Log.w(LOG_TAG, "Incorrect URL: " + url);
		} catch (Exception e) {
			getRequest.abort();
			Log.w(LOG_TAG, "Error while retrieving bitmap from " + url, e);
		} finally {
			client.getConnectionManager().shutdown();
		}
		return null;
	}

	/*
	 * An InputStream that skips the exact number of bytes provided, unless it
	 * reaches EOF.
	 */
	static class FlushedInputStream extends FilterInputStream {
		public FlushedInputStream(InputStream inputStream) {
			super(inputStream);
		}

		@Override
		public long skip(long n) throws IOException {
			long totalBytesSkipped = 0L;
			while (totalBytesSkipped < n) {
				long bytesSkipped = in.skip(n - totalBytesSkipped);
				if (bytesSkipped == 0L) {
					int b = read();
					if (b < 0) {
						break; // we reached EOF
					} else {
						bytesSkipped = 1; // we read one byte
					}
				}
				totalBytesSkipped += bytesSkipped;
			}
			return totalBytesSkipped;
		}
	}
	
	/**
	 * 图片变灰色处理
	 * @param bitmap
	 * @return
	 */
	public static final Bitmap grey(Bitmap bitmap) {
		  int width = bitmap.getWidth();
		  int height = bitmap.getHeight();
		  
		  Bitmap faceIconGreyBitmap = Bitmap
		    .createBitmap(width, height, Bitmap.Config.ARGB_8888);
		  
		  Canvas canvas = new Canvas(faceIconGreyBitmap);
		  Paint paint = new Paint();
		  ColorMatrix colorMatrix = new ColorMatrix();
		  colorMatrix.setSaturation(0);
		  ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
		    colorMatrix);
		  paint.setColorFilter(colorMatrixFilter);
		  canvas.drawBitmap(bitmap, 0, 0, paint);
		  return faceIconGreyBitmap;
	}
}