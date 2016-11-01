package com.sjsu.lab2.campusmap.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.LocationManager;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.sjsu.lab2.campusmap.R;
import com.sjsu.lab2.campusmap.model.BuildingDetail;
import com.sjsu.lab2.campusmap.model.CampusDetail;
import com.sjsu.lab2.campusmap.utility.HttpUtility;
import com.sjsu.lab2.campusmap.utility.MapUtility;

public class CanvasMap extends View{

    private Bitmap mapBitmap;
    private Context thisConext;
    double i=0,j=0;
    double currLat=0, currLng=0;
    private LocationManager lm;
    double topLeftLongitude=(-121.882140);
    double topLefttude =37.336535;
    double bottomRightLongitude = (-121.882140);
    double bottomRightLatitude = 37.336535;
    Point size;
    WindowManager wm;
    Display display;
    Bitmap resizedBitmap;
    double[] locValues = new double[]{0,0};
    ProgressDialog dialog;
    public CanvasMap(Context context) {
        super(context);
        thisConext = context;
        size = new Point();
        this.setBackgroundColor(Color.BLUE);
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        display.getSize(size);
        dialog = new ProgressDialog(context); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Fetching location. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        mapBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.campusmap);
        System.out.println(mapBitmap.getWidth()+"--------------"+mapBitmap.getHeight());
        resizedBitmap = Bitmap.createScaledBitmap(mapBitmap, size.x , (size.x*mapBitmap.getHeight())/mapBitmap.getWidth(), false);
        System.out.println(resizedBitmap.getWidth()+"--------------"+resizedBitmap.getHeight());
        // resizedBitmap = Bitmap.createScaledBitmap(mapBitmap, mapBitmap.getWidth() , mapBitmap.getHeight(), false);
    }

    public void setValues(double[] locValues) {
        this.locValues = locValues;

        /*double lat = locValues[0];
        double lng = locValues[1];
        double centerLat = 37.335818;
        double centerlng = -121.881665;
        double angle = 60.6715;

        angle = angle * Math.PI/180;

        i = (lat - centerLat)*Math.cos(angle) - (lng - centerlng)*Math.sin(angle) + centerLat;
        j = (lat - centerLat)*Math.sin(angle) - (lng - centerlng)*Math.cos(angle) + centerlng;

        i = i - 37.330872;
        j = j + 121.883021;

        //double x,y;
        i = (i* resizedBitmap.getWidth())/0.008187;
        j = (j* resizedBitmap.getHeight())/0.008203;

        System.out.println("Lat:"+lat);
        System.out.println("Lng:"+lng);

W/System.err:     at android.os.Looper.loop(Looper.java:146)
W/System.err:     at android.app.ActivityThread.main(ActivityThread.java:5487)
W/System.err:     at java.lang.reflect.Method.invokeNative(Native Method)
W/System.err:     at java.lang.reflect.Method.invoke(Method.java:515)
W/System.err:     at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1283)
W/System.err:     at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1099)
W/System.err:     at dalvik.system.NativeStart.main(Native Method)
        System.out.println("H:"+resizedBitmap.getHeight());
        System.out.println("W:"+resizedBitmap.getWidth());

        System.out.println(i+":"+j);
        //i = resizedBitmap.getWidth() - i;
        //j = resizedBitmap.getHeight() - j;*/

        currLng = locValues[1];
        currLat = locValues[0];

        i=longitudeToVPos(currLng);
        j=latitudeToHPos(currLat);
        invalidate();
    }

    public double longitudeToVPos(double currentLongitude){
        double xPosInPx = 121.886315 + currentLongitude;
        xPosInPx = (xPosInPx * 572)/(0.010143);

        double xCoordinate = xPosInPx*resizedBitmap.getWidth()/572;
        return xCoordinate;
    }

    public double latitudeToHPos(double currentLatitude){
        double yPosInPx = 37.340124 - currentLatitude;
        yPosInPx = yPosInPx *617/0.008938;

        double yCoordinate = yPosInPx*resizedBitmap.getHeight()/617;

        /*double latrad = currentLatitude*Math.PI/180;
        double mercN = Math.log(Math.tan((Math.PI/4)+(latrad/2)));

        double yPos= ((resizedBitmap.getHeight()/2)-(resizedBitmap.getWidth()*mercN/(2*Math.PI)));//(currentLatitude*Math.sin(Math.toRadians(32)));*/

        return yCoordinate;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if(currLat == 0 && currLng == 0)
            dialog.show();
        else
            dialog.hide();
        Paint white = new Paint();
        white.setColor(Color.WHITE);
        canvas.drawBitmap(resizedBitmap, 0, 0, white);
        Paint red = new Paint();
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL);
//        Toast toast = Toast.makeText(this.getContext(), ""+i+"-i-----------j-"+j+"------------------"+resizedBitmap.getWidth(), 10);
//        toast.show();
//        Paint black = new Paint();
//        black.setColor(Color.BLACK);
//        black.setStyle(Paint.Style.FILL);
        canvas.drawCircle((int)i,(int)j,16,red);
