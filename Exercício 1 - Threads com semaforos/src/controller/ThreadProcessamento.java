package controller;

import java.util.concurrent.Semaphore;

public class ThreadProcessamento extends Thread {

	private Semaphore semaforo;

	public ThreadProcessamento(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	public void run() {

		transacoes();
	}

	public void transacoes() {

		long idThread = getId();
		int tempo;

		if (idThread % 3 == 1) {

			tempo = (int) ((Math.random() * 801) + 200);

			calculos(idThread, tempo);
			BD(idThread, 1000);
			calculos(idThread, tempo);
			BD(idThread, 1000);
			calculos(idThread, tempo);
		}

		if (idThread % 3 == 2) {

			tempo = (int) ((Math.random() * 1001) + 500);

			calculos(idThread, tempo);
			BD(idThread, 1500);
			calculos(idThread, tempo);
			BD(idThread, 1500);
			calculos(idThread, tempo);
			BD(idThread, 1500);
		}

		if (idThread % 3 == 0) {

			tempo = (int) ((Math.random() * 1001) + 1000);

			calculos(idThread, tempo);
			BD(idThread, 1500);
			calculos(idThread, tempo);
			BD(idThread, 1500);
			calculos(idThread, tempo);
			BD(idThread, 1500);
		}
	}

	public void calculos(long idThread, int tempo) {

		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("A thread: " + idThread + " fez calculos de: " + tempo + " ms");
	}

	public void BD(long idThread, int tempo) {

		try {
			semaforo.acquire();

			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		finally {
			semaforo.release();
		}

		System.out.println("A thread " + idThread + " fez BD de " +tempo + " ms");
	}
}
