package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.airhockey.auth.Authentication;
import com.mygdx.airhockey.database.ConnectionFactory;
import com.mygdx.airhockey.database.DatabaseController;


public class LoginScreen implements Screen {
    public static final int INPUT_BOX_X = 200;
    public static final int WIDTH = 300;
    public static final int HEIGHT = 40;
    private transient Game game;
    private transient Stage stage;
    private transient TextField txfUsername;
    private transient TextField txfPassword;
    transient int rowHeight = Gdx.graphics.getWidth() / 12;
    transient int colWidth = Gdx.graphics.getWidth() / 12;

    /**
     * Constructor for login screen.
     * @param g game of the login screen;
     */
    public LoginScreen(Game g) {
        game = g;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        TextButton btnLogin = new TextButton("Login", skin);
        TextButton goBack = new TextButton("Back", skin);
        goBack.setPosition(colWidth * 5,Gdx.graphics.getHeight() - rowHeight * 8);
        goBack.setSize(colWidth * 2, rowHeight - 20);
        btnLogin.setPosition(colWidth * 4,Gdx.graphics.getHeight() - rowHeight * 6);
        btnLogin.setSize(colWidth * 4, rowHeight);
        btnLogin.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                btnLoginClicked();
                return true;
            }
        });
        goBack.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                game.setScreen(new MenuScreen(game));
                return true;
            }
        });
        txfUsername = new TextField("", skin);
        txfUsername.setPosition(colWidth * 4,Gdx.graphics.getHeight() - rowHeight * 3);
        txfUsername.setSize(colWidth * 4, HEIGHT);
        stage.addActor(txfUsername);
        txfPassword = new TextField("", skin);
        txfPassword.setPosition(colWidth * 4,Gdx.graphics.getHeight() - rowHeight * 4);
        txfPassword.setSize(colWidth * 4, HEIGHT);
        txfPassword.setPasswordCharacter('*');
        txfPassword.setPasswordMode(true);
        stage.addActor(txfPassword);
        stage.addActor(btnLogin);
        stage.addActor(goBack);

    }

    /**
     * Performs a check after clicking login button.
     */
    public void btnLoginClicked() {
        String username = txfUsername.getText();
        String password = txfPassword.getText();
        DatabaseController database = new DatabaseController(new ConnectionFactory());
        Authentication auth = new Authentication(database);
        if (auth.signIn(username, password)) {
            game.setScreen(new GameScreen(game));
        } else {
            System.out.println("Try again");
        }
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Renders the screen.
     * @param delta frequency.
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
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

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }


}