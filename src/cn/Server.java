package cn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static int port = 9990;
	private static ServerSocket server;

	static {
		try {
			server = new ServerSocket(port);
			AcceptClient acceptClient = new AcceptClient();
			Thread th1 = new Thread(acceptClient);
			th1.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}
	static class AcceptClient implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Socket socket = null;

				while ((socket = server.accept()) != null) {
					System.out.println(socket);
					AcceptMsg acceptMsg = new AcceptMsg(socket);
					Thread th = new Thread(acceptMsg);
					th.start();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	static class AcceptMsg implements Runnable {
		private Socket socket;

		public AcceptMsg(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				System.out.println(1);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String line = "";
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					bw.write(line);
					bw.flush();
					bw.newLine();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
