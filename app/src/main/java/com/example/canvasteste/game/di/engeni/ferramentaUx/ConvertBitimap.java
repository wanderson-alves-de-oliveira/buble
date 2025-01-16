package com.example.canvasteste.game.di.engeni.ferramentaUx;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

/**
 * Created by wanderson on 01/03/18.
 */

public class ConvertBitimap {



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getBitmap(Context context, int drawableId, int cor1, int cor2, int cor3, boolean muda) {
        Drawable drawable = ContextCompat.getDrawable(context,drawableId);
        if (drawable instanceof BitmapDrawable) {
          return BitmapFactory.decodeResource(context.getResources(),drawableId);
        } else if (drawable instanceof VectorDrawable) {
            drawable.mutate().clearColorFilter();
            if(muda){
            PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.rgb( cor1,cor2,cor3 ), PorterDuff.Mode.MULTIPLY);
            drawable.mutate().setColorFilter(colorFilter);}

            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("erro");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getBitmapBase(Context context, int drawableId, int cor1, int cor2, int cor3, boolean muda) {
        Drawable drawable = ContextCompat.getDrawable(context,drawableId);

        if (drawable instanceof BitmapDrawable) {
            return BitmapFactory.decodeResource(context.getResources(),drawableId);
        } else if (drawable instanceof VectorDrawable) {
            drawable.mutate().clearColorFilter();
            if(muda){
                PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.rgb( cor1,cor2,cor3 ), PorterDuff.Mode.MULTIPLY);
                drawable.mutate().setColorFilter(colorFilter);}
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("erro");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getBitmap(Context context, int drawableId, int alp, int cor1, int cor2, int cor3, boolean muda) {
        Drawable drawable = ContextCompat.getDrawable(context,drawableId);

        if (drawable instanceof BitmapDrawable) {
            return BitmapFactory.decodeResource(context.getResources(),drawableId);
        } else if (drawable instanceof VectorDrawable) {
            drawable.mutate().clearColorFilter();
            if(muda){
                PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.argb( alp, cor1,cor2,cor3 ), PorterDuff.Mode.MULTIPLY);
                drawable.mutate().setColorFilter(colorFilter);}
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("erro");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getBitmap(String vectorDrawable,int t,int p) {
        int largura=0;
       switch (vectorDrawable.length()){
           case 1:
               largura=200;
               break;

           case 2:
               largura=410;
               break;

           default:
               largura=600;
               break;

       }

        Paint pincel = new Paint(  );
        pincel.setColor( Color.argb( 90,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80));


        Bitmap bitmap = Bitmap.createBitmap(largura,300,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);



        canvas.drawRoundRect( 0 ,0 ,
                largura,300,
                70,70,pincel );


        pincel.setColor( Color.argb( 255,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80));
        pincel.setTextSize( t );
        canvas.drawText( vectorDrawable,p,180,pincel);

        return bitmap;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getBitmapBolha(String vectorDrawable, int t, int c) {
        int largura=0;
        int p;
        switch (vectorDrawable.length()){
            case 1:
                largura=200;
                p=60;
                break;

            case 2:
                largura=410;
                p=100;

                break;

            default:
                largura=600;
                p=30;

                break;

        }

        Paint pincel = new Paint(  );
        if(c==0) {
            pincel.setColor( Color.rgb( 140,205,205 ) );
        }else  if(c==1) {

            pincel.setColor( Color.rgb( 240,105,55 ) );

        }else  if(c==2) {

            pincel.setColor( Color.argb( 240,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80));

        }else  if(c==3) {

            pincel.setColor( Color.argb( 0,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80));

        }


        Bitmap bitmap = Bitmap.createBitmap(largura,300,Bitmap.Config.ARGB_8888);


        Canvas canvas = new Canvas(bitmap);



        canvas.drawRoundRect( 0 ,0 ,
                largura,300,
                0,0,pincel );


        pincel.setColor( Color.argb( 255,255,255,255));
        pincel.setTextSize( t );
        canvas.drawText( vectorDrawable,p,180,pincel);

        return bitmap;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getBitmapBarraxz(Context context,Bitmap img, int positionLetra, String palavra, int t) {

        float s =context.getResources().getDisplayMetrics().density;
        int ww = (int) (100*s);

        int p=220;

        //img = Bitmap.createScaledBitmap(img,80,75, false);

        Paint pincel = new Paint(  );

        pincel.setColor( Color.argb( 10,23,23,23));


        Bitmap bitmap = Bitmap.createBitmap(600,150,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor( Color.rgb( 10,129,112 ) );


        canvas.drawRoundRect( 15 ,0 ,
                560,120,
                50,50,pincel );
        //canvas.drawBitmap( img,5,(float) 60,pincel );

        pincel.setColor( Color.argb( 255,251,251,251));
        pincel.setTextSize( t );
        canvas.drawText( palavra,p,70,pincel);


        return bitmap;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getBitmapBarra(Context context,List<Bitmap> imag, int positionLetra, String palavra, int tt, int vida, AssetManager asset) {

        float s =context.getResources().getDisplayMetrics().density;
        int w= (int) (100*s);
        int p=50;
int t = (int) (w*0.3);
        Bitmap  img = Bitmap.createScaledBitmap(imag.get( 0 ), (int) (w*0.45),(int) (w*0.40), false);
        Bitmap  imgF= Bitmap.createScaledBitmap(imag.get( 2 ), (int) (w*0.35),(int) (w*0.35), false);
        Bitmap  imgC= Bitmap.createScaledBitmap(imag.get( 1 ), (int) (w*0.15),(int) (w*0.15), false);

        Paint pincel = new Paint(  );

        pincel.setColor( Color.rgb( 123,213,163));


        Bitmap bitmap = Bitmap.createBitmap(600,150,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor( Color.argb( 255,255,255,255 ) );



   canvas.drawBitmap( img,(int) (w*0.05),(int) (w*0.35),pincel );
     canvas.drawBitmap( imgF,89,(float) 70,pincel );

        for(int i =0;i<vida;i++) {

            canvas.drawBitmap( imgC, 450+(i*imgC.getWidth()), (float) 70, pincel );
        }
        pincel.setColor( Color.argb( 255,151,151,151));
        pincel.setTextSize( t );
        canvas.drawText( palavra,p,60,pincel);

        String palavraAux="";
        for(int i =0;i<positionLetra;i++) {

            palavraAux=palavraAux+palavra.charAt( i );

        }

        pincel.setColor( Color.argb( 255,245,0,100));
        pincel.setTextSize( t );
        canvas.drawText( palavraAux,p,60,pincel);


        Typeface tf =Typeface.createFromAsset(asset,"fonts/font.otf");
        pincel.setTypeface(tf);


        pincel.setColor( Color.argb( 255,151,151,151));
        pincel.setTextSize((float) (t-(w*0.001)));
         palavra=palavra.toLowerCase();

        canvas.drawText( palavra,p+125,105,pincel);

         palavraAux="";
        for(int i =0;i<positionLetra;i++) {

            palavraAux=palavraAux+palavra.charAt( i );

        }
        palavraAux=palavraAux.toLowerCase();

        pincel.setColor( Color.argb( 255,245,0,100));
        pincel.setTextSize((float) (t-(w*0.01)));
        canvas.drawText( palavraAux,p+125,105,pincel);


        return bitmap;
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getFichas(Bitmap imag, String palavra, int t, AssetManager asset) {
        int p=50;

        Bitmap  img = Bitmap.createScaledBitmap(imag,50,50, false);

        Paint pincel = new Paint(  );



        Bitmap bitmap = Bitmap.createBitmap(200,80,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
       // canvas.drawColor( Color.rgb( 100,129,182 ) );

        pincel.setColor( Color.argb( 144,100, 255, 155 ) );
        canvas.drawRoundRect( 5, 5,
               195, 75, 80, 80, pincel );

        pincel.setColor( Color.rgb( 123,163,213));

        canvas.drawBitmap( img,5,(float)5,pincel );


        pincel.setColor( Color.argb( 255,255,255,255));
        pincel.setTextSize( t );
        canvas.drawText( palavra,50,60,pincel);


        return bitmap;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getTextura(Context context, Bitmap imag, int txt) {
        float s =context.getResources().getDisplayMetrics().density;
        int ww = (int) (100*s);

        Bitmap  img = Bitmap.createScaledBitmap(imag,ww*4,ww*5, false);

        Paint pincel = new Paint(  );

        Bitmap tx = ConvertBitimap.getBitmap( context, txt,0,0,0,false );


       //Bitmap bitmap = Bitmap.createBitmap(tx.getWidth(),tx.getHeight(),Bitmap.Config.ARGB_8888);
        Bitmap bitmap = Bitmap.createBitmap((int) (ww*8), (int) (ww*10),Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor( Color.rgb( 100,129,182 ) );
        pincel.setColor( Color.rgb( 123,163,213));
           tx = Bitmap.createScaledBitmap(tx,ww*8,ww*10, false);

        canvas.drawBitmap( tx,0,0,pincel );


        canvas.drawBitmap( img,ww,ww*4,pincel );



        return bitmap;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getTexturaVirada(Context context,Bitmap imag,Bitmap tx) {
        float s =context.getResources().getDisplayMetrics().density;
        int ww = (int) (100*s);

        Bitmap  img = Bitmap.createScaledBitmap(imag, (int) (ww*2.0f), (int) (ww*2.0f), false);

        Paint pincel = new Paint(  );


        Bitmap bitmap = Bitmap.createBitmap((int) (ww*8), (int) (ww*10),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        // canvas.drawColor( Color.rgb( 100,129,182 ) );
        pincel.setColor( Color.rgb( 123,163,213));

          tx = Bitmap.createScaledBitmap(tx, (int) (ww*8.0f), (int) (ww*10), false);

        canvas.drawBitmap( tx,0,0,pincel );


        canvas.drawBitmap( img, (float) (ww*0.2),ww*4,pincel );



        return bitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getBitmap(String palavra) {
        int largura=0;
        switch (palavra.length()){
            case 1:
                largura=200;
                break;

            case 2:
                largura=410;
                break;

            default:
                largura=600;
                break;

        }

        Paint pincel = new Paint(  );

        int r =(int) (175 * Math.random()) + 20;
        int g=(int) (105 * Math.random()) + 80;
        int b = (int) (175 * Math.random()) + 80;
        pincel.setColor( Color.rgb( r,g,b));


        Bitmap bitmap = Bitmap.createBitmap(largura,300,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawRoundRect( 0 ,0 ,
                largura,300,
                70,70,pincel );

        pincel.setColor( Color.argb( 255,255,255,255));
        pincel.setTextSize( (float) (250) );
        canvas.drawText( palavra,10,270,pincel);

        return bitmap;
    }









    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getBitmap(float h, float w, long recordeVelhoD, long PalavrasConquistadas) {

        Paint pincel = new Paint();
        pincel.setColor( Color.argb( 120,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80 ) );


        Bitmap bitmap = Bitmap.createBitmap( (int)w,(int)h,Bitmap.Config.ARGB_8888 );
        Canvas canvas = new Canvas( bitmap );

            pincel.setColor( Color.argb( 255,55,130,240 ) );

            canvas.drawRect( 0,0,w,h,pincel );

            pincel.setTextSize( w / 100 * 9 );
            pincel.setColor( Color.argb( 255,255,250,250 ) );
            DecimalFormat formato = new DecimalFormat( "##.###" );
            Number numConvertido = 0;
            try {
                numConvertido = formato.parse( "" +recordeVelhoD );
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            canvas.drawText( "Maximo anterior  ",(float) ((float) w * 0.1),(float) (h * 0.4),pincel );
            canvas.drawText( numConvertido.toString() + " ",(float) ((float) w * 0.1),(float) (h * 0.53),pincel );

            canvas.drawText( "Atual ",(float) ((float)w * 0.1),(float) (h * 0.66),pincel);
            canvas.drawText( PalavrasConquistadas + " ",(float) ((float) w * 0.1),(float) (h * 0.83),pincel );




            pincel.setTextSize( (w / 100) * 12 );
            pincel.setColor( Color.argb( 255,255,250,250 ) );
            canvas.drawText( "Tente de novo ",(float) ((float) w * 0.1),(float) (h * 0.2),pincel );
            pincel.setTextSize( (w / 100) * 5 );


        return bitmap;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getBitmapPlacarMoedas(Bitmap img, int qtd, DisplayMetrics displayMetrics) {
        float s =displayMetrics.density;
        int ww = (int) (100*s);
        int hh = (int) (100*s);
        Paint pincel = new Paint(  );
        Bitmap bitmap = Bitmap.createBitmap(8*ww,3*ww,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);

        pincel.setColor( Color.argb( 255,255,155,155));

        canvas.drawRoundRect( ww*0.5f,
                ww*0.2f,
                ww*7.3f,
                ww*2.8f,
                ww*0.8f, ww*0.8f, pincel );

        img= Bitmap.createScaledBitmap( img, ww*3,hh*4,false);

        canvas.drawBitmap( img,0,0,pincel );
        pincel.setColor( Color.argb( 255,255,255,255));
        pincel.setTextSize( (float) (ww*1.8f) );
        canvas.drawText( String.valueOf(qtd),ww*2.5f,ww*2.7f,pincel);
        return bitmap;
    }


    public static Bitmap getBitmapPlacar(Bitmap img,int acerto,int erro) {
        int largura=600;


        Paint pincel = new Paint(  );


        Bitmap bitmap = Bitmap.createBitmap(largura,300,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);



        canvas.drawBitmap( img,0,0,pincel );




        pincel.setColor( Color.argb( 255,0,195,90));
        pincel.setTextSize( (float) (70) );
        canvas.drawText( "Acertos",10,70,pincel);
        pincel.setTextSize( (float) (220) );
        canvas.drawText( String.valueOf(acerto),10,270,pincel);

        pincel.setColor( Color.argb( 255,255,0,0));
        pincel.setTextSize( (float) (70) );
        canvas.drawText( "Erros",300,70,pincel);
        pincel.setTextSize( (float) (220) );
        canvas.drawText( String.valueOf(erro),300,270,pincel);
        return bitmap;
    }


    public static Bitmap getBitmapBase(int x , int y,Bitmap seta,String vectorDrawable, int t, int c) {
        int largura=840;
        int p=30;


        Paint pincel = new Paint(  );
        if(c==0) {
            pincel.setColor( Color.rgb( 140,205,205 ) );
        }else  if(c==1) {

            pincel.setColor( Color.rgb( 240,105,55 ) );

        }else  if(c==2) {

            pincel.setColor( Color.argb( 240,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80));

        }else  if(c==3) {

            pincel.setColor( Color.argb( 0,(int) (175 * Math.random()) + 20,(int) (105 * Math.random()) + 80,(int) (175 * Math.random()) + 80));

        }


        Bitmap bitmap = Bitmap.createBitmap(largura,400,Bitmap.Config.ARGB_8888);


        Canvas canvas = new Canvas(bitmap);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect( 0 ,0 ,
                    largura,300,
                    0,0,pincel );
        }


        pincel.setColor( Color.argb( 255,255,255,255));
        pincel.setTextSize( t );
        canvas.drawText( vectorDrawable,p,180,pincel);
        canvas.drawBitmap( seta,x,y,pincel );

        return bitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getCarimbo(Context context, Bitmap imag, int x,int y,int w,int h) {
        Bitmap  img = Bitmap.createScaledBitmap(imag,imag.getWidth(),imag.getHeight(), false);

        Paint pincel = new Paint(  );


        Bitmap bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);



        Canvas canvas = new Canvas(bitmap);
     //   canvas.drawColor( Color.rgb( 255,255,255 ) );

        pincel.setColor( Color.rgb( 123,163,213));
        canvas.drawBitmap( img,x,y,pincel );



        return bitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap getCarimboMolde(Context context, Bitmap imag, int x,int y,int w,int h) {
        Bitmap  img = Bitmap.createScaledBitmap(imag,imag.getWidth(),imag.getHeight(), false);

        Paint pincel = new Paint(  );


        Bitmap bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);



        Canvas canvas = new Canvas(bitmap);
        //   canvas.drawColor( Color.rgb( 255,255,255 ) );

        pincel.setColor( Color.rgb( 123,163,213));
        canvas.drawBitmap( img,x,y,pincel );



        return bitmap;
    }



}














