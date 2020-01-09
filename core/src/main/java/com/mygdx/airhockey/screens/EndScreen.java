package com.mygdx.airhockey.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

//to be added to the game once game finished functionality is implemented.
//TODO create and display leaderboard 
//TODO display score
//TODO display text
public class EndScreen implements Screen {
    private transient Stage stage;
    private transient Game game;

    /**
     * Instantiates a end screen.
     *
     * @param game to initialize the screen with.
     */
    public EndScreen(Game game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        int rowHeight = Gdx.graphics.getWidth() / 12;
        int colWidth = Gdx.graphics.getWidth() / 12;

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Button startGameButton = new TextButton("Play again", mySkin);
        startGameButton.setSize(colWidth * 4, rowHeight);
        startGameButton.setPosition(colWidth * 4, Gdx.graphics.getHeight() - rowHeight * 4);
        startGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                EndScreen.this.game.setScreen(new GameScreen(EndScreen.this.game));
                return true;
            }
        });
        stage.addActor(startGameButton);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        //stage.getBatch().draw(backgroundTexture, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

}
