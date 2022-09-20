package view;

import java.util.concurrent.Semaphore;

import controller.CozinhaController;

public class Main {

	public static void main(String[] args) {
		
		int permissoes=1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int i =0; i<5; i++) {			
			Thread thread = new CozinhaController(semaforo);
			thread.start();
		}
		
	}

}
