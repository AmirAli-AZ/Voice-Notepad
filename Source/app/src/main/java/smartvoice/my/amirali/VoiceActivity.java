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
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.speech.SpeechRecognizer;
import android.speech.RecognizerIntent;
import android.speech.RecognitionListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.content.Intent;
import android.net.Uri;
import android.content.ClipData;
import java.util.Timer;
import java.util.TimerTask;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Typeface;
import com.zolad.zoominimageview.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.TextView.OnEditorActionListener;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.EditorInfo;

public class VoiceActivity extends  AppCompatActivity  { 
	
	public final int REQ_CD_G_SPEECHPIC = 101;
	private Timer _timer = new Timer();
	
	private String end_sentence = "";
	private String Sentence = "";
	private String path = "";
	private String text = "";
	private double y = 0;
	private double total = 0;
	private double length = 0;
	private double num = 0;
	private String url = "";
	private String tag = "";
	private String clipdata = "";
	private String fontName = "";
	private String typeace = "";
	private boolean is_show = false;
	private String text1 = "";
	private  TextViewUndoRedo helper;
	
	private ArrayList<String> stringlist = new ArrayList<>();
	private ArrayList<String> adverbs = new ArrayList<>();
	private ArrayList<String> question_p = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout main_linear;
	private HorizontalScrollView hscroll2;
	private ScrollView vscroll1;
	private LinearLayout linear4;
	private LinearLayout tools_linear;
	private ImageView imageview9;
	private ImageView imageview8;
	private ImageView imageview6;
	private ImageView imageview4;
	private ImageView imageview3;
	private ImageView imageview2;
	private ImageView un;
	private ImageView re;
	private ImageView imageview1;
	private LinearLayout linear1;
	private EditText edittext1;
	private EditText edittext2;
	private TextView textview1;
	
