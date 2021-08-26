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
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.CheckBox;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.CompoundButton;
import android.view.View;
import android.graphics.Typeface;
import com.zolad.zoominimageview.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class SettingsActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	
	private String fontName = "";
	private String typeace = "";
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear;
	private LinearLayout linear23;
	private LinearLayout linear11;
	private LinearLayout linear15;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private LinearLayout linear21;
	private TextView textview1;
	private LinearLayout linear24;
	private TextView textview14;
	private ImageView imageview9;
	private TextView textview8;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private ImageView imageview4;
	private CheckBox checkbox1;
	private ImageView imageview5;
	private CheckBox checkbox2;
	private TextView textview9;
	private LinearLayout linear18;
	private TextView textview10;
	private ImageView imageview6;
	private LinearLayout linear22;
	private TextView textview13;
	private ImageView imageview8;
	
	private SharedPreferences settings;
	private SharedPreferences line;
	private Intent c = new Intent();
	private SharedPreferences dark;
	private TimerTask reload;
	private Intent t = new Intent();
	private SharedPreferences size;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.settings);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear = (LinearLayout) findViewById(R.id.linear);
		linear23 = (LinearLayout) findViewById(R.id.linear23);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		linear21 = (LinearLayout) findViewById(R.id.linear21);
		textview1 = (TextView) findViewById(R.id.textview1);
		linear24 = (LinearLayout) findViewById(R.id.linear24);
		textview14 = (TextView) findViewById(R.id.textview14);
		imageview9 = (ImageView) findViewById(R.id.imageview9);
		textview8 = (TextView) findViewById(R.id.textview8);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
		textview9 = (TextView) findViewById(R.id.textview9);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		textview10 = (TextView) findViewById(R.id.textview10);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
		linear22 = (LinearLayout) findViewById(R.id.linear22);
		textview13 = (TextView) findViewById(R.id.textview13);
		imageview8 = (ImageView) findViewById(R.id.imageview8);
		settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
		line = getSharedPreferences("line", Activity.MODE_PRIVATE);
		dark = getSharedPreferences("dark", Activity.MODE_PRIVATE);
		size = getSharedPreferences("size", Activity.MODE_PRIVATE);
		
		linear24.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(SettingsActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.list,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				final TextView title = (TextView) bottomSheetView.findViewById(R.id.title);
				final TextView text1 = (TextView) bottomSheetView.findViewById(R.id.text1);
				final TextView text2 = (TextView) bottomSheetView.findViewById(R.id.text2);
				final TextView text3 = (TextView) bottomSheetView.findViewById(R.id.text3);
				final ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
				final ImageView i2 = (ImageView) bottomSheetView.findViewById(R.id.i2);
				final ImageView i3 = (ImageView) bottomSheetView.findViewById(R.id.i3);
				final ImageView check1 = (ImageView) bottomSheetView.findViewById(R.id.check1);
				final ImageView check2 = (ImageView) bottomSheetView.findViewById(R.id.check2);
				final ImageView check3 = (ImageView) bottomSheetView.findViewById(R.id.check3);
				final LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				final LinearLayout b1 = (LinearLayout) bottomSheetView.findViewById(R.id.b1);
				final LinearLayout b2 = (LinearLayout) bottomSheetView.findViewById(R.id.b2);
				final LinearLayout b3 = (LinearLayout) bottomSheetView.findViewById(R.id.b3);
				title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				text1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				text2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				text3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				if (settings.getString("settings", "").equals("")) {
					settings.edit().putString("settings", "pro").commit();
				}
				if (settings.getString("settings", "").equals("pro")) {
					_VISIBLE(check1);
					_GONE(check2);
					_GONE(check3);
				}
				else {
					if (settings.getString("settings", "").equals("simple")) {
						_VISIBLE(check2);
						_GONE(check3);
						_GONE(check1);
					}
					else {
						if (settings.getString("settings", "").equals("google")) {
							_VISIBLE(check3);
							_GONE(check1);
							_GONE(check2);
						}
					}
				}
				if (dark.getString("dark", "").equals("true")) {
					_rippleRoundStroke(bg, "#212121", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#212121", "#BDBDBD", 10, 0, "#000000");
					_rippleRoundStroke(b2, "#212121", "#BDBDBD", 10, 0, "#000000");
					_rippleRoundStroke(b3, "#212121", "#BDBDBD", 10, 0, "#000000");
					title.setTextColor(0xFFFFFFFF);
					text1.setTextColor(0xFFFFFFFF);
					text2.setTextColor(0xFFFFFFFF);
					text3.setTextColor(0xFFFFFFFF);
				}
				else {
					_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#FFFFFF", "#BDBDBD", 10, 0, "#000000");
					_rippleRoundStroke(b2, "#FFFFFF", "#BDBDBD", 10, 0, "#000000");
					_rippleRoundStroke(b3, "#FFFFFF", "#BDBDBD", 10, 0, "#000000");
					title.setTextColor(0xFF000000);
					text1.setTextColor(0xFF000000);
					text2.setTextColor(0xFF000000);
					text3.setTextColor(0xFF000000);
				}
				b1.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (settings.getString("settings", "").equals("pro")) {
							bottomSheetDialog.dismiss();
						}
						else {
							_GONE(check3);
							_GONE(check2);
							_VISIBLE(check1);
						}
						settings.edit().putString("settings", "pro").commit();
					}
				});
				b2.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (settings.getString("settings", "").equals("simple")) {
							bottomSheetDialog.dismiss();
						}
						else {
							_GONE(check1);
							_GONE(check3);
							_VISIBLE(check2);
						}
						settings.edit().putString("settings", "simple").commit();
					}
				});
				b3.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (settings.getString("settings", "").equals("google")) {
							bottomSheetDialog.dismiss();
						}
						else {
							_GONE(check2);
							_GONE(check1);
							_VISIBLE(check3);
						}
						settings.edit().putString("settings", "google").commit();
					}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
			}
		});
		
		checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2)  {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					line.edit().putString("line", "line2").commit();
					checkbox2.setChecked(false);
				}
				else {
					checkbox2.setChecked(true);
				}
			}
		});
		
		checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2)  {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					line.edit().putString("line", "line3").commit();
					checkbox1.setChecked(false);
				}
				else {
					checkbox1.setChecked(true);
				}
			}
		});
		
		linear18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				t.setAction(Intent.ACTION_VIEW);
				t.setClass(getApplicationContext(), TtsSettingsActivity.class);
				startActivity(t);
			}
		});
		
		linear22.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(SettingsActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_edittext,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				/*

برای اینکه تعریف view ها رسمی بشه اولشون final رو اضافه می کنیم 
*/
				final TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				final TextView t2 = (TextView) bottomSheetView.findViewById(R.id.t2);
				
				final ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
				
				final LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				
				final LinearLayout linear5 = (LinearLayout) bottomSheetView.findViewById(R.id.linear5);
				
				final SeekBar seekbar1 = (SeekBar)
				bottomSheetView.findViewById(R.id.seekbar1);
				t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				t1.setText("Set Text Size");
				i1.setImageResource(R.drawable.fontsize);
				_ChangeSeekBarColor(seekbar1, "#2196F3", "#03A9F4");
				if (size.getString("size", "").equals("")) {
					_setSize(16);
				}
				if (!size.getString("size", "").equals("")) {
					_setProgress(seekbar1, Double.parseDouble(size.getString("size", "")));
					t2.setText(String.valueOf((long)(Double.parseDouble(size.getString("size", "")))));
				}
				if (dark.getString("dark", "").equals("true")) {
					_rippleRoundStroke(bg, "#212121", "#000000", 15, 0, "#000000");
					t1.setTextColor(0xFFFFFFFF);
					t2.setTextColor(0xFFFFFFFF);
				}
				else {
					_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
					t1.setTextColor(0xFF000000);
					t2.setTextColor(0xFF000000);
				}
				seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
								@Override
								public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
										final int _progressValue = _param2;
						_setSize(_progressValue);
						t2.setText(String.valueOf((long)(_progressValue)));
								}
								@Override
								public void onStartTrackingTouch(SeekBar _param1) {
										//Code To Run When Slider Moved
								}
								
								@Override
								public void onStopTrackingTouch(SeekBar _param2) {
										//Code To Run When Slider Stopped Tracking Touch
								}
						});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
			}
		});
	}
	
	private void initializeLogic() {
		_dark_mode();
		if (line.getString("line", "").equals("")) {
			line.edit().putString("line", "line2").commit();
		}
		if (line.getString("line", "").equals("line3")) {
			checkbox2.setChecked(true);
		}
		else {
			checkbox1.setChecked(true);
		}
		_googleSans();
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
	public void _setBackground (final View _view, final double _radius, final double _shadow, final String _color, final boolean _ripple) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setElevation((int)_shadow);
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
		}
	}
	
	
	public void _dark_mode () {
		if (dark.getString("dark", "").equals("true")) {
			vscroll1.setBackgroundColor(0xFF212121);
			_setBackground(linear12, 20, 9, "#000000", false);
			_setBackground(linear18, 20, 9, "#000000", true);
			_setBackground(linear22, 20, 9, "#000000", true);
			_setBackground(linear24, 20, 9, "#000000", true);
			_ICC(imageview4, "#FFFFFF", "#FFFFFF");
			_ICC(imageview5, "#FFFFFF", "#FFFFFF");
			textview1.setTextColor(0xFFFFFFFF);
			textview8.setTextColor(0xFFFFFFFF);
			textview9.setTextColor(0xFFFFFFFF);
			textview10.setTextColor(0xFFFFFFFF);
			textview13.setTextColor(0xFFFFFFFF);
			textview14.setTextColor(0xFFFFFFFF);
			
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFF212121);SketchUi.setCornerRadii(new float[]{
				d*0,d*0,d*0 ,d*0,d*56,d*56 ,d*56,d*56});
			linear.setElevation(d*14);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFF212121}), SketchUi, null);
			linear.setBackground(SketchUiRD);
			linear.setClickable(true);
			Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#212121"));
			View decor = getWindow().getDecorView();
			decor.setSystemUiVisibility(0);
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
				Window w =SettingsActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
			}
			linear1.setBackgroundColor(0xFF212121);
		}
		else {
			vscroll1.setBackgroundColor(0xFFFAFAFA);
			linear1.setBackgroundColor(0xFFFAFAFA);
			_setBackground(linear12, 20, 9, "#FFFFFF", false);
			_setBackground(linear18, 20, 9, "#FFFFFF", true);
			_setBackground(linear22, 20, 9, "#FFFFFF", true);
			_setBackground(linear24, 20, 9, "#FFFFFF", true);
			_ICC(imageview4, "#000000", "#FFFFFF");
			_ICC(imageview5, "#000000", "#FFFFFF");
			textview1.setTextColor(0xFF000000);
			textview8.setTextColor(0xFF000000);
			textview9.setTextColor(0xFF000000);
			textview10.setTextColor(0xFF000000);
			textview13.setTextColor(0xFF000000);
			textview14.setTextColor(0xFF000000);
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFAFAFA);SketchUi.setCornerRadii(new float[]{
				d*0,d*0,d*0 ,d*0,d*56,d*56 ,d*56,d*56});
			linear.setElevation(d*14);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFF5F5F5}), SketchUi, null);
			linear.setBackground(SketchUiRD);
			linear.setClickable(true);
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
				Window w =SettingsActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFFAFAFA);
			}
		}
	}
	
	
	public void _ICC (final ImageView _img, final String _c1, final String _c2) {
		_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
	}
	
	
	public void _googleSans () {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		textview13.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		textview14.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
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
	
	
	public void _setSize (final double _value) {
		size.edit().putString("size", String.valueOf((long)(_value))).commit();
	}
	
	
	public void _setProgress (final SeekBar _seekbar, final double _progress) {
		_seekbar.setProgress((int)_progress);
	}
	
	
	public void _ChangeSeekBarColor (final SeekBar _seekbar, final String _color1, final String _color2) {
		_seekbar.getProgressDrawable().setColorFilter(Color.parseColor(_color1), PorterDuff.Mode.SRC_IN);
		_seekbar.getThumb().setColorFilter(Color.parseColor(_color2), PorterDuff.Mode.SRC_IN);
	}
	
	
	public void _GONE (final View _view) {
		_view.setScaleX((float)(0.0d));
		_view.setScaleY((float)(0.0d));
	}
	
	
	public void _VISIBLE (final View _view) {
		_view.setScaleX((float)(1.0d));
		_view.setScaleY((float)(1.0d));
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