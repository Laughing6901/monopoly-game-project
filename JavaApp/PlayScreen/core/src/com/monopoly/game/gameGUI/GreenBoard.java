package com.monopoly.game.gameGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class GreenBoard {
  private Texture boardImg;
  private SpriteBatch sb;
  private BitmapFont word;
  private boolean boardOn;
  private float widthCard; // Width of card which board go along with if equal 0 => festival

  public GreenBoard(SpriteBatch sb) {
    this.sb = sb;
    boardImg = new Texture("greenBoard.png");
    this.word = new Word().word(45, Color.WHITE, "NerkoOne-Regular.ttf");
    boardOn = false;
  }

  public void boardOn(boolean on) {
    boardOn = on;
  }

  public void setCardAlong(float widthCard) {
    this.widthCard = widthCard;
  }

  public boolean isOn() {
    return boardOn;
  }

  public float getWidth() {
    return boardImg.getWidth();
  }

  public void render(String text) {
    if (boardOn) {
      String description = "Choose a property to sell and pay money";
      float x = Gdx.graphics.getWidth()/2 - boardImg.getWidth()/2 + widthCard/2;
      float y = Gdx.graphics.getHeight()/2 - boardImg.getHeight()/2;
      if (widthCard == 0) {
        if (text.equals("Would you like to get out of jail using cash?"))
          description = "You are in jail!";
        else
          description = "Organize festival in?";
      }
      sb.draw(boardImg, x, y);
      word.draw(sb, description, x + boardImg.getWidth()*0.05f, y + boardImg.getHeight()*0.9f, boardImg.getWidth()*0.9f, Align.left, true);
      word.draw(sb, text, x + boardImg.getWidth()*0.05f, y + boardImg.getHeight()*0.6f, boardImg.getWidth() * 0.9f, Align.left, true);
    }
  }
}
