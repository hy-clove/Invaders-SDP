package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import CtrlS.CurrencyManager;
import CtrlS.RoundState;
import CtrlS.ReceiptScreen;
import CtrlS.UpgradeManager;
import Sound_Operator.SoundManager;
import clove.Statistics;
import level_design.Background;
import clove.AchievementManager;
import screen.*;
import twoplayermode.TwoPlayerMode;

import static engine.Globals.*;
import static screen.Setting.*;

/**
 * Implements core game logic.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public final class Core {
	/** Frame to draw the screen on. */
	private static Frame frame;
	/** Screen currently shown. */
	private static Screen currentScreen;
	/** Difficulty settings list. */
	private static List<GameSettings> gameSettings;
	/** Application logger. */
	private static final Logger LOGGER = Logger.getLogger(Core.class.getSimpleName());
	/** Logger handler for printing to disk. */
	private static Handler fileHandler;
	/** Logger handler for printing to console. */
	private static ConsoleHandler consoleHandler;
    private AchievementManager achievementManager; // Team CLOVER
	/**
	 * Test implementation.
	 * 
	 * @param args
	 *            Program args, ignored.
	 */
	public static void main(final String[] args) {
		try {
			LOGGER.setUseParentHandlers(false);

			fileHandler = new FileHandler("log");
			fileHandler.setFormatter(new MinimalFormatter());

			consoleHandler = new ConsoleHandler();
			consoleHandler.setFormatter(new MinimalFormatter());

			LOGGER.addHandler(fileHandler);
			LOGGER.addHandler(consoleHandler);
			LOGGER.setLevel(Level.ALL);

			// TEAM CLOVER : Added log to check if function is working
			System.out.println("Initializing AchievementManager...");
			Globals.getAchievementManager();
			System.out.println("AchievementManager initialized!");

			// CtrlS: Make instance of Upgrade Manager
			Globals.getUpgradeManager();

			//Clove. Reset Player Statistics After the Game Starts
			Statistics statistics = new Statistics();
			statistics.resetStatistics();
			LOGGER.info("Reset Player Statistics");

		} catch (Exception e) {
			// TODO handle exception
			e.printStackTrace();
		}

		frame = new Frame(WIDTH, HEIGHT);
		DrawManager.getInstance().setFrame(frame);
		int width = frame.getWidth();
		int height = frame.getHeight();

		/** ### TEAM INTERNATIONAL ###*/
		/** Initialize singleton instance of a background*/
		Background.getInstance().initialize(frame);

		gameSettings = new ArrayList<GameSettings>();
		gameSettings.add(SETTINGS_LEVEL_1);
		gameSettings.add(SETTINGS_LEVEL_2);
		gameSettings.add(SETTINGS_LEVEL_3);
		gameSettings.add(SETTINGS_LEVEL_4);
		gameSettings.add(SETTINGS_LEVEL_5);
		gameSettings.add(SETTINGS_LEVEL_6);
		gameSettings.add(SETTINGS_LEVEL_7);
		
		GameState gameState;
		RoundState roundState;

		int returnCode = 1;
		do {
			// Add playtime parameter - Soomin Lee / TeamHUD
			// Add hitCount parameter - Ctrl S
			// Add coinItemsCollected parameter - Ctrl S
			gameState = new GameState(1, 0
					, MAX_LIVES, 0,0, 0, 0, 0, 0, 0, 0);
			switch (returnCode) {
			case 1:
				// Main menu.
                currentScreen = new TitleScreen(width, height, FPS);
				LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
						+ " title screen at " + FPS + " fps.");
				returnCode = frame.setScreen(currentScreen);
				LOGGER.info("Closing title screen.");
				break;
			case 2:
				// Game & score.
				LOGGER.info("Starting inGameBGM");
				// Sound Operator
				Globals.getSoundManager().playES("start_button_ES");
				Globals.getSoundManager().playBGM("inGame_bgm");

				do {
					// One extra live every few levels.
					boolean bonusLife = gameState.getLevel()
							% EXTRA_LIFE_FRECUENCY == 0
							&& gameState.getLivesRemaining() < MAX_LIVES;

					GameState prevState = gameState;
					currentScreen = new GameScreen(gameState,
							gameSettings.get(gameState.getLevel() - 1),
							bonusLife, width, height, FPS);
					LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
							+ " game screen at " + FPS + " fps.");
					frame.setScreen(currentScreen);
					LOGGER.info("Closing game screen.");


					Globals.getAchievementManager().updateAchievements(currentScreen); // TEAM CLOVER : Achievement

					Statistics statistics = new Statistics(); //Clove

					gameState = ((GameScreen) currentScreen).getGameState();

					roundState = new RoundState(prevState, gameState);

					// Add playtime parameter - Soomin Lee / TeamHUD
					gameState = new GameState(gameState.getLevel() + 1,
							gameState.getScore(),
							gameState.getLivesRemaining(),
							gameState.getLivesTwoRemaining(),
							gameState.getBulletsShot(),
							gameState.getShipsDestroyed(),
							gameState.getTime(),
							gameState.getCoin() + roundState.getRoundCoin(),
							gameState.getGem(),
							gameState.getHitCount(),
							gameState.getCoinItemsCollected());
          			LOGGER.info("Round Coin: " + roundState.getRoundCoin());
					LOGGER.info("Round Hit Rate: " + roundState.getRoundHitRate());
					LOGGER.info("Round Time: " + roundState.getRoundTime());

					try { //Clove
						statistics.addTotalPlayTime(roundState.getRoundTime());
						LOGGER.info("RoundTime Saving");
					} catch (IOException e){
						LOGGER.info("Failed to Save RoundTime");
					}

					// Show receiptScreen
					// If it is not the last round and the game is not over
					// Ctrl-S
					if (gameState.getLevel() <= 7 && gameState.getLivesRemaining() > 0) {
						LOGGER.info("loading receiptScreen");
						currentScreen = new ReceiptScreen(width, height, FPS, roundState, gameState);

						LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
								+ " receipt screen at " + FPS + " fps.");
						frame.setScreen(currentScreen);
						LOGGER.info("Closing receiptScreen.");
					}

                    Globals.getAchievementManager().updateAchievements(currentScreen);


				} while (gameState.getLivesRemaining() > 0
						&& gameState.getLevel() <= NUM_LEVELS);

				LOGGER.info("Stop InGameBGM");
				// Sound Operator
				Globals.getSoundManager().stopAllBGM();

				LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
						+ " score screen at " + FPS + " fps, with a score of "
						+ gameState.getScore() + ", "
						+ gameState.getLivesRemaining() + " lives remaining, "
						+ gameState.getBulletsShot() + " bullets shot and "
						+ gameState.getShipsDestroyed() + " ships destroyed.");
				currentScreen = new ScoreScreen(width, height, FPS, gameState);
				returnCode = frame.setScreen(currentScreen);
				LOGGER.info("Closing score screen.");
				break;
			case 3:
				// High scores.
				currentScreen = new HighScoreScreen(width, height, FPS);
				LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
						+ " high score screen at " + FPS + " fps.");
				returnCode = frame.setScreen(currentScreen);
				LOGGER.info("Closing high score screen.");
				break;
			case 4:
				LOGGER.info("Starting inGameBGM");
				// Sound Operator
				Globals.getSoundManager().playES("start_button_ES");
				Globals.getSoundManager().playBGM("inGame_bgm");

				do {
					if (gameSettings == null || gameSettings.isEmpty()) {
						gameSettings = new ArrayList<>();
						gameSettings.add(SETTINGS_LEVEL_1);
						gameSettings.add(SETTINGS_LEVEL_2);
						gameSettings.add(SETTINGS_LEVEL_3);
						gameSettings.add(SETTINGS_LEVEL_4);
						gameSettings.add(SETTINGS_LEVEL_5);
						gameSettings.add(SETTINGS_LEVEL_6);
						gameSettings.add(SETTINGS_LEVEL_7);
					}

					GameSettings currentGameSettings = gameSettings.get(gameState.getLevel() - 1);

					int fps = FPS;
					boolean bonusLife = gameState.getLevel() % EXTRA_LIFE_FRECUENCY == 0 &&
							(gameState.getLivesRemaining() < MAX_LIVES || gameState.getLivesTwoRemaining() < MAX_LIVES);

					GameState prevState = gameState;

					// TwoPlayerMode의 생성자를 호출할 때 필요한 매개변수를 모두 전달
					currentScreen = new TwoPlayerMode(gameState, currentGameSettings, bonusLife, width, height, fps);
					currentScreen.setTwoPlayerMode(true);
					Statistics statistics = new Statistics(); //Clove


					LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
							+ " game screen at " + FPS + " fps.");
					frame.setScreen(currentScreen);
					LOGGER.info("Closing game screen.");

					Globals.getAchievementManager().updateAchievements(currentScreen); // TEAM CLOVER : Achievement

					gameState = ((TwoPlayerMode) currentScreen).getGameState();

					roundState = new RoundState(prevState, gameState);

					// Add playtime parameter - Soomin Lee / TeamHUD
					gameState = new GameState(gameState.getLevel() + 1,
							gameState.getScore(),
							gameState.getLivesRemaining(),
							gameState.getLivesTwoRemaining(),
							gameState.getBulletsShot(),
							gameState.getShipsDestroyed(),
							gameState.getTime(),
							gameState.getCoin() + roundState.getRoundCoin(),
							gameState.getGem(),
							gameState.getHitCount(),
							gameState.getCoinItemsCollected());
					LOGGER.info("Round Coin: " + roundState.getRoundCoin());
					LOGGER.info("Round Hit Rate: " + roundState.getRoundHitRate());
					LOGGER.info("Round Time: " + roundState.getRoundTime());

					try { //Clove
						statistics.addTotalPlayTime(roundState.getRoundTime());
						LOGGER.info("RoundTime Saving");
					} catch (IOException e){
						LOGGER.info("Failed to Save RoundTime");
					}

					// Show receiptScreen
					// If it is not the last round and the game is not over
					// Ctrl-S
					if (gameState.getLevel() <= 7 && gameState.getLivesRemaining() > 0) {
						LOGGER.info("loading receiptScreen");
						currentScreen = new ReceiptScreen(width, height, FPS, roundState, gameState);

						LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
								+ " receipt screen at " + FPS + " fps.");
						frame.setScreen(currentScreen);
						LOGGER.info("Closing receiptScreen.");
					}

					Globals.getAchievementManager().updateAchievements(currentScreen);

				} while ((gameState.getLivesRemaining() > 0 || gameState.getLivesTwoRemaining() > 0) && gameState.getLevel() <= NUM_LEVELS);

				LOGGER.info("Stop InGameBGM");
				// Sound Operator
				Globals.getSoundManager().stopAllBGM();

				LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
						+ " score screen at " + FPS + " fps, with a score of "
						+ gameState.getScore() + ", "
						+ gameState.getLivesRemaining() + " lives remaining, "
						+ gameState.getBulletsShot() + " bullets shot and "
						+ gameState.getShipsDestroyed() + " ships destroyed.");
				currentScreen = new ScoreScreen(width, height, FPS, gameState);
				returnCode = frame.setScreen(currentScreen);
				LOGGER.info("Closing score screen.");
				break;
			case 5: // 7 -> 5 replaced by Starter
				// Recent Records.
				currentScreen = new RecordScreen(width, height, FPS);
				LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
						+ " recent record screen at " + FPS + " fps.");
				returnCode = frame.setScreen(currentScreen);
				LOGGER.info("Closing recent record screen.");
				break;
			default:
				break;
			}

		} while (returnCode != 0);

		fileHandler.flush();
		fileHandler.close();
		System.exit(0);
	}

	/**
	 * Constructor, not called.
	 */
	private Core() {

	}

	/**
	 * Controls access to the logger.
	 * 
	 * @return Application logger.
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Controls creation of new cooldowns.
	 * 
	 * @param milliseconds
	 *            Duration of the cooldown.
	 * @return A new cooldown.
	 */
	public static Cooldown getCooldown(final int milliseconds) {
		return new Cooldown(milliseconds);
	}

	/**
	 * Controls creation of new cooldowns with variance.
	 * 
	 * @param milliseconds
	 *            Duration of the cooldown.
	 * @param variance
	 *            Variation in the cooldown duration.
	 * @return A new cooldown with variance.
	 */
	public static Cooldown getVariableCooldown(final int milliseconds,
			final int variance) {
		return new Cooldown(milliseconds, variance);
	}
}
