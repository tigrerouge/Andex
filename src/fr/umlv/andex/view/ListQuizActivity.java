package fr.umlv.andex.view;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.umlv.andex.R;
import fr.umlv.andex.controller.QuizController;
import fr.umlv.andex.data.QuizDescription;

public class ListQuizActivity extends Activity implements OnItemClickListener{

	private QuizDescription[] table;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listquiz);
        
        ListView listView = (ListView) findViewById(R.id.listquiz);
        QuizController quizController = new QuizController();
        List<QuizDescription> listQuiz = quizController.findAllQuiz();

        table = (QuizDescription[]) listQuiz.toArray(new QuizDescription[listQuiz.size()]); 
        
        ArrayAdapter<QuizDescription> adapter = new ArrayAdapter<QuizDescription>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, table);

        listView.setAdapter(adapter); 
        listView.setOnItemClickListener(this);
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		
    	try{
    		Intent preIntent = new Intent(v.getContext(),
    				TreeActivity.class);
    		startActivity(preIntent);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		 	
	}
}
