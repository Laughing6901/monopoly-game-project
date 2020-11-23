package com.kuerlgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class monopoly_map extends ApplicationAdapter {
    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

    TextureAtlas textureAtlas, backAtlas;

    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        textureAtlas = new TextureAtlas("block_Features.txt");

        backAtlas = new TextureAtlas("back_.txt");
    }
    private void addBackSprite() {
        Array<AtlasRegion> regions = backAtlas.getRegions();
        //render background
        drawThings(regions, 1, 50, 500, 0.8f, 0.8f);
        drawThings(regions, 2, -260, -250, 1, 1);
        drawThings(regions, 2, -260, -250, 1, 1);
    }

    private void drawThings(Array<AtlasRegion> regions, int texture_ordinal, float x_position, float y_position, float x_Scale, float y_Scale) {
        AtlasRegion region = regions.get(texture_ordinal);
        Sprite sprite = backAtlas.createSprite(region.name);
        sprite.setPosition(x_position, y_position);
        sprite.setSize(sprite.getWidth()*x_Scale, sprite.getHeight()*y_Scale);
        sprites.put(region.name, sprite);
        sprite.draw(batch);
    }

    private void addSprites() {
        Array<AtlasRegion> regions = textureAtlas.getRegions();
        // render c
        drawSprite(regions, regions.size/4*2, (Gdx.graphics.getWidth()/2)-176, Gdx.graphics.getHeight()-206);
        // render c list
        for (int i = regions.size/4*2+1, x_position_blocks_small = 900, y_position_blocks_small = Gdx.graphics.getHeight()-250; i < regions.size/4*3; i++) {
            drawSprite(regions, i, x_position_blocks_small, y_position_blocks_small);
            x_position_blocks_small+=73;
            y_position_blocks_small-=42;
        }
        // render d
        drawSprite(regions, regions.size/4*3, 1410, Gdx.graphics.getHeight()/2-114);
        //render d list
        for (int i = regions.size/4*3+1, x_position_blocks_small = 1337, y_position_blocks_small = Gdx.graphics.getHeight()/2-132; i < regions.size; i++) {
            drawSprite(regions, i, x_position_blocks_small, y_position_blocks_small);
            x_position_blocks_small-=73;
            y_position_blocks_small-=42;
        }
        //render b list
        for (int i = regions.size/4*2-1, x_position_blocks_small = 680, y_position_blocks_small = Gdx.graphics.getHeight()-251; i > regions.size/4*1; i--) {
            drawSprite(regions, i, x_position_blocks_small, y_position_blocks_small);
            x_position_blocks_small-=73;
            y_position_blocks_small-=42;
        }
        // render b
        drawSprite(regions, regions.size/4*1, 36, Gdx.graphics.getHeight()/2-105);
        // render a list
        for (int i = regions.size/4*1-1, x_position_blocks_small = 240, y_position_blocks_small = Gdx.graphics.getHeight()/2-133; i > regions.size/4*0; i--) {
            drawSprite(regions, i, x_position_blocks_small, y_position_blocks_small);
            x_position_blocks_small+=73;
            y_position_blocks_small-=42;
        }
        //render a
        drawSprite(regions, regions.size/4*0, (Gdx.graphics.getWidth()/2)-189, -12);
    }

    // drawSprite() input information to render the texture items;
    private void drawSprite(Array<AtlasRegion> regions, int texture_ordinal, float x_position, float y_position) {
        AtlasRegion region = regions.get(texture_ordinal);
        Sprite sprite = textureAtlas.createSprite(region.name);
        sprite.setPosition(x_position, y_position);
        //to setSize of the texture when texture_ordinal at 0/8/16/24 in this map
            if(texture_ordinal == regions.size/4*0 ||
               texture_ordinal == regions.size/4*1 ||
               texture_ordinal == regions.size/4*2 ||
               texture_ordinal == regions.size/4*3
            ) {
                sprite.setSize(sprite.getWidth()*0.9f, sprite.getHeight()*0.9f);
            }
        sprites.put(region.name, sprite);
        sprite.draw(batch);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        addBackSprite();
        addSprites();

        batch.end();
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
        sprites.clear();
    }
}