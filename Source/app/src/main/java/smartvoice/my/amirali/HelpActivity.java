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
import java.util.HashMap;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Typeface;
import com.zolad.zoominimageview.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class HelpActivity extends  AppCompatActivity  { 
	
	
	private HashMap<String, Object> key = new HashMap<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private Button button1;
	private EditText telid;
	private EditText telmessage;
	private LinearLayout linear5;
	private TextView textview1;
	
	private RequestNetwork help_feedback;
	private RequestNetwork.RequestListener _help_feedback_request_listener;
	private SharedPreferences dark;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.help);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		button1 = (Button) findViewById(R.id.button1);
		telid = (EditText) findViewById(R.id.telid);
		telmessage = (EditText) findViewById(R.id.telmessage);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		textview1 = (TextView) findViewById(R.id.textview1);
		help_feedback = new RequestNetwork(this);
		dark = getSharedPreferences("dark", Activity.MODE_PRIVATE);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(telid.getText().toString().equals("") && telmessage.getText().toString().equals(""))) {
					if (telid.getText().toString().contains("@")) {
						key = new HashMap<>();
						key.put("username", "");
						key.put("avatar_url", "");
						key.put("content", "**User Email**\n".concat(telid.getText().toString().concat("\n**User Message**\n".concat(telmessage.getText().toString()))));
						help_feedback.setParams(key, RequestNetworkController.REQUEST_PARAM);
						help_feedback.startRequestNetwork(RequestNetworkController.POST, "webhook url", "a", _help_feedback_request_listener);
					}
					else {
						_custom_toast("Enter Email");
					}
				}
				else {
					_custom_toast("Please type something");
				}
			}
		});
		
		telmessage.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				textview1.setText("600/".concat(String.valueOf((long)(_charSeq.length()))));
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		_help_feedback_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				_custom_toast("Thanks for sending");
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				_custom_toast("Message not sent ");
			}
		};
	}
	
	private void initializeLogic() {
		_rippleRoundStroke(button1, "#2196F3", "#E0E0E0", 25, 0, "#000000");
		if (dark.getString("dark", "").equals("true")) {
			vscroll1.setBackgroundColor(0xFF212121);
			linear2.setBackgroundColor(0xFF212121);
			telid.setTextColor(0xFFFFFFFF);
			telmessage.setTextColor(0xFFFFFFFF);
			_card_style(linear3, 13, 20, "#000000");
			_card_style(linear4, 13, 20, "#000000");
			Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#212121"));
			View decor = getWindow().getDecorView();
			decor.setSystemUiVisibility(0);
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
				Window w =HelpActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
			}
		}
		else {
			vscroll1.setBackgroundColor(0xFFFFFFFF);
			linear2.setBackgroundColor(0xFFFFFFFF);
			telid.setTextColor(0xFF000000);
			telmessage.setTextColor(0xFF000000);
			_card_style(linear3, 13, 20, "#FFFFFF");
			_card_style(linear4, 13, 20, "#FFFFFF");
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
				Window w =HelpActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFFFFFFF);
			}
		}
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 1);
		telid.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		telmessage.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		_SetMax(telmessage, 600);
		_initSlideActivity();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
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
	
	
	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _card_style (final View _view, final double _shadow, final double _rounds, final String _colour) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_colour));
		gd.setCornerRadius((int)_rounds);
		_view.setBackground(gd);
		_view.setElevation((int)_shadow);
	}
	
	
	public void _SetMax (final TextView _edittext, final double _MaxLength) {
		_edittext.setFilters(new InputFilter[] { new InputFilter.LengthFilter((int) _MaxLength) });
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