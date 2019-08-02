import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Running this class with the Applet Viewer will simulate a Black Jack game. Both the dealer(computer) 
 * and user will start out with two cards.
 * @author Bryce DeVaughn
 *
 */
public class BlackJackApplet extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Deck d;
	private boolean gameOver;
	private ButtonPanel bPanel;
	private BlackJackHand player,dealer;
	private ActionHelper aHelper;
	private DisplayPanel dPanel;
	//private InstructionPanel iPanel;
	private int dScore, pScore;
	private Image backCover;
	private AudioClip music;

	public BlackJackApplet(){
		d = new Deck();
		d.shuffle();
		gameOver = false;
		aHelper = new ActionHelper();
		bPanel = new ButtonPanel(aHelper);
		dPanel = new DisplayPanel();
		//iPanel = new InstructionPanel();
		pScore = 0;//This variable will be used to keep track of wins for the player.
		dScore = 0;//This variable will be used to keep track of wins for the dealer.

		this.getContentPane().setLayout(new BorderLayout(1,1));
		this.getContentPane().add(dPanel,BorderLayout.CENTER);
		this.getContentPane().add(bPanel,BorderLayout.SOUTH);
		//this.getContentPane().add(iPanel,BorderLayout.NORTH);

		/*
		 * Setup for the player.
		 */
		player = new BlackJackHand();
		player.addCard(d.dealNextCard());
		player.addCard(d.dealNextCard());

		/*
		 * Set up for the dealer.
		 */
		dealer = new BlackJackHand();
		dealer.addCard(d.dealNextCard());
		dealer.addCard(d.dealNextCard());

		try {
			backCover = ImageIO.read(new File("cards//back.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init(){
		this.setSize(500,700);
		this.setBackground(Color.white);
	}

	public void paint(Graphics g){
		dPanel.paint(g);
	}

	/**
	 * This class will be used as a panel to display the buttons needed for the user to interact with.
	 * @author Bryce DeVaughn
	 *
	 */
	private class ButtonPanel extends JPanel{

		private static final long serialVersionUID = 1L;
		private JButton quitButton,hitButton,standButton,restartButton;

		public ButtonPanel(ActionHelper a){
			super();
			this.setSize(500, 100);

			quitButton = null;

			quitButton = new JButton("Quit");
			quitButton.addActionListener(a);

			hitButton = new JButton("Hit");
			hitButton.addActionListener(a);

			standButton = new JButton("Stand");
			standButton.addActionListener(a);

			restartButton = new JButton("Restart");
			restartButton.addActionListener(a);

			this.add(quitButton);
			this.add(hitButton);
			this.add(standButton);
			this.add(restartButton);
		}

		public JButton getQuitButton() {
			return quitButton;
		}

		public JButton getHitButton() {
			return hitButton;
		}

		public JButton getStandButton() {
			return standButton;
		}

		public JButton getRestartButton() {
			return restartButton;
		}


	}

	/**
	 * An instance of this class will be used to detect when the user clicks a certain button and carry
	 * out the certain events that occur when each button is pressed.
	 * @author Bryce DeVaughn
	 *
	 */
	private class ActionHelper implements ActionListener{

		private boolean canHit = true;

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == bPanel.getRestartButton()){
				restart();
			}

			if(e.getSource() == bPanel.getQuitButton()){
				System.out.print("Quitting I see...");
				System.exit(0);
			}

			if(canHit){
				if(e.getSource() == bPanel.getStandButton()){
					hitDealer();

					/*
					 * Goes through all of the checking for the win.
					 */
					checkForWinModified();
				}

				if(e.getSource() == bPanel.getHitButton()){
					hitPlayer();

					if(dealer.getValue() < 17 && !dealer.isSoft()){
						hitDealer();
					}
				}

				/*
				 * Goes through all of the checking for the win.
				 */
				checkForWinModified();
			}

			/*
			 * Resets the deck when the cards go below 20.
			 */
			if(d.getCardsLeft() < 20){
				d = new Deck();
				d.shuffle();
			}
			
			checkForWinModified();

		}

		/**
		 * Gives a card to the player.
		 */
		public void hitPlayer(){
			player.addCard(d.dealNextCard());
			repaint();
		}

		/**
		 * Gives a card to the dealer.
		 */
		public void hitDealer(){
			dealer.addCard(d.dealNextCard());
			repaint();
		}

		/**
		 * This method will restart the round for both the player and dealer.
		 */
		public void restart(){
			if(canHit){
				if(player.getNumOfCards()>2 && dealer.getNumOfCards()>2){
					for(int i = 2; i < player.getNumOfCards(); i++){
						d.returnCard(player.removeCard(player.getCards()[i]));
					}

					for(int i = 2; i < dealer.getNumOfCards(); i++){
						d.returnCard(dealer.removeCard(dealer.getCards()[i]));
					}
				}
			}else{
				reset();
				canHit = true;
			}

			repaint();
		}

		/**
		 * This method will do check to see if the dealer or player has won the current round.
		 */
		public void checkForWinModified(){

			if((player.getValue() == 11 && player.isSoft())&&(dealer.getValue() == 11 && dealer.isSoft())){
				canHit = false;
			}
			if(dealer.getValue() == 11 && dealer.isSoft()){
				dScore++;
				canHit = false;
			}
			if(player.getValue() == 11 && player.isSoft()){
				pScore++;
				canHit = false;
			}

			if(canHit){
				if((dealer.getValue() >= 17 && dealer.getValue() <= 21) || (dealer.isSoft() && dealer.getValue()+11 > 17)||dealer.getValue()>17){
					if(player.getValue() == 21 && dealer.getValue() == 21){
						canHit = false;
					}else if(player.getValue() == 21){
						pScore++;
						canHit = false;
					}else if(dealer.getValue() == 21){
						dScore++;
						canHit = false;
					}else if(dealer.isBust() && player.isBust()){
						canHit = false;
					}else if(dealer.isBust()){
						pScore++;
						canHit = false;
					}else if( player.isBust()){
						dScore++;
						canHit = false;
					}else if(dealer.isSoft() && player.isSoft()){
						if(player.getValue()+11 > dealer.getValue() && player.getValue()+11 <=21){
							pScore++;
							canHit = false;
						}else if(dealer.getValue()+11 > player.getValue()+11 && dealer.getValue()+11 <=21){
							dScore++;
							canHit = false;
						}
					}else if(dealer.isSoft() && !player.isSoft()){
						if(dealer.getValue()+11 > player.getValue() && dealer.getValue()<=21){
							dScore++;
							canHit = false;
						}
					}else if(player.isSoft() && dealer.getValue() > 17 && !dealer.isSoft()){
						if(player.getValue()+11 > dealer.getValue() && player.getValue() <=21){
							pScore++;
							canHit = false;
						}
					}else if(player.getValue() == dealer.getValue() && dealer.getValue()>17){
						canHit = false;
					}else if(player.getValue() > dealer.getValue() && dealer.getValue()>17){
						pScore++;
						canHit = false;
					}
				}
			}

			repaint();
		}

		public void reset(){
			for(int i = 0; i < player.getNumOfCards(); i++){
				player.removeCard(player.getCards()[i]);
			}

			for(int i = 0; i < dealer.getNumOfCards(); i++){
				dealer.removeCard(dealer.getCards()[i]);
			}

			player.addCard(d.dealNextCard());
			player.addCard(d.dealNextCard());

			dealer.addCard(d.dealNextCard());
			dealer.addCard(d.dealNextCard());

			repaint();
		}

		public boolean getCanHit(){
			return canHit;
		}
	}

	/**
	 * This class will be used as a display for the cards and other things pertaining to the game.
	 */
	private class DisplayPanel extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DisplayPanel(){
			super();
			this.setSize(700,500);
		}

		public void paint(Graphics gfx){

			if(!gameOver){

				gfx.setColor(new Color(39,82,49));
				gfx.fillRect(0,0,this.getWidth(),this.getHeight());

				/*
				 * This draws the player's cards.
				 */
				for(int i = 0; i < player.getNumOfCards(); i++){
					gfx.drawImage(player.getCards()[i].getImg(), 50+i*100, 400, null);
				}

				/*
				 * This draws the dealer's cards.
				 */
				for(int i = 0; i < dealer.getNumOfCards(); i++){
					gfx.drawImage(dealer.getCards()[i].getImg(), 50+i*100, 100, null);
				}

				gfx.setColor(Color.white);
				//gfx.drawString("Value: "+player.getValue(), 50, 335);
				//gfx.drawString("Cards In Hand: "+player.getNumOfCards(), 50, 320);
				//gfx.drawString("Value: "+dealer.getValue(), 50, 50);
				//gfx.drawString("Cards In Hand: "+dealer.getNumOfCards(), 50, 35);
				gfx.setFont(new Font(gfx.getFont().getName(),Font.BOLD,15));
				gfx.drawString("Cards Left In Deck: "+d.getCardsLeft(), 300, 30);
				gfx.setColor(Color.pink);
				gfx.drawString("Player Wins: "+pScore,50,305);
				gfx.drawString("Dealer Wins: "+dScore,50,20);

				if(player.isSoft() && player.getValue()+11 > 21){
					gfx.setColor(Color.red);
					gfx.drawString("Hand Is: Hard", 150, 350);
				}else if(player.isSoft()){
					gfx.setColor(Color.green);
					gfx.drawString("Hand Is: Soft", 150, 350);
				}


				if(dealer.isSoft()){
					gfx.setColor(Color.green);
					gfx.drawString("Hand Is: Soft", 150, 50);
				}

				if(aHelper.canHit == false){
					gfx.setColor(new Color(235,64,14));
					gfx.drawString("Press the 'Restart' button to play the next round.", 100,550);
				}else{
					/*
					 * This is the card cover for the dealer's first card.
					 */
					gfx.drawImage(backCover, 50, 100, null);
				}

			}

		}
	}
}
