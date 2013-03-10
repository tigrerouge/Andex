package fr.umlv.andex.view;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import fr.umlv.andex.R;
import fr.umlv.andex.controller.QuizController;
import fr.umlv.andex.data.Option;
import fr.umlv.andex.data.Question;
import fr.umlv.andex.data.TypeQuestion;

public class QuestionActivity extends Activity implements OnClickListener {

	private Question question;
	private TextView timeView;
	private final int CAMERA = 1;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		timeView = new TextView(this);
        LinearLayout layout = new LinearLayout(this); 
        layout.setOrientation(LinearLayout.VERTICAL);
        
        QuizController quizController = new QuizController();
        question = quizController.findQuestion(1);
        createView(layout);
        setContentView(layout);
	}
	
	@SuppressWarnings("unchecked")
	private void createView(LinearLayout layout){
		
		TextView title = new TextView(this);
		title.setText(question.getTitle());
		layout.addView(title);
		
		TextView description = new TextView(this);
		description.setText(question.getText());
		layout.addView(description);
		
		byte[] blob = question.getImage();
		if(blob.length>0){
			
			Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.ic_launcher);
			
			//TODO
			//Bitmap bmp = BitmapFactory.decodeByteArray(blob,0,blob.length);
		
			ImageView image = new ImageView(this);
			image.setImageBitmap(icon);
			//view.setImageBitmap(bmp);
			layout.addView(image);
		}
		
		for(TypeQuestion type:question.getTypeAnswer()){
			switch(type){
				case TYPE_ANSWER_RADIO:{
					createAnswerRadio(layout);
				};break;
				case TYPE_ANSWER_TEXT:{
					createAnswerText(layout);
				};break;
				case TYPE_ANSWER_SCHEMA:{
					createAnswerSchema(layout);
				};break;
				case TYPE_ANSWER_PHOTO:{
					createAnswerPhoto(layout);
				};break;
				case TYPE_ANSWER_CHECK:{
					createAnswerCheck(layout);
				};break;
			}
		}
		
		if(question.getTime()>0){
			
			layout.addView(timeView);
			
			DeadlineTask deadline = new DeadlineTask();
			deadline.setTimeView(timeView);
			deadline.execute(question.getTime());
		}
		
		Button save = new Button(this);
		save.setText(R.string.save);
		save.setOnClickListener(this);
		layout.addView(save);
	}
	
	private void createAnswerRadio(LinearLayout layout){
		List<Option> options  = question.getOptions();
		
		RadioGroup radioGroup = new RadioGroup(this);
		for(Option option: options){
			RadioButton radio = new RadioButton(this);
			radio.setText(option.getDescription());
			radio.setId(option.getId());
			radioGroup.addView(radio);
		}
		layout.addView(radioGroup);
	}
	
	private void createAnswerCheck(LinearLayout layout){
		List<Option> options  = question.getOptions();
	
		for(Option option: options){
			CheckBox check = new CheckBox(this);
			check.setText(option.getDescription());
			check.setId(option.getId());
			layout.addView(check);
		}
	}
	
	private void createAnswerText(LinearLayout layout){
		
		EditText answer = new EditText(this);
		layout.addView(answer);
	}
	
	private void createAnswerPhoto(LinearLayout layout){
		Button answer = new Button(this);
		answer.setText(R.string.take_photo);
		answer.setOnClickListener(this);
		layout.addView(answer);
	}
	
	private void createAnswerSchema(LinearLayout layout){
		
		Button answer = new Button(this);
		answer.setText(R.string.take_schema);
		answer.setOnClickListener(this);
		layout.addView(answer);
	}

	@Override
	public void onClick(View v) {
		
		Button button = (Button)v;
		
		String text = button.getText().toString();
		
		if(text.equals(getResources().getString(R.string.take_photo))){
			
			Intent i = new Intent(this, PhotoActivity.class);
			startActivityForResult(i, CAMERA);
			
		}else if(text.equals(getResources().getString(R.string.take_schema))){
			
		}else if(text.equals(getResources().getString(R.string.save))){
			
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			 if (requestCode == CAMERA) {
				  String path = data.getExtras().getString("PATH_PHOTO");
				  Bitmap imageBit = BitmapFactory.decodeFile(path);
				
				  int size = imageBit.getRowBytes() * imageBit.getHeight();
				  ByteBuffer b = ByteBuffer.allocate(size);
				  imageBit.copyPixelsToBuffer(b);
				  byte[] bytes = new byte[size];
				  try {
				     b.get(bytes, 0, bytes.length);
				  } catch (BufferUnderflowException e) {
				     e.printStackTrace();
				  }
				
				  question.setImageAnswer(bytes);
			}
		}
	}
}
