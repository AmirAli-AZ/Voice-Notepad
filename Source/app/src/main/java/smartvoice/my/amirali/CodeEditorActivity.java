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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.HorizontalScrollView;
import android.widget.EditText;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.content.ClipData;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.content.ClipboardManager;
import android.net.Uri;
import android.graphics.Typeface;
import com.zolad.zoominimageview.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class CodeEditorActivity extends  AppCompatActivity  { 
	
	public final int REQ_CD_FP = 101;
	
	private boolean is_show = false;
	private String text = "";
	private double length = 0;
	private double y = 0;
	private double total = 0;
	private String clipdata = "";
	private String path = "";
	private  TextViewUndoRedo helper;
	
	private ArrayList<String> str = new ArrayList<>();
	
	private LinearLayout bg;
	private ScrollView vscroll1;
	private LinearLayout linear8;
	private LinearLayout linear5;
	private LinearLayout linear1;
	private ImageView imageview1;
	private LinearLayout linear2;
	private ImageView imageview2;
	private ImageView imageview3;
	private ImageView imageview4;
	private TextView textview21;
	private LinearLayout linear4;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear10;
	private LinearLayout linear7;
	private TextView textview2;
	private TextView textview3;
	private TextView textview4;
	private TextView textview5;
	private TextView textview6;
	private TextView textview7;
	private TextView textview8;
	private TextView textview9;
	private TextView textview10;
	private TextView textview11;
	private TextView textview12;
	private TextView textview13;
	private TextView textview14;
	private TextView textview15;
	private TextView textview16;
	private TextView textview17;
	private TextView textview18;
	private TextView textview20;
	private TextView textview19;
	private EditText edittext1;
	private TextView textview1;
	
	private SharedPreferences save;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private SharedPreferences dark;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.code_editor);
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
		
		bg = (LinearLayout) findViewById(R.id.bg);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		textview21 = (TextView) findViewById(R.id.textview21);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		hscroll1 = (HorizontalScrollView) findViewById(R.id.hscroll1);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview5 = (TextView) findViewById(R.id.textview5);
		textview6 = (TextView) findViewById(R.id.textview6);
		textview7 = (TextView) findViewById(R.id.textview7);
		textview8 = (TextView) findViewById(R.id.textview8);
		textview9 = (TextView) findViewById(R.id.textview9);
		textview10 = (TextView) findViewById(R.id.textview10);
		textview11 = (TextView) findViewById(R.id.textview11);
		textview12 = (TextView) findViewById(R.id.textview12);
		textview13 = (TextView) findViewById(R.id.textview13);
		textview14 = (TextView) findViewById(R.id.textview14);
		textview15 = (TextView) findViewById(R.id.textview15);
		textview16 = (TextView) findViewById(R.id.textview16);
		textview17 = (TextView) findViewById(R.id.textview17);
		textview18 = (TextView) findViewById(R.id.textview18);
		textview20 = (TextView) findViewById(R.id.textview20);
		textview19 = (TextView) findViewById(R.id.textview19);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		textview1 = (TextView) findViewById(R.id.textview1);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		fp.setType("text/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		dark = getSharedPreferences("dark", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				FileUtil.writeFile(save.getString("save", ""), met.getText().toString());
				_custom_toast("Saved");
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (helper.getCanUndo()){
						    helper.undo();
				}else{
						     _custom_toast("Can't Undo");
				}
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (helper.getCanRedo()){
					    helper.redo();
				}else{
					    _custom_toast("Can't Redo");
				}
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_popup();
			}
		});
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol("<");
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol(">");
			}
		});
		
		textview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol("/");
			}
		});
		
		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol("=");
			}
		});
		
		textview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol("\"");
			}
		});
		
		textview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol(":");
			}
		});
		
		textview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol("@");
			}
		});
		
		textview9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol("+");
			}
		});
		
		textview10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol(";");
			}
		});
		
		textview11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol_2("()");
			}
		});
		
		textview12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol_2("{}");
			}
		});
		
		textview13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol_2("[]");
			}
		});
		
		textview14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol("?");
			}
		});
		
		textview15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol("|");
			}
		});
		
		textview16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol("_");
			}
		});
		
		textview17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol("-");
			}
		});
		
		textview18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol("&");
			}
		});
		
		textview20.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol(",");
			}
		});
		
		textview19.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_type_symbol(".");
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				text = met.getText().toString();
				Spannable spannable1 = new SpannableString(text);
				android.text.style.ForegroundColorSpan fgSpan = new android.text.style.ForegroundColorSpan(Color.RED);
				android.text.style.BackgroundColorSpan bgSpan = new android.text.style.BackgroundColorSpan(Color.YELLOW);
				length = _charSeq.length();
				y = 0;
				total = 0;
				if (text.contains(_charSeq) && (length > 0)) {
					for(int _repeat25 = 0; _repeat25 < (int)((1 + (text.length() - length))); _repeat25++) {
						if (text.substring((int)(y), (int)(y + length)).equals(_charSeq)) {
							y++;
							total++;
						}
						else {
							y++;
						}
					}
					int x = 0;
					for(int _repeat44 = 0; _repeat44 < (int)(total); _repeat44++) {
						int n = text.indexOf(_charSeq.toString(), x);
						x = n+1;
						spannable1.setSpan(android.text.style.CharacterStyle.wrap(fgSpan), n, n + _charSeq.length(), 0);
						spannable1.setSpan(android.text.style.CharacterStyle.wrap(bgSpan), n, n + _charSeq.length(), 0);
					}
				}
				met.setText(spannable1);
				if (!edittext1.getText().toString().equals("")) {
					textview1.setText(String.valueOf((long)(total)));
				}
				else {
					textview1.setText("");
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w =CodeEditorActivity.this.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF252525);
		}
		_circleRipple("#BDBDBD", imageview1);
		_circleRipple("#BDBDBD", imageview2);
		_circleRipple("#BDBDBD", imageview3);
		_circleRipple("#BDBDBD", imageview4);
		met = new SketchwareEditText(this);
		
		met.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
		
		met.setLineCountDividerEnabled(false);
		
		met.setWrapTextEnabled(false);
		
		// if only wrap text is disabled/false
		// met.setHorizontallyScrolling(true);
		
		//met.setLineNumberColor(Color.parseColor("#FFFFFF"));
		
		//met.setBackgroundColor(Color.parseColor("#212121"));
		
		// to get the edittext's content
		// met.getText()
		// just same as normal edittext
		
		//met.setText("Hello World\nHello");
		//،met.setTextColor(Color.BLACK);
		// met.setTextSize(10f);
		_setsetHorizontallyScrolling_CodeEditor(true);
		if (dark.getString("dark", "").equals("true")) {
			_SetLineNumber_Color("#FFFFFF");
			_setCodeEditorBackground("#212121");
			_setTextColor_CodeEditor("#FFFFFF");
		}
		else {
			_SetLineNumber_Color("#616161");
			_setCodeEditorBackground("#FFFFFF");
			_setTextColor_CodeEditor("#000000");
		}
		linear4.addView(met);
		// add undo and redo
		helper = new TextViewUndoRedo(met);
		if (FileUtil.isExistFile(save.getString("save", ""))) {
			if (save.getString("save", "").endsWith(".java")) {
				_setHighlighter(met);
				_SetText_CodeEditor(FileUtil.readFile(save.getString("save", "")));
			}
			else {
				_SetText_CodeEditor(FileUtil.readFile(save.getString("save", "")));
			}
		}
		helper.clearHistory();
		is_show = false;
		linear5.setVisibility(View.GONE);
		_removeScollBar(hscroll1);
		_dark_mode();
		textview21.setText(Uri.parse(save.getString("save", "")).getLastPathSegment());
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				_SetText_CodeEditor(met.getText().toString().concat("\n".concat(FileUtil.readFile(_filePath.get((int)(0))))));
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (is_show) {
			linear5.setVisibility(View.GONE);
			is_show = false;
			edittext1.setText("");
		}
		else {
			FileUtil.writeFile(save.getString("save", ""), met.getText().toString());
			finish();
		}
	}
	public void _initLibs () {
	}
	private SketchwareEditText met;
	public class SketchwareEditText extends EditText {
		
		public Rect rect;
		public Paint paint;
		public int line, baseline, lineCount, dip, c;
		public boolean lineCountDivider = true;
		
		/*
This Library was fully created by AdityaKapal362 for Sketchware educational purpose, you're not allowed to claim that this yours.
*/
		
		public SketchwareEditText(Context a) {
			super(a);
			dip = (int)getDip(2);
			rect = new Rect();
			paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setStyle(Paint.Style.FILL); paint.setColor(Color.GRAY); paint.setTextSize((float)getDip(16));
			setTextSize(16f);
			setGravity(Gravity.LEFT | Gravity.TOP);
			setBackgroundColor(Color.WHITE);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			paint.setTextSize(getTextSize());
			lineCount = getLineCount();
			baseline = 0;
			line = 0;
			
			for (int i = 0; i < lineCount; ++i) {
				baseline = getLineBounds(i, null);
				if (i == 0) {
					canvas.drawText(String.valueOf(line+1), dip, baseline, paint);
					line++;
				} else if (getText().charAt(getLayout().getLineStart(i) - 1) == '\n') {
					canvas.drawText(String.valueOf(line+1), dip, baseline, paint);
					line++;
				}
			};
			c = (int)getDip(String.valueOf(line).length()*14);
			if (lineCountDivider) {
				canvas.drawLine(c-dip, 0, c-dip, getMeasuredHeight()+baseline, paint);
			};
			super.onDraw(canvas);
			setPadding(c,0,0,0);
		}
		
		public void setLineNumberColor(int a) {
			paint.setColor(a);
			invalidate();
		}
		
		public void setLineCountDividerEnabled(boolean a) {
			lineCountDivider = a;
			invalidate();
		}
		
		public void setWrapTextEnabled(boolean a) {
			setHorizontallyScrolling(!a);
			invalidate();
		}
		
	}
	{
	}
	
	
	public void _circleRipple (final String _color, final View _v) {
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		_v.setBackground(ripdrb);
	}
	
	
	public void _SetText_CodeEditor (final String _text) {
		met.setText(_text);
	}
	
	
	public void _SetLineNumber_Color (final String _lin_color) {
		met.setLineNumberColor(Color.parseColor(_lin_color));
	}
	
	
	public void _setCodeEditorBackground (final String _bg) {
		met.setBackgroundColor(Color.parseColor(_bg));
	}
	
	
	public void _setsetHorizontallyScrolling_CodeEditor (final boolean _f_t) {
		met.setHorizontallyScrolling(_f_t);
	}
	
	
	public void _setTextColor_CodeEditor (final String _color) {
		met.setTextColor(Color.parseColor(_color));
	}
	
	
	public void _popup () {
		View popupView = getLayoutInflater().inflate(R.layout.popup_menu, null);
		
		//popup is the name of your custom view
		
		final PopupWindow popup = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		TextView txt1 = popupView.findViewById(R.id.txt1);
		
		TextView txt2 = popupView.findViewById(R.id.txt2);
		
		TextView txt3 = popupView.findViewById(R.id.txt2);
		
		TextView txt4 = popupView.findViewById(R.id.txt4);
		
		TextView txt5 = popupView.findViewById(R.id.txt5);
		
		TextView txt6 = popupView.findViewById(R.id.txt6);
		
		LinearLayout lin_popup = popupView.findViewById(R.id.lin_popup);
		
		LinearLayout lin_import = popupView.findViewById(R.id.lin_import);
		
		LinearLayout lin_search = popupView.findViewById(R.id.lin_search);
		
		LinearLayout lin_copy = popupView.findViewById(R.id.lin_copy);
		
		LinearLayout lin_paste = popupView.findViewById(R.id.lin_paste);
		
		LinearLayout lin_U = popupView.findViewById(R.id.lin_U);
		
		LinearLayout lin_L = popupView.findViewById(R.id.lin_L);
		
		LinearLayout div1 = popupView.findViewById(R.id.div1);
		
		LinearLayout div2 = popupView.findViewById(R.id.div2);
		
		LinearLayout div3 = popupView.findViewById(R.id.div3);
		
		LinearLayout div4 = popupView.findViewById(R.id.div4);
		
		LinearLayout div5 = popupView.findViewById(R.id.div5);
		
		ImageView img1 = popupView.findViewById(R.id.img1);
		
		ImageView img2 = popupView.findViewById(R.id.img2);
		
		ImageView img3 = popupView.findViewById(R.id.img3);
		
		ImageView img4 = popupView.findViewById(R.id.img4);
		
		ImageView img5 = popupView.findViewById(R.id.img5);
		
		ImageView img6 = popupView.findViewById(R.id.img6);
		android.graphics.drawable.GradientDrawable round = new android.graphics.drawable.GradientDrawable ();
		round.setColor (Color.parseColor("#303030"));
		
		round.setCornerRadius (20);
		
		lin_popup.setBackground (round);
		
		lin_popup.setElevation(8);
		div1.setBackgroundColor(0xFF424242);
		div2.setBackgroundColor(0xFF424242);
		div3.setBackgroundColor(0xFF424242);
		div4.setBackgroundColor(0xFF424242);
		div5.setBackgroundColor(0xFF424242);
		lin_import.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				startActivityForResult(fp, REQ_CD_FP);
				popup.dismiss();
			} });
		lin_search.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				is_show = true;
				linear5.setVisibility(View.VISIBLE);
				if (!(met.getSelectionStart() == met.getSelectionEnd())) {
					edittext1.setText(met.getText().toString().substring((int)(met.getSelectionStart()), (int)(met.getSelectionEnd())));
				}
				edittext1.requestFocus();
				SketchwareUtil.showKeyboard(getApplicationContext());
				popup.dismiss();
			} });
		lin_copy.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				if (met.getSelectionStart() == met.getSelectionEnd()) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", met.getText().toString()));
				}
				else {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", met.getText().toString().substring((int)(met.getSelectionStart()), (int)(met.getSelectionEnd()))));
				}
				_custom_toast("Copied");
				popup.dismiss();
			} });
		lin_paste.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				_getClipboardData(met);
				popup.dismiss();
			} });
		lin_U.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				//Upper Case
				_UpperCase(met, true);
				popup.dismiss();
			} });
		lin_L.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				//Lower Case
				_UpperCase(met, false);
				popup.dismiss();
			} });
		popup.setAnimationStyle(android.R.style.Animation_Dialog);
		
		popup.showAsDropDown(imageview4, 0,0);
	}
	
	
	public void _type_symbol (final String _symbol) {
		met.getText().insert(met.getSelectionStart(), _symbol);
	}
	
	
	public void _type_symbol_2 (final String _symbol) {
		met.getText().insert(met.getSelectionStart(), _symbol);
		met.setSelection(met.getSelectionStart()-1);
	}
	
	
	public void _setHighlighter (final TextView _view) {
		final String secondaryColor = "#2196F3";
		final String primaryColor = "#66BB6A";
		final String numbersColor = "#f6c921";
		final String quotesColor = "#ff1744";
		final String commentsColor = "#9e9e9e";
		final String charColor = "#ff5722";
		final TextView regex1 = new TextView(this);
		final TextView regex2 = new TextView(this);
		final TextView regex3 = new TextView(this);
		final TextView regex4 = new TextView(this);
		final TextView regex5 = new TextView(this);
		final TextView regex6 = new TextView(this);
		final TextView regex7 = new TextView(this);
		final TextView regex8 = new TextView(this);
		final TextView regex9 = new TextView(this);
		final TextView regex10 = new TextView(this);
		final TextView regex11 = new TextView(this);
		
		regex1.setText("\\b(out|print|println|valueOf|toString|concat|equals|for|while|switch|getText");
		
		regex2.setText("|println|printf|print|out|parseInt|round|sqrt|charAt|compareTo|compareToIgnoreCase|concat|contains|contentEquals|equals|length|toLowerCase|trim|toUpperCase|toString|valueOf|substring|startsWith|split|replace|replaceAll|lastIndexOf|size)\\b");
		
		regex3.setText("\\b(public|private|protected|void|switch|case|class|import|package|extends|Activity|TextView|EditText|LinearLayout|CharSequence|String|int|onCreate|ArrayList|float|if|else|static|Intent|Button|SharedPreferences");
		
		regex4.setText("|abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|goto|if|implements|import|instanceof|interface|long|native|new|package|private|protected|");
		
		regex5.setText("public|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while|true|false|null)\\b");
		
		regex6.setText("\\b([0-9]+)\\b");
		
		regex7.setText("(\\w+)(\\()+");
		
		regex8.setText("\\@\\s*(\\w+)");
		
		regex9.setText("\"(.*?)\"|'(.*?)'");
		
		regex10.setText("/\\*(?:.|[\\n\\r])*?\\*/|//.*");
		
		regex11.setText("\\b(Uzuakoli|Amoji|Bright|Ndudirim|Ezinwanne|Lightworker|Isuochi|Abia|Ngodo)\\b");
		_view.addTextChangedListener(new TextWatcher() {
			ColorScheme keywords1 = new ColorScheme(java.util.regex.Pattern.compile(regex1.getText().toString().concat(regex2.getText().toString())), Color.parseColor(secondaryColor));
			
			ColorScheme keywords2 = new ColorScheme(java.util.regex.Pattern.compile(regex3.getText().toString().concat(regex4.getText().toString().concat(regex5.getText().toString()))), Color.parseColor(primaryColor));
			
			ColorScheme keywords3 = new ColorScheme(java.util.regex.Pattern.compile(regex6.getText().toString()), Color.parseColor(numbersColor));
			
			ColorScheme keywords4 = new ColorScheme(java.util.regex.Pattern.compile(regex7.getText().toString()), Color.parseColor(secondaryColor));
			
			ColorScheme keywords5 = new ColorScheme(java.util.regex.Pattern.compile(regex9.getText().toString()), Color.parseColor(quotesColor));
			
			ColorScheme keywords6 = new ColorScheme(java.util.regex.Pattern.compile(regex10.getText().toString()), Color.parseColor(commentsColor));
			
			ColorScheme keywords7 = new ColorScheme(java.util.regex.Pattern.compile(regex8.getText().toString()), Color.parseColor(numbersColor));
			
			
			ColorScheme keywords8 = new ColorScheme(java.util.regex.Pattern.compile(regex11.getText().toString()), Color.parseColor(charColor));
			
			final ColorScheme[] schemes = {keywords1, keywords2, keywords3, keywords4, keywords5, keywords6, keywords7, keywords8}; @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { } @Override public void onTextChanged(CharSequence s, int start, int before, int count) { } @Override public void afterTextChanged(Editable s) { removeSpans(s, android.text.style.ForegroundColorSpan.class); for(ColorScheme scheme : schemes) { for(java.util.regex.Matcher m = scheme.pattern.matcher(s);
					
					m.find();) { if (scheme == keywords4) { s.setSpan(new android.text.style.ForegroundColorSpan(scheme.color), m.start(), m.end()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
						} 
						else { s.setSpan(new android.text.style.ForegroundColorSpan(scheme.color), m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); } } } } void removeSpans(Editable e, Class type) { android.text.style.CharacterStyle[] spans = e.getSpans(0, e.length(), type); for (android.text.style.CharacterStyle span : spans) { e.removeSpan(span); } } class ColorScheme { final java.util.regex.Pattern pattern; final int color; ColorScheme(java.util.regex.Pattern pattern, int color) { this.pattern = pattern; this.color = color; } } });
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
	
	
	public void _removeScollBar (final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
	}
	
	
	public void _getClipboardData (final TextView _text) {
		android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		ClipData clipData = clipboard.getPrimaryClip();
		if ((clipData != null)) {
			clipdata = clipData.getItemAt(0).getText().toString();;
			if (!(met.getSelectionStart() == met.getSelectionEnd())) {
				met.setText(met.getText().toString().replace(met.getText().toString().substring((int)(met.getSelectionStart()), (int)(met.getSelectionEnd())), clipdata));
			}
			else {
				_text.setText(_text.getText().toString().concat("\n".concat(clipdata)));
			}
		}
		else {
			_custom_toast("Clipboard is empty!");
		}
	}
	
	
	public void _dark_mode () {
		if (dark.getString("dark", "").equals("true")) {
			hscroll1.setBackgroundColor(0xFF212121);
			linear8.setBackgroundColor(0xFF212121);
			linear5.setBackgroundColor(0xFF212121);
			vscroll1.setBackgroundColor(0xFF212121);
			textview2.setTextColor(0xFFFFFFFF);
			textview3.setTextColor(0xFFFFFFFF);
			textview4.setTextColor(0xFFFFFFFF);
			textview5.setTextColor(0xFFFFFFFF);
			textview6.setTextColor(0xFFFFFFFF);
			textview7.setTextColor(0xFFFFFFFF);
			textview8.setTextColor(0xFFFFFFFF);
			textview9.setTextColor(0xFFFFFFFF);
			textview10.setTextColor(0xFFFFFFFF);
			textview11.setTextColor(0xFFFFFFFF);
			textview12.setTextColor(0xFFFFFFFF);
			textview13.setTextColor(0xFFFFFFFF);
			textview14.setTextColor(0xFFFFFFFF);
			textview15.setTextColor(0xFFFFFFFF);
			textview16.setTextColor(0xFFFFFFFF);
			textview17.setTextColor(0xFFFFFFFF);
			textview18.setTextColor(0xFFFFFFFF);
			edittext1.setTextColor(0xFFFFFFFF);
			textview1.setTextColor(0xFFFFFFFF);
			textview19.setTextColor(0xFFFFFFFF);
			textview20.setTextColor(0xFFFFFFFF);
		}
		else {
			hscroll1.setBackgroundColor(0xFFFFFFFF);
			linear8.setBackgroundColor(0xFFFFFFFF);
			linear5.setBackgroundColor(0xFFFFFFFF);
			vscroll1.setBackgroundColor(0xFFFFFFFF);
			textview2.setTextColor(0xFF000000);
			textview3.setTextColor(0xFF000000);
			textview4.setTextColor(0xFF000000);
			textview5.setTextColor(0xFF000000);
			textview6.setTextColor(0xFF000000);
			textview7.setTextColor(0xFF000000);
			textview8.setTextColor(0xFF000000);
			textview9.setTextColor(0xFF000000);
			textview10.setTextColor(0xFF000000);
			textview11.setTextColor(0xFF000000);
			textview12.setTextColor(0xFF000000);
			textview13.setTextColor(0xFF000000);
			textview14.setTextColor(0xFF000000);
			textview15.setTextColor(0xFF000000);
			textview16.setTextColor(0xFF000000);
			textview17.setTextColor(0xFF000000);
			textview18.setTextColor(0xFF000000);
			edittext1.setTextColor(0xFF000000);
			textview1.setTextColor(0xFF000000);
			textview19.setTextColor(0xFF000000);
			textview20.setTextColor(0xFF000000);
		}
	}
	
	
	public void _extra () {
	}
	
	public void _UpperCase (final EditText _edittext, final boolean _t_f) {
		if (!(_edittext.getSelectionStart() == _edittext.getSelectionEnd())) {
			String textEdit = _edittext.getText().toString();
			int min = 0;
			int max = _edittext.getText().length();
			if (_edittext.isFocused()) {
				final int selStart = _edittext.getSelectionStart();
				final int selEnd = _edittext.getSelectionEnd();
				min = Math.max(0, Math.min(selStart, selEnd));
				max = Math.max(0, Math.max(selStart, selEnd));
			}
			final String selectedText = textEdit.subSequence(min, max).toString();
			_edittext.setText("");
			if (_t_f){
				_edittext.append(textEdit.subSequence(0, min)+selectedText.toUpperCase()+ textEdit.subSequence(max, textEdit.length()));
			}else{
				_edittext.append(textEdit.subSequence(0, min)+selectedText.toLowerCase()+ textEdit.subSequence(max, textEdit.length()));
			}
			_edittext.setSelection(max);
		}else{
			_custom_toast("Text Not Selected");
			if (_t_f){
				_edittext.setText(_edittext.getText().toString().toUpperCase());
			}else{
				_edittext.setText(_edittext.getText().toString().toLowerCase());
			}
		}
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