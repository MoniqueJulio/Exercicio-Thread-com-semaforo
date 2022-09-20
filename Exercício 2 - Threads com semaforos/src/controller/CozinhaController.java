package controller;

import java.util.concurrent.Semaphore;

public class CozinhaController extends Thread {

	private Semaphore semaforo;

	public CozinhaController(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	public void run() {

		if (getId() % 2 != 0) {
			sopaDeCebola();
		}

		if (getId() % 2 == 0) {
			lasanhaBolonhesa();
		}
	}

	private void sopaDeCebola() {

		System.out.println("Iniciou sopa de cebola");

		int tempoTotal = (int) ((Math.random() * 301) + 500);
		int tempoInicial = 100;

		while (tempoTotal > tempoInicial) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			double percentualCozimento = (100 * (tempoInicial)) / tempoTotal;
			System.out.println(
					"Lasanha a Bolonhesa nº " + getId() + " Percentual de cozimento: " + percentualCozimento + "%");
			tempoInicial += 100;
		}

		entrega();
	}

	private void lasanhaBolonhesa() {
		System.out.println("Iniciou lasanha a bolonhesa");

		int tempoTotal = (int) ((Math.random() * 601) + 600);
		int tempoInicial = 100;

		while (tempoTotal > tempoInicial) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			double percentualCozimento = (100 * (tempoInicial)) / tempoTotal;
			System.out.println(
					"Lasanha a Bolonhesa nº " + getId() + " Percentual de cozimento: " + percentualCozimento + "%");
			tempoInicial += 100;
		}
		
		entrega();
	}

	public void entrega() {

		try {
			semaforo.acquire();

			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		finally {
			semaforo.release();
			System.out.println("Prato " + getId() + " entregue!");
		}
	}
}
