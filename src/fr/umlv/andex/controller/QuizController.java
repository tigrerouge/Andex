package fr.umlv.andex.controller;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.andex.data.NodeQuestion;
import fr.umlv.andex.data.Option;
import fr.umlv.andex.data.Question;
import fr.umlv.andex.data.QuizDescription;
import fr.umlv.andex.data.TreeQuestion;
import fr.umlv.andex.data.TypeQuestion;

public class QuizController {

	public List<QuizDescription> findAllQuiz(){
	
		QuizDescription des = new QuizDescription();
		des.setIdQuiz(1);
		des.setTitleQuiz("Android I");
		
		QuizDescription des2 = new QuizDescription();
		des2.setIdQuiz(1);
		des2.setTitleQuiz("Android II");
		
		QuizDescription des3 = new QuizDescription();
		des3.setIdQuiz(1);
		des3.setTitleQuiz("Android III");
		
		ArrayList<QuizDescription> list = new ArrayList<QuizDescription>();
		list.add(des);
		list.add(des2);
		list.add(des3);
		
		return list;
	}
	
	public TreeQuestion getQuizTree(long idQuiz){
		
		NodeQuestion question1 = new NodeQuestion();
		question1.setId(1);
		question1.setTitle("Niveau 0");
		NodeQuestion question2 = new NodeQuestion();
		question2.setId(2);
		question2.setTitle("Niveau 1");
		NodeQuestion question3 = new NodeQuestion();
		question3.setId(3);
		question3.setTitle("Niveau 2");
		
		NodeQuestion question1A = new NodeQuestion();
		question1A.setId(4);
		question1A.setTitle("Niveau 0");
		
		question1.setNodes(new ArrayList<NodeQuestion>());
		question1.getNodes().add(question2);
		
		question2.setNodes(new ArrayList<NodeQuestion>());
		question2.getNodes().add(question3);
		
		TreeQuestion tree = new TreeQuestion();
		tree.setNodes(new ArrayList<NodeQuestion>());
		tree.getNodes().add(question1);
		tree.getNodes().add(question1A);
		
		return tree;
	}
	
	public Question findQuestion(int id){
		
		Question question = new Question();
		question.setTitle("Titulo");
		question.setText("Questionnnnnnnnnnnnn shhehnfofm shdgr" +
				"bnekndfmm shhgvrvjslldm shahyve");
		
		Option option = new Option();
		option.setDescription("Option 1");
		option.setId(1);
		
		Option option2 = new Option();
		option2.setDescription("Option 2");
		option2.setId(2);
		
		question.getOptions().add(option);
		question.getOptions().add(option2);
		
		question.setImage("Hola".getBytes());
		TypeQuestion[] type = {TypeQuestion.TYPE_ANSWER_TEXT, TypeQuestion.TYPE_ANSWER_CHECK};
		question.setTypeAnswer(type);
		
		question.setTime(20000);
		return question;
	}
	
}
