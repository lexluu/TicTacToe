import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.*;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.stream.*;

/*
 * File: TicTacToe.java
 * Name: Alex Luu
 * Date: 29 May 2019
 * Desc: TicTacToe with a GUI. Human player plays against a computer
 * algorithm.
 * Versions Used: OpenJDK8, OpenJFX
 */
 
public class TicTacToe extends Application {
	Button btnTL = new Button();
    Button btnML = new Button();
    Button btnBL = new Button();
    Button btnTM = new Button();
    Button btnMM = new Button();
    Button btnBM = new Button();
    Button btnTR = new Button();
    Button btnMR = new Button();
    Button btnBR = new Button();
    @Override
    public void start(Stage primaryStage) {
	
		//Set button values
		//Top Left
        btnTL.setText("");
        btnTL.setOnAction(event->{
			PlayGame(0, btnTL);
			});
			
		//Middle Left
        btnML.setText("");
        btnML.setOnAction(event->{
			PlayGame(1, btnML);
			});
			
		//Bottom Left	
        btnBL.setText("");
        btnBL.setOnAction(event->{
			PlayGame(2, btnBL);
			});
		
		//Top Middle
        btnTM.setText("");
        btnTM.setOnAction(event->{
			PlayGame(3, btnTM);
			});
			
		//Middle Middle
        btnMM.setText("");
        btnMM.setOnAction(event->{
			PlayGame(4, btnMM);
			});
			
		//Bottom Middle
        btnBM.setText("");
        btnBM.setOnAction(event->{
			PlayGame(5, btnBM);
			});	
			
		//Top Right
        btnTR.setText("");
        btnTR.setOnAction(event->{
			PlayGame(6, btnTR);
			});
			
		//Middle Right
        btnMR.setText("");
        btnMR.setOnAction(event->{
			PlayGame(7, btnMR);
			});
		
		//Bottom Right
        btnBR.setText("");
        btnBR.setOnAction(event->{
			PlayGame(8, btnBR);
			});
					
        //Make grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setGridLinesVisible(true);
        grid.setPadding(new Insets(30,30,30,30));
        
        //Set buttons in grid
        //Left buttons
        grid.add(btnTL, 0, 0);
        grid.add(btnML, 0, 1);
        grid.add(btnBL, 0, 2);
        
        //Middle buttons
        grid.add(btnTM, 1, 0);
        grid.add(btnMM, 1, 1);
        grid.add(btnBM, 1, 2);
        
        //Right buttons
        grid.add(btnTR, 2, 0);
        grid.add(btnMR, 2, 1);
        grid.add(btnBR, 2, 2);
          
        //Set the board
        Scene scene = new Scene(grid, 400, 400);
        scene.getStylesheets()
		 .add(TicTacToe.class.getResource("style.css").toExternalForm());
        
        primaryStage.setTitle("Tic Tac Toe!");
        primaryStage.setScene(scene);
        primaryStage.show();        
    }
 
	static int[] Player1 = new int[9];
	static int[] Player2 = new int[9];
	static int[] FullBoard = new int[9];  
    int ActivePlayer = 1;
    
    //change buttons used in the game
    void PlayGame(int CellID, Button Clicked){		
		if(ActivePlayer==1){
			Clicked.setText("X");
			Player1[CellID] = 1;
			FullBoard[CellID] = 1;
			ActivePlayer = 2;
			AutoPlay();
		}
		else if(ActivePlayer==2){
			Clicked.setText("O");
			Player2[CellID] = 1;
			FullBoard[CellID] = 1;
			ActivePlayer = 1;	
		}
		Clicked.setDisable(true);
		boolean winner= Winner();
		if(winner == true){
			return;
		}
	}
	
	//Set cases for AutoPlay so I don't have to type it twice
	Button SelectedButton(int CellID){
		switch(CellID){
			case 0:
				return btnTL;
			case 1:
				return btnML;
			case 2:
				return btnBL;
			case 3:
				return btnTM;
			case 4:
				return btnMM;
			case 5:
				return btnBM;
			case 6:
				return btnTR;
			case 7:
				return btnMR;
			case 8:
				return btnBR;			
		}
		return btnBR;
	}
	
	//Winning patterns for AutoPlay and winner determination
	int[][] WinningPatterns = {
		{1,1,1,0,0,0,0,0,0}, {0,0,0,1,1,1,0,0,0}, {0,0,0,0,0,0,1,1,1},
		{1,0,0,1,0,0,1,0,0}, {0,1,0,0,1,0,0,1,0}, {0,0,1,0,0,1,0,0,1},
		{1,0,0,0,1,0,0,0,1}, {0,0,1,0,1,0,1,0,0}
	};
	
	//determines winner
	boolean hasOne(int[] player){
		
		int[] judge = new int[9];
		for(int[] i : WinningPatterns){			
			for(int j = 0; j < i.length; j++){
				if(player[j]==1 && i[j]==1){
					judge[j]=1;
				} else {
					judge[j]=0;
				}
			}			
			for(int[] k: WinningPatterns){
				if(Arrays.equals(k, judge) == true){
					return true;
				}
			}
		}
		return false;	
	}
	
	//determines winner using hasOne method
	boolean Winner(){
		int Winner = -1;
		if(hasOne(Player1)==true){
			Winner = 1;
		}
		else if(hasOne(Player2)==true){
			Winner = 2;
		}
		if(Winner != -1){
			String MSG = "Player " + Winner + " is the winner!";
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle(MSG);
			alert.setContentText(MSG);
			alert.show();
			return true;
		}
		return false;
	}
	
	//establish computer player
	void AutoPlay(){		
		for(int[] i : WinningPatterns){
			int[] judge = {0,0,0,0,0,0,0,0,0};
			int[] oppJudge = {0,0,0,0,0,0,0,0,0};			
			//determines how close the players are to winning
			for(int j = 0; j < 9; j++){
				if(Player2[j]==1 && i[j] ==1){
					judge[j]=1;
				}
				if(Player1[j] ==1 && i[j] ==1){
					oppJudge[j] =1;
				}
			}
			int sum = IntStream.of(judge).sum();
			int oppSum = IntStream.of(oppJudge).sum();		
			//computer takes winning move
			if(sum == 2){
				for(int k = 0; k < 9; k++){
					if(judge[k] == 0 && i[k] == 1 && FullBoard[k] == 0){
						PlayGame(k, SelectedButton(k));
						return;
					}
				}
			}
			//blocks Player1 from winning
			if(oppSum==2){
				for(int m =0; m <9; m++){
					if(oppJudge[m]==0 && FullBoard[m]==0 && i[m] ==1){
						PlayGame(m, SelectedButton(m));
						return;
					}
				}
			}
		}
		//move generated just in case
		for(int n=0; n<9; n++){
			if(FullBoard[n]==0){
				PlayGame(n, SelectedButton(n));
				return;
			}
		}
	}
	
    public static void main(String[] args){
		launch(args);
	}
}
