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
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.content.SharedPreferences;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.graphics.Typeface;
import com.bumptech.glide.Glide;
import com.zolad.zoominimageview.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class NewsVnActivity extends  AppCompatActivity  { 
	
	
	private boolean is_connected = false;
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout bg;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView imageview1;
	private TextView textview1;
	private ListView listview1;
	
	private SharedPreferences dark;
	private RequestNetwork get_data;
	private RequestNetwork.RequestListener _get_data_request_listener;
	private ObjectAnimator animation = new ObjectAnimator();
	private SharedPreferences offline;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.news_vn);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		bg = (LinearLayout) findViewById(R.id.bg);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		listview1 = (ListView) findViewById(R.id.listview1);
		dark = getSharedPreferences("dark", Activity.MODE_PRIVATE);
		get_data = new RequestNetwork(this);
		offline = getSharedPreferences("offline", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		_get_data_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try {
					_Custom_Loading(false);
					listmap = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					Collections.reverse(listmap);
					offline.edit().putString("offline_data", new Gson().toJson(listmap)).commit();
					listview1.setAdapter(new Listview1Adapter(listmap));
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				} catch(Exception _e){
					    //Code To Run When Exception Thrown
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		get_data.startRequestNetwork(RequestNetworkController.GET, "https://raw.githubusercontent.com/AmirAli-AZ/MyGitHubServer/main/News.html", "a", _get_data_request_listener);
		listview1.setSelector(android.R.color.transparent);
		listview1.setVerticalScrollBarEnabled(false);
		_circleRipple("#BDBDBD", imageview1);
		if (dark.getString("dark", "").equals("true")) {
			Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#212121"));
			View decor = getWindow().getDecorView();
			decor.setSystemUiVisibility(0);
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
				Window w =NewsVnActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
			}
			imageview1.setImageResource(R.drawable.ic_arrow_back_white);
			textview1.setTextColor(0xFFFFFFFF);
			linear2.setBackgroundColor(0xFF212121);
			bg.setBackgroundColor(0xFF212121);
			linear1.setBackgroundColor(0xFF212121);
		}
		else {
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
				Window w =NewsVnActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFFFFFFF);
			}
			imageview1.setImageResource(R.drawable.ic_arrow_back_black);
			textview1.setTextColor(0xFF000000);
			bg.setBackgroundColor(0xFFFFFFFF);
			linear2.setBackgroundColor(0xFFFFFFFF);
			linear1.setBackgroundColor(0xFFFFFFFF);
		}
		_initSlideActivity();
		android.net.ConnectivityManager CM = (android.net.ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		android.net.NetworkInfo NI = CM.getActiveNetworkInfo();
		if (NI != null && NI.isConnected()) {
			is_connected = true;
			_Custom_Loading(true);
		}
		else {
			is_connected = false;
			if (offline.getString("offline_data", "").equals("")) {
				_check_connection();
			}
			else {
				listmap = new Gson().fromJson(offline.getString("offline_data", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				listview1.setAdapter(new Listview1Adapter(listmap));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _Custom_Loading (final boolean _ifShow) {
		if (_ifShow) {
			if (coreprog == null){
				coreprog = new ProgressDialog(this);
				coreprog.setCancelable(false);
				coreprog.setCanceledOnTouchOutside(false);
				
				coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
				
			}
			coreprog.setMessage(null);
			coreprog.show();
			View _view = getLayoutInflater().inflate(R.layout.loading, null);
			LinearLayout linear_base = (LinearLayout) _view.findViewById(R.id.linear_base);
			if (dark.getString("dark", "").equals("true")) {
				android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
				gd.setColor(Color.parseColor("#212121"));
				gd.setCornerRadius(35);
				linear_base.setBackground(gd);
				coreprog.setContentView(_view);
			}
			else {
				android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
				gd.setColor(Color.parseColor("#FFFFFF"));
				gd.setCornerRadius(35);
				linear_base.setBackground(gd);
				coreprog.setContentView(_view);
			}
		}
		else {
			if (coreprog != null){
				coreprog.dismiss();
			}
		}
	}
	private ProgressDialog coreprog;
	{
	}
	
	
	public void _custom_toast (final String _text) {
		if (dark.getString("dark", "").equals("true")) {
			LayoutInflater inflater = getLayoutInflater(); View toastL = inflater.inflate(R.layout.toast, null);
			LinearLayout line_toast = (LinearLayout) toastL.findViewById(R.id.line_toast);
			
			TextView text1 = (TextView) toastL.findViewById(R.id.text1);
			text1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
			text1.setText(_text);
			android.graphics.drawable.GradientDrawable ln = new android.graphics.drawable.GradientDrawable ();
			ln.setColor (Color.parseColor("#2E2F32"));
			
			ln.setCornerRadius (19);
			
			line_toast.setBackground (ln);
			
			line_toast.setElevation(6);
			text1.setTextColor(0xFFFFFFFF);
			Toast toast = new Toast(getApplicationContext()); 
			
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(toastL);
			toast.show();
		}
		else {
			LayoutInflater inflater = getLayoutInflater(); View toastL = inflater.inflate(R.layout.toast, null);
			LinearLayout line_toast = (LinearLayout) toastL.findViewById(R.id.line_toast);
			
			TextView text1 = (TextView) toastL.findViewById(R.id.text1);
			text1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
			text1.setText(_text);
			android.graphics.drawable.GradientDrawable ln = new android.graphics.drawable.GradientDrawable ();
			ln.setColor (Color.parseColor("#FFFFFF"));
			
			ln.setCornerRadius (19);
			
			line_toast.setBackground (ln);
			
			line_toast.setElevation(6);
			text1.setTextColor(0xFF000000);
			Toast toast = new Toast(getApplicationContext()); 
			
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(toastL);
			toast.show();
		}
	}
	
	
	public void _check_connection () {
		final AlertDialog dialog1 = new AlertDialog.Builder(NewsVnActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.no_internet,null); 
		dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog1.setView(inflate);
		final TextView t1 = (TextView) inflate.findViewById(R.id.t1);
		
		final TextView t2 = (TextView) inflate.findViewById(R.id.t2);
		
		final TextView b1 = (TextView) inflate.findViewById(R.id.b1);
		
		final ImageView i1 = (ImageView) inflate.findViewById(R.id.i1);
		
		final LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
		t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		i1.setImageResource(R.drawable.ghost);
		t1.setText("Check your connection");
		t2.setText("You need the internet to see the news");
		b1.setText("Wi-Fi");
		_animation_image(animation, i1);
		if (dark.getString("dark", "").equals("true")) {
			_rippleRoundStroke(bg, "#2E2F32", "#000000", 15, 0, "#000000");
			t1.setTextColor(0xFFFFFFFF);
			t2.setTextColor(0xFFFFFFFF);
		}
		else {
			_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
			t1.setTextColor(0xFF000000);
			t2.setTextColor(0xFF000000);
		}
		_rippleRoundStroke(b1, "#2196F3", "#E0E0E0", 15, 0, "#000000");
		b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				Intent intent = new Intent(Intent.ACTION_MAIN, null);
				        intent.addCategory(Intent.CATEGORY_LAUNCHER);
				        ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
				        intent.setComponent(cn);
				        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				        startActivity( intent);
				// Enable & disable wifi 
				// by Amir Ali
				final android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager)getSystemService(Context.WIFI_SERVICE);
				if (wifi.isWifiEnabled()) {
					 wifi.setWifiEnabled(false);
				}
				else {
					 wifi.setWifiEnabled(true);
				}
				dialog1.dismiss();
				_cancel_animation(animation);
			}
		});
		i1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				if (!animation.isRunning()) {
					_animation_image(animation, i1);
				}
			}
		});
		dialog1.setCancelable(true);
		dialog1.show();
	}
	
	
	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _animation_image (final ObjectAnimator _anim, final View _view) {
		_anim.setTarget(_view);
		_anim.setPropertyName("translationX");
		_anim.setFloatValues((float)(0), (float)(600));
		_anim.setDuration((int)(2000));
		_anim.setRepeatMode(ValueAnimator.REVERSE);
		_anim.setRepeatCount((int)(3));
		_anim.setInterpolator(new LinearInterpolator());
		_anim.start();
	}
	
	
	public void _cancel_animation (final ObjectAnimator _anim) {
		try {
			_anim.cancel();
		} catch(Exception _e){
			
		}
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
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.news_item, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
			
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
			if (dark.getString("dark", "").equals("true")) {
				linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)17, 0xFF000000));
				linear1.setElevation((float)8);
				textview1.setTextColor(0xFFFFFFFF);
				textview2.setTextColor(0xFFFFFFFF);
			}
			else {
				linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)17, 0xFFFFFFFF));
				linear1.setElevation((float)8);
				textview1.setTextColor(0xFF000000);
				textview2.setTextColor(0xFF000000);
			}
			if (listmap.get((int)_position).get("image").toString().equals("true")) {
				imageview1.setVisibility(View.VISIBLE);
				if (is_connected) {
					if (!listmap.get((int)_position).get("imageURL").toString().equals("")) {
						Glide.with(getApplicationContext()).load(Uri.parse(listmap.get((int)_position).get("imageURL").toString())).into(imageview1);
					}
				}
				else {
					imageview1.setImageResource(R.drawable.ghost);
				}
			}
			else {
				imageview1.setVisibility(View.GONE);
			}
			textview1.setText(listmap.get((int)_position).get("Title").toString());
			textview2.setText(listmap.get((int)_position).get("Message").toString());
			ZoomInImageViewAttacher mIvAttacter = new ZoomInImageViewAttacher();
			mIvAttacter.attachImageView(imageview1);
			mIvAttacter.setZoomReleaseAnimDuration(900);
			textview2.setClickable(true);
			android.text.util.Linkify.addLinks(textview2, android.text.util.Linkify.ALL);
			textview2.setLinkTextColor(Color.parseColor("#2196F3"));
			textview2.setLinksClickable(true);
			
			return _view;
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