	private SpeechRecognizer stt;
	private SharedPreferences save;
	private TextToSpeech tts;
	private SharedPreferences name;
	private SharedPreferences information;
	private Intent i = new Intent();
	private SharedPreferences settings;
	private Intent s = new Intent();
	private SpeechRecognizer simple;
	private SharedPreferences line;
	private Intent next = new Intent();
	private RequestNetwork req;
	private RequestNetwork.RequestListener _req_request_listener;
	private SharedPreferences dark;
	private SharedPreferences tts_S;
	private Intent G_speechPic = new Intent(Intent.ACTION_GET_CONTENT);
	private SharedPreferences size;
	private TimerTask timer;
	private ObjectAnimator animation = new ObjectAnimator();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.voice);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
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
		
		main_linear = (LinearLayout) findViewById(R.id.main_linear);
		hscroll2 = (HorizontalScrollView) findViewById(R.id.hscroll2);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		tools_linear = (LinearLayout) findViewById(R.id.tools_linear);
		imageview9 = (ImageView) findViewById(R.id.imageview9);
		imageview8 = (ImageView) findViewById(R.id.imageview8);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		un = (ImageView) findViewById(R.id.un);
		re = (ImageView) findViewById(R.id.re);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		textview1 = (TextView) findViewById(R.id.textview1);
		stt = SpeechRecognizer.createSpeechRecognizer(this);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		tts = new TextToSpeech(getApplicationContext(), null);
		name = getSharedPreferences("name", Activity.MODE_PRIVATE);
		information = getSharedPreferences("information", Activity.MODE_PRIVATE);
		settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
		simple = SpeechRecognizer.createSpeechRecognizer(this);
		line = getSharedPreferences("line", Activity.MODE_PRIVATE);
		req = new RequestNetwork(this);
		dark = getSharedPreferences("dark", Activity.MODE_PRIVATE);
		tts_S = getSharedPreferences("tts_S", Activity.MODE_PRIVATE);
		G_speechPic.setType("*/*");
		G_speechPic.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		size = getSharedPreferences("size", Activity.MODE_PRIVATE);
		
		imageview9.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				_popup("Paste", imageview9);
				return true;
				}
			 });
		
		imageview9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_getClipboardData(edittext1);
			}
		});
		
		imageview8.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				_popup("Download", imageview8);
				return true;
				}
			 });
		
		imageview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(VoiceActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.button_sheet ,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
				
				TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
				
				ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
				
				LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				
				LinearLayout e1 = (LinearLayout) bottomSheetView.findViewById(R.id.e1);
				
				final EditText kode = new EditText(VoiceActivity.this);
				kode.setHint("URL");
				kode.setSingleLine(true);
				kode.setTextSize((float)18);
				kode.setBackgroundColor(Color.TRANSPARENT);
				
				kode.setHintTextColor(0xFF607D8B);
				if (dark.getString("dark", "").equals("true")) {
					kode.setTextColor(Color.parseColor("#FFFFFF"));
				}
				else {
					kode.setTextColor(Color.parseColor("#000000"));
				}
				LinearLayout.LayoutParams linear = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				
				kode.setLayoutParams(linear);
				e1.addView(kode);
				kode.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
				t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				b1.setText("Cancel");
				b2.setText("Download");
				t1.setText("Download txt file ");
				i1.setImageResource(R.drawable.icon_download);
				if (dark.getString("dark", "").equals("true")) {
					_rippleRoundStroke(bg, "#212121", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b2, "#2196F3", "#40FFFFFF", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#212121", "#9E9E9E", 15, 2.5d, "#9E9E9E");
					b1.setTextColor(0xFFFFFFFF);
					t1.setTextColor(0xFFFFFFFF);
				}
				else {
					_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b2, "#2196F3", "#40FFFFFF", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#FFFFFF", "#EEEEEE", 15, 2.5d, "#EEEEEE");
					b1.setTextColor(0xFF000000);
					t1.setTextColor(0xFF000000);
				}
				b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						bottomSheetDialog.dismiss();
					}
				});
				b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						if (kode.getText().toString().length() > 0) {
							url = kode.getText().toString();
							if (Uri.parse(url).getLastPathSegment().contains(".")) {
								tag = Uri.parse(url).getLastPathSegment();
							}
							else {
								tag = "a";
							}
							req.startRequestNetwork(RequestNetworkController.GET, url, tag, _req_request_listener);
						}
						else {
							_custom_toast("Enter URL");
						}
						bottomSheetDialog.dismiss();
					}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
			}
		});
		
		imageview6.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				_popup("Search", imageview6);
				return true;
				}
			 });
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				is_show = true;
				linear4.setVisibility(View.VISIBLE);
				if (!(edittext1.getSelectionStart() == edittext1.getSelectionEnd())) {
					edittext2.setText(edittext1.getText().toString().substring((int)(edittext1.getSelectionStart()), (int)(edittext1.getSelectionEnd())));
				}
				edittext2.requestFocus();
				SketchwareUtil.showKeyboard(getApplicationContext());
			}
		});
		
		imageview4.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				_popup("Information", imageview4);
				return true;
				}
			 });
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				information.edit().putString("information", String.valueOf((long)(edittext1.getText().toString().length()))).commit();
				i.setAction(Intent.ACTION_VIEW);
				i.setClass(getApplicationContext(), InfoActivity.class);
				startActivity(i);
			}
		});
		
		imageview3.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				_popup("Speaker", imageview3);
				return true;
				}
			 });
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(VoiceActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.button_sheet ,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
				
				TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
				
				ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
				
				LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				
				LinearLayout e1 = (LinearLayout) bottomSheetView.findViewById(R.id.e1);
				
				final EditText kode = new EditText(VoiceActivity.this);
				kode.setHint("File name here..");
				kode.setSingleLine(true);
				kode.setTextSize((float)18);
				kode.setBackgroundColor(Color.TRANSPARENT);
				
				kode.setHintTextColor(0xFF607D8B);
				if (dark.getString("dark", "").equals("true")) {
					kode.setTextColor(Color.parseColor("#FFFFFF"));
				}
				else {
					kode.setTextColor(Color.parseColor("#000000"));
				}
				LinearLayout.LayoutParams linear = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				
				kode.setLayoutParams(linear);
				e1.addView(kode);
				kode.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
				t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
				b1.setText("Cancel");
				b2.setText("Save");
				t1.setText("Save the audio file ");
				i1.setImageResource(R.drawable.speaker);
				if (dark.getString("dark", "").equals("true")) {
					_rippleRoundStroke(bg, "#212121", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b2, "#2196F3", "#40FFFFFF", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#212121", "#9E9E9E", 15, 2.5d, "#9E9E9E");
					b1.setTextColor(0xFFFFFFFF);
					t1.setTextColor(0xFFFFFFFF);
				}
				else {
					_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
					_rippleRoundStroke(b2, "#2196F3", "#40FFFFFF", 15, 0, "#000000");
					_rippleRoundStroke(b1, "#FFFFFF", "#EEEEEE", 15, 2.5d, "#EEEEEE");
					b1.setTextColor(0xFF000000);
					t1.setTextColor(0xFF000000);
				}
				b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						bottomSheetDialog.dismiss();
					}
				});
				b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						if (kode.getText().toString().length() > 0) {
							if (FileUtil.isExistFile(tts_S.getString("storage_location", ""))) {
								tts.setPitch((float)Double.parseDouble(tts_S.getString("pitch", "")));
								tts.setSpeechRate((float)Double.parseDouble(tts_S.getString("rate", "")));
								tts.speak(edittext1.getText().toString(), TextToSpeech.QUEUE_ADD, null);
								path = tts_S.getString("storage_location", "").concat(kode.getText().toString().concat(".mp3"));
								java.io.File myfile = new
								java.io.File(path);
								FileUtil.writeFile(path, "");
								tts.synthesizeToFile(edittext1.getText(), null, myfile, "tts");
								_custom_toast("The file was saved in".concat(tts_S.getString("storage_location", "")));
							}
							else {
								_custom_toast("not exist ".concat(tts_S.getString("storage_location", "")));
							}
						}
						bottomSheetDialog.dismiss();
					}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
			}
		});
		
		imageview2.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				_popup("Save", imageview2);
				return true;
				}
			 });
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				FileUtil.writeFile(save.getString("save", ""), edittext1.getText().toString());
				_custom_toast("File saved");
			}
		});
		
		un.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				_popup("Undo", un);
				return true;
				}
			 });
		
		un.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (helper.getCanUndo()){
					    helper.undo();
				}else{
					     _custom_toast("Can't Undo");
				}
			}
		});
		
		re.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				_popup("Redo", re);
				return true;
				}
			 });
		
		re.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (helper.getCanRedo()){
					    helper.redo();
				}else{
					    _custom_toast("Can't Redo");
				}
			}
		});
		
		imageview1.setOnLongClickListener(new View.OnLongClickListener() {
			 @Override
				public boolean onLongClick(View _view) {
				_popup("start listening", imageview1);
				return true;
				}
			 });
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (settings.getString("settings", "").equals("pro")) {
					Intent _intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
					_intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
					_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
					_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
					stt.startListening(_intent);
				}
				else {
					if (settings.getString("settings", "").equals("simple")) {
						Intent _intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
						_intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
						_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
						_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
						simple.startListening(_intent);
					}
					else {
						if (settings.getString("settings", "").equals("google")) {
							Intent intent = new Intent(android.speech.RecognizerIntent.ACTION_RECOGNIZE_SPEECH); intent.putExtra(android.speech.RecognizerIntent.EXTRA_LANGUAGE_MODEL, android.speech.RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); intent.putExtra(android.speech.RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); intent.putExtra(android.speech.RecognizerIntent.EXTRA_PROMPT, "Speak Now");
							try { startActivityForResult(intent, REQ_CODE_SPEECH_INPUT); }
							catch (ActivityNotFoundException a) {
								Toast.makeText(getApplicationContext(), "There was an error", Toast.LENGTH_SHORT).show(); }
						}
						else {
							
						}
					}
				}
			}
		});
		
		edittext2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		edittext2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				text = edittext1.getText().toString();
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
				edittext1.setText(spannable1);
				if (!edittext2.getText().toString().equals("")) {
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
		
		stt.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle _param1) {
			}
			@Override
			public void onBeginningOfSpeech() {
			}
			@Override
			public void onRmsChanged(float _param1) {
			}
			@Override
			public void onBufferReceived(byte[] _param1) {
			}
			@Override
			public void onEndOfSpeech() {
			}
			@Override
			public void onPartialResults(Bundle _param1) {
			}
			@Override
			public void onEvent(int _param1, Bundle _param2) {
			}
			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);
				_write(_result);
			}
			
			@Override
			public void onError(int _param1) {
				final String _errorMessage;
				switch (_param1) {
					case SpeechRecognizer.ERROR_AUDIO:
					_errorMessage = "audio error";
					break;
					case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
					_errorMessage = "speech timeout";
					break;
					case SpeechRecognizer.ERROR_NO_MATCH:
					_errorMessage = "speech no match";
					break;
					case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
					_errorMessage = "recognizer busy";
					break;
					case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
					_errorMessage = "recognizer insufficient permissions";
					break;
					default:
					_errorMessage = "recognizer other error";
					break;
				}
				_custom_toast(_errorMessage);
			}
		});
		
		simple.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle _param1) {
			}
			@Override
			public void onBeginningOfSpeech() {
			}
			@Override
			public void onRmsChanged(float _param1) {
			}
			@Override
			public void onBufferReceived(byte[] _param1) {
			}
			@Override
			public void onEndOfSpeech() {
			}
			@Override
			public void onPartialResults(Bundle _param1) {
			}
			@Override
			public void onEvent(int _param1, Bundle _param2) {
			}
			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);
				if (line.getString("line", "").equals("line2")) {
					edittext1.setText(edittext1.getText().toString().trim().concat(" ".concat(_result)).trim());
				}
				else {
					edittext1.setText(edittext1.getText().toString().trim().concat("\n".concat(_result)).trim());
				}
			}
			
			@Override
			public void onError(int _param1) {
				final String _errorMessage;
				switch (_param1) {
					case SpeechRecognizer.ERROR_AUDIO:
					_errorMessage = "audio error";
					break;
					case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
					_errorMessage = "speech timeout";
					break;
					case SpeechRecognizer.ERROR_NO_MATCH:
					_errorMessage = "speech no match";
					break;
					case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
					_errorMessage = "recognizer busy";
					break;
					case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
					_errorMessage = "recognizer insufficient permissions";
					break;
					default:
					_errorMessage = "recognizer other error";
					break;
				}
				_custom_toast(_errorMessage);
			}
		});
		
		_req_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (_tag.equals("a")) {
					if (line.getString("line", "").equals("line2")) {
						edittext1.setText(edittext1.getText().toString().trim().concat(" ".concat(_response)).trim());
					}
					else {
						edittext1.setText(edittext1.getText().toString().trim().concat("\n".concat(_response)).trim());
					}
				}
				else {
					FileUtil.writeFile(FileUtil.getPublicDir(Environment.DIRECTORY_DOWNLOADS).concat("/".concat(_tag)), _response);
					_custom_toast("File Downloaded");
					if (line.getString("line", "").equals("line2")) {
						edittext1.setText(edittext1.getText().toString().trim().concat(" ".concat(_response)).trim());
					}
					else {
						edittext1.setText(edittext1.getText().toString().trim().concat("\n".concat(_response)).trim());
					}
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
		_questions();
		_adverb();
		_circleRipple("#BDBDBD", re);
		_circleRipple("#BDBDBD", un);
		_circleRipple("#BDBDBD", imageview1);
		_circleRipple("#BDBDBD", imageview2);
		_circleRipple("#BDBDBD", imageview3);
		_circleRipple("#BDBDBD", imageview4);
		_circleRipple("#BDBDBD", imageview6);
		_circleRipple("#BDBDBD", imageview8);
		_circleRipple("#BDBDBD", imageview9);
		_removeScollBar(hscroll2);
		helper = new TextViewUndoRedo(edittext1);
		if (!save.getString("save", "").equals("")) {
			if (FileUtil.isExistFile(save.getString("save", ""))) {
				if (save.getString("save", "").endsWith(".java")) {
					_setHighlighter(edittext1);
					edittext1.setText(FileUtil.readFile(save.getString("save", "")));
				}
				else {
					edittext1.setText(FileUtil.readFile(save.getString("save", "")));
				}
			}
			else {
				
			}
		}
		else {
			
		}
		helper.clearHistory();
		_CheckWifiMobileData();
		if (settings.getString("settings", "").equals("")) {
			settings.edit().putString("settings", "pro").commit();
		}
		if (line.getString("line", "").equals("")) {
			line.edit().putString("line", "line2").commit();
		}
		linear4.setVisibility(View.GONE);
		EditText editText = (EditText)findViewById(R.id.edittext2); editText.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				boolean handled = false;
				if (actionId == EditorInfo.IME_ACTION_DONE) { 
					_onClick_keyboard_button();
					_hideSoftKeyboard(edittext2);
					handled = true;
				}
				return handled;
			}
		});
		_dark_mode();
		_changeActivityFont("google_sans_regular");
		edittext1.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
			
			    @Override
			    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				     return false;
				    }
			
			    @Override
			    public void onDestroyActionMode(ActionMode mode) {
				
				    }
			
			    @Override
			    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				     menu.add(0,0,0,"Capitals");
				     return true;
				    }
			
			    @Override
			    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				     switch (item.getItemId()) {
					      case 0:
					       String text3 = edittext1.getText().toString();
					       int min = 0;
					       int max = edittext1.getText().length();
					       if (edittext1.isFocused()) {
						        final int selStart = edittext1.getSelectionStart();
						        final int selEnd = edittext1.getSelectionEnd();
						
						        min = Math.max(0, Math.min(selStart, selEnd));
						        max = Math.max(0, Math.max(selStart, selEnd));
						       }
					       final String selectedText = text3.subSequence(min, max).toString();
					       edittext1.setText("");
					       edittext1.append(text3.subSequence(0, min)+selectedText.toUpperCase()+ text3.subSequence(max, text3.length()));
					   edittext1.setSelection(max);    
					     return true;
					      default:
					       break;
					     }
				     return false;
				    }
			   });
		android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
		int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		int clrs [] = {0xFF80DEEA,0xFF42A5F5};
		SketchUi= new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.RIGHT_LEFT, clrs);
		SketchUi.setCornerRadii(new float[]{
			d*70,d*70,d*70 ,d*70,d*0,d*0 ,d*0,d*0});
		linear4.setElevation(d*9);
		linear4.setBackground(SketchUi);
		is_show = false;
		if (size.getString("size", "").equals("")) {
			_SetSize(16);
		}
		if (!size.getString("size", "").equals("")) {
			edittext1.setTextSize((int)Double.parseDouble(size.getString("size", "")));
		}
		_initSlideActivity();
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
			
			androidx.core.app.NotificationCompat.Builder builder = new androidx.core.app.NotificationCompat.Builder(VoiceActivity.this, "id 1")
			.setSmallIcon(R.drawable.ic_warning_black)
			.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
			.setContentTitle("Error")
			.setContentText("An error occurred setting up storage")
			.setPriority(androidx.core.app.NotificationCompat.PRIORITY_MAX)
			.setStyle(IbStyle)
			.setAutoCancel(true);
			
			androidx.core.app.NotificationManagerCompat notificationManager = androidx.core.app.NotificationManagerCompat.from(VoiceActivity.this);
			notificationManager.notify(1, builder.build());
			tts_S.edit().putString("storage_location", FileUtil.getExternalStorageDir().concat("/Download/")).commit();
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_G_SPEECHPIC:
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
			}
			break;
			case REQ_CODE_SPEECH_INPUT:
			if (_resultCode == RESULT_OK && null != _data) {
				ArrayList<String> result = _data.getStringArrayListExtra(android.speech.RecognizerIntent.EXTRA_RESULTS);
				if (line.getString("line", "").equals("line2")) {
					edittext1.setText(edittext1.getText().toString().trim().concat(" ".concat(result.get(0))).trim());
				}
				else {
					edittext1.setText(edittext1.getText().toString().trim().concat("\n".concat(result.get(0))).trim());
				}
			}
			else {
				_custom_toast("Google Speech Recognizer Error");
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (is_show) {
			is_show = false;
			linear4.setVisibility(View.GONE);
			edittext2.setText("");
		}
		else {
			FileUtil.writeFile(save.getString("save", ""), edittext1.getText().toString());
			next.setClass(getApplicationContext(), CreateActivity.class);
			startActivity(next);
			finish();
		}
	}
	
	public void _questions () {
		stringlist.add("what");
		stringlist.add("when");
		stringlist.add("where");
		stringlist.add("how");
		stringlist.add("who");
		stringlist.add("whose");
		stringlist.add("why");
		stringlist.add("shall");
		stringlist.add("do");
		stringlist.add("did");
		stringlist.add("does");
		stringlist.add("what time");
		stringlist.add("can");
		stringlist.add("aren't");
		stringlist.add("are");
		stringlist.add("which");
		stringlist.add("for whom");
		stringlist.add("frome whom");
		stringlist.add("should");
		stringlist.add("will");
	}
	
	
	public void _adverb () {
		adverbs.add("badly");
		adverbs.add("fortunately");
		adverbs.add(" extremely well");
		adverbs.add("loudly");
		adverbs.add("truly");
		adverbs.add("happily");
		adverbs.add("sensibly");
		adverbs.add("fully");
		adverbs.add("fantastically");
		adverbs.add("publicly");
		adverbs.add("well");
		adverbs.add("with difficulty");
		adverbs.add("yearly");
		adverbs.add("much");
	}
	
	
	public void _find (final String _text) {
		end_sentence = ".";
		num = 0;
		for(int _repeat12 = 0; _repeat12 < (int)(stringlist.size()); _repeat12++) {
			if (_text.startsWith(stringlist.get((int)(num)))) {
				end_sentence = "?";
			}
			num++;
		}
	}
	
	
	public void _write (final String _words) {
		if (Locale.getDefault().getDisplayLanguage().equals("English")) {
			_find(_words);
			Sentence = _words.substring((int)(0), (int)(1)).toUpperCase().concat(_words.substring((int)(1), (int)(_words.length())));
			if (_words.startsWith("hello")) {
				if (_words.length() > 5) {
					_insert("!", "Hello", Sentence);
					_find(_words.substring((int)(6), (int)(_words.length())));
				}
			}
			else {
				if (_words.startsWith("hey")) {
					if (_words.length() > 3) {
						_insert("!", "Hey", Sentence);
						_find(_words.substring((int)(4), (int)(_words.length())));
					}
				}
				else {
					if (_words.startsWith("hi")) {
						if (_words.length() > 2) {
							_insert("!", "Hi", Sentence);
							_find(_words.substring((int)(3), (int)(_words.length())));
						}
					}
					else {
						
					}
				}
			}
			num = 0;
			for(int _repeat32 = 0; _repeat32 < (int)(adverbs.size()); _repeat32++) {
				if (_words.startsWith(adverbs.get((int)(num)))) {
					_insert(",", adverbs.get((int)(num)), Sentence);
				}
				num++;
			}
			Sentence = Sentence.concat(end_sentence);
			if (line.getString("line", "").equals("line2")) {
				edittext1.setText(edittext1.getText().toString().trim().concat(" ".concat(Sentence)).trim());
			}
			else {
				edittext1.setText(edittext1.getText().toString().trim().concat("\n".concat(Sentence)).trim());
			}
		}
		else {
			if (line.getString("line", "").equals("line2")) {
				edittext1.setText(edittext1.getText().toString().trim().concat(" ".concat(_words)).trim());
			}
			else {
				edittext1.setText(edittext1.getText().toString().trim().concat("\n".concat(_words)).trim());
			}
		}
	}
	
	
	public void _insert (final String _symbel, final String _word, final String _sentence) {
		Sentence = _sentence.substring((int)(0), (int)(_word.length())).concat(_symbel).concat(_sentence.substring((int)(_word.length()), (int)(_sentence.length())));
	}
	
	
	public void _circleRipple (final String _color, final View _v) {
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		_v.setBackground(ripdrb);
	}
	
	
	public void _CheckWifiMobileData () {
		android.net.ConnectivityManager cm = (android.net.ConnectivityManager) this.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
		android.net.NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		
		if ((activeNetwork != null)) {
			//online
			if ((activeNetwork.getType() == android.net.ConnectivityManager.TYPE_WIFI) || (activeNetwork.getType() == android.net.ConnectivityManager.TYPE_MOBILE)) {
				//online
			}
		}
		else {
			//offline
			_check_connection();
		}
	}
	
	
	public void _hideSoftKeyboard (final View _view) {
		InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE); inputMethodManager.hideSoftInputFromWindow(_view.getWindowToken(), 0);
	}
	
	
	public void _onClick_keyboard_button () {
		is_show = false;
		linear4.setVisibility(View.GONE);
		edittext2.setText("");
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
	
	
	public void _removeScollBar (final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
	}
	
	
	public void _dark_mode () {
		if (dark.getString("dark", "").equals("true")) {
			Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#212121"));
			View decor = getWindow().getDecorView();
			decor.setSystemUiVisibility(0);
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
				Window w =VoiceActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
			}
			hscroll2.setBackgroundColor(0xFF212121);
			vscroll1.setBackgroundColor(0xFF212121);
			tools_linear.setBackgroundColor(0xFF212121);
			main_linear.setBackgroundColor(0xFF212121);
			edittext1.setTextColor(0xFFFFFFFF);
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
				Window w =VoiceActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFFFFFFF);
			}
			hscroll2.setBackgroundColor(0xFFFFFFFF);
			vscroll1.setBackgroundColor(0xFFFFFFFF);
			tools_linear.setBackgroundColor(0xFFFFFFFF);
			main_linear.setBackgroundColor(0xFFFFFFFF);
			edittext1.setTextColor(0xFF000000);
		}
	}
	
	
	public void _popup (final String _text, final View _view) {
		View popupView = getLayoutInflater().inflate(R.layout.popup, null);
		
		//popup is the name of your custom view
		
		final PopupWindow popup = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		TextView text1 = popupView.findViewById(R.id.text1);
		
		LinearLayout line_popup = popupView.findViewById(R.id.line_popup);
		text1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						popup.dismiss();
					}
				});
			}
		};
		_timer.schedule(timer, (int)(3000));
		text1.setText(_text);
		line_popup.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				// nothing
				popup.dismiss();
			} });
		android.graphics.drawable.GradientDrawable round = new android.graphics.drawable.GradientDrawable ();
		round.setColor (Color.parseColor("#757575"));
		
		round.setCornerRadius (360);
		
		line_popup.setBackground (round);
		
		line_popup.setElevation(6);
		
		text1.setTextColor(0xFFFFFFFF);
		popup.setAnimationStyle(android.R.style.Animation_Dialog);
		
		popup.showAsDropDown(_view, 0,0);
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
	
	
	public void _getClipboardData (final TextView _text) {
		android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		ClipData clipData = clipboard.getPrimaryClip();
		if ((clipData != null)) {
			clipdata = clipData.getItemAt(0).getText().toString();;
			if (!(edittext1.getSelectionStart() == edittext1.getSelectionEnd())) {
				edittext1.setText(edittext1.getText().toString().replace(edittext1.getText().toString().substring((int)(edittext1.getSelectionStart()), (int)(edittext1.getSelectionEnd())), clipdata));
			}
			else {
				if (line.getString("line", "").equals("line2")) {
					_text.setText(_text.getText().toString().trim().concat(" ".concat(clipdata)).trim());
				}
				else {
					_text.setText(_text.getText().toString().trim().concat("\n".concat(clipdata)).trim());
				}
			}
		}
		else {
			_custom_toast("Clipboard is empty!");
		}
	}
	
	
	public void _check_connection () {
		final AlertDialog dialog1 = new AlertDialog.Builder(VoiceActivity.this).create();
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
		t2.setText("You need the Internet to convert speech to text");
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
	
	
	public void _changeActivityFont (final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			}
			else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				}
				else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					}
					else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			SketchwareUtil.showMessage(getApplicationContext(), "Error Loading Font");
		};
	}
	
	
	public void _G_speaker () {
	}
	public static final int REQ_CODE_SPEECH_INPUT = 1;
	{
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
	
	
	public void _SetSize (final double _value) {
		size.edit().putString("size", String.valueOf((long)(_value))).commit();
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