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
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.HorizontalScrollView;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
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


public class TextviewerActivity extends  AppCompatActivity  { 
	
	
	private String selectedFile = "";
	private String path = "";
	private String filename = "";
	private String extension = "";
	private double n = 0;
	private String filename2 = "";
	private boolean isDark = false;
	private double zoomValue = 0;
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear5;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private TextView textview1;
	private TextView textview2;
	private ImageView imageview1;
	private ScrollView vscroll2;
	private LinearLayout linear6;
	private HorizontalScrollView hscroll1;
	private TextView textview3;
	
	private Intent d = new Intent();
	private SharedPreferences intentData;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.textviewer);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		vscroll2 = (ScrollView) findViewById(R.id.vscroll2);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		hscroll1 = (HorizontalScrollView) findViewById(R.id.hscroll1);
		textview3 = (TextView) findViewById(R.id.textview3);
		intentData = getSharedPreferences("intentData", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try{
					if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice"))) {
						path = FileUtil.getExternalStorageDir().concat("/smart voice/".concat(filename));
						extension = path.substring((int)(path.lastIndexOf(".")), (int)(path.length()));
						filename2 = path.substring((int)(0), (int)(path.lastIndexOf(".")));
						n = 1;
						while(true) {
							if (FileUtil.isExistFile(path)) {
								n++;
								path = filename2.concat(String.valueOf((long)(n)).concat(extension));
							}
							else {
								break;
							}
						}
						if (FileUtil.isExistFile(selectedFile)) {
							FileUtil.copyFile(selectedFile, path);
							_custom_toast("File Saved In Notes");
							d.setClass(getApplicationContext(), CreateActivity.class);
							startActivity(d);
							finish();
						}
					}
				}catch (Exception e) { 
					
				}
			}
		});
	}
	
	private void initializeLogic() {
		_font();
		selectedFile = intentData.getString("filepath", "");
		filename = intentData.getString("filename", "");
		zoomValue = 16;
		textview3.setTextSize((int)zoomValue);
		try{
			textview3.setText(FileUtil.readFile(selectedFile));
			textview2.setText(selectedFile);
			textview1.setText(filename);
		}catch (Exception e) {
			_custom_toast(e.getMessage());
			imageview1.setVisibility(View.GONE);
		}
		_circleRipple("#BDBDBD", imageview1);
		if (Double.parseDouble(Build.VERSION.SDK) > 27) {
			int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
			if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
				Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#212121"));
				View decor = getWindow().getDecorView();
				decor.setSystemUiVisibility(0);
				if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
					Window w =TextviewerActivity.this.getWindow();
					w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
				}
				linear1.setBackgroundColor(0xFF212121);
				textview1.setTextColor(0xFFFFFFFF);
				textview2.setTextColor(0xFFFFFFFF);
				textview3.setTextColor(0xFFFFFFFF);
				isDark = true;
			}else{
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
					Window w =TextviewerActivity.this.getWindow();
					w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFFFFFFF);
				}
				linear1.setBackgroundColor(0xFFFFFFFF);
				textview1.setTextColor(0xFF000000);
				textview2.setTextColor(0xFF000000);
				textview3.setTextColor(0xFF000000);
				isDark = false;
			}
		}
		_actionBar();
		_overlay();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _custom_toast (final String _text) {
		if (isDark) {
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
	
	
	public void _circleRipple (final String _color, final View _v) {
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		_v.setBackground(ripdrb);
	}
	
	
	public void _overlay () {
		ViewGroup parent = (ViewGroup)linear1.getParent();
		ViewGroup root = (ViewGroup) parent.getParent();
		View inflate = getLayoutInflater().inflate(R.layout.overlay, null);
		inflate.setLayoutParams(new android.widget.LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,android.widget.LinearLayout.LayoutParams.MATCH_PARENT));
		root.addView(inflate);
		LinearLayout zoomIn = inflate.findViewById(R.id.zoomIn);
		LinearLayout zoomOut = inflate.findViewById(R.id.zoomOut);
		if (isDark) {
			_rippleRoundStroke(zoomIn, "#212121", "#BDBDBD", 360, 0, "#000000");
			zoomIn.setElevation((float)10);
			_rippleRoundStroke(zoomOut, "#212121", "#BDBDBD", 360, 0, "#000000");
			zoomOut.setElevation((float)10);
		}
		else {
			_rippleRoundStroke(zoomIn, "#FFFFFF", "#BDBDBD", 360, 0, "#000000");
			zoomIn.setElevation((float)10);
			_rippleRoundStroke(zoomOut, "#FFFFFF", "#BDBDBD", 360, 0, "#000000");
			zoomOut.setElevation((float)10);
		}
		zoomIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (zoomValue < 50) {
					zoomValue++;
					textview3.setTextSize((int)zoomValue);
				}
				 }});
		zoomOut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (16 < zoomValue) {
					zoomValue--;
					textview3.setTextSize((int)zoomValue);
				}
				 }});
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
	
	
	public void _actionBar () {
		if (isDark) {
			int[] colorsCRNDA2 = { Color.parseColor("#212121"), Color.parseColor("#212121") }; android.graphics.drawable.GradientDrawable CRNDA2 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNDA2);
			CRNDA2.setCornerRadii(new float[]{(int)0,(int)0,(int)0,(int)0,(int)15,(int)15,(int)15,(int)15});
			CRNDA2.setStroke((int) 0, Color.parseColor("#000000"));
			linear2.setElevation((float) 15);
			linear2.setBackground(CRNDA2);
		}
		else {
			int[] colorsCRNDA = { Color.parseColor("#ffffff"), Color.parseColor("#ffffff") }; android.graphics.drawable.GradientDrawable CRNDA = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNDA);
			CRNDA.setCornerRadii(new float[]{(int)0,(int)0,(int)0,(int)0,(int)15,(int)15,(int)15,(int)15});
			CRNDA.setStroke((int) 0, Color.parseColor("#000000"));
			linear2.setElevation((float) 15);
			linear2.setBackground(CRNDA);
		}
	}
	
	
	public void _font () {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
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