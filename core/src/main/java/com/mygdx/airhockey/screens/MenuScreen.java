package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen extends ScreenBase {

    private transient Stage stage;
    private transient Game game;
    private static final TextureRegion backgroundTexture = new TextureRegion(
            new Texture("arcade.png"), 0, 0, 900, 900);

    /**
     * Instantiates a menu screen.
     * @param game to initialize the screen with.
     */
    public MenuScreen(Game game, boolean playSound) {
        super(game, "music/open-space.mp3", "arcade.png");
        if (playSound) {
            System.out.println("playing");
            backgroundSound.play();
        }
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin mySkin = new Skin(Gdx.files.internal("Craftacular_UI_Skin/craftacular-ui.json"));

        Label logo = new Label("SPACE HOCKEY", mySkin, "title");
        logo.setFontScale(1);
        logo.setAlignment(Align.center);
        logo.setWidth(1000);
        int rowHeight = Gdx.graphics.getWidth() / 12;
        logo.setPosition(10, Gdx.graphics.getHeight() - rowHeight * 2);
        logo.setColor(Color.GOLD);
        stage.addActor(logo);

        Label title = new Label("Air Hockey made with love by Group 45", mySkin);
        int colWidth = Gdx.graphics.getWidth() / 12;
        title.setPosition(colWidth * 2, Gdx.graphics.getHeight() - rowHeight * 11);
        stage.addActor(title);

        Button startGameButton = new TextButton("Log in", mySkin);
        startGameButton.setSize(colWidth * 3,rowHeight);
        startGameButton.setPosition(colWidth * 4.5f,Gdx.graphics.getHeight() - rowHeight * 9);
        startGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MenuScreen.this.game.setScreen(
                        new LoginScreen(MenuScreen.this.game, "music/open-space.mp3"));
                return true;
            }
        });
        stage.addActor(startGameButton);



        Button signUpButton = new TextButton("Sign Up",mySkin);
        signUpButton.setSize(colWidth * 3,rowHeight);
        signUpButton.setPosition(colWidth * 4.5f,Gdx.graphics.getHeight() - rowHeight * 8);
        signUpButton.addListener(new InputListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MenuScreen.this.game.setScreen(
                        new SignupScreen(game, "music/open-space.mp3"));
                return true;
            }
        });
        stage.addActor(signUpButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0,Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
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
