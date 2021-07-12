package com.tcc2.bke_auth4isp.common;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;

import static com.tcc2.bke_auth4isp.common.Blur.fastblur;


/**
 * Created by Vagner on 20/01/2021.
 */
public class ImageUtilities {
    public static Bitmap getRoundCornerBitmap(Bitmap bitmap, int radius) {
        int w = bitmap.getHeight();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        final RectF rectF = new RectF(0, 0, h, h);

        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rectF, paint);

        /**
         * here to define your corners, this is for left bottom and right bottom corners
         */
        final Rect clipRect = new Rect(0, 0, w, h - radius);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        canvas.drawRect(clipRect, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rectF, paint);

        return output;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static void downloadWpp(final ImageView image2, final Context context, final boolean full, final String urlThumbnail, final String url, final int w, final int h) {         //Teste Downlaod Image como wpp

        final Transformation blurTransformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Bitmap blurred = fastblur(context, source, 10);
                source.recycle();
                return blurred;
            }

            @Override
            public String key() {
                return "blur()";
            }
        };
        Picasso.with(context)
                .load(urlThumbnail) // thumbnail url goes here
                .placeholder(image2.getDrawable())
                .resize(w, h)
                .transform(blurTransformation)
                .into(image2, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (full) {
                            com.tcc2.bke_auth4isp.common.ImageUtilities imageUtilities = new com.tcc2.bke_auth4isp.common.ImageUtilities();
                            Bitmap icon = imageUtilities.drawableToBitmap(image2.getDrawable());
                            icon = imageUtilities.getRoundCornerBitmap(icon, 360);
                            image2.setImageBitmap(icon);

                            Picasso.with(context)
                                    .load(url) // image url goes here
                                    .placeholder(image2.getDrawable())
                                    .resize(w, h)
                                    .transform(blurTransformation)
                                    .into(image2, new Callback() {
                                        @Override
                                        public void onSuccess() {
                                            com.tcc2.bke_auth4isp.common.ImageUtilities imageUtilities = new com.tcc2.bke_auth4isp.common.ImageUtilities();
                                            Bitmap icon = imageUtilities.drawableToBitmap(image2.getDrawable());
                                            icon = imageUtilities.getRoundCornerBitmap(icon, 360);
                                            image2.setImageBitmap(icon);
                                            Picasso.with(context)
                                                    .load(url) // image url goes here
                                                    .placeholder(image2.getDrawable())
                                                    .resize(w, h)
                                                    .into(image2, new Callback() {
                                                        @Override
                                                        public void onSuccess() {
                                                            com.tcc2.bke_auth4isp.common.ImageUtilities imageUtilities = new com.tcc2.bke_auth4isp.common.ImageUtilities();
                                                            Bitmap icon = imageUtilities.drawableToBitmap(image2.getDrawable());
                                                            icon = imageUtilities.getRoundCornerBitmap(icon, 360);
                                                            image2.setImageBitmap(icon);
                                                        }

                                                        @Override
                                                        public void onError() {
//                                                            Log.debugRed("Download error!");
                                                        }
                                                    });
                                        }

                                        @Override
                                        public void onError() {
                                        }
                                    });

//                            Log.debugRed("DBG - Atualizada imagem " + urlThumbnail);
                        } else {
                            image2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Toast.makeText(context, "DBG - Atualizando imagem.", Toast.LENGTH_LONG);
                                    downloadWpp(image2, context, true, urlThumbnail, url, 100, 100);
                                }
                            });

                        }

                    }

                    @Override
                    public void onError() {
                    }
                });

    }

    public static void downloadWppListening(final ImageView imageView, final Context context, final String url, boolean
            full) {

        try {

            Picasso.Builder builder = new Picasso.Builder(context);
            builder.listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    exception.printStackTrace();
                    if (exception.getLocalizedMessage().contains("SocketTimeoutException")) {
                        Toast.makeText(context, "Falha de conexão!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            final Transformation blurTransformation = new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    Log.d("Picaso", "Transformando");
                    Bitmap blurred = fastblur(context, source, 10);
                    source.recycle();
                    return blurred;
                }

                @Override
                public String key() {
                    return "blur()";
                }
            };
            if (full) {
                builder.build()
                        .load(url)
                        .placeholder(imageView.getDrawable())
                        .resize(100, 100)
                        .transform(blurTransformation)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Log.d("Picaso", "Download completo!");
                                //Round
                                roundImage(imageView, 360);
                                Log.d("Picaso", " Atualizada imagem " + url);
                            }

                            @Override
                            public void onError() {
                                Log.d("Picaso", "NÃO ATUALIZADA imagem " + url);
                            }
                        });
            } else {
                builder.build()
                        .load(url)
                        .placeholder(imageView.getDrawable())
                        .resize(100, 100)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Log.d("Picaso", "Download completo!");
                                Bitmap icon = com.tcc2.bke_auth4isp.common.ImageUtilities.drawableToBitmap(imageView.getDrawable());
                                icon = com.tcc2.bke_auth4isp.common.ImageUtilities.getRoundCornerBitmap(icon, 360);
                                imageView.setImageBitmap(icon);
                                Log.d("Picaso", "DBG - Atualizada imagem " + url);
                            }

                            @Override
                            public void onError() {
                                Log.d("Picaso", "DBG - NÃO ATUALIZADA imagem " + url);
                            }
                        });
            }
        } catch (OutOfMemoryError outOfMemoryError) {
            Log.e("Exception", "Out Of Memory");
        }
    }

    public static void downloadWppCategoryHeader(final ImageView imageView, final Context context, final String url, final int h, final int w, boolean
            full) {

        try {

            Picasso.Builder builder = new Picasso.Builder(context);
            builder.listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    exception.printStackTrace();
                    if (exception.getLocalizedMessage().contains("SocketTimeoutException")) {
                        Toast.makeText(context, "Falha de conexão!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            final Transformation blurTransformation = new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    Log.d("Picaso", "Transformando");
                    Bitmap blurred = source;// Blur.fastblur(context, source, 10);
//                    source.recycle();
                    return blurred;
                }

                @Override
                public String key() {
                    return "blur()";
                }
            };
            if (full) {
                builder.build()
                        .load(url)
                        .placeholder(imageView.getDrawable())
                        .resize(w, h)
                        .transform(blurTransformation)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Log.d("Picaso", "Download completo!");
                                //Round
//                                roundImage(imageView, 360);
                                Log.d("Picaso", " Atualizada imagem " + url);
                            }

                            @Override
                            public void onError() {
                                Log.d("Picaso", "NÃO ATUALIZADA imagem " + url);
                            }
                        });
            } else {
                builder.build()
                        .load(url)
                        .placeholder(imageView.getDrawable())
                        .resize(w, h)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Bitmap icon = com.tcc2.bke_auth4isp.common.ImageUtilities.drawableToBitmap(imageView.getDrawable());
                                imageView.setImageBitmap(icon);
                                Log.d("Picaso", "DBG - Atualizada imagem " + url);
                            }

                            @Override
                            public void onError() {
                                Log.d("Picaso", "DBG - NÃO ATUALIZADA imagem " + url);
                            }
                        });
            }
        } catch (OutOfMemoryError outOfMemoryError) {
            Log.e("Exception", "Out Of Memory");
        }

    }

    public static void downloadWppListening(final ImageView imageView, final Context context, final String url, boolean
            full, final int round, final int radius) {

        try {

            Picasso.Builder builder = new Picasso.Builder(context);
            builder.listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    exception.printStackTrace();
                    if (exception.getLocalizedMessage().contains("SocketTimeoutException")) {
                        Toast.makeText(context, "Falha de conexão!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            final Transformation blurTransformation = new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    Log.d("Picaso", "Transformando");
                    Bitmap blurred = fastblur(context, source, radius);
                    source.recycle();
                    return blurred;
                }

                @Override
                public String key() {
                    return "blur()";
                }
            };
            if (full) {
                builder.build()
                        .load(url)
                        .placeholder(imageView.getDrawable())
                        .resize(100, 100)
                        .transform(blurTransformation)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Log.d("Picaso", "Download completo!");
                                //Round
                                roundImage(imageView, radius);
                                Log.d("Picaso", " Atualizada imagem " + url);
                            }

                            @Override
                            public void onError() {
                                Log.d("Picaso", "NÃO ATUALIZADA imagem " + url);
                            }
                        });
            } else {
                builder.build()
                        .load(url)
                        .placeholder(imageView.getDrawable())
                        .resize(100, 100)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Log.d("Picaso", "Download completo!");
                                Bitmap icon = com.tcc2.bke_auth4isp.common.ImageUtilities.drawableToBitmap(imageView.getDrawable());
                                icon = com.tcc2.bke_auth4isp.common.ImageUtilities.getRoundCornerBitmap(icon, round);
                                imageView.setImageBitmap(icon);
                                Log.d("Picaso", "DBG - Atualizada imagem " + url);
                            }

                            @Override
                            public void onError() {
                                Log.d("Picaso", "DBG - NÃO ATUALIZADA imagem " + url);
                            }
                        });
            }
        } catch (OutOfMemoryError outOfMemoryError) {
            Log.e("Exception", "Out Of Memory");
        }
    }

    public static void downloadWppFast(final ImageView image2, final Context context, final String url, final int w, final int h) {         //Teste Downlaod Image como wpp

        Picasso.with(context)
                .load(url) // thumbnail url goes here
                .placeholder(image2.getDrawable())
                .resize(w, h)
                .into(image2, new Callback() {
                    @Override
                    public void onSuccess() {
                        com.tcc2.bke_auth4isp.common.ImageUtilities imageUtilities = new com.tcc2.bke_auth4isp.common.ImageUtilities();
                        Bitmap icon = imageUtilities.drawableToBitmap(image2.getDrawable());
                        icon = imageUtilities.getRoundCornerBitmap(icon, 360);
                        image2.setImageBitmap(icon);
                    }

                    @Override
                    public void onError() {
                        YLog.d("ImageUtilities", "Download error", url.toString());
                    }
                });
    }

    public static void downloadWppFastNoChange(final ImageView image2, final Context context, final String url) {         //Teste Downlaod Image como wpp

        Picasso.with(context)
                .load(url) // thumbnail url goes here
                .placeholder(image2.getDrawable())
//                .resize(w, h)
                .into(image2, new Callback() {
                    @Override
                    public void onSuccess() {
                        com.tcc2.bke_auth4isp.common.ImageUtilities imageUtilities = new com.tcc2.bke_auth4isp.common.ImageUtilities();
                        Bitmap icon = imageUtilities.drawableToBitmap(image2.getDrawable());
//                        icon = imageUtilities.getRoundCornerBitmap(icon, 0);
                        image2.setImageBitmap(icon);
                    }

                    @Override
                    public void onError() {
                        YLog.d("ImageUtilities", "Download error", url.toString());
                    }
                });
    }



    public static void roundImage(ImageView imageView, int radious) {
        Bitmap icon = drawableToBitmap(imageView.getDrawable());
        icon = getRoundCornerBitmap(icon, radious);
        imageView.setImageBitmap(icon);
    }

    public void scaleDown(ImageView imageView, float maxImageSize,
                          boolean filter) {
        Bitmap realImage = drawableToBitmap(imageView.getDrawable());
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        imageView.setImageBitmap(newBitmap);
    }
}
