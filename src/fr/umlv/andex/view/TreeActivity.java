package fr.umlv.andex.view;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.umlv.andex.controller.QuizController;
import fr.umlv.andex.data.NodeQuestion;
import fr.umlv.andex.data.TreeQuestion;

public class TreeActivity extends Activity implements View.OnClickListener{
	
	private TreeQuestion tree;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this); 
        layout.setOrientation(LinearLayout.VERTICAL);
    	
        QuizController quizController = new QuizController();
        tree = quizController.getQuizTree(1);
        addTree(layout, tree.getNodes(), "-");
        setContentView(layout);
    }
    
    private void addTree(LinearLayout layout, List<NodeQuestion> nodes, String prefix){
        
    	for(NodeQuestion node :nodes){
        	
        	TextView item = new TextView(this);
        	item.setText(prefix + node.getTitle());
        	item.setId(node.getId());
        	item.setOnClickListener(this);
        	item.setTextSize(25);
        	item.setHeight(50);
        	layout.addView(item);
        	
        	if(node.isOpen()){
        		addTree(layout, node.getNodes(), "	" + prefix);
        	}
        } 
    }
    
    private void searchItem(List<NodeQuestion> list, int id){
    	
    	boolean find = false;
    	Iterator<NodeQuestion> it = list.iterator();
    	while(!find && it.hasNext()){
    		
    		NodeQuestion item = it.next();
    		if(item.getId() == id){
    			find = true;
    			if(!item.isLeaf()){
    				item.setOpen(!item.isOpen());
    			}else{
    				Intent preIntent = new Intent(this,
    	    				QuestionActivity.class);
    	    		startActivity(preIntent);
    			}
    			
    		}else{
    			searchItem(item.getNodes(), id);
    		}
    	}
    }

	@Override
	public void onClick(View v) {
		System.out.println("click");
		searchItem(tree.getNodes(), v.getId());
		LinearLayout layout = new LinearLayout(this); 
        layout.setOrientation(LinearLayout.VERTICAL);
        addTree(layout, tree.getNodes(), "-");
        setContentView(layout);
	}
}
