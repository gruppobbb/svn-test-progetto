package model.scores;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Componente che si occupa del calcolo del punteggio.
 * @author Bianca
 */
public class ScoreCalculator {
	
	private ScoreKeeper scores = ScoreKeeper.getScoreKeeper();
	private long score = 0;
	private static final int COEFF = 50;
	Timer timer;
	
	/**
	 * Avvia il calcolatore dei punteggi.
	 */
	public void start(){
		timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {

				score = score+COEFF;
				System.out.println(score);
			}
		};
		timer.scheduleAtFixedRate(task, 0, 200);
	}
	
	/**
	 * Ferma il calcolatore dei punteggi.
	 */
	public void stop(){
		scores.addScore(score);
		timer.cancel();
	}

	/**
	 * Restituisce il punteggio attuale.
	 * @return
	 */
	public long getScore() {
		return score;
	}
}
