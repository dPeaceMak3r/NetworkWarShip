package org.academiadecodigo.bootcamp.Player;


import org.academiadecodigo.bootcamp.kuusisto.tinysound.Music;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.Sound;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.TinySound;

public class SoundEffect {


    private Music theme;
    private Music splash;
    private Music explosion;


    public void soundInit() {

        TinySound.init();
        theme = TinySound.loadMusic("BattleShipTheme.wav");
        splash = TinySound.loadMusic("SplashSound.wav");
        explosion = TinySound.loadMusic("ExplosionSound.wav");

    }


    public void playSplash() {
        splash.play(false);
    }

    public void playExplosion() {
        explosion.play(false);
    }

    public void playTheme() {
        theme.play(true);
    }


}


