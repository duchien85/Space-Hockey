package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.airhockey.auth.Authentication;
import com.mygdx.airhockey.database.DatabaseController;
import com.mygdx.airhockey.database.TuDbConnectionFactory;


public class SignupScreen extends AuthScreen {
    transient String soundPath;

    /**
     * Constructor for signup screen.
     *
     * @param g game of the login screen;
     */
    public SignupScreen(Game g, String soundPath) {
        super(g,soundPath);
        this.soundPath = soundPath;

        createBtn("Sign up", new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {

                btnSignupClicked();
                return true;
            }
        });
    }

    /**
     * Performs a check after clicking signup button.
     */
    public void btnSignupClicked() {
        String username = txfUsername.getText();
        String password = txfPassword.getText();
        DatabaseController database = new DatabaseController(new TuDbConnectionFactory());
        Authentication auth = new Authentication(database);
        if (auth.signUp(username, password)) {
            game.setScreen(new LoginScreen(game,soundPath));
        } else {
            txfUsername.setColor(Color.RED);
            txfPassword.setColor(Color.RED);
            System.out.println("Try again");
        }
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
}