//        canvas.drawRect(i+40,j+360,500,800,red);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Compute the height required to render the view
        int width = resizedBitmap.getWidth();
        int height = resizedBitmap.getHeight();
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();
        int w = size.x;
        int h = size.y;
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                //Check if the x and y position of the touch is inside the bitmap
//                xOfYourBitmap && x < (xOfYourBitmap + yourBitmap.getWidth() y >= yOfYourBitmap && y < (yOfYourBitmap + yourBitmap.getHeight())
                if(is_Inside(x,y,31,281,109,340) )//x > (54*w)/660) && x < ((54*w)/660) + 74 && y > ((186*h)/694) && y < ((186*h)/694) + 94 )
                {
                    //Toast toast = Toast.makeText(this.getContext(), "KING", 10);
                    getBuildingDetails("KING");
                    //toast.show();
                }else if(is_Inside(x,y,208,168,305,238) )//x > ((54*w)/660) && x < ((54*w)/660) + 74 && y > ((186*h)/694) && y < ((186*h)/694) + 94 )
                {
                    getBuildingDetails("ENG");
                    //Toast toast = Toast.makeText(this.getContext(), "ENG", 10);
                    //toast.show();
                } else if(is_Inside(x,y,109,431,183,468) )//x > ((54*w)/660) && x < ((54*w)/660) + 74 && y > ((186*h)/694) && y < ((186*h)/694) + 94 )
                {
                    getBuildingDetails("YUH");
                    //Toast toast = Toast.makeText(this.getContext(), "YUH", 10);
                    //toast.show();
                } else if(is_Inside(x,y,394,210,459,253) )//x > ((54*w)/660) && x < ((54*w)/660) + 74 && y > ((186*h)/694) && y < ((186*h)/694) + 94 )
                {
                    getBuildingDetails("BBC");
                    //Toast toast = Toast.makeText(this.getContext(), "BBC", 10);
                    //toast.show();
                } else if(is_Inside(x,y,256,467,373,526) )
                {
                    getBuildingDetails("SPG");
                    //Toast toast = Toast.makeText(this.getContext(), "SPG", 10);
                    //toast.show();
                } else if(is_Inside(x,y,250,253,333,277) ) {
                    getBuildingDetails("SU");
                    //Toast toast = Toast.makeText(this.getContext(), "SU", 10);
                    //toast.show();
                }
                return true;
        }
        return false;
    }

    public void getBuildingDetails(String buildAbbr){

        new CampusDetail();
        BuildingDetail searchedBuilding = MapUtility.seacrhFor(buildAbbr);
        if(searchedBuilding == null){
        }else{
            StringBuilder sb = new StringBuilder();
            sb.append("https://maps.googleapis.com/maps/api/distancematrix/json?")
                    .append("origins="+currLat+","+currLng)
                    .append("&destinations="+searchedBuilding.getLat()+","+searchedBuilding.getLng())
                    .append("&mode=walking")
                    .append("&language=en-EN")
                    .append("&key=AIzaSyAypuJRUJOVXZob1osDMYcw7TgzbXcvYZo");
            System.out.println("Fetching: "+sb.toString());

            try {
                final HttpUtility httpUtility = new HttpUtility(thisConext);
                httpUtility.execute(sb.toString(), searchedBuilding.getAbbr());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean is_Inside(float x, float y,int startimagex, int startimagey, int endimagex, int endimagey){
        if (x >= ((startimagex*resizedBitmap.getWidth())/572) && x < ((startimagex*resizedBitmap.getWidth())/572 + (endimagex-startimagex)) && y >= ((startimagey*resizedBitmap.getHeight())/617) && y < (((startimagey*resizedBitmap.getHeight())/617) + (endimagey-startimagey))) {
            return true;
        }
        return false;
    }
}