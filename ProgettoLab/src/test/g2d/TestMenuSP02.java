package test.g2d;

import model.Game;
import model.games.SinglePlayer2D;
import view2d.assets.Assets;
import view2d.menu.BMenu;
import view2d.menu.ButtonImageSet;
import view2d.menu.MenuButton;
import view2d.menu.SimpleButtonAnimation;
import view2d.menu.button.ExitMenuButton;
import view2d.menu.button.GameMenuButton;
import view2d.menu.button.NavigationMenuButton;

public class TestMenuSP02 {
	
	public static void main(String[] args) {
				
		BMenu menu = new BMenu("Test");
		Game singlePlayerGame = new SinglePlayer2D(menu);		
		
		ButtonImageSet imageSet = new ButtonImageSet(	Assets.IMAGE_BUTTON_OUT_FOCUS_B,
				Assets.IMAGE_BUTTON_ONFOCUS_UNPRESSED_B,
				Assets.IMAGE_BUTTON_ONFOCUS_PRESSED_B);
		
		MenuButton[] buttons = new MenuButton[3];			
		initMenu(singlePlayerGame, menu, imageSet, buttons);
		
		//###### NOTATEMI #####
		menu.showMenu();
	}
	
	private static void initMenu(Game singlePlayerGame, BMenu menu,
			ButtonImageSet imageSet, MenuButton[] buttons) {
		buttons[0] = new GameMenuButton(menu, singlePlayerGame, "Nuova Partita", imageSet);
		buttons[0].setButtonAnimator(new SimpleButtonAnimation(buttons[0],3));
		buttons[0].setBFont(Assets.FONT_GENERAL, 32.f);
		
		buttons[1] = new NavigationMenuButton(menu, null, "Highscore", imageSet);
		buttons[1].setButtonAnimator(new SimpleButtonAnimation(buttons[1],3));
		buttons[1].setBFont(Assets.FONT_GENERAL, 32.f);

		buttons[2] = new ExitMenuButton("Esci", imageSet);
		buttons[2].setButtonAnimator(new SimpleButtonAnimation(buttons[2],3));
		buttons[2].setBFont(Assets.FONT_GENERAL, 32.f);
		
		menu.addButtons(buttons);
	}
	
	

}
