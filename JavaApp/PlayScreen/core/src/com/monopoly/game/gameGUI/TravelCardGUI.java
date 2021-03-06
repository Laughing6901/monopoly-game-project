package com.monopoly.game.gameGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class TravelCardGUI implements CardGUI {
  private Texture bought; 
  private Texture travelImg;
  private SpriteBatch sb;
  private BitmapFont wordName, wordPrice;
  private boolean cardOn;
  private boolean isBought;
  private String name, price;
  private Sound cardSound;

  public TravelCardGUI(SpriteBatch sb) {
    this.sb = sb;
    travelImg = new Texture("card/travelCard.jpg");
    bought = new Texture("card/bought.png");
    cardOn = false;
    isBought = false;
    wordName = new Word().word(40, Color.BLACK, "NerkoOne-Regular.ttf");
    wordPrice = new Word().word(30, Color.BLACK, "NerkoOne-Regular.ttf");
    cardSound = Gdx.audio.newSound(Gdx.files.internal("music/card.ogg"));
  }

  public void setContent(boolean bought, String name, String price) {
    isBought = bought;
    this.name = name;
    this.price = price;
  }

  public void cardOn(boolean on) {
    if (on) {
      cardSound.play();
    }
    cardOn = on;
  }

  public boolean isOn() {
    return cardOn;
  }

  public float getWidth() {
    return travelImg.getWidth();
  }

  public void render(float moreWidth, boolean renderMore) {
    if (cardOn) {
      float x = Gdx.graphics.getWidth()/2 - travelImg.getWidth()/2;
      float y = Gdx.graphics.getHeight()/2 - travelImg.getHeight()/2;
      if (renderMore) {
        x -= moreWidth/2;
      }
      sb.draw(travelImg, x, y);
      if (isBought) {
        sb.draw(bought, x + travelImg.getWidth() - bought.getWidth()*2/3, y, bought.getWidth()*2/3, bought.getHeight()*2/3);
      } wordName.draw(sb, name, x, y + travelImg.getHeight() * 2/3, travelImg.getWidth(), Align.center, false);
      wordPrice.draw(sb, price, x + travelImg.getWidth() * 0.7f, y + travelImg.getHeight() * 0.55f);
    }
  }

  public void dispose() {
    cardSound.dispose();
  }
}
