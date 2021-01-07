package com.monopoly.game.gameGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Aircraft {
  private Texture rocketImg, planeImg;
  private Texture effect;
  private SpriteBatch sb;
  // private long launchTime, landTime;
  private long rocketTime, planeTime;
  private boolean isLaunching, isLanding, planeStart;
  private long rocketDuration, planeDuration;
  private float x, y;
  private float xPlane, yPlane;

  private enum Status {
    LAUNCH, LAND
  };

  private Status aircraftStatus;

  public Aircraft(SpriteBatch sb) {
    rocketImg = new Texture("aircraft/rocket.png");
    planeImg = new Texture("playScreenAssets/PlayerTwo/f52.png");
    effect = new Texture("aircraft/effect.png");
    this.sb = sb;
    rocketDuration = Long.valueOf(10) * 1000000000;
    planeDuration = Long.valueOf(15) * 1000000000;
    x = Gdx.graphics.getWidth() * 0.4f;
    y = Gdx.graphics.getHeight() * 0.4f;
    xPlane = yPlane = 0;
    rocketTime = planeTime = TimeUtils.nanoTime();
    isLaunching = isLanding = planeStart = false;
    aircraftStatus = Status.LAUNCH;
  }

  public void render() {
    sb.draw(rocketImg, x, y, rocketImg.getWidth() * 0.8f, rocketImg.getHeight() * 0.8f);
    // Handle render
    if (isLaunching) {
      sb.draw(effect, x + (rocketImg.getWidth() * 0.8f - effect.getWidth() * 2) / 2, y - effect.getHeight() * 2 + 10,
          effect.getWidth() * 2, effect.getHeight() * 2);
      y += 250 * Gdx.graphics.getDeltaTime();
      if (y > Gdx.graphics.getHeight()) {
        isLaunching = false;
        rocketTime = TimeUtils.nanoTime();
        aircraftStatus = Status.LAND;
      }
    } else if (isLanding) {
      sb.draw(effect, x + (rocketImg.getWidth() * 0.8f - effect.getWidth() * 2) / 2, y - effect.getHeight() * 2 + 10,
          effect.getWidth() * 2, effect.getHeight() * 2);
      y -= 200 * Gdx.graphics.getDeltaTime();
      if (y < Gdx.graphics.getHeight() * 0.4f) {
        isLanding = false;
        rocketTime = TimeUtils.nanoTime();
        aircraftStatus = Status.LAUNCH;
      }
    }

    if (planeStart) {
      sb.draw(planeImg, xPlane, yPlane);
      xPlane += 600 * Gdx.graphics.getDeltaTime();
      yPlane = (float) (0.55555 * xPlane);
      if (xPlane > Gdx.graphics.getWidth()) {
        planeStart = false;
        planeTime = TimeUtils.nanoTime();
        xPlane = yPlane = 0;
      }
    }

    // Check rocketTime
    if (TimeUtils.nanoTime() - rocketTime > rocketDuration) {
      switch (aircraftStatus) {
        case LAUNCH:
          isLaunching = true;
          break;
        case LAND:
          isLanding = true;
          break;
      }
    }
    if (TimeUtils.nanoTime() - planeTime > planeDuration) {
      planeStart = true;
    }
  }
}
