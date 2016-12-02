package com.allen.common.custom;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.common.R;

public class NewToast extends Toast {  
  
    public NewToast(Context context) {  
        super(context);  
    }  
      
    /**
     * 
     * @param context 
     * @param locId 位置代码 0center 1top 2buttom 3CENTER_VERTICAL 4CENTER_HORIZONTAL 5left 6right
     * @param text
     * @param duration
     * @return
     */
    public static Toast makeText(Context context, int locId, CharSequence text, int duration) {  //
        Toast result = new Toast(context);  
          
        //获取LayoutInflater对象  
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);   
        //由layout文件创建一个View对象  
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.float_text_toast, null);  
          
        layout.setPadding(30, 5, 5, 30);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);  
        lp.setMargins(10, 10, 10, 10);
        layout.setLayoutParams(lp);
        
        TextView tv=new TextView(context);
        tv.setText(text);
        tv.setLayoutParams(new LayoutParams(-2, -2));
        tv.setGravity(Gravity.CENTER);
	       
        layout.addView(tv);
        
        result.setView(layout);  
//        result.setGravity(Gravity.l, 0, 0);  
        switch(locId)
        {
        case 0:result.setGravity(Gravity.CENTER, 0, 0);break;
        case 1:result.setGravity(Gravity.TOP, 0, 0);break;
        case 2:result.setGravity(Gravity.BOTTOM, 0, 0);break;
        case 3:result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);break;
        case 4:result.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);break;
        case 5:result.setGravity(Gravity.LEFT, 0, 0);break;
        case 6:result.setGravity(Gravity.RIGHT, 0, 0);break;
        default:
        	result.setGravity(Gravity.BOTTOM, 0, 0);break;
        	
        }
        
        result.setDuration(duration);  
        return result;  
    }  
    
    public static Toast makeText(Context context, CharSequence text, int duration) {  
        Toast result = new Toast(context);  
          
        //获取LayoutInflater对象  
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);   
        //由layout文件创建一个View对象  
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.float_text_toast, null);  
          
        layout.setPadding(30, 10, 10, 30);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);  
        lp.setMargins(10, 10, 10, 10);
        layout.setLayoutParams(lp);
        
        TextView tv=new TextView(context);
        tv.setText(text);
        tv.setLayoutParams(new LayoutParams(-2, -2));
        tv.setGravity(Gravity.CENTER);
	       
        layout.addView(tv);
        
        result.setView(layout);  
        result.setGravity(Gravity.BOTTOM, 50, 0); 
        result.setDuration(duration);  
          
        return result;  
    }  
  
	public static void showToast(Context ctx,CharSequence text, int duration)
	{
		LayoutInflater inflater= (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.float_text_toast,null);
		TextView tv=new TextView(ctx);
		tv.setLayoutParams(new LayoutParams(-2, -2));
		tv.setPadding(10, 5,5, 10);
        tv.setText(text);
        tv.setLayoutParams(new LayoutParams(-2, -2));
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.WHITE);
        layout.addView(tv);
		Toast toast = new Toast(ctx);
		toast.setGravity(Gravity.BOTTOM, 12, 40);
		toast.setDuration(duration);
		toast.setView(layout);
		toast.show();
	}
	
	
}  