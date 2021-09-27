package smartvoice.my.amirali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.graphics.Typeface;
import com.zolad.zoominimageview.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.widget.NestedScrollView;

public class TtsSettingsActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	
	private String text1 = "";
	
	private NestedScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private EditText edittext3;
	private ImageView imageview1;
	private TextView textview1;
	private TextView textview2;
	private EditText edittext1;
	private TextView textview3;
	private EditText edittext2;
	private TextView textview4;
	private TextView textview5;
	private ImageView imageview2;
	
	private SharedPreferences dark;
	private SharedPreferences tts_S;
	private SharedPreferences selector_mode;
	private Intent i = new Intent();
	private TimerTask timer;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.tts_settings);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		vscroll1 = (NestedScrollView) findViewById(R.id.vscroll1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		edittext3 = (EditText) findViewById(R.id.edittext3);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		textview3 = (TextView) findViewById(R.id.textview3);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview5 = (TextView) findViewById(R.id.textview5);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		dark = getSharedPreferences("dark", Activity.MODE_PRIVATE);
		tts_S = getSharedPreferences("tts_S", Activity.MODE_PRIVATE);
		selector_mode = getSharedPreferences("selector_mode", Activity.MODE_PRIVATE);
		
		edittext3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (edittext3.getText().toString().contains(FileUtil.getExternalStorageDir())) {
					if (edittext3.getText().toString().endsWith("/")) {
						tts_S.edit().putString("storage_location", edittext3.getText().toString().trim().replace(FileUtil.getExternalStorageDir(), "")).commit();
						
					}
					else {
						tts_S.edit().putString("storage_location", edittext3.getText().toString().trim().concat("/").replace(FileUtil.getExternalStorageDir(), "")).commit();
						
					}
				}
				tts_S.edit().putString("name", Uri.parse(edittext3.getText().toString().trim()).getLastPathSegment()).commit();
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!edittext1.getText().toString().contains("-")) {
					tts_S.edit().putString("pitch", edittext1.getText().toString()).commit();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		edittext2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!edittext2.getText().toString().contains("-")) {
					tts_S.edit().putString("rate", edittext2.getText().toString()).commit();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				selector_mode.edit().putString("selector", "select_folder").commit();
				i.setAction(Intent.ACTION_VIEW);
				i.setClass(getApplicationContext(), CopyFileActivity.class);
				startActivity(i);
			}
		});
	}
	
	private void initializeLogic() {
		_circleRipple("#BDBDBD", imageview1);
		_initSlideActivity();
		if (dark.getString("dark", "").equals("true")) {
			_card_style(linear3, 10, 15, "#000000");
			_card_style(linear4, 10, 15, "#000000");
			_card_style(linear5, 10, 15, "#000000");
			textview1.setTextColor(0xFFFFFFFF);
			textview2.setTextColor(0xFFFFFFFF);
			textview3.setTextColor(0xFFFFFFFF);
			textview4.setTextColor(0xFFFFFFFF);
			edittext1.setTextColor(0xFFFFFFFF);
			edittext2.setTextColor(0xFFFFFFFF);
			edittext3.setTextColor(0xFFFFFFFF);
			imageview1.setImageResource(R.drawable.ic_arrow_back_white);
			linear1.setBackgroundColor(0xFF212121);
			vscroll1.setBackgroundColor(0xFF212121);
			textview5.setTextColor(0xFFFFFFFF);
			int[] colorsCRNDA2 = { Color.parseColor("#212121"), Color.parseColor("#212121") }; android.graphics.drawable.GradientDrawable CRNDA2 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNDA2);
			CRNDA2.setCornerRadii(new float[]{(int)0,(int)0,(int)0,(int)0,(int)15,(int)15,(int)15,(int)15});
			CRNDA2.setStroke((int) 0, Color.parseColor("#000000"));
			linear2.setElevation((float) 15);
			linear2.setBackground(CRNDA2);
			
			
			Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#212121"));
			View decor = getWindow().getDecorView();
			decor.setSystemUiVisibility(0);
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
				Window w =TtsSettingsActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
			}
		}
		else {
			_card_style(linear3, 10, 15, "#FFFFFF");
			_card_style(linear4, 10, 15, "#FFFFFF");
			_card_style(linear5, 10, 15, "#FFFFFF");
			textview1.setTextColor(0xFF000000);
			textview2.setTextColor(0xFF000000);
			textview3.setTextColor(0xFF000000);
			textview4.setTextColor(0xFF000000);
			edittext1.setTextColor(0xFF000000);
			edittext2.setTextColor(0xFF000000);
			edittext3.setTextColor(0xFF000000);
			imageview1.setImageResource(R.drawable.ic_arrow_back_black);
			linear1.setBackgroundColor(0xFFFFFFFF);
			vscroll1.setBackgroundColor(0xFFFFFFFF);
			textview5.setTextColor(0xFF000000);
			int[] colorsCRNDA = { Color.parseColor("#ffffff"), Color.parseColor("#ffffff") }; android.graphics.drawable.GradientDrawable CRNDA = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNDA);
			CRNDA.setCornerRadii(new float[]{(int)0,(int)0,(int)0,(int)0,(int)15,(int)15,(int)15,(int)15});
			CRNDA.setStroke((int) 0, Color.parseColor("#000000"));
			linear2.setElevation((float) 15);
			linear2.setBackground(CRNDA);
			
			
			if (Double.parseDouble(Build.VERSION.SDK) > 28) {
				Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#F5F5F5"));
			}
			else {
				Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#000000"));
			}
			View decor = getWindow().getDecorView();
			decor.setSystemUiVisibility(0);
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			getWindow().setStatusBarColor(0xFFFFFFFF);
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
				Window w =TtsSettingsActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFFFFFFF);
			}
		}
		_createChannel();
		if (tts_S.getString("pitch", "").equals("")) {
			tts_S.edit().putString("pitch", "1").commit();
		}
		if (tts_S.getString("rate", "").equals("")) {
			tts_S.edit().putString("rate", "1").commit();
		}
		if (tts_S.getString("storage_location", "").equals("")) {
			tts_S.edit().putString("storage_location", FileUtil.getExternalStorageDir().concat("/Download/")).commit();
		}
		if (tts_S.getString("name", "").equals("")) {
			tts_S.edit().putString("name", "Download").commit();
		}
		if (FileUtil.isExistFile(tts_S.getString("storage_location", ""))) {
			
		}
		else {
			text1 = "There is no ".concat(tts_S.getString("name", "").concat(" folder"));
			androidx.core.app.NotificationCompat.InboxStyle IbStyle = new androidx.core.app.NotificationCompat.InboxStyle(); 
			
			IbStyle.addLine(text1);
			IbStyle.addLine("Settings changed to default");
			IbStyle.setSummaryText("+2 more"); 
			IbStyle.setBigContentTitle("Error detected");
			
			androidx.core.app.NotificationCompat.Builder builder = new androidx.core.app.NotificationCompat.Builder(TtsSettingsActivity.this, "id 1")
			.setSmallIcon(R.drawable.ic_warning_black)
			.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
			.setContentTitle("Error")
			.setContentText("An error occurred setting up storage")
			.setPriority(androidx.core.app.NotificationCompat.PRIORITY_MAX)
			.setStyle(IbStyle)
			.setAutoCancel(true);
			
			androidx.core.app.NotificationManagerCompat notificationManager = androidx.core.app.NotificationManagerCompat.from(TtsSettingsActivity.this);
			notificationManager.notify(1, builder.build());
			tts_S.edit().putString("storage_location", FileUtil.getExternalStorageDir().concat("/Download/")).commit();
		}
		_googleSans();
		edittext1.setText(tts_S.getString("pitch", ""));
		edittext2.setText(tts_S.getString("rate", ""));
		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						textview5.setText(tts_S.getString("storage_location", ""));
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(timer, (int)(0), (int)(1000));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _card_style (final View _view, final double _shadow, final double _rounds, final String _colour) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_colour));
		gd.setCornerRadius((int)_rounds);
		_view.setBackground(gd);
		_view.setElevation((int)_shadow);
	}
	
	
	public void _googleSans () {
		
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		edittext3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
	}
	
	
	public void _initSlideActivity () {
		getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
		ViewConfiguration vc = ViewConfiguration.get(this);
		MIN_DISTANCE = vc.getScaledTouchSlop();
		
		rootView =(ViewGroup)getWindow().findViewById(Window.ID_ANDROID_CONTENT);
		//converts percent to 0-225 range .
		maxAlpha =(int) ((225.0d/100.0d)* MAX_SCRIM_ALPHA); 
		try{
			convertFromTranslucent = Activity.class.getDeclaredMethod("convertFromTranslucent");         convertFromTranslucent.setAccessible(true);
			java.lang.reflect.Method getActivityOptions = Activity.class.getDeclaredMethod("getActivityOptions"); 	getActivityOptions.setAccessible(true);
			options = getActivityOptions.invoke(this);
				Class<?>[] classes = Activity.class.getDeclaredClasses();
			 Class<?> translucentConversionListenerClazz = null;
				for (Class clazz : classes) {
						if (clazz.getSimpleName().contains("TranslucentConversionListener")) {
								translucentConversionListenerClazz = clazz; 
						} 
				} 
				 convertToTranslucent = Activity.class.getDeclaredMethod("convertToTranslucent", translucentConversionListenerClazz, ActivityOptions.class);
				convertToTranslucent.setAccessible(true);
		} catch (Exception e) {
			showMessage(e.toString());
			 }
	}
	// Custom Variables
	//You can change it to color of your choice 
	private static final int SCRIM_COLOR = 0xFF000000;
	//Alpha is not taken into consideration while calculating scrim color  so it dosent matter .
	
	private static final int  SCRIM_R = Color.red(SCRIM_COLOR);
	private static final int SCRIM_G = Color.green(SCRIM_COLOR);
	private static final int  SCRIM_B = Color.blue(SCRIM_COLOR);
	private static final int MAX_SCRIM_ALPHA= 80;
	//in percentage
	private ViewGroup rootView ;
	private boolean enableSwipe= false;
	private boolean lockSwipe = false;
	private float downX;
	private float downY;
	private float MIN_DISTANCE ;
	private int maxAlpha;
	private java.lang.reflect.Method convertFromTranslucent;
	private java.lang.reflect.Method getActivityOptions;
	
	private Object options;
	
	private java.lang.reflect.Method convertToTranslucent;
	// Detect touch Events
	 @Override public boolean dispatchTouchEvent(MotionEvent event) { 
		switch(event.getAction()) { 
			case MotionEvent.ACTION_DOWN: 
			downX = event.getRawX();
			downY =event.getRawY();
			enableSwipe = false;
			lockSwipe = false;
			//convert activity to transparent
			try {
					convertToTranslucent.invoke(this, null, options); 
			} catch (Throwable t) {
			}
			break; 
			case MotionEvent.ACTION_MOVE: 
			if (!lockSwipe){
				if(enableSwipe){
					float translation = event.getRawX() -downX - MIN_DISTANCE;
					if (translation >= rootView.getWidth() || translation<= 0){
						rootView.setTranslationX(0);
					}else{
						rootView.setTranslationX(translation);
						//calculate distance scrolled in percentage
						int distanceInPercentage =(int)( ((double)translation/(double)rootView.getWidth())*100);
						
						//calculate alpha from distance in range 0 - maxAlpha
						
						int alpha =(int) ( ((double)maxAlpha/100.0d)*distanceInPercentage);
						
						//alpha will be greater when it is scrolled more this we do not need this but we need the inverse of it so subtract it from maxAlpha
						alpha = maxAlpha - alpha;
						
						int scrimColor = Color.argb(alpha,SCRIM_R,SCRIM_G,SCRIM_B);
						
						getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(scrimColor));
					}
				}else{
					if(Math.abs(event.getRawY() - downY) >= MIN_DISTANCE){
						enableSwipe = false;
						lockSwipe = true;
					}else{
						enableSwipe = event.getRawX() -downX >= MIN_DISTANCE;
					}
				}
			}
			break; 
			case MotionEvent.ACTION_UP: 
			if(rootView.getTranslationX() > rootView.getWidth() / 5){
				rootView.animate() 
				.translationX(rootView.getWidth())
				.setListener(
				new AnimatorListenerAdapter() { 
							@Override public void onAnimationEnd(Animator animation) { 
						
							super.onAnimationEnd(animation);
						finish();
						overridePendingTransition(0, 0);
						
					} });
			}else{
				rootView.animate() 
				.translationX(0)
				.setListener(
				new AnimatorListenerAdapter() { 
							@Override public void onAnimationEnd(Animator animation) { 
						super.onAnimationEnd(animation);
						// convert activity back to normal
						try {
							 convertFromTranslucent.invoke(this);
							        } catch (Throwable t) {}
					} });
				enableSwipe =false;
				lockSwipe = false;
			}
			break; 
			default:
			enableSwipe =false;
			lockSwipe = false;
			break; 
		}
		if (enableSwipe){
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);
	}
	
	{
	}
	
	
	public void _circleRipple (final String _color, final View _v) {
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		_v.setBackground(ripdrb);
	}
	
	
	public void _createChannel () {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = "Channel name 1";
			String description = "Notification channel";
			int importance = NotificationManager.IMPORTANCE_MAX;
			NotificationChannel channel = new NotificationChannel("id 1", name, importance);
			channel.setDescription(description);
			
			NotificationManager notificationManager = getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
			
		}
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}