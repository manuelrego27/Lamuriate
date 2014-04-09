package com.manuelrego.lamuriate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FullscreenActivity extends Activity{

	private MediaPlayer player;
	private Random randomizer = new Random();

	private List<String> frases = Arrays.asList("Lamuriou, vai ter de pagar!", "5 CÊNTIMOS!", "Moedinha na caixa!", "Paga o que deves!", "Incha Porco!", "Vai lavar essa boca!", "Lamuriou, ahora tienes qui pagar!");

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen);

		player = MediaPlayer.create(this, R.raw.lamuria);
		Button submit = (Button) findViewById(R.id.dummy_button);
		final TextView txt = (TextView) findViewById(R.id.txt);

		txt.setText("");

		submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0){
				if (!player.isPlaying()){
					player.start();
					txt.setText(frases.get(randomizer.nextInt(frases.size())));
				}
				else{
					txt.setText("");
					player.stop();
					try{
						player.prepare();
					}
					catch (IllegalStateException e){
						e.printStackTrace();
					}
					catch (IOException e){
						e.printStackTrace();
					}
					player.seekTo(0);
				}
			}
		});

		player.setOnCompletionListener(new OnCompletionListener(){

			@Override
			public void onCompletion(MediaPlayer arg0){
				txt.setText("");
				player.stop();
				try{
					player.prepare();
				}
				catch (IllegalStateException e){
					e.printStackTrace();
				}
				catch (IOException e){
					e.printStackTrace();
				}
				player.seekTo(0);

			}
		});

	}
}
